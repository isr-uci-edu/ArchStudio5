package org.archstudio.bna.logics;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.utils.BNAUtils;

public abstract class AbstractThingLogic implements IThingLogic {

	protected final IBNAWorld world;
	protected final IBNAModel model;
	protected final IThingLogicManager logics;

	public AbstractThingLogic(IBNAWorld world) {
		BNAUtils.checkLock();

		this.world = checkNotNull(world);
		this.model = world.getBNAModel();
		this.logics = world.getThingLogicManager();
	}

	@Override
	public IBNAWorld getBNAWorld() {
		BNAUtils.checkLock();

		return world;
	}

	@Override
	public void init() {
		BNAUtils.checkLock();
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();
	}

}
