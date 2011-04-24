package org.archstudio.bna;

import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseListener {

	//Mouse events
	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY);

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY);

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY);

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY);

}