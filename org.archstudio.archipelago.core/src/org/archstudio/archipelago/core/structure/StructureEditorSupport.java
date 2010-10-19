package org.archstudio.archipelago.core.structure;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.util.ArchipelagoFinder;
import org.archstudio.archipelago.core.util.FileDirtyLogic;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.BNARenderingSettings;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.DefaultBNAModel;
import org.archstudio.bna.DefaultBNAView;
import org.archstudio.bna.DefaultBNAWorld;
import org.archstudio.bna.DefaultCoordinateMapper;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.facets.IHasStickyEndpoints;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.background.LifeSapperLogic;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic;
import org.archstudio.bna.logics.coordinating.AssemblyLogic;
import org.archstudio.bna.logics.coordinating.BoundingBoxRailLogic;
import org.archstudio.bna.logics.coordinating.EndpointFlowOrientationLogic;
import org.archstudio.bna.logics.coordinating.MaintainIndicatorLogic;
import org.archstudio.bna.logics.coordinating.MaintainMappingEndpointsLogic;
import org.archstudio.bna.logics.coordinating.MaintainStickyEndpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorAnchorPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorEndpointLogic;
import org.archstudio.bna.logics.coordinating.MirrorEndpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorMidpointsLogic;
import org.archstudio.bna.logics.coordinating.MirrorPathDataLogic;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.coordinating.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.coordinating.WorldThingInternalEventsLogic;
import org.archstudio.bna.logics.editing.AlignAndDistributeLogic;
import org.archstudio.bna.logics.editing.BoxReshapeHandleLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.DragMovableSelectionLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.RectifyToGridLogic;
import org.archstudio.bna.logics.editing.RotateTagsLogic;
import org.archstudio.bna.logics.editing.RotaterLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.SplineReshapeHandleLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.editing.StickySplineEndpointsColorLogic;
import org.archstudio.bna.logics.editing.StickySplineEndpointsLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.FindDialogLogic;
import org.archstudio.bna.logics.navigating.MousePanningLogic;
import org.archstudio.bna.logics.navigating.MouseWheelZoomingLogic;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.EndpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.logics.tracking.MouseLocationTrackingLogic;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;
import org.archstudio.bna.logics.tracking.StickyBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;
import org.archstudio.bna.logics.util.ExportBitmapLogic;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;

public class StructureEditorSupport{
	//For tree node cache
	public static final String BNA_WORLD_KEY = "bnaWorld";
	
	//For editor pane properties
	public static final String EDITING_BNA_COMPOSITE_KEY = "bnaComposite";
	
	public static void setupEditor(ArchipelagoServices AS, ObjRef structureRef){
		ObjRef documentRootRef = AS.xarch.getDocumentRootRef(structureRef);

		IBNAWorld bnaWorld = setupWorld(AS, documentRootRef, structureRef);
		if(bnaWorld == null) return;
		
		final IBNAView bnaView = new DefaultBNAView(null, bnaWorld, new DefaultCoordinateMapper());

		AS.editor.clearEditor();
		Composite parentComposite = AS.editor.getParentComposite();
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		parentComposite.setLayout(fl);
		
		final BNAComposite bnaComposite = new BNAComposite(parentComposite, SWT.V_SCROLL
			| SWT.H_SCROLL | SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED, bnaView);
		bnaComposite.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		bnaComposite.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e){
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaComposite.getView().getWorld().getBNAModel());
				BNAUtils.saveCoordinateMapperData(bnaComposite.getView().getCoordinateMapper(), ept);
				bnaComposite.removeDisposeListener(this);
			}
		});

		BNARenderingSettings.setAntialiasGraphics(bnaComposite, AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS));
		BNARenderingSettings.setAntialiasText(bnaComposite, AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_TEXT));
		BNARenderingSettings.setDecorativeGraphics(bnaComposite, AS.prefs.getBoolean(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS));
		
		//StructureMapper.updateStructure(AS, bnaWorld, structureRef);

		ArchipelagoUtils.addZoomWidget(bnaComposite, bnaView);
		
		bnaComposite.pack();
		parentComposite.layout(true);
		
		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaComposite.getView().getWorld().getBNAModel());
		BNAUtils.restoreCoordinateMapperData((IMutableCoordinateMapper)bnaComposite.getView().getCoordinateMapper(), ept);
		
		ArchipelagoUtils.setBNAComposite(AS.editor, bnaComposite);
		bnaComposite.setFocus();
	}
	
	public static IBNAWorld setupWorld(ArchipelagoServices AS, ObjRef documentRootRef, ObjRef structureRef){
		IBNAWorld bnaWorld = (IBNAWorld)AS.treeNodeDataCache.getData(documentRootRef, structureRef, BNA_WORLD_KEY);
		IBNAModel bnaModel = null;

		if(bnaWorld != null){
			bnaModel = bnaWorld.getBNAModel();
		}
		else{
			String archStructureID = XadlUtils.getID(AS.xarch, structureRef);
			if(archStructureID == null) return null;
			bnaModel = new DefaultBNAModel();
			
			EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaModel);
			ept.setProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, archStructureID);
		
			bnaWorld = new DefaultBNAWorld("structure", bnaModel);
			AS.treeNodeDataCache.setData(documentRootRef, structureRef, BNA_WORLD_KEY, bnaWorld);
			
			setupWorld(AS, AS.xarch.getDocumentRootRef(structureRef), bnaWorld);
			AS.eventBus.fireArchipelagoEvent(new StructureEvents.WorldCreatedEvent(structureRef, bnaWorld));
		}

		ArchipelagoUtils.applyGridPreferences(AS, bnaModel);
		
		StructureMapper.updateStructure(AS, bnaWorld, structureRef);
		
		return bnaWorld;
	}
	
	static void setupWorld(ArchipelagoServices AS, ObjRef documentRootRef, IBNAWorld bnaWorld){
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		logicManager.addThingLogic(new MouseWheelZoomingLogic());
		logicManager.addThingLogic(new MousePanningLogic());
		
		MouseLocationTrackingLogic mltl = new MouseLocationTrackingLogic();
		logicManager.addThingLogic(mltl);
		ThingRefTrackingLogic trtl = new ThingRefTrackingLogic();
		logicManager.addThingLogic(trtl);
		BoundingBoxTrackingLogic bbtl = new BoundingBoxTrackingLogic();
		logicManager.addThingLogic(bbtl);
		AnchorPointTrackingLogic aptl = new AnchorPointTrackingLogic();
		logicManager.addThingLogic(aptl);
		SelectionTrackingLogic stl = new SelectionTrackingLogic();
		logicManager.addThingLogic(stl);
		EndpointsTrackingLogic epstl = new EndpointsTrackingLogic();
		logicManager.addThingLogic(epstl);
		MidpointsTrackingLogic mpstl = new MidpointsTrackingLogic();
		logicManager.addThingLogic(mpstl);
		StickyBoxTrackingLogic sbtl = new StickyBoxTrackingLogic();
		logicManager.addThingLogic(sbtl);

		TypedThingSetTrackingLogic<IHasMutableRotatingOffset> ttstlOffset = new TypedThingSetTrackingLogic<IHasMutableRotatingOffset>(
			IHasMutableRotatingOffset.class);
		logicManager.addThingLogic(ttstlOffset);

		TypedThingSetTrackingLogic<IHasMutableLife> ttstlLife = new TypedThingSetTrackingLogic<IHasMutableLife>(
			IHasMutableLife.class);
		logicManager.addThingLogic(ttstlLife);
		
		TypedThingSetTrackingLogic<IHasMutableSelected> ttstlSelected = new TypedThingSetTrackingLogic<IHasMutableSelected>(
			IHasMutableSelected.class);
		logicManager.addThingLogic(ttstlSelected);

		TypedThingSetTrackingLogic<IHasStickyBox> ttstlSticky = new TypedThingSetTrackingLogic<IHasStickyBox>(
			IHasStickyBox.class);
		logicManager.addThingLogic(ttstlSticky);
		
		TypedThingSetTrackingLogic<IHasStickyEndpoints> ttstlStickyEndpoints = new TypedThingSetTrackingLogic<IHasStickyEndpoints>(
			IHasStickyEndpoints.class);
		logicManager.addThingLogic(ttstlStickyEndpoints);
		
		TypedThingSetTrackingLogic<IHasWorld> ttstlView = new TypedThingSetTrackingLogic<IHasWorld>(
			IHasWorld.class);
		logicManager.addThingLogic(ttstlView);
		
		logicManager.addThingLogic(new AssemblyLogic());
		logicManager.addThingLogic(new MoveWithLogic(trtl, bbtl, aptl));
		logicManager.addThingLogic(new MirrorAnchorPointLogic(trtl, aptl));
		logicManager.addThingLogic(new MirrorBoundingBoxLogic(trtl, bbtl));
		logicManager.addThingLogic(new MirrorEndpointsLogic(trtl, epstl));
		logicManager.addThingLogic(new MirrorMidpointsLogic(trtl, mpstl));
		logicManager.addThingLogic(new MirrorEndpointLogic(trtl, epstl));
		logicManager.addThingLogic(new MaintainIndicatorLogic(trtl, bbtl, aptl));
		logicManager.addThingLogic(new ArrowheadLogic(trtl, epstl, mpstl));
		logicManager.addThingLogic(new MirrorPathDataLogic(trtl));
		DragMovableLogic dml = new DragMovableLogic();
		logicManager.addThingLogic(dml);
		logicManager.addThingLogic(new SnapToGridLogic(dml, stl));
		logicManager.addThingLogic(new KeyNudgerLogic());
		logicManager.addThingLogic(new RotatingOffsetLogic(ttstlOffset));
		logicManager.addThingLogic(new LifeSapperLogic(ttstlLife));
		logicManager.addThingLogic(new ClickSelectionLogic(ttstlSelected));
		logicManager.addThingLogic(new MarqueeSelectionLogic(ttstlSelected));
		logicManager.addThingLogic(new DragMovableSelectionLogic(dml, stl));
		logicManager.addThingLogic(new BoxReshapeHandleLogic(stl, bbtl, dml));
		SplineReshapeHandleLogic srhl = new SplineReshapeHandleLogic(stl, epstl, mpstl, dml);
		logicManager.addThingLogic(srhl);
		logicManager.addThingLogic(new StickySplineEndpointsLogic(ttstlSticky, srhl));
		logicManager.addThingLogic(new StickySplineEndpointsColorLogic(stl, srhl));
		logicManager.addThingLogic(new MaintainStickyEndpointsLogic(trtl, ttstlStickyEndpoints, sbtl));
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new BoundingBoxRailLogic(trtl, bbtl, aptl));
		logicManager.addThingLogic(new EndpointFlowOrientationLogic(aptl));
		logicManager.addThingLogic(new StandardCursorLogic());
		logicManager.addThingLogic(new ToolTipLogic());
		logicManager.addThingLogic(new RotaterLogic());

		ModelBoundsTrackingLogic mbtl = new ModelBoundsTrackingLogic(bbtl, aptl);
		logicManager.addThingLogic(mbtl);

		WorldThingInternalEventsLogic vtiel = new WorldThingInternalEventsLogic(ttstlView);
		logicManager.addThingLogic(vtiel);
		logicManager.addThingLogic(new WorldThingExternalEventsLogic(ttstlView));
		//logicManager.addThingLogic(new WorldThingDestroyLogic(true));
		
		logicManager.addThingLogic(new StructureDropLogic(AS, documentRootRef));

		//(interface-interface) mapping handling logics
		TypedThingSetTrackingLogic<MappingGlassThing> ttstlMapping = new TypedThingSetTrackingLogic<MappingGlassThing>(
			MappingGlassThing.class);
		logicManager.addThingLogic(ttstlMapping);
		
		logicManager.addThingLogic(new MaintainMappingEndpointsLogic(trtl, ttstlMapping, vtiel));

		//XArchEvent logics
		logicManager.addThingLogic(new StructureXArchEventHandlerLogic(AS, ttstlView));
		
		logicManager.addThingLogic(new FileDirtyLogic(AS, documentRootRef));
		
		//Menu logics
		logicManager.addThingLogic(new FindDialogLogic(new ArchipelagoFinder(AS)));
		logicManager.addThingLogic(new AlignAndDistributeLogic());
		logicManager.addThingLogic(new RectifyToGridLogic());
		logicManager.addThingLogic(new StructureEditDescriptionLogic(AS, documentRootRef));
		logicManager.addThingLogic(new StructureEditDirectionLogic(AS, documentRootRef));
		logicManager.addThingLogic(new StructureNewElementLogic(AS, documentRootRef));
		logicManager.addThingLogic(new StructureNewInterfaceLogic(AS, documentRootRef));
		logicManager.addThingLogic(new ShowHideTagsLogic());
		logicManager.addThingLogic(new RotateTagsLogic());
		logicManager.addThingLogic(new StructureLinkEndpointLogic(AS, documentRootRef));
		logicManager.addThingLogic(new StructureEditColorLogic(AS));
		logicManager.addThingLogic(new StructureRemoveLogic(AS, documentRootRef));
		logicManager.addThingLogic(new StructureGraphLayoutLogic(AS, documentRootRef));
		logicManager.addThingLogic(new ExportBitmapLogic(mbtl));
	}
	
	public static void readHints(ArchipelagoServices AS, IBNAModel modelToPopulate, ObjRef structureRef){
		StructureHintSupport.readHintsForStructure(AS, modelToPopulate, structureRef);
	}
	
	public static void writeHints(ArchipelagoServices AS, ObjRef documentRootRef, IProgressMonitor monitor){
		ObjRef xADLRef = (ObjRef)AS.xarch.get(documentRootRef, "xADL");
		if(xADLRef != null){
			for(ObjRef structureRef : XadlUtils.getAllSubstitutionGroupElementsByTag(AS.xarch, xADLRef, "topLevelElement", "structure")){
				String structureName = XadlUtils.getName(AS.xarch, structureRef);
				if(structureName == null) structureName = "[Unnamed Structure]";
				monitor.setTaskName("Storing Hints for " + structureName);
				
				IBNAWorld world = (IBNAWorld)AS.treeNodeDataCache.getData(documentRootRef, structureRef, BNA_WORLD_KEY);
				if(world != null){
					IBNAModel model = world.getBNAModel();
					if(model != null){
						StructureHintSupport.writeHintsForStructure(AS, model, structureRef);
					}
				}
			}
		}
	}
}
