package org.archstudio.archipelago.core.structure;

import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;

public class StructureTreeDragSourceListener implements DragSourceListener {
	protected ArchipelagoServices AS = null;
	protected ObjRef documentRootRef = null;
	
	public StructureTreeDragSourceListener(ArchipelagoServices services, ObjRef documentRootRef){
		this.AS = services;
		this.documentRootRef = documentRootRef;
	}
	
	public void dragStart(DragSourceEvent event){
		if((event.data != null) && (event.data instanceof ObjRef)){
			if(XadlUtils.isInstanceOf(AS.xarch, (ObjRef)event.data, Structure_3_0Package.Literals.STRUCTURE)){
				//For dropping structures on components & connectors; only allow if we're editing a structure.
				BNAComposite bnaComposite = ArchipelagoUtils.getBNAComposite(AS.editor);
				if(bnaComposite != null){
					IBNAView view = bnaComposite.getView();
					if(view != null){
						IBNAModel m = view.getWorld().getBNAModel();
						if(m != null){
							EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
							String editingXArchID = ept.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
							if(editingXArchID != null){
								ObjRef editingRef = AS.xarch.getByID(documentRootRef, editingXArchID);
								if(editingRef != null){
									if(XadlUtils.isInstanceOf(AS.xarch, editingRef, Structure_3_0Package.Literals.STRUCTURE)){
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
	}
	
	public void dragSetData(DragSourceEvent event){
	}
	
	public void dragFinished(DragSourceEvent event){
	}
}
