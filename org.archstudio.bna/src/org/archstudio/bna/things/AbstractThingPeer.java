package org.archstudio.bna.things;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public abstract class AbstractThingPeer<T extends IThing> implements IThingPeer<T> {

	protected final T t;
	protected final IBNAView view;
	protected final IBNAModel model;
	protected final ICoordinateMapper cm;

	public AbstractThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		this.t = thing;
		this.view = view;
		this.model = view.getBNAWorld().getBNAModel();
		this.cm = cm;
	}

	@Override
	public void dispose() {
	}

}
