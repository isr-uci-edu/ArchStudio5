package org.archstudio.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.media.opengl.DebugGL2;
import javax.media.opengl.GL2;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;

import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

public class BNACanvas extends GLCanvas implements IBNAModelListener, PaintListener {

	protected static final boolean DEBUG = false;

	protected final IBNAView bnaView;
	protected final ScrollBar hBar = getHorizontalBar();
	protected final ScrollBar vBar = getVerticalBar();
	protected final BNASWTEventHandler eventHandler;
	protected final GLContext context;
	protected final ObscuredGL2 gl;

	public BNACanvas(Composite parent, int style, IBNAWorld bnaWorld) {
		this(parent, style, new DefaultBNAView(null, bnaWorld, new LinearCoordinateMapper()));
	}

	private static final GLData getInitialGLData() {
		GLData glData = new GLData();
		glData.doubleBuffer = true;
		return glData;
	}

	public BNACanvas(Composite parent, int style, IBNAView bnaView) {
		super(parent, style | SWT.NO_BACKGROUND, getInitialGLData());
		checkNotNull(bnaView);
		checkNotNull(bnaView.getBNAWorld());
		checkNotNull(bnaView.getBNAWorld().getBNAModel());
		this.bnaView = bnaView;
		bnaView.setComposite(this);
		this.eventHandler = new BNASWTEventHandler(this, bnaView);

		setCurrent();
		GLProfile glprofile = GLProfile.get(GLProfile.GL2);
		this.context = GLDrawableFactory.getFactory(glprofile).createExternalGLContext();
		this.gl = new ObscuredGL2(DEBUG ? new DebugGL2((GL2) context.getGL()) : (GL2) context.getGL());

		this.addControlListener(new ControlAdapter() {

			@Override
			public void controlResized(ControlEvent e) {
				updateScrollBars();
			}
		});
		getCoordinateMapper().addCoordinateMapperListener(new ICoordinateMapperListener() {

			@Override
			public void coordinateMappingsChanged(final CoordinateMapperEvent evt) {
				if (Display.getCurrent() == null) {
					SWT.error(SWT.ERROR_THREAD_INVALID_ACCESS);
				}
				updateScrollBars();
			}
		});
		if (hBar != null) {
			hBar.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					updateCM();
				}
			});
		}
		if (vBar != null) {
			vBar.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					updateCM();
				}
			});
		}
		updateScrollBars();
		updateCM();

		// prevent the mouse wheel from scrolling the canvas
		addListener(SWT.MouseWheel, new Listener() {

			@Override
			public void handleEvent(Event event) {
				event.doit = false;
			}
		});

		// select canvas on click
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				BNACanvas.this.forceFocus();
			}
		});

		// handle events
		getBNAView().getBNAWorld().getBNAModel().addBNAModelListener(this);
		addPaintListener(this);
	}

	@Override
	public void dispose() {
		removePaintListener(this);
		getBNAView().getBNAWorld().getBNAModel().removeBNAModelListener(this);
		bnaView.dispose();
		eventHandler.dispose();
		context.destroy();
		super.dispose();
	}

	boolean isUpdatingScrollBars = false;
	boolean isUpdatingCM = false;

	protected void updateScrollBars() {
		if (isUpdatingCM) {
			return;
		}
		isUpdatingScrollBars = true;
		try {
			IMutableCoordinateMapper mcm = getCoordinateMapper();
			updateCanvas(mcm.getLocalScale(), mcm.getLocalOrigin());
			Rectangle client = getClientArea();
			Rectangle localBounds = mcm.getLocalBounds();
			Point localOrigin = mcm.getLocalOrigin();
			if (hBar != null) {
				updateScrollBar(hBar, localOrigin.x - localBounds.x, client.width, localBounds.width);
			}
			if (vBar != null) {
				updateScrollBar(vBar, localOrigin.y - localBounds.y, client.height, localBounds.height);
			}
		}
		finally {
			isUpdatingScrollBars = false;
		}
	}

	private void updateScrollBar(ScrollBar bar, int selection, int thumb, int total) {
		assert isUpdatingScrollBars;

		// ScrollBar silently fails when certain constraints are violated
		thumb = Math.min(thumb, total);
		bar.setValues(Math.max(0, selection), 0, Math.max(1, total - thumb), Math.max(1, thumb),
				Math.max(1, thumb / 10), Math.max(1, thumb / 3));
	}

	protected void updateCM() {
		if (!isUpdatingScrollBars) {
			isUpdatingCM = true;
			try {
				IMutableCoordinateMapper mcm = getCoordinateMapper();
				Rectangle localBounds = mcm.getLocalBounds();
				Point newLocalOrigin = new Point((hBar != null ? hBar.getSelection() : 0) + localBounds.x,
						(vBar != null ? vBar.getSelection() : 0) + localBounds.y);
				updateCanvas(mcm.getLocalScale(), newLocalOrigin);
				mcm.setLocalOrigin(newLocalOrigin);
			}
			finally {
				isUpdatingCM = false;
			}
		}
	}

	int lastBoundsRelevantCount = 0;
	double lastLocalScale = 0;
	Point lastLocalOrigin = new Point(0, 0);

	protected void updateCanvas(double newLocalScale, Point newLocalOrigin) {
		if (lastLocalScale != newLocalScale) {
			// the scale changed, force a redraw of everything
			lastBoundsRelevantCount++;
			enqueueRedraw();
		}
		else if (!lastLocalOrigin.equals(newLocalOrigin)) {
			enqueueRedraw();
		}
		lastLocalScale = newLocalScale;
		lastLocalOrigin = newLocalOrigin;
	}

	@Override
	public void bnaModelChanged(final BNAModelEvent evt) {
		if (!evt.isInBulkChange()) {
			enqueueRedraw();
		}
	}

	private boolean redrawPending = false;
	private long redrawRequestTime;

	private void enqueueRedraw() {
		if (redrawPending) {
			return;
		}

		redrawRequestTime = System.nanoTime();
		redrawPending = true;
		SWTWidgetUtils.async(this, new Runnable() {

			@Override
			public void run() {
				redrawPending = false;
				repaint();
			}
		});
	}

	@Override
	public void paintControl(PaintEvent evt) {
		repaint();
	}

	public void repaint() {
		if (DEBUG) {
			System.out.println("Redraw lag : " + (System.nanoTime() - redrawRequestTime) / 1000000f);
		}

		long redrawTime = System.nanoTime();
		setCurrent();
		context.makeCurrent();
		try {
			Resources resources = null;
			try {
				resources = new Resources(this, gl, //
						BNARenderingSettings.getAntialiasText(this));
				Rectangle localBounds = getClientArea();
				BNAUtils.renderInit(bnaView, gl, localBounds, resources, //
						BNARenderingSettings.getAntialiasGraphics(this), //
						BNARenderingSettings.getAntialiasText(this));
				BNAUtils.renderReshape(bnaView, gl, localBounds, resources);
				BNAUtils.renderTopLevelThings(bnaView, gl, localBounds, resources);
				gl.glFlush();
				swapBuffers();
			}
			finally {
				if (resources != null) {
					resources.dispose();
				}
			}
		}
		finally {
			context.release();
		}

		if (DEBUG) {
			System.out.println("Redraw time: " + (System.nanoTime() - redrawTime) / 1000000f);
		}
	}

	public IBNAView getBNAView() {
		return bnaView;
	}

	private IMutableCoordinateMapper getCoordinateMapper() {
		return (IMutableCoordinateMapper) getBNAView().getCoordinateMapper();
	}
}