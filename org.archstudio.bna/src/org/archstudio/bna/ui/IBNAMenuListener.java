package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.jface.action.IMenuManager;

/**
 * @deprecated Use {@link IBNAMenuListener2}.
 */
@Deprecated
public interface IBNAMenuListener {

	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu);

}