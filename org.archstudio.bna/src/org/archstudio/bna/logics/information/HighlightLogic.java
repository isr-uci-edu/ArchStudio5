package org.archstudio.bna.logics.information;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableLoopPoint;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.archstudio.bna.facets.IHasValue;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.AbstractCurvedSplineThing;
import org.archstudio.bna.things.AbstractEllipseThing;
import org.archstudio.bna.things.borders.CurvedSplineGlowThing;
import org.archstudio.bna.things.borders.EllipseGlowThing;
import org.archstudio.bna.things.borders.RectangleGlowThing;
import org.archstudio.bna.things.borders.SplineGlowThing;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

import com.google.common.collect.Sets;

public class HighlightLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener {

	public static final String USER_MAY_HIGHLIGHT = "userMayHighlight";

	public static final IThingRefKey<IThing> HIGHLIGHT_PART_KEY = ThingRefKey.create("assembly-highlight");

	public static final IThingKey<RGB> HIGHLIGHT_COLOR_KEY = ThingKey.create("highlight");

	protected final ThingValueTrackingLogic valueLogic;
	protected final MirrorValueLogic mirrorLogic;

	public HighlightLogic(IBNAWorld world) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
		for (IThing thing : model.getAllThings()) {
			if (thing.get(HIGHLIGHT_COLOR_KEY) != null) {
				showHighlight(thing);
			}
		}
	}

	@Override
	synchronized public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		if (!things.isEmpty()) {
			final IThing t = Assemblies.getEditableThing(model, firstOrNull(things), IThing.class, USER_MAY_HIGHLIGHT);
			if (t != null) {
				final IThing ht = getHighlight(t);
				IAction highlightAction = new Action("Highlight") {

					@Override
					public void run() {
						if (ht == null) {
							ColorDialog cd = new ColorDialog(view.getBNAUI().getComposite().getShell());
							cd.setText("Highlight Color");
							RGB newColor = cd.open();
							if (newColor != null) {
								BNAOperations.set("Highlight", model, t, HIGHLIGHT_COLOR_KEY, newColor);
							}
						}
						else {
							BNAOperations.set("Remove Highlight", model, t, HIGHLIGHT_COLOR_KEY, null);
						}
					}
				};
				highlightAction.setChecked(ht != null);
				menu.add(highlightAction);
			}
		}
	}

	@Override
	synchronized public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_CHANGED:
			if (!evt.getThingEvent().getPropertyName().equals(HIGHLIGHT_COLOR_KEY)) {
				break;
			}
			// fall through
		case THING_ADDED:
			if (evt.getTargetThing().get(HIGHLIGHT_COLOR_KEY) != null) {
				showHighlight(evt.getTargetThing());
			}
			else {
				hideHighlight(evt.getTargetThing());
			}
			break;
		case THING_REMOVED:
			hideHighlight(evt.getTargetThing());
			break;
		default:
			// do nothing
		}
	}

	protected IThing getHighlight(IThing forThing) {
		return Assemblies.getPart(model, forThing, HIGHLIGHT_PART_KEY);
	}

	protected IThing showHighlight(IThing forThing) {
		IThing t = getHighlight(forThing);
		if (t == null) {
			IThing bkgThing = null;
			Set<IThing> assemblyThings = Sets.newHashSet(Assemblies.getAssemblyThings(model, forThing));
			for (IThing furthestBackThing : model.getAllThings()) {
				if (assemblyThings.contains(furthestBackThing)) {
					bkgThing = furthestBackThing;
					break;
				}
			}
			if (forThing instanceof AbstractCurvedSplineThing) {
				t = model.insertThing(new CurvedSplineGlowThing(null), bkgThing);
				mirrorLogic.mirrorValue(forThing, IHasEndpoints.ENDPOINT_1_KEY, t);
				mirrorLogic.mirrorValue(forThing, IHasEndpoints.ENDPOINT_2_KEY, t);
				mirrorLogic.mirrorValue(forThing, IHasValue.VALUE_KEY, t);
				mirrorLogic.mirrorValue(forThing, IHasMutableLoopPoint.LOOP_POINT_KEY, t);
			}
			else if (forThing instanceof IHasEndpoints) {
				t = model.insertThing(new SplineGlowThing(null), bkgThing);
				mirrorLogic.mirrorValue(forThing, IHasEndpoints.ENDPOINT_1_KEY, t);
				mirrorLogic.mirrorValue(forThing, IHasEndpoints.ENDPOINT_2_KEY, t);
				if (forThing instanceof IHasMidpoints) {
					mirrorLogic.mirrorValue(forThing, IHasMidpoints.MIDPOINTS_KEY, t);
				}
			}
			else if (forThing instanceof AbstractEllipseThing) {
				t = model.insertThing(new EllipseGlowThing(null), bkgThing);
				mirrorLogic.mirrorValue(forThing, IHasBoundingBox.BOUNDING_BOX_KEY, t);
			}
			else if (forThing instanceof IHasBoundingBox) {
				t = model.insertThing(new RectangleGlowThing(null), bkgThing);
				mirrorLogic.mirrorValue(forThing, IHasBoundingBox.BOUNDING_BOX_KEY, t);
				if (forThing instanceof IHasRoundedCorners) {
					mirrorLogic.mirrorValue(forThing, IHasRoundedCorners.CORNER_SIZE_KEY, t);
				}
			}
			if (t != null) {
				mirrorLogic.mirrorValue(forThing, HIGHLIGHT_COLOR_KEY, t, IHasColor.COLOR_KEY);
				Assemblies.markPart(forThing, HIGHLIGHT_PART_KEY, t);
			}
		}
		return t;
	}

	protected void hideHighlight(IThing forThing) {
		IThing t = getHighlight(forThing);
		if (t != null) {
			mirrorLogic.unmirror(t, IHasPoints.POINTS_KEY);
			mirrorLogic.unmirror(t, IHasBoundingBox.BOUNDING_BOX_KEY);
			model.removeThing(t);
		}
	}
}
