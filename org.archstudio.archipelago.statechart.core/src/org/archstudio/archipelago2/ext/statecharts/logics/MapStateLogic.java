package org.archstudio.archipelago2.ext.statecharts.logics;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.ext.statecharts.StatechartPreferences;
import org.archstudio.archipelago2.ext.statecharts.StatechartUtils;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.events.ListenToSubWorldEventsLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

/**
 * Maps States to BNA Rectangle Assemblies.
 */
public class MapStateLogic extends AbstractXADLToBNAPathLogic<RectangleThing>
    implements IPropertyChangeListener {
  protected final MirrorValueLogic mirrorLogic;
  protected Dimension defaultSize = new Dimension(6 * 24, 4 * 24);

  public MapStateLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String mappingDescription) {
    super(world, xarch, rootObjRef, objRefPath);
    mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
    logics.addThingLogic(ListenToSubWorldEventsLogic.class);

    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(Assemblies.BOUNDED_TEXT_KEY),
        IHasText.TEXT_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
    addBNAUpdater(Statechart_1_0Package.Literals.STATE__SUB_STATECHART.getName(),
        new IBNAUpdater() {
          @Override
          public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt,
              RectangleThing rootThing) {
            updateSubstructure(objRef, evt, rootThing);
          }
        });
    setProgressInfo(mappingDescription);

    Archipelago2Utils
        .weakListenToPropertyChanges(Archipelago2Preferences.getActivatorPreferenceStore(), this);
    Archipelago2Utils
        .weakListenToPropertyChanges(StatechartPreferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected RectangleThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    Point location = BNAUtils2.getNewThingSpot(world, true);
    RectangleThing thing =
        Assemblies.addWorld(world, null, Assemblies.createRectangle(world, null, null));
    thing.setBoundingBox(
        new Rectangle(location.x, location.y, defaultSize.width, defaultSize.height));
    thing.setRoundCorners(new Dimension(30, 30));

    mirrorLogic.mirrorValue(thing, IHasColor.COLOR_KEY, thing,
        IHasSecondaryColor.SECONDARY_COLOR_KEY, new Function<RGB, RGB>() {
          @Override
          @Nullable
          public RGB apply(@Nullable RGB input) {
            return BNAUtils.adjustBrightness(input, 1.2f);
          }
        });

    UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
        IHasMutableSize.USER_MAY_RESIZE, IHasMutableReferencePoint.USER_MAY_MOVE,
        HighlightLogic.USER_MAY_HIGHLIGHT, IHasMutableColor.USER_MAY_COPY_COLOR,
        IHasMutableColor.USER_MAY_EDIT_COLOR, IHasMutableAlpha.USER_MAY_CHANGE_ALPHA);
    UserEditableUtils.addEditableQualities(Assemblies.BOUNDED_TEXT_KEY.get(thing, model),
        IHasMutableText.USER_MAY_EDIT_TEXT);
    return thing;
  }

  @Override
  public void applyDefaults(RectangleThing thing) {
    super.applyDefaults(thing);
    thing.setColor(StatechartPreferences.getStateColor());
    applyPreferences(thing);
  }

  @Override
  public void propertyChange(PropertyChangeEvent event) {
    try (Finally lock = BNAUtils.lock()) {
      for (RectangleThing thing : getAddedThings()) {
        applyPreferences(thing);
      }
    }
  }

  public void applyPreferences(RectangleThing thing) {
    thing.setLineWidth(Archipelago2Preferences.getLineWidth());
    BoundedLabelThing label = Assemblies.BOUNDED_TEXT_KEY.get(thing, model);
    FontData labelFont = StatechartPreferences.getStateFont();
    label.setFontName(labelFont.getName());
    label.setFontSize(labelFont.getHeight());
    label.setFontStyle(FontStyle.fromSWT(labelFont.getStyle()));
  }

  protected void updateSubstructure(ObjRef objRef, XArchADTModelEvent evt,
      RectangleThing rootThing) {
    IHasMutableWorld worldThing = SystemUtils.castOrNull(
        BNAPath.resolve(model, rootThing, BNAPath.create(Assemblies.WORLD_KEY)),
        IHasMutableWorld.class);
    if (worldThing != null) {
      ObjRef substructureRef =
          (ObjRef) xarch.get(objRef, Statechart_1_0Package.Literals.SUB_STATECHART.getName());
      if (substructureRef != null) {
        ObjRef linkedRef = (ObjRef) xarch.get(substructureRef,
            Statechart_1_0Package.Literals.SUB_STATECHART__INNER_STATECHART.getName());
        if (linkedRef != null) {
          IBNAWorld substructureWorld =
              StatechartUtils.createStatechartWorld(xarch, linkedRef);
          worldThing.setWorld(substructureWorld);
          worldThing.set(IHasObjRef.OBJREF_KEY, substructureRef);
          return;
        }
      }
      // If the inner substructure was null then we need to remove the world from the worldThing.
      worldThing.remove(IHasWorld.WORLD_KEY);
      worldThing.remove(IHasObjRef.OBJREF_KEY);
    }
  }
}
