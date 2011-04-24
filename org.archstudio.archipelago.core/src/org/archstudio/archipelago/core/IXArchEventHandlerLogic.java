package org.archstudio.archipelago.core;

import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;

public interface IXArchEventHandlerLogic extends IThingLogic{
	public void handleXArchADTModelEvent(XArchADTModelEvent evt, IBNAWorld world);
}
