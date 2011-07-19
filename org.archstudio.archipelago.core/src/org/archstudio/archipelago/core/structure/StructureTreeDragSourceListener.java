package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

public class StructureTreeDragSourceListener implements DragSourceListener {
	protected ArchipelagoServices AS = null;
	protected ObjRef documentRootRef = null;

	public StructureTreeDragSourceListener(ArchipelagoServices services, ObjRef documentRootRef) {
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		if (event.data != null && event.data instanceof ObjRef) {
			if (XadlUtils.isInstanceOf(AS.xarch, (ObjRef) event.data, Structure_3_0Package.Literals.STRUCTURE)) {
				//For dropping structures on components & connectors; only allow if we're editing a structure.
				BNACanvas bnaCanvas = ArchipelagoUtils.getBNACanvas(AS.editor);
				IBNAView view = bnaCanvas;
				if (bnaCanvas != null) {
					IBNAModel m = view.getBNAWorld().getBNAModel();
					if (m != null) {
						EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
						String editingXArchID = ept.get(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
						if (editingXArchID != null) {
							ObjRef editingRef = AS.xarch.getByID(documentRootRef, editingXArchID);
							if (editingRef != null) {
								if (XadlUtils.isInstanceOf(AS.xarch, editingRef,
										Structure_3_0Package.Literals.STRUCTURE)) {
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

	@Override
	public void dragSetData(DragSourceEvent event) {
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
	}
}
