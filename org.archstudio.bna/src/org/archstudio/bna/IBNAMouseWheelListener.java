package org.archstudio.bna;

import java.util.List;

import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseWheelListener {

	public void mouseWheel(IBNAView view, MouseEvent e, List<IThing> t, ICoordinate location);

}
