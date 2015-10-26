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
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.StatechartSpecification;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.jface.action.IMenuManager;

public class UpdateStatechartSpecLogic extends AbstractThingLogic
    implements IBNADragAndDropListener2, IBNAMenuListener2, IBNAAllEventsListener2 {
  public static final String USER_MAY_SET_STATECHART_SPEC = "UserMaySetStatechartSpec";

  protected final IXArchADT xarch;

  public UpdateStatechartSpecLogic(IBNAWorld world, IXArchADT xarch) {
    super(world);
    this.xarch = xarch;
  }

  protected ObjRef getObjRef(IBNAView view, IThing thing) {
    if (Assemblies.getEditableThing(view.getBNAWorld().getBNAModel(), thing, IThing.class,
        USER_MAY_SET_STATECHART_SPEC) != null) {
      IThing o = Assemblies.getThingWithProperty(view.getBNAWorld().getBNAModel(), thing,
          IHasObjRef.OBJREF_KEY);
      if (o != null) {
        return o.get(IHasObjRef.OBJREF_KEY);
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
        ObjRef objRef = getObjRef(thingRef.getView(), thingRef.getThing());
        if (objRef != null) {
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
        ObjRef objRef = getObjRef(thingRef.getView(), thingRef.getThing());
        if (objRef != null) {
          ObjRef statechartRef = data.getData(ObjRef.class);
          if (XadlUtils.isInstanceOf(xarch, statechartRef,
              Statechart_1_0Package.Literals.STATECHART)) {
            StatechartSpecification statechartSpec =
                XArchADTProxy.getExtension(xarch, XArchADTProxy.proxy(xarch, objRef),
                    Statechart_1_0Package.Literals.STATECHART_SPECIFICATION, true);
            statechartSpec.setId(UIDGenerator.generateUID());
            statechartSpec.setStatechart(XArchADTProxy.<Statechart>proxy(xarch, statechartRef));
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
        ObjRef objRef = getObjRef(thingRef.getView(), thingRef.getThing());
        if (objRef != null) {
          MenuContributor.addClearStatechartSpecAction(menuManager, xarch, objRef);
        }
      }
    }
  }
}
