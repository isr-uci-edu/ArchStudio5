package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.bna.IThingLogic;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.OrientDirectionalLabelLogic;
import org.archstudio.bna.logics.coordinating.ActionOnMatchingValueLogic;
import org.archstudio.bna.logics.coordinating.StickAnchorPointLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadlbna.IHasObjRef;
import org.archstudio.xadlbna.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import edu.uci.isr.archstudio4.comp.archipelago.generic.logics.mapping.AbstractXADLToBNAPathLogic;
import edu.uci.isr.archstudio4.comp.archipelago.generic.logics.mapping.BNAPath;

public class MapInterfaceLogic extends AbstractXADLToBNAPathLogic<EndpointGlassThing> {

	private static class DirectionToFlow implements IXADLToBNATranslator<Direction, Flow> {
		@Override
		public Flow toBNAValue(Direction xadlValue) {
			return Flow.valueOf(xadlValue.getLiteral().toUpperCase());
		}
	};

	private static final DirectionToFlow DIRECTION_TO_FLOW = new DirectionToFlow();

	private final IThingLogic parentMappingKey;

	protected ThingValueTrackingLogic values = null;
	protected StickAnchorPointLogic stick = null;
	protected OrientDirectionalLabelLogic orient = null;
	protected ActionOnMatchingValueLogic rovp = null;

	public MapInterfaceLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath, MapBrickLogic parentMappingKey) {
		super(xarch, rootObjRef, objRefPath);
		this.parentMappingKey = parentMappingKey;
		mapAttribute("direction", DIRECTION_TO_FLOW, Flow.NONE, BNAPath.create(Assemblies.LABEL_KEY), IHasFlow.FLOW_KEY);
		mapAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY);
		mapAttribute("name", null, "[no description]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY);
		mapAttribute("name", null, "[no description]", BNAPath.create(), ToolTipLogic.TOOL_TIP_KEY);
	}

	@Override
	public void init() {
		values = getBNAWorld().getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		stick = getBNAWorld().getThingLogicManager().addThingLogic(StickAnchorPointLogic.class);
		orient = getBNAWorld().getThingLogicManager().addThingLogic(OrientDirectionalLabelLogic.class);
		rovp = getBNAWorld().getThingLogicManager().addThingLogic(ActionOnMatchingValueLogic.class);
		super.init();
	}

	@Override
	protected EndpointGlassThing addThing(ObjRef objRef, List<ObjRef> relativeAncestorRefs) {

		// this should be present, if not we want the iterator error to be thrown
		AbstractRectangleThing brickThing = (AbstractRectangleThing) getBNAModel().getThing(
				values.getThingIDs(IHasObjRef.OBJREF_KEY, relativeAncestorRefs.get(1), MAPPING_KEY, parentMappingKey)
						.iterator().next());

		EndpointGlassThing thing = Assemblies.createEndpoint(getBNAWorld(), null, brickThing);

		stick.stick(brickThing, StickyMode.EDGE, thing);
		orient.orient(brickThing, Assemblies.LABEL_KEY.get(thing, getBNAModel()));

		UserEditableUtils.addEditableQualities(thing, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
