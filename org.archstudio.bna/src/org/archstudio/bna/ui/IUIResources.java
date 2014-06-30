package org.archstudio.bna.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public interface IUIResources {

	public static interface FontMetrics {

		/**
		 * Returns the leading area of the font described by the receiver. A font's <em>leading area</em> is the space
		 * above its ascent which may include accents or other marks.
		 */
		public float getLeading();

		/**
		 * Returns the ascent of the font described by the receiver. A font's <em>ascent</em> is the distance from the
		 * baseline to the top of actual characters, not including any of the leading area, measured in pixels.
		 */
		public float getAscent();

		/**
		 * Returns the descent of the font described by the receiver. A font's <em>descent</em> is the distance from the
		 * baseline to the bottom of actual characters, not including any of the leading area, measured in pixels.
		 */
		public float getDescent();

	}

	public boolean isAntialiasGraphics();

	public void setAntialiasGraphics(boolean antialiasGraphics);

	public boolean isAntialiasText();

	public void setAntialiasText(boolean antialiasText);

	public boolean isDecorativeGraphics();

	public void setDecorativeGraphics(boolean decorativeGraphics);

	public boolean isDisplayShadows();

	public void setDisplayShadows(boolean displayShadows);

	public void renderTopLevelThings(IBNAView view, Rectangle localBounds);

	public void renderThings(IBNAView view, Rectangle localBounds);

	public double pushAlpha(double alpha);

	public double popAlpha();

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey);

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey, double alpha);

	public boolean setColor(RGB color, double alpha);

	public boolean setLineStyle(IHasLineData thing);

	public boolean setLineStyle(int width, int stipple);

	public void resetLineStyle();

	public Font getFont(IHasFontData thing, ICoordinateMapper cm);

	public Font getFont(IHasFontData thing, int size);

	public FontMetrics getFontMetrics(Font font);

	public Dimension getTextSize(Font font, String text);

	/**
	 * Draws text of the given font at the given (x, y) coordinates, where the coordinate mark the top-left corner of
	 * the bounding box containing the leading, ascent and descent of the text.
	 */

	public void drawText(Font font, String text, double x, double y);

	public void drawShape(Point2D localShape);

	public void drawShape(Shape localShape);

	public void glowShape(Shape localShape, RGB color, int width, double alpha);

	public void selectShape(Shape localShape, int offset);

	public void fillShape(Shape localShape);

	public void fillShape(Shape localShape, RGB color1, RGB color2);

	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha);

	public void pushMatrix(double x, double y, double angle);

	public void popMatrix();

}