package org.archstudio.archipelago.statechart.core;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.AbstractArchipelagoTreePlugin;
import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoMyxComponent;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.archipelago.core.IArchipelagoTreeNodeDataCache;
import org.archstudio.archipelago.statechart.core.logics.MapFinalStateLogic;
import org.archstudio.archipelago.statechart.core.logics.MapInitialStateLogic;
import org.archstudio.archipelago.statechart.core.logics.MapStateLogic;
import org.archstudio.archipelago.statechart.core.logics.MapTransitionLogic;
import org.archstudio.archipelago.statechart.core.logics.NewElementLogic;
import org.archstudio.archipelago.statechart.core.logics.StatechartDropLogic;
import org.archstudio.archipelago.statechart.core.logics.StatechartEditColorLogic;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
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
import org.archstudio.bna.logics.editing.SplineBreakLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.ProxyLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
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
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.DecorateChangesLogic;
import org.archstudio.xadl.bna.logics.editing.RemoveElementLogic;
import org.archstudio.xadl.bna.logics.editing.XadlCopyPasteLogic;
import org.archstudio.xadl.bna.logics.editing.XadlReshapeSplineGuide;
import org.archstudio.xadl.bna.logics.hints.XadlHintRepository;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Lists;

public class StatechartTreePlugin extends AbstractArchipelagoTreePlugin {

	private final String TOP_LEVEL_ELEMENT_NAME = "statechart";
	private final String LABEL_NAME = "Statecharts";
	private final String ELEMENT_LABEL_NAME = "Statechart";
	private final EClass ELEMENT_TYPE = Statechart_1_0Package.Literals.STATECHART;
	private final String FOLDER_NODE_TYPE = this.getClass().getName();
	private final String XADL_FEATURE_NAME = "topLevelElement";
	private final EClass DND_SOURCE = Statechart_1_0Package.Literals.STATECHART;
	private final EClass DND_TARGET = Statechart_1_0Package.Literals.STATECHART;

	public StatechartTreePlugin(final TreeViewer viewer, final Services AS, final ObjRef documentRootRef) {
		final IXArchADT xarch = AS.get(IXArchADT.class);

		this.contentProvider = new IArchipelagoTreeContentProvider() {

			FolderNode folderNode = null;

			@Override
			public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider) {
				return hasChildrenFromPreviousProvider || !getChildren(element, Lists.newArrayList()).isEmpty();
			}

			@Override
			public Object getParent(Object element, Object parentFromPreviousProvider) {
				if (element instanceof FolderNode) {
					return ((FolderNode) element).getParent();
				}
				if (element instanceof ObjRef
						&& xarch.getTagsOnlyPathString((ObjRef) element).equals("xADL/" + TOP_LEVEL_ELEMENT_NAME)) {
					return folderNode;
				}
				return parentFromPreviousProvider;
			}

			@Override
			public List<? extends Object> getChildren(Object parentElement,
					List<? extends Object> childrenFromPreviousProvider) {
				if (parentElement instanceof ObjRef
						&& xarch.getTagsOnlyPathString((ObjRef) parentElement).equals("xADL")) {
					if (folderNode == null) {
						folderNode = new FolderNode(parentElement, FOLDER_NODE_TYPE, LABEL_NAME);
					}
					return ArchipelagoUtils.combine(childrenFromPreviousProvider, folderNode);
				}
				if (parentElement instanceof FolderNode
						&& ((FolderNode) parentElement).getType().equals(FOLDER_NODE_TYPE)) {
					return ArchipelagoUtils.combine(childrenFromPreviousProvider, XadlUtils
							.getAllSubstitutionGroupElementsByTag(xarch,
									(ObjRef) ((FolderNode) parentElement).getParent(), "topLevelElement",
									TOP_LEVEL_ELEMENT_NAME));
				}
				return childrenFromPreviousProvider;
			}

			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}
		};

		this.labelProvider = new IArchipelagoLabelProvider() {

			@Override
			public String getText(Object element, String textFromPreviousProvider) {
				if (element instanceof ObjRef) {
					ObjRef ref = (ObjRef) element;
					if (XadlUtils.isInstanceOf(xarch, ref, ELEMENT_TYPE)) {
						String description = XadlUtils.getName(xarch, ref);
						if (description == null) {
							description = "[No Description]";
						}
						return description;
					}
				}
				return textFromPreviousProvider;
			}

			@Override
			public Image getImage(Object element, Image imageFromPreviousProvider) {
				return imageFromPreviousProvider;
			}

		};

		this.contextMenuFillers = new IArchipelagoTreeContextMenuFiller[] {
				// add new element
				new IArchipelagoTreeContextMenuFiller() {

					@Override
					public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
						if (selectedNodes != null && selectedNodes.length == 1) {
							Object selectedNode = selectedNodes[0];
							if (selectedNode instanceof FolderNode) {
								final FolderNode fn = (FolderNode) selectedNode;
								String fnType = fn.getType();
								if (fnType != null) {
									if (fnType.equals(FOLDER_NODE_TYPE)) {
										IAction newStructureAction = new Action("New " + ELEMENT_LABEL_NAME) {
											@Override
											public void run() {
												ObjRef newObjRef = XadlUtils.create(xarch, ELEMENT_TYPE);
												xarch.set(newObjRef, "id", UIDGenerator.generateUID(ELEMENT_LABEL_NAME)
														+ "-");
												XadlUtils.setName(xarch, newObjRef, "[New " + ELEMENT_LABEL_NAME + "]");
												XArchADTOperations.add("Add " + ELEMENT_LABEL_NAME, xarch,
														(ObjRef) fn.getParent(), XADL_FEATURE_NAME, newObjRef);
											}
										};
										m.add(newStructureAction);
									}
								}
							}
						}
					}
				},
				// remove existing element
				new IArchipelagoTreeContextMenuFiller() {

					@Override
					public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
						if (selectedNodes != null && selectedNodes.length == 1) {
							Object selectedNode = selectedNodes[0];
							if (selectedNode instanceof ObjRef) {
								final ObjRef targetRef = (ObjRef) selectedNode;
								if (xarch.isInstanceOf(targetRef, ELEMENT_TYPE.getEPackage().getNsURI(),
										ELEMENT_TYPE.getName())) {
									IAction removeAction = new Action("Remove") {
										@Override
										public void run() {
											XArchADTOperations.remove("Remove " + ELEMENT_LABEL_NAME, xarch,
													xarch.getParent(targetRef), XADL_FEATURE_NAME, targetRef);
										}
									};
									m.add(removeAction);
								}
							}
						}
					}
				},
				// rename existing element
				new IArchipelagoTreeContextMenuFiller() {

					@Override
					public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
						if (selectedNodes != null && selectedNodes.length == 1) {
							Object selectedNode = selectedNodes[0];
							if (selectedNode instanceof ObjRef) {
								final ObjRef targetRef = (ObjRef) selectedNode;
								if (xarch.isInstanceOf(targetRef, ELEMENT_TYPE.getEPackage().getNsURI(),
										ELEMENT_TYPE.getName())) {
									IAction removeAction = new Action("Edit Name...") {
										@Override
										public void run() {
											ArchipelagoUtils.beginTreeCellEditing(viewer, targetRef);
										}
									};
									m.add(removeAction);
								}
							}
						}
					}
				} };

		this.cellModifiers = new ICellModifier[] { new ICellModifier() {

			@Override
			public boolean canModify(Object element, String property) {
				if (element != null && element instanceof ObjRef) {
					ObjRef targetRef = (ObjRef) element;
					if (XadlUtils.isInstanceOf(xarch, targetRef, ELEMENT_TYPE)) {
						return true;
					}
				}
				return false;
			}

			@Override
			public Object getValue(Object element, String property) {
				if (element instanceof ObjRef) {
					ObjRef targetRef = (ObjRef) element;
					String name = XadlUtils.getName(xarch, targetRef);
					return name;
				}
				return null;
			}

			@Override
			public void modify(Object element, String property, Object value) {
				if (element instanceof ObjRef) {
					ObjRef targetRef = (ObjRef) element;
					if (value != null) {
						String newName = value.toString();
						XArchADTOperations.set("Rename " + ELEMENT_LABEL_NAME, xarch, targetRef, "name", newName);
					}
				}
			}

		} };

		this.doubleClickHandler = new IArchipelagoTreeDoubleClickHandler() {

			@Override
			public void treeNodeDoubleClicked(Object treeNode) {
				if (treeNode instanceof ObjRef) {
					if (xarch.isInstanceOf((ObjRef) treeNode, ELEMENT_TYPE.getEPackage().getNsURI(),
							ELEMENT_TYPE.getName())) {
						setupEditor(AS, AS.get(IXArchADT.class), (ObjRef) treeNode);
					}
				}
			}
		};

		this.dragSourceListener = new DragSourceAdapter() {

			@Override
			public void dragStart(DragSourceEvent event) {
				if (event.data != null && event.data instanceof ObjRef) {
					if (XadlUtils.isInstanceOf(xarch, (ObjRef) event.data, DND_SOURCE)) {
						//For dropping statecharts on states; only allow if we're editing a statechart.
						BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
						IBNAView view = bnaCanvas.getBNAView();
						if (bnaCanvas != null) {
							IBNAModel m = view.getBNAWorld().getBNAModel();
							if (m != null) {
								EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
								String editingXArchID = ept.get(ArchipelagoUtils.XARCH_ID_KEY);
								if (editingXArchID != null) {
									ObjRef editingRef = xarch.getByID(documentRootRef, editingXArchID);
									if (editingRef != null) {
										if (XadlUtils.isInstanceOf(xarch, editingRef, DND_TARGET)) {
											event.doit = true;
											event.detail = DND.DROP_LINK;
										}
									}
								}
							}
						}
					}
				}
			}
		};
	}

	protected void setupEditor(Services services, IXArchADT xarch, ObjRef structureRef) {
		ObjRef documentRootRef = xarch.getDocumentRootRef(structureRef);

		// get and clear editor
		IArchipelagoEditorPane editor = services.get(IArchipelagoEditorPane.class);
		editor.clearEditor();
		Composite parentComposite = editor.getParentComposite();
		FillLayout fl = new FillLayout();
		fl.type = SWT.HORIZONTAL;
		parentComposite.setLayout(fl);

		// use cached world, otherwise create it
		IBNAWorld bnaWorld = null;
		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			bnaWorld = (IBNAWorld) services.get(IArchipelagoTreeNodeDataCache.class).getData(documentRootRef,
					structureRef, FOLDER_NODE_TYPE);
		}
		if (bnaWorld == null) {
			bnaWorld = setupEditor(services, xarch, documentRootRef, structureRef);
			if (services.has(IArchipelagoTreeNodeDataCache.class)) {
				services.get(IArchipelagoTreeNodeDataCache.class).setData(documentRootRef, structureRef,
						FOLDER_NODE_TYPE, bnaWorld);
			}
		}
		final IBNAWorld fbnaWorld = bnaWorld;

		// create main canvas
		final BNACanvas bnaCanvas = new BNACanvas(parentComposite, SWT.V_SCROLL | SWT.H_SCROLL, fbnaWorld);
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
				BNARenderingSettings.setDisplayShadows(bnaCanvas,
						prefs.getBoolean(ArchipelagoConstants.PREF_DISPLAY_SHADOWS));
				GridThing gridThing = (GridThing) fbnaWorld.getBNAModel().getThing(GridThing.class);
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

		// display canvas
		bnaCanvas.pack();
		parentComposite.layout(true);
		ArchipelagoUtils.setBNACanvas(editor, bnaCanvas);
		bnaCanvas.setFocus();

	}

	public static IBNAWorld setupEditor(Services services, IXArchADT xarch, ObjRef documentRootRef, ObjRef structureRef) {

		// this cache is important for handling recursive worlds
		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			IBNAWorld bnaWorld = (IBNAWorld) services.get(IArchipelagoTreeNodeDataCache.class).getData(documentRootRef,
					structureRef, "statecharts");
			if (bnaWorld != null) {
				return bnaWorld;
			}
		}

		IBNAModel bnaModel = new DefaultBNAModel();
		IBNAWorld bnaWorld = new DefaultBNAWorld("", bnaModel);

		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			services.get(IArchipelagoTreeNodeDataCache.class).setData(documentRootRef, structureRef, "statecharts",
					bnaWorld);
		}

		IThingLogicManager logicManager = bnaWorld.getThingLogicManager();
		ProxyLogic logicProxy = logicManager.addThingLogic(new ProxyLogic());

		logicManager.addThingLogic(new SynchronizeHintsLogic(logicProxy.addObject(new XadlHintRepository(xarch))));

		bnaWorld.getBNAModel().addThing(new GridThing(GridThing.class));
		bnaWorld.getBNAModel().addThing(new ShadowThing(null));

		// these logics need to be first

		logicManager.addThingLogic(SnapToGridLogic.class);

		// generic logics -- alphabetized

		logicManager.addThingLogic(ClickSelectionLogic.class);
		logicManager.addThingLogic(new DecorateChangesLogic((IXArchADTVariability) xarch));
		logicManager.addThingLogic(DragMovableLogic.class);
		logicManager.addThingLogic(KeyNudgerLogic.class);
		logicManager.addThingLogic(LifeSapperLogic.class);
		logicManager.addThingLogic(MarqueeSelectionLogic.class);
		logicManager.addThingLogic(MousePanAndZoomLogic.class);
		logicManager.addThingLogic(ReshapeRectangleLogic.class);
		logicManager.addThingLogic(ReshapeSplineLogic.class).addReshapeSplineGuides(
				new XadlReshapeSplineGuide(xarch, Statechart_1_0Package.Literals.TRANSITION,
						Statechart_1_0Package.Literals.PSEUDO_STATE, -1, 0));
		logicManager.addThingLogic(RotatingOffsetLogic.class);
		logicManager.addThingLogic(SplineBreakLogic.class);
		logicManager.addThingLogic(StandardCursorLogic.class);
		logicManager.addThingLogic(new StatechartDropLogic(services, documentRootRef));
		logicManager.addThingLogic(ToolTipLogic.class);

		// menu logics -- order dictates menu order

		logicManager.addThingLogic(new NewElementLogic(xarch, services.get(IResources.class), structureRef));
		logicManager.addThingLogic(EditTextLogic.class);
		//logicManager.addThingLogic(EditFlowLogic.class);
		//logicManager.addThingLogic(StructureAssignMyxGenLogic(xarch));
		logicManager.addThingLogic(new StatechartEditColorLogic(xarch));
		logicManager.addThingLogic(ShowHideTagsLogic.class);
		//logicManager.addThingLogic(FindDialogLogic(ArchipelagoFinder(xarch, services.get(IResources.class))));
		logicManager.addThingLogic(new XadlCopyPasteLogic(xarch, services.get(IArchipelagoEditorPane.class)
				.getActionBars()));
		logicManager.addThingLogic(new RemoveElementLogic(xarch));
		logicManager.addThingLogic(RotaterLogic.class);
		logicManager.addThingLogic(AlignAndDistributeLogic.class);
		logicManager.addThingLogic(RectifyToGridLogic.class);
		//logicManager.addThingLogic(StructureGraphLayoutLogic(xarch, services.get(IResources.class),
		//		services.get(IGraphLayout.class), structureRef));
		logicManager.addThingLogic(ViewAllLogic.class);
		logicManager.addThingLogic(ExportImageLogic.class);

		// xADL mapping logics

		logicManager.addThingLogic(new MapStateLogic(services, xarch, structureRef, "state[@type='state']", //
				new Dimension(6 * 24, 4 * 24), 1));
		logicManager.addThingLogic(new MapInitialStateLogic(services, xarch, structureRef, "state[@type='initial']", //
				new Dimension(4 * 24 / 3, 4 * 24 / 3), 1));
		logicManager.addThingLogic(new MapFinalStateLogic(services, xarch, structureRef, "state[@type='final']", //
				new Dimension(4 * 24 / 3, 4 * 24 / 3), 1));
		logicManager.addThingLogic(new MapTransitionLogic(xarch, structureRef, "transition"));

		// propagate external events logics

		final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
		final IMyxBrick brick = myxRegistry.waitForBrick(ArchipelagoMyxComponent.class);
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTModelListener.class));
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTFileListener.class));
		myxRegistry.map(brick, logicProxy.getProxyForInterface(IXArchADTVariabilityListener.class));

		// these logics need to be last

		return bnaWorld;
	}

}
