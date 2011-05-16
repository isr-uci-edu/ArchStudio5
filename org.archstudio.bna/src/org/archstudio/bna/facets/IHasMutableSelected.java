package org.archstudio.bna.facets;

public interface IHasMutableSelected extends IHasSelected {

	public static final String USER_MAY_SELECT = "userMaySelect";

	public void setSelected(boolean selected);
}
