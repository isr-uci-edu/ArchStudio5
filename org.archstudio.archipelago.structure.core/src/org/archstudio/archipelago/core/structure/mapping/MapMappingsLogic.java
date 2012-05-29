package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadl.bna.XADLAssemblies;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xadl.bna.things.IHasXArchID;
import org.archstudio.xadl.bna.things.MappingSplineGlassThing;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.collect.Lists;

public class MapMappingsLogic extends AbstractXADLToBNAPathLogic<MappingSplineGlassThing> {

	DynamicStickPointLogic stickLogic = null;
	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	ReparentToThingIDLogic reparentLogic = null;

	public MapMappingsLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		stickLogic = addThingLogic(DynamicStickPointLogic.class);
		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		reparentLogic = addThingLogic(ReparentToThingIDLogic.class);

		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		// take the inner world objRef and interface objRef, set them on the MappingSplineThing
		setValue(BNAPath.create(), stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY),
				StickyMode.EDGE_FROM_CENTER);
		syncValue("innerInterfaceLink", null, null, BNAPath.create(), MappingSplineGlassThing.INTERNAL_THING_OBJREF,
				true);
		setAncestorObjRef(BNAPath.create(), MappingSplineGlassThing.WORLD_THING_OBJREF, 1);

		// take the outer interface objRef, find the thing with that objRef, and stick the anchor point to it
		setValue(BNAPath.create(), stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY),
				StickyMode.EDGE_FROM_CENTER);
		syncValue("outerInterfaceLink", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);
	}

	@Override
	protected MappingSplineGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		MappingSplineGlassThing thing = XADLAssemblies.createMapping(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setEndpoint1(new Point(newPointSpot.x - 50, newPointSpot.y + 50));
		thing.setEndpoint2(new Point(newPointSpot.x + 50, newPointSpot.y - 50));
		//Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, 2);

		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableSelected.USER_MAY_SELECT);

		// stack above the world thing
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(
				syncLogic.syncObjRefKeyToThingIDKey(reparentLogic.getReparentToThingIDKey()),
				Lists.reverse(relLineageRefs).get(1));

		return thing;
	}

}
