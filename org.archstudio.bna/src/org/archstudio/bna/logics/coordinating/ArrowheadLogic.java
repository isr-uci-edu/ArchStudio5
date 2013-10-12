package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.logics.AbstractCoordinatingThingLogic;
import org.archstudio.bna.things.labels.ArrowheadThing;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

public class ArrowheadLogic extends AbstractCoordinatingThingLogic {

	@NonNullByDefault
	public static interface IHasArrowheadStemPoint extends IThing, IHasShapeKeys {

		public Point getArroheadStempPoint(IThingKey<Point> pointKey);

	}

	private class ArrowheadUpdater extends Updater {

		final Object arrowheadThingID;
		final Object pointThingID;
		final IThingKey<Point> pointKey;

		public ArrowheadUpdater(ArrowheadThing arrowheadThing, IHasArrowheadStemPoint pointThing,
				IThingKey<Point> pointKey) {
			this.arrowheadThingID = arrowheadThing.getID();
			this.pointThingID = pointThing.getID();
			this.pointKey = pointKey;
		}

		@Override
		public void update() {
			IHasArrowheadStemPoint pointThing = castOrNull(model.getThing(pointThingID), IHasArrowheadStemPoint.class);
			if (pointThing != null) {
				ArrowheadThing arrowheadThing = castOrNull(model.getThing(arrowheadThingID), ArrowheadThing.class);
				if (arrowheadThing != null) {
					arrowheadThing.setAnchorPoint(pointThing.get(pointKey));
					arrowheadThing.setSecondaryAnchorPoint(pointThing.getArroheadStempPoint(pointKey));
				}
			}
		}
	}

	protected final StickPointLogic stickLogic;

	public ArrowheadLogic(IBNAWorld world) {
		super(world);
		stickLogic = logics.addThingLogic(StickPointLogic.class);
	}

	synchronized public void attach(ArrowheadThing arrowheadThing, IHasArrowheadStemPoint pointThing,
			IThingKey<Point> pointKey) {
		checkNotNull(arrowheadThing);
		checkNotNull(pointThing);
		checkNotNull(pointKey);

		ArrowheadUpdater updater = new ArrowheadUpdater(arrowheadThing, pointThing, pointKey);
		register(updater, arrowheadThing);

		track(updater, pointThing, arrowheadThing);
		for (IThingKey<?> key : pointThing.getShapeModifyingKeys()) {
			track(updater, pointThing, key);
		}
	}

	synchronized public void detach(ArrowheadThing arrowheadThing) {
		unregister(arrowheadThing);
	}
}
