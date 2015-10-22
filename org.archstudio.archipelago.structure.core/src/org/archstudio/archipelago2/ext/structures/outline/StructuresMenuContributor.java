package org.archstudio.archipelago2.ext.structures.outline;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2MenuContributor;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.archipelago2.IArchipelago2MenuContributor;
import org.archstudio.archipelago2.IArchipelago2Outline;
import org.archstudio.archipelago2.ext.structures.utils.MenuContributor;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.IMenuManager;

public class StructuresMenuContributor extends AbstractArchipelago2MenuContributor
    implements IArchipelago2MenuContributor {
  @Override
  public void fillMenu(IArchipelago2Outline outline, List<Object> element,
      IMenuManager menuManager) {
    final ObjRef xAdlRef = (ObjRef) xarch.get(docRef, "xADL");
    final ObjRef editRef = Archipelago2Utils.getLastObjRef(element);
    if (Archipelago2Utils.pathEndsWith(element, xAdlRef, "Structures")) {
      MenuContributor.addNewStructureAction(menuManager, xarch, xAdlRef, outline, element);
    }
    if (XadlUtils.isInstanceOf(xarch, editRef, Structure_3_0Package.Literals.STRUCTURE)) {
      MenuContributor.addNewComponentAction(menuManager, xarch, editRef, outline, element);
      MenuContributor.addNewConnectorAction(menuManager, xarch, editRef, outline, element);
      MenuContributor.addNewLinkAction(menuManager, xarch, editRef, outline, element);
    }
    if (XadlUtils.isInstanceOf(xarch, editRef, Structure_3_0Package.Literals.BRICK)) {
      MenuContributor.addNewInterfaceAction(menuManager, xarch, editRef, outline, element);
    }
  }
}
