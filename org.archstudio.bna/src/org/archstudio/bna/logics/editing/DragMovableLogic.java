package org.archstudio.bna.logics.editing;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class DragMovableLogic extends AbstractThingLogic implements IDragMoveListener {

	protected ThingValueTrackingLogic valuesLogic;

	protected final Map<IRelativeMovable, Point> movingThings = Maps.newHashMap();
	protected Runnable initialSnapshot = null;
	protected Point totalRelativePoint = new Point(0, 0);
	protected Point lastAdjustedMousePoint = new Point(0, 0);

	public DragMovableLogic() {
		super();
	}

	@Override
	protected void init() {
		super.init();
		this.valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
		// this logic listens to events from the following
		addThingLogic(DragMoveEventsLogic.class);
	}

	@Override
	protected void destroy() {
		movingThings.clear();
		super.destroy();
	}

	@Override
	public void dragStarted(DragMoveEvent evt) {
		movingThings.clear();
		totalRelativePoint.x = 0;
		totalRelativePoint.y = 0;
		lastAdjustedMousePoint = evt.getAdjustedThingLocation().getWorldPoint();
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			try {
				IRelativeMovable movingThing = Assemblies.getEditableThing(model, evt.getInitialThing(),
						IRelativeMovable.class, IRelativeMovable.USER_MAY_MOVE);
				if (movingThing != null) {
					List<IRelativeMovable> selectedThings = Lists
							.newArrayList(Iterables.filter(
									BNAUtils.getThings(model,
											valuesLogic.getThingIDs(IHasSelected.SELECTED_KEY, Boolean.TRUE)),
									IRelativeMovable.class));
					if (selectedThings.contains(movingThing)) {
						for (IRelativeMovable rmt : selectedThings) {
							if (rmt instanceof IHasMutableReferencePoint) {
								movingThings.put(rmt, ((IHasReferencePoint) rmt).getReferencePoint());
							}
							else {
								movingThings.put(rmt, null);
							}
						}
					}
					else {
						if (movingThing instanceof IHasMutableReferencePoint) {
							movingThings.put(movingThing, ((IHasReferencePoint) movingThing).getReferencePoint());
						}
						else {
							movingThings.put(movingThing, null);
						}
					}
					initialSnapshot = BNAOperations.takeSnapshotOfLocations(getBNAModel(), movingThings.keySet());
				}
			}
			finally {
				model.endBulkChange();
			}
		}
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			try {
				Point referencePointDelta = evt.getAdjustedThingLocation().getWorldPoint();
				Point initialLocation = evt.getInitialLocation().getWorldPoint();
				referencePointDelta.x -= initialLocation.x;
				referencePointDelta.y -= initialLocation.y;
				Point relativePointDelta = evt.getAdjustedThingLocation().getWorldPoint();
				relativePointDelta.x -= lastAdjustedMousePoint.x;
				relativePointDelta.y -= lastAdjustedMousePoint.y;
				totalRelativePoint.x += relativePointDelta.x;
				totalRelativePoint.y += relativePointDelta.y;

				for (Entry<IRelativeMovable, Point> e : movingThings.entrySet()) {
					if (e.getKey() instanceof IHasMutableReferencePoint) {
						Point referencePoint = e.getValue();
						referencePoint = new Point(referencePoint.x, referencePoint.y);
						referencePoint.x += referencePointDelta.x;
						referencePoint.y += referencePointDelta.y;
						((IHasMutableReferencePoint) e.getKey()).setReferencePoint(referencePoint);
					}
					else {
						e.getKey().moveRelative(relativePointDelta);
					}
				}
			}
			finally {
				lastAdjustedMousePoint = evt.getAdjustedThingLocation().getWorldPoint();
				model.endBulkChange();
			}
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
		// if we moved a handle, let the reshape logic handle the undo
		if (!(movingThings.size() == 1 && movingThings.keySet().iterator().next() instanceof ReshapeHandleThing)) {
			if (totalRelativePoint.x != 0 || totalRelativePoint.y != 0) {
				BNAOperations.runnable("Drag", initialSnapshot,
						BNAOperations.takeSnapshotOfLocations(getBNAModel(), movingThings.keySet()), false);
			}
		}
		initialSnapshot = null;
		movingThings.clear();
	}
}
