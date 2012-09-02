package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public interface IHasMutableColor extends IHasColor {

	public static final String USER_MAY_EDIT_COLOR = "userMayEditColor";

	public void setColor(@Nullable RGB c);
}
