package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutablePoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.ActionOnMatchingValueLogic;
import org.archstudio.bna.logics.coordinating.ActionOnMatchingValueLogic.MatchFoundAction;
import org.archstudio.bna.logics.coordinating.StickPointsLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.logics.mapping.BNAPath;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;


public class MapLinkLogic extends AbstractXADLToBNAPathLogic<SplineGlassThing> {

	protected StickPointsLogic stick = null;

	private final IThingKey<ObjRef> POINT1_REF_KEY = ActionOnMatchingValueLogic.create("point1ObjRef",
			IHasObjRef.OBJREF_KEY, new MatchFoundAction<ObjRef>() {
				@Override
				public void found(IThing sourceThing, IThingKey<ObjRef> sourceKey, IThing targetThing,
						IThingKey<ObjRef> targetKey, ObjRef value) {
					stick.stick((IIsSticky) targetThing, StickyMode.CENTER, 0, (IHasMutablePoints) sourceThing);
				}
			});

	private final IThingKey<ObjRef> POINT2_REF_KEY = ActionOnMatchingValueLogic.create("point2ObjRef",
			IHasObjRef.OBJREF_KEY, new MatchFoundAction<ObjRef>() {
				@Override
				public void found(IThing sourceThing, IThingKey<ObjRef> sourceKey, IThing targetThing,
						IThingKey<ObjRef> targetKey, ObjRef value) {
					stick.stick((IIsSticky) targetThing, StickyMode.CENTER, -1, (IHasMutablePoints) sourceThing);
				}
			});

	public MapLinkLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
		mapAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(), ToolTipLogic.TOOL_TIP_KEY, false);
		mapReference("point1", BNAPath.create(), POINT1_REF_KEY);
		mapReference("point2", BNAPath.create(), POINT2_REF_KEY);
	}

	@Override
	public void init() {
		stick = getBNAWorld().getThingLogicManager().addThingLogic(StickPointsLogic.class);
		getBNAWorld().getThingLogicManager().addThingLogic(ActionOnMatchingValueLogic.class);
		super.init();
	}

	@Override
	protected SplineGlassThing addThing(List<ObjRef> relativeAncestorRefs, ObjRef objRef) {

		SplineGlassThing thing = Assemblies.createSpline(getBNAWorld(), null, null);

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE, IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
