package org.archstudio.archipelago2.ext.structures.logics;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNAKeyListener2;
import org.archstudio.bna.ui.IBNAMouseClickListener2;
import org.archstudio.bna.ui.IBNAMouseMoveListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingReference;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.RGB;

import com.google.common.base.Preconditions;

public class CreateMappingLogic extends AbstractThingLogic implements IBNAKeyListener2,
    IBNAMouseMoveListener2, IBNAMouseClickListener2, IBNAAllEventsListener2 {
  IXArchADT xarch = null;
  ObjRef outerInterfaceRef = null;
  ObjRef subStructureRef = null;
  IBNAView splineView = null;
  IBNAWorld splineWorld = null;
  IBNAModel splineModel = null;
  SplineThing splineThing = null;
  IBNAView subStructureView = null;

  public CreateMappingLogic(IBNAWorld world) {
    super(world);
  }

  public void beginEditing(IBNAView splineView, IXArchADT xarch, ObjRef outerInterfaceRef,
      ObjRef subStructureRef) {
    endEditing();
    this.xarch = Preconditions.checkNotNull(xarch);
    this.outerInterfaceRef = Preconditions.checkNotNull(outerInterfaceRef);
    this.subStructureRef = Preconditions.checkNotNull(subStructureRef);
    this.splineView = Preconditions.checkNotNull(splineView);
    this.splineWorld = splineView.getBNAWorld();
    this.splineModel = splineWorld.getBNAModel();
    ThingValueTrackingLogic valueLogic =
        splineWorld.getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);

    // Find the brick and world within it.
    ObjRef brickRef = xarch.getParent(outerInterfaceRef);
    IThing brickThing =
        SystemUtils.firstOrNull(valueLogic.getThings(IHasObjRef.OBJREF_KEY, brickRef));
    if (brickThing == null) {
      endEditing();
      return;
    }
    IHasWorld worldThing = Assemblies.getPart(splineModel, brickThing, Assemblies.WORLD_KEY);
    if (worldThing == null) {
      endEditing();
      return;
    }

    // Find the view of the world.
    IThingPeer<IHasWorld> worldThingPeer = splineView.getThingPeer(worldThing);
    if (!(worldThingPeer instanceof IHasInnerViewPeer)) {
      endEditing();
      return;
    }
    subStructureView = ((IHasInnerViewPeer<IHasWorld>) worldThingPeer).getInnerView();

    // Find the outer interface thing.
    IThing ifaceThing =
        SystemUtils.firstOrNull(valueLogic.getThings(IHasObjRef.OBJREF_KEY, outerInterfaceRef));
    if (ifaceThing == null) {
      endEditing();
      return;
    }
    if (!(ifaceThing instanceof IHasAnchorPoint)) {
      endEditing();
      return;
    }

    // Create the spline thing.
    splineThing = splineModel.addThing(new SplineThing(null));
    // splineModel.moveAfter(splineThing, ifaceThing);
    splineThing.setEdgeColor(new RGB(255, 0, 0));
    splineThing.setEndpoint1(((IHasAnchorPoint) ifaceThing).getAnchorPoint());
  }

  private void endEditing() {
    if (splineView != null) {
      model.removeThing(splineThing);
      xarch = null;
      outerInterfaceRef = null;
      subStructureRef = null;
      splineView = null;
      splineWorld = null;
      splineModel = null;
      splineThing = null;
      subStructureView = null;
    }
  }

  @Override
  public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {
    if (splineView != null) {
      Point2D localPoint =
          BNAUtils.toPoint2D(thingsAtLocation.getOriginalLocation().getLocalPoint());
      Point2D worldPoint = splineView.getCoordinateMapper().localToWorld(localPoint);
      splineThing.setEndpoint2(worldPoint);
    }
  }

  @Override
  public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {}

  @Override
  public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {}

  @Override
  public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {
    if (splineView != null) {
      try {
        DefaultCoordinate thisViewCoordinate =
            DefaultCoordinate.forLocal(thingsAtLocation.getOriginalLocation().getLocalPoint(),
                subStructureView.getCoordinateMapper());
        ThingsAtLocation reference =
            BNAUtils2.getThingsAtLocation(subStructureView, thisViewCoordinate);
        if (reference.getThingAtLocation() != null) {
          ThingReference thingReference = reference.getThingAtLocation();
          IThing objRefThing =
              Assemblies.getThingWithProperty(subStructureView.getBNAWorld().getBNAModel(),
                  thingReference.getThing(), IHasObjRef.OBJREF_KEY);
          ObjRef innerInterfaceRef =
              objRefThing != null ? objRefThing.get(IHasObjRef.OBJREF_KEY) : null;
          if (innerInterfaceRef != null && XadlUtils.isInstanceOf(xarch, innerInterfaceRef,
              Structure_3_0Package.Literals.INTERFACE)) {
            ObjRef newInterfaceMappingRef =
                XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE_MAPPING);
            xarch.set(newInterfaceMappingRef, "id", UIDGenerator.generateUID());
            XadlUtils.setName(xarch, newInterfaceMappingRef, "[New Interface-Interface Mapping]");
            xarch.set(newInterfaceMappingRef,
                Structure_3_0Package.Literals.INTERFACE_MAPPING__OUTER_INTERFACE_LINK.getName(),
                outerInterfaceRef);
            xarch.set(newInterfaceMappingRef,
                Structure_3_0Package.Literals.INTERFACE_MAPPING__INNER_INTERFACE_LINK.getName(),
                innerInterfaceRef);
            XArchADTOperations.add("Add Interface Mapping", xarch, subStructureRef,
                Structure_3_0Package.Literals.SUB_STRUCTURE__INTERFACE_MAPPING.getName(),
                newInterfaceMappingRef);
          }
        }
      } finally {
        endEditing();
      }
    }
  }

  @Override
  public void keyPressed(IBNAView view, KeyType type, KeyEvent e) {}

  @Override
  public void keyReleased(IBNAView view, KeyType type, KeyEvent e) {
    if (e.character == SWT.ESC) {
      endEditing();
    }
  }

}
