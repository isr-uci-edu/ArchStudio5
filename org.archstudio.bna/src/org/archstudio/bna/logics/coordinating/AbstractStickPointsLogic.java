package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.coordinating.AbstractStickPointsLogic.StickPointsLogicData;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

abstract public class AbstractStickPointsLogic extends
		AbstractStickPointLogic<IIsSticky, IHasMutablePoints, StickPointsLogicData> {

	static class StickPointsLogicData {
		public final StickyMode stickyMode;
		public final int pointIndex;

		public StickPointsLogicData(StickyMode stickyMode, int pointIndex) {
			this.stickyMode = stickyMode;
			this.pointIndex = pointIndex;
		}
	}

	public AbstractStickPointsLogic(IThingLogicManager tlm) {
		super(IIsSticky.class, IHasMutablePoints.class);
	}

	public void stick(IIsSticky stickToThing, StickyMode stickyMode, int pointIndex, IHasMutablePoints... pointsThings) {
		checkNotNull(stickToThing);
		checkNotNull(stickyMode);

		for (IHasMutablePoints pointsThing : pointsThings) {
			unstick(pointsThing, pointIndex);
		}
		setPropagate(stickToThing, IHasPoints.POINTS_KEY, new StickPointsLogicData(stickyMode, pointIndex),
				pointsThings);
	}

	private int normalizePointIndex(IHasPoints pointsThing, int pointIndex) {
		return pointIndex < 0 ? pointIndex + pointsThing.getPointsSize() : pointIndex;
	}

	private Predicate<StickPointsLogicData> withNormalizedPointIndexPredicate(final IHasPoints pointsThing,
			int pointIndex) {
		final int normalizedPointIndex = normalizePointIndex(pointsThing, pointIndex);
		return new Predicate<StickPointsLogicData>() {
			@Override
			public boolean apply(StickPointsLogicData input) {
				if (normalizedPointIndex == normalizePointIndex(pointsThing, input.pointIndex)) {
					return true;
				}
				return false;
			}
		};
	}

	public boolean isStuck(IHasMutablePoints pointsThing, int pointIndex) {
		return !Iterables.isEmpty(getPropagateToTriggers(IHasPoints.POINTS_KEY, pointsThing,
				withNormalizedPointIndexPredicate(pointsThing, pointIndex)));
	}

	public @Nullable
	StickyMode getStickyMode(IHasMutablePoints pointsThing, int pointIndex) {
		for (StickPointsLogicData data : getPropagateToTriggers(IHasPoints.POINTS_KEY, pointsThing,
				withNormalizedPointIndexPredicate(pointsThing, pointIndex))) {
			return data.stickyMode;
		}
		return null;
	}

	public IIsSticky getStickyThing(IHasMutablePoints pointsThing, int pointIndex) {
		return firstOrNull(getPropagateFromThings(IHasPoints.POINTS_KEY, pointsThing,
				withNormalizedPointIndexPredicate(pointsThing, pointIndex)));
	}

	public void unstick(final IHasMutablePoints pointsThing, int pointIndex) {
		filterPropagateToTriggers(IHasPoints.POINTS_KEY, pointsThing,
				Predicates.not(withNormalizedPointIndexPredicate(pointsThing, pointIndex)));
	}

	@Override
	protected StickyMode getStickyMode(StickPointsLogicData data) {
		return data.stickyMode;
	}

	public Point getNearPoint(StickyMode stickyMode, IHasPoints pointsThing, int pointIndex) {
		if (stickyMode.isDependsOnSecondaryPoint()) {
			return pointsThing.getPoint(pointIndex + pointIndex >= 0 ? 1 : -1);
		}
		return pointsThing.getPoint(pointIndex);
	}

	@Override
	protected Point getNearPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickPointsLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent) {
		return getNearPoint(data.stickyMode, toThing, data.pointIndex);
	}

	@Override
	protected void setStuckPoint(IBNAModel model, IIsSticky fromThing, IThingKey<?> fromKey,
			ThingEvent<IIsSticky, ?, ?> fromThingEvent, StickPointsLogicData data, IHasMutablePoints toThing,
			IThingKey<?> toKey, ThingEvent<IHasMutablePoints, ?, ?> toThingEvent, Point stuckPoint) {
		toThing.setPoint(data.pointIndex, stuckPoint);
	}
}
