package org.archstudio.archipelago2.ext.structures.logics;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasInternalWorldPoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableToolTip;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic;
import org.archstudio.bna.logics.coordinating.ReparentToThingIDLogic.ReparentParams;
import org.archstudio.bna.logics.events.ListenToSubWorldEventsLogic;
import org.archstudio.bna.things.shapes.MappingThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.MaintainMappingLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;

public class MapMappingsLogic extends AbstractXADLToBNAPathLogic<MappingThing>
    implements IPropertyChangeListener {
  protected final DynamicStickPointLogic stickLogic;
  protected final SynchronizeThingIDAndObjRefLogic syncLogic;
  protected final ReparentToThingIDLogic reparentLogic;

  public MapMappingsLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String description) {
    super(world, xarch, rootObjRef, objRefPath);
    stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);
    syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
    reparentLogic = logics.addThingLogic(ReparentToThingIDLogic.class);
    logics.addThingLogic(MaintainMappingLogic.class);
    logics.addThingLogic(ListenToSubWorldEventsLogic.class);

    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);
    setValue(BNAPath.create(), stickLogic.getStickyModeKey(IHasAnchorPoint.ANCHOR_POINT_KEY),
        StickyMode.CENTER);
    // Set the inner world objRef and interface objRef on the MappingSplineThing.
    syncValue("innerInterfaceLink", null, null, BNAPath.create(),
        MaintainMappingLogic.INTERNAL_OBJREF_KEY, false);
    setAncestorObjRef(BNAPath.create(),
        syncLogic.syncObjRefKeyToThingIDKey(IHasInternalWorldPoint.INTERNAL_WORLD_KEY), 1);
    // Set the outer interface objRef.
    syncValue("outerInterfaceLink", null, null, BNAPath.create(), syncLogic
        .syncObjRefKeyToThingIDKey(stickLogic.getStickyThingKey(IHasAnchorPoint.ANCHOR_POINT_KEY)),
        true);
    setProgressInfo(description);

    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected MappingThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    ObjRef brickRef = descendantRefs.get(descendantRefs.size() - 3);
    Point location = BNAUtils2.getNewThingSpot(world, true);
    MappingThing thing = Assemblies.createMapping(world, null, null);
    thing.setAnchorPoint(new Point2D.Double(location.x - 50, location.y + 50));
    thing.setInternalPoint(new Point2D.Double(location.x + 50, location.y - 50));
    // Re-stack the interface mapping on the top of the brick (i.e., under the interface).
    thing.set(syncLogic.syncObjRefKeyToThingIDKey(ReparentToThingIDLogic.REPARENT_TO_THING_KEY),
        brickRef);
    thing.set(ReparentToThingIDLogic.REPARENT_TO_THING_PARAMS_KEY,
        new ReparentParams(BNAPath.create(Assemblies.WORLD_KEY), true));

    UserEditableUtils.addEditableQualities(thing, IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP,
        IHasMutableSelected.USER_MAY_SELECT);
    return thing;
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    try (Finally lock = BNAUtils.lock()) {
      for (MappingThing thing : getAddedThings()) {
        applyPreferences(thing);
      }
    }
  }

  public void applyPreferences(MappingThing thing) {
    thing.setLineWidth(Archipelago2Preferences.getLineWidth());
  }
}
