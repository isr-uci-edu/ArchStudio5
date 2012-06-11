package org.archstudio.bna.things.glass;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractEllipseThingPeer;
import org.eclipse.swt.graphics.Rectangle;

public class EllipseGlassThingPeer<T extends EllipseGlassThing> extends AbstractEllipseThingPeer<T> implements
		IThingPeer<T> {

	public EllipseGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		// TODO Auto-generated method stub
	}
}
