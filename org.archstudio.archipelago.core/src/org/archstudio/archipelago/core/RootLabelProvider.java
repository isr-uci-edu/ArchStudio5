package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.swt.XadlTreeUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;

public class RootLabelProvider implements IArchipelagoLabelProvider {
	protected Services AS = null;
	protected final IXArchADT xarch;
	protected final IResources resources;

	public RootLabelProvider(Services AS) {
		this.AS = AS;
		this.xarch = AS.get(IXArchADT.class);
		this.resources = AS.get(IResources.class);
	}

	@Override
	public String getText(Object element, String textFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, ref, Xadlcore_3_0Package.Literals.DOCUMENT_ROOT)) {
				return xarch.getURI(ref).toString();
			}
			if (XadlUtils.isInstanceOf(xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)) {
				return xarch.getURI(ref).toString();
			}
		}
		return textFromPreviousProvider;
	}

	@Override
	public Image getImage(Object element, Image imageFromPreviousProvider) {
		if (element instanceof ObjRef) {
			ObjRef ref = (ObjRef) element;
			if (XadlUtils.isInstanceOf(xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)) {
				return XadlTreeUtils.getIconForType(resources, XadlTreeUtils.Type.DOCUMENTROOT);
			}
		}
		return imageFromPreviousProvider;
	}

}
