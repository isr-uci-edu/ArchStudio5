package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Rectangle2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractAnchorPointThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.jogamp.opengl.util.awt.TextRenderer;

public class AnchoredLabelThingPeer<T extends AnchoredLabelThing> extends AbstractAnchorPointThingPeer<T> implements
		IThingPeer<T> {

	public AnchoredLabelThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		org.eclipse.swt.graphics.Point canvasSize = view.getComposite().getSize();
		if (r.setColor(t, IHasColor.COLOR_KEY)) {
			String text = t.getText();
			Font font = r.getFont(t);
			TextRenderer tr = r.getTextRenderer(font);
			Rectangle2D rectangleSize = tr.getBounds(text);
			Dimension size = new Dimension(BNAUtils.round(rectangleSize.getWidth()), BNAUtils.round(rectangleSize
					.getHeight()));
			Point localAnchor = cm.worldToLocal(t.getAnchorPoint());
			Point localOffset = new Point(0, 0);
			int offset = t.getOffset();

			HorizontalAlignment horizontalAlignment = t.getHorizontalAlignment();
			switch (horizontalAlignment) {
			case LEFT:
				localOffset.x += offset * cm.getLocalScale();
				break;
			case CENTER:
				localOffset.x -= size.width / 2;
				break;
			case RIGHT:
				localOffset.x -= size.width;
				localOffset.x -= offset * cm.getLocalScale();
				break;
			}
			switch (t.getVerticalAlignment()) {
			case TOP:
				break;
			case MIDDLE:
				localOffset.y -= size.height / 2;
				break;
			case BOTTOM:
				localOffset.y -= size.height;
				break;
			}

			gl.glTranslatef(localAnchor.x, canvasSize.y - localOffset.y, 0);
			gl.glRotatef(t.getAngle(), 1, 1, 0);
			tr.draw3D(text, 0, 0, 0, 1);
		}
	}

	@Override
	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm) {
		Rectangle bounds = new Rectangle(0, 0, 0, 0);
		bounds.x = bounds.y = Integer.MIN_VALUE / 2;
		bounds.width = bounds.height = Integer.MAX_VALUE;
		return bounds;
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
