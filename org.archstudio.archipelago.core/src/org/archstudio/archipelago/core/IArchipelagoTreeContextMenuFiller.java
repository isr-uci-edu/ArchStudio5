package org.archstudio.archipelago.core;

import org.eclipse.jface.action.IMenuManager;

public interface IArchipelagoTreeContextMenuFiller{
	public void fillContextMenu(IMenuManager m, Object[] selectedNodes);
}
