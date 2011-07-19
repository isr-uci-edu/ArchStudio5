package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadlswt.XadlTreeUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;

public class StructureTreeLabelProvider implements IArchipelagoLabelProvider {
	protected ArchipelagoServices AS = null;

	public StructureTreeLabelProvider(ArchipelagoServices services) {
		this.AS = services;
	}

	public String getText(Object element, String textFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(AS.xarch, ref, Structure_3_0Package.Literals.STRUCTURE)) {
				String description = XadlUtils.getName(AS.xarch, ref);
				if (description == null) {
					description = "[No Description]";
				}
				return description;
			}
		}
		return textFromPreviousProvider;
	}

	public Image getImage(Object element, Image imageFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(AS.xarch, ref, Structure_3_0Package.Literals.STRUCTURE)) {
				return XadlTreeUtils.getIconForType(AS.resources, XadlTreeUtils.Type.STRUCTURE);
			}
		}
		return imageFromPreviousProvider;
	}

}
