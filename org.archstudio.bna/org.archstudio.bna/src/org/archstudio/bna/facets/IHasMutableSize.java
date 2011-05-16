package org.archstudio.bna.facets;

import org.eclipse.draw2d.geometry.Dimension;

public interface IHasMutableSize extends IHasSize {

	public static final String USER_MAY_RESIZE = "userMayResize";

	public void setSize(Dimension size);

}
