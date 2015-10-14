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
		setLife(TIME_TO_LIVE);
		setColor(new RGB(255, 255, 128));
		setSecondaryColor(new RGB(192, 192, 128));
	}
}
