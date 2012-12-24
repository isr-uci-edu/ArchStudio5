package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

public class StructureTreeDragSourceListener implements DragSourceListener {
	protected Services AS = null;
	protected ObjRef documentRootRef = null;
	protected final IXArchADT xarch;
	protected final IArchipelagoEditorPane editor;

	public StructureTreeDragSourceListener(Services AS, ObjRef documentRootRef) {
		this.AS = AS;
		this.documentRootRef = documentRootRef;
		this.xarch = AS.get(IXArchADT.class);
		this.editor = AS.get(IArchipelagoEditorPane.class);
	}

	public void dragStart(DragSourceEvent event) {
		if (event.data != null && event.data instanceof ObjRef) {
			if (XadlUtils.isInstanceOf(xarch, (ObjRef) event.data, Structure_3_0Package.Literals.STRUCTURE)) {
				//For dropping structures on components & connectors; only allow if we're editing a structure.
				BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(editor);
				IBNAView view = bnaCanvas.getBNAView();
				if (bnaCanvas != null) {
					IBNAModel m = view.getBNAWorld().getBNAModel();
					if (m != null) {
						EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
						String editingXArchID = ept.get(ArchipelagoUtils.XARCH_ID_KEY);
						if (editingXArchID != null) {
							ObjRef editingRef = xarch.getByID(documentRootRef, editingXArchID);
							if (editingRef != null) {
								if (XadlUtils.isInstanceOf(xarch, editingRef, Structure_3_0Package.Literals.STRUCTURE)) {
									event.doit = true;
									event.detail = DND.DROP_LINK;
								}
							}
						}
					}
				}
			}
		}
	}

	public void dragSetData(DragSourceEvent event) {
	}

	public void dragFinished(DragSourceEvent event) {
	}
}
