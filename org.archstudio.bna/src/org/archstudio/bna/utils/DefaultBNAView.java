package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
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
import org.archstudio.bna.facets.IHasBackground;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.sysutils.FastMap;
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

	protected final IBNAView parentView;
	protected final IBNAWorld world;
	protected final FastMap<IThing, IThingPeer<?>> peers = new FastMap<>(true);
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
	public void dispose() {
		BNAUtils.checkLock();

		world.getBNAModel().removeBNAModelListener(this);
		disposePeers();
		if (PROFILE) {
			for (java.util.Map.Entry<Object, AtomicLong> entry : SystemUtils.sortedByValue(profileStats.asMap()
					.entrySet())) {
				System.err.println(entry.getValue() + "\t" + entry.getKey());
			}
		}
	}

	@Override
	public void disposePeers() {
		BNAUtils.checkLock();

		for (Map.Entry<IThing, IThingPeer<?>> entry : peers.entrySet()) {
			try {
				entry.getValue().dispose();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		peers.clear();
	}

	@Override
	public IBNAView getParentView() {
		BNAUtils.checkLock();

		return parentView;
	}

	@Override
	public IBNAUI getBNAUI() {
		BNAUtils.checkLock();

		return bnaUI;
	}

	@Override
	public void setBNAUI(IBNAUI bnaUI) {
		BNAUtils.checkLock();

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
		BNAUtils.checkLock();

		return world;
	}

	@Override
	public ICoordinateMapper getCoordinateMapper() {
		BNAUtils.checkLock();

		return cm;
	}

	@Override
	public List<IThing> getThingsAt(ICoordinate location) {
		BNAUtils.checkLock();

		location = DefaultCoordinate.forWorld(location.getWorldPoint(), cm);
		List<IThing> things = Lists.newArrayList();
		for (IThing t : Lists.reverse(getBNAWorld().getBNAModel().getAllThings())) {
			if (t.has(IHasBackground.BACKGROUND_KEY, Boolean.TRUE)) {
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
	public <T extends IThing> IThingPeer<T> getThingPeer(T thing) {
		BNAUtils.checkLock();

		Map.Entry<IThing, IThingPeer<?>> peerEntry = peers.createEntry(thing);
		if (peerEntry.getValue() == null) {
			peerEntry.setValue(thing.createPeer(this, cm));
		}
		return (IThingPeer<T>) peerEntry.getValue();
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		if (evt.getEventType() == EventType.THING_REMOVED) {
			IThingPeer<?> peer = peers.remove(evt.getTargetThing());
			if (peer != null) {
				peer.dispose();
			}
		}
	}
}
