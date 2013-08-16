package org.archstudio.bna.things.glass;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseGlassThingPeer<T extends EllipseGlassThing> extends AbstractEllipseThingPeer<T> implements
		IThingPeer<T> {

	public EllipseGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		if (Boolean.TRUE.equals(t.get(IHasSelected.SELECTED_KEY))) {
			Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
			if (!clip.intersects(lbb)) {
				return;
			}

			Shape localShape = new Ellipse2D.Float(lbb.x, lbb.y, lbb.width, lbb.height);
			BNAUtils.renderShapeSelected(t, view, cm, gl, clip, r, localShape);
		}
	}
}
