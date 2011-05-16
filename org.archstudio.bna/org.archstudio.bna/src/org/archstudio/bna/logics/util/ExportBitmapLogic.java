package org.archstudio.bna.logics.util;

import java.io.File;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.utils.BNAComposite;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchActionConstants;

public class ExportBitmapLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected static final int IMAGE_PADDING = 5;

	protected ModelBoundsTrackingLogic mbtl = null;

	public ExportBitmapLogic(ModelBoundsTrackingLogic mbtl) {
		this.mbtl = mbtl;
	}

	public void fillMenu(IBNAView view, Point localPoint, Point worldPoint, Iterable<IThing> things, IMenuManager m) {
		if (things == null) {
			final IBNAView fview = view;
			IAction saveAsPNGAction = new Action("Save as PNG...") {

				@Override
				public void run() {
					saveAsBitmap(fview, "png", "Portable Network Graphics (*.png)", SWT.IMAGE_PNG);
				}
			};
			m.add(saveAsPNGAction);

			IAction saveAsJPEGAction = new Action("Save as JPEG...") {

				@Override
				public void run() {
					saveAsBitmap(fview, "jpg", "Joint Photographic Experts Group (*.jpg)", SWT.IMAGE_JPEG);
				}
			};
			m.add(saveAsJPEGAction);

			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	protected void saveAsBitmap(IBNAView view, String extension, String bitmapName, int swtImageType) {
		Shell s = BNAUtils.getParentComposite(view).getShell();
		FileDialog fd = new FileDialog(s, SWT.SAVE);
		fd.setText("Save");
		String[] filterExt = { "*." + extension };
		String[] filterNames = { bitmapName };
		fd.setFilterExtensions(filterExt);
		fd.setFilterNames(filterNames);
		String selected = fd.open();

		if (selected == null) {
			return;
		}
		if (!selected.toLowerCase().trim().endsWith("." + extension)) {
			selected += "." + extension;
		}

		File f = new File(selected);
		if (f.exists()) {
			boolean confirm = MessageDialog.openConfirm(s, "Confirm Overwrite", "Overwrite existing file?");
			if (!confirm) {
				saveAsBitmap(view, extension, bitmapName, swtImageType);
				return;
			}
		}

		ImageData imageData = createImage(view);
		ImageLoader loader = new ImageLoader();
		loader.data = new ImageData[] { imageData };
		loader.save(selected, swtImageType);
	}

	protected ImageData createImage(IBNAView view) {
		ImageData imageData = null;
		Rectangle bounds = mbtl.getModelBounds();
		BNAComposite bnaComposite = (BNAComposite) BNAUtils.getParentComposite(view);
		Display d = bnaComposite.getDisplay();
		ResourceUtils res = ResourceUtils.resourceUtilsFor(d);

		DefaultCoordinateMapper cm = new DefaultCoordinateMapper();
		cm.repositionAbsolute(bounds.x - IMAGE_PADDING, bounds.y - IMAGE_PADDING);
		cm.rescaleAbsolute(1.0);

		DefaultBNAView newView = new DefaultBNAView(view, view.getBNAWorld(), cm);

		Image image = null;
		GC gc = null;
		Graphics g = null;

		try {
			image = new Image(d, bounds.width + 2 * IMAGE_PADDING, bounds.height + 2 * IMAGE_PADDING);
			gc = new GC(image);
			g = new SWTGraphics(gc);
			Rectangle clip = BNAUtils.toRectangle(image.getBounds());

			gc.setAntialias(BNARenderingSettings.getAntialiasGraphics(bnaComposite) ? SWT.ON : SWT.OFF);
			gc.setTextAntialias(BNARenderingSettings.getAntialiasText(bnaComposite) ? SWT.ON : SWT.OFF);

			BNAUtils.renderWorld(newView, g, clip, res);

			imageData = image.getImageData();
		}
		finally {
			if (g != null) {
				g.dispose();
				g = null;
			}
			if (gc != null) {
				gc.dispose();
				gc = null;
			}
			if (image != null) {
				image.dispose();
				image = null;
			}
		}
		return imageData;
	}
}
