package org.archstudio.bna.logics.editing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.peers.IHasLocalBounds;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.FileDialog;

public class ExportImageLogic extends AbstractThingLogic implements IBNAMenuListener {

	public ExportImageLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager menu) {
		BNAUtils.checkLock();

		if (things.size() == 0) {
			menu.add(new Action("Export Image...") {

				@Override
				public void run() {
					FileDialog fd = new FileDialog(view.getBNAUI().getComposite().getShell(), SWT.SAVE);
					fd.setFilterExtensions(new String[] { "*.png" });
					fd.setOverwrite(true);
					fd.open();

					if (fd.getFileName() == null) {
						return;
					}

					final File f = new File(fd.getFilterPath() + File.separator + fd.getFileName());
					final String format = fd.getFilterExtensions()[fd.getFilterIndex()].substring(2);

					try (Finally lock = BNAUtils.lock()) {
						ModelBoundsTrackingLogic boundsLogic = view.getBNAWorld().getThingLogicManager()
								.addThingLogic(ModelBoundsTrackingLogic.class);

						ICoordinateMapper cm = view.getCoordinateMapper();
						Rectangle wbb = boundsLogic.getModelBounds();
						Rectangle lbb = cm.worldToLocal(wbb);
						lbb.width++;
						lbb.height++;

						for (IThing t : view.getBNAWorld().getBNAModel().getAllThings()) {
							IThingPeer<?> peer = view.getThingPeer(t);
							if (peer instanceof IHasLocalBounds) {
								Rectangle peerLBB = ((IHasLocalBounds) peer).getLocalBounds();
								if (peerLBB != null) {
									lbb = lbb.union(peerLBB);
								}
							}
						}

						BufferedImage image = view.getBNAUI().render(lbb);
						ImageIO.write(image, format, f);
					}
					catch (Exception e) {
						e.printStackTrace();
						MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error", "An exception ("
								+ e.getClass().getName()
								+ ") has occurred. Please see the platform log file for details.");
					}
				}
			});
		}
	}
}
