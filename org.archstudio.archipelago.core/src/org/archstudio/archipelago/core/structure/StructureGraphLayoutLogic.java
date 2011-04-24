package org.archstudio.archipelago.core.structure;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.graphlayout.common.GraphLayout;
import org.archstudio.graphlayout.common.GraphLayoutException;
import org.archstudio.graphlayout.common.GraphLayoutParameters;
import org.archstudio.graphlayout.common.gui.GraphLayoutDialog;
import org.archstudio.resources.common.ArchStudioCommonResources;
import org.archstudio.swtutils.BSpline;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.things.glass.BoxGlassThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.glass.StickySplineGlassThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;

public class StructureGraphLayoutLogic extends AbstractThingLogic implements IBNAMenuListener{
	protected ArchipelagoServices AS = null;
	protected ObjRef documentRootRef = null;
	
	public StructureGraphLayoutLogic(ArchipelagoServices services, ObjRef documentRootRef){
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}
	
	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY){
		if(t != null){
			return;
		}
		
		final IBNAView fview = view;
		final int fworldX = worldX;
		final int fworldY = worldY;
		
		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(view.getWorld().getBNAModel());
		String structureXArchID = ept.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		if(structureXArchID != null){
			final ObjRef structureRef = AS.xarch.getByID(documentRootRef, structureXArchID);
			if((structureRef != null) && (XadlUtils.isInstanceOf(AS.xarch, structureRef, Structure_3_0Package.Literals.STRUCTURE))){
				IAction layoutAction = new Action("Automatic Layout..."){
					public void run(){
						doLayout(fview, structureRef, fworldX, fworldY);
					}
					
					public ImageDescriptor getImageDescriptor(){
						return AS.resources.getImageDescriptor(ArchStudioCommonResources.ICON_STRUCTURE);
					}
				};
				m.add(layoutAction);
				m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		}
	}
	
	protected void doLayoutInJob(final IBNAView view, final ObjRef structureRef, final int worldX, final int worldY, final String engineID, final GraphLayoutParameters glp){
		Job job = new Job("Laying Out"){
			protected IStatus run(IProgressMonitor monitor){
				doLayout(view, structureRef, worldX, worldY, engineID, glp);
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.setPriority(Job.SHORT);
		job.schedule();
		/*
		try{
			job.join();
		}
		catch(InterruptedException ie){}
		*/
	}
	
	protected void doLayout(IBNAView view, ObjRef structureRef, int worldX, int worldY){
		GraphLayoutDialog gld = new GraphLayoutDialog(BNAUtils.getParentComposite(view).getShell()); 
		GraphLayoutParameters glp = gld.open(AS.graphLayout);
		if(glp == null){
			return;
		}
		String engineID = (String)glp.getProperty("engineID");
		if(engineID == null){
			return;
		}
		doLayoutInJob(view, structureRef, worldX, worldY, engineID, glp);
		BNAUtils.getParentComposite(view).forceFocus();
	}
	
	protected void doLayout(final IBNAView view, ObjRef structureRef, int worldX, int worldY, String engineID, GraphLayoutParameters glp){
		try{
			GraphLayout gl = AS.graphLayout.layoutGraph(engineID, structureRef, glp);
			view.getWorld().getBNAModel().beginBulkChange();
			applyGraphLayout(view, structureRef, gl, glp, worldX, worldY);
		}
		catch(final GraphLayoutException gle){
			BNAUtils.getParentComposite(view).getDisplay().asyncExec(new Runnable(){
				public void run(){
					MessageDialog.openError(BNAUtils.getParentComposite(view).getShell(), "Error", gle.getMessage());
				}
			});
		}
		finally{
			view.getWorld().getBNAModel().endBulkChange();
		}
	}
	
	protected void applyGraphLayout(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp, int worldX, int worldY){
		for(int i = 0; i < gl.getNumNodes(); i++){
			GraphLayout.Node node = gl.getNodeAt(i);
			String nodeID = node.getNodeId();
			Rectangle nodeBounds = node.getBounds();
			nodeBounds.x += worldX;
			nodeBounds.y += worldY;
			
			IThing nodeRootThing = ArchipelagoUtils.findThing(view.getWorld().getBNAModel(), nodeID);
			if((nodeRootThing != null) && (nodeRootThing instanceof IHasAssemblyData)){
				BoxAssembly boxAssembly = BoxAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)nodeRootThing);
				BoxGlassThing bgt = boxAssembly.getBoxGlassThing();
				if(bgt != null){
					bgt.setBoundingBox(nodeBounds);
				}
			}
		}
		if(!getDontMoveInterfaces(glp)){
			repositionInterfaces(view, structureRef, gl, glp, worldX, worldY);
		}
		if(!getDontRouteLinks(glp)){
			routeLinks(view, structureRef, gl, glp, worldX, worldY);
		}
		else{
			unrouteLinks(view, structureRef, gl, glp, worldX, worldY);
		}
	}
	
	protected void repositionInterfaces(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp, int worldX, int worldY){
		Map<String,List<Point>> interfaceIDtoPointListMap = new HashMap<String,List<Point>>();
		
		for(int i = 0; i < gl.getNumEdges(); i++){
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String linkXArchID = edge.getEdgeId();
			
			ObjRef linkRef = AS.xarch.getByID(documentRootRef, linkXArchID);
			if(linkRef != null){
				ObjRef interface1Ref = (ObjRef)AS.xarch.get(linkRef, "point1");
				if(interface1Ref != null){
					String interface1ID = XadlUtils.getID(AS.xarch, interface1Ref);
					if(interface1ID != null){
						if(edge.getNumPoints() > 0){
							Point interface1Point = edge.getPointAt(0);
							List<Point> pointList = interfaceIDtoPointListMap.get(interface1ID);
							if(pointList == null){
								pointList = new ArrayList<Point>();
								interfaceIDtoPointListMap.put(interface1ID, pointList);
							}
							pointList.add(interface1Point);
						}
					}
				}
				
				ObjRef interface2Ref = (ObjRef)AS.xarch.get(linkRef, "point2");
				if(interface2Ref != null){
					String interface2ID = XadlUtils.getID(AS.xarch, interface2Ref);
					if(interface2ID != null){
						if(edge.getNumPoints() > 0){
							Point interface2Point = edge.getPointAt(edge.getNumPoints() - 1);
							List<Point> pointList = interfaceIDtoPointListMap.get(interface2ID);
							if(pointList == null){
								pointList = new ArrayList<Point>();
								interfaceIDtoPointListMap.put(interface2ID, pointList);
							}
							pointList.add(interface2Point);
						}
					}
				}
			}
		}
		
		for(String interfaceXArchID : interfaceIDtoPointListMap.keySet()){
			IThing nodeRootThing = ArchipelagoUtils.findThing(view.getWorld().getBNAModel(), interfaceXArchID);
			if((nodeRootThing != null) && (nodeRootThing instanceof IHasAssemblyData)){
				EndpointAssembly boxAssembly = EndpointAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)nodeRootThing);
				EndpointGlassThing egt = boxAssembly.getEndpointGlassThing();
				if(egt != null){
					List<Point> pointList = interfaceIDtoPointListMap.get(interfaceXArchID);
					if(pointList.size() > 0){
						//Average the points
						Point p = new Point(0, 0);
						for(Point p2 : pointList){
							p.x += p2.x;
							p.y += p2.y;
						}
						p.x /= pointList.size();
						p.y /= pointList.size();
						
						p.x += worldX;
						p.y += worldY;
						
						egt.setAnchorPoint(p);
					}
				}
			}
		}
	}
	
	protected void routeLinks(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp, int worldX, int worldY){
		for(int i = 0; i < gl.getNumEdges(); i++){
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String edgeID = edge.getEdgeId();
			
			Point[] controlPoints = null;
			if(edge.getNumPoints() > 3){
				controlPoints = new Point[edge.getNumPoints() - 2];
				for(int j = 1; j < edge.getNumPoints() - 1; j++){
					controlPoints[j-1] = edge.getPointAt(j);
					controlPoints[j-1].x += worldX;
					controlPoints[j-1].y += worldY;
				}
			}
			
			//The points returned by dot are actually B-spline control points;
			//we can convert these into rectilinear waypoints to simulate a curve.
			Point[] midpoints = BSpline.bspline(controlPoints, 2);
			midpoints = optimizePoints(midpoints);
			
			IThing linkRootThing = ArchipelagoUtils.findThing(view.getWorld().getBNAModel(), edgeID);
			if((linkRootThing != null) && (linkRootThing instanceof IHasAssemblyData)){
				StickySplineAssembly splineAssembly = StickySplineAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)linkRootThing);
				StickySplineGlassThing sgt = splineAssembly.getSplineGlassThing();
				if(sgt != null){
					sgt.setMidpoints(midpoints);
				}
			}
		}
	}
	
	protected static Point[] optimizePoints(Point[] points){
		List<Point> optimizedPoints = new ArrayList<Point>();
		Point lastPoint = null;
		
		for(int i = 0; i < points.length; i++){
			if(lastPoint == null){
				optimizedPoints.add(points[i]);
				lastPoint = points[i];
			}
			else{
				double dist = Point2D.distance(lastPoint.x, lastPoint.y, points[i].x, points[i].y);
				if(dist < 12.5){
					continue;
				}
				optimizedPoints.add(points[i]);
				lastPoint = points[i];
			}
		}
		return optimizedPoints.toArray(new Point[optimizedPoints.size()]);
	}
	
	protected void unrouteLinks(IBNAView view, ObjRef structureRef, GraphLayout gl, GraphLayoutParameters glp, int worldX, int worldY){
		for(int i = 0; i < gl.getNumEdges(); i++){
			GraphLayout.Edge edge = gl.getEdgeAt(i);
			String edgeID = edge.getEdgeId();
			
			IThing linkRootThing = ArchipelagoUtils.findThing(view.getWorld().getBNAModel(), edgeID);
			if((linkRootThing != null) && (linkRootThing instanceof IHasAssemblyData)){
				StickySplineAssembly splineAssembly = StickySplineAssembly.attach(view.getWorld().getBNAModel(), (IHasAssemblyData)linkRootThing);
				StickySplineGlassThing sgt = splineAssembly.getSplineGlassThing();
				if(sgt != null){
					sgt.setMidpoints(null);
				}
			}
		}
	}

	protected static boolean getDontMoveInterfaces(GraphLayoutParameters glp){
		Object o = glp.getProperty("dontMoveInterfaces");
		if(o == null){
			return false;
		}
		if(!(o instanceof Boolean)){
			return false;
		}
		return ((Boolean)o).booleanValue();
	}
	
	protected static boolean getDontRouteLinks(GraphLayoutParameters glp){
		Object o = glp.getProperty("dontRouteLinks");
		if(o == null){
			return false;
		}
		if(!(o instanceof Boolean)){
			return false;
		}
		return ((Boolean)o).booleanValue();
	}
}
