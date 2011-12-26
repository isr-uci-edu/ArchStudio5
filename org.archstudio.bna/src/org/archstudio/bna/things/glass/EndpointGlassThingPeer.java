package org.archstudio.bna.things.glass;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.eclipse.swt.graphics.Rectangle;

public class EndpointGlassThingPeer<T extends EndpointGlassThing> extends AbstractBoundedAnchorPointThingPeer<T>
		implements IThingPeer<T> {

	public EndpointGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
	}
}
