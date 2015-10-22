package org.archstudio.archipelago2.ext.statecharts.logics;

import java.awt.geom.Point2D;
import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.ext.statecharts.StatechartAssemblies;
import org.archstudio.archipelago2.ext.statecharts.StatechartPreferences;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.shapes.CurvedSplineThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.sysutils.Finally;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.logics.mapping.SynchronizeThingIDAndObjRefLogic;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

public class MapTransitionLogic extends AbstractXADLToBNAPathLogic<CurvedSplineThing>
    implements IPropertyChangeListener {
  protected final SynchronizeThingIDAndObjRefLogic syncLogic;
  protected final DynamicStickPointLogic stickLogic;

  public MapTransitionLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String description) {
    super(world, xarch, rootObjRef, objRefPath);
    syncLogic = logics.addThingLogic(SynchronizeThingIDAndObjRefLogic.class);
    stickLogic = logics.addThingLogic(DynamicStickPointLogic.class);

    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(Assemblies.ANCHORED_TEXT_KEY),
        IHasText.TEXT_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, false);
    syncValue("from", null, null, BNAPath.create(), syncLogic.syncObjRefKeyToThingIDKey(
        stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_1_KEY)), true);
    syncValue("to", null, null, BNAPath.create(), syncLogic.syncObjRefKeyToThingIDKey(
        stickLogic.getStickyThingKey(IHasEndpoints.ENDPOINT_2_KEY)), true);
    setProgressInfo(description);

    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
    Archipelago2Utils
        .weakListenToPropertyChanges(StatechartPreferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected CurvedSplineThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    Point location = BNAUtils2.getNewThingSpot(world, true);
    CurvedSplineThing transition = StatechartAssemblies.createTransition(world, null, null);
    transition.setEndpoint1(new Point2D.Double(location.x - 50, location.y + 50));
    transition.setEndpoint2(new Point2D.Double(location.x + 50, location.y - 50));
    transition.setArrowhead2Color(new RGB(0, 0, 0));
    transition.setArrowhead2EdgeColor(new RGB(0, 0, 0));
    transition.setSpacing(3);
    transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_1_KEY),
        StickyMode.EDGE_FROM_CENTER);
    transition.set(stickLogic.getStickyModeKey(IHasEndpoints.ENDPOINT_2_KEY),
        StickyMode.EDGE_FROM_CENTER);
    AnchoredLabelThing label = Assemblies.ANCHORED_TEXT_KEY.get(transition, model);

    UserEditableUtils.addEditableQualities(transition, IHasMutableSelected.USER_MAY_SELECT,
        IHasMutableReferencePoint.USER_MAY_MOVE, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1,
        IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_1,
        IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2,
        IHasMutableEndpoints.USER_MAY_RESTICK_ENDPOINT_2);
    UserEditableUtils.addEditableQualities(label, IHasMutableText.USER_MAY_EDIT_TEXT);
    return transition;
  }

  @Override
  public void applyDefaults(CurvedSplineThing thing) {
    super.applyDefaults(thing);
    applyPreferences(thing);
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    try (Finally lock = BNAUtils.lock()) {
      for (CurvedSplineThing thing : getAddedThings()) {
        applyPreferences(thing);
      }
    }
  }

  public void applyPreferences(CurvedSplineThing thing) {
    thing.setLineWidth(Archipelago2Preferences.getLineWidth());
    AnchoredLabelThing label = Assemblies.ANCHORED_TEXT_KEY.get(thing, model);
    FontData labelFont = StatechartPreferences.getTransitionFont();
    label.set(IHasFontData.FONT_NAME_KEY, labelFont.getName());
    label.set(IHasFontData.FONT_SIZE_KEY, labelFont.getHeight());
    label.set(IHasFontData.FONT_STYLE_KEY, FontStyle.fromSWT(labelFont.getStyle()));
  }
}
