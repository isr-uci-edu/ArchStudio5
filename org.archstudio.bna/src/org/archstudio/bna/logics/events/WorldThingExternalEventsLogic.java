package org.archstudio.bna.logics.events;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class WorldThingExternalEventsLogic extends AbstractThingLogic {

	public boolean PROFILE = false;
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

	private class ProxyHandler implements IThingLogic, InvocationHandler, IBNAMouseListener, IBNAMouseMoveListener,
			IBNAMenuListener {

		protected Method method;
		protected IHasWorld capturedWorldThing = null;

		public ProxyHandler() {
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
			BNAUtils.checkLock();

			this.method = method;
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

		@Override
		public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			BNAUtils.checkLock();

			capturedWorldThing = firstOrNull(things, IHasWorld.class);
			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, method, new Object[] { view, type, evt, things, location });
			}
		}

		@Override
		public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			BNAUtils.checkLock();

			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, method, new Object[] { view, type, evt, things, location });
				capturedWorldThing = null;
			}
		}

		@Override
		public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
			BNAUtils.checkLock();

			if (capturedWorldThing != null) {
				propagateEvent(view, capturedWorldThing, method, new Object[] { view, type, evt, things, location });
			}
			else {
				for (IThing innerThing : view.getBNAWorld().getBNAModel().getAllThings()) {
					if (innerThing instanceof IHasWorld) {
						WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(innerThing);
						if (worldThingPeer.isInThing(location)) {
							propagateEvent(view, innerThing, method, new Object[] { view, type, evt, things, location });
						}
					}
				}
			}
		}

		@Override
		public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
			BNAUtils.checkLock();

			IHasWorld worldThing = firstOrNull(things, IHasWorld.class);
			if (worldThing != null) {
				propagateEvent(view, worldThing, method, new Object[] { view, things, location, menu });
			}
		}

	}

	protected final ThingTypeTrackingLogic typeLogic;

	protected final IThingLogic proxy;

	public WorldThingExternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		try {
			proxy = (IThingLogic) Proxy.newProxyInstance( //
					this.getClass().getClassLoader(), //
					new Class<?>[] { IThingLogic.class, //
							IBNADragAndDropListener.class, //
							IBNAFocusListener.class, //
							//IBNAKeyListener.class, //
							IBNAMagnifyGestureListener.class, //
							IBNAMenuListener.class, //
							IBNAMouseClickListener.class, //
							IBNAMouseListener.class, //
							//IBNAMouseMoveListener.class, //
							IBNAMouseTrackListener.class, //
							IBNAPanGestureListener.class, //
							IBNARotateGestureListener.class, //
							IBNASwipeGestureListener.class, //
							IBNATrackGestureListener.class //
					}, new ProxyHandler());
		}
		catch (IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		logics.addThingLogic(proxy);
	}

	@SuppressWarnings("unchecked")
	public <T> T proxy(Class<T> ofType) {
		BNAUtils.checkLock();

		if (ofType.isInstance(proxy)) {
			return (T) proxy;
		}
		throw new IllegalArgumentException(ofType.toString());
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		logics.removeThingLogic(proxy);
		super.dispose();
	}
}
