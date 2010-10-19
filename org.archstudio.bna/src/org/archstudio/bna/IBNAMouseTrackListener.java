package org.archstudio.bna;

import org.eclipse.swt.events.MouseEvent;

public interface IBNAMouseTrackListener {

	//Mouse track events
	public void mouseEnter(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY);

	public void mouseExit(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY);

	public void mouseHover(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY);

}