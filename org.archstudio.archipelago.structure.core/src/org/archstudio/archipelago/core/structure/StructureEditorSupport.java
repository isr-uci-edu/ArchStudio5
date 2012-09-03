package org.archstudio.archipelago.core.structure;

import java.awt.Dimension;

import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoMyxComponent;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.archipelago.core.IArchipelagoTreeNodeDataCache;
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
import org.archstudio.bna.constants.GridDisplayType;
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
import org.archstudio.bna.logics.editing.RotaterLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.ProxyLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
import org.archstudio.bna.logics.information.FindDialogLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.logics.navigating.ViewAllLogic;
import org.archstudio.bna.things.ShadowThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.DecorateChangesLogic;
import org.archstudio.xadl.bna.logics.editing.RemoveElementLogic;
import org.archstudio.xadl.bna.logics.editing.XadlCopyPasteLogic;
import org.archstudio.xadl.bna.logics.editing.XadlReshapeSplineGuide;
import org.archstudio.xadl.bna.logics.hints.XadlHintRepository;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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

	public static void setupEditor(Services services, IXArchADT xarch, ObjRef structureRef) {
		ObjRef documentRootRef = xarch.getDocumentRootRef(structureRef);

		final IBNAWorld bnaWorld = setupWorld(services, xarch, documentRootRef, structureRef);
		if (bnaWorld == null) {
			return;
		}

		IArchipelagoEditorPane editor = services.get(IArchipelagoEditorPane.class);
		editor.clearEditor();
		Composite parentComposite = editor.getParentComposite();
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		parentComposite.setLayout(fl);

		final BNACanvas bnaCanvas = new BNACanvas(parentComposite, SWT.V_SCROLL | SWT.H_SCROLL, bnaWorld);
		bnaCanvas.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		final EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(bnaCanvas.getBNAView()
				.getBNAWorld().getBNAModel());

		ept.set(IHasObjRef.OBJREF_KEY, structureRef);
		ept.set(IHasXArchID.XARCH_ID_KEY, (String) xarch.get(structureRef, "id"));

		// persist the coordinate mapper

		final ICoordinateMapper cm = bnaCanvas.getBNAView().getCoordinateMapper();
		BNAUtils.restoreCoordinateMapperData((IMutableCoordinateMapper) cm, ept);
		bnaCanvas.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				BNAUtils.saveCoordinateMapperData(cm, ept);
			}
		});

		// coordinate preferences
		final IPreferenceStore prefs = org.archstudio.archipelago.core.Activator.getDefault().getPreferenceStore();
		final IPropertyChangeListener pcl = new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				BNARenderingSettings.setAntialiasGraphics(bnaCanvas,
						prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS));
				BNARenderingSettings.setAntialiasText(bnaCanvas,
						prefs.getBoolean(ArchipelagoConstants.PREF_ANTIALIAS_TEXT));
				BNARenderingSettings.setDecorativeGraphics(bnaCanvas,
						prefs.getBoolean(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS));
				GridThing gridThing = ((GridThing) bnaWorld.getBNAModel().getThing(GridThing.class));
				if (gridThing != null) {
					gridThing.setGridSpacing(prefs.getInt(ArchipelagoConstants.PREF_GRID_SPACING));
					gridThing.setGridDisplayType(GridDisplayType.valueOf(prefs
							.getString(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE)));
				}
				bnaCanvas.redraw();
			}
		};
		prefs.addPropertyChangeListener(pcl);
		bnaCanvas.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				prefs.removePropertyChangeListener(pcl);
			}
		});
		pcl.propertyChange(null);

		bnaCanvas.pack();
		parentComposite.layout(true);

		ArchipelagoUtils.setBNACanvas(editor, bnaCanvas);
		bnaCanvas.setFocus();
	}

	public static IBNAWorld setupWorld(Services services, IXArchADT xarch, ObjRef documentRootRef, ObjRef structureRef) {
		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			IBNAWorld bnaWorld = (IBNAWorld) services.get(IArchipelagoTreeNodeDataCache.class).getData(documentRootRef,
					structureRef, BNA_WORLD_KEY);
			if (bnaWorld != null)
				return bnaWorld;
		}

		String archStructureID = XadlUtils.getID(xarch, structureRef);
		if (archStructureID == null) {
			return null;
		}
		IBNAModel bnaModel = new DefaultBNAModel();
		IBNAWorld bnaWorld = new DefaultBNAWorld("structure", bnaModel);
		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			services.get(IArchipelagoTreeNodeDataCache.class).setData(documentRootRef, structureRef, BNA_WORLD_KEY,
					bnaWorld);
		}

		// ArchipelagoUtils.applyGridPreferences(AS, bnaModel);

		setupWorld(services, xarch, documentRootRef, structureRef, bnaWorld);

		//AS.eventBus.fireArchipelagoEvent(new StructureEvents.WorldCreatedEvent(structureRef, bnaWorld));

		return bnaWorld;
	}

	static void setupWorld(Services services, IXArchADT xarch, ObjRef documentRootRef, ObjRef structureRef,
			IBNAWorld bnaWorld) {
		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();
		ProxyLogic logicProxy = logicManager.addThingLogic(new ProxyLogic());

		logicManager.addThingLogic(new SynchronizeHintsLogic(logicProxy.addObject(new XadlHintRepository(xarch))));

		bnaWorld.getBNAModel().addThing(new ShadowThing(null));
		bnaWorld.getBNAModel().addThing(new GridThing(GridThing.class));

		// these logics need to be first

		logicManager.addThingLogic(new SnapToGridLogic());

		// generic logics -- alphabetized

		logicManager.addThingLogic(new ClickSelectionLogic());
		logicManager.addThingLogic(new DecorateChangesLogic((IXArchADTVariability) xarch));
		logicManager.addThingLogic(new DragMovableLogic());
		logicManager.addThingLogic(new KeyNudgerLogic());
		logicManager.addThingLogic(new LifeSapperLogic());
		logicManager.addThingLogic(new MarqueeSelectionLogic());
		logicManager.addThingLogic(new MousePanAndZoomLogic());
		logicManager.addThingLogic(new ReshapeRectangleLogic());
		logicManager.addThingLogic(new ReshapeSplineLogic()).addReshapeSplineGuides(
				new XadlReshapeSplineGuide(xarch, Structure_3_0Package.Literals.LINK,
						Structure_3_0Package.Literals.INTERFACE, -1, 0));
		logicManager.addThingLogic(new RotatingOffsetLogic());
		logicManager.addThingLogic(new SplineBreakLogic());
		logicManager.addThingLogic(new StandardCursorLogic());
		logicManager.addThingLogic(new StructureDropLogic(services, documentRootRef));
		logicManager.addThingLogic(new ToolTipLogic());

		// menu logics -- order dictates menu order

		logicManager.addThingLogic(new StructureNewElementLogic(xarch, services.get(IResources.class), structureRef));
		logicManager.addThingLogic(new StructureNewInterfaceLogic(xarch, services.get(IResources.class)));
		logicManager.addThingLogic(new StructureNewInterfaceMappingLogic(xarch, services.get(IResources.class)));
		logicManager.addThingLogic(new EditTextLogic());
		logicManager.addThingLogic(new StructureEditColorLogic(xarch));
		logicManager.addThingLogic(new EditFlowLogic());
		logicManager.addThingLogic(new StructureAssignMyxGenLogic(xarch));
		//logicManager.addThingLogic(new StructureEditColorLogic(AS));
		logicManager.addThingLogic(new ShowHideTagsLogic());
		logicManager.addThingLogic(new FindDialogLogic(new ArchipelagoFinder(xarch, services.get(IResources.class))));
		logicManager.addThingLogic(new XadlCopyPasteLogic(xarch, services.get(IArchipelagoEditorPane.class)
				.getActionBars()));
		logicManager.addThingLogic(new RemoveElementLogic(xarch));
		logicManager.addThingLogic(new RotaterLogic());
		logicManager.addThingLogic(new AlignAndDistributeLogic());
		logicManager.addThingLogic(new RectifyToGridLogic());
		logicManager.addThingLogic(new StructureGraphLayoutLogic(xarch, services.get(IResources.class), services
				.get(IGraphLayout.class), structureRef));
		//logicManager.addThingLogic(new ExportBitmapLogic());
		logicManager.addThingLogic(new ViewAllLogic());

		// xADL mapping logics

		logicManager.addThingLogic(new MapBrickLogic(services, xarch, structureRef,
				"component", //
				new Dimension(120, 80), ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR, 2,
				ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_FONT));
		logicManager.addThingLogic(new MapInterfaceLogic(xarch, structureRef, "component/interface"));
		logicManager
				.addThingLogic(new MapMappingsLogic(xarch, structureRef, "component/subStructure/interfaceMapping"));

		logicManager.addThingLogic(new MapBrickLogic(services, xarch, structureRef,
				"connector", //
				new Dimension(240, 36), ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR, 1,
				ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_FONT));
		logicManager.addThingLogic(new MapInterfaceLogic(xarch, structureRef, "connector/interface"));
		logicManager
				.addThingLogic(new MapMappingsLogic(xarch, structureRef, "connector/subStructure/interfaceMapping"));

		logicManager.addThingLogic(new MapLinkLogic(xarch, structureRef, "link"));

		// propagate external events logics

		final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
		final IMyxBrick brick = myxRegistry.waitForBrick(ArchipelagoMyxComponent.class);
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTModelListener.class));
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTFileListener.class));
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTVariabilityListener.class));

		// these logics need to be last
	}
}
