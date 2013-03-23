package org.archstudio.aimlauncher.core;

import java.util.EnumSet;
import java.util.List;

import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.resources.IResources;
import org.archstudio.xadl.swt.XadlTreeContentProvider;
import org.archstudio.xadl.swt.XadlTreeLabelProvider;
import org.archstudio.xadl.swt.XadlTreeUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;

public class AIMLauncherOutlinePage extends AbstractArchStudioOutlinePage {

	public AIMLauncherOutlinePage(IXArchADT xarch, ObjRef documentRootRef, IResources resources) {
		super(xarch, documentRootRef, resources, false, false);
	}

	@Override
	protected ITreeContentProvider createViewContentProvider() {
		return new XadlTreeContentProvider(xarch, documentRootRef, EnumSet.of(XadlTreeUtils.Type.STRUCTURE));
	}

	@Override
	protected ILabelProvider createViewLabelProvider() {
		return new XadlTreeLabelProvider(xarch, resources);
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		getTreeViewer().expandToLevel(2);
	}

	public ObjRef[] getSelectedRefs() {
		Object[] selectedObjects = getSelectedObjects();
		if (selectedObjects == null) {
			return new ObjRef[0];
		}
		ObjRef[] refs = new ObjRef[selectedObjects.length];
		System.arraycopy(selectedObjects, 0, refs, 0, selectedObjects.length);
		return refs;
	}

	private ObjRef normalize(ObjRef ref) {
		String pathString = xarch.getTagsOnlyPathString(ref);
		if (!pathString.startsWith("xADL")) {
			return xarch.getDocumentRootRef(ref);
		}
		if (pathString.equals("xADL") || pathString.equals("xADL/structure")) {
			return ref;
		}
		ObjRef parentRef = xarch.getParent(ref);
		if (parentRef == null) {
			return xarch.getDocumentRootRef(ref);
		}
		return normalize(parentRef);
	}

	@Override
	public void focusEditor(ObjRef[] refs) {
		if (refs.length > 0) {
			ObjRef ref = refs[0];
			ref = normalize(ref);

			List<ObjRef> ancestors = xarch.getAllAncestors(ref);
			for (int j = ancestors.size() - 1; j >= 1; j--) {
				getTreeViewer().expandToLevel(ancestors.get(j), 1);
			}
			IStructuredSelection ss = new StructuredSelection(new ObjRef[] { ref });
			getTreeViewer().setSelection(ss, true);
		}
	}
}
