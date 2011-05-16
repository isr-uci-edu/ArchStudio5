package org.archstudio.bna.logics.events;

public interface IDragMoveListener {

	public void dragStarted(DragMoveEvent evt);

	public void dragMoved(DragMoveEvent evt);

	public void dragFinished(DragMoveEvent evt);
}
