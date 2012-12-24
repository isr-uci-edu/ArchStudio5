package org.archstudio.archipelago.core.structure;

import java.util.Collections;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.FolderNode;
import org.archstudio.archipelago.core.IArchipelagoTreeContentProvider;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.Viewer;

public class StructureTreeContentProvider implements IArchipelagoTreeContentProvider {

	protected static final String FOLDER_NODE_TYPE = "STRUCTURES";
	protected FolderNode structureFolderNode = null;

	protected Services AS = null;
	protected final ObjRef documentRootRef;
	protected final IXArchADT xarch;

	public StructureTreeContentProvider(Services AS, ObjRef documentRootRef) {
		this.AS = AS;
		this.documentRootRef = documentRootRef;
		this.xarch = AS.get(IXArchADT.class);
	}

	protected boolean isRootElement(Object ref) {
		if (ref instanceof ObjRef) {
			String tagsPath = xarch.getTagsOnlyPathString((ObjRef) ref);
			if (tagsPath.equals("")) {
				return true;
			}
		}
		return false;
	}

	protected boolean isXADLElement(Object ref) {
		if (ref instanceof ObjRef) {
			String tagsPath = xarch.getTagsOnlyPathString((ObjRef) ref);
			if (tagsPath.equals("xADL")) {
				return true;
			}
		}
		return false;
	}

	protected boolean isStructureFolderNode(Object fn) {
		if (fn instanceof FolderNode) {
			return ((FolderNode) fn).getType().equals(FOLDER_NODE_TYPE);
		}
		return false;
	}

	protected boolean isTargetNode(Object ref) {
		if (ref instanceof ObjRef) {
			String refPath = xarch.getTagsOnlyPathString((ObjRef) ref);
			if (refPath.equals("xADL/structure")) {
				return true;
			}
		}
		return false;
	}

	protected static List<ObjRef> getStructureRefs(IXArchADTQuery xarch, ObjRef documentRootRef) {
		ObjRef xADLRef = (ObjRef) xarch.get(documentRootRef, "xADL");
		if (xADLRef == null) {
			return Collections.emptyList();
		}
		return XadlUtils.getAllSubstitutionGroupElementsByTag(xarch, xADLRef, "topLevelElement", "structure");
	}

	public List<? extends Object> getChildren(Object parentElement, List<? extends Object> childrenFromPreviousProvider) {
		if (isRootElement(parentElement) || isXADLElement(parentElement)) {
			if (structureFolderNode == null) {
				structureFolderNode = new FolderNode(parentElement, FOLDER_NODE_TYPE, "Structures");
			}
			return ArchipelagoUtils.combine(childrenFromPreviousProvider, structureFolderNode);
		}
		else if (isStructureFolderNode(parentElement)) {
			List<ObjRef> structureRefs = getStructureRefs(xarch, documentRootRef);
			return ArchipelagoUtils.combine(childrenFromPreviousProvider, structureRefs);
		}
		return childrenFromPreviousProvider;
	}

	public boolean hasChildren(Object element, boolean hasChildrenFromPreviousProvider) {
		if (isRootElement(element)) {
			return true;
		}
		if (isXADLElement(element)) {
			return true;
		}
		else if (isStructureFolderNode(element)) {
			return getChildren(element, Collections.emptyList()).size() > 0;
		}
		return hasChildrenFromPreviousProvider;
	}

	public Object getParent(Object element, Object parentFromPreviousProvider) {
		if (isTargetNode(element)) {
			return structureFolderNode;
		}
		if (isStructureFolderNode(element)) {
			return ((FolderNode) element).getParent();
		}
		return parentFromPreviousProvider;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

}
