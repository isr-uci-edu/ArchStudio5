package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic.ReferenceThingKey;
import org.archstudio.sysutils.SystemUtils;

public abstract class AbstractMaintainReferencedThingsLogic<S extends IThing, T extends IThing> extends
		AbstractMaintainThingsLogic<S, T> {

	protected final ReferenceTrackingLogic rtl;
	protected final ReferenceThingKey<?> referenceKey;

	public AbstractMaintainReferencedThingsLogic(Class<S> sourceThingClass, Class<T> targetThingClass,
			ReferenceTrackingLogic rtl, ReferenceThingKey<?> referenceKey) {
		super(sourceThingClass, targetThingClass);
		this.rtl = rtl;
		this.referenceKey = referenceKey;
		this.targetPropertyKeys.add(referenceKey);
	}

	@Override
	protected void maintainAll() {
		IBNAModel model = getBNAWorld().getBNAModel();
		for (Object thingId : rtl.getThingsReferencing(referenceKey)) {
			T targetThing = SystemUtils.castOrNull(model.getThing(thingId), targetThingClass);
			if (isTargetThing(targetThing, null)) {
				updateToTarget(null, targetThing, null);
			}
		}
	}

	abstract protected void maintain(IBNAModel sourceModel, S sourceThing, T targetThing,
			ThingEvent<S, ?, ?> sourceThingEvent);

	@Override
	protected void updateFromSource(IBNAModel sourceModel, S sourceThing, ThingEvent<S, ?, ?> sourceThingEvent) {
		IBNAModel targetModel = getBNAWorld().getBNAModel();
		for (Object thingId : rtl.getThingsReferencing(sourceThing.getID(), referenceKey)) {
			T targetThing = SystemUtils.castOrNull(targetModel.getThing(thingId), targetThingClass);
			if (targetThing != null && isTargetThing(targetThing, null)) {
				maintain(sourceModel, sourceThing, targetThing, sourceThingEvent);
			}
		}
	}

	@Override
	protected void updateToTarget(IBNAModel sourceModel, T targetThing, ThingEvent<T, ?, ?> targetThingEvent) {
		S sourceThing = SystemUtils.castOrNull(getBNAWorld().getBNAModel().getThing(targetThing.get(referenceKey)),
				sourceThingClass);
		if (sourceThing != null && isSourceThing(sourceModel, sourceThing, null)) {
			IBNAModel targetModel = getBNAWorld().getBNAModel();
			if (sourceModel == null) {
				sourceModel = targetModel;
			}
			if (sourceThing != null && isSourceThing(sourceModel, sourceThing, null)) {
				maintain(sourceModel, sourceThing, targetThing, null);
			}
		}
	}
}
