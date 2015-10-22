package org.archstudio.archipelago2.ext.statecharts.logics;

import org.archstudio.archipelago2.ext.statecharts.utils.MenuContributor;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
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
      MenuContributor.addNewInitialStateAction(menuManager, xarch, editRef, null, null);
      MenuContributor.addNewFinalStateAction(menuManager, xarch, editRef, null, null);
      MenuContributor.addNewStateAction(menuManager, xarch, editRef, null, null);
      MenuContributor.addNewTransitionAction(menuManager, xarch, editRef, null, null);
    }
  }
}
