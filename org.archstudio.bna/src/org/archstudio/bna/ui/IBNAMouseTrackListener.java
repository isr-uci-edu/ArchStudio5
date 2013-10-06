package org.archstudio.bna.ui;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseTrackListener {

	public void mouseEnter(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location);

	public void mouseExit(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location);

}