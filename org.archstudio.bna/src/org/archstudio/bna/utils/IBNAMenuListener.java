package org.archstudio.bna.utils;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.jface.action.IMenuManager;

public interface IBNAMenuListener {

	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu);

}