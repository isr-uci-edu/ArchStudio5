package org.archstudio.bna.logics.information;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.editing.BNAOperations;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.borders.RectangleGlowThing;
import org.archstudio.bna.things.borders.SplineGlowThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;

public class HighlightLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener {

	public static final String USER_MAY_HIGHLIGHT = "userMayHighlight";

	public static final IThingRefKey<IThing> HIGHLIGHT_PART_KEY = Assemblies.ThingAssemblyKey
			.create("assembly-highlight");

	public static final IThingKey<RGB> HIGHLIGHT_COLOR_KEY = ThingKey.create("highlight");

	protected ThingValueTrackingLogic valueLogic;
	protected MirrorValueLogic mirrorLogic;

	@Override
	protected void init() {
		super.init();
		valueLogic = addThingLogic(ThingValueTrackingLogic.class);
		mirrorLogic = addThingLogic(MirrorValueLogic.class);
		for (IThing thing : getBNAModel().getAllThings()) {
			if (thing.get(HIGHLIGHT_COLOR_KEY) != null) {
				showHighlight(thing);
			}
		}
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		IBNAModel m = getBNAModel();
		if (!things.isEmpty()) {
			final IThing t = Assemblies.getAssemblyWithRootOrPart(m, firstOrNull(things));
			if (t != null && UserEditableUtils.isEditableForAllQualities(t, USER_MAY_HIGHLIGHT)) {
				final IThing ht = getHighlight(t);
				IAction highlightAction = new Action("Highlight") {

					@Override
					public void run() {
						if (ht == null) {
							ColorDialog cd = new ColorDialog(view.getComposite().getShell());
							cd.setText("Highlight Color");
							RGB newColor = cd.open();
							if (newColor != null) {
								BNAOperations.set("Highlight", getBNAModel(), t, HIGHLIGHT_COLOR_KEY, newColor);
							}
						}
						else {
							BNAOperations.set("Remove Highlight", getBNAModel(), t, HIGHLIGHT_COLOR_KEY, null);
						}
					}
				};
				highlightAction.setChecked(ht != null);
				menu.add(highlightAction);
			}
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
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
		return Assemblies.getPart(getBNAModel(), forThing, HIGHLIGHT_PART_KEY);
	}

	protected IThing showHighlight(IThing forThing) {
		IThing t = getHighlight(forThing);
		if (t == null) {
			if (forThing instanceof IHasPoints) {
				t = getBNAModel().addThing(new SplineGlowThing(null), forThing);
				mirrorLogic.mirrorValue(forThing, IHasPoints.POINTS_KEY, t);
			}
			else if (forThing instanceof IHasBoundingBox) {
				t = getBNAModel().addThing(new RectangleGlowThing(null), forThing);
				mirrorLogic.mirrorValue(forThing, IHasBoundingBox.BOUNDING_BOX_KEY, t);
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
			getBNAModel().removeThing(t);
		}
	}
}
