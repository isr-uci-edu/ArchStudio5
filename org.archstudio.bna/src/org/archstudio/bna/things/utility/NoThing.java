package org.archstudio.bna.things.utility;

import org.archstudio.bna.IThing;
import org.archstudio.bna.things.AbstractThing;
import org.eclipse.jdt.annotation.Nullable;

public class NoThing extends AbstractThing implements IThing {

	public NoThing(@Nullable Object id) {
		super(id);
	}
}
