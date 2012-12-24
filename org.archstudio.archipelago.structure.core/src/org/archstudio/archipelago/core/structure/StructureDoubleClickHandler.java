package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.IArchipelagoTreeDoubleClickHandler;
import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class StructureDoubleClickHandler implements IArchipelagoTreeDoubleClickHandler {

	Services services;

	public StructureDoubleClickHandler(Services services) {
		this.services = services;
	}

	protected boolean isTargetNode(Object ref) {
		if (ref instanceof ObjRef) {
			String refPath = services.get(IXArchADT.class).getTagsOnlyPathString((ObjRef) ref);
			if (refPath.equals("xADL/structure")) {
				return true;
			}
		}
		return false;
	}

	public void treeNodeDoubleClicked(Object treeNode) {
		if (isTargetNode(treeNode)) {
			ObjRef structureRef = (ObjRef) treeNode;
			StructureEditorSupport.setupEditor(services, services.get(IXArchADT.class), structureRef);
		}
	}

}
