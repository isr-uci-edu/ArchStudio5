package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.swt.XadlTreeUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;

public class StructureTreeLabelProvider implements IArchipelagoLabelProvider {
	protected Services AS = null;
	protected final IXArchADT xarch;
	protected final IResources resources;

	public StructureTreeLabelProvider(Services AS) {
		this.AS = AS;
		this.xarch = AS.get(IXArchADT.class);
		this.resources = AS.get(IResources.class);
	}

	@Override
	public String getText(Object element, String textFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, ref, Structure_3_0Package.Literals.STRUCTURE)) {
				String description = XadlUtils.getName(xarch, ref);
				if (description == null) {
					description = "[No Description]";
				}
				return description;
			}
		}
		return textFromPreviousProvider;
	}

	@Override
	public Image getImage(Object element, Image imageFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, ref, Structure_3_0Package.Literals.STRUCTURE)) {
				return XadlTreeUtils.getIconForType(resources, XadlTreeUtils.Type.STRUCTURE);
			}
		}
		return imageFromPreviousProvider;
	}

}
