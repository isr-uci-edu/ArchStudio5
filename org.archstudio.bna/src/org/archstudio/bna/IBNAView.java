package org.archstudio.bna;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

public interface IBNAView {

	public IBNAView getParentView();

	public IBNAWorld getBNAWorld();

	public ICoordinateMapper getCoordinateMapper();

	public List<IThing> getThingsAt(ICoordinate location);

	public <T extends IThing> IThingPeer<T> getThingPeer(T t);

	public void setComposite(Composite composite);

	public Composite getComposite();

	public void dispose();
}
