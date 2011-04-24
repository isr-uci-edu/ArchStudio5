package org.archstudio.bna;

public class CoordinateMapperEvent {

	protected int newWorldOriginX;
	protected int newWorldOriginY;
	protected double newScale;

	public CoordinateMapperEvent(int newWorldOriginX, int newWorldOriginY, double newScale) {
		this.newWorldOriginX = newWorldOriginX;
		this.newWorldOriginY = newWorldOriginY;
		this.newScale = newScale;
	}

	public int getNewWorldOriginX() {
		return newWorldOriginX;
	}

	public int getNewWorldOriginY() {
		return newWorldOriginY;
	}

	public double getNewScale() {
		return newScale;
	}

	public String toString() {
		return "CoordinateMapperEvent{newWorldOriginX=" + newWorldOriginX + "; newWorldOriginY=" + newWorldOriginY + "; newScale=" + newScale + "}";
	}

}
