package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ShowHideTagsLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener {

	public static final String USER_MAY_SHOW_HIDE_TAG = "userMayShowHideTag";

	public static final IThingKey<Boolean> SHOW_TAG_KEY = ThingKey.create("showTag");

	public static final IThingRefKey<AnchoredLabelThing> TAG_KEY = ThingRefKey.create("assembly-tag");

	protected ThingValueTrackingLogic valueLogic;
	protected DynamicStickPointLogic stickLogic;
	protected MirrorValueLogic mirrorLogic;

	@Override
	protected void init() {
		super.init();
		valueLogic = addThingLogic(ThingValueTrackingLogic.class);
		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		mirrorLogic = addThingLogic(MirrorValueLogic.class);
		for (IIsSticky forThing : Iterables.filter(
				getBNAModel().getThingsByID(valueLogic.getThingIDs(SHOW_TAG_KEY, true)), IIsSticky.class)) {
			showTag(forThing);
		}
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		IBNAModel m = getBNAModel();
		if (!things.isEmpty()) {
			final IIsSticky st = Assemblies.getEditableThing(m, firstOrNull(things), IIsSticky.class,
					USER_MAY_SHOW_HIDE_TAG);
			if (st != null) {
				final AnchoredLabelThing tt = getTag(st);
				// lookup tags for thing
				IAction tagAction = new Action("Show Tag") {

					@Override
					public void run() {
						if (tt == null) {
							BNAOperations.set("Tag", getBNAModel(), st, SHOW_TAG_KEY, true);
						}
						else {
							BNAOperations.set("Tag", getBNAModel(), st, SHOW_TAG_KEY, false);
						}
					}
				};
				tagAction.setChecked(tt != null);
				menu.add(tagAction);
			}
		}
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_CHANGED:
			if (!evt.getThingEvent().getPropertyName().equals(SHOW_TAG_KEY)) {
				break;
			}
			// fall through
		case THING_ADDED:
			if (evt.getTargetThing() instanceof IIsSticky) {
				if (evt.getTargetThing().has(SHOW_TAG_KEY, true)) {
					showTag((IIsSticky) evt.getTargetThing());
				}
				else {
					hideTag((IIsSticky) evt.getTargetThing());
				}
			}
			break;
		case THING_REMOVED:
			if (evt.getTargetThing() instanceof IIsSticky) {
				hideTag((IIsSticky) evt.getTargetThing());
			}
			break;
		default:
			// do nothing
		}
	}

	protected AnchoredLabelThing getTag(IIsSticky forThing) {
		return firstOrNull(Iterables.filter(
				getBNAModel().getThingsByID(
						valueLogic.getThingIDs(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY),
								forThing.getID())), AnchoredLabelThing.class));
	}

	protected AnchoredLabelThing showTag(IIsSticky forThing) {
		AnchoredLabelThing t = getTag(forThing);
		if (t == null) {
			t = getBNAModel().addThing(new AnchoredLabelThing(Lists.newArrayList(forThing.getID(), "tag")));
			t.setAnchorPoint(BNAUtils.getCentralPoint(forThing));
			t.setEdgeColor(new RGB(0, 0, 0));
			UserEditableUtils.addEditableQualities(t, IRelativeMovable.USER_MAY_MOVE,
					IHasMutableAngle.USER_MAY_CHANGE_ANGLE);
			t.set(stickLogic.getStickyModeKey(IHasIndicatorPoint.INDICATOR_POINT_KEY), StickyMode.EDGE_FROM_CENTER);
			stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY).set(t, forThing);
			if (forThing instanceof IHasText) {
				mirrorLogic.mirrorValue(forThing, IHasText.TEXT_KEY, t);
			}
			else {
				mirrorLogic.mirrorValue(forThing, IHasToolTip.TOOL_TIP_KEY, t, IHasText.TEXT_KEY);
			}
			Assemblies.markPart(Assemblies.getRoot(getBNAModel(), forThing), TAG_KEY, t);
		}
		return t;
	}

	protected void hideTag(IIsSticky forThing) {
		AnchoredLabelThing t = getTag(forThing);
		if (t != null) {
			t.remove(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY));
			mirrorLogic.unmirror(t, IHasText.TEXT_KEY);
			Assemblies.unmarkPart(getBNAModel(), t);
			getBNAModel().removeThing(t);
		}
	}
}
