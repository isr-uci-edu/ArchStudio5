package org.archstudio.archipelago.core;

import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.swt.XadlTreeUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;

public class RootLabelProvider implements IArchipelagoLabelProvider {
	protected ArchipelagoServices AS = null;

	public RootLabelProvider(ArchipelagoServices services) {
		this.AS = services;
	}

	public String getText(Object element, String textFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.DOCUMENT_ROOT)) {
				return AS.xarch.getURI(ref).toString();
			}
			if (XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)) {
				return AS.xarch.getURI(ref).toString();
			}
		}
		return textFromPreviousProvider;
	}

	public Image getImage(Object element, Image imageFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)) {
				return XadlTreeUtils.getIconForType(AS.resources, XadlTreeUtils.Type.DOCUMENTROOT);
			}
		}
		return imageFromPreviousProvider;
	}

}
