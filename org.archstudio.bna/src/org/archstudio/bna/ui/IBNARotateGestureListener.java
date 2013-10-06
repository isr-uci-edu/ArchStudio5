package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.GestureType;
import org.eclipse.swt.events.GestureEvent;

public interface IBNARotateGestureListener {

	public void rotateGesture(IBNAView view, GestureType type, GestureEvent e, List<IThing> t, ICoordinate location);

}
