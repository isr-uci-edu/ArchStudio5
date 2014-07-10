package org.archstudio.bna.ui.swt;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.utils.AbstractUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
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
import com.google.common.collect.Maps;

public class SWTResources extends AbstractUIResources implements ISWTResources {

	public static final int RESOLUTION = 2;

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
	protected final LoadingCache<Shape, Path> shapePaths = CacheBuilder.newBuilder().maximumSize(16)
			.removalListener(new RemovalListener<Shape, Path>() {
				@Override
				public void onRemoval(RemovalNotification<Shape, Path> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<Shape, Path>() {
				@Override
				public Path load(Shape shape) throws Exception {
					Path path = new Path(gc.getDevice());
					return BNAUtils.toPath(path, shape);
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

	protected GC gc;
	protected Transform currentTransform = null;
	protected Deque<Transform> matrices = new LinkedList<Transform>();

	public SWTResources() {
	}

	@Override
	public void dispose() {
		colors.invalidateAll();
		shapePaths.invalidateAll();
		fonts.invalidateAll();
		super.dispose();
	}

	public void setGc(GC gc) {
		this.gc = gc;
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

	protected void setAlpha(double alpha) {
		gc.setAlpha(SystemUtils.bound(0, SystemUtils.round(getGlobalAlpha() * alpha * 255), 255));
	}

	protected void setFont(Font font) {
		gc.setFont(fonts.getUnchecked(font));
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
		Map<Class<?>, AtomicLong[]> counts = Maps.newHashMap();
		for (IThing thingToRender : model.getAllThings()) {
			long time = System.nanoTime();
			if (thingToRender.has(IIsHidden.HIDDEN_KEY, true)) {
				continue;
			}
			try {
				pushAlpha((float)thingToRender.get(IHasAlpha.ALPHA_KEY, 1f));
				try {
					//gc.setTint(thingToRender.get(IHasTint.TINT_KEY, new RGB(0, 0, 0)));
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
			if (DEBUG) {
				time = System.nanoTime() - time;
				AtomicLong[] als = counts.get(thingToRender.getClass());
				if (als == null) {
					counts.put(thingToRender.getClass(), als = new AtomicLong[] { new AtomicLong(), new AtomicLong() });
				}
				als[0].getAndIncrement();
				als[1].getAndAdd(time);
			}
		}
		if (DEBUG) {
			for (Entry<Class<?>, AtomicLong[]> e : SystemUtils.sortedByKey(counts.entrySet())) {
				AtomicLong[] als = e.getValue();
				System.err.println(e.getKey() + ": " + als[0] + " total, " + als[1].longValue() / als[0].longValue());
			}
		}
	}

	@Override
	public double pushAlpha(double alpha) {
		double newAlpha = super.pushAlpha(alpha);
		setAlpha(newAlpha);
		return newAlpha;
	}

	@Override
	public double popAlpha() {
		double newAlpha = super.popAlpha();
		setAlpha(newAlpha);
		return newAlpha;
	}

	@Override
	public boolean setColor(RGB color, double alpha) {
		if (color != null) {
			setForeground(color);
			setBackground(color);
			setAlpha(alpha);
			return true;
		}
		return false;
	}

	@Override
	public boolean setLineStyle(IHasLineData thing) {
		if (setColor(thing, IHasEdgeColor.EDGE_COLOR_KEY)) {
			gc.setLineWidth(thing.getLineWidth());
			gc.setLineStyle(thing.getLineStyle().toSwtStyle());
			return true;
		}
		return false;
	}

	@Override
	public void resetLineStyle() {
		gc.setLineWidth(1);
		gc.setLineStyle(SWT.LINE_SOLID);
	}

	@Override
	protected IUIResources.FontMetrics _getFontMetrics(Font font) {
		setFont(font);
		org.eclipse.swt.graphics.FontMetrics metrics = gc.getFontMetrics();
		return new AbstractUIResources.FontMetrics(metrics.getLeading(), metrics.getAscent(), metrics.getDescent());
	}

	@Override
	protected Dimension _getTextSize(Font font, String text) {
		setFont(font);
		return BNAUtils.toDimension(gc.textExtent(text, SWT.DRAW_TRANSPARENT));
	}

	@Override
	public void drawText(Font font, String text, double x, double y) {
		setFont(font);
		gc.drawString(text, SystemUtils.round(x), SystemUtils.round(y), true);
		if (DEBUG) {
			Dimension size = getTextSize(font, text);
			IUIResources.FontMetrics metrics = getFontMetrics(font);
			int line;
			line = SystemUtils.round(y);
			gc.drawLine(SystemUtils.round(x), line, SystemUtils.round(x) + size.width, line);
			line = SystemUtils.round(y + metrics.getLeading());
			gc.drawLine(SystemUtils.round(x), line, SystemUtils.round(x) + size.width, line);
			line = SystemUtils.round(y + metrics.getLeading() + metrics.getAscent());
			gc.drawLine(SystemUtils.round(x), line, SystemUtils.round(x) + size.width, line);
			line = SystemUtils.round(y + metrics.getLeading() + metrics.getAscent() + metrics.getDescent());
			gc.drawLine(SystemUtils.round(x), line, SystemUtils.round(x) + size.width, line);
		}
	}

	protected boolean isClosed(int[] xyArray) {
		boolean closed = false;
		if (xyArray.length >= 4) {
			closed = true;
			closed &= xyArray[0] == xyArray[xyArray.length - 2];
			closed &= xyArray[1] == xyArray[xyArray.length - 1];
		}
		return closed;
	}

	@Override
	public void drawShape(Shape localShape) {
		if (gc.getAdvanced()) {
			gc.drawPath(shapePaths.getUnchecked(localShape));
		}
		else {
			int[] xyArray = toXYIntArray(localShape);
			if (isClosed(xyArray)) {
				gc.drawPolygon(xyArray);
			}
			else {
				gc.drawPolyline(xyArray);
			}
		}
	}

	@Override
	public void glowShape(Shape localShape, RGB color, int width, double alpha) {
		double cumulativeAlpha = 0;
		if (gc.getAdvanced()) {
			Path path = shapePaths.getUnchecked(localShape);
			for (int i = width; i >= 1; i -= RESOLUTION) {
				double actualAlpha = alpha * (width - i) / width - cumulativeAlpha;
				cumulativeAlpha += actualAlpha;
				gc.setLineWidth(i);
				setColor(color, actualAlpha);
				gc.drawPath(path);
			}
		}
		else {
			int[] xyArray = toXYIntArray(localShape);
			boolean closes = isClosed(xyArray);
			for (int i = width; i >= 1; i -= RESOLUTION) {
				gc.setLineWidth(i);
				double actualAlpha = alpha * (width - i) / width - cumulativeAlpha;
				cumulativeAlpha += actualAlpha;
				setColor(color, actualAlpha);
				if (closes) {
					gc.drawPolygon(xyArray);
				}
				else {
					gc.drawPolyline(xyArray);
				}
			}
		}
		setAlpha(1f);
	}

	@Override
	public void selectShape(Shape localShape, int offset) {
		offset = Math.abs(offset) % 2;
		int[] lineDash = new int[] { 4, 4 };
		if (gc.getAdvanced()) {
			Path path = shapePaths.getUnchecked(localShape);
			if (offset == 0) {
				setForeground(new RGB(0, 0, 0));
				gc.drawPath(path);
				gc.setLineStyle(SWT.LINE_CUSTOM);
				gc.setLineDash(lineDash);
				setForeground(new RGB(255, 255, 255));
				gc.drawPath(path);
			}
			else {
				setForeground(new RGB(255, 255, 255));
				gc.drawPath(path);
				gc.setLineStyle(SWT.LINE_CUSTOM);
				gc.setLineDash(lineDash);
				setForeground(new RGB(0, 0, 0));
				gc.drawPath(path);
			}
		}
		else {
			int[] xyArray = toXYIntArray(localShape);
			if (offset == 0) {
				setForeground(new RGB(0, 0, 0));
				gc.drawPolygon(xyArray);
				gc.setLineStyle(SWT.LINE_CUSTOM);
				gc.setLineDash(lineDash);
				setForeground(new RGB(255, 255, 255));
				gc.drawPolygon(xyArray);
			}
			else {
				setForeground(new RGB(255, 255, 255));
				gc.drawPolygon(xyArray);
				gc.setLineStyle(SWT.LINE_CUSTOM);
				gc.setLineDash(lineDash);
				setForeground(new RGB(0, 0, 0));
				gc.drawPolygon(xyArray);
			}
		}
		gc.setLineStyle(SWT.LINE_SOLID);
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha) {
		if (!Double.isNaN(alpha)) {
			setAlpha(alpha);
		}
		if (gc.getAdvanced()) {
			boolean isGradientFilled = isDecorativeGraphics() && color1 != null && color2 != null
					&& !color1.equals(color2);
			if (isGradientFilled) {
				setForeground(color1);
				setBackground(color2);
				Rectangle r = getRectangle(localShape);
				if (r != null) {
					gc.fillGradientRectangle(r.x, r.y, r.width, r.height, true);
				}
				else {
					gc.setClipping(shapePaths.getUnchecked(localShape));
					try {
						r = BNAUtils.toRectangle(localShape.getBounds());
						gc.fillGradientRectangle(r.x, r.y, r.width, r.height, true);
					}
					finally {
						gc.setClipping((Rectangle) null);
					}
				}
			}
			else {
				if (color1 != null) {
					setBackground(color1);
				}
				Rectangle r = getRectangle(localShape);
				if (r != null) {
					gc.fillRectangle(r.x, r.y, r.width, r.height);
				}
				else {
					gc.fillPath(shapePaths.getUnchecked(localShape));
				}
			}
		}
		else {
			if (color1 != null) {
				setBackground(color1);
			}
			Rectangle r = getRectangle(localShape);
			if (r != null) {
				gc.fillGradientRectangle(r.x, r.y, r.width, r.height, true);
			}
			else {
				gc.fillPolygon(toXYIntArray(localShape));
			}
		}
		setAlpha(1f);
	}

	private Rectangle getRectangle(Shape localShape) {
		if (localShape instanceof Rectangle2D) {
			Rectangle2D r = (Rectangle2D) localShape;
			return BNAUtils.toRectangle(r);
		}
		if (localShape instanceof RoundRectangle2D) {
			RoundRectangle2D r = (RoundRectangle2D) localShape;
			if (r.getArcHeight() < 1 && r.getArcWidth() < 1) {
				return BNAUtils.toRectangle(new Rectangle2D.Double(r.getX(), r.getY(), r.getWidth(), r.getHeight()));
			}
		}
		return null;
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
				setAlpha(0);
				gc.fillRectangle(lbb);
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
