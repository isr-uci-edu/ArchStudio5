package org.archstudio.bna.logics.util;

import java.io.File;

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
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.BNARenderingSettings;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.DefaultBNAView;
import org.archstudio.bna.DefaultCoordinateMapper;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;

public class ExportBitmapLogic extends AbstractThingLogic implements IBNAMenuListener {
	protected ModelBoundsTrackingLogic mbtl = null;

	public ExportBitmapLogic(ModelBoundsTrackingLogic mbtl) {
		this.mbtl = mbtl;
	}

	public void fillMenu(IBNAView view, IMenuManager m, int localX, int localY, IThing t, int worldX, int worldY) {
		if (t == null) {
			final IBNAView fview = view;
			IAction saveAsPNGAction = new Action("Save as PNG...") {
				public void run() {
					saveAsBitmap(fview, "png", "Portable Network Graphics (*.png)", SWT.IMAGE_PNG);
				}
			};
			m.add(saveAsPNGAction);

			IAction saveAsJPEGAction = new Action("Save as JPEG...") {
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

		Image image = createImage(view);
		try {
			ImageLoader loader = new ImageLoader();
			loader.data = new ImageData[] { image.getImageData() };
			loader.save(selected, swtImageType);
		}
		finally {
			if (image != null) {
				image.dispose();
			}
		}
	}

	protected Image createImage(IBNAView view) {
		mbtl.recalculateNow();
		Rectangle bounds = mbtl.getModelBounds();

		BNAComposite bnaComposite = (BNAComposite) BNAUtils.getParentComposite(view);

		DefaultCoordinateMapper cm = new DefaultCoordinateMapper();
		cm.repositionAbsolute(bounds.x, bounds.y);
		cm.rescaleAbsolute(1.0);

		DefaultBNAView newView = new DefaultBNAView(view, view.getWorld(), cm);

		Image image = new Image(BNAUtils.getParentComposite(view).getDisplay(), bounds.width + 10, bounds.height + 10);
		GC gc = new GC(image);

		gc.setAntialias(BNARenderingSettings.getAntialiasGraphics(bnaComposite) ? SWT.ON : SWT.OFF);
		gc.setTextAntialias(BNARenderingSettings.getAntialiasText(bnaComposite) ? SWT.ON : SWT.OFF);

		for (IThing thing : newView.getWorld().getBNAModel().getAllThings()) {
			IThingPeer peer = newView.getPeer(thing);
			peer.draw(newView, gc);
		}
		gc.dispose();
		return image;
	}
}
