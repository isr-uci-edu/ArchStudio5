package org.archstudio.bna.things.labels;

import java.awt.Shape;
import java.awt.geom.Path2D;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class DirectionalLabelThingPeer<T extends DirectionalLabelThing> extends AbstractRectangleThingPeer<T> {

	protected Shape createTriangle(Orientation o, Rectangle lbb) {

		int n = 2;
		int d = 3;

		int x1 = lbb.x;
		int y1 = lbb.y;
		int xm = x1 + lbb.width / 2;
		int ym = y1 + lbb.height / 2;
		int x2 = x1 + lbb.width;
		int y2 = y1 + lbb.height;

		int xl = x1 + lbb.width * n / d;
		int yl = y1 + lbb.height * n / d;
		int xg = x1 + lbb.width - lbb.width * n / d;
		int yg = y1 + lbb.height - lbb.height * n / d;

		Point p1, p2, p3;

		switch (o) {
		case NORTH:
			p1 = new Point(x1, y2);
			p3 = new Point(xm, y1);
			p2 = new Point(x2, y2);
			break;
		case EAST:
			p1 = new Point(x1, y1);
			p3 = new Point(x2, ym);
			p2 = new Point(x1, y2);
			break;
		case SOUTH:
			p2 = new Point(x2, y1);
			p3 = new Point(xm, y2);
			p1 = new Point(x1, y1);
			break;
		case WEST:
			p2 = new Point(x2, y2);
			p3 = new Point(x1, ym);
			p1 = new Point(x2, y1);
			break;
		case NORTHEAST:
			p1 = new Point(x1, yg);
			p2 = new Point(x2, y1);
			p3 = new Point(xl, y2);
			break;
		case SOUTHEAST:
			p1 = new Point(xl, y1);
			p2 = new Point(x2, y2);
			p3 = new Point(x1, yl);
			break;
		case SOUTHWEST:
			p1 = new Point(x2, yl);
			p2 = new Point(x1, y2);
			p3 = new Point(xg, y1);
			break;
		case NORTHWEST:
			p1 = new Point(xg, y2);
			p2 = new Point(x1, y1);
			p3 = new Point(x2, yg);
			break;
		default:
			throw new IllegalArgumentException(o.toString());
		}

		Path2D shape = new Path2D.Double();
		shape.moveTo(p1.x, p1.y);
		shape.lineTo(p2.x, p2.y);
		shape.lineTo(p3.x, p3.y);
		shape.closePath();

		return shape;
	}

	public DirectionalLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	Flow flow;
	Orientation orientation;

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!localBounds.intersects(lbb)) {
			return false;
		}

		flow = t.getFlow();
		if (flow.equals(Flow.NONE)) {
			return false;
		}

		orientation = t.getOrientation();
		if (orientation.equals(Orientation.NONE)) {
			return false;
		}

		if (r.setColor(t, IHasColor.COLOR_KEY)) {

			int n = 1;
			int d = 4;

			int x1 = lbb.x;
			int y1 = lbb.y;
			int x2 = x1 + lbb.width;
			int y2 = y1 + lbb.height;

			int xl = x1 + lbb.width * n / d;
			int yl = y1 + lbb.height * n / d;
			int xg = x1 + lbb.width - lbb.width * n / d;
			int yg = y1 + lbb.height - lbb.height * n / d;

			switch (flow) {
			case IN:
				/*
				 * For "in" facing flows, the triangle points in the opposite direction.
				 */
				r.fillShape(createTriangle(orientation.opposite(), lbb), null, null);
				break;

			case OUT:
				r.fillShape(createTriangle(orientation, lbb), null, null);
				break;

			case INOUT: {
				switch (orientation) {

				case NORTH:
				case SOUTH: {
					Rectangle a = BNAUtils.clone(lbb);
					int height = a.height;
					a.height = a.height / 2 - 1;
					r.fillShape(createTriangle(Orientation.NORTH, a), null, null);
					a.y += height / 2;
					r.fillShape(createTriangle(Orientation.SOUTH, a), null, null);
				}
					break;

				case EAST:
				case WEST: {
					Rectangle a = BNAUtils.clone(lbb);
					int width = a.width;
					a.width = a.width / 2 - 1;
					r.fillShape(createTriangle(Orientation.WEST, a), null, null);
					a.x += width / 2;
					r.fillShape(createTriangle(Orientation.EAST, a), null, null);
				}
					break;

				case NORTHWEST:
				case SOUTHEAST: {
					Path2D shape;
					shape = new Path2D.Double();
					shape.moveTo(xl - 1, yg);
					shape.lineTo(x1, y1);
					shape.lineTo(xg, yl);
					shape.closePath();
					r.fillShape(shape, null, null);
					shape = new Path2D.Double();
					shape.moveTo(xg + 1, yl);
					shape.lineTo(x2, y2);
					shape.lineTo(xl, yg);
					shape.closePath();
					r.fillShape(shape, null, null);
				}
					break;

				case NORTHEAST:
				case SOUTHWEST: {
					Path2D shape;
					shape = new Path2D.Double();
					shape.moveTo(xl, yl);
					shape.lineTo(x2, y1);
					shape.lineTo(xg, yg - 1);
					shape.closePath();
					r.fillShape(shape, null, null);
					shape = new Path2D.Double();
					shape.moveTo(xg, yg + 1);
					shape.lineTo(x1, y2);
					shape.lineTo(xl, yl);
					shape.closePath();
					r.fillShape(shape, null, null);
				}
					break;

				default:
					throw new IllegalArgumentException(orientation.toString());
				}
			}
				break;

			default:
				throw new IllegalArgumentException(flow.toString());
			}
		}

		return true;
	}

}
