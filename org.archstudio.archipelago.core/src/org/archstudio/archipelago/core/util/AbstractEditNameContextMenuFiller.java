package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;

public abstract class AbstractEditNameContextMenuFiller implements IArchipelagoTreeContextMenuFiller{
	protected TreeViewer viewer = null;
	protected ArchipelagoServices AS =  null;
	protected ObjRef xArchRef = null;
	
	public AbstractEditNameContextMenuFiller(TreeViewer viewer, ArchipelagoServices AS, ObjRef xArchRef){
		this.viewer = viewer;
		this.AS = AS;
		this.xArchRef = xArchRef;
	}
	
	protected abstract boolean matches(Object node);
	
	public void fillContextMenu(IMenuManager m, Object[] selectedNodes){
		if((selectedNodes != null) && (selectedNodes.length == 1)){
			Object selectedNode = selectedNodes[0];
			if((selectedNode instanceof ObjRef) && matches(selectedNodes[0])){
				final ObjRef targetRef = (ObjRef)selectedNode;
				IAction editNameAction = new Action("Edit Name..."){
					public void run(){
						ArchipelagoUtils.beginTreeCellEditing(viewer, targetRef);
					}
				};
				m.add(editNameAction);
			}
		}
	}
}
