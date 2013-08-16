package org.archstudio.bna.things;

public abstract class AbstractAnchorPointThingPeer<T extends AbstractMutableAnchorPointThing> extends AbstractThingPeer<T> {

	public AbstractAnchorPointThingPeer(T thing) {
		super(thing);
	}

}
