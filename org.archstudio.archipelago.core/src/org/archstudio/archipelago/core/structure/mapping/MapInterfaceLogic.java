package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.ActionOnMatchingValueLogic;
import org.archstudio.bna.logics.coordinating.ActionOnMatchingValueLogic.MatchFoundAction;
import org.archstudio.bna.logics.coordinating.OrientDirectionalLabelLogic;
import org.archstudio.bna.logics.coordinating.StickAnchorPointLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.logics.mapping.BNAPath;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class MapInterfaceLogic extends AbstractXADLToBNAPathLogic<EndpointGlassThing> {

	private static class DirectionToFlow implements IXADLToBNATranslator<Direction, Flow> {
		@Override
		public Flow toBNAValue(Direction xadlValue) {
			return Flow.valueOf(xadlValue.getName().toUpperCase());
		}

		@Override
		public Direction toXadlValue(Flow value) {
			return Direction.valueOf(value.name().toLowerCase());
		}
	};

	private static final DirectionToFlow DIRECTION_TO_FLOW = new DirectionToFlow();

	protected StickAnchorPointLogic stick = null;
	protected OrientDirectionalLabelLogic orient = null;
	protected ActionOnMatchingValueLogic action = null;

	private final IThingKey<ObjRef> PARENT_REF_KEY = ActionOnMatchingValueLogic.create("parentObjRef",
			IHasObjRef.OBJREF_KEY, new MatchFoundAction<ObjRef>() {
				@Override
				public void found(IThing sourceThing, IThingKey<ObjRef> sourceKey, IThing targetThing,
						IThingKey<ObjRef> targetKey, ObjRef value) {
					IBNAModel model = getBNAModel();
					if (model != null) {
						model.beginBulkChange();
						try {
							model.reparent(targetThing, Assemblies.BACKGROUND_KEY.get(sourceThing, model));
							stick.stick((IIsSticky) targetThing, StickyMode.EDGE, (IHasMutableAnchorPoint) sourceThing);
							orient.orient((IHasBoundingBox) targetThing,
									Assemblies.LABEL_KEY.get(sourceThing, getBNAModel()));
						}
						finally {
							model.endBulkChange();
						}
					}
				}
			});

	public MapInterfaceLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
		mapAttribute("direction", DIRECTION_TO_FLOW, Flow.NONE, BNAPath.create(Assemblies.LABEL_KEY),
				IHasFlow.FLOW_KEY, true);
		mapAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(), ToolTipLogic.TOOL_TIP_KEY, true);
		mapAncestor(1, BNAPath.create(), PARENT_REF_KEY);
	}

	@Override
	public void init() {
		stick = getBNAWorld().getThingLogicManager().addThingLogic(StickAnchorPointLogic.class);
		orient = getBNAWorld().getThingLogicManager().addThingLogic(OrientDirectionalLabelLogic.class);
		action = getBNAWorld().getThingLogicManager().addThingLogic(ActionOnMatchingValueLogic.class);
		getBNAWorld().getThingLogicManager().addThingLogic(ActionOnMatchingValueLogic.class);
		super.init();
	}

	@Override
	protected EndpointGlassThing addThing(List<ObjRef> relativeAncestorRefs, ObjRef objRef) {

		EndpointGlassThing thing = Assemblies.createEndpoint(getBNAWorld(), null, null);

		UserEditableUtils.addEditableQualities(thing, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(thing, IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
