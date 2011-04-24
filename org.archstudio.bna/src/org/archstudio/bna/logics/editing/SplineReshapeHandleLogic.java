package org.archstudio.bna.logics.editing;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Vector;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.assemblies.ReshapeHandleAssembly;
import org.archstudio.bna.assemblies.SplineReshapeHandlesAssembly;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.ISplineReshapable;
import org.archstudio.bna.logics.tracking.EndpointsChangedEvent;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.IEndpointsTrackingListener;
import org.archstudio.bna.logics.tracking.IMidpointsTrackingListener;
import org.archstudio.bna.logics.tracking.ISelectionTrackingListener;
import org.archstudio.bna.logics.tracking.MidpointsChangedEvent;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.SelectionChangedEvent;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;

public class SplineReshapeHandleLogic extends AbstractThingLogic implements ISelectionTrackingListener, IEndpointsTrackingListener, IMidpointsTrackingListener,
        IDragMoveListener, IBNASynchronousModelListener {
	protected SelectionTrackingLogic stl = null;

	protected static final Point[] EMPTY_POINT_ARRAY = new Point[0];

	protected EndpointsTrackingLogic epstl = null;
	protected MidpointsTrackingLogic mpstl = null;

	protected DragMovableLogic dml = null;

	protected ISplineReshapable targetThing = null;

	protected SplineReshapeHandlesAssembly reshapeHandles = null;

	public SplineReshapeHandleLogic(SelectionTrackingLogic stl, EndpointsTrackingLogic epstl, MidpointsTrackingLogic mpstl, DragMovableLogic dml) {
		this.stl = stl;
		this.epstl = epstl;
		this.mpstl = mpstl;
		this.dml = dml;
	}

	public void init() {
		stl.addSelectionTrackingListener(this);
		epstl.addTrackingListener(this);
		mpstl.addTrackingListener(this);
		dml.addDragMoveListener(this);
		checkHandles();
	}

	public void destroy() {
		removeHandles();
		dml.removeDragMoveListener(this);
		mpstl.removeTrackingListener(this);
		epstl.removeTrackingListener(this);
		stl.removeSelectionTrackingListener(this);
	}

	public ISplineReshapable getTargetThing() {
		return targetThing;
	}

	public SplineReshapeHandlesAssembly getReshapeHandles() {
		return reshapeHandles;
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

	protected synchronized int getNumPoints(ISplineReshapable t) {
		int i = 2;
		Point[] midpoints = t.getMidpoints();
		if (midpoints != null) {
			i += midpoints.length;
		}
		return i;
	}

	protected synchronized void updateHandlePositions() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			m.beginBulkChange();
			if ((reshapeHandles != null) && (targetThing != null)) {
				Point ep1 = targetThing.getEndpoint1();
				Point ep2 = targetThing.getEndpoint2();
				Point[] midpoints = targetThing.getMidpoints();
				if (midpoints == null)
					midpoints = EMPTY_POINT_ARRAY;

				ReshapeHandleAssembly[] rhs = reshapeHandles.getAllReshapeHandleAssemblies();
				rhs[0].getReshapeHandleGlassThing().setAnchorPoint(new Point(ep1.x, ep1.y));
				for (int i = 1; i < (rhs.length - 1); i++) {
					rhs[i].getReshapeHandleGlassThing().setAnchorPoint(new Point(midpoints[i - 1].x, midpoints[i - 1].y));
				}
				rhs[rhs.length - 1].getReshapeHandleGlassThing().setAnchorPoint(new Point(ep2.x, ep2.y));
			}
			m.endBulkChange();
		}
	}

	protected synchronized void addHandles(ISplineReshapable t) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			targetThing = t;
			Point[] midpoints = t.getMidpoints();
			if (midpoints == null)
				midpoints = EMPTY_POINT_ARRAY;
			reshapeHandles = SplineReshapeHandlesAssembly.create(m, null, 2 + midpoints.length);
			int i = 0;
			for (ReshapeHandleAssembly rh : reshapeHandles.getAllReshapeHandleAssemblies()) {
				rh.getReshapeHandleThing().setColor(new RGB(0, 0, 255));
				rh.getReshapeHandleGlassThing().setTargetThingID(t.getID());
				rh.getReshapeHandleGlassThing().setProperty("#splinepoint", i++);
			}
			updateHandlePositions();
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
			if (selectedThing instanceof ISplineReshapable) {
				if (((ISplineReshapable) selectedThing).isUserEditable()) {
					addHandles((ISplineReshapable) selectedThing);
				}
			}
		}
	}

	public synchronized void selectionChanged(SelectionChangedEvent evt) {
		checkHandles();
	}

	public synchronized void endpointsChanged(EndpointsChangedEvent evt) {
		if ((targetThing != null) && (targetThing.equals(evt.getTargetThing()))) {
			updateHandlePositions();
		}
	}

	public void midpointsChanged(MidpointsChangedEvent evt) {
		Point[] oldmps = evt.getOldMidpoints();
		int oldmpscount = oldmps == null ? 0 : oldmps.length;
		Point[] newmps = evt.getNewMidpoints();
		int newmpscount = newmps == null ? 0 : newmps.length;

		if (oldmpscount != newmpscount) {
			removeHandles();
			if ((evt.getTargetThing() != null) && ((evt.getTargetThing() instanceof ISplineReshapable))) {
				checkHandles();
				//addHandles((ISplineReshapable)evt.getTargetThing());
			}
		}
		else {
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
					int pointNum = rht.getProperty("#splinepoint");
					if (pointNum == 0) {
						Point p = rht.getAnchorPoint();
						targetThing.setEndpoint1(p);
						fireEndpointMovedEvent(targetThing, 1, p.x, p.y);
					}
					else {
						Point[] midpoints = targetThing.getMidpoints();
						if ((pointNum - 1) < midpoints.length) {
							Point p = rht.getAnchorPoint();
							if (p != null) {
								midpoints[pointNum - 1] = p;
								targetThing.setMidpoints(midpoints);
							}
						}
						else {
							Point p = rht.getAnchorPoint();
							if (p != null) {
								targetThing.setEndpoint2(p);
								fireEndpointMovedEvent(targetThing, 2, p.x, p.y);
							}
						}
					}
				}
			}
		}
	}

	protected static final int MERGE_DIST = 5;

	public void dragFinished(DragMoveEvent evt) {
		if (targetThing != null) {
			IThing movedThing = evt.getMovedThing();
			if (movedThing instanceof ReshapeHandleGlassThing) {
				ReshapeHandleGlassThing rht = (ReshapeHandleGlassThing) movedThing;
				String rhtTargetThingID = rht.getTargetThingID();
				if ((rhtTargetThingID != null) && (rhtTargetThingID.equals(targetThing.getID()))) {
					Point[] midpoints = targetThing.getMidpoints();

					//We don't merge endpoints with one another, just midpoints
					//(or endpoints with midpoints)
					if ((midpoints == null) || (midpoints.length == 0))
						return;
					int pointNum = rht.getProperty("#splinepoint");
					if (pointNum == 0) {
						Point ep1 = rht.getAnchorPoint();
						//Get the first midpoint (we know there is one)
						Point mp1 = reshapeHandles.getAllReshapeHandleAssemblies()[1].getReshapeHandleGlassThing().getAnchorPoint();
						if (BNAUtils.round(Point2D.distance(ep1.x, ep1.y, mp1.x, mp1.y)) <= MERGE_DIST) {
							//Delete mp1
							Point[] newmidpoints = new Point[midpoints.length - 1];
							System.arraycopy(midpoints, 1, newmidpoints, 0, newmidpoints.length);
							targetThing.setMidpoints(newmidpoints);
						}
					}
					else {
						if ((pointNum - 1) < midpoints.length) {
							//It's a midpoint
							Point mp = rht.getAnchorPoint();

							Point p1 = reshapeHandles.getAllReshapeHandleAssemblies()[pointNum - 1].getReshapeHandleGlassThing().getAnchorPoint();
							Point p2 = reshapeHandles.getAllReshapeHandleAssemblies()[pointNum + 1].getReshapeHandleGlassThing().getAnchorPoint();

							int dist1 = BNAUtils.round(Point2D.distance(p1.x, p1.y, mp.x, mp.y));
							int dist2 = BNAUtils.round(Point2D.distance(p2.x, p2.y, mp.x, mp.y));

							if ((dist1 <= MERGE_DIST) || (dist2 <= MERGE_DIST)) {
								//It's close enough to an endpoint we should remove it.
								Point[] newmidpoints = new Point[midpoints.length - 1];
								int k = 0;
								for (int i = 0; i < midpoints.length; i++) {
									if (i != (pointNum - 1)) {
										newmidpoints[k++] = midpoints[i];
									}
								}
								targetThing.setMidpoints(newmidpoints);
							}
						}
						else {
							//It's endpoint 2.
							Point ep2 = rht.getAnchorPoint();
							Point mp2 = reshapeHandles.getAllReshapeHandleAssemblies()[pointNum - 1].getReshapeHandleGlassThing().getAnchorPoint();

							if (BNAUtils.round(Point2D.distance(ep2.x, ep2.y, mp2.x, mp2.y)) <= MERGE_DIST) {
								//Delete mp1
								Point[] newmidpoints = new Point[midpoints.length - 1];
								System.arraycopy(midpoints, 0, newmidpoints, 0, newmidpoints.length);
								targetThing.setMidpoints(newmidpoints);
							}
						}
					}
				}
			}
		}
	}

	protected Vector<IEndpointMovedListener> endpointMovedListeners = new Vector<IEndpointMovedListener>();

	public void addEndpointMovedListener(IEndpointMovedListener l) {
		endpointMovedListeners.add(l);
	}

	public void removeEndpointMovedListener(IEndpointMovedListener l) {
		endpointMovedListeners.remove(l);
	}

	protected void fireEndpointMovedEvent(ISplineReshapable targetThing, int endpointNum, int worldX, int worldY) {
		EndpointMovedEvent evt = new EndpointMovedEvent(this, targetThing, endpointNum, worldX, worldY);
		for (IEndpointMovedListener l : Collections.unmodifiableCollection(endpointMovedListeners)) {
			l.endpointMoved(evt);
		}
	}
}
