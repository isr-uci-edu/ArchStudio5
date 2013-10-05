package org.archstudio.archipelago.core.structure;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.graphlayout.GraphLayout;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.graphlayout.GraphLayoutParameters;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.graphlayout.gui.GraphLayoutDialog;
import org.archstudio.resources.ArchStudioCommonResources;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.BSpline;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.IWorkbenchActionConstants;

public class StructureGraphLayoutLogic extends AbstractThingLogic implements IBNAMenuListener {
	protected final IXArchADT xarch;
	protected final IResources resources;
	protected final IGraphLayout graphLayout;
	protected final ObjRef documentRootRef;

	public StructureGraphLayoutLogic(IBNAWorld world, IXArchADT xarch, IResources resources, IGraphLayout graphLayout,
			ObjRef documentRootRef) {
		super(world);
		this.xarch = xarch;
		this.resources = resources;
		this.graphLayout = graphLayout;
		this.documentRootRef = documentRootRef;
	}

	@Override
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		if (!things.isEmpty()) {
			return;
		}

		final IBNAView fview = view;
		final Point fworld = location.getWorldPoint();
		final int fworldX = fworld.x;
		final int fworldY = fworld.y;

		EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
		final ObjRef structureRef = ept.get(IHasObjRef.OBJREF_KEY);
		if (structureRef != null
				&& XadlUtils.isInstanceOf(xarch, structureRef, Structure_3_0Package.Literals.STRUCTURE)) {
			IAction layoutAction = new Action("Automatic Layout...") {

				@Override
				public void run() {
					doLayout(fview, structureRef, fworldX, fworldY);
				}

				@Override
				public ImageDescriptor getImageDescriptor() {
					return resources.getImageDescriptor(ArchStudioCommonResources.ICON_STRUCTURE);
				}
			};
			menu.add(layoutAction);
			menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	protected void doLayoutInJob(final IBNAView view, final ObjRef structureRef, final int worldX, final int worldY,
			final String engineID, final GraphLayoutParameters glp) {
		Job job = new Job("Laying Out") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				doLayout(view, structureRef, worldX, worldY, engineID, glp);
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.setPriority(Job.SHORT);
		job.schedule();
		/*
		 * try{ job.join(); } catch(InterruptedException ie){}
		 */
	}

	protected void doLayout(IBNAView view, ObjRef structureRef, int worldX, int worldY) {
		GraphLayoutDialog gld = new GraphLayoutDialog(view.getComposite().getShell());
		GraphLayoutParameters glp = gld.open(graphLayout);
		if (glp == null) {
			return;
		}
		String engineID = (String) glp.getProperty("engineID");
		if (engineID == null) {
			return;
		}
		doLayoutInJob(view, structureRef, worldX, worldY, engineID, glp);
		view.getComposite().forceFocus();
	}

	protected void doLayout(final IBNAView view, final ObjRef structureRef, final int worldX, final int worldY,
			final String engineID, final GraphLayoutParameters glp) {
		SWTWidgetUtils.sync(view.getComposite(), new Runnable() {

			@Override
			public void run() {
				try {
					view.getBNAWorld().getBNAModel().beginBulkChange();
					GraphLayout gl = graphLayout.layoutGraph(engineID, structureRef, glp);
					applyGraphLayout(view, structureRef, gl, glp, worldX, worldY);
				}
				catch (final GraphLayoutException gle) {
					MessageDialog.openError(view.getComposite().getShell(), "Error", gle.getMessage());
				}
				finally {
					view.getBNAWorld().getBNAModel().endBulkChange();
				}
			}
		});
	}

	protected void applyGraphLayout(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp,
			int worldX, int worldY) {
		for (int i = 0; i < gl.getNumNodes(); i++) {
			GraphLayout.Node node = gl.getNodeAt(i);
			String nodeID = node.getNodeId();
			Rectangle nodeBounds = node.getBounds();
			nodeBounds.x += worldX;
			nodeBounds.y += worldY;

			IThing nodeRootThing = ArchipelagoUtils.findThing(view.getBNAWorld().getBNAModel(), nodeID);
			if (nodeRootThing instanceof IHasMutableBoundingBox) {
				((IHasMutableBoundingBox) nodeRootThing).setBoundingBox(nodeBounds);
			}
		}
		if (!getDontMoveInterfaces(glp)) {
			repositionInterfaces(view, structureRef, gl, glp, worldX, worldY);
		}
		if (!getDontRouteLinks(glp)) {
			routeLinks(view, structureRef, gl, glp, worldX, worldY);
		}
		else {
			unrouteLinks(view, structureRef, gl, glp, worldX, worldY);
		}
	}

	protected void repositionInterfaces(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp,
			int worldX, int worldY) {
		Map<String, List<Point>> interfaceIDtoPointListMap = new HashMap<String, List<Point>>();

		for (int i = 0; i < gl.getNumEdges(); i++) {
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String linkXArchID = edge.getEdgeId();

			ObjRef linkRef = xarch.getByID(documentRootRef, linkXArchID);
			if (linkRef != null) {
				ObjRef interface1Ref = (ObjRef) xarch.get(linkRef, "point1");
				if (interface1Ref != null) {
					String interface1ID = XadlUtils.getID(xarch, interface1Ref);
					if (interface1ID != null) {
						if (edge.getNumPoints() > 0) {
							Point interface1Point = edge.getPointAt(0);
							List<Point> pointList = interfaceIDtoPointListMap.get(interface1ID);
							if (pointList == null) {
								pointList = new ArrayList<Point>();
								interfaceIDtoPointListMap.put(interface1ID, pointList);
							}
							pointList.add(interface1Point);
						}
					}
				}

				ObjRef interface2Ref = (ObjRef) xarch.get(linkRef, "point2");
				if (interface2Ref != null) {
					String interface2ID = XadlUtils.getID(xarch, interface2Ref);
					if (interface2ID != null) {
						if (edge.getNumPoints() > 0) {
							Point interface2Point = edge.getPointAt(edge.getNumPoints() - 1);
							List<Point> pointList = interfaceIDtoPointListMap.get(interface2ID);
							if (pointList == null) {
								pointList = new ArrayList<Point>();
								interfaceIDtoPointListMap.put(interface2ID, pointList);
							}
							pointList.add(interface2Point);
						}
					}
				}
			}
		}

		for (String interfaceXArchID : interfaceIDtoPointListMap.keySet()) {
			IThing nodeRootThing = ArchipelagoUtils.findThing(view.getBNAWorld().getBNAModel(), interfaceXArchID);
			if (nodeRootThing instanceof EndpointGlassThing) {
				List<Point> pointList = interfaceIDtoPointListMap.get(interfaceXArchID);
				if (pointList.size() > 0) {
					//Average the points
					Point p = new Point(0, 0);
					for (Point p2 : pointList) {
						p.x += p2.x;
						p.y += p2.y;
					}
					p.x /= pointList.size();
					p.y /= pointList.size();

					p.x += worldX;
					p.y += worldY;

					((EndpointGlassThing) nodeRootThing).setAnchorPoint(p);
				}
			}
		}
	}

	protected void routeLinks(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp,
			int worldX, int worldY) {
		for (int i = 0; i < gl.getNumEdges(); i++) {
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String edgeID = edge.getEdgeId();

			Point[] controlPoints = null;
			if (edge.getNumPoints() > 3) {
				controlPoints = new Point[edge.getNumPoints() - 2];
				for (int j = 1; j < edge.getNumPoints() - 1; j++) {
					controlPoints[j - 1] = edge.getPointAt(j);
					controlPoints[j - 1].x += worldX;
					controlPoints[j - 1].y += worldY;
				}
			}

			//The points returned by dot are actually B-spline control points;
			//we can convert these into rectilinear waypoints to simulate a curve.
			Point[] midpoints = BSpline.bspline(controlPoints, 2);
			midpoints = optimizePoints(midpoints);

			IThing linkRootThing = ArchipelagoUtils.findThing(view.getBNAWorld().getBNAModel(), edgeID);
			if (linkRootThing instanceof SplineThing) {
				((SplineThing) linkRootThing).setMidpoints(Arrays.asList(midpoints));
			}
		}
	}

	protected static Point[] optimizePoints(Point[] points) {
		List<Point> optimizedPoints = new ArrayList<Point>();
		Point lastPoint = null;

		for (Point point : points) {
			if (lastPoint == null) {
				optimizedPoints.add(point);
				lastPoint = point;
			}
			else {
				double dist = Point2D.distance(lastPoint.x, lastPoint.y, point.x, point.y);
				if (dist < 12.5) {
					continue;
				}
				optimizedPoints.add(point);
				lastPoint = point;
			}
		}
		return optimizedPoints.toArray(new Point[optimizedPoints.size()]);
	}

	protected void unrouteLinks(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp,
			int worldX, int worldY) {
		for (int i = 0; i < gl.getNumEdges(); i++) {
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String edgeID = edge.getEdgeId();

			IThing linkRootThing = ArchipelagoUtils.findThing(view.getBNAWorld().getBNAModel(), edgeID);
			if (linkRootThing instanceof SplineThing) {
				((SplineThing) linkRootThing).setMidpoints(Collections.<Point> emptyList());
			}
		}
	}

	protected static boolean getDontMoveInterfaces(GraphLayoutParameters glp) {
		Object o = glp.getProperty("dontMoveInterfaces");
		if (o == null) {
			return false;
		}
		if (!(o instanceof Boolean)) {
			return false;
		}
		return ((Boolean) o).booleanValue();
	}

	protected static boolean getDontRouteLinks(GraphLayoutParameters glp) {
		Object o = glp.getProperty("dontRouteLinks");
		if (o == null) {
			return false;
		}
		if (!(o instanceof Boolean)) {
			return false;
		}
		return ((Boolean) o).booleanValue();
	}
}
