package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.util.AbstractEditNameCellModifier;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;

public class StructureEditDescriptionCellModifier extends AbstractEditNameCellModifier {

	public StructureEditDescriptionCellModifier(ArchipelagoServices services, ObjRef xArchRef) {
		super(services, xArchRef);
	}

	public boolean canModify(Object element, String property) {
		if ((element != null) && (element instanceof ObjRef)) {
			ObjRef targetRef = (ObjRef) element;
			if (XadlUtils.isInstanceOf(AS.xarch, targetRef, Structure_3_0Package.Literals.STRUCTURE)) {
				return true;
			}
		}
		return false;
	}
}
