package org.archstudio.swtutils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class GradientComposite extends Composite implements PaintListener {
	protected Composite parent;

	protected Color fg;
	protected Color bg;
	protected Font mainTextFont;

	public GradientComposite(Composite parent, int style) {
		super(parent, style);
		fg = parent.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		bg = parent.getDisplay().getSystemColor(SWT.COLOR_BLACK);
		this.addPaintListener(this);
	}

	@Override
	public void setForeground(Color fg) {
		this.fg = fg;
	}

	@Override
	public void setBackground(Color bg) {
		this.bg = bg;
	}

	@Override
	public void dispose() {
		this.removePaintListener(this);
		super.dispose();
	}

	@Override
	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		Composite c = (Composite) e.widget;
		gc.setForeground(fg);
		gc.setBackground(bg);
		Point size = c.getSize();
		gc.fillGradientRectangle(0, 0, size.x, size.y, false);

		if ((getStyle() & SWT.BORDER) != 0) {
			gc.setLineWidth(1);

			gc.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_GRAY));
			gc.drawLine(0, 0, 0, size.y - 1);
			gc.drawLine(0, 0, size.x - 1, 0);

			gc.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
			gc.drawLine(0, size.y - 1, size.x - 1, size.y - 1);
			gc.drawLine(size.x - 1, size.y - 1, size.x - 1, size.y - 1);
		}
	}
}
