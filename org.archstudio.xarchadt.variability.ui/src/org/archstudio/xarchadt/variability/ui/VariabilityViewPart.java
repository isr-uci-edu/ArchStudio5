package org.archstudio.xarchadt.variability.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.archstudio.archipelago.core.ObjRefTransfer;
import org.archstudio.eclipse.ui.views.AbstractArchStudioView;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.DelayedExecuteOnceThread;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.variability_3_0.ChangeSet;
import org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.variability.ChangeSetTransform;
import org.archstudio.xarchadt.variability.IXArchADTVariability;
import org.archstudio.xarchadt.variability.VariabilityUtils;
import org.archstudio.xarchadt.variability.ui.actions.AddChangeSetAction;
import org.archstudio.xarchadt.variability.ui.actions.IHasXArchRef;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.INullSelectionListener;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class VariabilityViewPart extends AbstractArchStudioView<VariabilityMyxComponent> implements ISelectionProvider,
		IXArchADTModelListener, IPartListener, ISelectionChangedListener, INullSelectionListener, Listener {

	List<ISelectionChangedListener> selectionChangedListeners = Lists.newCopyOnWriteArrayList();
	List<ObjRef> selectedRefs = new ArrayList<ObjRef>();
	IAction overviewModeAction = null;

	protected static GridData excludeGridData() {
		GridData d = new GridData();
		d.exclude = true;
		return d;
	}

	protected static void addCellEditor(TreeViewer viewer, CellEditor editor) {
		CellEditor[] editors = viewer.getCellEditors();
		if (editors == null) {
			editors = new CellEditor[0];
		}
		CellEditor[] newEditors = new CellEditor[editors.length + 1];
		System.arraycopy(editors, 0, newEditors, 0, editors.length);
		newEditors[newEditors.length - 1] = editor;
		viewer.setCellEditors(newEditors);
	}

	protected static void addColumnProperty(TreeViewer viewer, String property) {
		String[] properties = (String[]) viewer.getColumnProperties();
		if (properties == null) {
			properties = new String[0];
		}
		String[] newProperties = new String[properties.length + 1];
		System.arraycopy(properties, 0, newProperties, 0, properties.length);
		newProperties[newProperties.length - 1] = property;
		viewer.setColumnProperties(newProperties);
	}

	protected static int indexOf(Item[] items, Object element) {
		for (int i = 0; i < items.length; i++) {
			if (SystemUtils.nullEquals(items[i].getData(), element)) {
				return i;
			}
		}
		return -1;
	}

	protected IXArchADTVariability xarch;
	protected TreeViewer changeSetViewer = null;
	protected ChangeSetSorter changeSetSorter = null;
	protected ChangeSetLabelProvider changeSetLabelProvider = null;
	protected Composite notificationComposite = null;
	protected Object ignoreEventsLock = new Object();
	protected int ignoreChangeSetSelectionEvents = 0;

	public VariabilityViewPart() {
		super(VariabilityMyxComponent.class);
	}

	protected Collection<Object> myxMapped = new ArrayList<Object>();

	protected void myxMap(Object o) {
		if (o != null) {
			myxMapped.add(o);
			MyxRegistry.getSharedInstance().map(brick, o);
		}
	}

	public void handleEvent(Event event) {
		if (hideSelection) {
			event.detail &= ~SWT.SELECTED;
			event.detail &= ~SWT.FOCUSED;
			event.detail &= ~SWT.HOT;
			event.detail |= SWT.BACKGROUND;
			event.detail |= SWT.FOREGROUND;

			if (event.item instanceof TreeItem) {
				Tree tree = (Tree) event.widget;
				TreeItem treeItem = (TreeItem) event.item;
				int column = event.index;
				TreeColumn treeColumn = tree.getColumn(column);
				CellLabelProvider labelProvider = changeSetViewer.getLabelProvider(column);
				Object data = treeColumn.getData();
				if (data == null)
					data = treeItem.getData();
				if (labelProvider instanceof IColorProvider) {
					IColorProvider colorProvider = (IColorProvider) labelProvider;
					Color fc = colorProvider.getForeground(data);
					if (fc != null)
						event.gc.setForeground(fc);
					Color bc = colorProvider.getBackground(data);
					if (bc != null) {
						event.gc.setBackground(bc);
						event.gc.fillRectangle(event.gc.getClipping());
					}
				}
			}
		}
	}

	protected void createMainMyxPartControl(Composite parent) {
		changeSetViewer = new TreeViewer(parent, SWT.MULTI | SWT.FULL_SELECTION) {

			boolean needsLabelUpdate = false;
			DelayedExecuteOnceThread labelUpdater = null;

			@Override
			protected synchronized void handleLabelProviderChanged(LabelProviderChangedEvent event) {
				/*
				 * We catch calls to this method and then perform a single call
				 * at a later time.
				 */
				if (labelUpdater == null) {
					labelUpdater = new DelayedExecuteOnceThread(250, new Runnable() {

						public void run() {
							SWTWidgetUtils.async(changeSetViewer, new Runnable() {

								public void run() {
									superHandleLabelProviderChanged(new LabelProviderChangedEvent(changeSetViewer
											.getLabelProvider()));
								}
							});
						}
					});
					labelUpdater.start();
				}
				needsLabelUpdate = true;
				labelUpdater.execute();
			}

			private void superHandleLabelProviderChanged(LabelProviderChangedEvent event) {
				if (needsLabelUpdate) {
					needsLabelUpdate = false;
					super.handleLabelProviderChanged(event);
				}
			}
		};

		changeSetViewer.getTree().addListener(SWT.EraseItem, this);
		//changeSetViewer.getTree().addListener(SWT.PaintItem, this);

		changeSetViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		changeSetViewer.setUseHashlookup(true);
		changeSetViewer.setContentProvider(new ChangeSetContentProvider(xarch));
		changeSetViewer.setComparator(changeSetSorter = new ChangeSetSorter(xarch));
		changeSetViewer.setLabelProvider(changeSetLabelProvider = new ChangeSetLabelProvider(changeSetViewer, xarch));
		changeSetViewer.setCellModifier(new ChangeSetCellModifier(changeSetViewer, xarch));
		changeSetViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				synchronized (ignoreEventsLock) {
					if (ignoreChangeSetSelectionEvents > 0) {
						ignoreChangeSetSelectionEvents--;
						return;
					}
				}
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					ObjRef csRef = null;
					IStructuredSelection ss = (IStructuredSelection) selection;
					if (ss.size() == 1) {
						csRef = (ObjRef) ss.getFirstElement();
					}
					ObjRef xArchRef = (ObjRef) changeSetViewer.getInput();
					if (xArchRef != null) {
						if (xarch.isChangeSetsEnabled(xArchRef)) {
							xarch.setActiveChangeSet(xArchRef, csRef);
						}
					}
				}
			}
		});
		changeSetViewer.addOpenListener(new IOpenListener() {

			public void open(OpenEvent event) {
				for (Object element : ((IStructuredSelection) event.getSelection()).toArray()) {
					changeSetViewer.getCellModifier().modify(element, "Apply",
							!xarch.getAppliedChangeSets((ObjRef) changeSetViewer.getInput()).contains(element));
				}
			}
		});
		changeSetViewer.addSelectionChangedListener(this);
		changeSetViewer.addDragSupport(DND.DROP_MOVE, new Transfer[] { ObjRefTransfer.getInstance() },
				new DragSourceAdapter() {

					ObjRef[] data = null;

					@SuppressWarnings("unchecked")
					@Override
					public void dragStart(DragSourceEvent event) {
						ISelection selection = changeSetViewer.getSelection();
						if (selection instanceof IStructuredSelection) {
							data = (ObjRef[]) ((IStructuredSelection) selection).toList().toArray(new ObjRef[0]);
						}
						event.doit &= data != null;

						// it seems like this should be done automatically!?
						if (changeSetViewer.isCellEditorActive()) {
							for (CellEditor e : changeSetViewer.getCellEditors()) {
								if (e != null) {
									e.deactivate();
								}
							}
						}
					}

					@Override
					public void dragSetData(DragSourceEvent event) {
						if (ObjRefTransfer.getInstance().isSupportedType(event.dataType) && data != null) {
							event.data = data.clone();
						}
					}

					@Override
					public void dragFinished(DragSourceEvent event) {
						if (!event.doit) {
							return;
						}
					}
				});
		changeSetViewer.addDropSupport(DND.DROP_MOVE, new Transfer[] { ObjRefTransfer.getInstance() },
				new ViewerDropAdapter(changeSetViewer) {

					@Override
					protected int determineLocation(DropTargetEvent event) {
						if (!(event.item instanceof Item)) {
							return ViewerDropAdapter.LOCATION_NONE;
						}
						Item item = (Item) event.item;
						Point coordinates = new Point(event.x, event.y);
						coordinates = changeSetViewer.getControl().toControl(coordinates);
						if (item != null) {
							Rectangle bounds = getBounds(item);
							if (bounds == null) {
								return ViewerDropAdapter.LOCATION_NONE;
							}
							if (coordinates.y - (bounds.y + bounds.height / 2) < 0) {
								return ViewerDropAdapter.LOCATION_BEFORE;
							}
							else {
								return ViewerDropAdapter.LOCATION_AFTER;
							}
						}
						return ViewerDropAdapter.LOCATION_ON;
					}

					@Override
					public boolean validateDrop(Object target, int operation, TransferData transferType) {
						return ObjRefTransfer.getInstance().isSupportedType(transferType);
					}

					@Override
					public boolean performDrop(Object data) {

						if (data instanceof ObjRef[] && ((ObjRef[]) data).length > 0) {
							ObjRef[] dataRefs = (ObjRef[]) data;
							if (XadlUtils.isInstanceOf(xarch, dataRefs[0], Variability_3_0Package.Literals.CHANGE_SET)) {
								int newIndex = indexOf(changeSetViewer.getTree().getItems(), getCurrentTarget());
								if (newIndex >= 0) {
									if (getCurrentLocation() == ViewerDropAdapter.LOCATION_AFTER)
										newIndex++;
									move(xarch, xarch.getParent(dataRefs[0]), dataRefs, -newIndex - 1);
									return true;
								}
							}
						}
						return false;
					}

					private void move(IXArchADTVariability xarch, ObjRef variabilityRef, ObjRef[] changeSetRefs,
							int index) {
						Variability variability = XArchADTProxy.proxy(xarch, variabilityRef);
						List<ChangeSet> newChangeSets = Lists.newArrayList(variability.getChangeSet());
						List<ChangeSet> movedChangeSets = XArchADTProxy.proxy(xarch, changeSetRefs);

						// reorder change sets
						newChangeSets.add(index < 0 ? newChangeSets.size() + index + 1 : index, null);
						newChangeSets.removeAll(movedChangeSets);
						newChangeSets.addAll(newChangeSets.indexOf(null), Lists.reverse(movedChangeSets));
						newChangeSets.remove(null);

						variability.getChangeSet().clear();
						variability.getChangeSet().addAll(newChangeSets);

						// apply change sets in new order
						List<ChangeSet> appliedChangeSets = variability.getAppliedChangeSets();
						Set<ChangeSet> appliedChangeSetsSet = Sets.newHashSet(appliedChangeSets);
						newChangeSets.retainAll(appliedChangeSetsSet);
						xarch.applyChangeSets(xarch.getDocumentRootRef(variabilityRef),
								XArchADTProxy.unproxy(newChangeSets));
					}
				});

		// Tree tree = changeSetViewer.getTree();
		// tree.setHeaderVisible(true);
		// tree.setLinesVisible(true);
		// //tree.setLayout(new TableLayout());
		//
		// TreeViewerColumn vColumn;
		// TreeColumn tColumn;
		//
		// vColumn = new TreeViewerColumn(changeSetViewer, SWT.LEFT);
		// vColumn.setEditingSupport(new
		// ChangeSetColumnEditorSupport(changeSetViewer, xarch));
		// vColumn.setLabelProvider(new ChangeSetColumnLabelProvider(xarch));
		// tColumn = vColumn.getColumn();
		// tColumn.setText("Change Set");
		// tColumn.setAlignment(SWT.LEFT);
		// tColumn.setMoveable(false);
		// tColumn.setResizable(true);
		// tColumn.setWidth(20);

		Tree tree = changeSetViewer.getTree();
		TreeColumn column;
		CellEditor editor;
		TableLayout layout = new TableLayout();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		tree.setLayout(layout);

		/*
		 * Feature in Windows. The first column in a windows table reserves room
		 * for a check box. The fix is to have this column with a width of zero
		 * to hide it.
		 */
		column = new TreeColumn(tree, SWT.CENTER);
		column.setText("Windows first column fix");
		column.setImage(null);
		column.setResizable(false);
		column.setAlignment(SWT.CENTER);
		layout.addColumnData(new ColumnPixelData(1, column.getResizable()));
		editor = null;
		addCellEditor(changeSetViewer, editor);
		addColumnProperty(changeSetViewer, column.getText());

		column = new TreeColumn(tree, SWT.CENTER);
		column.setText("");
		//column.setImage(XArchCSActivator.getDefault().getImageRegistry().get("res/icons/applied.gif"));
		column.setResizable(false);
		column.setAlignment(SWT.CENTER);
		layout.addColumnData(new ColumnPixelData(20, column.getResizable()));
		editor = new CheckboxCellEditor(tree);
		addCellEditor(changeSetViewer, editor);
		addColumnProperty(changeSetViewer, "Apply");

		column = new TreeColumn(tree, SWT.CENTER);
		column.setText("");
		//column.setImage(XArchCSActivator.getDefault().getImageRegistry().get("res/icons/explicit.gif"));
		column.setResizable(false);
		column.setAlignment(SWT.CENTER);
		layout.addColumnData(new ColumnPixelData(20, column.getResizable()));
		editor = new CheckboxCellEditor(tree);
		addCellEditor(changeSetViewer, editor);
		addColumnProperty(changeSetViewer, "View");

		column = new TreeColumn(tree, SWT.LEFT);
		column.setText("Change Set");
		column.setResizable(true);
		column.setAlignment(SWT.LEFT);
		layout.addColumnData(new ColumnWeightData(1, column.getResizable()));
		editor = new TextCellEditor(tree);
		addCellEditor(changeSetViewer, editor);
		addColumnProperty(changeSetViewer, column.getText());

		ObjRef xArchRef = null;

		IEditorPart editorPart = getSite().getPage().getActiveEditor();
		if (editorPart != null && editorPart.getEditorInput() instanceof IURIEditorInput) {
			IURIEditorInput uriEditorInput = (IURIEditorInput) editorPart.getEditorInput();
			xArchRef = xarch.getDocumentRootRef(URI.createURI(uriEditorInput.getURI().toString()));
		}

		setInput(xArchRef);

		getViewSite().getActionBars().getToolBarManager().add(new AddChangeSetAction(xarch));
		getViewSite().getActionBars().updateActionBars();

		MenuManager contextMenuManager = new MenuManager();
		contextMenuManager.setRemoveAllWhenShown(true);
		contextMenuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				final ObjRef changeSetRef = (ObjRef) ((IStructuredSelection) changeSetViewer.getSelection())
						.getFirstElement();
				if (XadlUtils.isInstanceOf(xarch, changeSetRef,
						Variability_3_0Package.Literals.TRANSFORM_CHANGE_SET_OF_CHANGES)) {
					manager.add(new Action("Apply Transform") {
						@Override
						public void run() {
							applyTransform(changeSetRef);
						}
					});
				}
				else {
					Action a = new Action("Apply Transform") {
						public void run() {
						}
					};
					a.setEnabled(false);
					manager.add(a);
				}
			}
		});
		Menu menu = contextMenuManager.createContextMenu(changeSetViewer.getControl());
		changeSetViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenuManager, changeSetViewer);

		/*
		 * This needs to be here since we always want to update the change sets
		 * viewer before the relationships viewer.
		 */
		myxMap(this);
		myxMap(changeSetViewer.getContentProvider());
		myxMap(changeSetViewer.getLabelProvider());
		myxMap(changeSetViewer.getSorter());
		getViewSite().setSelectionProvider(this);

		IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();

		overviewModeAction = new Action("Overview Mode", IAction.AS_CHECK_BOX) {

			@Override
			public void run() {
				ObjRef xArchRef = (ObjRef) changeSetViewer.getInput();
				if (xArchRef != null) {
					xarch.setOverviewModeEnabled(xArchRef, !xarch.isOverviewModeEnabled(xArchRef));
					overviewModeAction.setChecked(xarch.isOverviewModeEnabled(xArchRef));
				}
			}
		};
		menuManager.add(overviewModeAction);

		//		IAction diffToExternalFile = new Action("(Experimental) Create Diff to External File") {
		//
		//			@Override
		//			public void run() {
		//				FileDialog fileDialog = new FileDialog(changeSetViewer.getControl().getShell(), SWT.OPEN);
		//				fileDialog.setFilterExtensions(new String[] { "*.xml" });
		//				fileDialog.setFilterNames(new String[] { "XML Files (*.xml)" });
		//				fileDialog.setText("Select File");
		//				fileDialog.open();
		//				String fileName = fileDialog.getFileName();
		//				String filePath = fileDialog.getFilterPath();
		//				if (fileName != null && !"".equals(fileName.trim())) {
		//					try {
		//						ObjRef targetXArchRef = xarch.parseFromFile(filePath + java.io.File.separator + fileName);
		//						ObjRef sourceXArchRef = (ObjRef) changeSetViewer.getInput();
		//						xarch.diffToExternalFile(sourceXArchRef, targetXArchRef);
		//					}
		//					catch (Exception e) {
		//						MessageDialog.openError(changeSetViewer.getControl().getShell(), "Error", e.getMessage());
		//					}
		//				}
		//			}
		//		};
		//		menuManager.add(diffToExternalFile);

		// IAction diffFromExternalFile = new
		// Action("Create Diff from External File"){
		//
		// @Override
		// public void run(){
		// FileDialog fileDialog = new
		// FileDialog(changeSetViewer.getControl().getShell(), SWT.OPEN);
		// fileDialog.setFilterExtensions(new String[]{"*.xml"});
		// fileDialog.setFilterNames(new String[]{"XML Files (*.xml)"});
		// fileDialog.open();
		// String fileName = fileDialog.getFileName();
		// String filePath = fileDialog.getFilterPath();
		// if(fileName != null && !"".equals(fileName.trim())){
		// try{
		// ObjRef targetXArchRef = xarch.parseFromFile(filePath +
		// java.io.File.separator + fileName);
		// ObjRef sourceXArchRef = (ObjRef)changeSetViewer.getInput();
		// xarch.diffFromExternalFile(sourceXArchRef, targetXArchRef);
		// }
		// catch(Exception e){
		// String[] labels = {"Ok"};
		// MessageDialog dialog = new
		// MessageDialog(changeSetViewer.getControl().getShell(), "Error", null,
		// e.getMessage(), MessageDialog.ERROR, labels, 0);
		// dialog.open();
		// }
		// }
		// }
		// };
		// menuManager.add(diffFromExternalFile);
	}

	private void applyTransform(ObjRef transformChangeSetRef) {

		JavaTransformChangeSetOfChanges javaTransformChangeSet = XArchADTProxy.proxy(xarch, transformChangeSetRef);
		ObjRef documentRootRef = xarch.getDocumentRootRef(transformChangeSetRef);

		/*
		 * There are three possibilities for how this is run: (1) as a plug-in
		 * project in the current workspace, (2) as a java project in the
		 * current workspace, and (3) as an installed plugin in the current
		 * runtime environment.
		 * 
		 * TODO: We only support (3) right now...
		 */

		try {
			Bundle b = null;
			for(Bundle b2 : Activator.getContext().getBundles()){
				if(b2.getSymbolicName().equals(javaTransformChangeSet.getBundle())){
					b = b2;
					break;
				}
			}
			@SuppressWarnings("unchecked")
			final Class<ChangeSetTransform> c = (Class<ChangeSetTransform>) b.loadClass(javaTransformChangeSet.getClass_());
			VariabilityUtils.updateDynamicChangeSet(xarch, documentRootRef, transformChangeSetRef,
					new ChangeSetTransform() {

						@Override
						public void transform(IXArchADT xarch, ObjRef documentRootRef) {
							try {
								c.newInstance().transform(xarch, documentRootRef);
							}
							catch (Throwable t) {
								throw new RuntimeException(t);
							}
						}
					});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createPartControl(Composite parent) {
		xarch = brick.xarch;

		parent.setLayout(new GridLayout());

		notificationComposite = new Composite(parent, SWT.BORDER);

		notificationComposite.setLayoutData(excludeGridData());
		notificationComposite.setVisible(false);

		createMainMyxPartControl(parent);

		getSite().getWorkbenchWindow().getPartService().addPartListener(this);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);

		updateStatus();
	}

	protected boolean hideSelection = false;

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		//		hideSelection = false;
		//		if (this != part) {
		//			ObjRef[] selectedRefs = HasObjRefUtil.getObjRefs(selection);
		//			if (selectedRefs.length <= 1) {
		//				ObjRef selectedRef = selectedRefs.length > 0 ? selectedRefs[0] : null;
		//				hideSelection = true;
		//				changeSetLabelProvider.setChangeColors(selectedRef);
		//				return;
		//			}
		//			changeSetLabelProvider.setChangeColors(null);
		//		}
	}

	@Override
	public void dispose() {
		for (Object o : myxMapped) {
			MyxRegistry.getSharedInstance().unmap(brick, o);
		}

		getSite().getPage().removePartListener(this);

		super.dispose();
	}

	@Override
	public void setFocus() {
		changeSetViewer.getControl().setFocus();
	}

	//	public void handleXArchChangeSetEvent(final XArchChangeSetEvent evt) {
	//		if (evt.getEventType() == XArchChangeSetEvent.ChangeSetEventType.UPDATED_ACTIVE_CHANGE_SET) {
	//			SWTWidgetUtils.async(changeSetViewer, new Runnable() {
	//
	//				public void run() {
	//					if (equalz(evt.getXArchRef(), changeSetViewer.getInput())) {
	//						ObjRef activeChangeSetRef = evt.getActiveChangeSet();
	//						StructuredSelection selection = StructuredSelection.EMPTY;
	//						if (activeChangeSetRef != null) {
	//							selection = new StructuredSelection(activeChangeSetRef);
	//						}
	//						ignoreChangeSetSelectionEvents++;
	//						changeSetViewer.setSelection(selection);
	//					}
	//				}
	//			});
	//		}
	//		if (evt.getEventType() == XArchChangeSetEvent.ChangeSetEventType.UPDATED_ENABLED) {
	//			SWTWidgetUtils.async(changeSetViewer, new Runnable() {
	//
	//				public void run() {
	//					if (equalz(evt.getXArchRef(), changeSetViewer.getInput())) {
	//						updateStatus();
	//					}
	//				}
	//			});
	//		}
	//	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (changeSetSorter != null) {
			changeSetSorter.handleXArchADTModelEvent(evt);
		}
	}

	protected void setInput(ObjRef xArchRef) {
		if (!SystemUtils.nullEquals(changeSetViewer.getInput(), xArchRef)) {
			changeSetViewer.setInput(xArchRef);
			changeSetLabelProvider.refresh();
			updateStatus();
		}
	}

	public void partActivated(final IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			if (editorPart.getEditorInput() instanceof IURIEditorInput) {
				IURIEditorInput uriEditorInput = (IURIEditorInput) editorPart.getEditorInput();
				setInput(xarch.getDocumentRootRef(URI.createURI(uriEditorInput.getURI().toString())));
			}
		}
	}

	public void partDeactivated(IWorkbenchPart part) {
	}

	public void partClosed(final IWorkbenchPart part) {
		if (part instanceof IEditorPart) {
			IEditorPart editorPart = (IEditorPart) part;
			if (editorPart.getEditorInput() instanceof IURIEditorInput) {
				IURIEditorInput uriEditorInput = (IURIEditorInput) editorPart.getEditorInput();
				ObjRef xArchRef = xarch.getDocumentRootRef(URI.createURI(uriEditorInput.getURI().toString()));
				if (xArchRef == null || xArchRef.equals(changeSetViewer.getInput())) {
					setInput(null);
				}
			}
		}
		else {
			setInput(null);
		}
	}

	public void partBroughtToTop(IWorkbenchPart part) {
	}

	public void partOpened(IWorkbenchPart part) {
	}

	protected void updateStatus() {
		boolean enabled = false;

		for (Control c : notificationComposite.getChildren()) {
			c.dispose();
		}
		ObjRef xArchRef = (ObjRef) changeSetViewer.getInput();
		if (xArchRef == null) {
			notificationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			notificationComposite.setVisible(true);

			notificationComposite.setLayout(new GridLayout());
			notificationComposite.setBackground(notificationComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

			Label message = new Label(notificationComposite, SWT.CENTER | SWT.WRAP);
			message.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
			message.setText("Open a xADL document to use change sets.");
			message.setBackground(notificationComposite.getBackground());
			message.setForeground(message.getDisplay().getSystemColor(SWT.COLOR_BLACK));

			// notificationComposite.setLayoutData(excludeGridData());
			// notificationComposite.setVisible(false);

		}
		else if (!xarch.isChangeSetsEnabled(xArchRef)) {
			notificationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
			notificationComposite.setVisible(true);

			notificationComposite.setLayout(new GridLayout());
			notificationComposite.setBackground(notificationComposite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

			Label message = new Label(notificationComposite, SWT.CENTER | SWT.WRAP);
			message.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));
			message.setText("Change sets not enabled for this document");
			message.setBackground(notificationComposite.getBackground());
			message.setForeground(message.getDisplay().getSystemColor(SWT.COLOR_BLACK));

			final Button enableButton = new Button(notificationComposite, SWT.PUSH);
			enableButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
			enableButton.setText("Enable Change Sets");
			enableButton.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent event) {
					final ObjRef xArchRef = (ObjRef) changeSetViewer.getInput();
					if (xArchRef != null) {
						try {
							new ProgressMonitorDialog(changeSetViewer.getControl().getShell()).run(true, true,
									new IRunnableWithProgress() {
										public void run(final IProgressMonitor monitor) {
											monitor.beginTask(
													"Creating a baseline change set for the current document...",
													IProgressMonitor.UNKNOWN);
											xarch.setChangeSetsEnabled(xArchRef, true);
											monitor.done();
											SWTWidgetUtils.async(enableButton, new Runnable() {
												@Override
												public void run() {
													updateStatus();
												}
											});
										}
									});
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		else {
			enabled = true;
			notificationComposite.setLayoutData(excludeGridData());
			notificationComposite.setVisible(false);
			ObjRef activeChangeSetRef = xarch.getActiveChangeSet(xArchRef);
			++ignoreChangeSetSelectionEvents;
			changeSetViewer.setSelection(activeChangeSetRef != null ? new StructuredSelection(activeChangeSetRef)
					: StructuredSelection.EMPTY);
			if (overviewModeAction != null)
				overviewModeAction.setChecked(xarch.isOverviewModeEnabled(xArchRef));
		}

		changeSetViewer.getControl().setEnabled(enabled);
		for (IContributionItem item : getViewSite().getActionBars().getToolBarManager().getItems()) {
			if (item instanceof ActionContributionItem) {
				IAction action = ((ActionContributionItem) item).getAction();
				if (action instanceof IHasXArchRef) {
					((IHasXArchRef) action).setXArchRef(enabled ? xArchRef : null);
				}
			}
		}

		notificationComposite.layout();
		notificationComposite.getParent().layout();
	}

	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	public ISelection getSelection() {
		return new StructuredSelection(selectedRefs);
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		// TODO Auto-generated method stub

	}

	protected void fireSelectionChangedEvent(ISelection selection) {
		SelectionChangedEvent evt = new SelectionChangedEvent(this, selection);
		for (ISelectionChangedListener l : selectionChangedListeners) {
			l.selectionChanged(evt);
		}
	}

	public void selectionChanged(SelectionChangedEvent event) {
		Iterator<?> iterator = ((IStructuredSelection) event.getSelection()).iterator();
		if (iterator != null) {
			selectedRefs.clear();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				if (obj != null && obj instanceof ObjRef) {
					ObjRef ref = (ObjRef) obj;
					selectedRefs.add(ref);
				}
			}
			fireSelectionChangedEvent(new StructuredSelection(selectedRefs));
		}
	}

}
