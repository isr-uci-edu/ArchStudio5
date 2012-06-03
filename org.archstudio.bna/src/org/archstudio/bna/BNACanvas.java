package org.archstudio.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultBNAView;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class BNACanvas extends Canvas implements IBNAModelListener, PaintListener {

	private static boolean requestedRedraw = false;
	private static Rectangle redrawRect = new Rectangle();

	private final class ThingPeerCache {
		private Rectangle lastLocalBounds = new Rectangle();
		private int lastBoundsRelevantCount = -1;

		public void updateCacheData(IThingPeer<?> peer) {
			this.lastLocalBounds = peer.getLocalBounds(bnaView, bnaView.getCoordinateMapper(), resources);
			this.lastBoundsRelevantCount = BNACanvas.this.lastBoundsRelevantCount;
		}

		public void redraw() {
			if (!requestedRedraw) {
				requestedRedraw = true;
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						if (BNACanvas.this.isDisposed())
							return;
						if (redrawRect == null)
							BNACanvas.this.redraw();
						else
							BNACanvas.this.redraw(redrawRect.x, redrawRect.y, redrawRect.width, redrawRect.height,
									false);
						redrawRect = new Rectangle();
						requestedRedraw = false;
					}
				});
			}

			if (redrawRect == null)
				return;
			if (lastLocalBounds == null) {
				redrawRect = null;
			}
			else {
				if (redrawRect.isEmpty())
					redrawRect = new Rectangle(lastLocalBounds);
				else
					redrawRect.union(lastLocalBounds);
			}
		}
	}

	protected final IBNAView bnaView;
	protected final ScrollBar hBar = getHorizontalBar();
	protected final ScrollBar vBar = getVerticalBar();
	protected final BNASWTEventHandler eventHandler;
	protected final Resources resources;
	private final LoadingCache<IThing, ThingPeerCache> renderDataCache = CacheBuilder.newBuilder().build(
			new CacheLoader<IThing, ThingPeerCache>() {
				@Override
				public ThingPeerCache load(IThing input) {
					return new ThingPeerCache();
				}
			});

	public BNACanvas(Composite parent, int style, IBNAWorld bnaWorld) {
		this(parent, style, new DefaultBNAView(null, bnaWorld, new LinearCoordinateMapper()));
	}

	public BNACanvas(Composite parent, int style, IBNAView bnaView) {
		super(parent, style | SWT.NO_REDRAW_RESIZE | SWT.DOUBLE_BUFFERED);
		checkNotNull(bnaView);
		checkNotNull(bnaView.getBNAWorld());
		checkNotNull(bnaView.getBNAWorld().getBNAModel());

		this.bnaView = bnaView;
		this.eventHandler = new BNASWTEventHandler(this, bnaView);
		this.resources = new Resources(this);

		bnaView.setComposite(this);
		this.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				updateScrollBars();
			}
		});
		bnaView.getCoordinateMapper().addCoordinateMapperListener(new ICoordinateMapperListener() {
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
		resources.dispose();
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
			ICoordinateMapper mcm = bnaView.getCoordinateMapper();
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
				IMutableCoordinateMapper mcm = (IMutableCoordinateMapper) bnaView.getCoordinateMapper();
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
			// scroll the screen to align with the desired origin
			int dx = newLocalOrigin.x - lastLocalOrigin.x;
			int dy = newLocalOrigin.y - lastLocalOrigin.y;

			org.eclipse.swt.graphics.Rectangle client = getClientArea();
			scroll(-dx, -dy, 0, 0, client.width, client.height, true);
			// scroll automatically causes a redraw of the newly revealed area

			// update the cached local bounds as well
			for (ThingPeerCache renderData : renderDataCache.asMap().values()) {
				if (renderData.lastLocalBounds != null)
					renderData.lastLocalBounds.translate(-dx, -dy);
			}
		}
		lastLocalScale = newLocalScale;
		lastLocalOrigin = newLocalOrigin;
	}

	// ignore the flurry of events that occur before rendering the BNA World for the first time
	boolean startedRendering = false;

	@Override
	public void paintControl(PaintEvent e) {
		IBNAModel bnaModel = getBNAView().getBNAWorld().getBNAModel();

		// the first time we paint, take note so that we start paying attention to model updates
		if (!startedRendering) {
			startedRendering = true;
		}

		SWTGraphics g = null;
		Region localClipRegion = null;
		Rectangle localClipRectangle = null;

		try {
			g = new SWTGraphics(e.gc);
			g.setAdvanced(true);
			g.setAntialias(BNARenderingSettings.getAntialiasGraphics(this) ? SWT.ON : SWT.OFF);
			g.setTextAntialias(BNARenderingSettings.getAntialiasText(this) ? SWT.ON : SWT.OFF);

			localClipRegion = new Region(e.display);
			e.gc.getClipping(localClipRegion);
			localClipRectangle = BNAUtils.toRectangle(localClipRegion.getBounds());

			// render all things
			g.pushState();
			try {
				for (IThing thingToRender : bnaModel.getAllThings()) {
					IThingPeer<?> peer = bnaView.getThingPeer(thingToRender);
					ThingPeerCache peerCache = renderDataCache.getUnchecked(thingToRender);

					// update cached local bounds, if necessary
					if (peerCache.lastBoundsRelevantCount != lastBoundsRelevantCount) {
						peerCache.updateCacheData(peer);
					}

					// draw the thing
					if (peerCache.lastLocalBounds != null) {
						if (localClipRectangle.intersects(peerCache.lastLocalBounds)
								&& localClipRegion.intersects(peerCache.lastLocalBounds.x, peerCache.lastLocalBounds.y,
										peerCache.lastLocalBounds.width, peerCache.lastLocalBounds.height)) {
							try {
								g.restoreState();
								g.clipRect(peerCache.lastLocalBounds);
								peer.draw(bnaView, bnaView.getCoordinateMapper(), resources, g);
							}
							catch (Exception e2) {
								System.err.println(peer.getClass());
								e2.printStackTrace();
							}
						}
					}
					else {
						try {
							g.restoreState();
							peer.draw(bnaView, bnaView.getCoordinateMapper(), resources, g);
						}
						catch (Exception e2) {
							System.err.println(peer.getClass());
							e2.printStackTrace();
						}
					}
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
			finally {
				g.popState();
			}
		}
		finally {
			localClipRegion = SWTWidgetUtils.quietlyDispose(localClipRegion);
			if (g != null) {
				g.dispose();
			}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(final BNAModelEvent<ET, EK, EV> evt) {

		if (isDisposed())
			return;
		
		// ignore model events until we start painting
		if (!startedRendering)
			return;

		// update rendering of thing
		final IThing t = evt.getTargetThing();
		if (t != null) {
			IThingPeer<?> peer = getBNAView().getThingPeer(t);
			ThingPeerCache peerCache = renderDataCache.getUnchecked(t);
			switch (evt.getEventType()) {
			case THING_REMOVED:
				// redraw the old area where it was to erase it
				peerCache.redraw();
				getBNAView().disposePeer(t);
				break;
			case THING_CHANGED:
			case THING_RESTACKED:
				// redraw the old area where it was to erase it
				peerCache.redraw();
				// redraw the new area to update it
				peerCache.updateCacheData(peer);
				peerCache.redraw();
				break;
			case THING_ADDED:
				// redraw the new area to update it
				peerCache.updateCacheData(peer);
				peerCache.redraw();
				break;
			}
		}
	}

	public IBNAView getBNAView() {
		return bnaView;
	}

}