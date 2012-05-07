package org.archstudio.bna;

import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

public interface IResources {

	public Device getDevice();

	public Composite getComposite();

	public Color getColor(int systemColor);

	public Color getColor(RGB color);

	public Font getFont(String fontName, int size, FontStyle style);

	public Image getImage(ImageData imageData);

}
