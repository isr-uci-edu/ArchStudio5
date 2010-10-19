package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.GC;

import org.archstudio.bna.AbstractThingPeer;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;

public class EnvironmentPropertiesThingPeer extends AbstractThingPeer implements IThingPeer {

	public EnvironmentPropertiesThingPeer(IThing t) {
		super(t);
	}

	public void draw(IBNAView view, GC g) {
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}
