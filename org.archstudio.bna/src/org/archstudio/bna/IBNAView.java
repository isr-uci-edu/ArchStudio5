package org.archstudio.bna;

import org.eclipse.swt.widgets.Control;

// View is parameterized with what kind of thing peer data it stores.
public interface IBNAView {

	public IBNAView getParentView();

	public IBNAWorld getBNAWorld();

	public ICoordinateMapper getCoordinateMapper();

	public Iterable<IThing> getThingsAt(ICoordinate location);

	public void setCursor(int swtCursor);

	/**
	 * Get the ThingPeer for the given Thing.
	 * @param t ThingPeer
	 * @return Thing's peer
	 */
	public <T extends IThing> IThingPeer<T> getThingPeer(T t);
	
	public <T extends IThing> void disposePeer(T t);

	public Control getControl();

}
