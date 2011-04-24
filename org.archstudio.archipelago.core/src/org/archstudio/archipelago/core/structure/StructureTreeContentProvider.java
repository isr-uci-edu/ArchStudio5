package org.archstudio.archipelago.core.structure;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xarchadt.common.IXArchADTQuery;
import org.archstudio.xarchadt.common.ObjRef;

public class StructureTreeContentProvider implements IArchipelagoTreeContentProvider{
	protected static final String FOLDER_NODE_TYPE = "STRUCTURES";
	protected FolderNode structureFolderNode = null;
	
	protected ArchipelagoServices AS = null;
	
	protected final ObjRef documentRootRef;
	
	public StructureTreeContentProvider(ArchipelagoServices services, ObjRef documentRootRef){
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}
	
	protected boolean isRootElement(Object ref){
		if(ref instanceof ObjRef){
			String tagsPath = AS.xarch.getTagsOnlyPathString((ObjRef)ref);
			if(tagsPath.equals("")){
				return true;
			}
		}
		return false;
	}
	
	protected boolean isXADLElement(Object ref){
		if(ref instanceof ObjRef){
			String tagsPath = AS.xarch.getTagsOnlyPathString((ObjRef)ref);
			if(tagsPath.equals("xADL")){
				return true;
			}
		}
		return false;
	}
	
	protected boolean isStructureFolderNode(Object fn){
		if(fn instanceof FolderNode){
			return ((FolderNode)fn).getType().equals(FOLDER_NODE_TYPE);
		}
		return false;
	}
	
	protected boolean isTargetNode(Object ref){
		if(ref instanceof ObjRef){
			String refPath = AS.xarch.getTagsOnlyPathString((ObjRef)ref);
			if(refPath.equals("xADL/structure")){
				return true;
			}
		}
		return false;
	}
	
	protected static List<ObjRef> getStructureRefs(IXArchADTQuery xarch, ObjRef documentRootRef){
		ObjRef xADLRef = (ObjRef)xarch.get(documentRootRef, "xADL");
		if(xADLRef == null){
			return Collections.emptyList();
		}
		return XadlUtils.getAllSubstitutionGroupElementsByTag(xarch, xADLRef, "topLevelElement", "structure");
	}
	
	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider){
		if(isRootElement(parentElement) || isXADLElement(parentElement)){
			if(structureFolderNode == null){
				structureFolderNode = new FolderNode((ObjRef)parentElement, FOLDER_NODE_TYPE, "Structures");
			}
			return ArchipelagoUtils.combine(childrenFromPreviousProvider, structureFolderNode);
		}
		else if(isStructureFolderNode(parentElement)){
			List<ObjRef> structureRefs = getStructureRefs(AS.xarch, documentRootRef);
			return ArchipelagoUtils.combine(childrenFromPreviousProvider, structureRefs);
		}
		return childrenFromPreviousProvider;
	}
	
	public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider){
		if(isRootElement(element)){
			return true;
		}
		if(isXADLElement(element)){
			return true;
		}
		else if(isStructureFolderNode(element)){
			return getChildren(element, Collections.emptyList()).size() > 0;
		}
		return hasChildrenFromPreviousProvider;
	}
	
	public Object getParent(Object element, Object parentFromPreviousProvider){
		if(isTargetNode(element)){
			return structureFolderNode;
		}
		if(isStructureFolderNode(element)){
			return ((FolderNode)element).getParent();
		}
		return parentFromPreviousProvider;
	}
	
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput){
	}

	public void dispose(){
	}
	
}
