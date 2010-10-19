package org.archstudio.bna.things.utility;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;

public class EnvironmentPropertiesThing extends AbstractThing {
	public EnvironmentPropertiesThing() {
		this(BNAUtils.generateUID(EnvironmentPropertiesThing.class.getName()));
	}

	public EnvironmentPropertiesThing(String id) {
		super(id);
	}
}
