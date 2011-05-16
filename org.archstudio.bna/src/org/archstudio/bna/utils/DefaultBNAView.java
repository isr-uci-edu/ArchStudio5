package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.PeerCache.Cache;
import org.eclipse.swt.widgets.Control;

import com.google.common.collect.Lists;

public class DefaultBNAView implements IBNAView {

	@Nullable
	final protected IBNAView parentView;
	final protected IBNAWorld bnaWorld;
	final protected ICoordinateMapper cm;
	final protected PeerCache<> peerCache = new PeerCache();
	final protected Control control;

	public DefaultBNAView(@Nullable Control control, @Nullable IBNAView parentView, IBNAWorld bnaWorld,
			ICoordinateMapper cm) {
		super();
		this.control = control;
		this.parentView = parentView;
		this.bnaWorld = checkNotNull(bnaWorld);
		this.cm = checkNotNull(cm);
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
	public Iterable<IThing> getThingsAt(ICoordinate location) {
		List<IThing> things = Lists.newArrayList();
		for (IThing t : getBNAWorld().getBNAModel().getReverseThings()) {
			if (!Boolean.TRUE.equals(t.get(IIsBackground.BACKGROUND_KEY))) {
				if (peerCache.getPeerCache(t).peer.isInThing(this, cm, location)) {
					things.add(t);
				}
			}
		}
		return things;
	}

	@Override
	public <T extends IThing, D> Cache<T, D> getPeerCache(T thing) {
		return peerCache.getPeerCache(thing);
		@Override
		protected Cache<IThing, CacheData> getPeerCache(IThing t) {
			PeerCache.Cache<IThing, CacheData> pc = getPeerCache(t);
			if (pc.renderData == null) {
				pc.renderData = new CacheData();
			}
			return pc;
		}

	}

	@Override
	public void setCursor(int swtCursor) {
		if (control != null) {
			control.getDisplay().getSystemCursor(swtCursor);
		}
	}
}
