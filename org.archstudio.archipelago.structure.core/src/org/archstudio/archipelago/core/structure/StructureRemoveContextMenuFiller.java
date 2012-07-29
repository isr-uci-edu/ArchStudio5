package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.util.AbstractRemoveContextMenuFiller;
import org.archstudio.archipelago.core.util.XArchADTOperations;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureRemoveContextMenuFiller extends AbstractRemoveContextMenuFiller {

	protected final IXArchADT xarch;

	public StructureRemoveContextMenuFiller(TreeViewer viewer, Services AS, ObjRef xArchRef) {
		super(viewer, AS, xArchRef);
		this.xarch = AS.get(IXArchADT.class);
	}

	@Override
	protected boolean matches(Object node) {
		if (node != null) {
			if (node instanceof ObjRef) {
				ObjRef targetRef = (ObjRef) node;
				if (XadlUtils.isInstanceOf(xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void remove(ObjRef targetRef) {
		if (targetRef != null) {
			if (XadlUtils.isInstanceOf(xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
				ObjRef parentRef = xarch.getParent(targetRef);
				if (parentRef != null) {
					IXArchADTTypeMetadata type = xarch.getTypeMetadata(parentRef);
					String elementName = xarch.getContainingFeatureName(targetRef);
					switch (type.getFeatures().get(elementName).getType()) {
					case ATTRIBUTE:
					case ELEMENT_SINGLE:
						XArchADTOperations.set("Remove Structure", xarch, parentRef, elementName, null);
						break;
					case ELEMENT_MULTIPLE:
						XArchADTOperations.remove("Remove Structure", xarch, parentRef, elementName, targetRef);
						break;
					}
				}
				return;
			}
		}
		super.remove(targetRef);
	}
}
