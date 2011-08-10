package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IThing;

public class MirrorValueLogic extends AbstractMirrorValueLogic<IThing, IThing> {

	public MirrorValueLogic() {
		super(IThing.class, IThing.class);
	}

}
