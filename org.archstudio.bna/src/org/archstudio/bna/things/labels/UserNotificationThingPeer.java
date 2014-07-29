package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.swtutils.constants.LineStyle;
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
		Font font = r.getFont(t.getRawFontName(), t.getRawFontStyle(), t.getRawFontSize());
		Dimension size = r.getTextSize(font, text);

		Rectangle2D.Double localShape = new Rectangle2D.Double(lap.getX() - size.width / 2, lap.getY() - size.height
				/ 2, size.width, size.height);
		localShape.x -= SPACING;
		localShape.y -= SPACING;
		localShape.width += 2 * SPACING;
		localShape.height += 2 * SPACING;

		double alpha = SystemUtils
				.bound(0, Math.sin(Math.PI * t.getLife() / UserNotificationThing.TIME_TO_LIVE) * 2, 1);

		if (t.getColor() != null) {
			r.fillShape(localShape, t.getColor(), t.isGradientFilled() ? t.getSecondaryColor() : null, alpha);
		}

		r.drawShape(localShape, t.getRawEdgeColor(), 1, LineStyle.SOLID, 1);
		r.drawText(font, text, localShape.x + SPACING, localShape.y + SPACING, t.getRawEdgeColor(), 1);

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
