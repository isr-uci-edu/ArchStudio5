package org.archstudio.bna;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

public interface IResources {

	public Device getDevice();

	public Composite getComposite();

	public void dispose();

	public Color getColor(RGB color);

	public Color getColor(int systemColor);

	public boolean setForegroundColor(Graphics g, IThing thing, IThingKey<RGB> colorKey);

	public boolean setBackgroundColor(Graphics g, IThing thing, IThingKey<RGB> colorKey);

	public Font getFont(String fontName, int size, FontStyle style);

	public boolean setFont(Graphics g, IHasFontData thing);

	public boolean setLineStyle(Graphics g, IHasLineData thing);

}
