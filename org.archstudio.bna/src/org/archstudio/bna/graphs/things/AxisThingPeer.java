package org.archstudio.bna.graphs.things;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.graphs.GraphCoordinateMapper;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.opengl.util.awt.TextRenderer;

public class AxisThingPeer<T extends AxisThing> extends AbstractRectangleThingPeer<T> {

	public AxisThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Point canvasSize = r.getComposite().getSize();
		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY) & r.setLineStyle(t)) {
			TextRenderer tr = r.getTextRenderer(r.getFont(t));
			Rectangle wbb = t.getBoundingBox();
			Rectangle lbb = cm.worldToLocal(wbb);
			int wUnit = t.getUnit();
			int localTickSize = t.getLocalTickSize();
			int maxWidth = 0;
			switch (t.getOrientation()) {
			case BOTTOM:
				gl.glBegin(GL2.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, lbb.y + 0.5f);
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, lbb.y + 0.5f);
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + 0.5f);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + localTickSize + 0.5f);
						gl.glEnd();
						tr.beginRendering(canvasSize.x, canvasSize.y);
						tr.draw("" + wX, lPoint.x - (int) tr.getBounds("" + wX).getWidth() / 2, canvasSize.y
								- (lPoint.y + localTickSize + t.getFontSize()));
						tr.endRendering();
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1; // wbb.x / wUnit * wUnit;
					if (wMin < 1)
						wMin = 1;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + 0.5f);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + localTickSize + 0.5f);
						gl.glEnd();
					}
					tr.beginRendering(canvasSize.x, canvasSize.y);
					tr.setColor(0, 0, 0, 1f);
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						tr.draw("" + wX, lPoint.x - (int) tr.getBounds("" + wX).getWidth() / 2, canvasSize.y - lPoint.y
								- localTickSize - t.getFontSize());
					}
					tr.endRendering();
					break;
				}
				}
				tr.beginRendering(canvasSize.x, canvasSize.y);
				tr.setColor(0, 0, 0, 1);
				tr.draw(t.getText(), lbb.x + lbb.width / 2 - (int) tr.getBounds(t.getText()).getWidth() / 2,
						canvasSize.y - lbb.y - localTickSize - t.getFontSize() * 2);
				tr.endRendering();
				break;
			case TOP:
				gl.glBegin(GL2.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, lbb.y + lbb.height + 0.5f);
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, lbb.y + lbb.height + 0.5f);
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getXAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y + wbb.height));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + lbb.height + 0.5f);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + lbb.height - localTickSize + 0.5f);
						gl.glEnd();
						tr.beginRendering(canvasSize.x, canvasSize.y);
						tr.draw("" + wX, lPoint.x - (int) tr.getBounds("" + wX).getWidth() / 2, canvasSize.y
								- (lPoint.y - localTickSize));
						tr.endRendering();
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = 1; // wbb.x / wUnit * wUnit;
					if (wMin < 1)
						wMin = 1;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + lbb.height + 0.5f);
						gl.glVertex2f(lPoint.x + 0.5f, lbb.y + lbb.height - localTickSize + 0.5f);
						gl.glEnd();
					}
					tr.beginRendering(canvasSize.x, canvasSize.y);
					tr.setColor(0, 0, 0, 1f);
					for (int wX = wMin; wX <= wMax; wX *= wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						tr.draw("" + wX, lPoint.x - (int) tr.getBounds("" + wX).getWidth() / 2, canvasSize.y - lPoint.y
								+ localTickSize);
					}
					tr.endRendering();
					break;
				}
				}
				tr.beginRendering(canvasSize.x, canvasSize.y);
				tr.setColor(0, 0, 0, 1);
				tr.draw(t.getText(), lbb.x + lbb.width / 2 - (int) tr.getBounds(t.getText()).getWidth() / 2,
						canvasSize.y - lbb.y + localTickSize + t.getFontSize());
				tr.endRendering();
				break;
			case LEFT:
				gl.glBegin(GL2.GL_LINES);
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, lbb.y + 0.5f);
				gl.glVertex2f(lbb.x + lbb.width + 0.5f, lbb.y + lbb.height + 0.5f);
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x + wbb.width, wY));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x - localTickSize + 0.5f, lPoint.y + 0.5f);
						gl.glVertex2f(lPoint.x + 0.5f, lPoint.y + 0.5f);
						gl.glEnd();
						tr.beginRendering(canvasSize.x, canvasSize.y);
						tr.draw("" + -wY, lPoint.x - (int) tr.getBounds("" + -wY).getWidth() - localTickSize,
								canvasSize.y - (lPoint.y + localTickSize));
						tr.endRendering();
						maxWidth = Math.max(maxWidth, (int) tr.getBounds("" + -wY).getWidth());
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lbb.x + lbb.width - localTickSize + 0.5f, lPoint.y + 0.5f);
						gl.glVertex2f(lbb.x + lbb.width + 0.5f, lPoint.y + 0.5f);
						gl.glEnd();
					}
					tr.beginRendering(canvasSize.x, canvasSize.y);
					tr.setColor(0, 0, 0, 1f);
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						tr.draw("" + -wY, lbb.x + lbb.width - (int) tr.getBounds("" + -wY).getWidth() - localTickSize,
								canvasSize.y - lPoint.y - t.getFontSize() / 2);
						maxWidth = Math.max(maxWidth, (int) tr.getBounds("" + -wY).getWidth());
					}
					tr.endRendering();
					break;
				}
				}
				tr.beginRendering(canvasSize.x, canvasSize.y);
				gl.glMatrixMode(GL2.GL_MODELVIEW);
				gl.glTranslated(lbb.x + lbb.width - localTickSize - maxWidth, canvasSize.y - lbb.y
						- (lbb.height + tr.getBounds(t.getText()).getWidth()) / 2, 0);
				gl.glRotated(90, 0, 0, 1);
				tr.setColor(0, 0, 0, 1);
				tr.draw(t.getText(), 0, 0);
				tr.endRendering();
				break;
			case RIGHT:
				gl.glBegin(GL2.GL_LINES);
				gl.glVertex2f(lbb.x + 0.5f, lbb.y + 0.5f);
				gl.glVertex2f(lbb.x + 0.5f, lbb.y + lbb.height + 0.5f);
				gl.glEnd();
				switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
						: GraphCoordinateMapper.Type.LINEAR) {
				case LINEAR: {
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lPoint.x + 0.5f, lPoint.y + 0.5f);
						gl.glVertex2f(lPoint.x + localTickSize + 0.5f, lPoint.y + 0.5f);
						gl.glEnd();
						tr.beginRendering(canvasSize.x, canvasSize.y);
						tr.draw("" + -wY, lPoint.x + localTickSize, canvasSize.y - (lPoint.y + localTickSize));
						tr.endRendering();
						maxWidth = Math.max(maxWidth, (int) tr.getBounds("" + -wY).getWidth());
					}
					break;
				}
				case LOGARITHMIC: {
					int wMin = -1;
					int wMax = wbb.y;
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						gl.glBegin(GL2.GL_LINES);
						gl.glVertex2f(lbb.x + 0.5f, lPoint.y + 0.5f);
						gl.glVertex2f(lbb.x + localTickSize + 0.5f, lPoint.y + 0.5f);
						gl.glEnd();
					}
					tr.beginRendering(canvasSize.x, canvasSize.y);
					tr.setColor(0, 0, 0, 1f);
					for (int wY = wMin; wY >= wMax; wY *= wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						tr.draw("" + -wY, lbb.x + localTickSize, canvasSize.y - lPoint.y - t.getFontSize() / 2);
						maxWidth = Math.max(maxWidth, (int) tr.getBounds("" + -wY).getWidth());
					}
					tr.endRendering();
					break;
				}
				}
				tr.beginRendering(canvasSize.x, canvasSize.y);
				gl.glMatrixMode(GL2.GL_MODELVIEW);
				gl.glTranslated(lbb.x + localTickSize + maxWidth + t.getFontSize(), canvasSize.y - lbb.y
						- (lbb.height + tr.getBounds(t.getText()).getWidth()) / 2, 0);
				gl.glRotated(90, 0, 0, 1);
				tr.setColor(0, 0, 0, 1);
				tr.draw(t.getText(), 0, 0);
				tr.endRendering();
				break;
			}
		}
	}
	//		switch (t.getOrientation()) {
	//		case LEFT: {
	//			g.drawLine(lbb.x + lbb.width, lbb.y, lbb.x + lbb.width, lbb.y + lbb.height);
	//			int maxWidth = 0;
	//			switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
	//					: GraphCoordinateMapper.Type.LINEAR) {
	//			case LINEAR: {
	//				int wMin = wbb.y / wUnit * wUnit;
	//				int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
	//				for (int wY = wMin; wY <= wMax; wY += wUnit) {
	//					if (wY < wbb.y || wY > wbb.y + wbb.height)
	//						continue;
	//					Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
	//					g.drawLine(lbb.x + lbb.width, lPoint.y, lbb.x + lbb.width - localTickSize, lPoint.y);
	//					tl.setText(-wY + " ");
	//					g.drawString(-wY + " ", lPoint.x + lbb.width - tl.getBounds().width - localTickSize,
	//							lPoint.y - tl.getBounds().height / 2);
	//					maxWidth = Math.max(maxWidth, tl.getBounds().width);
	//				}
	//				break;
	//			}
	//			case LOGARITHMIC: {
	//				int wMin = -1;
	//				int wMax = -wbb.y;
	//				for (int wY = wMin; wY <= wMax; wY *= wUnit) {
	//					if (wY < wbb.y || wY > wbb.y + wbb.height)
	//						continue;
	//					Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
	//					g.drawLine(lbb.x + lbb.width, lPoint.y, lbb.x + lbb.width - localTickSize, lPoint.y);
	//					tl.setText(-wY + " ");
	//					g.drawString(-wY + " ", lPoint.x + lbb.width - tl.getBounds().width - localTickSize,
	//							lPoint.y - tl.getBounds().height / 2);
	//					maxWidth = Math.max(maxWidth, tl.getBounds().width);
	//				}
	//				break;
	//			}
	//			}
	//			g.setFont(r.getFont(t.getFontName(), t.getFontSize(), FontStyle.BOLD));
	//			tl.setFont(g.getFont());
	//			tl.setText(t.getText());
	//			g.translate(lbb.x + lbb.width - localTickSize - tl.getBounds().height - maxWidth, lbb.y + lbb.height / 2
	//					+ tl.getBounds().width / 2);
	//			g.rotate(-90);
	//			g.drawString(t.getText(), 0, 0);
	//			break;
	//		}
	//		case RIGHT: {
	//			g.drawLine(lbb.x, lbb.y, lbb.x, lbb.y + lbb.height);
	//			int maxWidth = 0;
	//			switch (cm instanceof GraphCoordinateMapper ? ((GraphCoordinateMapper) cm).getYAxisType()
	//					: GraphCoordinateMapper.Type.LINEAR) {
	//			case LINEAR: {
	//				int wMin = wbb.y / wUnit * wUnit;
	//				int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
	//				for (int wY = wMin; wY <= wMax; wY += wUnit) {
	//					if (wY < wbb.y || wY > wbb.y + wbb.height)
	//						continue;
	//					Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
	//					g.drawLine(lbb.x, lPoint.y, lbb.x + localTickSize, lPoint.y);
	//					tl.setText(" " + -wY);
	//					g.drawString(" " + -wY, lPoint.x + localTickSize, lPoint.y - tl.getBounds().height / 2);
	//					maxWidth = Math.max(maxWidth, tl.getBounds().width);
	//				}
	//				break;
	//			}
	//			case LOGARITHMIC: {
	//				int wMin = -1;
	//				int wMax = -wbb.y;
	//				for (int wY = wMin; wY <= wMax; wY *= wUnit) {
	//					if (wY < wbb.y || wY > wbb.y + wbb.height)
	//						continue;
	//					Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
	//					g.drawLine(lbb.x, lPoint.y, lbb.x + localTickSize, lPoint.y);
	//					tl.setText(" " + -wY);
	//					g.drawString(" " + -wY, lPoint.x + localTickSize, lPoint.y - tl.getBounds().height / 2);
	//					maxWidth = Math.max(maxWidth, tl.getBounds().width);
	//				}
	//				break;
	//			}
	//			}
	//			g.setFont(r.getFont(t.getFontName(), t.getFontSize(), FontStyle.BOLD));
	//			tl.setFont(g.getFont());
	//			tl.setText(t.getText());
	//			g.translate(lbb.x + localTickSize + maxWidth, lbb.y + lbb.height / 2 + tl.getBounds().width / 2);
	//			g.rotate(-90);
	//			g.drawString(t.getText(), 0, 0);
	//			break;
	//		}
	//		}
}
