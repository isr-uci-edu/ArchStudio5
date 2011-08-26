package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.widgets.Control;

import com.google.common.collect.Lists;

public class DefaultBNAView implements IBNAView {

	@Nullable
	protected final IBNAView parentView;
	protected final IBNAWorld bnaWorld;
	protected final ICoordinateMapper cm;
	protected final PeerCache peerCache;
	protected final Control control;

	public DefaultBNAView(Control control, @Nullable IBNAView parentView, IBNAWorld bnaWorld, ICoordinateMapper cm) {
		super();
		this.control = control;
		this.parentView = parentView;
		this.peerCache = new PeerCache();
		this.bnaWorld = checkNotNull(bnaWorld);
		this.cm = checkNotNull(cm);
	}

	@Override
	public Control getControl() {
		return control;
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
		location = DefaultCoordinate.forWorld(location.getWorldPoint(new Point()), cm);
		List<IThing> things = Lists.newArrayList();
		for (IThing t : getBNAWorld().getBNAModel().getReverseThings()) {
			if (peerCache.getPeer(t).isInThing(this, cm, location)) {
				things.add(t);
			}
		}
		return things;
	}

	@Override
	public <T extends IThing> IThingPeer<T> getThingPeer(T thing) {
		return peerCache.getPeer(thing);
	}

	public <T extends IThing> void disposePeer(T thing) {
		peerCache.disposePeer(thing);
	}

	@Override
	public void setCursor(int swtCursor) {
		if (control != null) {
			control.getDisplay().getSystemCursor(swtCursor);
		}
	}
}
