package org.archstudio.bna.facets;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasMutableEndpoints extends IHasEndpoints {

	public static final String USER_MAY_MOVE_ENDPOINT1 = "userMayMoveEndpoint1";
	public static final String USER_MAY_MOVE_ENDPOINT2 = "userMayMoveEndpoint2";
	public static final String USER_MAY_RESTICK_ENDPOINT1 = "userMayRestickEndpoint1";
	public static final String USER_MAY_RESTICK_ENDPOINT2 = "userMayRestickEndpoint2";

	public void setEndpoint1(Point endpoint1);

	public void setEndpoint2(Point endpoint2);

}
