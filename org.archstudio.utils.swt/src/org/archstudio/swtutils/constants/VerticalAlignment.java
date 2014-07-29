package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum VerticalAlignment {
	TOP(-1), MIDDLE(0), BOTTOM(1);

	private final int delta;

	private VerticalAlignment(int delta) {
		this.delta = delta;
	}

	public int getDelta() {
		return delta;
	}

	public int toSWT() {
		switch (this) {
		case TOP:
			return SWT.TOP;
		case MIDDLE:
			return SWT.CENTER;
		case BOTTOM:
			return SWT.BOTTOM;
		default:
			return SWT.CENTER;
		}
	}

}
