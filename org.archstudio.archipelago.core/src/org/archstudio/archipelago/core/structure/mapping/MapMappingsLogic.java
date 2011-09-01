package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasLineWidth;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.logics.coordinating.StickInternalPointLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.logics.mapping.LookupInternalThingIDFromObjRefLogic;
import org.archstudio.xadlbna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.collect.Lists;

public class MapMappingsLogic extends AbstractXADLToBNAPathLogic<MappingGlassThing> {

	LookupInternalThingIDFromObjRefLogic lookupInternalLogic = null;
	ReparentToThingIDLogic reparentLogic = null;
	StickInternalPointLogic stickInternalLogic = null;
	StickPointLogic stickLogic = null;
	SynchronizeThingIDAndObjRefLogic syncLogic = null;

	public MapMappingsLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		lookupInternalLogic = addThingLogic(LookupInternalThingIDFromObjRefLogic.class);
		reparentLogic = addThingLogic(ReparentToThingIDLogic.class);
		stickInternalLogic = addThingLogic(StickInternalPointLogic.class);
		stickLogic = addThingLogic(StickPointLogic.class);
		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);

		syncAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(), Assemblies.TEXT_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);

		// take the outer interface objRef, find the thing with that objRef, and stick endpoint 1 to it
		syncAttribute("outerInterfaceLink", null, null, BNAPath.create(),
				syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getThingRefKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
		// take the inner interface objRef, find the inner thing with that objRef, and stick endpoint 2 to it 
		syncAttribute("innerInterfaceLink", null, null, BNAPath.create(),
				lookupInternalLogic.getObjRefToThingIDKeyFor(stickInternalLogic
						.getThingRefKey(MappingGlassThing.WORLD_POINT_KEY)), true);
	}

	@Override
	protected MappingGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		MappingGlassThing thing = Assemblies.createMapping(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setPoint(0, newPointSpot.getTranslated(-50, 50));
		thing.setPoint(-1, newPointSpot.getTranslated(50, -50));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLineWidth.LINE_WIDTH_KEY, 2);

		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT,
				IHasMutableSelected.USER_MAY_SELECT);

		thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY), StickyMode.CENTER);
		thing.set(stickLogic.getStickyModeKey(MappingGlassThing.WORLD_POINT_KEY), StickyMode.EDGE_FROM_CENTER);

		// stack above the world thing
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(
				syncLogic.syncObjRefKeyToThingIDKey(reparentLogic.getReparentToThingIDKey()),
				Lists.reverse(relLineageRefs).get(1));

		return thing;
	}

	@Override
	protected void updateThing(List<ObjRef> relativeLineageRefs, XArchADTPath relativePath, ObjRef objRef,
			XArchADTModelEvent evt, MappingGlassThing thing) {
		super.updateThing(relativeLineageRefs, relativePath, objRef, evt, thing);

		// TODO: merge & specialize the StickInternalPointLogic and LookupInternalThingIDFromObjRefLogic so that they simply work with a MappingThing
		// currently, this is too confusing and difficult to configure/follow
		
		// set the world thing ID for the stick inner point logic
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(stickInternalLogic
				.getWorldThingRefKey(MappingGlassThing.WORLD_POINT_KEY)), Lists.reverse(relativeLineageRefs).get(1));
		// set the world thing ID for the lookup inner thing logic
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(lookupInternalLogic.getWorldThingIDKeyFor(stickInternalLogic
				.getThingRefKey(MappingGlassThing.WORLD_POINT_KEY))), Lists.reverse(relativeLineageRefs).get(1));
		// set the world thing ID for the mapping thing
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(MappingGlassThing.WORLD_REF_KEY), Lists.reverse(relativeLineageRefs).get(1));
	}
}
