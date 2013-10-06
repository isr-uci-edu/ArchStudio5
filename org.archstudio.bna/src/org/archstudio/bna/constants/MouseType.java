package org.archstudio.bna.constants;

import java.awt.event.MouseEvent;

import org.eclipse.swt.SWT;

public enum MouseType {
	UP(java.awt.event.MouseEvent.MOUSE_RELEASED, SWT.MouseUp), //
	DOWN(java.awt.event.MouseEvent.MOUSE_PRESSED, SWT.MouseDown), // 
	CLICK(java.awt.event.MouseEvent.MOUSE_CLICKED, -1), //
	MOVE(java.awt.event.MouseEvent.MOUSE_MOVED, SWT.MouseMove), // 
	ENTER(java.awt.event.MouseEvent.MOUSE_ENTERED, SWT.MouseEnter), //
	EXIT(java.awt.event.MouseEvent.MOUSE_EXITED, SWT.MouseExit), //
	VERTICAL_WHEEL(java.awt.event.MouseEvent.MOUSE_WHEEL, SWT.MouseVerticalWheel), //
	HORIZONTAL_WHEEL(-1, SWT.MouseHorizontalWheel); //

	private final int awtId;
	private final int swtType;

	private MouseType(int awtId, int swtType) {
		this.awtId = awtId;
		this.swtType = swtType;
	}

	public int getAwtId() {
		return awtId;
	}

	public int getSwtType() {
		return swtType;
	}

	public static MouseType fromAwt(MouseEvent e) {
		if (e.getID() == MouseEvent.MOUSE_DRAGGED || e.getID() == MouseEvent.MOUSE_MOVED) {
			return MOVE;
		}
		for (MouseType t : values()) {
			if (e.getID() == t.awtId) {
				return t;
			}
		}
		throw new IllegalArgumentException("" + e.getID());
	}
}