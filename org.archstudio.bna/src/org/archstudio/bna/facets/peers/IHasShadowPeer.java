package org.archstudio.bna.facets.peers;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.eclipse.swt.graphics.Rectangle;

public interface IHasShadowPeer {

	public void drawShadow(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r);

}
