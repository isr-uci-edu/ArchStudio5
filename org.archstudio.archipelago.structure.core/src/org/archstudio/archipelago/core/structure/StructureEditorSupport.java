package org.archstudio.archipelago.core.structure;

import java.awt.Dimension;
import java.awt.geom.Point2D;

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
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.background.LifeSapperLogic;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.editing.AlignAndDistributeLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.EditFlowLogic;
import org.archstudio.bna.logics.editing.EditTextLogic;
import org.archstudio.bna.logics.editing.ExportImageLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.RectifyToGridLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.RotaterLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.ProxyLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
import org.archstudio.bna.logics.information.FindDialogLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.PanAndZoomLogic;
import org.archstudio.bna.logics.navigating.ViewAllLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.ui.IBNAUI.AvailableUI;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.utils.bna.dot.ExportImportDot;
import org.archstudio.utils.bna.gexf.ExportImportGexf;
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
	public static final String BNA_WORLD_KEY = "world";

	//For editor pane properties
	public static final String EDITING_BNA_COMPOSITE_KEY = "bnaComposite";

	public static void setupEditor(Services services, IXArchADT xarch, ObjRef structureRef) {

		// if the editor is already set up, return
		{
			IArchipelagoEditorPane editor = services.get(IArchipelagoEditorPane.class);
			if (editor != null) {
				BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
				if (bnaCanvas != null) {
					IBNAWorld world = bnaCanvas.getBNAView().getBNAWorld();
					EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
					if (ept.has(IHasObjRef.OBJREF_KEY, structureRef)) {
						return;
					}
				}
			}
		}

		ObjRef documentRootRef = xarch.getDocumentRootRef(structureRef);

		final IBNAWorld world = setupWorld(services, xarch, documentRootRef, structureRef);
		if (world == null) {
			return;
		}

		IArchipelagoEditorPane editor = services.get(IArchipelagoEditorPane.class);
		editor.clearEditor();
		Composite parentComposite = editor.getParentComposite();
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		parentComposite.setLayout(fl);

		final BNACanvas bnaCanvas = new BNACanvas(parentComposite, SWT.V_SCROLL | SWT.H_SCROLL, world);
		bnaCanvas.setBackground(parentComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		final EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);

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
				BNARenderingSettings.setDisplayShadows(bnaCanvas,
						prefs.getBoolean(ArchipelagoConstants.PREF_DISPLAY_SHADOWS));
				GridThing gridThing = GridThing.getIn(world);
				if (gridThing != null) {
					gridThing.setGridSpacing(prefs.getInt(ArchipelagoConstants.PREF_GRID_SPACING));
					gridThing.setGridDisplayType(GridDisplayType.valueOf(prefs
							.getString(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE)));
				}
				try {
					AvailableUI availableUI = AvailableUI.valueOf(prefs.getString(ArchipelagoConstants.PREF_BNA_UI));
					IBNAUI bnaUI = (IBNAUI) availableUI.getBNAUIClass().getConstructors()[0].newInstance(bnaCanvas
							.getBNAView());
					bnaCanvas.setBNAUI(bnaUI);
				}
				catch (Exception e) {
					e.printStackTrace();
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
			IBNAWorld world = (IBNAWorld) services.get(IArchipelagoTreeNodeDataCache.class).getData(documentRootRef,
					structureRef, BNA_WORLD_KEY);
			if (world != null) {
				return world;
			}
		}

		String archStructureID = XadlUtils.getID(xarch, structureRef);
		if (archStructureID == null) {
			return null;
		}
		IBNAModel bnaModel = new DefaultBNAModel();
		IBNAWorld world = new DefaultBNAWorld(XadlUtils.getName(xarch, structureRef), bnaModel);
		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			services.get(IArchipelagoTreeNodeDataCache.class).setData(documentRootRef, structureRef, BNA_WORLD_KEY,
					world);
		}

		// ArchipelagoUtils.applyGridPreferences(AS, bnaModel);

		setupWorld(services, xarch, documentRootRef, structureRef, world);

		//AS.eventBus.fireArchipelagoEvent(new StructureEvents.WorldCreatedEvent(structureRef, world));

		return world;
	}

	static void setupWorld(Services services, IXArchADT xarch, ObjRef documentRootRef, ObjRef structureRef,
			IBNAWorld world) {
		IThingLogicManager logics = world.getThingLogicManager();

		// these logics need to be first

		ProxyLogic proxyLogic = logics.addThingLogic(ProxyLogic.class);
		logics.addThingLogic(SnapToGridLogic.class);
		logics.addThingLogic(new SynchronizeHintsLogic(world, proxyLogic.addObject(new XadlHintRepository(xarch))));

		GridThing.createIn(world);
		ShadowThing.createIn(world);

		// generic logics -- alphabetized

		logics.addThingLogic(ClickSelectionLogic.class);
		logics.addThingLogic(new DecorateChangesLogic(world, (IXArchADTVariability) xarch));
		logics.addThingLogic(DragMovableLogic.class);
		logics.addThingLogic(KeyNudgerLogic.class);
		logics.addThingLogic(LifeSapperLogic.class);
		logics.addThingLogic(MarqueeSelectionLogic.class);
		logics.addThingLogic(PanAndZoomLogic.class);
		logics.addThingLogic(ReshapeRectangleLogic.class);
		logics.addThingLogic(ReshapeSplineLogic.class).addReshapeSplineGuides(
				new XadlReshapeSplineGuide(xarch, Structure_3_0Package.Literals.LINK,
						Structure_3_0Package.Literals.INTERFACE, IHasEndpoints.ENDPOINT_1_KEY,
						IHasEndpoints.ENDPOINT_2_KEY));
		logics.addThingLogic(RotatingOffsetLogic.class);
		logics.addThingLogic(StandardCursorLogic.class);
		logics.addThingLogic(new StructureDropLogic(world, services, documentRootRef));
		logics.addThingLogic(ToolTipLogic.class);
		logics.addThingLogic(HighlightLogic.class);

		// menu logics -- order dictates menu order

		logics.addThingLogic(new StructureNewElementLogic(world, xarch, services.get(IResources.class), structureRef));
		logics.addThingLogic(new StructureNewInterfaceLogic(world, xarch, services.get(IResources.class)));
		logics.addThingLogic(new StructureNewInterfaceMappingLogic(world, xarch, services.get(IResources.class)));
		logics.addThingLogic(EditTextLogic.class);
		logics.addThingLogic(new StructureEditColorLogic(world, xarch));
		logics.addThingLogic(EditFlowLogic.class);
		logics.addThingLogic(new StructureAssignMyxGenLogic(world, xarch));
		//logics.addThingLogic(new StructureEditColorLogic(AS));
		logics.addThingLogic(ShowHideTagsLogic.class);
		logics.addThingLogic(new FindDialogLogic(world, new ArchipelagoFinder(xarch, services.get(IResources.class))));
		logics.addThingLogic(new XadlCopyPasteLogic(world, xarch, services.get(IArchipelagoEditorPane.class)
				.getActionBars()));
		logics.addThingLogic(new RemoveElementLogic(world, xarch));
		logics.addThingLogic(RotaterLogic.class);
		logics.addThingLogic(AlignAndDistributeLogic.class);
		logics.addThingLogic(RectifyToGridLogic.class);
		logics.addThingLogic(new StructureGraphLayoutLogic(world, xarch, services.get(IResources.class), services
				.get(IGraphLayout.class), structureRef));
		logics.addThingLogic(new ExportImportGexf(world) {
			@Override
			protected boolean isNodeOfInterest(IBNAModel model, IThing node) {
				return Assemblies.getEditableThing(model, node, IHasBoundingBox.class, IHasMutableSize.USER_MAY_RESIZE) == node;
			}

			@Override
			protected IThing getNodeFromEdgeEndpoint(IBNAModel model, IThing edge, IThingKey<Point2D> endpointKey,
					StickPointLogic spl) {
				IThing endpointRoot = super.getNodeFromEdgeEndpoint(model, edge, endpointKey, spl);
				IThing brick = Assemblies.getRoot(model, model.getParentThing(endpointRoot));
				return brick;
			}
		});
		logics.addThingLogic(new ExportImportDot(world) {
			@Override
			protected boolean isNodeOfInterest(IBNAModel model, IThing node) {
				return Assemblies.getEditableThing(model, node, IHasBoundingBox.class, IHasMutableSize.USER_MAY_RESIZE) == node;
			}

			@Override
			protected IThing getNodeFromEdgeEndpoint(IBNAModel model, IThing edge, IThingKey<Point2D> endpointKey,
					StickPointLogic spl) {
				IThing endpointRoot = super.getNodeFromEdgeEndpoint(model, edge, endpointKey, spl);
				IThing brick = Assemblies.getRoot(model, model.getParentThing(endpointRoot));
				return brick;
			}
		});
		logics.addThingLogic(ViewAllLogic.class);
		logics.addThingLogic(ExportImageLogic.class);

		// xADL mapping logics

		String prefix = "" + XadlUtils.getName(xarch, structureRef) + ": ";

		logics.addThingLogic(new MapBrickLogic(world, services, xarch, structureRef,
				"component", //
				new Dimension(120, 80), ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR, 2,
				ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_FONT, prefix + "Loading Components"));
		logics.addThingLogic(new MapInterfaceLogic(world, xarch, structureRef, "component/interface", prefix
				+ "Loading Component Interfaces"));
		logics.addThingLogic(new MapMappingsLogic(world, xarch, structureRef,
				"component/subStructure/interfaceMapping", prefix + "Loading Component Substructure Links"));

		logics.addThingLogic(new MapBrickLogic(world, services, xarch, structureRef,
				"connector", //
				new Dimension(240, 36), ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR, 1,
				ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_FONT, prefix + "Loading Connectors"));
		logics.addThingLogic(new MapInterfaceLogic(world, xarch, structureRef, "connector/interface", prefix
				+ "Loading Connector Interfaces"));
		logics.addThingLogic(new MapMappingsLogic(world, xarch, structureRef,
				"connector/subStructure/interfaceMapping", prefix + "Loading Component Substructure Links"));

		logics.addThingLogic(new MapLinkLogic(world, xarch, structureRef, "link", prefix + "Loading Links"));

		// propagate external events logics

		final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
		final IMyxBrick brick = myxRegistry.waitForBrick(ArchipelagoMyxComponent.class);
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTModelListener.class));
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTFileListener.class));
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTVariabilityListener.class));

		// these logics need to be last
	}
}
