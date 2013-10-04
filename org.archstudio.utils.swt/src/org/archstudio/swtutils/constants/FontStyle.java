package org.archstudio.swtutils.constants;

import java.awt.Font;

import org.eclipse.swt.SWT;

public enum FontStyle {
	NORMAL(Font.PLAIN, SWT.NORMAL), //
	BOLD(Font.BOLD, SWT.BOLD), //
	ITALIC(Font.ITALIC, SWT.ITALIC), //
	BOLD_ITALIC(Font.BOLD | Font.ITALIC, SWT.BOLD | SWT.ITALIC);

	private int awtStyle;
	private int swtStyle;

	private FontStyle(int awtStyle, int swtStyle) {
		this.awtStyle = awtStyle;
		this.swtStyle = swtStyle;
	}

	public int toSWT() {
		return swtStyle;
	}

	public static FontStyle fromSWT(int swtStyle) {
		swtStyle &= BOLD_ITALIC.swtStyle;
		for (FontStyle style : values()) {
			if (style.swtStyle == swtStyle) {
				return style;
			}
		}
		throw new IllegalArgumentException("Invaid font style: " + swtStyle);
	}

	public int toAWT() {
		return awtStyle;
	}

	public static FontStyle fromAWT(int awtStyle) {
		awtStyle &= BOLD_ITALIC.awtStyle;
		for (FontStyle style : values()) {
			if (style.awtStyle == awtStyle) {
				return style;
			}
		}
		throw new IllegalArgumentException("Invaid font style: " + awtStyle);
	}
}