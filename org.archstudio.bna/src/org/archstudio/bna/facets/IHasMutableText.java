package org.archstudio.bna.facets;

public interface IHasMutableText extends IHasText {

	public static final String USER_MAY_EDIT_TEXT = "userMayEditText";

	public void setText(String text);
}
