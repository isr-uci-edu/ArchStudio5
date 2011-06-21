package org.archstudio.bna.facets.peers;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.eclipse.draw2d.Graphics;

public interface IHasShadowPeer<T extends IThing> extends IThingPeer<T> {

	public void drawShadow(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, boolean fill);

}
