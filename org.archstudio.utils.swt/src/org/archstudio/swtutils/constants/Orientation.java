package org.archstudio.swtutils.constants;

public enum Orientation {

	NORTHEAST(-1, -1), NORTH(0, -1), NORTHWEST(1, -1), //
	EAST(-1, 0), NONE(0, 0), WEST(1, 0), //
	SOUTHEAST(-1, 1), SOUTH(0, 1), SOUTHWEST(1, 1);

	private final int deltaX, deltaY;

	private Orientation(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public Orientation getOpposite() {
		switch (this) {
		case NORTHEAST:
			return SOUTHWEST;
		case NORTH:
			return SOUTH;
		case NORTHWEST:
			return SOUTHEAST;
		case EAST:
			return WEST;
		case NONE:
			return NONE;
		case WEST:
			return EAST;
		case SOUTHEAST:
			return NORTHWEST;
		case SOUTH:
			return NORTH;
		case SOUTHWEST:
			return NORTHEAST;
		default:
			throw new IllegalArgumentException();
		}
	}

}
