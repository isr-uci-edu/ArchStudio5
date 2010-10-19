package org.archstudio.bna;

public interface IBNAWorld extends java.io.Serializable {

	public void destroy();

	public boolean isDestroyed();

	public String getID();

	public IBNAModel getBNAModel();

	public IThingLogicManager getThingLogicManager();

}
