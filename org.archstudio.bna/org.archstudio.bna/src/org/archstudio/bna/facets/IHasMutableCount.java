package org.archstudio.bna.facets;

public interface IHasMutableCount extends IHasCount {

	public static final String USER_MAY_EDIT_COUNT = "userMayEditCount";

	public void setCount(int count);
}
