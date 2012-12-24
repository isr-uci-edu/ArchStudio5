package org.archstudio.swtutils;

import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class Banner extends Canvas implements PaintListener {
	protected Composite parent;
	protected String mainText;
	protected String secondaryText;
	protected Button button;

	protected Image icon;
	protected Point iconSize = new Point(0, 0);
	protected int iconGap = 0;

	protected Color textColor;
	protected Color fg;
	protected Color bg;
	protected Font mainTextFont;
	protected Font secondaryTextFont;

	protected Rectangle mainTextBounds;
	protected Rectangle secondaryTextBounds;

	public Banner(Composite parent, Image icon, String mainText, String secondaryText, Color fg, Color bg) {
		super(parent, SWT.NONE);

		this.parent = parent;
		this.icon = icon;
		if (icon != null) {
			iconSize = new Point(icon.getBounds().width, icon.getBounds().height);
			iconGap = 5;
		}
		this.mainText = mainText;
		this.secondaryText = secondaryText;
		this.textColor = parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		this.fg = fg;
		this.bg = bg;

		FontRegistry fr = JFaceResources.getFontRegistry();
		mainTextFont = fr.getBold(JFaceResources.HEADER_FONT);
		secondaryTextFont = fr.getItalic(JFaceResources.DEFAULT_FONT);

		TextLayout mainTextLayout = new TextLayout(parent.getDisplay());
		mainTextLayout.setFont(mainTextFont);
		mainTextLayout.setText(mainText);
		mainTextBounds = mainTextLayout.getBounds();

		TextLayout secondaryTextLayout = new TextLayout(parent.getDisplay());
		secondaryTextLayout.setFont(secondaryTextFont);
		secondaryTextLayout.setText(secondaryText);
		secondaryTextBounds = secondaryTextLayout.getBounds();

		this.addPaintListener(this);
	}

	public void dispose() {
		this.removePaintListener(this);
		super.dispose();
	}

	public Point _computeSize(int wHint, int hHint) {
		int width = 50;
		if (wHint != SWT.DEFAULT) {
			width = wHint;
		}
		else {
			width = iconSize.x + iconGap + mainTextBounds.width + secondaryTextBounds.width + 40;
		}

		int height = Math.max(iconSize.y, Math.max(mainTextBounds.height, secondaryTextBounds.height)) + 10;
		return new Point(width, height);
	}

	public Point computeSize(int wHint, int hHint) {
		return _computeSize(wHint, hHint);
	}

	public Point computeSize(int wHint, int hHint, boolean changed) {
		return _computeSize(wHint, hHint);
	}

	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Canvas c = (Canvas) e.widget;
		gc.setForeground(fg);
		gc.setBackground(bg);
		Point size = c.getSize();
		gc.fillGradientRectangle(0, 0, size.x, size.y, false);

		gc.setLineWidth(1);

		gc.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
		gc.drawLine(0, 0, 0, size.y - 1);
		gc.drawLine(0, 0, size.x - 1, 0);

		gc.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
		gc.drawLine(0, size.y - 1, size.x - 1, size.y - 1);
		gc.drawLine(size.x - 1, size.y - 1, size.x - 1, size.y - 1);

		if (icon != null) {
			gc.drawImage(icon, 5, (size.y - iconSize.y) / 2);
		}
		int mainY = (size.y - mainTextBounds.height) / 2;
		int secondaryY = (size.y - secondaryTextBounds.height) / 2;

		gc.setForeground(textColor);
		gc.setFont(mainTextFont);
		gc.drawString(mainText, 5 + iconSize.x + iconGap, mainY, true);

		boolean shouldDrawSecondary = mainTextBounds.width + secondaryTextBounds.width + 15 <= size.x;
		if (shouldDrawSecondary) {
			gc.setFont(secondaryTextFont);
			gc.drawString(secondaryText, size.x - secondaryTextBounds.width - 5, secondaryY, true);
		}
	}
}
