package org.archstudio.bna.ui.jogl;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasTint;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.utils.AbstractUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.ObscuredGL2;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.RenderAttachment;
import com.jogamp.opengl.FBObject.TextureAttachment;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class JOGLResources extends AbstractUIResources implements IJOGLResources {

	protected ObscuredGL2 gl;
	protected BufferedImage bufferedImage;
	protected TextureData textureData;
	protected Texture texture;
	protected AffineTransform transform = new AffineTransform();

	public JOGLResources(GL2 gl) {
		this.gl = new ObscuredGL2(gl);
	}

	@Override
	public void dispose() {
		bufferedImage = null;
		if (textureData != null) {
			textureData.destroy();
			textureData = null;
		}
		if (texture != null) {
			texture.destroy(gl);
			texture = null;
		}
		super.dispose();
	}

	@Override
	public void setAntialiasGraphics(boolean antialiasGraphics) {
		if (antialiasGraphics) {
			gl.glEnable(GL2ES1.GL_POINT_SMOOTH);
			gl.glHint(GL2ES1.GL_POINT_SMOOTH_HINT, GL.GL_NICEST);
			gl.glEnable(GL.GL_LINE_SMOOTH);
			gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);
			gl.glEnable(GL2GL3.GL_POLYGON_SMOOTH);
			gl.glHint(GL2GL3.GL_POLYGON_SMOOTH_HINT, GL.GL_NICEST);
			gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		}
		else {
			gl.glDisable(GL2ES1.GL_POINT_SMOOTH);
			gl.glDisable(GL.GL_LINE_SMOOTH);
			gl.glDisable(GL2GL3.GL_POLYGON_SMOOTH);
			gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_FASTEST);
		}
		super.setAntialiasGraphics(antialiasGraphics);
	}

	public void renderInit() {
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL2.GL_LINE_STIPPLE);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void renderReshape(Rectangle localBounds) {
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(localBounds.x, localBounds.x + localBounds.width, localBounds.y + localBounds.height, localBounds.y,
				0, 1);
		gl.glViewport(0, 0, localBounds.width, localBounds.height);
	}

	@Override
	public void renderTopLevelThings(IBNAView view, Rectangle localBounds) {
		gl.reset();
		gl.glClearColor(1, 1, 1, 0);
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);
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
					gl.setTint(thingToRender.get(IHasTint.TINT_KEY, new RGB(0, 0, 0)));
					IThingPeer<?> peer = view.getThingPeer(thingToRender);
					if (peer.draw(localBounds, this)) {
						peer.draw(gl, localBounds, this);
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
	public boolean setColor(RGB color, double alpha) {
		if (color != null) {
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, (float) (getGlobalAlpha() * alpha));
			return true;
		}
		return false;
	}

	@Override
	public boolean setLineStyle(IHasLineData thing) {
		if (thing.getLineStyle() != LineStyle.NONE && setColor(thing, IHasEdgeColor.EDGE_COLOR_KEY)) {
			gl.glLineWidth(thing.getLineWidth());
			gl.glLineStipple(1, (short) thing.getLineStyle().toBitPattern());
			return true;
		}
		return false;
	}

	@Override
	public void resetLineStyle() {
		gl.glLineWidth(1);
		gl.glLineStipple(1, (short) 0xffffffff);
	}

	@Override
	public void drawText(Font font, String text, double x, double y) {

		//TODO: determine how the text can be clipped, this needs to account for transforms

		// determine what text area needs to be rendered
		Dimension size = getTextSize(font, text);
		if (size.width <= 0 || size.height <= 0) {
			// the empty string will have a width of 0
			return;
		}

		// create a buffered image to back the text
		if (bufferedImage == null || bufferedImage.getWidth() < size.width || bufferedImage.getHeight() < size.height) {
			bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB_PRE);
		}

		// create a texture to display the text
		if (texture == null || texture.getImageWidth() != bufferedImage.getWidth()
				|| texture.getImageHeight() != bufferedImage.getHeight()) {
			if (texture != null) {
				textureData.destroy();
				texture.destroy(gl);
			}
			textureData = new AWTTextureData(gl.getGLProfile(), 0, 0, false, bufferedImage);
			texture = AWTTextureIO.newTexture(gl.getGLProfile(), bufferedImage, false);
			texture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			texture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
		}

		// draw text on the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();
		try {
			g2d.setComposite(AlphaComposite.Clear);
			g2d.fillRect(0, 0, size.width, size.height);
			g2d.setComposite(AlphaComposite.Src);
			g2d.setColor(Color.WHITE);
			if (isAntialiasText()) {
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			}
			else {
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			}
			IUIResources.FontMetrics metrics = getFontMetrics(font);
			g2d.setFont(font);
			g2d.drawString(text, 0, metrics.getLeading() + metrics.getAscent());
			if (DEBUG) {
				Dimension s = getTextSize(font, text);
				System.err.println(font + " " + metrics + " " + s);
				int line;
				line = SystemUtils.round(metrics.getLeading());
				g2d.drawLine(0, line, size.width, line);
				line = SystemUtils.round(metrics.getLeading() + metrics.getAscent());
				g2d.drawLine(0, line, size.width, line);
				line = SystemUtils.round(metrics.getLeading() + metrics.getAscent() + metrics.getDescent());
				g2d.drawLine(0, line, size.width, line);
			}
		}
		finally {
			g2d.dispose();
		}

		// update the text area of the texture
		texture.updateSubImage(gl, textureData, 0, 0, 0, 0, 0, size.width, size.height);

		// draw the texture
		TextureCoords coords = texture.getSubImageTexCoords(0, bufferedImage.getHeight() - size.height, size.width,
				bufferedImage.getHeight());
		texture.enable(gl);
		texture.bind(gl);
		try {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glTexCoord2f(coords.left(), coords.top());
			gl.glVertex2f((float) x, (float) y);
			gl.glTexCoord2f(coords.right(), coords.top());
			gl.glVertex2f((float) x + size.width, (float) y);
			gl.glTexCoord2f(coords.right(), coords.bottom());
			gl.glVertex2f((float) x + size.width, (float) y + size.height);
			gl.glTexCoord2f(coords.left(), coords.bottom());
			gl.glVertex2f((float) x, (float) y + size.height);
			gl.glEnd();
		}
		finally {
			texture.disable(gl);
		}
		if (DEBUG) {
			x += 0.5f;
			double line;
			IUIResources.FontMetrics metrics = getFontMetrics(font);
			gl.glBegin(GL2.GL_LINES);
			line = y + 0.5f;
			gl.glVertex2f((float) x, (float) line);
			gl.glVertex2f((float) x + size.width, (float) line);
			line = y + 0.5f + metrics.getLeading();
			gl.glVertex2f((float) x, (float) line);
			gl.glVertex2f((float) x + size.width, (float) line);
			line = y + 0.5f + metrics.getLeading() + metrics.getAscent();
			gl.glVertex2f((float) x, (float) line);
			gl.glVertex2f((float) x + size.width, (float) line);
			line = y + 0.5f + metrics.getLeading() + metrics.getAscent() + metrics.getDescent();
			gl.glVertex2f((float) x, (float) line);
			gl.glVertex2f((float) x + size.width, (float) line);
			gl.glEnd();
		}
	}

	@Override
	public void drawShape(Shape localShape) {

		gl.glBegin(GL.GL_LINE_STRIP);
		for (Point2D point : toPointsList(localShape)) {
			gl.glVertex2f((float) point.getX() + 0.5f, (float) point.getY() + 0.5f);
		}
		gl.glEnd();
	}

	@Override
	public void glowShape(Shape localShape, RGB color, int width, double alpha) {

		List<Point2D> points = toPointsList(localShape);
		boolean closed = false;
		if (points.size() >= 2) {
			closed = points.get(0).distanceSq(points.get(points.size() - 1)) < BNAUtils.TO_POINTS_ERROR
					* BNAUtils.TO_POINTS_ERROR;
		}

		renderGlowSide(points, color, width, alpha, closed);
		renderGlowSide(Lists.reverse(points), color, width, alpha, closed);
	}

	protected void renderGlowSide(List<Point2D> points, RGB color, int width, double alpha, boolean closed) {

		Point2D closesP = new Point2D.Float(0, 0);
		Point2D closesPa = new Point2D.Float(0, 0);

		gl.glBegin(GL2.GL_TRIANGLE_STRIP);
		for (int i = 1; i < points.size(); i++) {
			Point2D p1 = points.get(i - 1);
			Point2D p2 = points.get(i);

			double angle = Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
			Point2D p1a = new Point2D.Double(p1.getX() + width * Math.sin(-angle), p1.getY() + width * Math.cos(-angle));
			Point2D p2a = new Point2D.Double(p2.getX() + width * Math.sin(-angle), p2.getY() + width * Math.cos(-angle));

			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha);
			gl.glVertex2f((float) p1.getX(), (float) p1.getY());
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, 0f);
			gl.glVertex2f((float) p1a.getX(), (float) p1a.getY());

			if (i == 1) {
				closesP = p1;
				closesPa = p1a;
			}

			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha);
			gl.glVertex2f((float) p2.getX(), (float) p2.getY());
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, 0f);
			gl.glVertex2f((float) p2a.getX(), (float) p2a.getY());
		}
		if (closed) {
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha);
			gl.glVertex2f((float) closesP.getX(), (float) closesP.getY());
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, 0f);
			gl.glVertex2f((float) closesPa.getX(), (float) closesPa.getY());
		}
		gl.glEnd();

	}

	@Override
	public void selectShape(Shape localShape, int offset) {

		List<Point2D> points = toPointsList(localShape);

		gl.glColor4f(1, 1, 1, 1);
		gl.glBegin(GL.GL_LINE_STRIP);
		for (Point2D point : points) {
			gl.glVertex2f((float) point.getX() + 0.5f, (float) point.getY() + 0.5f);
		}
		gl.glEnd();

		gl.glColor4f(0f, 0f, 0f, 1);
		gl.glLineStipple(1, (short) (0x0f0f0f0f >> offset % 8));
		gl.glBegin(GL.GL_LINE_STRIP);
		for (Point2D point : points) {
			gl.glVertex2f((float) point.getX() + 0.5f, (float) point.getY() + 0.5f);
		}
		gl.glEnd();
		gl.glLineStipple(1, (short) 0xffffffff);
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha) {

		Rectangle2D localBounds = localShape.getBounds2D();
		boolean isGradientFilled = isDecorativeGraphics() && color1 != null && color2 != null && !color1.equals(color2);
		double minY = isGradientFilled ? localBounds.getMinY() : 0;
		double maxY = isGradientFilled ? localBounds.getMaxY() : 0;

		gl.glBegin(GL.GL_TRIANGLE_FAN);
		if (color1 != null) {
			if (!Double.isNaN(alpha)) {
				gl.glColor4f(color1.red / 255f, color1.green / 255f, color1.blue / 255f, (float) alpha);
			}
			else {
				gl.glColor3f(color1.red / 255f, color1.green / 255f, color1.blue / 255f);
			}
		}
		if (isGradientFilled) {
			blendColor(color1, color2, minY, maxY, localBounds.getCenterY(), alpha);
		}
		gl.glVertex2f((float) localBounds.getCenterX() + 0.5f, (float) localBounds.getCenterY() + 0.5f);
		for (Point2D point : toPointsList(localShape)) {
			if (isGradientFilled) {
				blendColor(color1, color2, minY, maxY, point.getY(), alpha);
			}
			gl.glVertex2f((float) point.getX() + 0.5f, (float) point.getY() + 0.5f);
		}
		gl.glEnd();
	}

	protected void blendColor(RGB color1, RGB color2, double minY, double maxY, double y, double alpha) {
		float f = (float) ((y - minY) / (maxY - minY));
		if (!Double.isNaN(alpha)) {
			gl.glColor4f(//
					SystemUtils.bound(0, (color1.red + (color2.red - color1.red) * f) / 255f, 1),//
					SystemUtils.bound(0, (color1.green + (color2.green - color1.green) * f) / 255f, 1),//
					SystemUtils.bound(0, (color1.blue + (color2.blue - color1.blue) * f) / 255f, 1),//
					(float) alpha);
		}
		else {
			gl.glColor3f(//
					SystemUtils.bound(0, (color1.red + (color2.red - color1.red) * f) / 255f, 1),//
					SystemUtils.bound(0, (color1.green + (color2.green - color1.green) * f) / 255f, 1),//
					SystemUtils.bound(0, (color1.blue + (color2.blue - color1.blue) * f) / 255f, 1));
		}
	}

	@Override
	public void pushMatrix(double x, double y, double angle) {
		gl.glPushMatrix();
		gl.glTranslatef((float) x, (float) y, 0);
		gl.glRotatef((float) (angle / Math.PI * 180), 0, 0, 1);
	}

	@Override
	public void popMatrix() {
		gl.glPopMatrix();
	}

	public BufferedImage renderToImage(IBNAView view, Rectangle lbb) {

		FBObject fbObject = null;
		RenderAttachment renderAttachment = null;
		TextureAttachment texture0Attachment = null;

		try {
			// create framebuffer
			fbObject = new FBObject();
			fbObject.reset(gl, lbb.width, lbb.height);
			fbObject.bind(gl);

			// create and bind renderbuffers
			fbObject.attachRenderbuffer(gl, GL.GL_DEPTH_COMPONENT16);
			renderAttachment = fbObject.getDepthAttachment();
			renderAttachment.initialize(gl);

			// create and bind texture 0
			texture0Attachment = fbObject.attachTexture2D(gl, 0, true);
			texture0Attachment.initialize(gl);

			// draw on texture 0 ...
			gl.glDrawBuffers(1, new int[] { GL.GL_COLOR_ATTACHMENT0 + 0 }, 0);

			// ... draw things
			renderReshape(lbb);
			setColor(new RGB(0, 0, 0), 0);
			fillShape(new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height));
			renderThings(view, lbb);
			if (DEBUG) {
				setColor(new RGB(255, 0, 0), 1f);
				drawShape(new Rectangle2D.Double(lbb.x + 1, lbb.y + 1, lbb.width - 3, lbb.height - 3));
			}

			// read texture contents
			ByteBuffer byteBuffer = ByteBuffer.allocate(lbb.width * lbb.height * 4);
			gl.glPixelStorei(GL.GL_PACK_ALIGNMENT, 1);
			gl.glReadPixels(0, 0, lbb.width, lbb.height, GL.GL_RGBA, GL.GL_UNSIGNED_BYTE, byteBuffer);

			// create the image
			BufferedImage image = new BufferedImage(lbb.width, lbb.height, BufferedImage.TYPE_4BYTE_ABGR);
			int bufferIndex = 0;
			for (int y = lbb.height - 1; y >= 0; y--) {
				for (int x = 0; x < lbb.width; x++) {
					int r = byteBuffer.get(bufferIndex++);
					int g = byteBuffer.get(bufferIndex++);
					int b = byteBuffer.get(bufferIndex++);
					int a = byteBuffer.get(bufferIndex++);
					int i = a << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
					image.setRGB(x, y, i);
				}
			}

			if (DEBUG) {
				Graphics2D g2d = image.createGraphics();
				g2d.setColor(Color.BLACK);
				g2d.drawRect(0, 0, lbb.width - 1, lbb.height - 1);
				g2d.dispose();
			}

			return image;
		}
		finally {
			if (texture0Attachment != null) {
				texture0Attachment.free(gl);
			}
			if (renderAttachment != null) {
				renderAttachment.free(gl);
			}
			if (fbObject != null) {
				fbObject.unbind(gl);
				fbObject.destroy(gl);
			}
		}
	}
}
