package org.archstudio.bna;

import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseWheelListener {

	public void mouseWheel(IBNAView view, MouseEvent e, Iterable<IThing> t, ICoordinate location);

}
