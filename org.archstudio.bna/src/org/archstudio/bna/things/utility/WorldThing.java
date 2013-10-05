package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.eclipse.jdt.annotation.Nullable;

public class WorldThing extends AbstractRectangleThing implements IHasMutableWorld {

	public WorldThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public IBNAWorld getWorld() {
		return get(WORLD_KEY);
	}

	@Override
	public void setWorld(IBNAWorld world) {
		set(WORLD_KEY, world);
	}

	public void clearWorld() {
		remove(WORLD_KEY);
	}
}
