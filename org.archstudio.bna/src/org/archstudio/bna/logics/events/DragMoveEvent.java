package org.archstudio.bna.logics.events;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.eclipse.swt.events.MouseEvent;

public class DragMoveEvent {

	private final IBNAView view;
	private final MouseEvent evt;
	private final Iterable<IThing> initialThings;
	private final ICoordinate initialLocation;
	private final ICoordinate adjustedLocation;

	public DragMoveEvent(IBNAView view, MouseEvent evt, Iterable<IThing> initialThings, ICoordinate initialLocation) {
		this.view = view;
		this.evt = evt;
		this.initialThings = initialThings;
		this.initialLocation = initialLocation;
		this.adjustedLocation = initialLocation;
	}

	public DragMoveEvent(DragMoveEvent evt, ICoordinate adjustedLocation) {
		super();
		this.view = evt.view;
		this.evt = evt.evt;
		this.initialThings = evt.initialThings;
		this.initialLocation = evt.initialLocation;
		this.adjustedLocation = adjustedLocation;
	}

	public IBNAView getView() {
		return view;
	}

	public MouseEvent getEvt() {
		return evt;
	}

	public Iterable<IThing> getInitialThings() {
		return initialThings;
	}

	public ICoordinate getInitialLocation() {
		return initialLocation;
	}

	public ICoordinate getAdjustedLocation() {
		return adjustedLocation;
	}
}
