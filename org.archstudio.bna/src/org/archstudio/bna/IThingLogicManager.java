package org.archstudio.bna;

public interface IThingLogicManager {

	public void addThingLogicManagerListener(IThingLogicManagerListener l);

	public void removeThingLogicManagerListener(IThingLogicManagerListener l);

	public void dispose();

	public <L extends IThingLogic> L addThingLogic(Class<L> logicClass);

	public <L extends IThingLogic> L addThingLogic(L thingLogic);

	public void removeThingLogic(IThingLogic thingLogic);

	public Iterable<IThingLogic> getAllThingLogics();

	public <T> Iterable<T> getThingLogics(Class<T> ofType);
}
