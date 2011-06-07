package org.archstudio.bna.logics.editing;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic.StickyMode;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ISelectionTrackingListener;
import org.archstudio.bna.logics.tracking.SelectionChangedEvent;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.utils.AssemblyUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.SplineUtils;
import org.archstudio.bna.utils.SplineUtils.SplineData;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.RGB;

public class SplineReshapeHandleLogic extends AbstractThingLogic implements ISelectionTrackingListener,
		IBNAModelListener, IDragMoveListener, IStickyModeSpecifier {

	private static final boolean DEBUG = false;

	private static final RGB UNSTUCK_COLOR = new RGB(255, 0, 0);
	private static final RGB STUCK_COLOR = new RGB(0, 255, 0);
	private static final RGB NORMAL_COLOR = new RGB(0, 0, 255);

	private static final int MERGE_DIST = 5;
	private static final int STICK_DIST = 8;

	private static final boolean equalz(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	protected SelectionTrackingLogic stl = null;

	protected IThing targetThing = null;
	protected boolean editingEndpoint1 = false;
	protected boolean editingEndpoint2 = false;
	protected boolean editingMidpoints = false;
	protected boolean restickingEndpoint1 = false;
	protected boolean restickingEndpoint2 = false;

	protected SplineReshapeHandlesAssembly reshapeHandles = null;

	public SplineReshapeHandleLogic(SelectionTrackingLogic stl, DragMoveEventsLogic dml) {
		this.stl = stl;
		// dml: this logic listens to dml events, this ensures that the designer remembers to include it
	}

	@Override
	protected void init() {
		stl.addSelectionTrackingListener(this);
		checkHandles();
	}

	@Override
	protected void destroy() {
		removeHandles();
		stl.removeSelectionTrackingListener(this);
	}

	public IThing getTargetThing() {
		return targetThing;
	}

	public SplineReshapeHandlesAssembly getReshapeHandles() {
		return reshapeHandles;
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (targetThing != null) {
			if (evt.getEventType() == EventType.THING_REMOVING) {
				if (targetThing.equals(evt.getTargetThing())) {
					removeHandles();
				}
			}
			if (evt.getEventType() == EventType.THING_CHANGED) {
				if (equalz(targetThing, evt.getTargetThing())) {
					updateHandles();
				}
			}
		}
	}

	protected synchronized void removeHandles() {
		if (reshapeHandles != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				reshapeHandles.remove(true);
				reshapeHandles = null;
			}
		}
	}

	protected void updateHandle(ReshapeHandleAssembly rhs, IThing targetThing, String pointPropertyName) {
		Point point = targetThing.get(pointPropertyName);
		RGB color = UNSTUCK_COLOR;

		String stickyThingId = StickRelativeMovablesLogic.getStuckToThingId(pointPropertyName, targetThing);
		if (stickyThingId != null) {
			StickyMode stickyMode = StickRelativeMovablesLogic.getStickyMode(pointPropertyName, targetThing);
			if (stickyMode != null) {
				IThing stickyThing = getBNAModel().getThing(stickyThingId);
				if (stickyThing != null) {
					color = STUCK_COLOR;
					point = StickRelativeMovablesLogic.getStuckReferencePoint(stickyThing, stickyMode, point);
				}
			}
		}
		rhs.getReshapeHandleThing().setColor(color);
		rhs.getReshapeHandleGlassThing().setAnchorPoint(point);
	}

	protected synchronized void updateHandles() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			if (targetThing != null) {

				SplineData sd = SplineUtils.getPoints(targetThing);
				editingMidpoints = targetThing instanceof IHasMutableMidpoints
						&& UserEditableUtils.isEditableForAnyQualities(targetThing,
								IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
								IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS,
								IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS);
				editingEndpoint1 = sd.includesEndpoint1
						&& targetThing instanceof IHasMutableEndpoints
						&& UserEditableUtils.isEditableForAnyQualities(targetThing,
								IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1);
				editingEndpoint2 = sd.includesEndpoint2
						&& targetThing instanceof IHasMutableEndpoints
						&& UserEditableUtils.isEditableForAnyQualities(targetThing,
								IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2);
				restickingEndpoint1 = editingEndpoint1
						&& UserEditableUtils.isEditableForAnyQualities(targetThing,
								IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT1);
				restickingEndpoint2 = editingEndpoint2
						&& UserEditableUtils.isEditableForAnyQualities(targetThing,
								IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT2);

				List<Point> points = new ArrayList<Point>();
				if (editingEndpoint1 && sd.includesEndpoint1) {
					points.add(sd.allPoints.get(0));
				}
				if (editingMidpoints) {
					points.addAll(sd.getMidpoints());
				}
				if (editingEndpoint2 && sd.includesEndpoint2) {
					points.add(sd.allPoints.get(sd.allPoints.size() - 1));
				}

				m.beginBulkChange();
				try {
					if (reshapeHandles == null
							|| reshapeHandles.getAllReshapeHandleAssemblies().length != points.size()) {
						removeHandles();
						reshapeHandles = new SplineReshapeHandlesAssembly(m, null, null, points.size());
					}
					ReshapeHandleAssembly[] rhs = reshapeHandles.getAllReshapeHandleAssemblies();
					for (int i = 0; i < rhs.length; i++) {
						rhs[i].getReshapeHandleGlassThing().setProperty("#splinepoint", i);
						rhs[i].getReshapeHandleGlassThing().setTargetThingID(targetThing.getID());
						if (i == 0 && editingEndpoint1) {
							updateHandle(rhs[i], targetThing, IHasEndpoints.ENDPOINT_1_KEY);
						}
						else if (i == rhs.length - 1 && editingEndpoint2) {
							updateHandle(rhs[i], targetThing, IHasEndpoints.ENDPOINT_2_KEY);
						}
						else {
							rhs[i].getReshapeHandleGlassThing().setAnchorPoint(points.get(i));
							rhs[i].getReshapeHandleThing().setColor(NORMAL_COLOR);
						}
					}
				}
				finally {
					m.endBulkChange();
				}
			}
		}
	}

	protected synchronized void checkHandles() {
		IHasSelected[] selectedThings = stl.getSelectedThings();
		if (selectedThings.length != 1 || !equalz(targetThing, selectedThings[0])) {
			// The selection has changed to a different thing
			if (targetThing != null) {
				removeHandles();
				targetThing = null;
			}
		}
		if (selectedThings.length == 1 && !equalz(targetThing, selectedThings[0])) {
			// Show reshape handles whenever one thing is selected.
			IThing selectedThing = selectedThings[0];
			if (selectedThing instanceof IHasMutableMidpoints
					&& UserEditableUtils.isEditableForAnyQualities(selectedThing,
							IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
							IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS,
							IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS)
					|| selectedThing instanceof IHasMutableEndpoints
					&& UserEditableUtils.isEditableForAnyQualities(selectedThing,
							IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2)) {
				targetThing = selectedThing;
				updateHandles();
			}
		}
	}

	ListenerList<IStickyModeSpecifier> stickyModeSpecifiers = new ListenerList<IStickyModeSpecifier>(
			IStickyModeSpecifier.class);

	public boolean addStickyModeSpecifier(IStickyModeSpecifier stickyModeSpecifier) {
		return stickyModeSpecifiers.add(stickyModeSpecifier);
	}

	public boolean removeStickyModeSpecifier(Object stickyModeSpecifier) {
		return stickyModeSpecifiers.remove(stickyModeSpecifier);
	}

	public StickyMode[] getAllowableStickyModes(IThing thing, String propertyName, IThing stickToThing) {
		if (!thing.equals(stickToThing) && UserEditableUtils.isEditable(stickToThing)) {
			if (stickyModeSpecifiers.size() == 0) {
				if ("glass".equals(AssemblyUtils.getPartName(stickToThing))
						&& !(stickToThing instanceof ReshapeHandleGlassThing)
						&& !(stickToThing instanceof IHasEndpoints)) {
					return new StickyMode[] { StickyMode.EDGE_FROM_CENTER, StickyMode.EDGE };
				}
			}
			else {
				Set<StickyMode> stickyModes = new HashSet<StickyMode>();
				for (IStickyModeSpecifier stickyModeSpecifier : stickyModeSpecifiers) {
					StickyMode[] sm = stickyModeSpecifier.getAllowableStickyModes(thing, propertyName, stickToThing);
					if (sm != null) {
						stickyModes.addAll(Arrays.asList(sm));
					}
				}
				return stickyModes.toArray(new StickyMode[stickyModes.size()]);
			}
		}
		return new StickyMode[0];
	}

	private void stickEndpointNear(IBNAView view, IBNAModel model, IThing targetThing, String propertyName,
			Point checkForStickyPoint, Point point, boolean restick) {
		Lock lock = targetThing.getPropertyLock();
		lock.lock();
		try {
			if (restick) {
				IThing nst = null;
				StickyMode nsm = null;
				float nsmd = Float.POSITIVE_INFINITY;

				for (IThing st : model.getAllThings()) {
					StickyMode[] stickyModes = getAllowableStickyModes(targetThing, propertyName, st);
					if (stickyModes.length > 0) {
						boolean sti = isInThing(view, model, st, checkForStickyPoint);
						float std = sti ? 0 : StickRelativeMovablesLogic.getStickDistance(st, StickyMode.EDGE,
								checkForStickyPoint);
						if (sti || std < STICK_DIST) {
							nst = st;
							nsm = null;
							nsmd = Float.POSITIVE_INFINITY;
							for (StickyMode sm : stickyModes) {
								float smd = StickRelativeMovablesLogic.getStickDistance(st, sm, checkForStickyPoint);
								if (nsm == null || smd < nsmd) {
									nst = st;
									nsm = sm;
									nsmd = smd;
								}
							}
						}
					}
				}

				if (DEBUG) {
					System.out.println(nsm + " " + nsmd + " " + nst);
				}

				StickRelativeMovablesLogic.stickPoint(nst != null ? nst.getID() : null, propertyName, nsm, targetThing);
			}
			if (point != null) {
				targetThing.setProperty(propertyName, point);
			}
		}
		finally {
			lock.unlock();
		}
	}

	private boolean isInThing(IBNAView view, IBNAModel model, IThing thing, Point p) {
		if (thing != null) {
			IThingPeer peer = view.getPeerCache(thing);
			if (peer != null) {
				return peer.isInThing(view, p.x, p.y);
			}
		}
		return false;
	}

	public synchronized void selectionChanged(SelectionChangedEvent evt) {
		checkHandles();
	}

	public void dragStarted(DragMoveEvent evt) {
	}

	public void dragMoved(DragMoveEvent evt) {
		if (targetThing != null) {
			IThing movedThing = evt.getInitialThing();
			if (movedThing instanceof ReshapeHandleGlassThing) {
				ReshapeHandleGlassThing rht = (ReshapeHandleGlassThing) movedThing;
				String rhtTargetThingID = rht.getTargetThingID();
				if (rhtTargetThingID != null && rhtTargetThingID.equals(targetThing.getID())) {
					int pointNum = rht.get("#splinepoint");
					if (pointNum == 0 && editingEndpoint1) {
						stickEndpointNear(evt.getView(), getBNAModel(), targetThing, IHasEndpoints.ENDPOINT_1_KEY,
								evt.getWorldPoint(), evt.getAdjustedWorldPoint(), restickingEndpoint1);
					}
					else if (pointNum == reshapeHandles.getAllReshapeHandleAssemblies().length - 1 && editingEndpoint2) {
						stickEndpointNear(evt.getView(), getBNAModel(), targetThing, IHasEndpoints.ENDPOINT_2_KEY,
								evt.getWorldPoint(), evt.getAdjustedWorldPoint(), restickingEndpoint2);
					}
					else if (editingMidpoints) {
						if (editingEndpoint1) {
							pointNum--;
						}
						Point[] midpoints = BNAUtils.clone(((IHasMidpoints) targetThing).getMidpoints());
						if (pointNum >= 0 && pointNum < midpoints.length) {
							midpoints[pointNum] = evt.getAdjustedWorldPoint();
							((IHasMutableMidpoints) targetThing).setMidpoints(midpoints);
						}
					}

					// This code is here to keep the anchor
					// points on the spline
					updateHandles();
				}
			}
		}
	}

	public void dragFinished(DragMoveEvent evt) {
		if (targetThing != null) {
			IThing movedThing = evt.getInitialThing();
			if (movedThing instanceof ReshapeHandleGlassThing) {
				ReshapeHandleGlassThing rht = (ReshapeHandleGlassThing) movedThing;
				String rhtTargetThingID = rht.getTargetThingID();
				if (rhtTargetThingID != null && rhtTargetThingID.equals(targetThing.getID())) {

					ReshapeHandleAssembly[] rhas = reshapeHandles.getAllReshapeHandleAssemblies();
					int pointNum = rht.get("#splinepoint");

					// We only want to remove a midpoint if it is close to its neighbor
					if (pointNum == 0 && editingEndpoint1) {
						// don't check the endpoint, check the next point instead
						pointNum++;
					}
					else if (pointNum == rhas.length - 1 && editingEndpoint2) {
						// don't check the endpoint, check the previous point instead
						pointNum--;
					}
					if (pointNum == 0 && editingEndpoint1 || pointNum == rhas.length - 1 && editingEndpoint2) {
						// we're still trying to check an endpoint, there must not have been any midpoints to check
						return;
					}

					// get the point and neighboring point
					Point p0 = pointNum - 1 >= 0 ? rhas[pointNum - 1].getReshapeHandleGlassThing().getAnchorPoint()
							: null;
					Point p1 = rhas[pointNum].getReshapeHandleGlassThing().getAnchorPoint();
					Point p2 = pointNum + 1 < rhas.length ? rhas[pointNum + 1].getReshapeHandleGlassThing()
							.getAnchorPoint() : null;

					// check that the distances
					boolean remove = false;
					remove |= p0 != null && BNAUtils.round(Point2D.distance(p0.x, p0.y, p1.x, p1.y)) <= MERGE_DIST;
					remove |= p2 != null && BNAUtils.round(Point2D.distance(p2.x, p2.y, p1.x, p1.y)) <= MERGE_DIST;

					if (remove) {
						SplineData sd = SplineUtils.getPoints(targetThing);
						sd.getMidpoints().remove(pointNum - (editingEndpoint1 ? 1 : 0));
						SplineUtils.setPoints(targetThing, sd);
					}
				}
			}
		}
	}
}
