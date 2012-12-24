package org.archstudio.archipelago.core.structure;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.FlyToUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Point;

public class StructureEditorFocuser implements IArchipelagoEditorFocuser {
	protected TreeViewer viewer = null;
	protected Services AS = null;
	protected ObjRef xArchRef = null;
	protected final IXArchADT xarch;
	protected final IArchipelagoEditorPane editor;

	public StructureEditorFocuser(TreeViewer viewer, Services AS, ObjRef xArchRef) {
		this.viewer = viewer;
		this.AS = AS;
		this.xArchRef = xArchRef;
		this.xarch = AS.get(IXArchADT.class);
		this.editor = AS.get(IArchipelagoEditorPane.class);
	}

	@Override
	public void focusEditor(String editorName, ObjRef[] refs) {
		//Highlight the tree nodes
		if (refs.length == 0) {
			return;
		}

		List<ObjRef> structureRefList = new ArrayList<ObjRef>();
		for (int i = 0; i < refs.length; i++) {
			String pathString = xarch.getTagsOnlyPathString(refs[i]);
			if (pathString != null) {
				List<ObjRef> ancestors = xarch.getAllAncestors(refs[i]);
				if (pathString.startsWith("xADL/structure")) {
					ObjRef structureRef = ancestors.get(ancestors.size() - 3);
					if (i == 0) {
						focusOnElement(structureRef, refs[i]);
					}
					structureRefList.add(structureRef);
				}
			}
		}
		if (structureRefList.size() > 0) {
			IStructuredSelection ss = ArchipelagoUtils
					.addToSelection(viewer.getSelection(), structureRefList.toArray());
			viewer.setSelection(ss, true);
		}

	}

	protected void focusOnElement(ObjRef structureRef, ObjRef ref) {
		StructureEditorSupport.setupEditor(AS, xarch, structureRef);
		if (SystemUtils.nullEquals(structureRef, ref)) {
			return;
		}
		BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
		IBNAView view = bnaCanvas.getBNAView();
		if (bnaCanvas != null) {
			IBNAModel structureModel = view.getBNAWorld().getBNAModel();
			String xArchID = XadlUtils.getID(xarch, ref);
			if (xArchID != null) {
				IThing t = ArchipelagoUtils.findThing(structureModel, xArchID);
				if (t != null) {
					IThing glassThing = Assemblies.getAssemblyWithRootOrPart(structureModel, t);
					if (glassThing != null) {
						Point p = BNAUtils.getCentralPoint(glassThing);
						if (p != null) {
							FlyToUtils.flyTo(view, p);
							ArchipelagoUtils.pulseNotify(structureModel, glassThing);
						}
					}
				}
			}
		}
	}
}
