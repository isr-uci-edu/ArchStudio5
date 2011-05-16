package org.archstudio.bna;

public interface IBNAWorld {

	public Object getID();

	public void destroy();

	public boolean isDestroyed();

	public IBNAModel getBNAModel();

	public IThingLogicManager getThingLogicManager();
}
