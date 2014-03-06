package org.archstudio.bna.facets;

import org.archstudio.bna.IBNAWorld;

public interface IHasMutableWorld extends IHasWorld {

	public void setWorld(IBNAWorld newWorld);

}
