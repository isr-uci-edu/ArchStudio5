package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IXArchEventHandlerLogic;
import org.archstudio.xarchadt.common.IXArchADTModelListener;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;

public class StructureXArchEventHandler implements IXArchADTModelListener{

	protected ArchipelagoServices AS = null;
	
	public StructureXArchEventHandler(ArchipelagoServices AS){
		this.AS = AS;
	}
	
	public synchronized void handleXArchADTModelEvent(XArchADTModelEvent evt){
		BNAComposite currentlyEditingComposite = ArchipelagoUtils.getBNAComposite(AS.editor);
		if(currentlyEditingComposite != null){
			IBNAView view = currentlyEditingComposite.getView();
			if(view != null){
				IBNAWorld world = view.getWorld();
				if(world != null){
					String worldID = world.getID();
					if(worldID.equals("structure")){
						for(IThingLogic tl : world.getThingLogicManager().getAllThingLogics()){
							if(tl instanceof IXArchEventHandlerLogic){
								((IXArchEventHandlerLogic)tl).handleXArchADTModelEvent(evt, world);
							}
						}
					}
				}
			}
		}
	}
	
}
