package org.archstudio.archipelago.core.structure.mapping;

import static org.archstudio.bna.constants.StickyMode.EDGE;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReorientToThingIDLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Point;

public class MapInterfaceLogic extends AbstractXADLToBNAPathLogic<EndpointGlassThing> {

	private static final IXADLToBNATranslator<Direction, Flow> DIRECTION_TO_FLOW = new IXADLToBNATranslator<Direction, Flow>() {

		@Override
		public Flow toBNAValue(Direction xadlValue) {
			return Flow.valueOf(xadlValue.getName().toUpperCase());
		}

		@Override
		public Direction toXadlValue(Flow value) {
			return Direction.valueOf(value.name().toUpperCase());
		}
	};

	private static final IXADLToBNATranslator<String, StickyMode> DOMAIN_TO_STICKY_MODE = new IXADLToBNATranslator<String, StickyMode>() {

		@Override
		public StickyMode toBNAValue(String xadlValue) {
			if (xadlValue == null) {
				return EDGE;
			}
			// TODO: add necessary sticky modes
			return DomainType.TOP.equals(xadlValue) ? StickyMode.EDGE : StickyMode.EDGE;
		};

		@Override
		public String toXadlValue(StickyMode value) {
			throw new UnsupportedOperationException();
		};
	};

	SynchronizeThingIDAndObjRefLogic syncLogic = null;
	ReparentToThingIDLogic reparentLogic = null;
	ReorientToThingIDLogic reorientLogic = null;
	DynamicStickPointLogic stickLogic = null;

	public MapInterfaceLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	@Override
	public void init() {
		super.init();

		syncLogic = addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
		reparentLogic = addThingLogic(ReparentToThingIDLogic.class);
		reorientLogic = addThingLogic(ReorientToThingIDLogic.class);
		stickLogic = addThingLogic(DynamicStickPointLogic.class);

		syncValue("direction", DIRECTION_TO_FLOW, Flow.NONE, BNAPath.create(Assemblies.LABEL_KEY), IHasFlow.FLOW_KEY,
				true);
		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
		syncXAttribute("ext[*[namespace-uri()='" + Domain_3_0Package.eNS_URI + "']]/domain/type",
				DOMAIN_TO_STICKY_MODE, null, BNAPath.create(Assemblies.BACKGROUND_KEY),
				stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY), false);
	}

	@Override
	protected EndpointGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		EndpointGlassThing thing = Assemblies.createEndpoint(getBNAWorld(), null, null);
		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());
		thing.setAnchorPoint(newPointSpot);

		UserEditableUtils.addEditableQualities(thing, IRelativeMovable.USER_MAY_MOVE);
		//UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()), IHasMutableText.USER_MAY_EDIT_TEXT);
		UserEditableUtils.addEditableQualities(Assemblies.LABEL_KEY.get(thing, getBNAModel()),
				IHasMutableFlow.USER_MAY_EDIT_FLOW);
		UserEditableUtils.addEditableQualities(thing, ShowHideTagsLogic.USER_MAY_SHOW_HIDE_TAG);

		/*
		 * restack on top of the thing representing the first ancestor (i.e.,
		 * the component or connector)
		 */
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(
				syncLogic.syncObjRefKeyToThingIDKey(reparentLogic.getReparentToThingIDKey()), relLineageRefs.get(1));

		/* orient to the parent thing */
		Assemblies.LABEL_KEY.get(thing, getBNAModel()).set(
				syncLogic.syncObjRefKeyToThingIDKey(reorientLogic.getReorientToThingKey()), relLineageRefs.get(1));

		thing.set(stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY), StickyMode.EDGE);
		thing.set(syncLogic.syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasAnchorPoint.ANCHOR_POINT_KEY)),
				relLineageRefs.get(1));

		return thing;
	}
}
