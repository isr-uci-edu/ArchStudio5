package org.archstudio.eclipse.ui.views;

import org.archstudio.eclipse.ui.IFocusEditorListener;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.IMenuFiller;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.operations.UndoRedoActionGroup;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

@SuppressWarnings("deprecation")
public abstract class AbstractArchStudioOutlinePage extends ContentOutlinePage implements IFocusEditorListener {
	protected IXArchADT xarch;
	protected IResources resources = null;

	protected ObjRef documentRootRef = null;

	protected boolean hasPulldownMenu = false;
	protected boolean hasContextMenu = false;

	public AbstractArchStudioOutlinePage(IXArchADT xarch, ObjRef documentRootRef, IResources resources,
			boolean hasPulldownMenu, boolean hasContextMenu) {
		this.xarch = xarch;
		this.documentRootRef = documentRootRef;
		this.resources = resources;
		this.hasPulldownMenu = hasPulldownMenu;
		this.hasContextMenu = hasContextMenu;
	}

	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);

		// enable undo/redo
		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		UndoRedoActionGroup undoRedo = new UndoRedoActionGroup(new IWorkbenchPartSite() {

			public IWorkbenchPage getPage() {
				return getSite().getPage();
			}

			public ISelectionProvider getSelectionProvider() {
				throw new UnsupportedOperationException();
			}

			public Shell getShell() {
				throw new UnsupportedOperationException();
			}

			public IWorkbenchWindow getWorkbenchWindow() {
				return getSite().getWorkbenchWindow();
			}

			public void setSelectionProvider(ISelectionProvider provider) {
				throw new UnsupportedOperationException();
			}

			@SuppressWarnings("rawtypes")
			public Object getAdapter(Class adapter) {
				throw new UnsupportedOperationException();
			}

			@SuppressWarnings("rawtypes")
			public Object getService(Class api) {
				throw new UnsupportedOperationException();
			}

			@SuppressWarnings("rawtypes")
			public boolean hasService(Class api) {
				throw new UnsupportedOperationException();
			}

			public String getId() {
				throw new UnsupportedOperationException();
			}

			public String getPluginId() {
				throw new UnsupportedOperationException();
			}

			public String getRegisteredName() {
				throw new UnsupportedOperationException();
			}

			public void registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider) {
				throw new UnsupportedOperationException();
			}

			public void registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider) {
				throw new UnsupportedOperationException();
			}

			public IKeyBindingService getKeyBindingService() {
				throw new UnsupportedOperationException();
			}

			public IWorkbenchPart getPart() {
				return getSite().getPage().getActivePart();
			}

		}, undoContext, true);
		undoRedo.fillActionBars(actionBars);
		actionBars.updateActionBars();
	}

	public void init(IPageSite pageSite) {
		super.init(pageSite);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		if (documentRootRef == null) {
			return;
		}
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(createViewContentProvider());
		viewer.setLabelProvider(createViewLabelProvider());
		// viewer.addSelectionChangedListener(this);
		viewer.setInput(getSite());

		// Do Pulldown Menu
		if (hasPulldownMenu) {
			IMenuManager menu = getSite().getActionBars().getMenuManager();
			IAction[] actions = createPulldownMenuItems();
			for (IAction action : actions) {
				menu.add(action);
			}
		}

		// Do Context Menu
		if (hasContextMenu) {
			SWTWidgetUtils.setupContextMenu("#PopupMenu", getTreeViewer().getControl(), getSite(), new IMenuFiller() {
				public void fillMenu(IMenuManager m) {
					fillContextMenu(m);
				}
			});
		}
	}

	protected abstract ITreeContentProvider createViewContentProvider();

	protected abstract ILabelProvider createViewLabelProvider();

	protected IAction[] createPulldownMenuItems() {
		return new IAction[0];
	}

	protected void fillContextMenu(IMenuManager m) {
	}

	public void updateOutlinePage() {
		if (getTreeViewer() == null) {
			return;
		}
		if (getTreeViewer().getTree() == null) {
			return;
		}
		if (getTreeViewer().getTree().isDisposed()) {
			return;
		}
		Object[] expandedElements = getTreeViewer().getExpandedElements();
		getTreeViewer().refresh(true);
		getTreeViewer().setExpandedElements(expandedElements);
	}

	public Object[] getSelectedObjects() {
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object[] nodes = ss.toArray();
			return nodes;
		}
		else {
			return new Object[0];
		}
	}

	public abstract void focusEditor(String editorName, ObjRef[] refs);

}
