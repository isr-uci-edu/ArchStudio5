package org.archstudio.bna.logics.tracking;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IBNAModel;

public class ModelBoundsChangedEvent {

	protected ModelBoundsTrackingLogic source;
	protected IBNAModel targetModel;
	protected Rectangle oldBoundingBox;
	protected Rectangle newBoundingBox;

	public ModelBoundsChangedEvent(ModelBoundsTrackingLogic source, IBNAModel targetModel, Rectangle oldBoundingBox, Rectangle newBoundingBox) {
		this.source = source;
		this.targetModel = targetModel;
		this.oldBoundingBox = oldBoundingBox;
		this.newBoundingBox = newBoundingBox;
	}

	public ModelBoundsTrackingLogic getSource() {
		return source;
	}

	public IBNAModel getTargetModel() {
		return targetModel;
	}

	public Rectangle getOldBoundingBox() {
		return oldBoundingBox;
	}

	public Rectangle getNewBoundingBox() {
		return newBoundingBox;
	}
}
