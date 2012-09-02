package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public interface IHasMutableEdgeColor extends IHasEdgeColor {

	public static final String USER_MAY_EDIT_EDGE_COLOR = "userMayEditEdgeColor";

	public void setEdgeColor(@Nullable RGB c);
}
