package org.archstudio.bna.graphs.things;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

public class AxisThingPeer<T extends AxisThing> extends AbstractRectangleThingPeer<T> {

	public AxisThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		TextLayout tl = null;
		try {
			if (BNAUtils.setForegroundColor(r, g, t, IHasEdgeColor.EDGE_COLOR_KEY)) {
				BNAUtils.setLineStyle(r, g, t);
				BNAUtils.setFont(r, g, t);
				tl = new TextLayout(r.getDevice());
				tl.setFont(g.getFont());

				Rectangle wbb = t.getBoundingBox();
				Rectangle lbb = cm.worldToLocal(wbb);
				int wUnit = t.getUnit();
				int localTickSize = t.getLocalTickSize();

				// draw baseline
				switch (t.getOrientation()) {
				case BOTTOM: {
					g.drawLine(lbb.x, lbb.y, lbb.x + lbb.width, lbb.y);
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						g.drawLine(lPoint.x, lbb.y, lPoint.x, lbb.y + localTickSize);
						tl.setText("" + wX);
						g.drawString("" + wX, lPoint.x - tl.getBounds().width / 2, lPoint.y + localTickSize);
					}
					tl.setText(t.getText());
					g.drawString(t.getText(), lbb.x + lbb.width / 2 - tl.getBounds().width / 2, lbb.y + localTickSize
							+ tl.getBounds().height);
					break;
				}
				case TOP: {
					g.drawLine(lbb.x, lbb.y + lbb.height, lbb.x + lbb.width, lbb.y + lbb.height);
					int wMin = wbb.x / wUnit * wUnit;
					int wMax = ((wbb.x + wbb.width) / wUnit + 1) * wUnit;
					for (int wX = wMin; wX <= wMax; wX += wUnit) {
						if (wX < wbb.x || wX > wbb.x + wbb.width)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wX, wbb.y));
						g.drawLine(lPoint.x, lbb.y + lbb.height, lPoint.x, lbb.y + lbb.height - localTickSize);
						tl.setText("" + wX);
						g.drawString("" + wX, lPoint.x - tl.getBounds().width / 2, lPoint.y + lbb.height
								- localTickSize - tl.getBounds().height);
					}
					tl.setText(t.getText());
					g.drawString(t.getText(), lbb.x + lbb.width / 2 - tl.getBounds().width / 2, lbb.y + lbb.height
							- localTickSize - tl.getBounds().height * 2);
					break;
				}
				case LEFT: {
					g.drawLine(lbb.x + lbb.width, lbb.y, lbb.x + lbb.width, lbb.y + lbb.height);
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					int maxWidth = 0;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						g.drawLine(lbb.x + lbb.width, lPoint.y, lbb.x + lbb.width - localTickSize, lPoint.y);
						tl.setText(-wY + " ");
						g.drawString(-wY + " ", lPoint.x + lbb.width - tl.getBounds().width - localTickSize, lPoint.y
								- tl.getBounds().height / 2);
						maxWidth = Math.max(maxWidth, tl.getBounds().width);
					}
					tl.setText(t.getText());
					g.translate(lbb.x + lbb.width - localTickSize - tl.getBounds().height - maxWidth, lbb.y
							+ lbb.height / 2 + tl.getBounds().width / 2);
					g.rotate(-90);
					g.drawString(t.getText(), 0, 0);
					break;
				}
				case RIGHT: {
					g.drawLine(lbb.x, lbb.y, lbb.x, lbb.y + lbb.height);
					int wMin = wbb.y / wUnit * wUnit;
					int wMax = ((wbb.y + wbb.height) / wUnit + 1) * wUnit;
					int maxWidth = 0;
					for (int wY = wMin; wY <= wMax; wY += wUnit) {
						if (wY < wbb.y || wY > wbb.y + wbb.height)
							continue;
						Point lPoint = cm.worldToLocal(new Point(wbb.x, wY));
						g.drawLine(lbb.x, lPoint.y, lbb.x + localTickSize, lPoint.y);
						tl.setText(" " + -wY);
						g.drawString(" " + -wY, lPoint.x + localTickSize, lPoint.y - tl.getBounds().height / 2);
						maxWidth = Math.max(maxWidth, tl.getBounds().width);
					}
					tl.setText(t.getText());
					g.translate(lbb.x + localTickSize + maxWidth, lbb.y + lbb.height / 2 + tl.getBounds().width / 2);
					g.rotate(-90);
					g.drawString(t.getText(), 0, 0);
					break;
				}
				}
			}
		}
		finally {
			if (tl != null)
				tl.dispose();
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		return null;
	}
}
