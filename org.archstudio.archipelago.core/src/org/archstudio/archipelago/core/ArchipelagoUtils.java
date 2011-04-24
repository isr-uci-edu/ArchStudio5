package org.archstudio.archipelago.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.DefaultCoordinateMapper;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ZoomUtils;
import org.archstudio.bna.assemblies.BoxAssembly;
import org.archstudio.bna.assemblies.EndpointAssembly;
import org.archstudio.bna.assemblies.SplineAssembly;
import org.archstudio.bna.assemblies.StickySplineAssembly;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAssemblyData;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;
import org.archstudio.bna.things.borders.PulsingBorderThing;
import org.archstudio.bna.things.labels.UserNotificationThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.NoThing;

public class ArchipelagoUtils{
	public static final String XARCH_ID_PROPERTY_NAME = "#xArchID";

	public static List<Object> combine(List<? extends Object> list1, Object o){
		List<Object> newList = new ArrayList<Object>(list1.size() + 1);
		newList.addAll(list1);
		newList.add(o);
		return newList;
	}
	
	public static List<Object> combine(List<? extends Object> list1, List<? extends Object> list2){
		List<Object> newList = new ArrayList<Object>(list1.size() + list2.size());
		newList.addAll(list2);
		return newList;
	}
	
	public static IThing findThing(IBNAModel m, String xArchID){
		for(IThing t : m.getAllThings()){
			if(!(t instanceof EnvironmentPropertiesThing)){
				String id = t.getProperty(XARCH_ID_PROPERTY_NAME);
				if((id != null) && (id.equals(xArchID))){
					return t;
				}
			}
		}
		return null;
	}
	
	public static IThing[] findAllThings(IBNAModel m, String xArchID){
		List<IThing> results = new ArrayList<IThing>();
		for(IThing t : m.getAllThings()){
			String id = t.getProperty(XARCH_ID_PROPERTY_NAME);
			if((id != null) && (id.equals(xArchID))){
				results.add(t);
			}
		}
		return results.toArray(new IThing[results.size()]);
	}
	
	public static final String BNA_BOTTOM_ROOT_THING_ID = "$BOTTOM";
	public static final String BNA_NORMAL_ROOT_THING_ID = "$NORMAL";
	public static final String BNA_TOP_ROOT_THING_ID = "$TOP";
	
	protected static void initBNAModel(IBNAModel m){
		if(m.getThing(BNA_BOTTOM_ROOT_THING_ID) == null){
			IThing bottomRootThing = new NoThing(BNA_BOTTOM_ROOT_THING_ID);
			m.addThing(bottomRootThing);
		}

		if(m.getThing(BNA_NORMAL_ROOT_THING_ID) == null){
			IThing normalRootThing = new NoThing(BNA_NORMAL_ROOT_THING_ID);
			m.addThing(normalRootThing);
		}
		
		if(m.getThing(BNA_TOP_ROOT_THING_ID) == null){
			IThing topRootThing = new NoThing(BNA_TOP_ROOT_THING_ID);
			m.addThing(topRootThing);
		}
	}
	
	protected static IThing getRootThing(IBNAModel m, String id){
		IThing rootThing = m.getThing(id);
		if(rootThing == null){
			initBNAModel(m);
			return m.getThing(id);
		}
		return rootThing;
	}
	
	public static IThing getNormalRootThing(IBNAModel m){
		return getRootThing(m, BNA_NORMAL_ROOT_THING_ID);
	}
	
	public static IThing getBottomRootThing(IBNAModel m){
		return getRootThing(m, BNA_BOTTOM_ROOT_THING_ID);
	}

	public static IThing getTopRootThing(IBNAModel m){
		return getRootThing(m, BNA_TOP_ROOT_THING_ID);
	}
	
	public static void setNewThingSpot(IBNAModel m, int worldX, int worldY){
		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
		ept.setProperty("#newThingSpotWorldX", worldX);
		ept.setProperty("#newThingSpotWorldY", worldY);
	}
	
	public static Point findOpenSpotForNewThing(IBNAModel m){
		try{
			EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
			if(ept.hasProperty("#newThingSpotWorldX") && ept.hasProperty("#newThingSpotWorldY")){
				int wx = ept.getProperty("#newThingSpotWorldX");
				int wy = ept.getProperty("#newThingSpotWorldY");
				return new Point(wx, wy);
			}
		}
		catch(Exception e){}
		
		Point p = new Point((DefaultCoordinateMapper.DEFAULT_WORLD_WIDTH / 2) + 30, (DefaultCoordinateMapper.DEFAULT_WORLD_HEIGHT / 2) + 30);
		List<IThing> allThings = m.getAllThings();
		while(true){
			boolean found = false;
			for(IThing t : allThings){
				if(t instanceof IHasBoundingBox){
					Rectangle r = ((IHasBoundingBox)t).getBoundingBox();
					if((r.x == p.x) && (r.y == p.y)){
						found = true;
						break;
					}
				}
				if(t instanceof IHasAnchorPoint){
					Point ap = ((IHasAnchorPoint)t).getAnchorPoint();
					if((ap.x == p.x) && (ap.y == p.y)){
						found = true;
						break;
					}
				}
			}
			if(!found){
				return p;
			}
			else{
				p.x += 10;
				p.y += 10;
			}
		}
	}
	
	public static final String EDITOR_PANE_PROPERTY_BNA_COMPOSITE = "bnaComposite";
	
	public static void setBNAComposite(IArchipelagoEditorPane editorPane, BNAComposite bnaComposite){
		editorPane.setProperty(EDITOR_PANE_PROPERTY_BNA_COMPOSITE, bnaComposite);
	}
	
	public static BNAComposite getBNAComposite(IArchipelagoEditorPane editorPane){
		return (BNAComposite)editorPane.getProperty(EDITOR_PANE_PROPERTY_BNA_COMPOSITE);
	}
	
	public static void sendEventToInnerViews(XArchADTModelEvent evt, IBNAWorld world, TypedThingSetTrackingLogic<IHasWorld> ttstlView){
		//Ship the event to subviews
		IHasWorld[] worldThings = ttstlView.getThings();
		if(worldThings != null){
			for(IHasWorld vt : worldThings){
				IBNAWorld innerWorld = vt.getWorld();
				if(innerWorld != null){
					IThingLogicManager innerThingLogicManager = innerWorld.getThingLogicManager();
					if(innerThingLogicManager != null){
						for(IThingLogic innerThingLogic : innerThingLogicManager.getAllThingLogics()){
							if(innerThingLogic instanceof IXArchEventHandlerLogic){
								((IXArchEventHandlerLogic)innerThingLogic).handleXArchADTModelEvent(evt, innerWorld);
							}
						}
					}
				}
			}
		}
	}
	
	public static void showUserNotification(IBNAModel m, String text, int worldX, int worldY){
		UserNotificationThing unt = new UserNotificationThing();
		unt.setText(text);
		unt.setAnchorPoint(new Point(worldX, worldY));
		unt.setVerticalAlignment(VerticalAlignment.MIDDLE);
		unt.setHorizontalAlignment(HorizontalAlignment.CENTER);
		unt.setColor(new RGB(0,0,0));
		unt.setSecondaryColor(new RGB(192,192,192));
		m.addThing(unt);
	}
	
	public static void beginTreeCellEditing(TreeViewer viewer, Object allowEditing){
		viewer.setData("allowCellEditing", allowEditing);
		//System.err.println("allowing cell editing on: " + allowEditing);
		viewer.editElement(allowEditing, 0);
		viewer.setData("allowCellEditing", null);
	}
	
	public static String getClassName(Class<?> c){
		if(c.isArray()){
			Class<?> cc = c.getComponentType();
			return getClassName(cc) + "[]";
		}
		return c.getName();
	}
	
	public static void applyGridPreferences(ArchipelagoServices AS, IBNAModel bnaModel){
		int gridSpacing = AS.prefs.getInt(ArchipelagoConstants.PREF_GRID_SPACING);
		BNAUtils.setGridSpacing(bnaModel, gridSpacing);
		
		String gridDisplayTypeString = AS.prefs.getString(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE);
		if((gridDisplayTypeString == null) || (gridDisplayTypeString.length() == 0)){
			gridDisplayTypeString = GridDisplayType.NONE.name();
		}
		try{
			GridDisplayType gdt = GridDisplayType.valueOf(gridDisplayTypeString);
			if(gdt != null){
				BNAUtils.setGridDisplayType(bnaModel, gdt);
			}
		}
		catch(Exception e){
			BNAUtils.setGridDisplayType(bnaModel, GridDisplayType.NONE);
		}
	}
	
	public static void pulseNotify(final IBNAModel m, final IThing t){
		if((t != null) && (t instanceof IHasBoundingBox)){
			final Rectangle bb = ((IHasBoundingBox)t).getBoundingBox();
			if(bb != null){
				Thread pulserThread = new Thread(){
					public void run(){
						PulsingBorderThing pbt = new PulsingBorderThing();
						pbt.setBoundingBox(bb);
						m.addThing(pbt);
						try{
							Thread.sleep(6000);
						}
						catch(InterruptedException ie){}
						m.removeThing(pbt);
					}
				};
				pulserThread.start();
			}
		}
	}
	
	public static IThing getGlassThing(IBNAModel m, IThing rootThing){
		if((rootThing != null) && (rootThing instanceof IHasAssemblyData)){
			IHasAssemblyData assemblyRootThing = (IHasAssemblyData)rootThing;
			String assemblyKind = assemblyRootThing.getAssemblyKind();
			if(assemblyKind != null){
				if(assemblyKind.equals(BoxAssembly.ASSEMBLY_KIND)){
					BoxAssembly a = BoxAssembly.attach(m, assemblyRootThing);
					return a.getBoxGlassThing();
				}
				else if(assemblyKind.equals(StickySplineAssembly.ASSEMBLY_KIND)){
					StickySplineAssembly a = StickySplineAssembly.attach(m, assemblyRootThing);
					return a.getSplineGlassThing();
				}
				else if(assemblyKind.equals(SplineAssembly.ASSEMBLY_KIND)){
					SplineAssembly a = SplineAssembly.attach(m, assemblyRootThing);
					return a.getSplineGlassThing();
				}
				else if(assemblyKind.equals(EndpointAssembly.ASSEMBLY_KIND)){
					EndpointAssembly a = EndpointAssembly.attach(m, assemblyRootThing);
					return a.getEndpointGlassThing();
				}
			}
		}
		return null;
	}
	
	public static IStructuredSelection addToSelection(ISelection os, Object[] thingsToAdd){
		Set<Object> newSelections = new HashSet<Object>();
		if(os != null){
			if(os instanceof IStructuredSelection){
				for(Iterator it = ((IStructuredSelection)os).iterator(); it.hasNext(); ){
					newSelections.add(it.next());
				}
			}
		}
		for(Object o : thingsToAdd){
			newSelections.add(o);
		}
		return new StructuredSelection(newSelections.toArray());
	}
	
	public static void addZoomWidget(final BNAComposite bnaComposite, final IBNAView bnaView){
		Listener l = new Listener(){
			public void handleEvent(Event e) {
				bnaComposite.forceFocus();
			}
		};
		
		bnaComposite.addListener(SWT.Activate, l);
		bnaComposite.addListener(SWT.MouseDown, l);

		final Control zoomWidget = ZoomUtils.createZoomWidget(bnaComposite, bnaComposite, bnaView.getCoordinateMapper());
		zoomWidget.setSize(zoomWidget.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		zoomWidget.setLocation(bnaComposite.getClientArea().x + bnaComposite.getClientArea().width - zoomWidget.getSize().x - 1, 1);
		
		bnaComposite.addControlListener(new ControlListener(){
			public void controlMoved(ControlEvent e){
			}
			public void controlResized(ControlEvent e){
				zoomWidget.setLocation(bnaComposite.getClientArea().x + bnaComposite.getClientArea().width - zoomWidget.getSize().x - 1, 1);
			}
		});
		
		/*
		final IToolBarManager tb = AS.editor.getActionBars().getToolBarManager();
		
		final ControlContribution cc = new ControlContribution("ZOOM"){
			protected Control createControl(Composite parent){
				final Control zoomWidget = ZoomUtils.createZoomWidget(parent, bnaComposite, bnaView.getCoordinateMapper());
				return zoomWidget;
			}
		};
		tb.add(cc);
		tb.markDirty();
		tb.update(true);
		
		bnaComposite.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e){
				tb.remove(cc);
				tb.update(true);
			}
		});
		*/
	}
}
