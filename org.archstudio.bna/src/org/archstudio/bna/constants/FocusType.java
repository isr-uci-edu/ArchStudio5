package org.archstudio.bna.constants;

import java.awt.event.FocusEvent;

import org.eclipse.swt.SWT;

public enum FocusType {
	GAINED(java.awt.event.FocusEvent.FOCUS_GAINED, SWT.FocusIn), //
	LOST(java.awt.event.FocusEvent.FOCUS_LOST, SWT.FocusOut);

	private final int awtId;
	private final int swtType;

	private FocusType(int awtId, int swtType) {
		this.awtId = awtId;
		this.swtType = swtType;
	}

	public int getAwtId() {
		return awtId;
	}

	public int getSwtType() {
		return swtType;
	}

	public static FocusType fromAwt(FocusEvent e) {
		for (FocusType t : values()) {
			if (e.getID() == t.awtId) {
				return t;
			}
		}
		throw new IllegalArgumentException("" + e.getID());
	}
}