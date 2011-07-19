package org.archstudio.bna.things.glass;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThingPeer;
import org.eclipse.draw2d.Graphics;

public class ReshapeHandleGlassThingPeer<T extends ReshapeHandleGlassThing> extends
		AbstractBoundedAnchorPointThingPeer<T> {

	public static final int HANDLE_WIDTH = 6;
	public static final int HANDLE_HEIGHT = 6;

	public ReshapeHandleGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
	}

}
