package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.RGB;

public interface IHasMutableEdgeColor extends IHasEdgeColor {

	public static final String USER_MAY_EDIT_EDGE_COLOR = "userMayEditEdgeColor";

	public void setEdgeColor(RGB c);
}
