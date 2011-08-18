package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IXArchEventHandlerLogic;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.XArchADTModelEvent;

public class StructureXArchEventHandler implements IXArchADTModelListener {

	protected ArchipelagoServices AS = null;

	public StructureXArchEventHandler(ArchipelagoServices AS) {
		this.AS = AS;
	}

	@Override
	public synchronized void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		BNACanvas currentlyEditingCanvas = ArchipelagoUtils.getBNACanvas(AS.editor);
		if (currentlyEditingCanvas != null) {
			IBNAView view = currentlyEditingCanvas.getBNAView();
			if (view != null) {
				IBNAWorld world = view.getBNAWorld();
				if (world != null) {
					Object worldID = world.getID();
					if ("structure".equals(worldID)) {
						for (IThingLogic tl : world.getThingLogicManager().getAllThingLogics()) {
							if (tl instanceof IXArchEventHandlerLogic) {
								((IXArchEventHandlerLogic) tl).handleXArchADTModelEvent(evt, world);
							}
						}
					}
				}
			}
		}
	}

}
