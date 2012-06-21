package org.archstudio.bna;

import java.awt.Font;

import javax.media.opengl.GL2;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import com.jogamp.opengl.util.awt.TextRenderer;

public interface IResources {

	public Device getDevice();

	public Composite getComposite();

	public GL2 getGL();

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey);

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey, TextRenderer tr);

	public void setColor(RGB color, float alpha);

	public void setColor(RGB color, float alpha, TextRenderer tr);

	public boolean setLineStyle(IHasLineData thing);

	public Font getFont(IHasFontData thing);

	public Font getFont(IHasFontData thing, int size);

	public void destroy();

	public TextRenderer getTextRenderer(Font f);

}
