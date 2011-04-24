package org.archstudio.archipelago.core;

import org.eclipse.swt.graphics.Image;

import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xadlswt.common.XadlTreeUtils;
import org.archstudio.xarchadt.common.ObjRef;

public class RootLabelProvider implements IArchipelagoLabelProvider{
	protected ArchipelagoServices AS = null;
	
	public RootLabelProvider(ArchipelagoServices services){
		this.AS = services;
	}
	
	public String getText(Object element, String textFromPreviousProvider){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			if(XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.DOCUMENT_ROOT)){
				return AS.xarch.getURI(ref).toString();
			}
			if(XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)){
				return AS.xarch.getURI(ref).toString();
			}
		}
		return textFromPreviousProvider;
	}
	
	public Image getImage(Object element, Image imageFromPreviousProvider){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			if(XadlUtils.isInstanceOf(AS.xarch, ref, Xadlcore_3_0Package.Literals.XADL_TYPE)){
				return XadlTreeUtils.getIconForType(AS.resources, XadlTreeUtils.Type.DOCUMENTROOT);
			}
		}
		return imageFromPreviousProvider;
	}
	
}
