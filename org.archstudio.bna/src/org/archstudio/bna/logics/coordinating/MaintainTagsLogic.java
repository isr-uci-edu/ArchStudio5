package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.coordinating.StickRelativeMovablesLogic.StickyMode;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.archstudio.bna.things.labels.TagThing;
import org.archstudio.bna.utils.AssemblyUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class MaintainTagsLogic extends AbstractMaintainReferencedThingsLogic<IThing, TagThing> {

	public static final String USER_MAY_SHOW_TAG = "userMayShowTag";

	public static final String TAG_PART_NAME = "tag";
	public static final ThingKey<Boolean, V> SHOW_TAG_KEY = ThingKey.createKey("showTag");

	public MaintainTagsLogic(ReferenceTrackingLogic rtl) {
		super(IThing.class, new String[] { SHOW_TAG_KEY, IHasText.TEXT_KEY }, TagThing.class, new String[] {}, rtl,
				IHasTargetThing.TARGET_THING_ID_KEY);

	}

	@Override
	@SuppressWarnings("unchecked")
	protected void maintainAll() {
		IBNAModel model = getBNAModel();
		for (IThing sourceThing : model.getAllThings()) {
			if (isSourceThing(model, sourceThing, null)) {
				updateFromSource(null, sourceThing, null);
			}
		}
	}

	@Override
	protected void updateFromSource(IBNAModel sourceModel, IThing sourceThing, ThingEvent sourceThingEvent) {
		IBNAModel targetModel = getBNAModel();
		if ((sourceThingEvent == null || sourceThingEvent.getPropertyName().equals(SHOW_TAG_KEY))
				&& Boolean.TRUE.equals(sourceThing.getProperty(SHOW_TAG_KEY))) {
			TagThing t = new TagThing();
			int delta = 20;
			Point initialPoint = t.getAnchorPoint();
			if (sourceThing instanceof IHasAnchorPoint) {
				initialPoint = BNAUtils.clone(((IHasAnchorPoint) sourceThing).getAnchorPoint());
				initialPoint.x += delta;
				initialPoint.y -= delta;
			}
			else if (sourceThing instanceof IHasBoundingBox) {
				Rectangle r = ((IHasBoundingBox) sourceThing).getBoundingBox();
				initialPoint = new Point(r.x + r.width / 2, r.y + r.height / 2);
				initialPoint.x += delta;
				initialPoint.y -= delta;
			}
			t.setAnchorPoint(initialPoint);
			targetModel.addThing(t, sourceThing);

			MoveWithLogic.moveWith(sourceThing, MoveWithLogic.TRACK_ANCHOR_POINT_FIRST, t);
			StickRelativeMovablesLogic.stickPoint(sourceThing, IHasIndicatorPoint.INDICATOR_POINT_KEY,
					StickyMode.CENTER, t);
			AbstractMirrorValueLogic.mirrorValue(sourceThing, IHasText.TEXT_KEY, t);
			UserEditableUtils.addEditableQuality(t, IRelativeMovable.USER_MAY_MOVE,
					IHasMutableAngle.USER_MAY_CHANGE_ANGLE);

			IAssemblyThing assembly = AssemblyUtils.getAssemblyWithPart(sourceThing);
			if (assembly != null) {
				assembly.markPart(TAG_PART_NAME, t);
			}
		}
		if (sourceThingEvent != null && sourceThingEvent.getPropertyName().equals(SHOW_TAG_KEY)
				&& !Boolean.TRUE.equals(sourceThing.getProperty(SHOW_TAG_KEY))) {
			IAssemblyThing assembly = AssemblyUtils.getAssemblyWithPart(sourceThing);
			if (assembly != null) {
				IThing t = assembly.getPart(TAG_PART_NAME);
				if (t != null) {
					assembly.unmarkPart(TAG_PART_NAME);
					targetModel.removeThing(t);
				}
			}
		}
		super.updateFromSource(sourceModel, sourceThing, sourceThingEvent);
	}

	@Override
	protected void maintain(IBNAModel sourceModel, IThing sourceThing, TagThing targetThing, ThingEvent thingEvent) {
	}
}
