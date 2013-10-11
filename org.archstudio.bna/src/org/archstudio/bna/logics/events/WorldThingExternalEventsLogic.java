package org.archstudio.bna.logics.events;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.bna.ui.IBNADragAndDropListener;
import org.archstudio.bna.ui.IBNAFocusListener;
import org.archstudio.bna.ui.IBNAKeyListener;
import org.archstudio.bna.ui.IBNAMagnifyGestureListener;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.ui.IBNAMouseClickListener;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.ui.IBNAMouseTrackListener;
import org.archstudio.bna.ui.IBNAPanGestureListener;
import org.archstudio.bna.ui.IBNARotateGestureListener;
import org.archstudio.bna.ui.IBNASwipeGestureListener;
import org.archstudio.bna.ui.IBNATrackGestureListener;
import org.eclipse.swt.events.MouseEvent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public class WorldThingExternalEventsLogic extends AbstractThingLogic {

	protected static final boolean PROFILE = false;
	protected static final LoadingCache<Object, AtomicLong> profileStats = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	@SuppressWarnings("unchecked")
	private static <T> T get(Method method, Object[] args, Class<T> ofType) {
		for (Object arg : args) {
			if (ofType.isInstance(arg)) {
				return (T) arg;
			}
		}
		return null;
	}

	private static <T> void set(Method method, Object[] args, Class<T> ofType, Object[] newArgs, T value) {
		for (int i = 0; i < args.length; i++) {
			if (ofType.isInstance(args[i])) {
				newArgs[i] = value;
				return;
			}
		}
	}

	protected class ProxyHandler implements IThingLogic, InvocationHandler, IBNAMouseListener, IBNAMouseMoveListener {

		protected IHasWorld capturedWorldThing = null;

		public ProxyHandler() throws NoSuchMethodException, SecurityException {
		}

		@Override
		public IBNAWorld getBNAWorld() {
			return world;
		}

		@Override
		public void init() {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			try {
				Method selfMethod = this.getClass().getMethod(method.getName(), method.getParameterTypes());
				return selfMethod.invoke(this, args);
			}
			catch (NoSuchMethodException | SecurityException e) {
			}

			IBNAView view = get(method, args, IBNAView.class);

			if (view != null) {
				for (IThing innerThing : view.getBNAWorld().getBNAModel().getAllThings()) {
					propagateEvent(view, innerThing, method, args);
				}
			}

			return null;
		}

		private void propagateEvent(IBNAView view, IThing innerThing, Method method, Object[] args) {
			if (innerThing instanceof IHasWorld) {
				IBNAWorld iWorld = ((IHasWorld) innerThing).getWorld();
				if (iWorld != null) {

					Object[] newArgs = new Object[args.length];
					System.arraycopy(args, 0, newArgs, 0, args.length);

					WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(innerThing);
					IBNAView iView = worldThingPeer.getInnerView();
					if (iView != null) {
						set(method, args, IBNAView.class, newArgs, iView);

						ICoordinate location = get(method, args, ICoordinate.class);
						if (location != null) {
							ICoordinate iLocation = DefaultCoordinate.forLocal(location.getLocalPoint(),
									iView.getCoordinateMapper());
							set(method, args, ICoordinate.class, newArgs, iLocation);
							List<IThing> iThings = iView.getThingsAt(iLocation);
							set(method, args, List.class, newArgs, iThings);
						}

						for (Object o : iWorld.getThingLogicManager().getThingLogics(method.getDeclaringClass())) {
							try {
								long time = System.nanoTime();
								method.invoke(o, newArgs);
								if (PROFILE) {
									time = System.nanoTime() - time;
									profileStats.get(o).addAndGet(time);
								}
							}
							catch (Throwable t) {
								t.printStackTrace();
							}
						}
					}
				}
			}
		}

		Method MOUSE_DOWN = IBNAMouseListener.class.getMethod("mouseDown", IBNAView.class, MouseType.class,
				MouseEvent.class, List.class, ICoordinate.class);

		@Override
		public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			capturedWorldThing = firstOrNull(things, IHasWorld.class);
			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, MOUSE_DOWN, new Object[] { view, type, evt, things, location });
			}
		}

		Method MOUSE_UP = IBNAMouseListener.class.getMethod("mouseUp", IBNAView.class, MouseType.class,
				MouseEvent.class, List.class, ICoordinate.class);

		@Override
		public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, MOUSE_UP, new Object[] { view, type, evt, things, location });
				capturedWorldThing = null;
			}
		}

		Method MOUSE_MOVE = IBNAMouseMoveListener.class.getMethod("mouseMove", IBNAView.class, MouseType.class,
				MouseEvent.class, List.class, ICoordinate.class);

		@Override
		public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, MOUSE_MOVE, new Object[] { view, type, evt, things, location });
			}
		}
	}

	protected final ThingTypeTrackingLogic typeLogic;

	List<IThingLogic> proxies = Lists.newArrayList();

	public WorldThingExternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		IThingLogic proxy;
		try {
			proxy = (IThingLogic) Proxy.newProxyInstance( //
					this.getClass().getClassLoader(), //
					new Class<?>[] { IThingLogic.class, //
							IBNADragAndDropListener.class, //
							IBNAFocusListener.class, //
							IBNAKeyListener.class, //
							IBNAMagnifyGestureListener.class, //
							IBNAMenuListener.class, //
							IBNAMouseClickListener.class, //
							IBNAMouseListener.class, //
							IBNAMouseMoveListener.class, //
							IBNAMouseTrackListener.class, //
							IBNAPanGestureListener.class, //
							IBNARotateGestureListener.class, //
							IBNASwipeGestureListener.class, //
							IBNATrackGestureListener.class //
					}, new ProxyHandler());
		}
		catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		proxies.add(proxy);
		logics.addThingLogic(proxy);
	}

	@Override
	synchronized public void dispose() {
		for (IThingLogic logic : proxies) {
			logics.removeThingLogic(logic);
		}
		proxies.clear();
		super.dispose();
	}

	protected void createProxy(Class<?> ofType) {
	}
}
