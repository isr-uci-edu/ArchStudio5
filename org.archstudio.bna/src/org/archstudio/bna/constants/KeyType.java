package org.archstudio.bna.constants;

import java.awt.event.KeyEvent;

import org.eclipse.swt.SWT;

public enum KeyType {
	PRESSED(java.awt.event.KeyEvent.KEY_PRESSED, SWT.KeyDown), //
	RELEASED(java.awt.event.KeyEvent.KEY_RELEASED, SWT.KeyUp);

	private final int awtId;
	private final int swtType;

	private KeyType(int awtId, int swtType) {
		this.awtId = awtId;
		this.swtType = swtType;
	}

	public int getAwtId() {
		return awtId;
	}

	public int getSwtType() {
		return swtType;
	}

	public static KeyType fromAwt(KeyEvent e) {
		for (KeyType t : values()) {
			if (e.getID() == t.awtId) {
				return t;
			}
		}
		throw new IllegalArgumentException("" + e.getID());
	}
}