package org.archstudio.bna;

import org.eclipse.swt.widgets.Composite;

public interface IBNAView {
	public void setParentComposite(Composite parent);

	public Composite getParentComposite();

	public IBNAView getParentView();

	public IBNAWorld getWorld();

	public ICoordinateMapper getCoordinateMapper();

	public IThing getThingAt(int lx, int ly);

	public IThingPeer createPeer(IThing th);

	public IThingPeer getPeer(IThing th);

}
