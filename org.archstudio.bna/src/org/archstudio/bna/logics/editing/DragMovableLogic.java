package org.archstudio.bna.logics.editing;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Maps;

public class DragMovableLogic extends AbstractThingLogic implements IDragMoveListener {

	protected final ThingValueTrackingLogic valueLogic;

	protected final Map<IHasMutableReferencePoint, Point> movingThings = Maps.newHashMap();
	protected Runnable initialSnapshot = null;
	protected Point totalRelativePoint = new Point(0, 0);
	protected Point lastAdjustedMousePoint = new Point(0, 0);

	public DragMovableLogic(IBNAWorld world) {
		super(world);
		this.valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		logics.addThingLogic(DragMoveEventsLogic.class);
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		movingThings.clear();
		super.dispose();
	}

	@Override
	public void dragStarted(DragMoveEvent evt) {
		BNAUtils.checkLock();

		movingThings.clear();
		totalRelativePoint.x = 0;
		totalRelativePoint.y = 0;
		lastAdjustedMousePoint = evt.getAdjustedThingLocation().getWorldPoint();
		IHasMutableReferencePoint movingThing = Assemblies.getEditableThing(model, evt.getInitialThing(),
				IHasMutableReferencePoint.class, IHasMutableReferencePoint.USER_MAY_MOVE);
		if (movingThing != null) {
			Collection<IHasMutableReferencePoint> selectedThings = valueLogic.getThings(IHasSelected.SELECTED_KEY,
					Boolean.TRUE, IHasMutableReferencePoint.class);
			if (selectedThings.contains(movingThing)) {
				for (IHasMutableReferencePoint rmt : selectedThings) {
					movingThings.put(rmt, rmt.getReferencePoint());
				}
			}
			else {
				if (movingThing instanceof IHasReferencePoint) {
					movingThings.put(movingThing, movingThing.getReferencePoint());
				}
				else {
					movingThings.put(movingThing, null);
				}
			}
			initialSnapshot = BNAOperations.takeSnapshotOfLocations(model, movingThings.keySet());
		}
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		BNAUtils.checkLock();

		try (Finally bulkChange = model.beginBulkChange()) {
			Point referencePointDelta = evt.getAdjustedThingLocation().getWorldPoint();
			Point initialLocation = evt.getInitialLocation().getWorldPoint();
			referencePointDelta.x -= initialLocation.x;
			referencePointDelta.y -= initialLocation.y;
			Point relativePointDelta = evt.getAdjustedThingLocation().getWorldPoint();
			relativePointDelta.x -= lastAdjustedMousePoint.x;
			relativePointDelta.y -= lastAdjustedMousePoint.y;
			totalRelativePoint.x += relativePointDelta.x;
			totalRelativePoint.y += relativePointDelta.y;

			for (Entry<IHasMutableReferencePoint, Point> e : movingThings.entrySet()) {
				if (e.getKey() instanceof IHasMutableReferencePoint) {
					Point referencePoint = BNAUtils.clone(e.getValue());
					referencePoint.x += referencePointDelta.x;
					referencePoint.y += referencePointDelta.y;
					e.getKey().setReferencePoint(referencePoint);
				}
			}
		}
		finally {
			lastAdjustedMousePoint = evt.getAdjustedThingLocation().getWorldPoint();
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
		BNAUtils.checkLock();

		// if we moved a handle, let the reshape logic handle the undo
		if (!(movingThings.size() == 1 && movingThings.keySet().iterator().next() instanceof ReshapeHandleThing)) {
			if (totalRelativePoint.x != 0 || totalRelativePoint.y != 0) {
				BNAOperations.runnable("Drag", initialSnapshot,
						BNAOperations.takeSnapshotOfLocations(model, movingThings.keySet()), false);
			}
		}
		initialSnapshot = null;
		movingThings.clear();
	}
}
