package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasIndicatorPoint;
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
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Iterables;

public class ShowHideTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

	public static final String USER_MAY_SHOW_HIDE_TAG = "userMayShowHideTag";

	public static final IThingRefKey<TagThing> TAG_KEY = ThingRefKey.create("assembly-tag");

	public static final IThingKey<Boolean> SHOW_TAG_KEY = ThingKey.create("showTag");

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
			final IIsSticky st = castOrNull(Assemblies.getAssemblyWithRootOrPart(m, firstOrNull(things)),
					IIsSticky.class);
			if (st != null && UserEditableUtils.isEditableForAllQualities(st, USER_MAY_SHOW_HIDE_TAG)) {
				final TagThing tt = getTag(st);
				// lookup tags for thing
				IAction tagAction = new Action("Show Tag") {

					@Override
					public void run() {
						if (tt == null) {
							showTag(st);
						}
						else {
							hideTag(st);
						}
					}
				};
				tagAction.setChecked(tt != null);
				menu.add(tagAction);
			}
		}
	}

	protected TagThing getTag(IIsSticky forThing) {
		return firstOrNull(Iterables.filter(
				getBNAModel().getThingsByID(
						valueLogic.getThingIDs(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY),
								forThing.getID())), TagThing.class));
	}

	protected TagThing showTag(IIsSticky forThing) {
		TagThing t = getTag(forThing);
		if (t == null) {
			t = getBNAModel().addThing(new TagThing(null));
			t.setAnchorPoint(forThing.getStickyPointNear(StickyMode.CENTER, new Point(0, 0)));
			UserEditableUtils.addEditableQualities(t, IRelativeMovable.USER_MAY_MOVE);
			t.set(stickLogic.getStickyModeKey(IHasIndicatorPoint.INDICATOR_POINT_KEY), StickyMode.EDGE_FROM_CENTER);
			stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY).set(t, forThing);
			if (forThing instanceof IHasText) {
				mirrorLogic.mirrorValue(forThing, IHasText.TEXT_KEY, t);
			}
			else {
				mirrorLogic.mirrorValue(forThing, IHasToolTip.TOOL_TIP_KEY, t, IHasText.TEXT_KEY);
			}
			Assemblies.markPart(forThing, TAG_KEY, t);
		}
		forThing.set(SHOW_TAG_KEY, true);
		return t;
	}

	protected void hideTag(IIsSticky forThing) {
		TagThing t = getTag(forThing);
		if (t != null) {
			t.remove(stickLogic.getStickyThingKey(IHasIndicatorPoint.INDICATOR_POINT_KEY));
			mirrorLogic.unmirror(t, IHasText.TEXT_KEY);
			getBNAModel().removeThing(t);
		}
		forThing.set(SHOW_TAG_KEY, false);
	}

}
