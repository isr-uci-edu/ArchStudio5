package org.archstudio.bna.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.media.opengl.GL2;

import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureData;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class TextUtils {

	public static final boolean DEBUG = false;

	private class TextToRender {
		Font font;
		LineMetrics lineMetrics;
		String text;
		int x, y, width, height;

		public TextToRender(Font font, String text, int x, int y) {
			this.font = font;
			this.lineMetrics = font.getLineMetrics(text, fontRenderContext);
			this.text = text;
			this.x = x;
			this.y = y;
			Rectangle2D bounds = font.getStringBounds(text, fontRenderContext);
			this.width = BNAUtils.round(Math.ceil(bounds.getWidth()));
			this.height = BNAUtils.round(Math.ceil(bounds.getHeight()));
		}

		public Rectangle getBounds() {
			return new Rectangle(x, y, width, height);
		}

		@Override
		public String toString() {
			return text;
		}
	}

	private FontRenderContext fontRenderContext;
	private List<TextToRender> textsToRender = Lists.newArrayList();
	private BufferedImage bufferedImage;
	private GL2 textureGL;
	private Texture texture;
	private TextureData textureData;

	public TextUtils(boolean antialiasText) {
		this.fontRenderContext = new FontRenderContext(null, antialiasText, antialiasText);
	}

	public void dispose() {
		textsToRender.clear();
		bufferedImage = null;
		if (texture != null) {
			texture.destroy(textureGL);
			texture = null;
		}
	}

	public FontRenderContext getFontRenderContext() {
		return fontRenderContext;
	}

	public void beginRendering() {
	}

	public void draw(Font font, String text, double x, double y) {
		textsToRender.add(new TextToRender(font, text, BNAUtils.round(x), BNAUtils.round(y)));
	}

	public void endRendering(GL2 gl, Rectangle localBounds) {

		// determine what text area needs to be rendered
		Rectangle textArea = null;
		for (TextToRender textToRender : textsToRender) {
			if (textArea == null) {
				textArea = textToRender.getBounds();
			}
			else {
				textArea = textArea.union(textToRender.getBounds());
			}
		}
		if (textArea == null) {
			textsToRender.clear();
			return;
		}

		/*
		 * Cannot clip due to possibility that current transform is applied
		 */
		//// clip the text area to the viewable screen
		//Rectangle clippedTextArea = textArea.intersection(localBounds);
		//if (clippedTextArea.width == 0 || clippedTextArea.height == 0) {
		//	textsToRender.clear();
		//	return;
		//}
		Rectangle clippedTextArea = textArea;

		// create a buffered image to back the text
		if (bufferedImage == null || bufferedImage.getWidth() < clippedTextArea.width
				|| bufferedImage.getHeight() < clippedTextArea.height) {
			bufferedImage = new BufferedImage(clippedTextArea.width, clippedTextArea.height,
					BufferedImage.TYPE_INT_ARGB_PRE);
		}

		// create a texture to display the text
		if (texture == null || texture.getImageWidth() != bufferedImage.getWidth()
				|| texture.getImageHeight() != bufferedImage.getHeight()) {
			if (texture != null) {
				texture.destroy(textureGL);
				texture = null;
			}
			textureGL = gl;
			textureData = new AWTTextureData(gl.getGLProfile(), 0, 0, false, bufferedImage);
			texture = AWTTextureIO.newTexture(gl.getGLProfile(), bufferedImage, false);
			texture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			texture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
		}

		// draw text on the buffered image
		Graphics2D g2d = bufferedImage.createGraphics();
		try {
			g2d.setComposite(AlphaComposite.Clear);
			g2d.fillRect(0, 0, clippedTextArea.width, clippedTextArea.height);
			g2d.setComposite(AlphaComposite.Src);
			g2d.setColor(Color.WHITE);
			if (fontRenderContext.isAntiAliased()) {
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			}
			else {
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
			}
			if (fontRenderContext.usesFractionalMetrics()) {
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			}
			else {
				g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
			}
			for (TextToRender textToRender : textsToRender) {
				g2d.setFont(textToRender.font);
				g2d.drawString(textToRender.text, textToRender.x - clippedTextArea.x, clippedTextArea.height
						- (textToRender.y - clippedTextArea.y) - textToRender.lineMetrics.getDescent());
				if (DEBUG) {
					g2d.drawRect(0, 0, clippedTextArea.width, clippedTextArea.height);
					g2d.drawLine(0, 0, clippedTextArea.width, clippedTextArea.height);
					g2d.drawLine(0, clippedTextArea.height, clippedTextArea.width, 0);
				}
			}
		}
		finally {
			g2d.dispose();
		}

		// update the text area of the texture
		texture.updateSubImage(gl, textureData, 0, 0, 0, 0, 0, clippedTextArea.width, clippedTextArea.height);

		// draw the texture
		TextureCoords coords = texture.getSubImageTexCoords(0, bufferedImage.getHeight() - clippedTextArea.height,
				clippedTextArea.width, bufferedImage.getHeight());
		texture.enable(gl);
		texture.bind(gl);
		try {
			gl.glBegin(GL2.GL_TRIANGLE_FAN);
			gl.glTexCoord2f(coords.left(), coords.bottom());
			gl.glVertex2f(clippedTextArea.x, clippedTextArea.y);
			gl.glTexCoord2f(coords.right(), coords.bottom());
			gl.glVertex2f(clippedTextArea.x + clippedTextArea.width, clippedTextArea.y);
			gl.glTexCoord2f(coords.right(), coords.top());
			gl.glVertex2f(clippedTextArea.x + clippedTextArea.width, clippedTextArea.y + clippedTextArea.height);
			gl.glTexCoord2f(coords.left(), coords.top());
			gl.glVertex2f(clippedTextArea.x, clippedTextArea.y + clippedTextArea.height);
			gl.glEnd();
		}
		finally {
			texture.disable(gl);
		}

		// reset the text to render
		textsToRender.clear();
	}

	public double getWidth(Font font, String text) {
		return font.getStringBounds(text, fontRenderContext).getWidth();
	}
}
