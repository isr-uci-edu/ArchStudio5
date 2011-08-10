package org.archstudio.bna.logics.editing;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DragMovableLogic extends AbstractThingLogic implements IDragMoveListener {

	protected ThingValueTrackingLogic valuesLogic;

	protected final Map<IRelativeMovable, Point> movingThings = Maps.newHashMap();
	protected final Point lastAdjustedMousePoint = new Point();

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
		evt.getAdjustedThingLocation().getWorldPoint(lastAdjustedMousePoint);
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			try {
				IRelativeMovable movingThing = SystemUtils.castOrNull(evt.getInitialThing(), IRelativeMovable.class);
				if (UserEditableUtils.isEditableForAllQualities(movingThing, IRelativeMovable.USER_MAY_MOVE)) {
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
					Set<IThing> moveToFrontThings = Sets.newHashSet();
					for (IThing thing : movingThings.keySet()) {
						moveToFrontThings.addAll(Assemblies.getRelatedParts(model, thing));
					}
					model.bringToFront(moveToFrontThings);
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
				Point referencePointDelta = evt.getAdjustedThingLocation().getWorldPoint(new Point());
				referencePointDelta.translate(evt.getInitialLocation().getWorldPoint(new Point()).getNegated());
				Point relativePointDelta = evt.getAdjustedThingLocation().getWorldPoint(new Point());
				relativePointDelta.translate(lastAdjustedMousePoint.getNegated());

				for (Entry<IRelativeMovable, Point> e : movingThings.entrySet()) {
					if (e.getKey() instanceof IHasMutableReferencePoint) {
						((IHasMutableReferencePoint) e.getKey()).setReferencePoint(e.getValue().getTranslated(
								referencePointDelta));
					}
					else {
						e.getKey().moveRelative(relativePointDelta);
					}
				}
			}
			finally {
				evt.getAdjustedThingLocation().getWorldPoint(lastAdjustedMousePoint);
				model.endBulkChange();
			}
		}
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
		movingThings.clear();
	}
}
