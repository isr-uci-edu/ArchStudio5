package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseClickListener {

	public abstract void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location);

	public abstract void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location);

}