package org.archstudio.bna.facets.peers;

import javax.media.opengl.GL2;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.Resources;
import org.eclipse.swt.graphics.Rectangle;

public interface IHasShadowPeer<T extends IThing> extends IThingPeer<T> {

	public void drawShadow(GL2 gl, Rectangle localBounds, Resources r);

}
