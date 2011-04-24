package org.archstudio.bna;

import org.eclipse.jface.action.IMenuManager;

public interface IBNAMenuListener {

	//Menu events
	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY);

}