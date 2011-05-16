package org.archstudio.bna.things;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutablePathData;
import org.archstudio.bna.utils.PathDataUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.PathData;

public class PathEssenceThing extends AbstractAnchorPointThing implements IHasMutablePathData, IHasBoundingBox {

	public PathEssenceThing(Object id) {
		super(id);

		addThingListener(new IThingListener() {

			public void thingChanged(ThingEvent thingEvent) {
				Object propertyName = thingEvent.getPropertyName();
				if (!IHasBoundingBox.BOUNDING_BOX_KEY.equals(propertyName)) {
					put(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setPathData(PathDataUtils.EMPTY_PATH_DATA);
		put(IHasBoundingBox.BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	final protected Rectangle calculateBoundingBox() {

		PathData p = getPathData();
		Point o = getAnchorPoint();

		float x1 = Float.MAX_VALUE;
		float y1 = Float.MAX_VALUE;
		float x2 = Float.MIN_VALUE;
		float y2 = Float.MIN_VALUE;

		int i = 0;
		float[] points = p.points;

		for (byte type : p.types) {
			switch (type) {
			case SWT.PATH_MOVE_TO: {
				float x = points[i++] + o.x;
				float y = points[i++] + o.y;
				x1 = Math.min(x1, x);
				x2 = Math.max(x2, x);
				y1 = Math.min(y1, y);
				y2 = Math.max(y2, y);
				break;
			}
			case SWT.PATH_LINE_TO: {
				float x = points[i++] + o.x;
				float y = points[i++] + o.y;
				x1 = Math.min(x1, x);
				x2 = Math.max(x2, x);
				y1 = Math.min(y1, y);
				y2 = Math.max(y2, y);
				break;
			}
			case SWT.PATH_CLOSE: {
				// do nothing, the "close" point was already included
				break;
			}
			default:
				throw new UnsupportedOperationException("Unimplemented");
			}
		}

		int ix1 = (int) Math.round(Math.floor(x1));
		int iy1 = (int) Math.round(Math.floor(y1));
		int ix2 = (int) Math.round(Math.ceil(x2));
		int iy2 = (int) Math.round(Math.ceil(y2));

		return new Rectangle(ix1, iy1, ix2 - ix1, iy2 - iy1);
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) get(BOUNDING_BOX_KEY);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public PathData getPathData() {
		return get(PATH_DATA_KEY);
	}

	public void setPathData(PathData pathData) {
		put(PATH_DATA_KEY, pathData);
	}

}
