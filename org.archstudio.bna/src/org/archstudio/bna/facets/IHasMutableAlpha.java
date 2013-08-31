package org.archstudio.bna.facets;

public interface IHasMutableAlpha extends IHasAlpha {

	public static final String USER_MAY_CHANGE_ALPHA = "userMayChangeAlpha";

	public void setAlpha(float angle);
}
