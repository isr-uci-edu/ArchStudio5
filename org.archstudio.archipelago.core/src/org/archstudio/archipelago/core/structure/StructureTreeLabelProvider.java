package org.archstudio.archipelago.core.structure;

import org.eclipse.swt.graphics.Image;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.IArchipelagoLabelProvider;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadlswt.common.XadlTreeUtils;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureTreeLabelProvider implements IArchipelagoLabelProvider{
	protected ArchipelagoServices AS = null;
	
	public StructureTreeLabelProvider(ArchipelagoServices services){
		this.AS = services;
	}
	
	public String getText(Object element, String textFromPreviousProvider){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			if(AS.xarch.isInstanceOf(ref, "org.archstudio.xadl3.structure_3_0.Structure")){
				String description = XadlUtils.getName(AS.xarch, ref);
				if(description == null){
					description = "[No Description]";
				}
				return description;
			}
		}
		return textFromPreviousProvider;
	}
	
	public Image getImage(Object element, Image imageFromPreviousProvider){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			if(AS.xarch.isInstanceOf(ref, "org.archstudio.xadl3.structure_3_0.Structure")){
				return XadlTreeUtils.getIconForType(AS.resources, XadlTreeUtils.Type.STRUCTURE);
			}
		}
		return imageFromPreviousProvider;
	}
	
}
