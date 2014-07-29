package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum HorizontalAlignment {
	LEFT(-1), CENTER(0), RIGHT(1);

	private final int delta;

	private HorizontalAlignment(int delta) {
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}

	public int toSWT() {
		switch (this) {
		case LEFT:
			return SWT.LEFT;
		case CENTER:
			return SWT.CENTER;
		case RIGHT:
			return SWT.RIGHT;
		default:
			return SWT.CENTER;
		}
	}
}
