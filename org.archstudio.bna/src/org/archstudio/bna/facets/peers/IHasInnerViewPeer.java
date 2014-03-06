package org.archstudio.bna.facets.peers;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public interface IHasInnerViewPeer<T extends IThing> extends IThingPeer<T> {

	public IBNAView getInnerView();

}
