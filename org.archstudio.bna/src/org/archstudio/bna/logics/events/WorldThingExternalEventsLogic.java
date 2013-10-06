package org.archstudio.bna.logics.events;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogic;
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

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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

	protected class ProxyHandler implements IThingLogic, InvocationHandler {

		protected final Class<?> ofType;
		protected final Set<Method> methods;

		public ProxyHandler(Class<?> ofType) {
			this.ofType = ofType;
			this.methods = Sets.newHashSet();
			methods.addAll(Arrays.asList(Object.class.getMethods()));
			methods.addAll(Arrays.asList(IThingLogic.class.getMethods()));
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
			if (methods.contains(method)) {
				return method.invoke(this, args);
			}

			IBNAView view = get(method, args, IBNAView.class);
			@SuppressWarnings("unchecked")
			List<IThing> things = get(method, args, List.class);

			if (view != null && things != null) {
				IHasWorld worldThing = firstOrNull(things, IHasWorld.class);
				if (worldThing != null) {
					IBNAWorld iWorld = worldThing.getWorld();
					if (iWorld != null) {

						Object[] newArgs = new Object[args.length];
						System.arraycopy(args, 0, newArgs, 0, args.length);

						WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
						IBNAView iView = worldThingPeer.getInnerView();
						if (iView != null) {
							set(method, args, IBNAView.class, newArgs, iView);

							ICoordinate location = get(method, args, ICoordinate.class);
							ICoordinate iLocation = DefaultCoordinate.forLocal(location.getLocalPoint(),
									iView.getCoordinateMapper());
							set(method, args, ICoordinate.class, newArgs, iLocation);

							List<IThing> iThings = iView.getThingsAt(iLocation);
							set(method, args, List.class, newArgs, iThings);

							for (Object o : iWorld.getThingLogicManager().getThingLogics(ofType)) {
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

			return null;
		}
	}

	protected final ThingTypeTrackingLogic typeLogic;

	List<IThingLogic> proxies = Lists.newArrayList();

	public WorldThingExternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
		createProxy(IBNADragAndDropListener.class);
		createProxy(IBNAFocusListener.class);
		createProxy(IBNAKeyListener.class);
		createProxy(IBNAMagnifyGestureListener.class);
		createProxy(IBNAMenuListener.class);
		createProxy(IBNAMouseClickListener.class);
		createProxy(IBNAMouseListener.class);
		createProxy(IBNAMouseMoveListener.class);
		createProxy(IBNAMouseTrackListener.class);
		createProxy(IBNAPanGestureListener.class);
		createProxy(IBNARotateGestureListener.class);
		createProxy(IBNASwipeGestureListener.class);
		createProxy(IBNATrackGestureListener.class);
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
		IThingLogic proxy = (IThingLogic) Proxy.newProxyInstance( //
				this.getClass().getClassLoader(), //
				new Class<?>[] { ofType, IThingLogic.class }, //
				new ProxyHandler(ofType));
		proxies.add(proxy);
		logics.addThingLogic(proxy);
	}
}
