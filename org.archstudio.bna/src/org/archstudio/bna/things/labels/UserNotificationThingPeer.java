package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Rectangle;

public class UserNotificationThingPeer<T extends UserNotificationThing> extends AbstractThingPeer<T> implements
		IThingPeer<T> {

	int SPACING = 4;

	public UserNotificationThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		Point2D lap = cm.worldToLocal(t.getRawAnchorPoint());
		String text = t.getText().trim();
		Font font = r.getFont(t, t.getFontSize());
		Dimension size = r.getTextSize(font, text);

		Rectangle2D.Double localShape = new Rectangle2D.Double(lap.getX() - size.width / 2, lap.getY() - size.height
				/ 2, size.width, size.height);
		localShape.x -= SPACING;
		localShape.y -= SPACING;
		localShape.width += 2 * SPACING;
		localShape.height += 2 * SPACING;

		float alpha = SystemUtils.bound(0, (5000 - Math.abs(t.getLife() - 5000)) / 2500f, 1);

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), t.isGradientFilled() ? t.getSecondaryColor() : null, alpha);
		}

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY, alpha)) {
			r.drawShape(localShape);
			r.drawText(font, text, localShape.x + SPACING, localShape.y + SPACING);
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
