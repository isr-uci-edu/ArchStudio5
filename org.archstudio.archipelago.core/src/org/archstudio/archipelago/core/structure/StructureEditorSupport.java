package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.mapping.MapBrickLogic;
import org.archstudio.archipelago.core.structure.mapping.MapInterfaceLogic;
import org.archstudio.archipelago.core.structure.mapping.MapLinkLogic;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.ShadowThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class StructureEditorSupport {
	//For tree node cache
	public static final String BNA_WORLD_KEY = "bnaWorld";

	//For editor pane properties
	public static final String EDITING_BNA_COMPOSITE_KEY = "bnaComposite";

	public static void setupEditor(ArchipelagoServices AS, ObjRef structureRef) {
		ObjRef documentRootRef = AS.xarch.getDocumentRootRef(structureRef);

		IBNAWorld bnaWorld = setupWorld(AS, documentRootRef, structureRef);
		if (bnaWorld == null) {
			return;
		}

		AS.editor.clearEditor();
		Composite parentComposite = AS.editor.getParentComposite();
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		parentComposite.setLayout(fl);

		final BNACanvas bnaCanvas = new BNACanvas(parentComposite, SWT.V_SCROLL | SWT.H_SCROLL | SWT.NO_BACKGROUND
				| SWT.DOUBLE_BUFFERED, bnaWorld);
		bnaCanvas.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		bnaCanvas.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaCanvas.getBNAWorld()
						.getBNAModel());
				BNAUtils.saveCoordinateMapperData(bnaCanvas.getCoordinateMapper(), ept);
				bnaCanvas.removeDisposeListener(this);
			}
		});

		BNARenderingSettings.setAntialiasGraphics(bnaCanvas,
				AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS));
		BNARenderingSettings.setAntialiasText(bnaCanvas, AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_TEXT));
		BNARenderingSettings.setDecorativeGraphics(bnaCanvas,
				AS.prefs.getBoolean(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS));

		//StructureMapper.updateStructure(AS, bnaWorld, structureRef);

		//ArchipelagoUtils.addZoomWidget(bnaCanvas, bnaView);

		bnaCanvas.pack();
		parentComposite.layout(true);

		EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaCanvas.getBNAWorld().getBNAModel());
		BNAUtils.restoreCoordinateMapperData((IMutableCoordinateMapper) bnaCanvas.getCoordinateMapper(), ept);

		ArchipelagoUtils.setBNACanvas(AS.editor, bnaCanvas);
		bnaCanvas.setFocus();
	}

	public static IBNAWorld setupWorld(ArchipelagoServices AS, ObjRef documentRootRef, ObjRef structureRef) {
		IBNAWorld bnaWorld = (IBNAWorld) AS.treeNodeDataCache.getData(documentRootRef, structureRef, BNA_WORLD_KEY);
		IBNAModel bnaModel = null;

		if (bnaWorld != null) {
			bnaModel = bnaWorld.getBNAModel();
		}
		else {
			String archStructureID = XadlUtils.getID(AS.xarch, structureRef);
			if (archStructureID == null) {
				return null;
			}
			bnaModel = new DefaultBNAModel();

			EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaModel);
			ept.set(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, archStructureID);

			bnaWorld = new DefaultBNAWorld("structure", bnaModel);
			AS.treeNodeDataCache.setData(documentRootRef, structureRef, BNA_WORLD_KEY, bnaWorld);

			// ArchipelagoUtils.applyGridPreferences(AS, bnaModel);
			bnaModel.addThing(new ShadowThing(null));
			bnaModel.addThing(new GridThing(null));

			setupWorld(AS, structureRef, bnaWorld);
			//AS.eventBus.fireArchipelagoEvent(new StructureEvents.WorldCreatedEvent(structureRef, bnaWorld));
		}

		//StructureMapper.updateStructure(AS, bnaWorld, structureRef);

		return bnaWorld;
	}

	static void setupWorld(ArchipelagoServices AS, ObjRef structureRef, IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		logicManager.addThingLogic(new ClickSelectionLogic());
		logicManager.addThingLogic(new DragMovableLogic());
		logicManager.addThingLogic(new MarqueeSelectionLogic());
		logicManager.addThingLogic(new MousePanAndZoomLogic());

		logicManager.addThingLogic(new MapInterfaceLogic(AS.xarch, structureRef, "component/interface"));
		logicManager.addThingLogic(new MapBrickLogic(AS.xarch, structureRef, "component", //
				new Dimension(120, 80)));
		logicManager.addThingLogic(new MapInterfaceLogic(AS.xarch, structureRef, "connector/interface"));
		logicManager.addThingLogic(new MapBrickLogic(AS.xarch, structureRef, "connector", //
				new Dimension(240, 36)));
		logicManager.addThingLogic(new MapLinkLogic(AS.xarch, structureRef, "link"));

		//logicManager.addThingLogic(new AssemblyLogic());, new Dimension(100,80)
		//logicManager.addThingLogic(new MoveWithLogic(trtl, bbtl, aptl));
		//DragMovableLogic dml = new DragMovableLogic();
		//logicManager.addThingLogic(dml);
		//logicManager.addThingLogic(new SnapToGridLogic(dml, stl));
		//logicManager.addThingLogic(new KeyNudgerLogic());
		//logicManager.addThingLogic(new RotatingOffsetLogic(ttstlOffset));
		//logicManager.addThingLogic(new LifeSapperLogic(ttstlLife));
		//logicManager.addThingLogic(new ClickSelectionLogic(ttstlSelected));
		//logicManager.addThingLogic(new MarqueeSelectionLogic(ttstlSelected));
		//logicManager.addThingLogic(new DragMovableSelectionLogic(dml, stl));
		//logicManager.addThingLogic(new BoxReshapeHandleLogic(stl, bbtl, dml));
		//SplineReshapeHandleLogic srhl = new SplineReshapeHandleLogic(stl, epstl, mpstl, dml);
		//logicManager.addThingLogic(srhl);
		//logicManager.addThingLogic(new StickySplineEndpointsLogic(ttstlSticky, srhl));
		//logicManager.addThingLogic(new StickySplineEndpointsColorLogic(stl, srhl));
		//logicManager.addThingLogic(new MaintainStickyEndpointsLogic(trtl, ttstlStickyEndpoints, sbtl));
		//logicManager.addThingLogic(new SplineBreakLogic());
		//logicManager.addThingLogic(new BoundingBoxRailLogic(trtl, bbtl, aptl));
		//logicManager.addThingLogic(new EndpointFlowOrientationLogic(aptl));
		//logicManager.addThingLogic(new StandardCursorLogic());
		//logicManager.addThingLogic(new ToolTipLogic());
		//logicManager.addThingLogic(new RotaterLogic());
		//
		//ModelBoundsTrackingLogic mbtl = new ModelBoundsTrackingLogic(bbtl, aptl);
		//logicManager.addThingLogic(mbtl);
		//
		//WorldThingInternalEventsLogic vtiel = new WorldThingInternalEventsLogic(ttstlView);
		//logicManager.addThingLogic(vtiel);
		//logicManager.addThingLogic(new WorldThingExternalEventsLogic(ttstlView));
		////logicManager.addThingLogic(new WorldThingDestroyLogic(true));
		//
		//logicManager.addThingLogic(new StructureDropLogic(AS, documentRootRef));
		//
		////(interface-interface) mapping handling logics
		//TypedThingSetTrackingLogic<MappingGlassThing> ttstlMapping = new TypedThingSetTrackingLogic<MappingGlassThing>(
		//		MappingGlassThing.class);
		//logicManager.addThingLogic(ttstlMapping);
		//
		//logicManager.addThingLogic(new MaintainMappingEndpointsLogic(trtl, ttstlMapping, vtiel));
		//
		////XArchEvent logics
		//logicManager.addThingLogic(new StructureXArchEventHandlerLogic(AS, ttstlView));
		//
		//logicManager.addThingLogic(new FileDirtyLogic(AS, documentRootRef));
		//
		////Menu logics
		//logicManager.addThingLogic(new FindDialogLogic(new ArchipelagoFinder(AS)));
		//logicManager.addThingLogic(new AlignAndDistributeLogic());
		//logicManager.addThingLogic(new RectifyToGridLogic());
		//logicManager.addThingLogic(new StructureEditDescriptionLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new StructureEditDirectionLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new StructureNewElementLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new StructureNewInterfaceLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new ShowHideTagsLogic());
		//logicManager.addThingLogic(new RotateTagsLogic());
		//logicManager.addThingLogic(new StructureLinkEndpointLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new StructureEditColorLogic(AS));
		//logicManager.addThingLogic(new StructureRemoveLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new StructureGraphLayoutLogic(AS, documentRootRef));
		//logicManager.addThingLogic(new ExportBitmapLogic(mbtl));
	}
	//public static void readHints(ArchipelagoServices AS, IBNAModel modelToPopulate, ObjRef structureRef) {
	//	StructureHintSupport.readHintsForStructure(AS, modelToPopulate, structureRef);
	//}
	//
	//public static void writeHints(ArchipelagoServices AS, ObjRef documentRootRef, IProgressMonitor monitor) {
	//	ObjRef xADLRef = (ObjRef) AS.xarch.get(documentRootRef, "xADL");
	//	if (xADLRef != null) {
	//		for (ObjRef structureRef : XadlUtils.getAllSubstitutionGroupElementsByTag(AS.xarch, xADLRef,
	//				"topLevelElement", "structure")) {
	//			String structureName = XadlUtils.getName(AS.xarch, structureRef);
	//			if (structureName == null) {
	//				structureName = "[Unnamed Structure]";
	//			}
	//			monitor.setTaskName("Storing Hints for " + structureName);
	//
	//			IBNAWorld world = (IBNAWorld) AS.treeNodeDataCache
	//					.getData(documentRootRef, structureRef, BNA_WORLD_KEY);
	//			if (world != null) {
	//				IBNAModel model = world.getBNAModel();
	//				if (model != null) {
	//					StructureHintSupport.writeHintsForStructure(AS, model, structureRef);
	//				}
	//			}
	//		}
	//	}
	//}
}
