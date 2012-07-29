package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoTreeContextMenuFiller;
import org.archstudio.archipelago.core.util.XArchOperation;
import org.archstudio.myx.fw.Services;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureNewStructureContextMenuFiller implements IArchipelagoTreeContextMenuFiller {
	protected final TreeViewer viewer;
	protected final IXArchADT xarch;
	protected final ObjRef documentRootRef;

	public StructureNewStructureContextMenuFiller(TreeViewer viewer, Services AS, ObjRef documentRootRef) {
		this.viewer = viewer;
		this.xarch = AS.get(IXArchADT.class);
		this.documentRootRef = documentRootRef;
	}

	public void fillContextMenu(IMenuManager m, Object[] selectedNodes) {
		if ((selectedNodes != null) && (selectedNodes.length == 1)) {
			Object selectedNode = selectedNodes[0];
			if (selectedNode instanceof FolderNode) {
				FolderNode fn = (FolderNode) selectedNode;
				String fnType = fn.getType();
				if (fnType != null) {
					if (fnType.equals(StructureTreeContentProvider.FOLDER_NODE_TYPE)) {
						IAction newStructureAction = new Action("New Structure") {
							public void run() {
								createNewStructure();
							}
						};
						m.add(newStructureAction);
					}
				}
			}
		}
	}

	protected void createNewStructure() {
		ObjRef newStructureRef = XadlUtils.create(xarch, Structure_3_0Package.Literals.STRUCTURE);
		String newID = UIDGenerator.generateUID("structure");

		xarch.set(newStructureRef, "id", newID);
		XadlUtils.setName(xarch, newStructureRef, "[New Structure]");

		ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
		if (xADLRef != null) {
			XArchOperation.add("Add Structure", xarch, xADLRef, "topLevelElement", newStructureRef, true);
		}
	}

}
