package org.archstudio.bna.ui.jogl;

import java.awt.image.BufferedImage;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.GLRunnable;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ui.utils.AbstractSWTUI;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

import com.jogamp.opengl.swt.GLCanvas;

public class JOGLBNAUI extends AbstractSWTUI {

	class RepaintThread {

		private final Thread thread;
		private boolean needsRepaint = false;
		private boolean disposed = false;

		public RepaintThread() {
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!disposed) {
						synchronized (RepaintThread.this) {
							try {
								if (!needsRepaint) {
									RepaintThread.this.wait();
								}
							}
							catch (InterruptedException e) {
							}
							needsRepaint = false;
						}
						try {
							glCanvas.display();
							// rate limit repaint
							Thread.sleep(1000 / 30);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.setDaemon(true);
			thread.start();
		}

		public void paint() {
			needsRepaint = true;
			synchronized (this) {
				notifyAll();
			}
		}

		public void dispose() {
			disposed = true;
			synchronized (this) {
				notifyAll();
			}
		}

	}

	class BNAGLEventListener implements GLEventListener {

		public BNAGLEventListener() {
		}

		@Override
		public void init(GLAutoDrawable drawable) {
			joglThreadResources = new JOGLResources(drawable.getGL().getGL2ES2());
		}

		@Override
		public void dispose(GLAutoDrawable drawable) {
			joglThreadResources.dispose();
		}

		@Override
		public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		}

		@Override
		public void display(GLAutoDrawable drawable) {
			loadPreferences(joglThreadResources, parent);
			joglThreadResources.renderInit();
			joglThreadResources.renderReshape(new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight()));
			joglThreadResources.renderTopLevelThings(view,
					new Rectangle(0, 0, drawable.getWidth(), drawable.getHeight()));
		}
	}

	JOGLResources joglThreadResources;
	Composite parent;
	GLCanvas glCanvas;
	RepaintThread repaintThread = new RepaintThread();
	PaintListener paintListener;

	public JOGLBNAUI(IBNAView view) {
		super(view);
	}

	@Override
	public void dispose() {
		super.dispose();
		repaintThread.dispose();
		glCanvas.removePaintListener(paintListener);
		if (!glCanvas.isDisposed()) {
			glCanvas.dispose();
		}
	}

	@Override
	public void init(Composite parent, int style) {
		this.parent = parent;

		GLCapabilities caps;
		caps = new GLCapabilities(GLProfile.getGL2ES2());
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);
		caps.setSampleBuffers(true);
		caps.setNumSamples(4);

		glCanvas = new GLCanvas(parent, style, caps, null);
		glCanvas.addPaintListener(paintListener = new PaintListener() {

			/*
			 * This clears the display buffer so that old content does not flash up when, for instance, changing BNAUIs.
			 */

			boolean completedFirstPaint = false;

			@Override
			public void paintControl(PaintEvent e) {
				if (!completedFirstPaint) {
					completedFirstPaint = true;
					e.gc.setBackground(e.gc.getDevice().getSystemColor(SWT.COLOR_WHITE));
					e.gc.fillRectangle(glCanvas.getClientArea());
					glCanvas.display();
				}
			}
		});
		super.init(glCanvas, true);
		view.setBNAUI(this);

		glCanvas.addGLEventListener(new BNAGLEventListener());
	}

	@Override
	public Composite getComposite() {
		return glCanvas;
	}

	@Override
	public void forceFocus() {
		glCanvas.forceFocus();
	}

	@Override
	public void paint() {
		repaintThread.paint();
	}

	@Override
	public BufferedImage render(final Rectangle localBounds) {

		final BufferedImage[] image = new BufferedImage[1];
		glCanvas.invoke(true, new GLRunnable() {
			@Override
			public boolean run(GLAutoDrawable drawable) {
				loadPreferences(joglThreadResources, parent);
				image[0] = joglThreadResources.renderToImage(view, localBounds);
				return true;
			}
		});

		return image[0];
	}
}
