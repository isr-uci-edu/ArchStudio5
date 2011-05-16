package org.archstudio.bna.utils;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseTrackListener {

	public void mouseEnter(IBNAView view, MouseEvent e, Iterable<IThing> t, ICoordinate location);

	public void mouseExit(IBNAView view, MouseEvent e, Iterable<IThing> t, ICoordinate location);

	public void mouseHover(IBNAView view, MouseEvent e, Iterable<IThing> t, ICoordinate location);

}