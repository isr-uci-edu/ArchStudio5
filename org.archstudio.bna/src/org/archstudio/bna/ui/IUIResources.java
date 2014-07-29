package org.archstudio.bna.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.Disposable;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public interface IUIResources extends Disposable {

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

	public Font getFont(String name, FontStyle style, int size);

	public FontMetrics getFontMetrics(Font font);

	public Dimension getTextSize(Font font, String text);

	/**
	 * Draws text of the given font at the given (x, y) coordinates, where the coordinate mark the top-left corner of
	 * the bounding box containing the leading, ascent and descent of the text.
	 */

	public void drawText(Font font, String text, double x, double y, RGB color, double alpha);

	public void drawShape(Point2D localShape, RGB color, double alpha);

	public void drawShape(Shape localShape, RGB color, int width, LineStyle style, double alpha);

	public void drawShape(Shape localShape, RGB color, int width, int stipple, double alpha);

	public void glowShape(Shape localShape, RGB color, int width, double alpha);

	public void selectShape(Shape localShape, int offset);

	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha);

	public void pushMatrix(double x, double y, double angle);

	public void popMatrix();

}