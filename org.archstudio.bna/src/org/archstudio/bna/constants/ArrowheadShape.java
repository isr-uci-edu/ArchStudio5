package org.archstudio.bna.constants;

public enum ArrowheadShape {
	NONE(false, false), WEDGE(false, true), TRIANGLE(true, true);

	private final boolean filled;
	private final boolean edged;

	private ArrowheadShape(boolean filled, boolean edged) {
		this.filled = filled;
		this.edged = edged;
	}

	public boolean isFilled() {
		return filled;
	}

	public boolean isEdged() {
		return edged;
	}

}
