package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;

public class ShowHideTagsLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener {

	public static final String USER_MAY_SHOW_HIDE_TAG = "userMayShowHideTag";

	public static final IThingKey<Boolean> SHOW_TAG_KEY = ThingKey.create("showTag");

	public static final IThingRefKey<AnchoredLabelThing> TAG_KEY = ThingRefKey.create("assembly-tag");

	protected final ThingValueTrackingLogic valueLogic;
	protected final DynamicStickPointLogic stickLogic;
	protected final MirrorValueLogic mirrorLogic;

	public ShowHideTagsLogic(IBNAWorld world) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
		for (IHasStickyShape forThing : valueLogic.getThings(SHOW_TAG_KEY, true, IHasStickyShape.class)) {
			showTag(forThing);
		}
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		BNAUtils.checkLock();

		if (!things.isEmpty()) {
			final IHasStickyShape st = Assemblies.getEditableThing(model, firstOrNull(things), IHasStickyShape.class,
					USER_MAY_SHOW_HIDE_TAG);
			if (st != null) {
				final AnchoredLabelThing tt = getTag(st);
				// lookup tags for thing
				IAction tagAction = new BNAAction("Show Tag") {

					@Override
					public void runWithLock() {
						if (tt == null) {
							BNAOperations.set("Tag", model, st, SHOW_TAG_KEY, true);
						}
						else {
							BNAOperations.set("Tag", model, st, SHOW_TAG_KEY, false);
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
		BNAUtils.checkLock();

		switch (evt.getEventType()) {
		case THING_CHANGED:
			if (!evt.getThingEvent().getPropertyName().equals(SHOW_TAG_KEY)) {
				break;
			}
			// fall through
		case THING_ADDED:
			if (evt.getTargetThing() instanceof IHasStickyShape) {
				if (evt.getTargetThing().has(SHOW_TAG_KEY, true)) {
					showTag((IHasStickyShape) evt.getTargetThing());
				}
				else {
					hideTag((IHasStickyShape) evt.getTargetThing());
				}
			}
			break;
		case THING_REMOVED:
			if (evt.getTargetThing() instanceof IHasStickyShape) {
				hideTag((IHasStickyShape) evt.getTargetThing());
			}
			break;
		default:
			// do nothing
		}
	}

	protected AnchoredLabelThing getTag(IHasStickyShape forThing) {
		return firstOrNull(valueLogic.getThings(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY),
				forThing.getID(), AnchoredLabelThing.class));
	}

	protected AnchoredLabelThing showTag(IHasStickyShape forThing) {
		AnchoredLabelThing t = getTag(forThing);
		if (t == null) {
			t = model.addThing(new AnchoredLabelThing(Lists.newArrayList(forThing.getID(), "tag")), forThing);
			t.setAnchorPoint(BNAUtils.getCentralPoint(forThing));
			t.setEdgeColor(new RGB(0, 0, 0));
			UserEditableUtils.addEditableQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE,
					IHasMutableAngle.USER_MAY_CHANGE_ANGLE);
			t.set(stickLogic.getStickyModeKey(IHasIndicatorPoint.INDICATOR_POINT_KEY), StickyMode.EDGE_FROM_CENTER);
			stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY).set(t, forThing);
			if (forThing instanceof IHasText) {
				mirrorLogic.mirrorValue(forThing, IHasText.TEXT_KEY, t);
			}
			else {
				mirrorLogic.mirrorValue(forThing, IHasToolTip.TOOL_TIP_KEY, t, IHasText.TEXT_KEY);
			}
			Assemblies.markPart(Assemblies.getRoot(model, forThing), TAG_KEY, t);
		}
		return t;
	}

	protected void hideTag(IHasStickyShape forThing) {
		AnchoredLabelThing t = getTag(forThing);
		if (t != null) {
			t.remove(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY));
			mirrorLogic.unmirror(t, IHasText.TEXT_KEY);
			Assemblies.unmarkPart(model, t);
			model.removeThing(t);
		}
	}
}
