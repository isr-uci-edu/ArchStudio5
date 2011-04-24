package org.archstudio.bna.things.labels;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;

public class DirectionalLabelThingPeer extends AbstractThingPeer {

	protected DirectionalLabelThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public DirectionalLabelThingPeer(IThing t) {
		super(t);
		if (!(t instanceof DirectionalLabelThing)) {
			throw new IllegalArgumentException("DirectionalLabelThingPeer can only peer for DirectionalLabelThing");
		}
		this.lt = (DirectionalLabelThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());
		if (!g.getClipping().intersects(localBoundingBox))
			return;

		Flow f = lt.getFlow();
		if (f.equals(Flow.NONE))
			return;

		Orientation o = lt.getOrientation();
		if (o.equals(Orientation.NONE))
			return;

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}
		g.setBackground(fg);

		if (f.equals(Flow.OUT) || f.equals(Flow.IN)) {
			//For "out" facing flows, the triangle points in the direction of the 
			//orientation.  For "in" facing flows, it points the opposite direction.
			if (f.equals(Flow.IN))
				o = o.opposite();
			int[] trianglePoints = BNAUtils.createIsocolesTriangle(localBoundingBox, o);
			g.fillPolygon(trianglePoints);
		}
		else if (f.equals(Flow.INOUT)) {
			Rectangle bb = new Rectangle(localBoundingBox.x + 1, localBoundingBox.y + 1, localBoundingBox.width - 2, localBoundingBox.height - 2);
			//We have to render two triangles.
			int[] trianglePoints = new int[6];
			switch (o) {
			case NORTH:
			case SOUTH:
				trianglePoints[0] = bb.x;
				trianglePoints[1] = bb.y + (bb.height / 2);

				trianglePoints[2] = bb.x + (bb.width / 2);
				trianglePoints[3] = bb.y;

				trianglePoints[4] = bb.x + bb.width;
				trianglePoints[5] = bb.y + (bb.height / 2);

				g.fillPolygon(trianglePoints);

				trianglePoints[0] = bb.x;
				trianglePoints[1] = bb.y + (bb.height / 2) + 1;

				trianglePoints[2] = bb.x + (bb.width / 2);
				trianglePoints[3] = bb.y + bb.height;

				trianglePoints[4] = bb.x + bb.width;
				trianglePoints[5] = bb.y + (bb.height / 2) + 1;

				g.fillPolygon(trianglePoints);
				break;
			case EAST:
			case WEST:
				trianglePoints[0] = bb.x + (bb.width / 2);
				trianglePoints[1] = bb.y;

				trianglePoints[2] = bb.x;
				trianglePoints[3] = bb.y + (bb.height / 2);

				trianglePoints[4] = bb.x + (bb.width / 2);
				trianglePoints[5] = bb.y + bb.height;

				g.fillPolygon(trianglePoints);

				trianglePoints[0] = bb.x + (bb.width / 2) + 1;
				trianglePoints[1] = bb.y;

				trianglePoints[2] = bb.x + bb.width;
				trianglePoints[3] = bb.y + (bb.height / 2);

				trianglePoints[4] = bb.x + (bb.width / 2);
				trianglePoints[5] = bb.y + bb.height;

				g.fillPolygon(trianglePoints);
				break;
			case NORTHEAST:
			case SOUTHWEST:
				trianglePoints[0] = bb.x + 1 + 1;
				trianglePoints[1] = bb.y + 1;

				trianglePoints[2] = bb.x + bb.width;
				trianglePoints[3] = bb.y + 1;

				trianglePoints[4] = bb.x + bb.width;
				trianglePoints[5] = bb.y + bb.height - 1;

				g.fillPolygon(trianglePoints);

				trianglePoints[0] = bb.x + 1;
				trianglePoints[1] = bb.y + 1;

				trianglePoints[2] = bb.x + 1;
				trianglePoints[3] = bb.y + bb.height;

				trianglePoints[4] = bb.x + bb.width;
				trianglePoints[5] = bb.y + bb.height;

				g.fillPolygon(trianglePoints);
				break;
			case NORTHWEST:
			case SOUTHEAST:
				trianglePoints[0] = bb.x + 1;
				trianglePoints[1] = bb.y + 1;

				trianglePoints[2] = bb.x + bb.width - 1;
				trianglePoints[3] = bb.y + 1;

				trianglePoints[4] = bb.x + 1;
				trianglePoints[5] = bb.y + bb.height - 1;

				g.fillPolygon(trianglePoints);

				trianglePoints[0] = bb.x + bb.width;
				trianglePoints[1] = bb.y + bb.height;

				trianglePoints[2] = bb.x + bb.width;
				trianglePoints[3] = bb.y + 1;

				trianglePoints[4] = bb.x + 1;
				trianglePoints[5] = bb.y + bb.height;

				g.fillPolygon(trianglePoints);
				break;
			}

		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
