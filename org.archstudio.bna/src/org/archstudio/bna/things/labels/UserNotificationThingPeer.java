package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class UserNotificationThingPeer<T extends UserNotificationThing> extends AbstractAnchorPointThingPeer<T>
		implements IThingPeer<T> {

	int SPACING = 4;

	public UserNotificationThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {

		Point lap = cm.worldToLocal(t.getAnchorPoint());
		String text = t.getText().trim();
		Font font = r.getFont(t, t.getFontSize());
		Dimension size = r.getTextSize(font, text);

		Rectangle lbb = new Rectangle(lap.x - size.width / 2, lap.y - size.height / 2, size.width, size.height);
		lbb.x -= SPACING;
		lbb.y -= SPACING;
		lbb.width += 2 * SPACING;
		lbb.height += 2 * SPACING;

		Shape localShape = new Rectangle2D.Double(lbb.x, lbb.y, lbb.width, lbb.height);
		float alpha = SystemUtils.bound(0, (16 - Math.abs(t.getLife() - 16)) / 8f, 1);

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), t.isGradientFilled() ? t.getSecondaryColor() : null, alpha);
		}

		if (r.setColor(t, IHasEdgeColor.EDGE_COLOR_KEY, alpha)) {
			r.drawShape(localShape);
			r.drawText(font, text, lbb.x + SPACING, lbb.y + SPACING);
		}

		return true;
	}
}
