package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoMyxComponent;
import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.mapping.MapBrickLogic;
import org.archstudio.archipelago.core.structure.mapping.MapInterfaceLogic;
import org.archstudio.archipelago.core.structure.mapping.MapLinkLogic;
import org.archstudio.archipelago.core.structure.mapping.MapMappingsLogic;
import org.archstudio.archipelago.core.util.ArchipelagoFinder;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.logics.background.LifeSapperLogic;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.editing.AlignAndDistributeLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.EditFlowLogic;
import org.archstudio.bna.logics.editing.EditTextLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.RectifyToGridLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.ProxyToLogicsLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
import org.archstudio.bna.logics.information.FindDialogLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.things.ShadowThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadlbna.logics.editing.RemoveElementLogic;
import org.archstudio.xadlbna.logics.editing.XadlReshapeSplineGuide;
import org.archstudio.xadlbna.logics.hints.XadlHintRepository;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
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

		final BNACanvas bnaCanvas = new BNACanvas(parentComposite, SWT.V_SCROLL | SWT.H_SCROLL, new DefaultBNAView(
				null, bnaWorld, new LinearCoordinateMapper()));
		bnaCanvas.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		final EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaCanvas.getBNAView()
				.getBNAWorld().getBNAModel());

		ept.set(IHasObjRef.OBJREF_KEY, structureRef);
		ept.set(IHasXArchID.XARCH_ID_KEY, (String) AS.xarch.get(structureRef, "id"));

		// persist the coordinate mapper

		final ICoordinateMapper cm = bnaCanvas.getBNAView().getCoordinateMapper();
		BNAUtils.restoreCoordinateMapperData((IMutableCoordinateMapper) cm, ept);
		bnaCanvas.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				BNAUtils.saveCoordinateMapperData(cm, ept);
			}
		});

		BNARenderingSettings.setAntialiasGraphics(bnaCanvas,
				AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS));
		BNARenderingSettings.setAntialiasText(bnaCanvas, AS.prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_TEXT));
		BNARenderingSettings.setDecorativeGraphics(bnaCanvas,
				AS.prefs.getBoolean(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS));

		bnaCanvas.pack();
		parentComposite.layout(true);

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

			bnaWorld = new DefaultBNAWorld("structure", bnaModel);
			AS.treeNodeDataCache.setData(documentRootRef, structureRef, BNA_WORLD_KEY, bnaWorld);

			// ArchipelagoUtils.applyGridPreferences(AS, bnaModel);

			setupWorld(AS, structureRef, bnaWorld);

			//AS.eventBus.fireArchipelagoEvent(new StructureEvents.WorldCreatedEvent(structureRef, bnaWorld));
		}

		return bnaWorld;
	}

	static void setupWorld(final ArchipelagoServices AS, ObjRef structureRef, IBNAWorld bnaWorld) {
		ObjRef documentRootRef = AS.xarch.getDocumentRootRef(structureRef);

		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();

		logicManager.addThingLogic(new SynchronizeHintsLogic(new XadlHintRepository(AS.xarch)));

		bnaWorld.getBNAModel().addThing(new ShadowThing(null));
		bnaWorld.getBNAModel().addThing(new GridThing(null));

		// these logics need to be first

		logicManager.addThingLogic(new SnapToGridLogic());

		// generic logics -- alphabetized

		logicManager.addThingLogic(new ClickSelectionLogic());
		logicManager.addThingLogic(new DragMovableLogic());
		logicManager.addThingLogic(new KeyNudgerLogic());
		logicManager.addThingLogic(new LifeSapperLogic());
		logicManager.addThingLogic(new MarqueeSelectionLogic());
		logicManager.addThingLogic(new MousePanAndZoomLogic());
		logicManager.addThingLogic(new ReshapeRectangleLogic());
		logicManager.addThingLogic(new ReshapeSplineLogic()).addReshapeSplineGuides(
				new XadlReshapeSplineGuide(AS.xarch, Structure_3_0Package.Literals.LINK,
						Structure_3_0Package.Literals.INTERFACE, -1, 0));
		logicManager.addThingLogic(new RotatingOffsetLogic());
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new StandardCursorLogic());
		logicManager.addThingLogic(new StructureDropLogic(AS, documentRootRef));
		logicManager.addThingLogic(new ToolTipLogic());

		// menu logics -- order dictates menu order

		logicManager.addThingLogic(new StructureNewElementLogic(AS.xarch, AS.resources, structureRef));
		logicManager.addThingLogic(new StructureNewInterfaceLogic(AS.xarch, AS.resources));
		logicManager.addThingLogic(new EditTextLogic());
		logicManager.addThingLogic(new EditFlowLogic());
		logicManager.addThingLogic(new FindDialogLogic(new ArchipelagoFinder(AS)));
		logicManager.addThingLogic(new RemoveElementLogic(AS.xarch));
		logicManager.addThingLogic(new AlignAndDistributeLogic());
		logicManager.addThingLogic(new RectifyToGridLogic());
		logicManager.addThingLogic(new StructureGraphLayoutLogic(AS.xarch, AS.resources, AS.graphLayout, structureRef));

		// xADL mapping logics

		logicManager.addThingLogic(new MapBrickLogic(AS, AS.xarch, structureRef, "component", //
				new Dimension(120, 80), ArchipelagoStructureConstants.DEFAULT_COMPONENT_RGB, 2));
		logicManager.addThingLogic(new MapInterfaceLogic(AS.xarch, structureRef, "component/interface"));
		logicManager.addThingLogic(new MapBrickLogic(AS, AS.xarch, structureRef, "connector", //
				new Dimension(240, 36), ArchipelagoStructureConstants.DEFAULT_CONNECTOR_RGB, 1));
		logicManager.addThingLogic(new MapInterfaceLogic(AS.xarch, structureRef, "connector/interface"));
		logicManager.addThingLogic(new MapLinkLogic(AS.xarch, structureRef, "link"));
		logicManager.addThingLogic(new MapMappingsLogic(AS.xarch, structureRef,
				"component/subStructure/interfaceMapping"));
		logicManager.addThingLogic(new MapMappingsLogic(AS.xarch, structureRef,
				"connector/subStructure/interfaceMapping"));

		// propagate external events logics

		ProxyToLogicsLogic logicProxy = logicManager.addThingLogic(new ProxyToLogicsLogic());
		final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
		final IMyxBrick brick = myxRegistry.waitForBrick(ArchipelagoMyxComponent.class);
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTModelListener.class));
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTFileListener.class));

		// these logics need to be last

		// these logics need to be reintegrated

		//logicManager.addThingLogic(new RotaterLogic());
		//
		////Menu logics
		//
		//logicManager.addThingLogic(new FindDialogLogic(new ArchipelagoFinder(AS)));
		//logicManager.addThingLogic(new ShowHideTagsLogic());
		//logicManager.addThingLogic(new RotateTagsLogic());
		//logicManager.addThingLogic(new StructureEditColorLogic(AS));
		//logicManager.addThingLogic(new ExportBitmapLogic(mbtl));
	}
}
