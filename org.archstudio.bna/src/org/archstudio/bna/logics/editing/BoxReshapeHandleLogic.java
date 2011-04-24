package org.archstudio.bna.logics.editing;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.assemblies.BoxReshapeHandlesAssembly;
import org.archstudio.bna.assemblies.ReshapeHandleAssembly;
import org.archstudio.bna.facets.IBoxReshapable;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.tracking.BoundingBoxChangedEvent;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.IBoundingBoxTrackingListener;
import org.archstudio.bna.logics.tracking.ISelectionTrackingListener;
import org.archstudio.bna.logics.tracking.SelectionChangedEvent;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.swtutils.constants.Orientation;

public class BoxReshapeHandleLogic extends AbstractThingLogic implements ISelectionTrackingListener, IBoundingBoxTrackingListener, IDragMoveListener,
        IBNASynchronousModelListener {
	protected SelectionTrackingLogic stl = null;

	protected BoundingBoxTrackingLogic bbtl = null;

	protected DragMovableLogic dml = null;

	protected IBoxReshapable targetThing = null;

	protected BoxReshapeHandlesAssembly reshapeHandles = null;

	public BoxReshapeHandleLogic(SelectionTrackingLogic stl, BoundingBoxTrackingLogic bbtl, DragMovableLogic dml) {
		this.stl = stl;
		this.bbtl = bbtl;
		this.dml = dml;
	}

	public void init() {
		stl.addSelectionTrackingListener(this);
		bbtl.addTrackingListener(this);
		dml.addDragMoveListener(this);
		checkHandles();
	}

	public void destroy() {
		removeHandles();
		dml.removeDragMoveListener(this);
		bbtl.removeTrackingListener(this);
		stl.removeSelectionTrackingListener(this);
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if (targetThing != null) {
			if (evt.getEventType() == EventType.THING_REMOVING) {
				if (targetThing.equals(evt.getTargetThing())) {
					removeHandles();
				}
			}
		}
	}

	protected synchronized void removeHandles() {
		if (reshapeHandles == null)
			return;

		IBNAModel m = getBNAModel();
		if (m != null) {
			IThing handleAssemblyRoot = reshapeHandles.getRootThing();
			m.removeThingAndChildren(handleAssemblyRoot);
			targetThing = null;
			reshapeHandles = null;
		}
	}

	protected synchronized void updateHandlePositions() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			m.beginBulkChange();
			if ((reshapeHandles != null) && (targetThing != null)) {
				Rectangle bb = targetThing.getBoundingBox();
				if (bb != null) {
					ReshapeHandleAssembly rh;
					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.NORTHWEST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x, bb.y));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.NORTH);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x + (bb.width / 2), bb.y));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.NORTHEAST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x + bb.width, bb.y));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.EAST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x + bb.width, bb.y + (bb.height / 2)));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.SOUTHEAST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x + bb.width, bb.y + bb.height));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.SOUTH);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x + (bb.width / 2), bb.y + bb.height));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.SOUTHWEST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x, bb.y + bb.height));

					rh = reshapeHandles.getReshapeHandleAssembly(Orientation.WEST);
					rh.getReshapeHandleGlassThing().setAnchorPoint(new Point(bb.x, bb.y + (bb.height / 2)));
				}
			}
			m.endBulkChange();
		}
	}

	protected synchronized void addHandles(IBoxReshapable t) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			Rectangle bb = t.getBoundingBox();
			if (bb != null) {
				targetThing = t;
				reshapeHandles = BoxReshapeHandlesAssembly.create(m, null);
				for (ReshapeHandleAssembly rh : reshapeHandles.getAllReshapeHandleAssemblies()) {
					rh.getReshapeHandleThing().setColor(new RGB(0, 0, 255));
					rh.getReshapeHandleGlassThing().setTargetThingID(targetThing.getID());
				}
				updateHandlePositions();
			}
		}
	}

	protected synchronized void checkHandles() {
		IHasSelected[] selectedThings = stl.getSelectedThings();
		// Show reshape handles whenever one thing is selected.
		if (selectedThings.length != 1) {
			removeHandles();
			return;
		}
		else {
			IHasSelected selectedThing = selectedThings[0];
			if (selectedThing.equals(targetThing)) {
				// It's fine, do nothing.
				return;
			}
			if ((targetThing != null) && (!targetThing.equals(selectedThing))) {
				// The selection has changed to a different thing
				removeHandles();
			}
			if (selectedThing instanceof IBoxReshapable) {
				if (((IBoxReshapable) selectedThing).isUserEditable()) {
					addHandles((IBoxReshapable) selectedThing);
				}
			}
		}
	}

	public synchronized void selectionChanged(SelectionChangedEvent evt) {
		checkHandles();
	}

	public synchronized void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		if ((targetThing != null) && (targetThing.equals(evt.getTargetThing()))) {
			updateHandlePositions();
		}
	}

	public void dragMoved(DragMoveEvent evt) {
		if (targetThing != null) {
			IThing movedThing = evt.getMovedThing();
			if (movedThing instanceof ReshapeHandleGlassThing) {
				ReshapeHandleGlassThing rht = (ReshapeHandleGlassThing) movedThing;
				String rhtTargetThingID = rht.getTargetThingID();
				if ((rhtTargetThingID != null) && (rhtTargetThingID.equals(targetThing.getID()))) {
					Orientation o = rht.getOrientation();
					Rectangle bb = targetThing.getBoundingBox();

					int x1 = bb.x;
					int y1 = bb.y;
					int x2 = bb.x + bb.width;
					int y2 = bb.y + bb.height;

					int nx1 = x1;
					int ny1 = y1;
					int nx2 = x2;
					int ny2 = y2;

					Point ap = rht.getAnchorPoint();

					switch (o) {
					case NORTHWEST:
						nx1 = ap.x;
						ny1 = ap.y;
						break;
					case NORTH:
						ny1 = ap.y;
						break;
					case NORTHEAST:
						nx2 = ap.x;
						ny1 = ap.y;
						break;
					case EAST:
						nx2 = ap.x;
						break;
					case SOUTHEAST:
						nx2 = ap.x;
						ny2 = ap.y;
						break;
					case SOUTH:
						ny2 = ap.y;
						break;
					case SOUTHWEST:
						nx1 = ap.x;
						ny2 = ap.y;
						break;
					case WEST:
						nx1 = ap.x;
						break;
					}
					bb.x = nx1;
					bb.y = ny1;
					bb.width = nx2 - nx1;
					bb.height = ny2 - ny1;
					targetThing.setBoundingBox(bb);

					//This code is here to keep the NSEW anchor
					//points from drifting along the sides of the box,
					//effectively counteracting the undesired part of the
					//drag motion
					switch (o) {
					case NORTH:
					case SOUTH:
						ap.x = bb.x + (bb.width / 2);
						break;
					case EAST:
					case WEST:
						ap.y = bb.y + (bb.height / 2);
					}
					rht.setAnchorPoint(ap);
				}
			}
		}
	}

	public void dragFinished(DragMoveEvent evt) {
	}
}
