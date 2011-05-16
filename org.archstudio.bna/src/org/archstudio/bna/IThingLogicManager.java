package org.archstudio.bna;

public interface IThingLogicManager {

	public void addThingLogicManagerListener(IThingLogicManagerListener l);

	public void removeThingLogicManagerListener(IThingLogicManagerListener l);

	public void destroy();

	public <L extends IThingLogic> L addThingLogic(Class<L> tlClass);

	public <L extends IThingLogic> L addThingLogic(L tl);

	public void removeThingLogic(IThingLogic tl);

	public Iterable<IThingLogic> getAllThingLogics();

	public <T> Iterable<T> getThingLogics(Class<T> implementingInterface);
}
