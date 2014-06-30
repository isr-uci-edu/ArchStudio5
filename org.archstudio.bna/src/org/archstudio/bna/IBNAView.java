package org.archstudio.bna;

import java.util.List;

import org.archstudio.bna.ui.IBNAUI;

public interface IBNAView {

	public IBNAView getParentView();

	public IBNAUI getBNAUI();

	public void setBNAUI(IBNAUI bnaUI);

	public IBNAWorld getBNAWorld();

	public ICoordinateMapper getCoordinateMapper();

	public List<IThing> getThingsAt(ICoordinate location);

	public <T extends IThing> IThingPeer<T> getThingPeer(T t);

	public void disposePeers();

	public void dispose();
}
