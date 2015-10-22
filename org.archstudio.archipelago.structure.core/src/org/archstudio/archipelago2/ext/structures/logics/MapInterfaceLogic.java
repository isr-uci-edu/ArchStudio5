package org.archstudio.archipelago2.ext.structures.logics;

import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasFlow;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableToolTip;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReorientDirectionalLabelToThingIDLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.things.shapes.EndpointThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.sysutils.Finally;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xadl3.structure_3_0.Direction;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;

public class MapInterfaceLogic extends AbstractXADLToBNAPathLogic<EndpointThing>
    implements IPropertyChangeListener {
  private static final IXADLToBNATranslator<Direction, Flow> DIRECTION_TO_FLOW =
      new IXADLToBNATranslator<Direction, Flow>() {
        @Override
        public Flow toBNAValue(Direction xadlValue) {
          return Flow.valueOf(xadlValue.getName().toUpperCase());
        }

        @Override
        public Direction toXadlValue(Flow value) {
          return Direction.valueOf(value.name().toUpperCase());
        }
      };

  protected final SynchronizeThingIDAndObjRefLogic syncLogic;
  protected final ReparentToThingIDLogic reparentLogic;
  protected final ReorientDirectionalLabelToThingIDLogic reorientLogic;
  protected final DynamicStickPointLogic stickLogic;

  public MapInterfaceLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String mappingDescription) {
    super(world, xarch, rootObjRef, objRefPath);
    syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
    reparentLogic = logics.addThingLogic(ReparentToThingIDLogic.class);
    reorientLogic = logics.addThingLogic(ReorientDirectionalLabelToThingIDLogic.class);
    stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);

    syncValue("direction", DIRECTION_TO_FLOW, Flow.NONE, BNAPath.create(Assemblies.DIRECTION_KEY),
        IHasFlow.FLOW_KEY, true);
    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
    setProgressInfo(mappingDescription);

    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected EndpointThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    ObjRef brickRef = descendantRefs.get(descendantRefs.size() - 2);
    Point location = BNAUtils2.getNewThingSpot(world, true);
    EndpointThing thing = Assemblies.createEndpoint(world, null, null);
    thing.setAnchorPoint(BNAUtils.toPoint2D(location));
    thing.set(stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY), StickyMode.EDGE);
    // Re-stack the interface on the top of its brick thing.
    thing.set(syncLogic.syncObjRefKeyToThingIDKey(ReparentToThingIDLogic.REPARENT_TO_THING_KEY),
        brickRef);
    // Stick the interface to the brick thing.
    thing.set(syncLogic.syncObjRefKeyToThingIDKey(
        stickLogic.getStickyThingKey(IHasAnchorPoint.ANCHOR_POINT_KEY)), brickRef);
    // Orient the flow to match the edge of the brick.
    Assemblies.DIRECTION_KEY.get(thing, model)
        .set(syncLogic.syncObjRefKeyToThingIDKey(reorientLogic.getReorientToThingKey()), brickRef);

    UserEditableUtils.addEditableQualities(thing, IHasMutableReferencePoint.USER_MAY_MOVE,
        HighlightLogic.USER_MAY_HIGHLIGHT, ShowHideTagsLogic.USER_MAY_SHOW_HIDE_TAG,
        IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP, IHasMutableAlpha.USER_MAY_CHANGE_ALPHA);
    UserEditableUtils.addEditableQualities(Assemblies.DIRECTION_KEY.get(thing, model),
        IHasMutableFlow.USER_MAY_EDIT_FLOW);
    return thing;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    try (Finally lock = BNAUtils.lock()) {
      for (EndpointThing thing : getAddedThings()) {
        applyPreferences(thing);
      }
    }
  }

  public void applyPreferences(EndpointThing thing) {
    thing.setLineWidth(Archipelago2Preferences.getLineWidth());
  }
}
