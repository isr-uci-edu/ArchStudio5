package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IIsBackground;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.sysutils.FastIntMap;
import org.archstudio.sysutils.FastIntMap.Entry;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public class DefaultBNAView implements IBNAView, IBNAModelListener {

	public boolean PROFILE = false;
	protected static final LoadingCache<Object, AtomicLong> profileStats = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	private static final LoadingCache<Class<? extends IThingPeer<?>>, Constructor<?>> constructorsCache = CacheBuilder
			.newBuilder().build(new CacheLoader<Class<? extends IThingPeer<?>>, Constructor<?>>() {

				@Override
				public Constructor<?> load(Class<? extends IThingPeer<?>> key) throws Exception {
					for (Constructor<?> c : key.getConstructors()) {
						Class<?>[] p = c.getParameterTypes();
						if (p.length == 3) {
							if (IThing.class.isAssignableFrom(p[0])) {
								if (IBNAView.class.isAssignableFrom(p[1])) {
									if (ICoordinateMapper.class.isAssignableFrom(p[2])) {
										return c;
									}
								}
							}
						}
					}
					throw new IllegalArgumentException("Cannot create peer for " + key);
				}
			});

	protected final IBNAView parentView;
	protected final IBNAWorld world;
	protected final FastIntMap<IThingPeer<?>> peers = new FastIntMap<>(1000);
	protected final ICoordinateMapper cm;
	protected IBNAUI bnaUI;

	public DefaultBNAView(@Nullable IBNAView parentView, IBNAWorld bnaWorld, ICoordinateMapper cm) {
		super();
		this.parentView = parentView;
		this.world = checkNotNull(bnaWorld);
		this.cm = checkNotNull(cm);
		world.getBNAModel().addBNAModelListener(this);
	}

	@Override
	synchronized public void dispose() {
		world.getBNAModel().removeBNAModelListener(this);
		for (Entry<IThingPeer<?>> entry : peers.entries()) {
			try {
				entry.getValue().dispose();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		peers.clear();
		if (PROFILE) {
			for (java.util.Map.Entry<Object, AtomicLong> entry : SystemUtils.sortedByValue(profileStats.asMap()
					.entrySet())) {
				System.err.println(entry.getValue() + "\t" + entry.getKey());
			}
		}
	}

	@Override
	public IBNAView getParentView() {
		return parentView;
	}

	@Override
	public IBNAUI getBNAUI() {
		return bnaUI;
	}

	@Override
	public void setBNAUI(IBNAUI bnaUI) {
		if (!SystemUtils.nullEquals(this.bnaUI, bnaUI)) {
			this.bnaUI = bnaUI;
			for (IThing t : getBNAWorld().getBNAModel().getAllThings()) {
				IThingPeer<?> tp = getThingPeer(t);
				if (tp instanceof IHasInnerViewPeer) {
					IBNAView innerView = ((IHasInnerViewPeer<?>) tp).getInnerView();
					if (innerView != null) {
						innerView.setBNAUI(bnaUI);
					}
				}
			}
		}
	}

	@Override
	public IBNAWorld getBNAWorld() {
		return world;
	}

	@Override
	public ICoordinateMapper getCoordinateMapper() {
		return cm;
	}

	@Override
	public List<IThing> getThingsAt(ICoordinate location) {
		location = DefaultCoordinate.forWorld(location.getWorldPoint(), cm);
		List<IThing> things = Lists.newArrayList();
		for (IThing t : getBNAWorld().getBNAModel().getReverseThings()) {
			if (t.has(IIsBackground.BACKGROUND_KEY, Boolean.TRUE)) {
				continue;
			}
			try {
				IThingPeer<?> tp = getThingPeer(t);
				long time = System.nanoTime();
				boolean isInThing = tp.isInThing(location);
				if (PROFILE) {
					time = System.nanoTime() - time;
					profileStats.get(tp.getClass()).addAndGet(time);
				}
				if (isInThing) {
					things.add(t);
				}
			}
			catch (Throwable th) {
			}
		}
		return things;
	}

	@Override
	@SuppressWarnings("unchecked")
	synchronized public <T extends IThing> IThingPeer<T> getThingPeer(T thing) {
		Entry<IThingPeer<?>> peerEntry = peers.createEntry(thing.getUID());
		if (peerEntry.getValue() == null) {
			try {
				peerEntry.setValue((IThingPeer<?>) constructorsCache.getUnchecked(thing.getPeerClass()).newInstance(
						thing, this, cm));
			}
			catch (Throwable t) {
				throw new RuntimeException(t);
			}
		}
		return (IThingPeer<T>) peerEntry.getValue();
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == EventType.THING_REMOVED) {
			IThingPeer<?> peer = peers.remove(evt.getTargetThing().getUID());
			if (peer != null) {
				peer.dispose();
			}
		}
	}
}
