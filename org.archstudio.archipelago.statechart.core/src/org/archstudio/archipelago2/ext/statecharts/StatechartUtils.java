package org.archstudio.archipelago2.ext.statecharts;

import java.util.Map;

import org.archstudio.archipelago2.Activator;
import org.archstudio.archipelago2.MyxRegistryToProxyLogicDelegate;
import org.archstudio.archipelago2.ext.statecharts.logics.MapFinalStateLogic;
import org.archstudio.archipelago2.ext.statecharts.logics.MapInitialStateLogic;
import org.archstudio.archipelago2.ext.statecharts.logics.MapStateLogic;
import org.archstudio.archipelago2.ext.statecharts.logics.MapTransitionLogic;
import org.archstudio.archipelago2.ext.statecharts.logics.NewElementLogic;
import org.archstudio.archipelago2.ext.statecharts.logics.UpdateSubStatechartLogic;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.background.LifeSapperLogic;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.editing.AlignAndDistributeLogic;
import org.archstudio.bna.logics.editing.ClickSelectionLogic;
import org.archstudio.bna.logics.editing.DragMovableLogic;
import org.archstudio.bna.logics.editing.EditColorLogic;
import org.archstudio.bna.logics.editing.EditTextLogic;
import org.archstudio.bna.logics.editing.ExportImageLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.RectifyToGridLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.RestoreDefaultsLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.logics.editing.StandardCursorLogic;
import org.archstudio.bna.logics.events.ListenToSubWorldEventsLogic;
import org.archstudio.bna.logics.events.ProxyLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.logics.navigating.PanAndZoomLogic;
import org.archstudio.bna.logics.navigating.ViewAllLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.GridThing;
import org.archstudio.bna.things.utility.ShadowThing;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.archstudio.utils.bna.dot.ExportImportDot;
import org.archstudio.utils.bna.gexf.ExportImportGexf;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.archstudio.xadl.bna.logics.editing.RemoveElementLogic;
import org.archstudio.xadl.bna.logics.editing.XadlCopyPasteLogic;
import org.archstudio.xadl.bna.logics.editing.XadlReshapeSplineGuide;
import org.archstudio.xadl.bna.logics.hints.XadlHintRepository;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.MapMaker;

public class StatechartUtils extends AbstractThingLogic {
  private static final Map<ObjRef, IBNAWorld> EDIT_REF_TO_WORLD =
      new MapMaker().softValues().makeMap();

  public StatechartUtils(IBNAWorld world) {
    super(world);
  }

  synchronized public static IBNAWorld createStatechartWorld(IXArchADT xarch, ObjRef editRef) {
    // Retrieve existing world if it already exists.
    IBNAWorld world = EDIT_REF_TO_WORLD.get(editRef);
    if (world != null) {
      return world;
    }

    // It doesn't exist so create it.
    world = new DefaultBNAWorld(editRef.toString(), new DefaultBNAModel());
    EDIT_REF_TO_WORLD.put(editRef, world);
    GridThing.createIn(world);
    ShadowThing.createIn(world);
    EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
    ept.set(IHasObjRef.OBJREF_KEY, editRef);
    ept.set(IHasXArchID.XARCH_ID_KEY, (String) xarch.get(editRef, "id"));

    // These logics need to be first in order to affect later logics.
    IThingLogicManager logics = world.getThingLogicManager();
    ProxyLogic proxyLogic = logics.addThingLogic(new ProxyLogic(world,
        new MyxRegistryToProxyLogicDelegate(Activator.getDefault().getMyxBrick())));
    logics.addThingLogic(SnapToGridLogic.class);
    logics.addThingLogic(
        new SynchronizeHintsLogic(world, proxyLogic.addObject(new XadlHintRepository(xarch))));

    // Logics that map xADL elements to BNA things.
    logics.addThingLogic(
        new MapStateLogic(world, xarch, editRef, "state[@type='state']", "Loading States"));
    logics.addThingLogic(new MapInitialStateLogic(world, xarch, editRef, "state[@type='initial']",
        "Loading States"));
    logics.addThingLogic(
        new MapFinalStateLogic(world, xarch, editRef, "state[@type='final']", "Loading States"));
    logics.addThingLogic(
        new MapTransitionLogic(world, xarch, editRef, "transition", "Loading Transitions"));

    // Generic editor logics (organized alphabetically).
    logics.addThingLogic(ClickSelectionLogic.class);
    logics.addThingLogic(DragMovableLogic.class);
    logics.addThingLogic(ExportImageLogic.class);
    logics.addThingLogic(KeyNudgerLogic.class);
    logics.addThingLogic(LifeSapperLogic.class);
    logics.addThingLogic(ListenToSubWorldEventsLogic.class);
    logics.addThingLogic(MarqueeSelectionLogic.class);
    logics.addThingLogic(PanAndZoomLogic.class);
    logics.addThingLogic(ReshapeRectangleLogic.class);
    logics.addThingLogic(ReshapeSplineLogic.class)
        .addReshapeSplineGuides(new XadlReshapeSplineGuide(xarch,
            Statechart_1_0Package.Literals.TRANSITION, Statechart_1_0Package.Literals.PSEUDO_STATE,
            IHasEndpoints.ENDPOINT_1_KEY, IHasEndpoints.ENDPOINT_2_KEY));
    logics.addThingLogic(RotatingOffsetLogic.class);
    logics.addThingLogic(StandardCursorLogic.class);
    logics.addThingLogic(ToolTipLogic.class);

    // Menu logics, order determines menu order.
    logics.addThingLogic(new NewElementLogic(world, xarch, editRef));
    logics.addThingLogic(new UpdateSubStatechartLogic(world, xarch));
    logics.addThingLogic(new XadlCopyPasteLogic(world, xarch));
    logics.addThingLogic(new RemoveElementLogic(world, xarch));
    logics.addThingLogic(EditTextLogic.class);
    logics.addThingLogic(EditColorLogic.class);
    logics.addThingLogic(RestoreDefaultsLogic.class);
    logics.addThingLogic(HighlightLogic.class);
    logics.addThingLogic(ShowHideTagsLogic.class);
    logics.addThingLogic(AlignAndDistributeLogic.class);
    logics.addThingLogic(RectifyToGridLogic.class);
    logics.addThingLogic(ExportImportDot.class);
    logics.addThingLogic(ExportImportGexf.class);
    logics.addThingLogic(ViewAllLogic.class);

    return world;
  }
}
