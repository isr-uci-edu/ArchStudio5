package org.archstudio.bna;

import java.util.List;

import org.archstudio.bna.constants.MouseType;
import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseWheelListener {

	public void mouseVerticalWheel(IBNAView view, MouseType type, MouseEvent e, List<IThing> t, ICoordinate location);

	public void mouseHorizontalWheel(IBNAView view, MouseType type, MouseEvent e, List<IThing> t, ICoordinate location);

}
