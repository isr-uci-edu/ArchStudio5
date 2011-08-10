package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.jface.action.IMenuManager;

public interface IBNAMenuListener {

	//Menu events
	public void fillMenu(IBNAView view, Iterable<IThing> things, ICoordinate location, IMenuManager menu);

}