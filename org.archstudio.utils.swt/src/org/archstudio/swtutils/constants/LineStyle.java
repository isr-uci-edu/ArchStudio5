package org.archstudio.swtutils.constants;

import java.util.Arrays;

import org.eclipse.swt.SWT;

public enum LineStyle {
	// Note: bit patterns adhere to conditions in #toBitPattern()
	NONE(SWT.LINE_CUSTOM, 0x00000000), //
	SOLID(SWT.LINE_SOLID, 0xFFFFFFFF), //
	DASH(SWT.LINE_DASH, 0xF0F0F0F0), //
	DOT(SWT.LINE_DOT, 0xAAAAAAAA), //
	DASH_DOT(SWT.LINE_DASHDOT, 0xE4E4E4E4), //
	DASH_DOT_DOT(SWT.LINE_DASHDOTDOT, 0xF111F111);

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

	public static int[] toSWTDashes(int stipple) {
		// SWT custom styles are annoying, they:
		// (1) do not allow zero length dashes, thus always starting with an on bit
		// (2) must be an even length to avoid repeating the inverse pattern

		// This method treats the first bit as on, and last bit as off, to enforce this
		stipple |= 0x8000;
		stipple &= 0xFFFE;

		int[] dashes = new int[16];
		int dashIndex = 0;
		boolean lastBit = true;
		int bit = 0x8000;
		while (bit != 0) {
			boolean currentBit = (stipple & bit) != 0;
			if (lastBit != currentBit) {
				dashIndex++;
			}
			dashes[dashIndex]++;
			lastBit = currentBit;
			bit >>= 1;
		}
		return Arrays.copyOfRange(dashes, 0, dashIndex + 1);
	}
}