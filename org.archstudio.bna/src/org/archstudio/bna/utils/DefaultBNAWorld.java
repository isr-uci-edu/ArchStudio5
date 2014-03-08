package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.IThingLogicManagerListener;
import org.archstudio.bna.ThingLogicManagerEvent;

public class DefaultBNAWorld implements IBNAWorld, IThingLogicManagerListener {

	protected Object id = null;
	protected IBNAModel model = null;

	protected transient IThingLogicManager logics = null;

	public DefaultBNAWorld(Object id, IBNAModel model) {
		this.id = id;
		this.model = checkNotNull(model);
		this.logics = new DefaultThingLogicManager(this);
		logics.addThingLogicManagerListener(this);
	}

	@Override
	public IThingLogicManager getThingLogicManager() {
		return logics;
	}

	@Override
	public void dispose() {
		logics.dispose();
	}

	@Override
	public Object getID() {
		return id;
	}

	@Override
	public IBNAModel getBNAModel() {
		return model;
	}

	@Override
	public void handleThingLogicManagerEvent(ThingLogicManagerEvent evt) {
		switch (evt.getEventType()) {
		case LOGIC_ADDED:
			if (evt.getLogic() instanceof IBNAModelListener) {
				model.addBNAModelListener((IBNAModelListener) evt.getLogic());
			}
			break;
		case LOGIC_REMOVING:
			if (evt.getLogic() instanceof IBNAModelListener) {
				model.removeBNAModelListener((IBNAModelListener) evt.getLogic());
			}
			break;
		default:
			// do nothing
		}
	}

	@Override
	public String toString() {
		return id.toString();
	}
}
