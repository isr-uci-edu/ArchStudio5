package org.archstudio.archipelago2.ext.structures;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2BnaEditor;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

/**
 * The Archipelago editor for structures, as defined by the {@link Structure} xADL type.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class StructureEditor extends AbstractArchipelago2BnaEditor {
  @Override
  public boolean canEdit(List<Object> elementPath) {
    ObjRef editRef = SystemUtils.castOrNull(elementPath.get(elementPath.size() - 1), ObjRef.class);
    return xarch.isInstanceOf(editRef, Structure_3_0Package.eNS_URI,
        Structure_3_0Package.Literals.STRUCTURE.getName());
  }

  @Override
  public void createPartControl(IEditorPart editorPart, Composite parent,
      List<Object> elementPath) {
    parent.setLayout(new FillLayout());
    ObjRef editRef = SystemUtils.castOrNull(elementPath.get(elementPath.size() - 1), ObjRef.class);
    try (Finally lock = BNAUtils.lock()) {
      bnaWorld = StructureUtils.createStructureWorld(xarch, editRef);
      bnaCanvas = new BNACanvas(parent, SWT.V_SCROLL | SWT.H_SCROLL, bnaWorld);
      bnaCanvas.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
      EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(bnaWorld);
      ept.monitorCoordinateMapper(bnaCanvas.getBNAView().getCoordinateMapper());
      setFocusControl(bnaCanvas);
      propertyChange(null);
    }
  }
}
