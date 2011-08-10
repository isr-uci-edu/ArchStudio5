package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.logics.mapping.SynchronizeObjRefAndThingIDLogic;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Point;

public class MapLinkLogic extends AbstractXADLToBNAPathLogic<SplineGlassThing> {

	SynchronizeObjRefAndThingIDLogic syncLogic = null;
	StickPointLogic stickLogic = null;

	public MapLinkLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		syncLogic = addThingLogic(SynchronizeObjRefAndThingIDLogic.class);
		stickLogic = addThingLogic(StickPointLogic.class);

		syncAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		syncAttribute("point1", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getThingRefKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		syncAttribute("point2", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getThingRefKey(IHasEndpoints.ENDPOINT_2_KEY)), true);
	}

	@Override
	protected SplineGlassThing addThing(List<ObjRef> relativeAncestorRefs, ObjRef objRef) {

		SplineGlassThing thing = Assemblies.createSpline(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setPoint(0, newPointSpot.getTranslated(-50, 50));
		thing.setPoint(-1, newPointSpot.getTranslated(50, -50));

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS, IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS,
				IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS);

		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.CENTER);
		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY), StickyMode.CENTER);

		return thing;
	}
}
