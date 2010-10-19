package org.archstudio.archipelago.core;

import org.eclipse.swt.graphics.Image;

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
			if(AS.xarch.isInstanceOf(ref, "org.archstudio.xadl3.xadlcore_3_0.DocumentRoot")){
				return AS.xarch.getURI(ref).toString();
			}
			if(AS.xarch.isInstanceOf(ref, "org.archstudio.xadl3.xadlcore_3_0.XADLType")){
				return AS.xarch.getURI(ref).toString();
			}
		}
		return textFromPreviousProvider;
	}
	
	public Image getImage(Object element, Image imageFromPreviousProvider){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			if(AS.xarch.isInstanceOf(ref, "org.archstudio.xadl3.xadlcore_3_0.XADLType")){
				return XadlTreeUtils.getIconForType(AS.resources, XadlTreeUtils.Type.DOCUMENTROOT);
			}
		}
		return imageFromPreviousProvider;
	}
	
}
