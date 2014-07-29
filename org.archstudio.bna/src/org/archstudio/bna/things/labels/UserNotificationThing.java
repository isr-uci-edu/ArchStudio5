package org.archstudio.bna.things.labels;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class UserNotificationThing extends UserNotificationThingBase {

	/* package */static final int TIME_TO_LIVE = 3000;

	public UserNotificationThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setRawLife(TIME_TO_LIVE);
		setRawColor(new RGB(255, 255, 128));
		setRawSecondaryColor(new RGB(192, 192, 128));
	}
}
