package org.archstudio.bna.facets.peers;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public interface IHasShadowPeer<T extends IThing> extends IThingPeer<T> {

	public void drawShadow(IBNAView view, ICoordinateMapper cm, GL2 gl, IResources r);

}
