package org.archstudio.archipelago2.ext.structures;

import java.util.Map;

import org.archstudio.archipelago2.Activator;
import org.archstudio.archipelago2.MyxRegistryToProxyLogicDelegate;
import org.archstudio.archipelago2.ext.statecharts.logics.UpdateStatechartSpecLogic;
import org.archstudio.archipelago2.ext.structures.logics.AssignMyxGenLogic;
import org.archstudio.archipelago2.ext.structures.logics.MapComponentLogic;
import org.archstudio.archipelago2.ext.structures.logics.MapConnectorLogic;
import org.archstudio.archipelago2.ext.structures.logics.MapInterfaceLogic;
import org.archstudio.archipelago2.ext.structures.logics.MapLinkLogic;
import org.archstudio.archipelago2.ext.structures.logics.MapMappingsLogic;
import org.archstudio.archipelago2.ext.structures.logics.NewElementLogic;
import org.archstudio.archipelago2.ext.structures.logics.UpdateSubStructureLogic;
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
import org.archstudio.bna.logics.editing.EditFlowLogic;
import org.archstudio.bna.logics.editing.EditTextLogic;
import org.archstudio.bna.logics.editing.ExportImageLogic;
import org.archstudio.bna.logics.editing.KeyNudgerLogic;
import org.archstudio.bna.logics.editing.MarqueeSelectionLogic;
import org.archstudio.bna.logics.editing.RectifyToGridLogic;
import org.archstudio.bna.logics.editing.ReshapeRectangleLogic;
import org.archstudio.bna.logics.editing.ReshapeSplineLogic;
import org.archstudio.bna.logics.editing.RestoreDefaultsLogic;
import org.archstudio.bna.logics.editing.RotaterLogic;
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
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.MapMaker;

public class StructureUtils extends AbstractThingLogic {
  private static final Map<ObjRef, IBNAWorld> EDIT_REF_TO_WORLD =
      new MapMaker().softValues().makeMap();

  public StructureUtils(IBNAWorld world) {
    super(world);
  }

  synchronized public static IBNAWorld createStructureWorld(IXArchADT xarch, ObjRef editRef) {
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
        new MapComponentLogic(world, xarch, editRef, "component", "Loading Components"));
    logics.addThingLogic(new MapInterfaceLogic(world, xarch, editRef, "component/interface",
        "Loading Component Interfaces"));
    logics.addThingLogic(new MapMappingsLogic(world, xarch, editRef,
        "component/subStructure/interfaceMapping", "Loading Component Interface Mappings"));
    logics.addThingLogic(
        new MapConnectorLogic(world, xarch, editRef, "connector", "Loading Connectors"));
    logics.addThingLogic(new MapInterfaceLogic(world, xarch, editRef, "connector/interface",
        "Loading Connector Interfaces"));
    logics.addThingLogic(new MapMappingsLogic(world, xarch, editRef,
        "connector/subStructure/interfaceMapping", "Loading Connector Interface Mappings"));
    logics.addThingLogic(new MapLinkLogic(world, xarch, editRef, "link", "Loading Links"));

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
            Structure_3_0Package.Literals.LINK, Structure_3_0Package.Literals.INTERFACE,
            IHasEndpoints.ENDPOINT_1_KEY, IHasEndpoints.ENDPOINT_2_KEY));
    logics.addThingLogic(RotatingOffsetLogic.class);
    logics.addThingLogic(StandardCursorLogic.class);
    logics.addThingLogic(ToolTipLogic.class);

    // Menu logics, order determines menu order.
    logics.addThingLogic(new NewElementLogic(world, xarch, editRef));
    logics.addThingLogic(new UpdateSubStructureLogic(world, xarch));
    logics.addThingLogic(new UpdateStatechartSpecLogic(world, xarch));
    logics.addThingLogic(new XadlCopyPasteLogic(world, xarch));
    logics.addThingLogic(new RemoveElementLogic(world, xarch));
    logics.addThingLogic(new AssignMyxGenLogic(world, xarch));
    logics.addThingLogic(EditTextLogic.class);
    logics.addThingLogic(EditFlowLogic.class);
    logics.addThingLogic(EditColorLogic.class);
    logics.addThingLogic(HighlightLogic.class);
    logics.addThingLogic(RestoreDefaultsLogic.class);
    logics.addThingLogic(ShowHideTagsLogic.class);
    logics.addThingLogic(RotaterLogic.class);
    logics.addThingLogic(AlignAndDistributeLogic.class);
    logics.addThingLogic(RectifyToGridLogic.class);
    logics.addThingLogic(ExportImportDot.class);
    logics.addThingLogic(ExportImportGexf.class);
    logics.addThingLogic(ViewAllLogic.class);
    logics.addThingLogic(ExportImageLogic.class);

    return world;
  }
}
