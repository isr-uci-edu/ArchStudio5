package org.archstudio.swtutils.constants;

import org.eclipse.swt.SWT;

public enum LineStyle {
	SOLID(SWT.LINE_SOLID, 0xffffffff), //
	DASH(SWT.LINE_DASH, 0x0f0f0f0f), //
	DOT(SWT.LINE_DOT, 0x55555555), //
	DASH_DOT(SWT.LINE_DASHDOT, 0x27272727), //
	DASH_DOT_DOT(SWT.LINE_DASHDOTDOT, 0x111f111f);

	private final int swtStyle;
	private final int bitPattern;

	private LineStyle(int swtStyle, int bitPattern) {
		this.swtStyle = swtStyle;
		this.bitPattern = bitPattern;
	}

	public int toSwtStyle() {
		return swtStyle;
	}

	public int toBitPattern() {
		return bitPattern;
	}
}