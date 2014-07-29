package org.archstudio.bna.ui.swt;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Deque;
import java.util.LinkedList;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasHidden;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.utils.AbstractUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.ExpandableIntBuffer;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Display;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class SWTResources extends AbstractUIResources implements ISWTResources {

	public static final int GLOW_RESOLUTION = 2;

	protected final LoadingCache<RGB, Color> colors = CacheBuilder.newBuilder().maximumSize(16)
			.removalListener(new RemovalListener<RGB, Color>() {
				@Override
				public void onRemoval(RemovalNotification<RGB, Color> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<RGB, Color>() {
				@Override
				public Color load(RGB rgb) throws Exception {
					return new Color(gc.getDevice(), rgb);
				}
			});
	protected final LoadingCache<Font, org.eclipse.swt.graphics.Font> fonts = CacheBuilder.newBuilder().maximumSize(16)
			.removalListener(new RemovalListener<Font, org.eclipse.swt.graphics.Font>() {
				@Override
				public void onRemoval(RemovalNotification<Font, org.eclipse.swt.graphics.Font> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<Font, org.eclipse.swt.graphics.Font>() {
				@Override
				public org.eclipse.swt.graphics.Font load(Font font) throws Exception {
					return new org.eclipse.swt.graphics.Font(//
							gc.getDevice(), font.getFamily(), font.getSize(), //
							FontStyle.fromAWT(font.getStyle()).toSWT());
				}
			});
	protected final LoadingCache<Integer, int[]> stipples = CacheBuilder.newBuilder().maximumSize(16)
			.build(new CacheLoader<Integer, int[]>() {
				@Override
				public int[] load(Integer key) throws Exception {
					return LineStyle.toSWTDashes(key);
				}
			});

	protected GC gc;
	protected Transform currentTransform = null;
	protected Deque<Transform> matrices = new LinkedList<Transform>();

	public SWTResources() {
	}

	public void invalidate() {
		colors.invalidateAll();
		fonts.invalidateAll();
		if (lastPath != null) {
			lastPath.dispose();
		}
		lastPath = null;
	}

	@Override
	public void dispose() {
		invalidate();
		super.dispose();
	}

	Shape lastShape = null;
	int lastPoints;
	Rectangle lastBounds;
	float lastMinY, lastMaxY;
	ExpandableIntBuffer lastXYIntBuffer = new ExpandableIntBuffer();
	int[] lastXY;
	Path lastPath = null;
	boolean lastClosed = false;

	private void updateLastShape(Shape localShape) {
		if (lastShape != localShape) {
			lastShape = localShape;

			// determine min y, max y
			lastBounds = BNAUtils.toRectangle(localShape.getBounds2D());
			lastMinY = lastBounds.y;
			lastMaxY = lastBounds.y + lastBounds.height;

			// get xy positions
			lastXYIntBuffer.rewind();
			BNAUtils.toXYIntBuffer(localShape, lastXYIntBuffer);
			lastPoints = lastXYIntBuffer.position() / 2;
			lastXY = new int[lastPoints * 2];
			lastXYIntBuffer.rewind();
			lastXYIntBuffer.get(lastXY);

			// create path
			if (lastPath != null) {
				lastPath.dispose();
			}
			lastPath = new Path(gc.getDevice());
			BNAUtils.toPath(lastPath, localShape);

			// check for closure
			lastClosed = false;
			if (lastXY.length >= 4) {
				lastClosed = lastXY[0] == lastXY[lastXY.length - 2] && lastXY[1] == lastXY[lastXY.length - 1];
			}
		}
	}

	public void setGc(GC gc) {
		this.gc = gc;
		gc.setLineJoin(SWT.JOIN_BEVEL);
	}

	@Override
	public void setAntialiasGraphics(boolean antialiasGraphics) {
		super.setAntialiasGraphics(antialiasGraphics);
		gc.setAntialias(isAntialiasGraphics() ? SWT.ON : SWT.OFF);
	}

	@Override
	public void setAntialiasText(boolean antialiasText) {
		super.setAntialiasText(antialiasText);
		gc.setTextAntialias(isAntialiasText() ? SWT.ON : SWT.OFF);
	}

	protected void setForeground(RGB rgb) {
		gc.setForeground(colors.getUnchecked(rgb));
	}

	protected void setBackground(RGB rgb) {
		gc.setBackground(colors.getUnchecked(rgb));
	}

	@Override
	public double pushAlpha(double alpha) {
		alpha = super.pushAlpha(alpha);
		gc.setAlpha(SystemUtils.bound(0, SystemUtils.round(alpha * 255), 255));
		return alpha;
	}

	protected Rectangle toRectangle(RectangularShape r) {
		int x = SystemUtils.round(r.getMinX());
		int y = SystemUtils.round(r.getMinY());
		int w = SystemUtils.round(r.getMaxX()) - x;
		int h = SystemUtils.round(r.getMaxY()) - y;
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void renderTopLevelThings(IBNAView view, Rectangle localBounds) {
		setBackground(new RGB(255, 255, 255));
		gc.fillRectangle(localBounds);
		renderThings(view, localBounds);
	}

	@Override
	public void renderThings(IBNAView view, Rectangle localBounds) {
		IBNAModel model = view.getBNAWorld().getBNAModel();
		for (IThing thingToRender : model.getAllThings()) {
			if (thingToRender.has(IHasHidden.HIDDEN_KEY, true)) {
				continue;
			}
			try {
				pushAlpha(thingToRender.get(IHasAlpha.ALPHA_KEY, 1d));
				try {
					IThingPeer<?> peer = view.getThingPeer(thingToRender);
					if (peer.draw(localBounds, this)) {
						peer.draw(gc, localBounds, this);
					}
				}
				finally {
					popAlpha();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public IUIResources.FontMetrics getFontMetrics(Font font) {
		gc.setFont(fonts.getUnchecked(font));
		org.eclipse.swt.graphics.FontMetrics metrics = gc.getFontMetrics();
		return new AbstractUIResources.FontMetrics(metrics.getLeading(), metrics.getAscent(), metrics.getDescent());
	}

	@Override
	public Dimension getTextSize(Font font, String text) {
		gc.setFont(fonts.getUnchecked(font));
		return BNAUtils.toDimension(gc.textExtent(text, SWT.DRAW_TRANSPARENT));
	}

	@Override
	public void drawText(Font font, String text, double x, double y, RGB color, double alpha) {
		if (color == null || text.length() == 0 || alpha == 0) {
			return;
		}

		gc.setFont(fonts.getUnchecked(font));
		setForeground(color);
		pushAlpha(alpha);
		gc.drawString(text, SystemUtils.round(x), SystemUtils.round(y), true);
		popAlpha();
	}

	@Override
	public void drawShape(Point2D localShape, RGB color, double alpha) {
		if (color == null || alpha == 0) {
			return;
		}

		setForeground(color);
		pushAlpha(alpha);
		gc.drawPoint(SystemUtils.round(localShape.getX()), SystemUtils.round(localShape.getY()));
		popAlpha();
	}

	@Override
	public void drawShape(Shape localShape, RGB color, int width, LineStyle lineStyle, double alpha) {
		if (color == null || width == 0 || lineStyle == LineStyle.NONE || alpha == 0) {
			return;
		}

		gc.setLineWidth(width);
		setForeground(color);
		pushAlpha(alpha);
		if (lineStyle == LineStyle.DOT) {
			gc.setLineStyle(SWT.LINE_CUSTOM);
			gc.setLineDash(new int[] { 1, 1 });
			gc.setAntialias(SWT.OFF);
		}
		else {
			gc.setLineStyle(lineStyle.toSwtStyle());
		}
		if (localShape instanceof Rectangle2D) {
			gc.drawRectangle(toRectangle((Rectangle2D) localShape));
		}
		else if (localShape instanceof Ellipse2D) {
			Rectangle r = toRectangle((Ellipse2D) localShape);
			gc.drawOval(r.x, r.y, r.width, r.height);
		}
		else {
			updateLastShape(localShape);

			gc.drawPath(lastPath);
		}
		if (lineStyle == LineStyle.DOT) {
			gc.setAntialias(isAntialiasGraphics() ? SWT.ON : SWT.OFF);
		}
		popAlpha();
	}

	@Override
	public void drawShape(Shape localShape, RGB color, int width, int stipple, double alpha) {
		if (color == null || width == 0 || stipple == 0 || alpha == 0) {
			return;
		}

		gc.setLineWidth(width);
		setForeground(color);
		pushAlpha(alpha);
		if (localShape instanceof Rectangle2D) {
			gc.drawRectangle(toRectangle((Rectangle2D) localShape));
		}
		else if (localShape instanceof Ellipse2D) {
			Rectangle r = toRectangle((Ellipse2D) localShape);
			gc.drawOval(r.x, r.y, r.width, r.height);
		}
		else {
			updateLastShape(localShape);
			gc.drawPath(lastPath);
		}
		popAlpha();
	}

	@Override
	public void glowShape(Shape localShape, RGB color, int width, double alpha) {
		if (color == null || width == 0 || alpha == 0) {
			return;
		}

		updateLastShape(localShape);

		setForeground(color);
		double alphaDelta = alpha / width * GLOW_RESOLUTION;
		double cumulativeAlpha = 0;
		gc.setLineStyle(SWT.LINE_SOLID);
		for (int i = width; i >= 1; i -= GLOW_RESOLUTION) {
			double actualAlpha = alphaDelta * (width - i) - cumulativeAlpha;
			cumulativeAlpha += actualAlpha;
			gc.setLineWidth(i);
			pushAlpha(actualAlpha);
			gc.drawPath(lastPath);
			popAlpha();
		}
	}

	@Override
	public void selectShape(Shape localShape, int offset) {
		offset = offset / 2 % 2;
		int[] lineDash = new int[] { 4, 4 };
		RGB background = offset < 1 ? new RGB(0, 0, 0) : new RGB(255, 255, 255);
		RGB foreground = offset < 1 ? new RGB(255, 255, 255) : new RGB(0, 0, 0);
		gc.setLineWidth(1);
		gc.setLineStyle(SWT.LINE_SOLID);
		if (localShape instanceof Rectangle2D) {
			Rectangle r = toRectangle((Rectangle2D) localShape);
			setForeground(background);
			gc.drawRectangle(r);
			gc.setLineStyle(SWT.LINE_CUSTOM);
			gc.setLineDash(lineDash);
			setForeground(foreground);
			gc.drawRectangle(r);
		}
		else if (localShape instanceof Ellipse2D) {
			Rectangle r = toRectangle((Ellipse2D) localShape);
			setForeground(background);
			gc.drawOval(r.x, r.y, r.width, r.height);
			gc.setLineStyle(SWT.LINE_CUSTOM);
			gc.setLineDash(lineDash);
			setForeground(foreground);
			gc.drawOval(r.x, r.y, r.width, r.height);
		}
		else {
			updateLastShape(localShape);
			setForeground(background);
			gc.drawPath(lastPath);
			gc.setLineStyle(SWT.LINE_CUSTOM);
			gc.setLineDash(lineDash);
			setForeground(foreground);
			gc.drawPath(lastPath);
		}
		gc.setLineStyle(SWT.LINE_SOLID);
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha) {
		if (color1 == null || alpha == 0) {
			return;
		}

		boolean isGradientFilled = isDecorativeGraphics() && color2 != null && !color1.equals(color2);

		pushAlpha(alpha);
		if (localShape instanceof Rectangle2D) {
			Rectangle r = toRectangle((Rectangle2D) localShape);
			if (isGradientFilled) {
				setForeground(color1);
				setBackground(color2);
				gc.fillGradientRectangle(r.x, r.y, r.width, r.height, true);
			}
			else {
				setBackground(color1);
				gc.fillRectangle(r.x, r.y, r.width, r.height);
			}
		}
		else {
			if (isGradientFilled) {
				updateLastShape(localShape);

				setForeground(color1);
				setBackground(color2);
				gc.setClipping(lastPath);
				gc.fillGradientRectangle(lastBounds.x, lastBounds.y, lastBounds.width, lastBounds.height, true);
				gc.setClipping((Rectangle) null);
			}
			else {
				if (localShape instanceof Ellipse2D) {
					Rectangle r = toRectangle((Ellipse2D) localShape);
					setBackground(color1);
					gc.fillOval(r.x, r.y, r.width, r.height);
				}
				else {
					updateLastShape(localShape);

					setBackground(color1);
					gc.fillPath(lastPath);
				}
			}
		}
		popAlpha();
	}

	@Override
	public void pushMatrix(double x, double y, double angle) {
		matrices.push(currentTransform);
		Transform newTransform = new Transform(gc.getDevice());
		if (currentTransform != null) {
			newTransform.multiply(currentTransform);
		}
		newTransform.translate((float) x, (float) y);
		newTransform.rotate((float) (angle / Math.PI * 180));
		gc.setTransform(currentTransform = newTransform);
	}

	@Override
	public void popMatrix() {
		currentTransform.dispose();
		gc.setTransform(currentTransform = matrices.pop());
	}

	public BufferedImage renderToImage(IBNAView view, Rectangle lbb) {

		Image image = null;
		GC gc = null;
		try {
			PaletteData paletteData; // = new PaletteData(0xFF, 0xFF00, 0xFF0000);
			ImageData imageData; // = new ImageData(lbb.width, lbb.height, 32, paletteData);
			image = new Image(Display.getCurrent(), lbb);

			gc = new GC(image, SWT.TRANSPARENCY_ALPHA | SWT.TRANSPARENCY_MASK);
			setGc(gc);
			setAntialiasGraphics(isAntialiasGraphics());
			setAntialiasText(isAntialiasText());

			pushMatrix(-lbb.x, -lbb.y, 0);
			try {
				gc.setAlpha(0);
				gc.fillRectangle(lbb);
				gc.setAlpha(255);
				renderThings(view, lbb);
			}
			finally {
				popMatrix();
			}

			imageData = image.getImageData();
			paletteData = imageData.palette;
			if (paletteData.isDirect) {
				BufferedImage bufferedImage = new BufferedImage(imageData.width, imageData.height,
						BufferedImage.TYPE_4BYTE_ABGR);
				for (int y = 0; y < imageData.height; y++) {
					for (int x = 0; x < imageData.width; x++) {
						RGB rgb = paletteData.getRGB(imageData.getPixel(x, y));
						int r = rgb.red;
						int g = rgb.green;
						int b = rgb.blue;
						int a = imageData.getAlpha(x, y);
						int i = a << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
						bufferedImage.setRGB(x, y, i);
					}
				}
				return bufferedImage;
			}
			else {
				RGB[] rgbs = paletteData.getRGBs();
				byte[] red = new byte[rgbs.length];
				byte[] green = new byte[rgbs.length];
				byte[] blue = new byte[rgbs.length];
				for (int i = 0; i < rgbs.length; i++) {
					RGB rgb = rgbs[i];
					red[i] = (byte) rgb.red;
					green[i] = (byte) rgb.green;
					blue[i] = (byte) rgb.blue;
				}
				ColorModel colorModel;
				if (imageData.transparentPixel != -1) {
					colorModel = new IndexColorModel(imageData.depth, rgbs.length, red, green, blue,
							imageData.transparentPixel);
				}
				else {
					colorModel = new IndexColorModel(imageData.depth, rgbs.length, red, green, blue);
				}
				BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(
						imageData.width, imageData.height), false, null);
				WritableRaster raster = bufferedImage.getRaster();
				int[] pixelArray = new int[1];
				for (int y = 0; y < imageData.height; y++) {
					for (int x = 0; x < imageData.width; x++) {
						int pixel = imageData.getPixel(x, y);
						pixelArray[0] = pixel;
						raster.setPixel(x, y, pixelArray);
					}
				}
				return bufferedImage;
			}
		}
		finally {
			if (gc != null) {
				gc.dispose();
			}
			if (image != null) {
				image.dispose();
			}
		}
	}
}
