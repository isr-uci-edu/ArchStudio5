package org.archstudio.archipelago2.ext.structures.logics;

import org.archstudio.archipelago2.ext.structures.utils.MenuContributor;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DNDActionType;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNADragAndDropListener2;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils2.ThingReference;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.structure_3_0.Brick;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.structure_3_0.SubStructure;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.jface.action.IMenuManager;

public class UpdateSubStructureLogic extends AbstractThingLogic
    implements IBNADragAndDropListener2, IBNAMenuListener2, IBNAAllEventsListener2 {
  protected final IXArchADT xarch;

  public UpdateSubStructureLogic(IBNAWorld world, IXArchADT xarch) {
    super(world);
    this.xarch = xarch;
  }

  protected ObjRef getBrickRef(IBNAView view, IThing thing) {
    IThing o = Assemblies.getThingWithProperty(view.getBNAWorld().getBNAModel(), thing,
        IHasObjRef.OBJREF_KEY);
    if (o != null) {
      ObjRef stateRef = o.get(IHasObjRef.OBJREF_KEY);
      if (XadlUtils.isInstanceOf(xarch, stateRef, Structure_3_0Package.Literals.BRICK)) {
        return stateRef;
      }
    }
    return null;
  }

  @Override
  public void drag(IBNAView view, DNDType type, DNDData data, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {
    if (view.getBNAWorld() != world) {
      return;
    }
    if (thingsAtLocation.getBackgroundThingAtLocation() != null) {
      ThingReference thingRef = thingsAtLocation.getBackgroundThingAtLocation();
      if (thingRef.getView().getBNAWorld() == world) {
        ObjRef brickRef = getBrickRef(thingRef.getView(), thingRef.getThing());
        if (brickRef != null) {
          if (data.getDataTypes().isEmpty()) {
            data.setDropType(DNDActionType.LINK);
            return;
          }
          ObjRef structureRef = data.getData(ObjRef.class);
          if (XadlUtils.isInstanceOf(xarch, structureRef,
              Structure_3_0Package.Literals.STRUCTURE)) {
            data.setDropType(DNDActionType.LINK);
            return;
          }
        }
      }
    }
  }

  @Override
  public void drop(IBNAView view, DNDType type, DNDData data, ICoordinate location,
      ThingsAtLocation thingsAtLocation) {
    if (view.getBNAWorld() != world) {
      return;
    }
    if (thingsAtLocation.getBackgroundThingAtLocation() != null) {
      ThingReference thingRef = thingsAtLocation.getBackgroundThingAtLocation();
      if (thingRef.getView().getBNAWorld() == world) {
        ObjRef brickRef = getBrickRef(thingRef.getView(), thingRef.getThing());
        if (brickRef != null) {
          ObjRef structureRef = data.getData(ObjRef.class);
          if (XadlUtils.isInstanceOf(xarch, structureRef,
              Structure_3_0Package.Literals.STRUCTURE)) {
            SubStructure subStructure =
                XArchADTProxy.create(xarch, Structure_3_0Package.Literals.SUB_STRUCTURE);
            subStructure.setId(UIDGenerator.generateUID());
            subStructure.setInnerStructureLink(XArchADTProxy.<Structure>proxy(xarch, structureRef));
            Brick brick = XArchADTProxy.proxy(xarch, brickRef);
            brick.setSubStructure(subStructure);
          }
        }
      }
    }
  }

  @Override
  public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
      IMenuManager menuManager) {
    if (view.getBNAWorld() != world) {
      return;
    }
    if (thingsAtLocation.getBackgroundThingAtLocation() != null) {
      ThingReference thingRef = thingsAtLocation.getBackgroundThingAtLocation();
      if (thingRef.getView().getBNAWorld() == world) {
        ObjRef brickRef = getBrickRef(thingRef.getView(), thingRef.getThing());
        if (brickRef != null) {
          Brick brick = XArchADTProxy.proxy(xarch, brickRef);
          if (brick.getSubStructure() != null) {
            MenuContributor.addClearSubStructureAction(menuManager, xarch, brickRef);
          }
        }
      }
    }
  }
}
