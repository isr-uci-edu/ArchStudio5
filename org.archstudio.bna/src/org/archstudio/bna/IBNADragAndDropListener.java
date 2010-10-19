package org.archstudio.bna;

import org.eclipse.swt.dnd.DropTargetEvent;

public interface IBNADragAndDropListener {

	//Drop events
	public void dragEnter(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

	public void dragOver(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

	public void dragOperationChanged(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

	public void dragLeave(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

	public void dropAccept(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

	public void drop(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY);

}