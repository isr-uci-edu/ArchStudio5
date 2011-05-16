package org.archstudio.bna.things.labels;

import java.text.BreakIterator;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;

public class TextLayoutCache {

	private static final boolean DEBUG = false;

	private static final boolean equalz(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	private Display display;

	public TextLayoutCache(Display display) {
		this.display = display;
		display.disposeExec(new Runnable() {

			public void run() {
				dispose();
			}
		});
	}

	private TextLayout textLayout = null;
	private Font textLayoutFont = null;
	private boolean textLayoutFits = true;
	private boolean textLayoutIsDirty = true;

	public void dispose() {
		if (Display.getCurrent() != display) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		if (textLayout != null && !textLayout.isDisposed()) {
			try {
				textLayout.dispose();
			}
			catch (Throwable t) {
			}
		}
		textLayout = null;

		if (textLayoutFont != null && !textLayoutFont.isDisposed()) {
			try {
				textLayoutFont.dispose();
			}
			catch (Throwable t) {
			}
		}
		textLayoutFont = null;
	}

	private void setFont(FontData textFontData) {
		if (textLayoutFont != null) {
			try {
				textLayoutFont.dispose();
			}
			catch (Throwable t) {
			}
			textLayoutFont = null;
		}
		textLayoutFont = new Font(display, textFontData);
		textLayout.setFont(textLayoutFont);
	}

	public TextLayout getTextLayout() {
		if (Display.getCurrent() != display) {
			SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
		}

		if (size.x < 2 || size.y < 2) {
			return null;
		}
		if (text == null || text.length() == 0 || font == null) {
			return null;
		}

		if (textLayout == null && !display.isDisposed()) {
			textLayout = new TextLayout(display);
			textLayoutIsDirty = true;
		}

		if (textLayout != null && textLayoutIsDirty) {
			textLayoutIsDirty = false;
			textLayout.setWidth(size.x);
			textLayout.setText(text);
			textLayout.setAlignment(alignment);
			textLayout.setSegments(new int[] { 0, text.length() });

			FontData textFontData = font.getFontData()[0];
			// int layoutHeight = textLayout.getBounds().height;

			int minFontHeight = 0;
			int maxFontHeight = increaseFontSize ? size.y : textFontData.getHeight();

			// shrink to fit in the area
			while (minFontHeight <= maxFontHeight) {
				textFontData.setHeight((minFontHeight + maxFontHeight) / 2);
				setFont(textFontData);
				int layoutHeight = textLayout.getBounds().height;

				if (layoutHeight <= size.y) {
					boolean validLineBreaks = true;
					for (int index : textLayout.getLineOffsets()) {
						if (!validOffsets.contains(index)) {
							validLineBreaks = false;
							break;
						}
					}
					if (validLineBreaks) {
						minFontHeight = textFontData.getHeight() + 1;
						continue;
					}
				}
				maxFontHeight = textFontData.getHeight() - 1;
			}

			int fontHeight = minFontHeight - 1;
			textLayoutFits = fontHeight > 0;
			if (textLayoutFits) {
				textFontData.setHeight(fontHeight);
				setFont(textFontData);
			}
			//			// the above result might return 3 for an actual correct value of 2.3
			//			// we check for this here and correct it if necessary
			//			if(layoutHeight > size.y){
			//				if(textFontData.getHeight() > 0){
			//					textFontData.setHeight(textFontData.getHeight() - 1);
			//					setFont(textFontData);
			//					layoutHeight = 0;
			//				}
			//			}

			if (DEBUG) {
				String s = textLayout.getText();
				int[] offsets = textLayout.getLineOffsets();
				System.err.println("String: " + s);
				if (textLayoutFits) {
					System.err.println(" - Size: " + fontHeight);
					for (int i = 0; i < offsets.length - 1; i++) {
						System.err.println(" - " + i + ": \"" + s.substring(offsets[i], offsets[i + 1]) + "\"");
					}
				}
				else {
					System.err.println(" - Does not fit");
				}
			}
		}

		return textLayoutFits ? textLayout : null;
	}

	boolean increaseFontSize = false;
	String text = "";
	Set<Integer> validOffsets = new HashSet<Integer>();
	Font font = null;
	Point size = new Point(0, 0);
	int alignment = SWT.LEFT;

	public void setIncreaseFontSize(boolean increaseFontSize) {
		if (this.increaseFontSize != increaseFontSize) {
			textLayoutIsDirty = true;
			this.increaseFontSize = increaseFontSize;
		}
	}

	public void setText(String text) {
		if (!equalz(this.text, text)) {
			textLayoutIsDirty = true;
			this.text = text;
			validOffsets.clear();
			BreakIterator wi = BreakIterator.getWordInstance();
			wi.setText(text);
			int index;
			validOffsets.add(0);
			while ((index = wi.next()) != BreakIterator.DONE) {
				validOffsets.add(index);
			}
		}
	}

	public void setFont(Font font) {
		if (!equalz(this.font, font)) {
			textLayoutIsDirty = true;
			this.font = font;
		}
	}

	public void setSize(Point size) {
		if (!equalz(this.size, size)) {
			textLayoutIsDirty = true;
			this.size.x = size.x;
			this.size.y = size.y;
		}
	}

	public void setAlignment(int alignment) {
		if (this.alignment != alignment) {
			textLayoutIsDirty = true;
			this.alignment = alignment;
		}
	}

	public boolean isIncreaseFontSize() {
		return increaseFontSize;
	}

	public String getText() {
		return text;
	}

	public Font getFont() {
		return font;
	}

	public Point getSize() {
		return size;
	}

	public int getAlignment() {
		return alignment;
	}
}
