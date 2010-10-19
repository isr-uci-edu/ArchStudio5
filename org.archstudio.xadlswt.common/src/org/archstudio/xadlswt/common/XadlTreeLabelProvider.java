package org.archstudio.xadlswt.common;

import org.archstudio.resources.common.ArchStudioCommonResources;
import org.archstudio.resources.common.IResources;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.ObjRef;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class XadlTreeLabelProvider extends LabelProvider implements ILabelProvider{

	protected IXArchADT xarch = null;
	protected IResources resources = null;
	
	public XadlTreeLabelProvider(IXArchADT xarch, IResources resources){
		this.xarch = xarch;
		this.resources = resources;
		ArchStudioCommonResources.init(resources);
	}
	
	public String getText(Object element){
		if(element == null) return "null";
		
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			XadlTreeUtils.Type type = XadlTreeUtils.getType(xarch, ref);
			if(type.equals(XadlTreeUtils.Type.UNKNOWN)){
				return "[Unknown Element]";
			}
			else if(type.equals(XadlTreeUtils.Type.DOCUMENTROOT)){
				URI uri = xarch.getURI(ref);
				if(uri == null) return "null";
				return uri.toString();
			}
			else if(type.equals(XadlTreeUtils.Type.XADLELEMENT)){
				URI uri = xarch.getURI(ref);
				if(uri == null) return "null";
				return uri.toString();
			}
			else{
				String description = XadlUtils.getName(xarch, ref);
				if(description == null){
					String id = XadlUtils.getID(xarch, ref);
					if(id != null){
						description = "[id=" + id + "]";
					}
					description = "[No Identifier]";
				}
				return description;
			}
		}
		return super.getText(element);
	}
	
	public Image getImage(Object element){
		if(element instanceof ObjRef){
			ObjRef ref = (ObjRef)element;
			XadlTreeUtils.Type type = XadlTreeUtils.getType(xarch, ref);
			Image image = XadlTreeUtils.getIconForType(resources, type);
			if(image != null){
				return image;
			}
		}
		return super.getImage(element);
	}
	
}
