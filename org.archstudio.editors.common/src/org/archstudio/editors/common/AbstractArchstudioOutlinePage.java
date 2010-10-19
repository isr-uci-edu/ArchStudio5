package org.archstudio.editors.common;

import org.archstudio.resources.common.IResources;
import org.archstudio.swtutils.IMenuFiller;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.ObjRef;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public abstract class AbstractArchstudioOutlinePage extends ContentOutlinePage implements IFocusEditorListener{
	protected IXArchADT xarch;
	protected IResources resources = null;
	
	protected ObjRef documentRootRef = null;
	
	protected boolean hasPulldownMenu = false;
	protected boolean hasContextMenu = false;

	public AbstractArchstudioOutlinePage(IXArchADT xarch, ObjRef documentRootRef, IResources resources, boolean hasPulldownMenu, boolean hasContextMenu){
		this.xarch = xarch;
		this.documentRootRef = documentRootRef;
		this.resources = resources;
		this.hasPulldownMenu = hasPulldownMenu;
		this.hasContextMenu = hasContextMenu;
	}
	
	public void init(IPageSite pageSite){
		super.init(pageSite);
	}
	
	public void createControl(Composite parent){
		super.createControl(parent);
		if(documentRootRef == null)
			return;
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(createViewContentProvider());
		viewer.setLabelProvider(createViewLabelProvider());
		// viewer.addSelectionChangedListener(this);
		viewer.setInput(getSite());

		// Do Pulldown Menu
		if(hasPulldownMenu){
			IMenuManager menu = getSite().getActionBars().getMenuManager();
			IAction[] actions = createPulldownMenuItems();
			for(int i = 0; i < actions.length; i++){
				menu.add(actions[i]);
			}
		}

		// Do Context Menu
		if(hasContextMenu){
			SWTWidgetUtils.setupContextMenu("#PopupMenu", getTreeViewer().getControl(), getSite(), new IMenuFiller(){
				public void fillMenu(IMenuManager m){
					fillContextMenu(m);
				}
			});
		}
	}
	
	protected abstract ITreeContentProvider createViewContentProvider();
	protected abstract ILabelProvider createViewLabelProvider();
	
	protected IAction[] createPulldownMenuItems(){ 
		return new IAction[0]; 
	}
	
	protected void fillContextMenu(IMenuManager m){}
	
	public void updateOutlinePage(){
		if(getTreeViewer() == null) return;
		if(getTreeViewer().getTree() == null) return;
		if(getTreeViewer().getTree().isDisposed()) return;
		Object[] expandedElements = getTreeViewer().getExpandedElements();
		getTreeViewer().refresh(true);
		getTreeViewer().setExpandedElements(expandedElements);
	}

	public Object[] getSelectedObjects(){
		ISelection selection = getSelection();
		if(selection instanceof IStructuredSelection){
			IStructuredSelection ss = (IStructuredSelection)selection;
			Object[] nodes = ss.toArray();
			return nodes;
		}
		else{
			return new Object[0];
		}
	}
	
	public abstract void focusEditor(String editorName, ObjRef[] refs);

}
