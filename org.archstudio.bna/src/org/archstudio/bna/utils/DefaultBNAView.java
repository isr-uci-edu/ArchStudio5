package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.nullEquals;

import java.util.List;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IIsBackground;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Lists;

public class DefaultBNAView implements IBNAView {

	protected final IBNAView parentView;
	protected final IBNAWorld bnaWorld;
	protected final ICoordinateMapper cm;
	protected final PeerCache peerCache;
	protected Composite composite = null;

	public DefaultBNAView(@Nullable IBNAView parentView, IBNAWorld bnaWorld, ICoordinateMapper cm) {
		super();
		this.composite = parentView != null ? parentView.getComposite() : null;
		this.parentView = parentView;
		this.peerCache = new PeerCache();
		this.bnaWorld = checkNotNull(bnaWorld);
		this.cm = checkNotNull(cm);
	}

	@Override
	public Composite getComposite() {
		return composite;
	}

	@Override
	public void setComposite(Composite composite) {
		if (!nullEquals(this.composite, composite)) {
			this.composite = composite;
			for (IThing t : getBNAWorld().getBNAModel().getAllThings()) {
				IThingPeer<?> tp = getThingPeer(t);
				if (tp instanceof IHasInnerViewPeer) {
					IBNAView innerView = ((IHasInnerViewPeer) tp).getInnerView();
					if (innerView != null) {
						innerView.setComposite(composite);
					}
				}
			}
		}
	}

	@Override
	public IBNAView getParentView() {
		return parentView;
	}

	@Override
	public IBNAWorld getBNAWorld() {
		return bnaWorld;
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
			if (Boolean.TRUE.equals(t.get(IIsBackground.BACKGROUND_KEY))) {
				continue;
			}
			try {
				if (peerCache.getPeer(t).isInThing(this, cm, location)) {
					things.add(t);
				}
			}
			catch (Throwable th) {
			}
		}
		return things;
	}

	@Override
	public <T extends IThing> IThingPeer<T> getThingPeer(T thing) {
		return peerCache.getPeer(thing);
	}

	@Override
	public <T extends IThing> void disposePeer(T thing) {
		peerCache.disposePeer(thing);
	}
	
	@Override
	public void dispose() {
		peerCache.dispose();
	}
}
