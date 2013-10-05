package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.IThingLogicManager;

public abstract class AbstractThingLogic implements IThingLogic {

	protected final IBNAWorld world;
	protected final IBNAModel model;
	protected final IThingLogicManager logics;

	public AbstractThingLogic(IBNAWorld world) {
		this.world = checkNotNull(world);
		this.model = world.getBNAModel();
		this.logics = world.getThingLogicManager();
	}

	@Override
	synchronized public IBNAWorld getBNAWorld() {
		return world;
	}

	@Override
	synchronized public void init() {
	}

	@Override
	synchronized public void dispose() {
	}

}
