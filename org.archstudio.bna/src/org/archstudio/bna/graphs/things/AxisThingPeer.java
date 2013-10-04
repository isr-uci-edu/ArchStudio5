package org.archstudio.bna.graphs.things;

import java.awt.Font;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.graphs.GraphCoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.TextUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class AxisThingPeer<T extends AxisThing> extends AbstractRectangleThingPeer<T> {

	public AxisThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		if (r.setLineStyle(t)) {
			TextUtils textUtils = r.getTextUtils();
			Rectangle wbb = t.getBoundingBox();
			Rectangle lbb = cm.worldToLocal(wbb);
			int wUnit = t.getUnit();
			int localTickSize = t.getLocalTickSize();
			double maxWidth = 0;
			Font f = r.getFont(t);
			switch (t.getOrientation()) {
			case BOTTOM:
				gl.glBegin(GL.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lbb.y + 0.5f));
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					textUtils.beginRendering();
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + localTickSize + 0.5f));
						gl.glEnd();
						gl.glColor4f(0, 0, 0, 1);
						textUtils.draw(f, "" + wX, lPoint.x - textUtils.getWidth(f, "" + wX) / 2, localBounds.height
								- (lPoint.y + localTickSize + t.getFontSize()));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1; // wbb.x / wUnit * wUnit;
					if (wMin < 1) {
						wMin = 1;
					}
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + localTickSize + 0.5f));
						gl.glEnd();
					}
					gl.glColor4f(0, 0, 0, 1);
					textUtils.beginRendering();
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						textUtils.draw(f, "" + wX, lPoint.x - textUtils.getWidth(f, "" + wX) / 2, localBounds.height
								- lPoint.y - localTickSize - t.getFontSize());
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				}
				f = f.deriveFont(Font.BOLD);
				f = f.deriveFont(f.getSize2D() * 1.2f);
				gl.glColor4f(0, 0, 0, 1);
				textUtils.beginRendering();
				textUtils.draw(f, t.getText(), lbb.x + lbb.width / 2 - textUtils.getWidth(f, t.getText()) / 2,
						localBounds.height - (lbb.y + localTickSize + t.getFontSize() * 2));
				textUtils.endRendering(gl, localBounds);
				break;
			case TOP:
				gl.glBegin(GL.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					textUtils.beginRendering();
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y + wbb.height));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height - localTickSize + 0.5f));
						gl.glEnd();
						gl.glColor4f(0, 0, 0, 1);
						textUtils.draw(f, "" + wX, lPoint.x - textUtils.getWidth(f, "" + wX) / 2, localBounds.height
								- (lPoint.y - localTickSize));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1; // wbb.x / wUnit * wUnit;
					if (wMin < 1) {
						wMin = 1;
					}
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lbb.y + lbb.height - localTickSize + 0.5f));
						gl.glEnd();
					}
					gl.glColor4f(0, 0, 0, 1);
					textUtils.beginRendering();
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						textUtils.draw(f, "" + wX, lPoint.x - textUtils.getWidth(f, "" + wX) / 2, localBounds.height
								- (lPoint.y - localTickSize));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				}
				f = f.deriveFont(Font.BOLD);
				f = f.deriveFont(f.getSize2D() * 1.2f);
				gl.glColor4f(0, 0, 0, 1);
				textUtils.beginRendering();
				textUtils.draw(f, t.getText(), lbb.x + lbb.width / 2 - textUtils.getWidth(f, t.getText()) / 2,
						localBounds.height - (lbb.y + lbb.height - localTickSize - t.getFontSize()));
				textUtils.endRendering(gl, localBounds);
				break;
			case LEFT:
				gl.glBegin(GL.GL_LINES);
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lbb.y + 0.5f));
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					textUtils.beginRendering();
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x + wbb.width, wY));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x - localTickSize + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glEnd();
						gl.glColor4f(0, 0, 0, 1);
						textUtils.draw(f, "" + -wY, lPoint.x - textUtils.getWidth(f, "" + -wY) - localTickSize,
								localBounds.height - (lPoint.y + localTickSize));
						maxWidth = Math.max(maxWidth, textUtils.getWidth(f, "" + -wY));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lbb.x + lbb.width - localTickSize + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lbb.x + lbb.width + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glEnd();
					}
					gl.glColor4f(0, 0, 0, 1);
					textUtils.beginRendering();
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						textUtils.draw(f, "" + -wY,
								lbb.x + lbb.width - textUtils.getWidth(f, "" + -wY) - localTickSize, localBounds.height
										- (lPoint.y + t.getFontSize() / 2));
						maxWidth = Math.max(maxWidth, textUtils.getWidth(f, "" + -wY));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				}
				f = f.deriveFont(Font.BOLD);
				f = f.deriveFont(f.getSize2D() * 1.2f);
				gl.glPushMatrix();
				gl.glTranslated(lbb.x + lbb.width - localTickSize - maxWidth, localBounds.height
						- (lbb.y + (lbb.height + textUtils.getWidth(f, t.getText())) / 2), 0);
				gl.glRotated(90, 0, 0, 1);
				gl.glColor4f(0, 0, 0, 1);
				textUtils.beginRendering();
				textUtils.draw(f, t.getText(), 0, 0);
				textUtils.endRendering(gl, localBounds);
				gl.glPopMatrix();
				break;
			case RIGHT:
				gl.glBegin(GL.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lbb.y + 0.5f));
				gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lbb.y + lbb.height + 0.5f));
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lPoint.x + localTickSize + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glEnd();
						gl.glColor4f(0, 0, 0, 1);
						textUtils.beginRendering();
						textUtils.draw(f, "" + -wY, lPoint.x + localTickSize, localBounds.height
								- (lPoint.y + localTickSize));
						textUtils.endRendering(gl, localBounds);
						maxWidth = Math.max(maxWidth, textUtils.getWidth(f, "" + -wY));
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL.GL_LINES);
						gl.glVertex2f(lbb.x + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glVertex2f(lbb.x + localTickSize + 0.5f, localBounds.height - (lPoint.y + 0.5f));
						gl.glEnd();
					}
					gl.glColor4f(0, 0, 0, 1);
					textUtils.beginRendering();
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height) {
							continue;
						}
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						textUtils.draw(f, "" + -wY, lbb.x + localTickSize,
								localBounds.height - (lPoint.y + t.getFontSize() / 2));
						maxWidth = Math.max(maxWidth, textUtils.getWidth(f, "" + -wY));
					}
					textUtils.endRendering(gl, localBounds);
					break;
				}
				}
				f = f.deriveFont(Font.BOLD);
				f = f.deriveFont(f.getSize2D() * 1.2f);
				gl.glPushMatrix();
				gl.glTranslated(lbb.x + localTickSize + maxWidth + t.getFontSize(), localBounds.height
						- (lbb.y + (lbb.height + textUtils.getWidth(f, t.getText())) / 2), 0);
				gl.glRotated(90, 0, 0, 1);
				gl.glColor4f(0, 0, 0, 1);
				textUtils.beginRendering();
				textUtils.draw(f, t.getText(), 0, 0);
				textUtils.endRendering(gl, localBounds);
				gl.glPopMatrix();
				break;
			}
		}
	}
}
