package org.archstudio.archipelago2.core.outline;

import org.archstudio.archipelago2.AbstractArchipelago2LabelDecorator;
import org.archstudio.archipelago2.Archipelago2Utils;
import org.archstudio.utils.resources.swt.ColorUtils;
import org.archstudio.utils.resources.swt.FontUtils;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;

public class ObjRefLabelDecorator extends AbstractArchipelago2LabelDecorator {
  @Override
  public StyledString decorateStyledText(StyledString styledString, Object element) {
    ObjRef objRef = Archipelago2Utils.getLastObjRef(element);
    if (objRef != null) {
      IXArchADTTypeMetadata type = xarch.getTypeMetadata(objRef);
      styledString.append(" ").append("(" + type.getTypeName() + ")", new Styler() {
        @Override
        public void applyStyles(TextStyle textStyle) {
          Font defaultFont = JFaceResources.getDefaultFont();
          textStyle.font = FontUtils.getFont(display, "Arial Narrow",
              defaultFont.getFontData()[0].height, SWT.ITALIC);
          textStyle.foreground = ColorUtils.getSystemColor(display, SWT.COLOR_GRAY);
        }
      });
    }
    return null;
  }
}
