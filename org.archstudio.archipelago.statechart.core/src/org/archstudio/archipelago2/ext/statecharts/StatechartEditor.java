package org.archstudio.archipelago2.ext.statecharts;

import java.util.List;

import org.archstudio.archipelago2.AbstractArchipelago2BnaEditor;
import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;

/**
 * The Archipelago editor for statecharts, as defined by the {@link Statechart} xADL type.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class StatechartEditor extends AbstractArchipelago2BnaEditor {
  @Override
  public boolean canEdit(List<Object> elementPath) {
    ObjRef editRef = SystemUtils.castOrNull(elementPath.get(elementPath.size() - 1), ObjRef.class);
    return xarch.isInstanceOf(editRef, Statechart_1_0Package.eNS_URI,
        Statechart_1_0Package.Literals.STATECHART.getName());
  }

  @Override
  public void createPartControl(IEditorPart editorPart, Composite parent,
      List<Object> elementPath) {
    parent.setLayout(new FillLayout());
    ObjRef editRef = SystemUtils.castOrNull(elementPath.get(elementPath.size() - 1), ObjRef.class);
    try (Finally lock = BNAUtils.lock()) {
      bnaWorld = StatechartUtils.createStatechartWorld(xarch, editRef);
      bnaCanvas = new BNACanvas(parent, SWT.V_SCROLL | SWT.H_SCROLL, bnaWorld);
      bnaCanvas.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
      EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(bnaWorld);
      ept.monitorCoordinateMapper(bnaCanvas.getBNAView().getCoordinateMapper());
      setFocusControl(bnaCanvas);
      propertyChange(null);
    }
  }
}
