package org.archstudio.bna.ui.jogl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;

import javax.media.opengl.DebugGL4bc;
import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.GL4bc;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasHidden;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Program;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Shader;
import org.archstudio.bna.ui.utils.AbstractUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.Matrix;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.RenderAttachment;
import com.jogamp.opengl.FBObject.TextureAttachment;
import com.jogamp.opengl.util.PMVMatrix;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class JOGLResources extends AbstractUIResources implements IJOGLResources {

	private GL2ES2 gl;
	private PMVMatrix matrix = new PMVMatrix();
	private List<float[]> projectionMatrixStack = Lists.newArrayList();
	private List<float[]> modelViewMatrixStack = Lists.newArrayList();
	private List<float[]> textureMatrixStack = Lists.newArrayList();
	private int stipple = LineStyle.SOLID.toBitPattern();
	private RGB color = new RGB(0, 0, 0);
	private double alpha = 1d;

	private GL2ES2Shader fillVP;
	private GL2ES2Shader fillFP;
	private GL2ES2Program fillP;

	private GL2ES2Shader lineVP;
	private GL2ES2Shader lineFP;
	private GL2ES2Program lineP;

	private GL2ES2Shader textVP;
	private GL2ES2Shader textFP;
	private GL2ES2Program textP;

	private BufferedImage bufferedImage;
	private TextureData textureData;
	private Texture texture;

	public JOGLResources(GL2ES2 gl) {
		this.gl = new DebugGL4bc((GL4bc) gl);

		fillVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/fill.vp"));
		fillFP = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/fill.fp"));
		fillP = GL2ES2Program.create(gl, fillVP, fillFP);
		fillP.bindAttribute("attribute_position", 1);
		fillP.bindAttribute("attribute_color", 1);
		fillP.link();

		lineVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/line.vp"));
		lineFP = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/line.fp"));
		lineP = GL2ES2Program.create(gl, lineVP, lineFP);
		lineP.bindAttribute("attribute_position", 1);
		lineP.bindAttribute("attribute_color", 1);
		lineP.bindAttribute("attribute_stipple_offset", 1);
		lineP.link();

		textVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/text.vp"));
		textFP = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/text.fp"));
		textP = GL2ES2Program.create(gl, textVP, textFP);
		textP.bindAttribute("attribute_position", 1);
		textP.bindAttribute("attribute_texture_position", 1);
		textP.link();
	}

	@Override
	public void dispose() {

		if (fillP != null) {
			fillP.dispose();
			fillP = null;
		}
		if (fillVP != null) {
			fillVP.dispose();
			fillVP = null;
		}
		if (fillFP != null) {
			fillFP.dispose();
			fillFP = null;
		}

		if (lineP != null) {
			lineP.dispose();
			lineP = null;
		}
		if (lineVP != null) {
			lineVP.dispose();
			lineVP = null;
		}
		if (lineFP != null) {
			lineFP.dispose();
			lineFP = null;
		}

		if (textP != null) {
			textP.dispose();
			textP = null;
		}
		if (textVP != null) {
			textVP.dispose();
			textVP = null;
		}
		if (textFP != null) {
			textFP.dispose();
			textFP = null;
		}

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
			gl.glEnable(GL.GL_MULTISAMPLE);
			gl.glEnable(GL.GL_LINE_SMOOTH);
			gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);
		}
		else {
			gl.glDisable(GL.GL_MULTISAMPLE);
			gl.glDisable(GL.GL_LINE_SMOOTH);
		}
		super.setAntialiasGraphics(antialiasGraphics);
	}

	public void renderInit() {
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
	}

	public void renderReshape(Rectangle localBounds) {
		matrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		matrix.glLoadIdentity();
		matrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		matrix.glLoadIdentity();
		matrix.glOrthof(localBounds.x, localBounds.x + localBounds.width, localBounds.y + localBounds.height,
				localBounds.y, -10000, 10000);
		gl.glDepthRange(-1000, 1000);
	}

	@Override
	public void renderTopLevelThings(IBNAView view, Rectangle localBounds) {
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
			if (thingToRender.has(IHasHidden.HIDDEN_KEY, true)) {
				continue;
			}
			try {
				IThingPeer<?> peer = view.getThingPeer(thingToRender);
				if (peer.draw(localBounds, this)) {
					peer.draw(gl, localBounds, this);
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
			this.color = new RGB(color.red, color.green, color.blue);
			this.alpha = alpha;
			return true;
		}
		return false;
	}

	@Override
	public boolean setLineStyle(IHasLineData thing) {
		LineStyle style = thing.getLineStyle();
		if (style != LineStyle.NONE && setColor(thing, IHasEdgeColor.EDGE_COLOR_KEY)) {
			return setLineStyle(thing.getLineWidth(), style.toBitPattern());
		}
		return false;
	}

	@Override
	public boolean setLineStyle(int width, int stipple) {
		gl.glLineWidth(width);
		this.stipple = stipple;
		return true;
	}

	@Override
	public void resetLineStyle() {
		gl.glLineWidth(1);
		stipple = LineStyle.SOLID.toBitPattern();
	}

	@Override
	public void drawText(Font font, String text, double x, double y) {

		double dx = x - Math.floor(x);
		double dy = y - Math.floor(y);
		x = Math.floor(x);
		y = Math.floor(y);

		//TODO: determine how the text can be clipped, this needs to account for transforms

		// determine what text area needs to be rendered
		Dimension textSize = getTextSize(font, text);
		if (textSize.width <= 0 || textSize.height <= 0) {
			// the empty string will have a width of 0
			return;
		}
		textSize.width += 2;
		textSize.height += 2;

		// create a buffered image to back the text
		if (bufferedImage == null || bufferedImage.getWidth() < textSize.width
				|| bufferedImage.getHeight() < textSize.height) {
			bufferedImage = new BufferedImage(textSize.width, textSize.height, BufferedImage.TYPE_INT_RGB);
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
			texture.setTexParameteri(gl, GL.GL_TEXTURE_MIN_FILTER, GL.GL_NEAREST);
			texture.setTexParameteri(gl, GL.GL_TEXTURE_MAG_FILTER, GL.GL_NEAREST);
		}

		// draw text on the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();
		try {

			gl.glBlendFuncSeparate(GL2ES2.GL_CONSTANT_COLOR, GL.GL_ONE_MINUS_SRC_COLOR, GL2ES2.GL_CONSTANT_ALPHA,
					GL.GL_ONE_MINUS_SRC_ALPHA);
			gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_ADD);
			gl.glBlendColor(color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha);

			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, textSize.width, textSize.height);
			g2d.setColor(Color.WHITE);

			if (isAntialiasText()) {
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			}
			else {
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			}
			IUIResources.FontMetrics metrics = getFontMetrics(font);
			g2d.setFont(font);
			g2d.drawString(text, (float) dx, (float) dy + metrics.getLeading() + metrics.getAscent());
			if (DEBUG) {
				Dimension s = getTextSize(font, text);
				System.err.println(font + " " + metrics + " " + s);
				int line;
				line = SystemUtils.round(metrics.getLeading());
				g2d.drawLine(0, line, textSize.width, line);
				line = SystemUtils.round(metrics.getLeading() + metrics.getAscent());
				g2d.drawLine(0, line, textSize.width, line);
				line = SystemUtils.round(metrics.getLeading() + metrics.getAscent() + metrics.getDescent());
				g2d.drawLine(0, line, textSize.width, line);
			}
		}
		finally {
			g2d.dispose();
		}

		// update the text area of the texture
		texture.updateSubImage(gl, textureData, 0, 0, 0, 0, 0, textSize.width, textSize.height);

		// draw the texture
		TextureCoords coords = texture.getSubImageTexCoords(0, bufferedImage.getHeight() - textSize.height,
				textSize.width, bufferedImage.getHeight());
		FloatBuffer texture_coords = Buffers.newDirectFloatBuffer(new float[] {//
				//
						coords.right(), coords.top(),//
						coords.left(), coords.top(),//
						coords.left(), coords.bottom(),//
						coords.right(), coords.bottom() });
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(new float[] {//
				//
						(float) x + textSize.width, (float) y,//
						(float) x, (float) y,//
						(float) x, (float) y + textSize.height,//
						(float) x + textSize.width, (float) y + textSize.height });
		texture.enable(gl);
		try {
			textP.use();
			try {
				gl.glUniformMatrix4fv(textP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
				gl.glUniform1i(textP.getUniform("uniform_texture"), 0);
				gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
				texture.bind(gl);
				vertices.rewind();
				textP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", vertices, GL.GL_STATIC_DRAW, 2, false);
				texture_coords.rewind();
				textP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_texture_position", texture_coords,
						GL.GL_STATIC_DRAW, 2, false);
				gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 4);
			}
			finally {
				textP.done();
			}
		}
		finally {
			texture.disable(gl);
		}
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		if (DEBUG) {
			double line;
			IUIResources.FontMetrics metrics = getFontMetrics(font);
			line = y;
			drawShape(new Line2D.Double(x, line, x + textSize.width, line));
			line = y + metrics.getLeading();
			drawShape(new Line2D.Double(x, line, x + textSize.width, line));
			line = y + metrics.getLeading() + metrics.getAscent();
			drawShape(new Line2D.Double(x, line, x + textSize.width, line));
			line = y + metrics.getLeading() + metrics.getAscent() + metrics.getDescent();
			drawShape(new Line2D.Double(x, line, x + textSize.width, line));
		}
	}

	@Override
	public void drawShape(Shape localShape) {

		float[] xy = BNAUtils.toXYFloatArray(localShape);
		for (int i = 0; i < xy.length; i += 2) {
			xy[i + 0] += 0.5f;
			xy[i + 1] += 0.5f;
		}
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(xy);
		FloatBuffer colors = Buffers.newDirectFloatBuffer(xy.length / 2 * 4);
		FloatBuffer offsets = Buffers.newDirectFloatBuffer(xy.length / 2);
		float offset = 0;
		for (int i = 0; i < xy.length; i += 2) {
			RGB result = color;
			colors.put(result.red / 255f);
			colors.put(result.green / 255f);
			colors.put(result.blue / 255f);
			colors.put((float) alpha);
			if (i > 0 && (stipple & 0xFFFF) != 0xFFFF) {
				offset += Point2D.distance(xy[i - 2], xy[i - 1], xy[i + 0], xy[i + 1]);
			}
			offsets.put(offset);
		}

		lineP.use();
		try {
			gl.glUniformMatrix4fv(lineP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1i(lineP.getUniform("uniform_stipple"), stipple);
			vertices.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", vertices, GL.GL_STATIC_DRAW, 2, false);
			colors.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", colors, GL.GL_STATIC_DRAW, 4, false);
			offsets.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", offsets, GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(GL.GL_LINE_STRIP, 0, xy.length / 2);
		}
		finally {
			lineP.done();
		}
	}

	@Override
	public void drawShape(Point2D localShape) {

		float[] xy = new float[] { (float) localShape.getX() + 0.5f, (float) localShape.getY() + 0.5f };
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(xy);
		FloatBuffer colors = Buffers.newDirectFloatBuffer(xy.length / 2 * 4);
		FloatBuffer offsets = Buffers.newDirectFloatBuffer(xy.length / 2);
		float offset = 0;

		RGB result = color;
		colors.put(result.red / 255f);
		colors.put(result.green / 255f);
		colors.put(result.blue / 255f);
		colors.put((float) alpha);

		offsets.put(offset);

		lineP.use();
		try {
			gl.glUniformMatrix4fv(lineP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1i(lineP.getUniform("uniform_stipple"), 0xFFFFFFFF);
			vertices.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", vertices, GL.GL_STATIC_DRAW, 2, false);
			colors.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", colors, GL.GL_STATIC_DRAW, 4, false);
			offsets.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", offsets, GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(GL.GL_POINTS, 0, xy.length / 2);
		}
		finally {
			lineP.done();
		}
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

		int n = points.size() * 4 + 2;
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(2 * n);
		FloatBuffer colors = Buffers.newDirectFloatBuffer(4 * n);
		float r = color.red / 255f;
		float g = color.green / 255f;
		float b = color.blue / 255f;

		n = 0;
		for (int i = 1; i < points.size(); i++) {
			Point2D p1 = points.get(i - 1);
			Point2D p2 = points.get(i);

			double angle = Math.atan2(p2.getY() - p1.getY(), p2.getX() - p1.getX());
			Point2D p1a = new Point2D.Double(p1.getX() + width * Math.sin(-angle), p1.getY() + width * Math.cos(-angle));
			Point2D p2a = new Point2D.Double(p2.getX() + width * Math.sin(-angle), p2.getY() + width * Math.cos(-angle));

			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put((float) alpha);
			vertices.put((float) p1.getX());
			vertices.put((float) p1.getY());

			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put(0);
			vertices.put((float) p1a.getX());
			vertices.put((float) p1a.getY());

			if (i == 1) {
				closesP = p1;
				closesPa = p1a;
			}

			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put((float) alpha);
			vertices.put((float) p2.getX());
			vertices.put((float) p2.getY());

			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put(0);
			vertices.put((float) p2a.getX());
			vertices.put((float) p2a.getY());
		}
		if (closed) {
			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put((float) alpha);
			vertices.put((float) closesP.getX());
			vertices.put((float) closesP.getY());

			n++;
			colors.put(r);
			colors.put(g);
			colors.put(b);
			colors.put(0);
			vertices.put((float) closesPa.getX());
			vertices.put((float) closesPa.getY());
		}

		fillP.use();
		try {
			gl.glUniformMatrix4fv(fillP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			vertices.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", vertices, GL.GL_STATIC_DRAW, 2, false);
			colors.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", colors, GL.GL_STATIC_DRAW, 4, false);
			gl.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, n);
		}
		finally {
			fillP.done();
		}
	}

	@Override
	public void selectShape(Shape localShape, int offset) {
		RGB oldColor = color;
		int oldStipple = stipple;
		try {
			color = new RGB(0, 0, 0);
			stipple = 0xFFFFFFFF;
			drawShape(localShape);
			color = new RGB(255, 255, 255);
			stipple = 0xF0F0F0F0 >> offset * 2 % 8;
			drawShape(localShape);
		}
		finally {
			color = oldColor;
			stipple = oldStipple;
		}
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha) {

		Rectangle2D localBounds = localShape.getBounds2D();
		boolean isGradientFilled = isDecorativeGraphics() && color1 != null && color2 != null && !color1.equals(color2);
		double minY = isGradientFilled ? localBounds.getMinY() : 0;
		double maxY = isGradientFilled ? localBounds.getMaxY() : 0;
		float actualAlpha = Double.isNaN(alpha) ? (float) this.alpha : (float) (this.alpha * alpha);

		float[] xy = BNAUtils.toXYFloatArray(localShape);
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(xy);
		FloatBuffer colors = Buffers.newDirectFloatBuffer(xy.length / 2 * 4);
		if (isGradientFilled) {
			RGB result = new RGB(0, 0, 0);
			for (int i = 0; i < xy.length; i += 2) {
				//float x = xy[i];
				float y = xy[i + 1];
				blendColor(result, color1, color2, minY, maxY, y);
				colors.put(result.red / 255f);
				colors.put(result.green / 255f);
				colors.put(result.blue / 255f);
				colors.put(actualAlpha);
			}
		}
		else {
			for (int i = 0; i < xy.length; i += 2) {
				RGB result = color1 != null ? color1 : color;
				colors.put(result.red / 255f);
				colors.put(result.green / 255f);
				colors.put(result.blue / 255f);
				colors.put(actualAlpha);
			}
		}

		fillP.use();
		try {
			gl.glUniformMatrix4fv(fillP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			vertices.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", vertices, GL.GL_STATIC_DRAW, 2, false);
			colors.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", colors, GL.GL_STATIC_DRAW, 4, false);
			gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, xy.length / 2);
		}
		finally {
			fillP.done();
		}
	}

	@Override
	public void fillShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int n) {

		fillP.use();
		try {
			gl.glUniformMatrix4fv(fillP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			xyzVertices.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", xyzVertices, GL.GL_STATIC_DRAW, 3, false);
			rgbaColors.rewind();
			fillP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", rgbaColors, GL.GL_STATIC_DRAW, 4, false);
			gl.glDrawArrays(mode, 0, n);
		}
		finally {
			fillP.done();
		}
	}

	@Override
	public void drawShape(Dimension size, int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int n) {

		FloatBuffer offsets = Buffers.newDirectFloatBuffer(n);
		float offset = 0;
		offsets.put(0);
		xyzVertices.rewind();
		float[] values = new float[16];
		matrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION, values, 0);
		Matrix m = Matrix.newColumnMajorMatrix(4, values);
		Matrix p1 = m.product(Matrix.newRowMajorMatrix(1, xyzVertices.get(), xyzVertices.get(), xyzVertices.get(), 1));
		for (int i = 1; i < n; i++) {
			Matrix p2 = m.product(Matrix.newRowMajorMatrix(1, xyzVertices.get(), xyzVertices.get(), xyzVertices.get(),
					1));
			double dx = size.width * (p2.get(0, 0) - p1.get(0, 0)) / 2;
			double dy = size.height * (p2.get(0, 1) - p1.get(0, 1)) / 2;
			offset += (float) Math.sqrt(dx * dx + dy * dy);
			offsets.put(offset);
			p1 = p2;
		}

		lineP.use();
		try {
			gl.glUniformMatrix4fv(fillP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1i(lineP.getUniform("uniform_stipple"), stipple);
			xyzVertices.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", xyzVertices, GL.GL_STATIC_DRAW, 3, false);
			rgbaColors.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", rgbaColors, GL.GL_STATIC_DRAW, 4, false);
			offsets.rewind();
			lineP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", offsets, GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(mode, 0, n);
		}
		finally {
			lineP.done();
		}
	}

	protected void blendColor(RGB result, RGB color1, RGB color2, double minY, double maxY, double y) {
		float f = (float) ((y - minY) / (maxY - minY));
		result.red = SystemUtils.bound(0, SystemUtils.round(color1.red + (color2.red - color1.red) * f), 255);
		result.green = SystemUtils.bound(0, SystemUtils.round(color1.green + (color2.green - color1.green) * f), 255);
		result.blue = SystemUtils.bound(0, SystemUtils.round(color1.blue + (color2.blue - color1.blue) * f), 255);
	}

	@Override
	public void pushMatrix(double x, double y, double angle) {
		int oldMatrixMode = matrix.glGetMatrixMode();
		pushMatrix(GLMatrixFunc.GL_MODELVIEW);
		pushMatrix(GLMatrixFunc.GL_PROJECTION);
		pushMatrix(GL.GL_TEXTURE);
		matrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		matrix.glTranslatef((float) x, (float) y, 0);
		matrix.glRotatef((float) (angle / Math.PI * 180), 0, 0, 1);
		matrix.glMatrixMode(oldMatrixMode);
	}

	@Override
	public void popMatrix() {
		int oldMatrixMode = matrix.glGetMatrixMode();
		popMatrix(GLMatrixFunc.GL_MODELVIEW);
		popMatrix(GLMatrixFunc.GL_PROJECTION);
		popMatrix(GL.GL_TEXTURE);
		matrix.glMatrixMode(oldMatrixMode);
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

	@Override
	public PMVMatrix getMatrix() {
		return matrix;
	}

	@Override
	public void pushMatrix(int matrixName) {
		List<float[]> stack;
		switch (matrixName) {
		case GLMatrixFunc.GL_MODELVIEW:
			stack = modelViewMatrixStack;
			break;
		case GLMatrixFunc.GL_PROJECTION:
			stack = projectionMatrixStack;
			break;
		case GL.GL_TEXTURE:
			stack = textureMatrixStack;
			break;
		default:
			throw new IllegalArgumentException("Unrecognized matrix name: " + matrixName);
		}

		float[] values = new float[16];
		matrix.glMatrixMode(matrixName);
		matrix.glGetFloatv(matrixName, values, 0);
		values = Arrays.copyOf(values, values.length);
		stack.add(values);
	}

	@Override
	public void popMatrix(int matrixName) {
		List<float[]> stack;
		switch (matrixName) {
		case GLMatrixFunc.GL_MODELVIEW:
			stack = modelViewMatrixStack;
			break;
		case GLMatrixFunc.GL_PROJECTION:
			stack = projectionMatrixStack;
			break;
		case GL.GL_TEXTURE:
			stack = textureMatrixStack;
			break;
		default:
			throw new IllegalArgumentException("Unrecognized matrix name: " + matrixName);
		}

		float[] values = stack.remove(stack.size() - 1);
		matrix.glMatrixMode(matrixName);
		matrix.glLoadMatrixf(values, 0);
	}

}
