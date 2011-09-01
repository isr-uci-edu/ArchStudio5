package org.archstudio.bna.things.labels;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

public class ImageThingPeer<T extends ImageThing> extends AbstractRectangleThingPeer<T> {

	protected Image image = null;

	public ImageThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {

		ImageData imageData = t.getImageData();
		if (imageData == null)
			return;

		Image image = r.getImage(imageData);
		if (image == null)
			return;

		Rectangle lbb = cm.worldToLocal(t.getBoundingBox());
		if (lbb.height >= 1 && lbb.width >= 1) {
			if (t.isScaled()) {
				g.drawImage(image, 0, 0, imageData.width, imageData.height, lbb.x, lbb.y, lbb.width, lbb.height);
			}
			else {
				int x = lbb.x;
				int y = lbb.y;
				switch (t.getHorizontalAlignment()) {
				case LEFT:
					break;
				case CENTER:
					x += (lbb.width / 2) - (imageData.width / 2);
					break;
				case RIGHT:
					x += (lbb.width) - (imageData.width);
					break;
				}

				switch (t.getVerticalAlignment()) {
				case TOP:
					break;
				case MIDDLE:
					y += (lbb.height / 2) - (imageData.height / 2);
					break;
				case BOTTOM:
					y += (lbb.height) - (imageData.height);
					break;
				}
				g.drawImage(image, x, y);
			}
		}
	}
}
