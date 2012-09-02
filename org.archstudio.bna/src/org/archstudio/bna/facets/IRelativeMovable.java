package org.archstudio.bna.facets;

import java.awt.Dimension;

import org.archstudio.bna.IThing;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IRelativeMovable extends IThing {

	public static final String USER_MAY_MOVE = "userMayMove";

	public void moveRelative(Point worldDelta);

	public void moveRelative(Dimension worldDelta);

}
