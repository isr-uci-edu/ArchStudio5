package org.archstudio.archipelago.core.structure;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoEditorFocuser;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.FlyToUtils;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

public class StructureEditorFocuser implements IArchipelagoEditorFocuser {
	protected TreeViewer viewer = null;
	protected ArchipelagoServices AS = null;
	protected ObjRef xArchRef = null;

	public StructureEditorFocuser(TreeViewer viewer, ArchipelagoServices services, ObjRef xArchRef) {
		this.viewer = viewer;
		this.AS = services;
		this.xArchRef = xArchRef;
	}

	@Override
	public void focusEditor(String editorName, ObjRef[] refs) {
		//Highlight the tree nodes
		if (refs.length == 0) {
			return;
		}

		List<ObjRef> structureRefList = new ArrayList<ObjRef>();
		for (int i = 0; i < refs.length; i++) {
			String pathString = AS.xarch.getTagsOnlyPathString(refs[i]);
			if (pathString != null) {
				List<ObjRef> ancestors = AS.xarch.getAllAncestors(refs[i]);
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
		StructureEditorSupport.setupEditor(AS, structureRef);
		if (SystemUtils.nullEquals(structureRef, ref)) {
			return;
		}
		BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(AS.editor);
		IBNAView view = bnaCanvas;
		if (bnaCanvas != null) {
			IBNAModel structureModel = view.getBNAWorld().getBNAModel();
			String xArchID = XadlUtils.getID(AS.xarch, ref);
			if (xArchID != null) {
				IThing t = ArchipelagoUtils.findThing(structureModel, xArchID);
				if (t != null) {
					IThing glassThing = ArchipelagoUtils.getGlassThing(structureModel, t);
					if (glassThing != null) {
						Point p = BNAUtils.getCentralPoint(glassThing);
						if (p != null) {
							FlyToUtils.flyTo(view, p.x, p.y);
							ArchipelagoUtils.pulseNotify(structureModel, glassThing);
						}
					}
				}
			}
		}
	}
}
