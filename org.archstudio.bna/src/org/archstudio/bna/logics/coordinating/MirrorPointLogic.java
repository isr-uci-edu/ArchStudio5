package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.MirrorPointLogic.MirrorPointLogicData;
import org.eclipse.draw2d.geometry.Point;

public class MirrorPointLogic extends AbstractPropagateValueLogic<IHasPoints, IThing, MirrorPointLogicData> {

	public MirrorPointLogic() {
		super(IHasPoints.class, IThing.class);
	}

	static class MirrorPointLogicData {
		public final int pointIndex;

		public MirrorPointLogicData(int pointIndex) {
			this.pointIndex = pointIndex;
		}
	}

	public void mirror(IHasPoints thingWithPoints, int pointIndex, IRelativeMovable... mirroringThings) {
		checkNotNull(thingWithPoints);

		setPropagate(thingWithPoints, IHasPoints.POINTS_KEY, null, new MirrorPointLogicData(pointIndex),
				mirroringThings);
	}

	public void mirror(IHasPoints thingWithPoints, int pointIndex, IHasMutableAnchorPoint... mirroringThings) {
		checkNotNull(thingWithPoints);

		setPropagate(thingWithPoints, IHasPoints.POINTS_KEY, IHasAnchorPoint.ANCHOR_POINT_KEY,
				new MirrorPointLogicData(pointIndex), mirroringThings);
	}

	@Override
	protected void doPropagation(IBNAModel model, IHasPoints fromThing, IThingKey<?> fromKey,
			ThingEvent<IHasPoints, ?, ?> fromThingEvent, MirrorPointLogicData data, IThing toThing, IThingKey<?> toKey,
			ThingEvent<IThing, ?, ?> toThingEvent) {
		Point p = fromThing.getPoint(data.pointIndex);
		if (toThing instanceof IHasMutableAnchorPoint) {
			((IHasMutableAnchorPoint) toThing).setAnchorPoint(p);
		}
		else if (toThing instanceof IRelativeMovable) {
			((IRelativeMovable) toThing).setReferencePoint(p);
		}
	}
}
