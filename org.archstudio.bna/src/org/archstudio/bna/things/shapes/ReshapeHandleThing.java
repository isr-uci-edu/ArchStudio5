package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class ReshapeHandleThing extends ReshapeHandleThingBase {

	public static final RGB UNSTUCK_COLOR = new RGB(255, 0, 0);
	public static final RGB STUCK_COLOR = new RGB(0, 255, 0);
	public static final RGB NORMAL_COLOR = new RGB(0, 0, 255);

	public ReshapeHandleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		initProperty(COLOR_KEY, NORMAL_COLOR);
		initProperty(SIZE_KEY, new Dimension(8, 8));
	}

	@Override
	public Point getReferencePoint() {
		return BNAUtils.toPoint(getAnchorPoint());
	}

	@Override
	public void setReferencePoint(Point value) {
		setAnchorPoint(BNAUtils.toPoint2D(value));
	}

}
