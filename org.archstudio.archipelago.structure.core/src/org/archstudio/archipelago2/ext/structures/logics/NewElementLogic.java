package org.archstudio.archipelago2.ext.structures.logics;

import org.archstudio.archipelago2.ext.structures.utils.MenuContributor;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Point;

public class NewElementLogic extends AbstractThingLogic implements IBNAMenuListener2 {
  protected final IXArchADT xarch;
  protected final ObjRef editRef;

  public NewElementLogic(IBNAWorld world, IXArchADT xarch, ObjRef editRef) {
    super(world);
    this.xarch = xarch;
    this.editRef = editRef;
  }

  @Override
  public void fillMenu(IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
      IMenuManager menuManager) {
    Point worldPoint = location.getWorldPoint();
    BNAUtils2.setNewThingSpot(view.getBNAWorld(), worldPoint.x, worldPoint.y);
    if (thingsAtLocation.getViewAtLocation() != null) {
      MenuContributor.addNewComponentAction(menuManager, xarch, editRef, null, null);
      MenuContributor.addNewConnectorAction(menuManager, xarch, editRef, null, null);
      MenuContributor.addNewLinkAction(menuManager, xarch, editRef, null, null);
    } else {
      IThing thing = Assemblies.getThingWithProperty(model,
          thingsAtLocation.getThingAtLocation().getThing(), IHasObjRef.OBJREF_KEY);
      if (thing != null) {
        ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
        if (XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.BRICK)) {
          MenuContributor.addNewInterfaceAction(menuManager, xarch, objRef, null, null);
        }
        if (XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.INTERFACE)) {
          MenuContributor.addNewMapping(menuManager, xarch, view, thing, location.getLocalPoint());
        }
      }
    }
  }
}
