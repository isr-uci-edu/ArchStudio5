package org.archstudio.bna;

import org.eclipse.swt.widgets.Control;

public interface IBNAView {

	public IBNAView getParentView();

	public IBNAWorld getBNAWorld();

	@Deprecated
	public IBNAWorld getWorld();

	public ICoordinateMapper getCoordinateMapper();

	public Iterable<IThing> getThingsAt(ICoordinate location);

	public void setCursor(int swtCursor);

	public <T extends IThing> IThingPeer<T> getThingPeer(T t);

	public Control getControl();

}
