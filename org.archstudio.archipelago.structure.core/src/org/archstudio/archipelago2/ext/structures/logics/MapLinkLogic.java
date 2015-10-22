package org.archstudio.archipelago2.ext.structures.logics;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableToolTip;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;

public class MapLinkLogic extends AbstractXADLToBNAPathLogic<SplineThing>
    implements IPropertyChangeListener {
  protected final SynchronizeThingIDAndObjRefLogic syncLogic;
  protected final DynamicStickPointLogic stickLogic;

  public MapLinkLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String description) {
    super(world, xarch, rootObjRef, objRefPath);
    syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
    stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);

    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
    syncValue("point1", null, null, BNAPath.create(), syncLogic.syncObjRefKeyToThingIDKey(
        stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
    syncValue("point2", null, null, BNAPath.create(), syncLogic.syncObjRefKeyToThingIDKey(
        stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);
    setProgressInfo(description);

    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected SplineThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    Point location = BNAUtils2.getNewThingSpot(world, true);
    SplineThing thing = Assemblies.createSpline(world, null, null);
    thing.setEndpoint1(new Point2D.Double(location.x - 50, location.y + 50));
    thing.setEndpoint2(new Point2D.Double(location.x + 50, location.y - 50));
    thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY),
        StickyMode.EDGE_FROM_CENTER);
    thing.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY),
        StickyMode.EDGE_FROM_CENTER);

    UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
        IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP,
        IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS, IHasMutableMidpoints.USER_MAY_ADD_MIDPOINTS,
        IHasMutableMidpoints.USER_MAY_REMOVE_MIDPOINTS,
        IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1,
        IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_1,
        IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2,
        IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_2);
    return thing;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    try (Finally lock = BNAUtils.lock()) {
      for (SplineThing thing : getAddedThings()) {
        applyPreferences(thing);
      }
    }
  }

  public void applyPreferences(SplineThing thing) {
    thing.setLineWidth(Archipelago2Preferences.getLineWidth());
  }
}
