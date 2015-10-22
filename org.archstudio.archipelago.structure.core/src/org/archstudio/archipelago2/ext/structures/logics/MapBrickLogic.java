package org.archstudio.archipelago2.ext.structures.logics;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago2.Archipelago2Preferences;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.ext.statecharts.StatechartUtils;
import org.archstudio.archipelago2.ext.statecharts.logics.UpdateStatechartSpecLogic;
import org.archstudio.archipelago2.ext.structures.StructureUtils;
import org.archstudio.archipelago2.ext.structures.StructurePreferences;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
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
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

/**
 * Maps xADL Bricks (i.e., Components or Connectors) to BNA Rectangle Assemblies.
 */
public abstract class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleThing>
    implements IPropertyChangeListener {
  protected final MirrorValueLogic mirrorLogic;
  protected Dimension defaultSize;
  protected int defaultCount;

  public MapBrickLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      int defaultCount, Dimension defaultSize, String mappingDescription) {
    super(world, xarch, rootObjRef, objRefPath);
    this.defaultCount = defaultCount;
    this.defaultSize = defaultSize;
    mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
    logics.addThingLogic(ListenToSubWorldEventsLogic.class);

    syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(Assemblies.BOUNDED_TEXT_KEY),
        IHasText.TEXT_KEY, true);
    syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
    addBNAUpdater(Structure_3_0Package.Literals.BRICK__SUB_STRUCTURE.getName(), new IBNAUpdater() {
      @Override
      public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt,
          RectangleThing rootThing) {
        updateSubstructure(objRef, evt, rootThing);
      }
    });
    addXPathBNAUpdater(
        "ext[*[namespace-uri()='" + Statechart_1_0Package.eINSTANCE.getNsURI() + "']]",
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
        .weakListenToPropertyChanges(StructurePreferences.getActivatorPreferenceStore(), this);
  }

  @Override
  protected RectangleThing addThing(List<ObjRef> descendantRefs, ObjRef objRef) {
    Point location = BNAUtils2.getNewThingSpot(world, true);
    RectangleThing thing =
        Assemblies.addWorld(world, null, Assemblies.createRectangle(world, null, null));
    thing.setBoundingBox(
        new Rectangle(location.x, location.y, defaultSize.width, defaultSize.height));
    thing.setCount(defaultCount);

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
        IHasMutableColor.USER_MAY_COPY_COLOR, IHasMutableColor.USER_MAY_EDIT_COLOR,
        UpdateStatechartSpecLogic.USER_MAY_SET_STATECHART_SPEC);
    UserEditableUtils.addEditableQualities(Assemblies.BOUNDED_TEXT_KEY.get(thing, model),
        IHasMutableText.USER_MAY_EDIT_TEXT);
    return thing;
  }

  @Override
  public void applyDefaults(RectangleThing thing) {
    super.applyDefaults(thing);
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
  }

  protected void updateSubstructure(ObjRef objRef, XArchADTModelEvent evt,
      RectangleThing rootThing) {
    IHasMutableWorld worldThing = SystemUtils.castOrNull(
        BNAPath.resolve(model, rootThing, BNAPath.create(Assemblies.WORLD_KEY)),
        IHasMutableWorld.class);
    if (worldThing != null) {
      ObjRef substructureRef =
          (ObjRef) xarch.get(objRef, Structure_3_0Package.Literals.BRICK__SUB_STRUCTURE.getName());
      if (substructureRef != null) {
        ObjRef linkedRef = (ObjRef) xarch.get(substructureRef,
            Structure_3_0Package.Literals.SUB_STRUCTURE__INNER_STRUCTURE_LINK.getName());
        if (linkedRef != null) {
          IBNAWorld substructureWorld = StructureUtils.createStructureWorld(xarch, linkedRef);
          worldThing.setWorld(substructureWorld);
          worldThing.set(IHasObjRef.OBJREF_KEY, substructureRef);
          return;
        }
      }
      ObjRef statechartSpec = XadlUtils.getExt(xarch, objRef, Statechart_1_0Package.eNS_URI,
          Statechart_1_0Package.Literals.STATECHART_SPECIFICATION.getName());
      if (statechartSpec != null) {
        ObjRef statechartRef = (ObjRef) xarch.get(statechartSpec,
            Statechart_1_0Package.Literals.STATECHART_SPECIFICATION__STATECHART.getName());
        if (statechartRef != null) {
          IBNAWorld statechartWorld =
              StatechartUtils.createStatechartWorld(xarch, statechartRef);
          worldThing.setWorld(statechartWorld);
          return;
        }
      }
      // If the inner substructure was null then we need to remove the world from the worldThing.
      worldThing.remove(IHasWorld.WORLD_KEY);
      worldThing.remove(IHasObjRef.OBJREF_KEY);
    }
  }
}
