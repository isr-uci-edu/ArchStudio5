package org.archstudio.bna.things.labels;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> implements
		IThingPeer<T>, IThingListener {

	protected boolean needsTextLayout = true;
	protected TextLayout textLayout = null;
	protected double textLayoutScale = 1d;
	protected Point2D localTextPoint = null;
	protected Point2D localIndicatorPoint = null;
	protected Shape localTextShape = null;

	public AnchoredLabelThingPeer(T thing) {
		super(thing);
		thing.addThingListener(this);
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void thingChanged(ThingEvent<ET, EK, EV> thingEvent) {
		needsTextLayout = true;
	}

	@Override
	public void dispose() {
		t.removeThingListener(this);
		if (textLayout != null) {
			try {
				textLayout.dispose();
			}
			catch (Exception e) {
			}
			finally {
				textLayout = null;
			}
		}
		super.dispose();
	}

	protected void doLayout(IBNAView view, ICoordinateMapper cm, IResources r) {
		if (textLayout == null || textLayout.getDevice() != r.getDevice()) {
			if (textLayout != null) {
				try {
					textLayout.dispose();
				}
				catch (Exception e) {
				}
				finally {
					textLayout = null;
				}
			}
			textLayout = new TextLayout(r.getDevice());
			needsTextLayout = true;
		}

		needsTextLayout |= cm.getLocalScale() != textLayoutScale;

		if (needsTextLayout) {
			needsTextLayout = false;
			textLayoutScale = cm.getLocalScale();
			localTextShape = null;

			textLayout.setText(t.getText());
			int textLayoutAngle = t.getAngle();
			int size = t.getFontSize();
			Point lap = cm.worldToLocal(t.getAnchorPoint());
			Point ip = t.getIndicatorPoint();
			Point lip = ip != null ? cm.worldToLocal(ip) : lap;
			if (textLayoutScale < 1d) {
				size *= textLayoutScale;
			}
			if (size > 0) {
				textLayout.setFont(r.getFont(t.getFontName(), size, t.getFontStyle()));
				Rectangle localTextBounds = BNAUtils.toRectangle(textLayout.getBounds());
				Point localAlignment = new Point();

				HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
				switch (horizontalAlignment) {
				case LEFT:
					break;
				case CENTER:
					localAlignment.x -= localTextBounds.width / 2;
					break;
				case RIGHT:
					localAlignment.x -= localTextBounds.width;
					break;
				}
				textLayout.setAlignment(horizontalAlignment.toSWT());

				switch (t.getVerticalAlignment()) {
				case TOP:
					break;
				case MIDDLE:
					localAlignment.y -= localTextBounds.height / 2;
					break;
				case BOTTOM:
					localAlignment.y -= localTextBounds.height;
					break;
				}

				AffineTransform transform;
				GeneralPath path = new GeneralPath(new Rectangle2D.Float(localAlignment.x, localAlignment.y,
						localTextBounds.width, localTextBounds.height));
				localTextPoint = new Point2D.Float(localAlignment.x, localAlignment.y);
				Point2D ip0 = new Point2D.Float(lip.x, lip.y);
				Point2D ip1 = new Point2D.Float(localAlignment.x, localAlignment.y + localTextBounds.height / 2);
				Point2D ip2 = new Point2D.Float(localAlignment.x + localTextBounds.width, localAlignment.y
						+ localTextBounds.height / 2);

				transform = new AffineTransform();
				transform.rotate(Math.PI * textLayoutAngle / 180);
				path.transform(transform);
				transform.transform(localTextPoint, localTextPoint);
				transform.transform(ip1, ip1);
				transform.transform(ip2, ip2);

				transform = new AffineTransform();
				transform.translate(lap.x, lap.y);
				path.transform(transform);
				transform.transform(localTextPoint, localTextPoint);
				transform.transform(ip1, ip1);
				transform.transform(ip2, ip2);

				localTextShape = path;
				localIndicatorPoint = ip1;
				if (ip1.distance(ip0) > ip2.distance(ip0)) {
					localIndicatorPoint = ip2;
				}
			}
		}
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g) {
		if (BNAUtils.setForegroundColor(r, g, t, IHasColor.COLOR_KEY)) {
			doLayout(view, cm, r);
			if (localTextShape != null) {
				
				Point ip = t.getIndicatorPoint();
				if (ip != null) {
					g.drawLine(cm.worldToLocal(ip), BNAUtils.toPoint(localIndicatorPoint));
				}

				g.setFont(textLayout.getFont());
				int angle = t.getAngle();
				if (angle == 0) {
					g.drawString(t.getText(), BNAUtils.round(localTextPoint.getX()),
							BNAUtils.round(localTextPoint.getY()));
				}
				else {
					g.translate(BNAUtils.round(localTextPoint.getX()), BNAUtils.round(localTextPoint.getY()));
					g.rotate(angle);
					g.drawString(t.getText(), 0, 0);
				}
			}
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r) {
		doLayout(view, cm, r);
		if (localTextShape != null) {
			Rectangle bounds = BNAUtils.toRectangle(localTextShape.getBounds());
			Point ip = t.getIndicatorPoint();
			if (ip != null) {
				bounds.union(cm.worldToLocal(ip));
				bounds.union(BNAUtils.toPoint(localIndicatorPoint));
			}
			return bounds;
		}
		return new Rectangle();
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (localTextShape != null) {
			Point lPoint = location.getLocalPoint();
			return localTextShape.contains(lPoint.x, lPoint.y);
		}
		return false;
	}
}
