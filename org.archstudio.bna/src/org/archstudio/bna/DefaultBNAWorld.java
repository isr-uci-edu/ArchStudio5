package org.archstudio.bna;

public class DefaultBNAWorld implements IBNAWorld, IBNAModelListener, IBNASynchronousModelListener {
	protected String id = null;
	protected IBNAModel model = null;
	protected boolean isDestroyed = false;

	protected transient IThingLogicManager logicManager = null;

	public DefaultBNAWorld(String id, IBNAModel model) {
		this.id = id;
		this.model = model;
		this.logicManager = new DefaultThingLogicManager(this);

		getBNAModel().addBNAModelListener(this);
		getBNAModel().addSynchronousBNAModelListener(this);
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		for (IBNAModelListener l : logicManager.getThingLogics(IBNAModelListener.class)) {
			l.bnaModelChanged(evt);
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		for (IBNASynchronousModelListener l : logicManager.getThingLogics(IBNASynchronousModelListener.class)) {
			l.bnaModelChangedSync(evt);
		}
	}

	public IThingLogicManager getThingLogicManager() {
		return logicManager;
	}

	public void destroy() {
		logicManager.destroy();

		model.removeSynchronousBNAModelListener(this);
		model.removeBNAModelListener(this);

		isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public String getID() {
		return id;
	}

	public IBNAModel getBNAModel() {
		return model;
	}

}
