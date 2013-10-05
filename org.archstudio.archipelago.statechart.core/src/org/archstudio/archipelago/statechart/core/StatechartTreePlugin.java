package org.archstudio.archipelago.statechart.core;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago.core.AbstractArchipelagoTreePlugin;
import org.archstudio.archipelago.core.ArchipelagoConstants;
import org.archstudio.archipelago.core.ArchipelagoMyxComponent;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
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
import org.archstudio.bna.IThing;
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
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.MousePanAndZoomLogic;
import org.archstudio.bna.logics.navigating.ViewAllLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.bna.utils.FlyToUtils;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
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

		this.editorFocuser = new IArchipelagoEditorFocuser() {

			@Override
			public void focusEditor(String editorName, ObjRef[] refs) {
				focusEditor(refs);
			}

			@Override
			public void focusEditor(ObjRef[] refs) {
				if (refs.length == 0) {
					return;
				}

				// highlight the tree node
				List<ObjRef> structureRefList = new ArrayList<ObjRef>();
				for (int i = 0; i < refs.length; i++) {
					String pathString = xarch.getTagsOnlyPathString(refs[i]);
					if (pathString != null) {
						List<ObjRef> ancestors = xarch.getAllAncestors(refs[i]);
						if (pathString.startsWith("xADL/" + TOP_LEVEL_ELEMENT_NAME)) {
							ObjRef structureRef = ancestors.get(ancestors.size() - 3);
							if (i == 0) {
								focusOnElement(structureRef, refs[i]);
							}
							structureRefList.add(structureRef);
						}
					}
				}
				if (structureRefList.size() > 0) {
					IStructuredSelection ss = ArchipelagoUtils.addToSelection(viewer.getSelection(),
							structureRefList.toArray());
					viewer.setSelection(ss, true);
				}
			}

			private void focusOnElement(ObjRef structureRef, ObjRef objRef) {
				setupEditor(AS, xarch, structureRef);
				if (SystemUtils.nullEquals(structureRef, objRef)) {
					return;
				}
				BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
				if (bnaCanvas != null) {
					IBNAView view = bnaCanvas.getBNAView();
					IBNAModel structureModel = view.getBNAWorld().getBNAModel();
					String xArchID = XadlUtils.getID(xarch, objRef);
					if (xArchID != null) {
						IThing t = ArchipelagoUtils.findThing(structureModel, xArchID);
						if (t != null) {
							IThing rootThing = Assemblies.getRoot(structureModel, t);
							if (rootThing != null) {
								Point p = BNAUtils.getCentralPoint(rootThing);
								if (p != null) {
									FlyToUtils.flyTo(view, p);
									ArchipelagoUtils.pulseNotify(structureModel, rootThing);
								}
							}
						}
					}
				}
			}
		};
	}

	protected void setupEditor(Services services, IXArchADT xarch, ObjRef structureRef) {

		// if the editor is already set up, return
		{
			IArchipelagoEditorPane editor = services.get(IArchipelagoEditorPane.class);
			if (editor != null) {
				BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
				if (bnaCanvas != null) {
					IBNAModel model = bnaCanvas.getBNAView().getBNAWorld().getBNAModel();
					EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(model);
					if (ept.has(IHasObjRef.OBJREF_KEY, structureRef)) {
						return;
					}
				}
			}
		}

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

		IBNAModel model = new DefaultBNAModel();
		IBNAWorld world = new DefaultBNAWorld("", model);

		if (services.has(IArchipelagoTreeNodeDataCache.class)) {
			services.get(IArchipelagoTreeNodeDataCache.class).setData(documentRootRef, structureRef, "statecharts",
					world);
		}

		IThingLogicManager logics = world.getThingLogicManager();
		ProxyLogic proxyLogic = logics.addThingLogic(ProxyLogic.class);

		logics.addThingLogic(new SynchronizeHintsLogic(world, proxyLogic.addObject(new XadlHintRepository(xarch))));

		world.getBNAModel().addThing(new GridThing());
		world.getBNAModel().addThing(new ShadowThing());

		// these logics need to be first

		logics.addThingLogic(SnapToGridLogic.class);

		// generic logics -- alphabetized

		logics.addThingLogic(ClickSelectionLogic.class);
		logics.addThingLogic(new DecorateChangesLogic(world, (IXArchADTVariability) xarch));
		logics.addThingLogic(DragMovableLogic.class);
		logics.addThingLogic(KeyNudgerLogic.class);
		logics.addThingLogic(LifeSapperLogic.class);
		logics.addThingLogic(MarqueeSelectionLogic.class);
		logics.addThingLogic(MousePanAndZoomLogic.class);
		logics.addThingLogic(ReshapeRectangleLogic.class);
		logics.addThingLogic(ReshapeSplineLogic.class).addReshapeSplineGuides(
				new XadlReshapeSplineGuide(xarch, Statechart_1_0Package.Literals.TRANSITION,
						Statechart_1_0Package.Literals.PSEUDO_STATE, -1, 0));
		logics.addThingLogic(RotatingOffsetLogic.class);
		logics.addThingLogic(SplineBreakLogic.class);
		logics.addThingLogic(StandardCursorLogic.class);
		logics.addThingLogic(new StatechartDropLogic(world, services, documentRootRef));
		logics.addThingLogic(ToolTipLogic.class);
		logics.addThingLogic(HighlightLogic.class);

		// menu logics -- order dictates menu order

		logics.addThingLogic(new NewElementLogic(world, xarch, services.get(IResources.class), structureRef));
		logics.addThingLogic(EditTextLogic.class);
		//logicManager.addThingLogic(EditFlowLogic.class);
		//logicManager.addThingLogic(StructureAssignMyxGenLogic(xarch));
		logics.addThingLogic(new StatechartEditColorLogic(world, xarch));
		logics.addThingLogic(ShowHideTagsLogic.class);
		//logicManager.addThingLogic(FindDialogLogic(ArchipelagoFinder(xarch, services.get(IResources.class))));
		logics.addThingLogic(new XadlCopyPasteLogic(world, xarch, services.get(IArchipelagoEditorPane.class)
				.getActionBars()));
		logics.addThingLogic(new RemoveElementLogic(world, xarch));
		logics.addThingLogic(RotaterLogic.class);
		logics.addThingLogic(AlignAndDistributeLogic.class);
		logics.addThingLogic(RectifyToGridLogic.class);
		logics.addThingLogic(new ExportImportGexf(world));
		logics.addThingLogic(new ExportImportDot(world));
		//logicManager.addThingLogic(StructureGraphLayoutLogic(xarch, services.get(IResources.class),
		//		services.get(IGraphLayout.class), structureRef));
		logics.addThingLogic(ViewAllLogic.class);
		logics.addThingLogic(ExportImageLogic.class);

		// xADL mapping logics

		logics.addThingLogic(new MapStateLogic(world, services, xarch, structureRef, "state[@type='state']", //
				new Dimension(6 * 24, 4 * 24), 1));
		logics.addThingLogic(new MapInitialStateLogic(world, services, xarch, structureRef, "state[@type='initial']", //
				new Dimension(4 * 24 / 3, 4 * 24 / 3)));
		logics.addThingLogic(new MapFinalStateLogic(world, services, xarch, structureRef, "state[@type='final']", //
				new Dimension(4 * 24 / 3, 4 * 24 / 3)));
		logics.addThingLogic(new MapTransitionLogic(world, xarch, structureRef, "transition"));

		// propagate external events logics

		final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
		final IMyxBrick brick = myxRegistry.waitForBrick(ArchipelagoMyxComponent.class);
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTModelListener.class));
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTFileListener.class));
		myxRegistry.map(brick, proxyLogic.getProxyForInterface(IXArchADTVariabilityListener.class));

		// these logics need to be last

		return world;
	}

}
