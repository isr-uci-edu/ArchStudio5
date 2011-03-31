package org.archstudio.archipelago.core.structure;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureNewStructureContextMenuFiller implements IArchipelagoTreeContextMenuFiller{
	protected TreeViewer viewer = null;
	protected ArchipelagoServices AS =  null;
	protected ObjRef documentRootRef = null;
	
	public StructureNewStructureContextMenuFiller(TreeViewer viewer, ArchipelagoServices AS, ObjRef documentRootRef){
		this.viewer = viewer;
		this.AS = AS;
		this.documentRootRef = documentRootRef;
	}
	
	
	public void fillContextMenu(IMenuManager m, Object[] selectedNodes){
		if((selectedNodes != null) && (selectedNodes.length == 1)){
			Object selectedNode = selectedNodes[0];
			if(selectedNode instanceof FolderNode){
				FolderNode fn = (FolderNode)selectedNode;
				String fnType = fn.getType();
				if(fnType != null){
					if(fnType.equals(StructureTreeContentProvider.FOLDER_NODE_TYPE)){
						IAction newStructureAction = new Action("New Structure"){
							public void run(){
								createNewStructure();
							}
						};
						m.add(newStructureAction);
					}
				}
			}
		}
	}

	protected void createNewStructure(){
		ObjRef newStructureRef = XadlUtils.create(AS.xarch, Structure_3_0Package.Literals.STRUCTURE);
		String newID = UIDGenerator.generateUID("structure");
		
		AS.xarch.set(newStructureRef, "id", newID);
		XadlUtils.setName(AS.xarch, newStructureRef, "[New Structure]");
		
		ObjRef xADLRef = (ObjRef)AS.xarch.get(documentRootRef, "xADL");
		if(xADLRef != null){
			AS.xarch.add(xADLRef, "topLevelElement", newStructureRef);
		}
	}

}
