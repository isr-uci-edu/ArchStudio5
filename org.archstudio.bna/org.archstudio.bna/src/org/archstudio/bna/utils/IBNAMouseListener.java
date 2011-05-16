package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseListener {

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location);

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location);

}