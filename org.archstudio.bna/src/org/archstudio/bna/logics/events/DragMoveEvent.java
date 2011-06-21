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
	private ICoordinate adjustedLocation;

	public DragMoveEvent(IBNAView view, MouseEvent evt, IThing initialThing, ICoordinate initialLocation) {
		this.view = view;
		this.evt = evt;
		this.initialThing = initialThing;
		this.initialLocation = initialLocation;
		this.adjustedLocation = initialLocation;
	}

	public DragMoveEvent(DragMoveEvent evt, ICoordinate adjustedLocation) {
		super();
		this.view = evt.view;
		this.evt = evt.evt;
		this.initialThing = evt.initialThing;
		this.initialLocation = evt.initialLocation;
		this.adjustedLocation = adjustedLocation;
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

	public ICoordinate getAdjustedLocation() {
		return adjustedLocation;
	}

	public void setAdjustedLocation(ICoordinate adjustedLocation) {
		this.adjustedLocation = adjustedLocation;
	}

}
