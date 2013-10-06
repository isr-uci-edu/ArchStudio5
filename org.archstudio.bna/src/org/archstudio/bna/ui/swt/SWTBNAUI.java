package org.archstudio.bna.ui.swt;

import java.awt.image.BufferedImage;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ui.AbstractSWTUI;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class SWTBNAUI extends AbstractSWTUI implements PaintListener {

	Composite parent;
	Canvas canvas;
	SWTResources resources;

	public SWTBNAUI(IBNAView view) {
		super(view);
	}

	@Override
	public void init(Composite parent, int style) {
		this.parent = parent;

		canvas = new Canvas(parent, style | SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED);
		super.init(canvas, true);
		view.setBNAUI(this);

		canvas.addPaintListener(this);
	}

	@Override
	public void dispose() {
		if (!canvas.isDisposed()) {
			canvas.dispose();
		}
		if (resources != null) {
			resources.dispose();
		}
		super.dispose();
	}

	@Override
	public Composite getComposite() {
		return canvas;
	}

	@Override
	public void forceFocus() {
		canvas.forceFocus();
	}

	private volatile boolean needsRepaint = false;

	@Override
	public void paint() {
		if (!needsRepaint) {
			needsRepaint = true;
			SWTWidgetUtils.async(canvas, new Runnable() {
				@Override
				public void run() {
					needsRepaint = false;
					canvas.redraw();
				}
			});
		}
	}

	@Override
	public void paintControl(PaintEvent e) {
		if (resources == null) {
			resources = new SWTResources();
		}
		resources.setGc(e.gc);
		loadPreferences(resources, parent);
		resources.renderTopLevelThings(view, new Rectangle(e.x, e.y, e.width, e.height));
	}

	@Override
	public BufferedImage render(final Rectangle localBounds) {

		final BufferedImage[] image = new BufferedImage[1];
		SWTWidgetUtils.sync(canvas, new Runnable() {
			@Override
			public void run() {
				image[0] = resources.renderToImage(view, localBounds);
			}
		});

		return image[0];
	}

}
