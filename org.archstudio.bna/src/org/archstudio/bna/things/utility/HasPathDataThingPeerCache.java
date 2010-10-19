package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;

import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasPathData;
import org.archstudio.bna.facets.IMirrorsPathData;

public class HasPathDataThingPeerCache implements IThingListener {

	protected boolean dirty = true;

	IHasPathData lt;

	public Path wPath = null;

	public Rectangle wBounds = null;

	public Path lPath = null;

	public Rectangle lBounds = null;

	public HasPathDataThingPeerCache(IHasPathData t) {
		this.lt = t;
		this.lt.addThingListener(this);
	}

	public synchronized void thingChanged(ThingEvent thingEvent) {
		if (IHasPathData.PATH_DATA_PROPERTY_NAME.equals(thingEvent.getPropertyName()) //
		        || IMirrorsPathData.PATH_DATA_MIRROR_OFFSETS_PROPETY_NAME.equals(thingEvent.getPropertyName())) {
			dirty = true;
		}
	}

	public synchronized void dispose() {
		if (wPath != null)
			wPath.dispose();
		wPath = null;

		if (lPath != null)
			lPath.dispose();
		lPath = null;
	}

	public synchronized void update(IBNAView view, Display display) {
		if (dirty) {
			//System.err.println("Was dirty: " + lt.getID());
			dispose();

			Point offset = null;
			if (lt instanceof IMirrorsPathData) {
				offset = ((IMirrorsPathData) lt).getPathDataMirrorOffsets();
			}

			wPath = new Path(display);
			BNAUtils.fillPath(wPath, lt.getPathData(), offset, null);
			wBounds = BNAUtils.getBounds(wPath);

			lPath = new Path(display);
			BNAUtils.fillPath(lPath, lt.getPathData(), offset, view.getCoordinateMapper());
			lBounds = BNAUtils.getBounds(lPath);

			dirty = false;
		}
	}
}