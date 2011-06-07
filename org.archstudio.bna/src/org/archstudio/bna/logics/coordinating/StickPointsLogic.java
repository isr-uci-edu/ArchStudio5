package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.coordinating.StickPointsLogic.StickPointsLogicData;
import org.eclipse.draw2d.geometry.Point;

public class StickPointsLogic extends AbstractStickyLogic<IIsSticky, IHasMutablePoints, StickPointsLogicData> {

	static class StickPointsLogicData {
		public final StickyMode stickyMode;
		public final int pointIndex;

		public StickPointsLogicData(StickyMode stickyMode, int pointIndex) {
			this.stickyMode = stickyMode;
			this.pointIndex = pointIndex;
		}
	}

	public StickPointsLogic(IThingLogicManager tlm) {
		super(IIsSticky.class, IHasMutablePoints.class, tlm);
	}

	public void stick(IIsSticky stickToThing, StickyMode stickyMode, int pointIndex, IHasMutablePoints... pointsThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		setPropagate(stickToThing, IHasPoints.POINTS_KEY, new StickPointsLogicData(stickyMode, pointIndex),
				pointsThings);
	}

	@Override
	protected StickyMode getStickyMode(StickPointsLogicData data) {
		return data.stickyMode;
	}

	@Override
	protected Point getNearPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickPointsLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent) {
		if (data.stickyMode.isDependsOnSecondaryPoint()) {
			return toThing.getPoint(data.pointIndex + data.pointIndex >= 0 ? 1 : -1);
		}
		return toThing.getPoint(data.pointIndex);
	}

	@Override
	protected void setStuckPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickPointsLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent, Point stuckPoint) {
		toThing.setPoint(data.pointIndex, stuckPoint);
	}
}
