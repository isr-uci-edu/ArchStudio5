package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.archstudio.bna.logics.coordinating.StickPointsLogic.StickPointLogicData;
import org.eclipse.draw2d.geometry.Point;

public class StickPointsLogic extends AbstractStickyLogic<IThing, IHasMutablePoints, StickPointLogicData> {

	static class StickPointLogicData {
		public final StickyMode stickyMode;
		public final int pointIndex;

		public StickPointLogicData(StickyMode stickyMode, int pointIndex) {
			this.stickyMode = stickyMode;
			this.pointIndex = pointIndex;
		}
	}

	public StickPointsLogic(IThingLogicManager tlm) {
		super(IThing.class, IHasMutablePoints.class, tlm);
	}

	public void stick(IHasBoundingBox stickToThing, StickyMode stickyMode, int pointIndex,
			IHasMutablePoints... pointsThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		setPropagate(stickToThing, IHasBoundingBox.BOUNDING_BOX_KEY, IHasPoints.POINTS_KEY, new StickPointLogicData(
				stickyMode, pointIndex), pointsThings);
		setPropagate(stickToThing, IHasRoundedCorners.CORNER_SIZE_KEY, IHasPoints.POINTS_KEY, new StickPointLogicData(
				stickyMode, pointIndex), pointsThings);
	}

	public void stick(IHasAnchorPoint stickToThing, StickyMode stickyMode, int pointIndex,
			IHasMutablePoints... pointsThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		setPropagate(stickToThing, IHasAnchorPoint.ANCHOR_POINT_KEY, IHasPoints.POINTS_KEY, new StickPointLogicData(
				stickyMode, pointIndex), pointsThings);
	}

	@Override
	protected StickyMode getStickyMode(StickPointLogicData data) {
		return data.stickyMode;
	};

	@Override
	protected Point getCurrentPoint(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			ThingEvent<IThing, ?, ?> fromThingEvent, StickPointLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent) {
		return toThing.getPoint(data.pointIndex);
	};

	@Override
	protected void setCurrentPoint(IBNAModel model, IThing fromThing, IThingKey<?> fromKey,
			ThingEvent<IThing, ?, ?> fromThingEvent, StickPointLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent, Point stuckPoint) {
		toThing.setPoint(data.pointIndex, stuckPoint.getCopy());
	};
}
