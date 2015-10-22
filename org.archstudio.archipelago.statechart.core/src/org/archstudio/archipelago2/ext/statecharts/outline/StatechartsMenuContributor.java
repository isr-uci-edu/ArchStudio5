package org.archstudio.archipelago2.ext.statecharts.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2MenuContributor;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2MenuContributor;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.archipelago2.ext.statecharts.utils.MenuContributor;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.IMenuManager;

public class StatechartsMenuContributor extends AbstractArchipelago2MenuContributor
    implements IArchipelago2MenuContributor {
  @Override
  public void fillMenu(IArchipelago2Outline outline, List<Object> element,
      IMenuManager menuManager) {
    final ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
    final ObjRef editRef = Archipelago2Utils.getLastObjRef(element);
    if (Archipelago2Utils.pathEndsWith(element, xAdlRef, "Statecharts")) {
      MenuContributor.addNewStatechartAction(menuManager, xarch, xAdlRef, outline, element);
    }
    if (XadlUtils.isInstanceOf(xarch, editRef, Statechart_1_0Package.Literals.STATECHART)) {
      MenuContributor.addNewInitialStateAction(menuManager, xarch, editRef, outline, element);
      MenuContributor.addNewFinalStateAction(menuManager, xarch, editRef, outline, element);
      MenuContributor.addNewStateAction(menuManager, xarch, editRef, outline, element);
      MenuContributor.addNewTransitionAction(menuManager, xarch, editRef, outline, element);
    }
    if (XadlUtils.isInstanceOf(xarch, editRef, Statechart_1_0Package.Literals.STATE)) {
      MenuContributor.addClearSubStatechartAction(menuManager, xarch, editRef);
    }
  }
}
