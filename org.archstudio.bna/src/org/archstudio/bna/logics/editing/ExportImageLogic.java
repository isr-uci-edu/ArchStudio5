package org.archstudio.bna.logics.editing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLPbuffer;
import javax.media.opengl.GLProfile;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.LinearCoordinateMapper;
import org.archstudio.bna.ObscuredGL2;
import org.archstudio.bna.Resources;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

@SuppressWarnings("deprecation")
public class ExportImageLogic extends AbstractThingLogic implements IBNAMenuListener {

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager menu) {
		if (things.size() == 0) {
			menu.add(new Action("Export Image...") {

				@Override
				public void run() {
					saveAs(view);
				}
			});
		}
	}

	public static void saveAs(final IBNAView view) {
		ModelBoundsTrackingLogic mbtl = view.getBNAWorld().getThingLogicManager()
				.addThingLogic(ModelBoundsTrackingLogic.class);
		FileDialog fd = new FileDialog(view.getComposite().getShell(), SWT.SAVE);
		fd.setFilterExtensions(new String[] { "*.png" });
		fd.setOverwrite(true);
		fd.open();
		if (fd.getFileName() != null) {
			GLContext context = null;
			GLPbuffer buffer = null;
			Resources resources = null;
			try {
				File f = new File(fd.getFilterPath() + File.separator + fd.getFileName());

				Rectangle bounds = mbtl.getModelBounds();
				int padding = 5;
				bounds.x -= padding;
				bounds.y -= padding;
				bounds.width += padding * 2;
				bounds.height += padding * 2;

				GLProfile glprofile = GLProfile.get(GLProfile.GL2);
				GLCapabilities glCap = new GLCapabilities(glprofile);
				glCap.setDoubleBuffered(false);
				glCap.setSampleBuffers(true);
				glCap.setNumSamples(4);
				buffer = GLDrawableFactory.getFactory(glprofile).createGLPbuffer(GLProfile.getDefaultDevice(), glCap,
						null, bounds.width, bounds.height, null);
				context = buffer.createContext(null);
				context.makeCurrent();
				ObscuredGL2 gl = new ObscuredGL2((GL2) context.getGL());
				resources = new Resources(view.getComposite(), gl);
				final LinearCoordinateMapper cm = new LinearCoordinateMapper();
				cm.align(new Point(0, 0), new Point(bounds.x, bounds.y));

				BufferedImage image = BNAUtils.renderToImage(new IBNAView() {

					@Override
					public IBNAView getParentView() {
						return null;
					}

					@Override
					public IBNAWorld getBNAWorld() {
						return view.getBNAWorld();
					}

					@Override
					public ICoordinateMapper getCoordinateMapper() {
						return cm;
					}

					@Override
					public List<IThing> getThingsAt(ICoordinate location) {
						return view.getThingsAt(location);
					}

					@Override
					public <T extends IThing> IThingPeer<T> getThingPeer(T t) {
						return view.getThingPeer(t);
					}

					@Override
					public void setComposite(Composite composite) {
						throw new UnsupportedOperationException();
					}

					@Override
					public Composite getComposite() {
						return view.getComposite();
					}

					@Override
					public void dispose() {
					}

				}, gl, new Rectangle(0, 0, bounds.width, bounds.height), resources, true, true);

				ImageIO.write(image, fd.getFilterExtensions()[fd.getFilterIndex()].substring(2), f);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (resources != null) {
					resources.dispose();
				}
				if (context != null) {
					context.release();
					context.destroy();
				}
				if (buffer != null) {
					buffer.destroy();
				}
			}
		}
	}
}
