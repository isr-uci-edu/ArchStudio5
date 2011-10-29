package org.archstudio.bna.utils;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.dnd.DropTargetEvent;

public interface IBNADragAndDropListener {

	//Drop events
	public void dragEnter(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

	public void dragOver(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

	public void dragOperationChanged(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

	public void dragLeave(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

	public void dropAccept(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

	public void drop(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location);

}