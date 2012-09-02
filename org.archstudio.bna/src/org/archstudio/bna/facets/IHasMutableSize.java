package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableSize extends IHasSize {

	public static final String USER_MAY_RESIZE = "userMayResize";

	public void setSize(Dimension size);

}
