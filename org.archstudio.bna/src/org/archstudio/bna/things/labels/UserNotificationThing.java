package org.archstudio.bna.things.labels;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class UserNotificationThing extends UserNotificationThingBase {

	public UserNotificationThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setRawLife(5000);
		setRawColor(new RGB(128, 192, 255));
		setRawSecondaryColor(new RGB(128, 128, 192));
	}
}
