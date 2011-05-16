package org.archstudio.bna.facets;

public interface IHasMutableAngle extends IHasAngle {

	public static final String USER_MAY_CHANGE_ANGLE = "userMayChangeAngle";

	public void setAngle(int angle);
}
