package org.archstudio.archipelago2.ext.statecharts.logics;

import org.archstudio.archipelago2.ext.statecharts.utils.MenuContributor;
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
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.jface.action.IMenuManager;

public class UpdateSubStatechartLogic extends AbstractThingLogic
    implements IBNADragAndDropListener2, IBNAMenuListener2, IBNAAllEventsListener2 {
  protected final IXArchADT xarch;

  public UpdateSubStatechartLogic(IBNAWorld world, IXArchADT xarch) {
    super(world);
    this.xarch = xarch;
  }

  protected ObjRef getStateRef(IBNAView view, IThing thing) {
    IThing o = Assemblies.getThingWithProperty(view.getBNAWorld().getBNAModel(), thing,
        IHasObjRef.OBJREF_KEY);
    if (o != null) {
      ObjRef stateRef = o.get(IHasObjRef.OBJREF_KEY);
      if (XadlUtils.isInstanceOf(xarch, stateRef, Statechart_1_0Package.Literals.STATE)) {
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
        ObjRef stateRef = getStateRef(thingRef.getView(), thingRef.getThing());
        if (stateRef != null) {
          if (data.getDataTypes().isEmpty()) {
            data.setDropType(DNDActionType.LINK);
            return;
          }
          ObjRef statechartRef = data.getData(ObjRef.class);
          if (XadlUtils.isInstanceOf(xarch, statechartRef,
              Statechart_1_0Package.Literals.STATECHART)) {
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
        ObjRef stateRef = getStateRef(thingRef.getView(), thingRef.getThing());
        if (stateRef != null) {
          ObjRef statechartRef = data.getData(ObjRef.class);
          if (XadlUtils.isInstanceOf(xarch, statechartRef,
              Statechart_1_0Package.Literals.STATECHART)) {
            SubStatechart subStatechart =
                XArchADTProxy.create(xarch, Statechart_1_0Package.Literals.SUB_STATECHART);
            subStatechart.setId(UIDGenerator.generateUID());
            subStatechart.setInnerStatechart(XArchADTProxy.<Statechart>proxy(xarch, statechartRef));
            State state = XArchADTProxy.proxy(xarch, stateRef);
            state.setSubStatechart(subStatechart);
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
        ObjRef stateRef = getStateRef(thingRef.getView(), thingRef.getThing());
        if (stateRef != null) {
          State state = XArchADTProxy.proxy(xarch, stateRef);
          if (state.getSubStatechart() != null) {
            MenuContributor.addClearSubStatechartAction(menuManager, xarch, stateRef);
          }
        }
      }
    }
  }
}
