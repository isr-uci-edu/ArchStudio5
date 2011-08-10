package org.archstudio.bna.logics.events;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.events.MouseEvent;

public class DragMoveEvent {

	private final IBNAView view;
	private final MouseEvent evt;
	private final IThing initialThing;
	private final ICoordinate initialLocation;
	private final ICoordinate mouseLocation;
	private ICoordinate adjustedThingLocation;
	private ICoordinate adjustedMouseLocation;

	public DragMoveEvent(IBNAView view, MouseEvent evt, IThing initialThing, ICoordinate initialLocation) {
		this.view = view;
		this.evt = evt;
		this.initialThing = initialThing;
		this.initialLocation = initialLocation;
		this.mouseLocation = initialLocation;
		this.adjustedThingLocation = initialLocation;
		this.adjustedMouseLocation = initialLocation;
	}

	public DragMoveEvent(DragMoveEvent oldEvent, MouseEvent evt, ICoordinate movedLocation) {
		super();
		this.view = oldEvent.view;
		this.evt = evt;
		this.initialThing = oldEvent.initialThing;
		this.initialLocation = oldEvent.initialLocation;
		this.mouseLocation = movedLocation;
		this.adjustedThingLocation = movedLocation;
		this.adjustedMouseLocation = movedLocation;
	}

	public IBNAView getView() {
		return view;
	}

	public MouseEvent getEvt() {
		return evt;
	}

	public IThing getInitialThing() {
		return initialThing;
	}

	public ICoordinate getInitialLocation() {
		return initialLocation;
	}

	public ICoordinate getMouseLocation() {
		return mouseLocation;
	}

	public ICoordinate getAdjustedThingLocation() {
		return adjustedThingLocation;
	}

	public void setAdjustedThingLocation(ICoordinate adjustedLocation) {
		this.adjustedThingLocation = adjustedLocation;
	}

	public ICoordinate getAdjustedMouseLocation() {
		return adjustedMouseLocation;
	}

	public void setAdjustedMouseLocation(ICoordinate adjustedMouseLocation) {
		this.adjustedMouseLocation = adjustedMouseLocation;
	}

}
