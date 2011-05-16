package org.archstudio.bna.logics.editing;

public class EndpointMovedEvent {

	protected SplineReshapeHandleLogic source;
	protected ISplineReshapable targetThing;
	protected int endpointNum;
	protected int worldX;
	protected int worldY;

	public EndpointMovedEvent(SplineReshapeHandleLogic source, ISplineReshapable targetThing, int endpointNum,
			int worldX, int worldY) {
		this.source = source;
		this.targetThing = targetThing;
		this.endpointNum = endpointNum;
		this.worldX = worldX;
		this.worldY = worldY;
	}

	public int getEndpointNum() {
		return endpointNum;
	}

	public SplineReshapeHandleLogic getSource() {
		return source;
	}

	public ISplineReshapable getTargetThing() {
		return targetThing;
	}

	public int getWorldX() {
		return worldX;
	}

	public int getWorldY() {
		return worldY;
	}
}
