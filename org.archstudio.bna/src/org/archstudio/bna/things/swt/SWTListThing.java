package org.archstudio.bna.things.swt;

import org.archstudio.bna.*;

public class SWTListThing extends AbstractSWTOptionSelectionThing {

	public SWTListThing() {
		this(BNAUtils.generateUID(SWTListThing.class.getName()));
	}

	public SWTListThing(String id) {
		super(id);
	}

}
