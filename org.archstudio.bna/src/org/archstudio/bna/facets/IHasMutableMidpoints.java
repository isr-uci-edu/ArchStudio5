package org.archstudio.bna.facets;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;

public interface IHasMutableMidpoints extends IHasMidpoints {

	public static final String USER_MAY_ADD_MIDPOINTS = "userMayAddMidpoints";
	public static final String USER_MAY_REMOVE_MIDPOINTS = "userMayRemoveMidpoints";
	public static final String USER_MAY_MOVE_MIDPOINTS = "userMayMoveMidpoints";

	public void setMidpoints(List<Point> midpoints);

}
