package org.archstudio.bna;

public interface IBNAView {

	public IBNAView getParentView();

	public IBNAWorld getBNAWorld();

	public ICoordinateMapper getCoordinateMapper();

	public Iterable<IThing> getThingsAt(ICoordinate location);

	public void setCursor(int swtCursor);

}
