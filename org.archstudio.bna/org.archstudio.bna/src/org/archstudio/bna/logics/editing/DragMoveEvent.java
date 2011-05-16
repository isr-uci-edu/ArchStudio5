package org.archstudio.bna.logics.editing;

import org.eclipse.swt.events.MouseEvent;

public class DragMoveEvent {

	protected DragMovableLogic source = null;
	protected MouseEvent mouseEvent = null;
	protected IDragMovable movedThing = null;
	protected int dx = 0;
	protected int dy = 0;

	public DragMoveEvent(DragMovableLogic source, IDragMovable movedThing, MouseEvent mouseEvent, int dx, int dy) {
		super();
		this.source = source;
		this.movedThing = movedThing;
		this.mouseEvent = mouseEvent;
		this.dx = dx;
		this.dy = dy;
	}

	public int getDX() {
		return dx;
	}

	public int getDY() {
		return dy;
	}

	public IDragMovable getMovedThing() {
		return movedThing;
	}

	public MouseEvent getMouseEvent() {
		return mouseEvent;
	}

	public DragMovableLogic getSource() {
		return source;
	}

}
