package org.archstudio.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Random;

import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.PeerCache;
import org.archstudio.bna.utils.PeerCache.Cache;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.Graphics;
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

import com.google.common.collect.Lists;

public class BNACanvas extends Canvas implements IBNAView, IBNAModelListener, PaintListener {

	protected static final boolean DEBUG = false;

	protected static class RenderData {
		protected Rectangle lastLocalBounds = new Rectangle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 0);
		protected int lastBoundsRelevantCount = 0;
		protected boolean needsLastRenderCleanup = false;
		protected boolean needsRenderUpdate = true;
	}

	protected final ScrollBar hBar = getHorizontalBar();
	protected final ScrollBar vBar = getVerticalBar();
	protected final IMutableCoordinateMapper mcm;
	/*
	 * This ICoordinateMapper is special in two ways: First, it is only updated in the SWT thread so that painting is
	 * consistent with scrolling. When this is not the case, rapidly sliding a scroll bar back and forth causes
	 * artifacts on the display. Second, it translation of coordinates is turned off so that the translation can occur
	 * within the graphics object during rendering. When translation remains enabled, the local cache values becomes
	 * inconsistent with the rendered thins once the canvas is scrolled.
	 */
	/*
	 * The relationship of coordinate mappers is: mcm <-> scrollbars -> (renderMCM <=> scrollableMCM)
	 */
	protected final IMutableCoordinateMapper renderMCM;
	protected final IScrollableCoordinateMapper scrollableMCM; // null if renderMCM is not IScrollableCoordinateMapper
	boolean isScrollBarUpdating = false;
	int lastBoundsRelevantCount = 0;

	protected void doUpdateRender() {
		if (renderMCM.getLocalScale() != mcm.getLocalScale()) {
			renderMCM.setLocalScale(mcm.getLocalScale());
			renderMCM.setLocalOrigin(mcm.getLocalOrigin(new Point()));
			lastBoundsRelevantCount++;
			redraw();
			return;
		}
		Point renderLocalOrigin = renderMCM.getLocalOrigin(new Point());
		Point mcmLocalOrigin = mcm.getLocalOrigin(new Point());
		if (!renderLocalOrigin.equals(mcmLocalOrigin)) {
			if (scrollableMCM != null) {
				int dx = mcmLocalOrigin.x - renderLocalOrigin.x;
				int dy = mcmLocalOrigin.y - renderLocalOrigin.y;
				org.eclipse.swt.graphics.Rectangle client = getClientArea();
				scroll(-dx, -dy, 0, 0, client.width, client.height, false);
				renderMCM.setLocalOrigin(mcmLocalOrigin);
			}
			else {
				renderMCM.setLocalOrigin(mcmLocalOrigin);
				lastBoundsRelevantCount++;
				redraw();
			}
			return;
		}
	}

	protected final IBNAWorld bnaWorld;
	protected final IBNAModel bnaModel;
	protected final BNASWTEventHandler eventHandler;
	protected final PeerCache<RenderData> peerCache = new PeerCache<RenderData>();

	public BNACanvas(Composite parent, int style, IBNAWorld bnaWorld) {
		super(parent, style | SWT.NO_REDRAW_RESIZE | SWT.H_SCROLL | SWT.V_SCROLL);
		this.mcm = new LinearCoordinateMapper();
		this.bnaWorld = checkNotNull(bnaWorld);
		this.bnaModel = checkNotNull(bnaWorld.getBNAModel());
		this.eventHandler = new BNASWTEventHandler(this, this);
		this.renderMCM = new LinearCoordinateMapper();
		this.scrollableMCM = SystemUtils.castOrNull(renderMCM, IScrollableCoordinateMapper.class);
		if (scrollableMCM != null) {
			this.scrollableMCM.setTranslate(false);
		}

		this.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				doUpdateBars();
			}
		});

		// coordinate mapping
		doUpdateBars();
		hBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isScrollBarUpdating) {
					mcm.synchronizedUpdate(new Runnable() {
						@Override
						public void run() {
							Rectangle localBounds = mcm.getLocalBounds(new Rectangle());
							Point localOrigin = mcm.getLocalOrigin(new Point());
							mcm.setLocalOrigin(new Point(hBar.getSelection() + localBounds.x, localOrigin.y));
						}
					});
				}
			}
		});
		vBar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!isScrollBarUpdating) {
					mcm.synchronizedUpdate(new Runnable() {
						@Override
						public void run() {
							Rectangle localBounds = mcm.getLocalBounds(new Rectangle());
							Point localOrigin = mcm.getLocalOrigin(new Point());
							mcm.setLocalOrigin(new Point(localOrigin.x, vBar.getSelection() + localBounds.y));
						}
					});
				}
			}
		});
		mcm.addCoordinateMapperListener(new ICoordinateMapperListener() {
			@Override
			public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
				SWTWidgetUtils.sync(BNACanvas.this, new Runnable() {
					@Override
					public void run() {
						doUpdateRender();
						doUpdateBars();
					}
				});
			}
		});
		doUpdateRender();
		doUpdateBars();

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
				BNACanvas.this.setFocus();
			}
		});

		// handle events
		bnaModel.addBNAModelListener(this);
		addPaintListener(this);
	}

	@Override
	public void dispose() {
		try {
			if (resources != null) {
				resources.dispose();
			}
		}
		catch (Throwable t) {
		}
		resources = null;
		bnaModel.removeBNAModelListener(this);
		eventHandler.dispose();
	}

	protected void doUpdateBars() {
		org.eclipse.swt.graphics.Rectangle client = getClientArea();
		Rectangle localBounds = mcm.getLocalBounds(new Rectangle());
		Point localOrigin = mcm.getLocalOrigin(new Point());
		doUpdateBar(hBar, localOrigin.x - localBounds.x, client.width, localBounds.width);
		doUpdateBar(vBar, localOrigin.y - localBounds.y, client.height, localBounds.height);
	}

	private void doUpdateBar(ScrollBar bar, int selection, int thumb, int total) {
		isScrollBarUpdating = true;
		try {
			// ScrollBar silently fails when certain constraints are violated
			thumb = Math.min(thumb, total);
			bar.setValues(Math.max(0, selection), 0, Math.max(1, total - thumb), Math.max(1, thumb),
					Math.max(1, thumb / 10), Math.max(1, thumb / 3));
		}
		finally {
			isScrollBarUpdating = false;
		}
	}

	@Override
	public IBNAView getParentView() {
		return null;
	}

	@Override
	public IBNAWorld getBNAWorld() {
		return bnaWorld;
	}

	@Override
	public ICoordinateMapper getCoordinateMapper() {
		return mcm;
	}

	@Override
	public Iterable<IThing> getThingsAt(ICoordinate location) {
		List<IThing> things = Lists.newArrayList();
		for (IThing t : getBNAWorld().getBNAModel().getReverseThings()) {
			if (peerCache.getPeerCache(t).peer.isInThing(this, renderMCM, location)) {
				things.add(t);
			}
		}
		return things;
	}

	public <T extends IThing> Cache<T, RenderData> getPeerCache(T thing) {
		PeerCache.Cache<T, RenderData> cache = peerCache.getPeerCache(thing);
		if (cache.renderData == null) {
			cache.renderData = new RenderData();
		}
		return cache;
	}

	@Override
	public void setCursor(int swtCursor) {
		getDisplay().getSystemCursor(swtCursor);
	}

	Display rDevice = null;
	IResources resources = null;
	Graphics g = null;

	@Override
	public void paintControl(PaintEvent e) {
		if (rDevice != e.display) {
			try {
				if (resources != null) {
					resources.dispose();
				}
			}
			catch (Throwable t) {
			}
			resources = null;
			resources = new DeviceResource(rDevice = e.display);
		}

		g = new SWTGraphics(e.gc);

		if (DEBUG) {
			int debugColor = new Random().nextInt(256);
			org.eclipse.swt.graphics.Rectangle r = e.gc.getClipping();
			g.setBackgroundColor(e.display.getSystemColor(debugColor & 0x0F));
			g.fillRectangle(r.x, r.y, r.width, r.height);
			g.setForegroundColor(e.display.getSystemColor(SWT.COLOR_BLACK));
			g.drawRectangle(r.x, r.y, r.width - 1, r.height - 1);
		}

		Region localClipRegion = null;
		try {
			localClipRegion = new Region(e.display);
			e.gc.getClipping(localClipRegion);

			if (scrollableMCM != null) {
				Point renderLocalOrigin = renderMCM.getLocalOrigin(new Point());
				g.translate(-renderLocalOrigin.x, -renderLocalOrigin.y);
				localClipRegion.translate(renderLocalOrigin.x, renderLocalOrigin.y);
			}

			try {
				//// further clip graphics
				//Rectangle lbb = new Rectangle();
				//for (IThing thing : bnaModel.getThings()) {
				//	Cache<IThing, RenderData> cache = getPeerCache(thing);
				//	RenderData cacheData = cache.renderData;
				//	if (!cacheData.needsLastRenderCleanup && !cacheData.needsRenderUpdate) {
				//		if (cache.peer.getLocalBounds(this, renderMCM, g, resources, lbb)) {
				//			if (cache.peer.isOpaqueAndFilled()) {
				//				localClipRegion.subtract(lbb.x, lbb.y, lbb.width, lbb.height);
				//			}
				//		}
				//		else {
				//			localClipRegion.add(lbb.x, lbb.y, lbb.width, lbb.height);
				//		}
				//	}
				//}

				// render things
				IRegion localClip = new BNARegion(localClipRegion, ICoordinateMapper.IDENTITY);
				IRegion worldClip = new BNARegion(localClipRegion, renderMCM);
				g.pushState();
				for (IThing thing : bnaModel.getThings()) {
					Cache<IThing, RenderData> cache = getPeerCache(thing);
					RenderData cacheData = cache.renderData;

					g.restoreState();
					if (cacheData.lastBoundsRelevantCount != lastBoundsRelevantCount) {
						cacheData.lastLocalBounds.setSize(0, 0);
						cache.renderData.lastBoundsRelevantCount = lastBoundsRelevantCount;
					}
					Rectangle drawArea = new Rectangle(cacheData.lastLocalBounds);
					cache.peer.getLocalBounds(this, renderMCM, g, resources, cacheData.lastLocalBounds);
					union(cacheData.lastLocalBounds, drawArea);

					if (!cacheData.lastLocalBounds.isEmpty()) {
						g.clipRect(cacheData.lastLocalBounds);
						cache.peer.draw(this, renderMCM, g, resources, localClip, worldClip);
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
			if (localClipRegion != null) {
				localClipRegion.dispose();
			}
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(final BNAModelEvent<ET, EK, EV> evt) {
		SWTWidgetUtils.async(this, new Runnable() {
			@Override
			public void run() {
				if (evt.isInBulkChange()) {
					final ET t = evt.getTargetThing();
					if (t != null) {
						Cache<ET, RenderData> cache = getPeerCache(t);
						RenderData cacheData = cache.renderData;
						switch (evt.getEventType()) {
						case THING_REMOVED:
							cacheData.needsLastRenderCleanup = true;
							break;
						case THING_ADDED:
							cacheData.needsRenderUpdate = true;
							break;
						case THING_CHANGED:
						case THING_RESTACKED:
							cacheData.needsLastRenderCleanup = true;
							cacheData.needsRenderUpdate = true;
							break;
						}
					}
				}
				else if (evt.getEventType() == EventType.BULK_CHANGE_END) {
					Rectangle redrawRect = new Rectangle();
					for (IThing t : bnaModel.getThings()) {
						processRedrawRect(redrawRect, t);
					}
					doRedrawRect(redrawRect);
				}
				else {
					final ET t = evt.getTargetThing();
					if (t != null) {
						Rectangle redrawRect = new Rectangle();
						Cache<ET, RenderData> cache = getPeerCache(t);
						RenderData cacheData = cache.renderData;
						switch (evt.getEventType()) {
						case THING_CHANGED:
						case THING_RESTACKED:
							cacheData.needsLastRenderCleanup = true;
							cacheData.needsRenderUpdate = true;
							break;
						case THING_ADDED:
							cacheData.needsRenderUpdate = true;
							break;
						case THING_REMOVED:
							cacheData.needsLastRenderCleanup = true;
							break;
						}
						processRedrawRect(redrawRect, t);
						doRedrawRect(redrawRect);
					}
				}
			}

			private void processRedrawRect(Rectangle redrawRect, IThing t) {
				Cache<IThing, RenderData> cache = getPeerCache(t);
				RenderData cacheData = cache.renderData;
				if (cacheData.needsLastRenderCleanup) {
					if (cacheData.lastBoundsRelevantCount == lastBoundsRelevantCount) {
						union(redrawRect, cacheData.lastLocalBounds);
					}
					cacheData.needsLastRenderCleanup = false;
				}
				if (cacheData.needsRenderUpdate) {
					cache.peer.getLocalBounds(BNACanvas.this, renderMCM, g, resources, cacheData.lastLocalBounds);
					union(redrawRect, cacheData.lastLocalBounds);
					cacheData.needsRenderUpdate = false;
				}
			}

			private void doRedrawRect(Rectangle redrawRect) {
				if (!redrawRect.isEmpty()) {
					Point renderLocalOrigin = renderMCM.getLocalOrigin(new Point());
					redrawRect.translate(-renderLocalOrigin.x, -renderLocalOrigin.y);
					redraw(redrawRect.x, redrawRect.y, redrawRect.width, redrawRect.height, false);
				}
			}
		});
	}

	public static final void union(Rectangle bounds, Rectangle lastBounds) {
		if (bounds.isEmpty()) {
			bounds.setBounds(lastBounds);
		}
		else if (!lastBounds.isEmpty()) {
			bounds.union(lastBounds);
		}
	}
}