package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.util.AbstractEditNameCellModifier;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;

public class StructureEditDescriptionCellModifier extends AbstractEditNameCellModifier {

	public StructureEditDescriptionCellModifier(Services AS, ObjRef xArchRef) {
		super(AS, xArchRef);
	}

	@Override
	public boolean canModify(Object element, String property) {
		if (element != null && element instanceof ObjRef) {
			ObjRef targetRef = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
				return true;
			}
		}
		return false;
	}
}
