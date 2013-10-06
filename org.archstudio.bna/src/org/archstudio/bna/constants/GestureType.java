package org.archstudio.bna.constants;

import org.eclipse.swt.SWT;

public enum GestureType {
	BEGIN(SWT.GESTURE_BEGIN), //
	END(SWT.GESTURE_END), //
	MAGNIFY(SWT.GESTURE_MAGNIFY), //
	PAN(SWT.GESTURE_PAN), //
	ROTATE(SWT.GESTURE_ROTATE), //
	SWIPE(SWT.GESTURE_SWIPE);

	private final int swtType;

	private GestureType(int swtType) {
		this.swtType = swtType;
	}

	public static GestureType fromSwtEvent(int eventType) {
		for (GestureType gestureType : values()) {
			if (gestureType.swtType == eventType) {
				return gestureType;
			}
		}
		throw new IllegalArgumentException("" + eventType);
	}

}