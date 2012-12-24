package org.archstudio.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GL2ES1;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLContext;
import javax.media.opengl.GLDrawableFactory;
import javax.media.opengl.GLProfile;
import javax.media.opengl.fixedfunc.GLMatrixFunc;
import javax.media.opengl.glu.GLU;

import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasTint;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.utils.BNARenderingSettings;
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
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.opengl.GLCanvas;
import org.eclipse.swt.opengl.GLData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

public class BNACanvas extends GLCanvas implements IBNAModelListener, PaintListener {

	protected static int DEBUG = 0;

	protected final IBNAView bnaView;
	protected final ScrollBar hBar = getHorizontalBar();
	protected final ScrollBar vBar = getVerticalBar();
	protected final BNASWTEventHandler eventHandler;
	protected final GLContext context;
	protected final ObscuredGL2 gl;
	protected final Resources resources;

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
		this.resources = new Resources(this, gl = new ObscuredGL2((GL2) context.getGL(), 1));

		this.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				org.eclipse.swt.graphics.Rectangle bounds = BNACanvas.this.getBounds();
				float fAspect = (float) bounds.width / (float) bounds.height;
				BNACanvas.this.setCurrent();
				context.makeCurrent();
				GL2 gl = (GL2) context.getGL();
				gl.glViewport(0, -getHorizontalBar().getSize().y, bounds.width, bounds.height);
				gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
				gl.glLoadIdentity();
				GLU glu = new GLU();
				glu.gluPerspective(45.0f, fAspect, 0.5f, 1f);
				gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
				gl.glLoadIdentity();
				context.release();
			}
		});

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
		hBar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateCM();
			}
		});
		vBar.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				updateCM();
			}
		});
		updateScrollBars();
		updateCM();

		// prevent the mouse wheel from scrolling the canvas
		addListener(SWT.MouseWheel, new Listener() {

			@Override
			public void handleEvent(Event event) {
				// prevent the mouse wheel from scrolling the canvas
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
		getBNAView().getBNAWorld().getBNAModel().removeBNAModelListener(this);
		eventHandler.dispose();
		resources.destroy();
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
			org.eclipse.swt.graphics.Rectangle client = getClientArea();
			Rectangle localBounds = mcm.getLocalBounds();
			Point localOrigin = mcm.getLocalOrigin();
			updateScrollBar(hBar, localOrigin.x - localBounds.x, client.width, localBounds.width);
			updateScrollBar(vBar, localOrigin.y - localBounds.y, client.height, localBounds.height);
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
				Point newLocalOrigin = new Point(hBar.getSelection() + localBounds.x, vBar.getSelection()
						+ localBounds.y);
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
			redraw();
		}
		else if (!lastLocalOrigin.equals(newLocalOrigin)) {
			redraw();
		}
		lastLocalScale = newLocalScale;
		lastLocalOrigin = newLocalOrigin;
	}

	private boolean needsRedraw = false;
	private boolean redrawPending = false;

	@Override
	public void bnaModelChanged(final BNAModelEvent evt) {
		if (!evt.isInBulkChange() && !needsRedraw && !redrawPending) {
			needsRedraw = true;
			SWTWidgetUtils.async(this, new Runnable() {

				@Override
				public void run() {
					if (needsRedraw && !redrawPending) {
						redrawPending = true;
						needsRedraw = false;
						redraw();
					}
				}
			});
		}
		if (evt.getEventType() == EventType.THING_REMOVING) {
			SWTWidgetUtils.async(this, new Runnable() {

				@Override
				public void run() {
					getBNAView().disposePeer(evt.getTargetThing());
				}
			});
		}
	}

	@Override
	public void paintControl(PaintEvent evt) {
		IBNAModel bnaModel = getBNAView().getBNAWorld().getBNAModel();

		setCurrent();
		context.makeCurrent();
		try {
			org.eclipse.swt.graphics.Rectangle bounds = getBounds();

			gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrtho(0, bounds.width, bounds.height, 0, 0, 1);
			gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
			gl.glDisable(GL.GL_DEPTH_TEST);
			gl.glEnable(GL2.GL_LINE_STIPPLE);
			gl.glEnable(GL.GL_BLEND);
			gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

			if (BNARenderingSettings.getAntialiasGraphics(this)) {
				gl.glEnable(GL.GL_LINE_SMOOTH);
				gl.glEnable(GL2ES1.GL_POINT_SMOOTH);
				gl.glHint(GL2ES1.GL_POINT_SMOOTH_HINT, GL.GL_NICEST);
				gl.glEnable(GL.GL_LINE_SMOOTH);
				gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST);
				gl.glEnable(GL2GL3.GL_POLYGON_SMOOTH);
				gl.glHint(GL2GL3.GL_POLYGON_SMOOTH_HINT, GL.GL_NICEST);
				gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
			}
			else {
				gl.glDisable(GL2ES1.GL_POINT_SMOOTH);
				gl.glDisable(GL.GL_LINE_SMOOTH);
				gl.glDisable(GL2GL3.GL_POLYGON_SMOOTH);
				gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_FASTEST);
			}
			resources.setAntialiasText(BNARenderingSettings.getAntialiasText(this));

			gl.glClearColor(1f, 1f, 1f, 1f);
			gl.glClear(GL.GL_COLOR_BUFFER_BIT);

			redrawPending = false;
			// clip from evt doesn't seem to work consistently
			// instead always using entire bounds
			Rectangle clip = new Rectangle(0, 0, bounds.width, bounds.height);
			IBNAView bnaView = getBNAView();
			ICoordinateMapper cm = bnaView.getCoordinateMapper();
			for (IThing thingToRender : bnaModel.getAllThings()) {
				if (Boolean.TRUE.equals(thingToRender.get(IIsHidden.HIDDEN_KEY))) {
					continue;
				}

				//gl.glPushMatrix();
				gl.glPushAttrib(GL2.GL_TRANSFORM_BIT | GL2.GL_LINE_BIT | GL2.GL_CURRENT_BIT | GL.GL_COLOR_BUFFER_BIT);
				try {
					gl.setAlpha(thingToRender.get(IHasAlpha.ALPHA_KEY, 1f));
					gl.setTint(thingToRender.get(IHasTint.TINT_KEY, new RGB(0, 0, 0)));
					IThingPeer<?> peer = bnaView.getThingPeer(thingToRender);
					peer.draw(bnaView, cm, gl, clip, resources);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					gl.glPopAttrib();
					//gl.glPopMatrix();
				}
			}
			gl.glFlush();
			swapBuffers();
		}
		finally {
			context.release();
		}
	}

	public IBNAView getBNAView() {
		return bnaView;
	}

	private IMutableCoordinateMapper getCoordinateMapper() {
		return (IMutableCoordinateMapper) getBNAView().getCoordinateMapper();
	}
}