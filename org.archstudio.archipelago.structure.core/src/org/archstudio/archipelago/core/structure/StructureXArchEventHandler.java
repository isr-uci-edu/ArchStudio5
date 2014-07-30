package org.archstudio.archipelago.core.structure;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.IArchipelagoEditorPane;
import org.archstudio.archipelago.core.IXArchEventHandlerLogic;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.sysutils.Finally;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.XArchADTModelEvent;

public class StructureXArchEventHandler implements IXArchADTModelListener {

	protected Services AS = null;
	protected final IArchipelagoEditorPane editor;

	public StructureXArchEventHandler(Services AS) {
		this.AS = AS;
		this.editor = AS.get(IArchipelagoEditorPane.class);
	}

	@Override
	public synchronized void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		try (Finally lock = BNAUtils.lock()) {
			BNACanvas currentlyEditingCanvas = ArchipelagoUtils.getBNACanvas(editor);
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

}
