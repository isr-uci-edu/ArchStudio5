package org.archstudio.bna.ui.jogl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GL2ES2;
import javax.media.opengl.fixedfunc.GLMatrixFunc;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.Antialias;
import org.archstudio.bna.facets.IHasHidden;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Program;
import org.archstudio.bna.ui.jogl.utils.GL2ES2Shader;
import org.archstudio.bna.ui.utils.AbstractUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.sysutils.ExpandableFloatBuffer;
import org.archstudio.sysutils.Matrix;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
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

	private final GL2ES2 gl;
	private Rectangle localBounds = new Rectangle(0, 0, 0, 0);

	private final PMVMatrix matrix = new PMVMatrix();
	private final List<float[]> projectionMatrixStack = Lists.newArrayList();
	private final List<float[]> modelViewMatrixStack = Lists.newArrayList();
	private final List<float[]> textureMatrixStack = Lists.newArrayList();
	PMVMatrix textMatrix = new PMVMatrix();

	private GL2ES2Shader fill2dVP;
	private GL2ES2Shader fill2dFP;
	private GL2ES2Program fill2dP;

	private GL2ES2Shader fill3dVP;
	private GL2ES2Shader fill3dFP;
	private GL2ES2Program fill3dP;

	private GL2ES2Shader line2dVP;
	private GL2ES2Shader line2dFP;
	private GL2ES2Program line2dP;

	private GL2ES2Shader line3dVP;
	private GL2ES2Shader line3dFP;
	private GL2ES2Program line3dP;

	private GL2ES2Shader textVP;
	private GL2ES2Shader textFP;
	private GL2ES2Program textP;

	private BufferedImage bufferedImage;
	private TextureData textureData;
	private Texture texture;

	public JOGLResources(GL2ES2 gl) {
		this.gl = gl;

		fill2dVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/fill2d.vp"));
		fill2dFP = GL2ES2Shader
				.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/fill2d.fp"));
		fill2dP = GL2ES2Program.create(gl, fill2dVP, fill2dFP);
		fill2dP.bindAttribute("attribute_position", 1);
		fill2dP.bindAttribute("attribute_color", 1);
		fill2dP.link();

		fill3dVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/fill3d.vp"));
		fill3dFP = GL2ES2Shader
				.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/fill3d.fp"));
		fill3dP = GL2ES2Program.create(gl, fill3dVP, fill3dFP);
		fill3dP.bindAttribute("attribute_position", 1);
		fill3dP.bindAttribute("attribute_color", 1);
		fill3dP.link();

		line2dVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/line2d.vp"));
		line2dFP = GL2ES2Shader
				.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/line2d.fp"));
		line2dP = GL2ES2Program.create(gl, line2dVP, line2dFP);
		line2dP.bindAttribute("attribute_position", 1);
		line2dP.bindAttribute("attribute_stipple_offset", 1);
		line2dP.link();

		line3dVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/line3d.vp"));
		line3dFP = GL2ES2Shader
				.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/line3d.fp"));
		line3dP = GL2ES2Program.create(gl, line3dVP, line3dFP);
		line3dP.bindAttribute("attribute_position", 1);
		line3dP.bindAttribute("attribute_color", 1);
		line3dP.bindAttribute("attribute_stipple_offset", 1);
		line3dP.link();

		textVP = GL2ES2Shader.create(gl, GL2ES2.GL_VERTEX_SHADER, JOGLResources.class.getResource("glsl/text.vp"));
		textFP = GL2ES2Shader.create(gl, GL2ES2.GL_FRAGMENT_SHADER, JOGLResources.class.getResource("glsl/text.fp"));
		textP = GL2ES2Program.create(gl, textVP, textFP);
		textP.bindAttribute("attribute_position", 1);
		textP.bindAttribute("attribute_texture_position", 1);
		textP.link();
	}

	@Override
	public void dispose() {
		SystemUtils.dispose(fill2dP, fill2dVP, fill2dFP);
		fill2dP = null;
		fill2dVP = fill2dFP = null;
		SystemUtils.dispose(fill3dP, fill3dVP, fill3dFP);
		fill3dP = null;
		fill3dVP = fill3dFP = null;
		SystemUtils.dispose(line2dP, line2dVP, line2dFP);
		line2dP = null;
		line2dVP = line2dFP = null;
		SystemUtils.dispose(line3dP, line3dVP, line3dFP);
		line3dP = null;
		line3dVP = line3dFP = null;
		SystemUtils.dispose(textP, textVP, textFP);
		textP = null;
		textVP = textFP = null;

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

	public void setLocalBounds(Rectangle localBounds) {
		this.localBounds = localBounds;
	}

	Shape lastShape = null;
	int lastPoints;
	float lastMinY, lastMaxY;
	ExpandableFloatBuffer lastXYFloatBuffer = new ExpandableFloatBuffer();
	ExpandableFloatBuffer lastOffsetFloatBuffer = new ExpandableFloatBuffer();

	private void updateLastShape(Shape localShape) {
		if (lastShape != localShape) {
			lastShape = localShape;

			// determine min y, max y
			Rectangle2D bounds = localShape.getBounds2D();
			lastMinY = (float) bounds.getMinY();
			lastMaxY = (float) bounds.getMaxY();

			// get xy positions, resize colors and offsets
			lastXYFloatBuffer.rewind();
			BNAUtils.toXYFloatBuffer(localShape, lastXYFloatBuffer);
			lastPoints = lastXYFloatBuffer.position() / 2;

			// update offsets
			float[] xy = new float[2];
			float lastX = 0, lastY = 0;
			float offset = 0;
			lastXYFloatBuffer.rewind();
			lastOffsetFloatBuffer.rewind();
			for (int i = 0; i < lastPoints; i++) {
				lastXYFloatBuffer.get(xy, 0, 2);
				if (i > 0) {
					offset += (float) Point2D.distance(xy[0], xy[1], lastX, lastY);
				}
				lastOffsetFloatBuffer.put(offset);
				lastX = xy[0];
				lastY = xy[1];
			}
		}
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
		textMatrix.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		textMatrix.glLoadIdentity();
		textMatrix.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		textMatrix.glLoadIdentity();
		textMatrix.glOrthof(localBounds.x, localBounds.x + localBounds.width, localBounds.y + localBounds.height,
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
		for (IThing thingToRender : model.getAllThings()) {
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
		}
	}

	@Override
	public void drawText(Font font, String text, double x, double y, RGB color, double alpha) {
		if (color == null || text.length() == 0 || alpha == 0) {
			return;
		}

		// determine what text area needs to be rendered
		Dimension textSize = getTextSize(font, text);
		if (textSize.width <= 0 || textSize.height <= 0) {
			// the empty string will have a width of 0
			return;
		}

		// get a transform to the local screen coordinates
		AffineTransform transform = getLocalTransform();

		// determine the actual area we need to draw
		IUIResources.FontMetrics metrics = getFontMetrics(font);
		Path2D.Double textShape = new Path2D.Double(new Rectangle2D.Double(x, y, textSize.width, textSize.height));
		textShape.transform(transform);
		Rectangle textBounds = BNAUtils.toRectangle(textShape.getBounds2D());
		textBounds.intersect(localBounds);
		if (textBounds.width <= 0 || textBounds.height <= 0) {
			return;
		}

		// create a buffered image to back the text
		if (bufferedImage == null || bufferedImage.getWidth() < textBounds.width
				|| bufferedImage.getHeight() < textBounds.height) {
			bufferedImage = new BufferedImage(textBounds.width + 2, textBounds.height + 2, BufferedImage.TYPE_INT_RGB);
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
		Point2D xy = transform.transform(new Point2D.Double(x, y), null);
		try {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, textBounds.width, textBounds.height);
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
			g2d.setFont(font);
			g2d.translate(xy.getX() - textBounds.x, xy.getY() - textBounds.y);
			g2d.rotate(-getLocalRotation());
			g2d.drawString(text, 0, metrics.getLeading() + metrics.getAscent());
		}
		finally {
			g2d.dispose();
		}

		// update the text area of the texture
		texture.updateSubImage(gl, textureData, 0, 0, 0, 0, 0, textBounds.width + 1, textBounds.height + 1);

		// draw the texture
		TextureCoords coords = texture.getSubImageTexCoords(0, bufferedImage.getHeight() - textBounds.height,
				textBounds.width, bufferedImage.getHeight());
		FloatBuffer texture_coords = Buffers.newDirectFloatBuffer(new float[] {//
				//
						coords.right(), coords.top(),//
						coords.left(), coords.top(),//
						coords.left(), coords.bottom(),//
						coords.right(), coords.bottom() });
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(new float[] {//
				//
						(float) textBounds.x + textBounds.width, textBounds.y,//
						textBounds.x, textBounds.y,//
						textBounds.x, (float) textBounds.y + textBounds.height,//
						(float) textBounds.x + textBounds.width, (float) textBounds.y + textBounds.height });

		pushBlendFunction();
		gl.glBlendFuncSeparate(GL2ES2.GL_CONSTANT_COLOR, GL.GL_ONE_MINUS_SRC_COLOR, GL2ES2.GL_CONSTANT_ALPHA,
				GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_ADD);
		gl.glBlendColor(color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha);
		texture.enable(gl);
		try {
			textP.use();
			try {
				gl.glUniformMatrix4fv(textP.getUniform("uniform_projection"), 1, false, textMatrix.glGetMatrixf());
				gl.glUniform1i(textP.getUniform("uniform_texture"), 0);
				gl.glActiveTexture(GL.GL_TEXTURE0 + 0);
				texture.bind(gl);
				vertices.rewind();
				textP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", 4, vertices, GL.GL_STATIC_DRAW, 2, false);
				texture_coords.rewind();
				textP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_texture_position", 4, texture_coords,
						GL.GL_STATIC_DRAW, 2, false);
				gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, 4);
			}
			finally {
				textP.done();
			}
		}
		finally {
			popBlendFunction();
			texture.disable(gl);
		}
	}

	@Override
	public void drawShape(Point2D localShape, RGB color, double alpha) {
		if (color == null || alpha == 0) {
			return;
		}

		int stipple = 0xFFFFFFFF;

		FloatBuffer lastXYFloatBuffer = Buffers.newDirectFloatBuffer(2);
		lastXYFloatBuffer.put((float) localShape.getX());
		lastXYFloatBuffer.put((float) localShape.getY());

		FloatBuffer lastOffsetFloatBuffer = Buffers.newDirectFloatBuffer(1);
		lastOffsetFloatBuffer.put(0f);

		int lastPoints = 1;

		line2dP.use();
		try {
			matrix.glTranslatef(0.5f, 0.5f, 0);
			gl.glUniformMatrix4fv(line2dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform4f(line2dP.getUniform("uniform_rgba"), color.red / 255f, color.green / 255f, color.blue / 255f,
					(float) (getGlobalAlpha() * alpha));
			gl.glUniform1i(line2dP.getUniform("uniform_stipple"), stipple);
			lastXYFloatBuffer.rewind();
			line2dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", lastPoints, lastXYFloatBuffer,
					GL.GL_STATIC_DRAW, 2, false);
			lastOffsetFloatBuffer.rewind();
			line2dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", lastPoints, lastOffsetFloatBuffer,
					GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(GL.GL_LINE_STRIP, 0, lastPoints);
			matrix.glTranslatef(-0.5f, -0.5f, 0);
		}
		finally {
			line2dP.done();
		}
	}

	@Override
	public void drawShape(Shape localShape, RGB color, int width, LineStyle style, double alpha) {
		drawShape(localShape, color, width, style.toBitPattern(), alpha);
	}

	@Override
	public void drawShape(Shape localShape, RGB color, int width, int stipple, double alpha) {
		if (color == null || stipple == 0 || alpha == 0) {
			return;
		}

		updateLastShape(localShape);

		pushBlendFunction();
		gl.glBlendFuncSeparate(GL2ES2.GL_CONSTANT_COLOR, GL.GL_ONE_MINUS_SRC_COLOR, GL2ES2.GL_CONSTANT_ALPHA,
				GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glBlendEquationSeparate(GL.GL_FUNC_ADD, GL.GL_FUNC_ADD);
		gl.glBlendColor(color.red / 255f, color.green / 255f, color.blue / 255f, getGlobalAlpha() * (float) alpha);
		gl.glLineWidth(width + 2);
		line2dP.use();
		try {
			matrix.glTranslatef(0.5f, 0.5f, 0);
			gl.glUniformMatrix4fv(line2dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform2fv(line2dP.getUniform("uniform_rgb_offsets"), 1, Antialias.HRGB.getSubpixelRGBXYDeltas(), 0);
			gl.glUniform4f(line2dP.getUniform("uniform_screen_dimension"), localBounds.width, localBounds.height, 1, 1);
			gl.glUniform1i(line2dP.getUniform("uniform_stipple"), stipple);
			gl.glUniform1f(line2dP.getUniform("uniform_width"), width);
			lastXYFloatBuffer.rewind();
			line2dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", lastPoints,
					lastXYFloatBuffer.getBackingBuffer(), GL.GL_STATIC_DRAW, 2, false);
			lastOffsetFloatBuffer.rewind();
			line2dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", lastPoints,
					lastOffsetFloatBuffer.getBackingBuffer(), GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(GL.GL_LINE_STRIP, 0, lastPoints);
			matrix.glTranslatef(-0.5f, -0.5f, 0);
		}
		finally {
			popBlendFunction();
			line2dP.done();
		}
	}

	@Override
	public void glowShape(Shape localShape, RGB color, int width, double alpha) {
		if (color == null || width == 0 || alpha == 0) {
			return;
		}

		updateLastShape(localShape);

		float[] xy = new float[lastPoints * 2];
		lastXYFloatBuffer.rewind();
		lastXYFloatBuffer.get(xy);

		boolean closed = BNAUtils.realEq(xy[0], xy[xy.length - 2]) && BNAUtils.realEq(xy[1], xy[xy.length - 1]);

		renderGlowSide(xy, false, color, width, alpha, closed);
		renderGlowSide(xy, true, color, width, alpha, closed);
	}

	protected void renderGlowSide(float[] xy, boolean reverse, RGB color, int width, double alpha, boolean closed) {
		int numberOfPoints = xy.length / 2 * 4 + 2;
		FloatBuffer vertices = Buffers.newDirectFloatBuffer(2 * numberOfPoints);
		FloatBuffer colors = Buffers.newDirectFloatBuffer(4 * numberOfPoints);
		float[] rgbArgb0 = new float[] { //
		//
				color.red / 255f, color.green / 255f, color.blue / 255f, (float) alpha, //
				color.red / 255f, color.green / 255f, color.blue / 255f, 0 };
		int closesI = 0;
		float closesXe = 0;
		float closesYe = 0;

		boolean first = true;
		numberOfPoints = 0;
		for (int i = 2; i < xy.length; i += 2) {
			int i0 = !reverse ? i - 2 : xy.length - i;
			int i1 = !reverse ? i - 0 : xy.length - i - 2;

			double angle = -Math.atan2(xy[i1 + 1] - xy[i0 + 1], xy[i1 + 0] - xy[i0 + 0]);
			float sinAngle = (float) Math.sin(angle);
			float cosAngle = (float) Math.cos(angle);
			float p0Xe = xy[i0 + 0] + width * sinAngle / 2;
			float p0Ye = xy[i0 + 1] + width * cosAngle / 2;
			float p1Xe = xy[i1 + 0] + width * sinAngle / 2;
			float p1Ye = xy[i1 + 1] + width * cosAngle / 2;

			numberOfPoints++;
			vertices.put(xy, i0, 2);

			numberOfPoints++;
			vertices.put(p0Xe);
			vertices.put(p0Ye);

			colors.put(rgbArgb0);

			if (first) {
				first = false;
				closesI = i0;
				closesXe = p0Xe;
				closesYe = p0Ye;
			}

			numberOfPoints++;
			vertices.put(xy, i1, 2);

			numberOfPoints++;
			vertices.put(p1Xe);
			vertices.put(p1Ye);

			colors.put(rgbArgb0);
		}
		if (closed) {
			numberOfPoints++;
			vertices.put(xy, closesI, 2);

			numberOfPoints++;
			vertices.put(closesXe);
			vertices.put(closesYe);

			colors.put(rgbArgb0);
		}

		fill3dP.use();
		try {
			gl.glUniformMatrix4fv(fill3dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			vertices.rewind();
			fill3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", numberOfPoints, vertices,
					GL.GL_STATIC_DRAW, 2, false);
			colors.rewind();
			fill3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", numberOfPoints, colors, GL.GL_STATIC_DRAW, 4,
					false);
			gl.glDrawArrays(GL.GL_TRIANGLE_STRIP, 0, numberOfPoints);
		}
		finally {
			fill3dP.done();
		}
	}

	@Override
	public void selectShape(Shape localShape, int offset) {
		drawShape(localShape, new RGB(0, 0, 0), 1, 0xFFFFFFFF, 1);
		drawShape(localShape, new RGB(255, 255, 255), 1, 0xF0F0F0F0 >> offset * 2 % 8, 1);
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2, double alpha) {
		if (color1 == null || alpha == 0) {
			return;
		}

		color2 = color2 != null && isDecorativeGraphics() ? color2 : color1;

		updateLastShape(localShape);

		fill2dP.use();
		try {
			gl.glUniformMatrix4fv(fill2dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1f(fill2dP.getUniform("uniform_y1"), lastMinY);
			gl.glUniform4f(fill2dP.getUniform("uniform_y1_rgba"), color1.red / 255f, color1.green / 255f,
					color1.blue / 255f, (float) (getGlobalAlpha() * alpha));
			gl.glUniform1f(fill2dP.getUniform("uniform_y2"), lastMaxY);
			gl.glUniform4f(fill2dP.getUniform("uniform_y2_rgba"), color2.red / 255f, color2.green / 255f,
					color2.blue / 255f, (float) (getGlobalAlpha() * alpha));
			lastXYFloatBuffer.rewind();
			fill2dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", lastPoints,
					lastXYFloatBuffer.getBackingBuffer(), GL.GL_STATIC_DRAW, 2, false);
			gl.glDrawArrays(GL.GL_TRIANGLE_FAN, 0, lastPoints);
		}
		finally {
			fill2dP.done();
		}
	}

	@Override
	public void fillShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int numberOfPoints) {

		fill3dP.use();
		try {
			gl.glUniformMatrix4fv(fill3dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			fill3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", numberOfPoints, xyzVertices,
					GL.GL_STATIC_DRAW, 3, false);
			fill3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", numberOfPoints, rgbaColors,
					GL.GL_STATIC_DRAW, 4, false);
			gl.glDrawArrays(mode, 0, numberOfPoints);
		}
		finally {
			fill3dP.done();
		}
	}

	@Override
	public void drawShape(int mode, FloatBuffer xyzVertices, FloatBuffer rgbaColors, int numberOfPoints, int stipple) {

		// get projection matrix
		float[] matrixValues = new float[16];
		matrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION, matrixValues, 0);
		Matrix projection = Matrix.newColumnMajorMatrix(4, matrixValues);

		// update offsets
		float[] xyzw = new float[4];
		xyzw[3] = 1;
		float lastX = 0, lastY = 0;
		float offset = 0;
		xyzVertices.mark();
		FloatBuffer offsets = Buffers.newDirectFloatBuffer(numberOfPoints);
		for (int i = 0; i < numberOfPoints; i++) {
			xyzVertices.get(xyzw, 0, 3);
			Matrix p1 = projection.product(Matrix.newRowMajorMatrix(1, xyzw));
			float nextX = localBounds.width / 2f * (float) p1.get(0, 0);
			float nextY = localBounds.height / 2f * (float) p1.get(0, 1);
			if (i > 0) {
				offset += (float) Point2D.distance(lastX, lastY, nextX, nextY);
			}
			if (mode == GL.GL_LINES && i % 2 == 0) {
				offset = 0;
			}
			offsets.put(offset);
			lastX = nextX;
			lastY = nextY;
		}

		line3dP.use();
		try {
			gl.glUniformMatrix4fv(line3dP.getUniform("uniform_projection"), 1, false, matrix.glGetMatrixf());
			gl.glUniform1i(line3dP.getUniform("uniform_stipple"), stipple);
			xyzVertices.reset();
			line3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_position", numberOfPoints, xyzVertices,
					GL.GL_STATIC_DRAW, 3, false);
			line3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_color", numberOfPoints, rgbaColors,
					GL.GL_STATIC_DRAW, 4, false);
			offsets.rewind();
			line3dP.bindBufferData(GL.GL_ARRAY_BUFFER, "attribute_stipple_offset", numberOfPoints, offsets,
					GL.GL_STATIC_DRAW, 1, false);
			gl.glDrawArrays(mode, 0, numberOfPoints);
		}
		finally {
			line3dP.done();
		}
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

	public BufferedImage renderToImage(final IBNAView view, final Rectangle lbb) {
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

			// clear the background
			gl.glClearColor(0, 0, 0, 0);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT | GL.GL_STENCIL_BUFFER_BIT);

			// ... draw things
			pushMatrix(-lbb.x, -lbb.y, 0);
			try {
				renderReshape(new Rectangle(0, 0, lbb.width, lbb.height));
				renderThings(view, new Rectangle(0, 0, lbb.width, lbb.height));
			}
			finally {
				popMatrix();
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
					int i = (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
					image.setRGB(x, y, i);
				}
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

	private AffineTransform getLocalTransform() {
		float[] values = new float[16];
		matrix.glGetFloatv(GLMatrixFunc.GL_PROJECTION, values, 0);
		AffineTransform transform = new AffineTransform(values[0], values[1], values[4], values[5], values[12],
				values[13]);
		transform.preConcatenate(AffineTransform.getScaleInstance(localBounds.width / 2, -localBounds.height / 2));
		transform.preConcatenate(AffineTransform.getTranslateInstance(localBounds.width / 2, localBounds.height / 2));
		return transform;
	}

	private double getLocalRotation() {
		AffineTransform transform = getLocalTransform();
		double[] matrix = new double[6];
		transform.getMatrix(matrix);
		double theta = Math.atan(matrix[2] / matrix[0]);
		if (matrix[0] < 0) {
			theta += Math.PI;
		}
		return theta;
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

	private final List<List<Object>> blendStack = Lists.newArrayList();

	@Override
	public void pushBlendFunction() {
		boolean isEnabled = gl.glIsEnabled(GL.GL_BLEND);
		int[] settings = new int[6];
		float[] color = new float[4];
		gl.glGetIntegerv(GL.GL_BLEND_SRC_RGB, settings, 0);
		gl.glGetIntegerv(GL.GL_BLEND_DST_RGB, settings, 1);
		gl.glGetIntegerv(GL.GL_BLEND_SRC_ALPHA, settings, 2);
		gl.glGetIntegerv(GL.GL_BLEND_DST_ALPHA, settings, 3);
		gl.glGetIntegerv(GL.GL_BLEND_EQUATION_RGB, settings, 4);
		gl.glGetIntegerv(GL.GL_BLEND_EQUATION_ALPHA, settings, 5);
		gl.glGetFloatv(GL2ES2.GL_BLEND_COLOR, color, 0);
		blendStack.add(Lists.<Object> newArrayList(isEnabled, settings, color));
	}

	@Override
	public void popBlendFunction() {
		List<Object> values = blendStack.remove(blendStack.size() - 1);
		boolean isEnabled = (Boolean) values.get(0);
		int[] settings = (int[]) values.get(1);
		float[] color = (float[]) values.get(2);
		if (isEnabled) {
			gl.glEnable(GL.GL_BLEND);
		}
		else {
			gl.glDisable(GL.GL_BLEND);
		}
		gl.glBlendFuncSeparate(settings[0], settings[1], settings[2], settings[3]);
		gl.glBlendEquationSeparate(settings[4], settings[5]);
		gl.glBlendColor(color[0], color[1], color[2], color[3]);
	}
}
