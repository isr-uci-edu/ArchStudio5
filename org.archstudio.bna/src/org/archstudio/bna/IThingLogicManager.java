package org.archstudio.bna;

import java.util.List;

public interface IThingLogicManager {
	public void destroy();

	public void addThingLogic(IThingLogic tl);

	public void removeThingLogic(IThingLogic tl);

	public List<IThingLogic> getAllThingLogics();

	public <T> List<T> getThingLogics(Class<T> implementingInterface);
}
