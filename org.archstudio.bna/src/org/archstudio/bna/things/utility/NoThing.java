package org.archstudio.bna.things.utility;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IThing;

public class NoThing extends AbstractThing implements IThing {

	public NoThing() {
		this(BNAUtils.generateUID(NoThing.class.getName()));
	}

	public NoThing(String id) {
		super(id);
	}

}
