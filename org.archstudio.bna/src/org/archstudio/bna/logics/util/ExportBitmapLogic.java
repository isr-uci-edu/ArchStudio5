package org.archstudio.bna.logics.util;

import java.io.File;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.utils.BNARenderingSettings;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

	@Override
	public void fillMenu(IBNAView view, Iterable<IThing> things, ICoordinate location, IMenuManager menu) {
		if (things.iterator().hasNext()) {
			return;
		}

		final IBNAView fview = view;
		IAction saveAsPNGAction = new Action("Save as PNG...") {

			@Override
			public void run() {
				saveAsBitmap(fview, "png", "Portable Network Graphics (*.png)", SWT.IMAGE_PNG);
			}
		};
		menu.add(saveAsPNGAction);

		IAction saveAsJPEGAction = new Action("Save as JPEG...") {

			@Override
			public void run() {
				saveAsBitmap(fview, "jpg", "Joint Photographic Experts Group (*.jpg)", SWT.IMAGE_JPEG);
			}
		};
		menu.add(saveAsJPEGAction);

		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	protected void saveAsBitmap(IBNAView view, String extension, String bitmapName, int swtImageType) {
		Control c = view.getComposite();
		Shell s = c.getShell();
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
		Composite c = view.getComposite();
		Display d = c.getDisplay();
		Resources r = new Resources(c);

		IMutableCoordinateMapper icm = new LinearCoordinateMapper();
		icm.setLocalScaleAndAlign(1, new Point(IMAGE_PADDING, IMAGE_PADDING), bounds.getTopLeft());

		DefaultBNAView innerView = new DefaultBNAView(view, view.getBNAWorld(), icm);
		IBNAWorld innerWorld = view.getBNAWorld();

		Image image = null;
		GC gc = null;
		Graphics g = null;

		try {
			image = new Image(d, bounds.width + 2 * IMAGE_PADDING, bounds.height + 2 * IMAGE_PADDING);
			gc = new GC(image);
			g = new SWTGraphics(gc);

			gc.setAntialias(BNARenderingSettings.getAntialiasGraphics(c) ? SWT.ON : SWT.OFF);
			gc.setTextAntialias(BNARenderingSettings.getAntialiasText(c) ? SWT.ON : SWT.OFF);

			ModelBoundsTrackingLogic mbtl = innerWorld.getThingLogicManager().addThingLogic(
					ModelBoundsTrackingLogic.class);
			Rectangle modelBounds = mbtl.getModelBounds();
			if (modelBounds != null) {

				g.pushState();
				try {
					for (IThing thing : innerView.getBNAWorld().getBNAModel().getAllThings()) {
						IThingPeer<?> peer = innerView.getThingPeer(thing);
						g.restoreState();
						peer.draw(innerView, icm, g, r);
					}
				}
				finally {
					g.popState();
				}
			}

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
