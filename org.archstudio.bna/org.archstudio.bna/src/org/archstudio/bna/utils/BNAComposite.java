package org.archstudio.bna.utils;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNASWTEventHandler;
import org.archstudio.bna.CoordinateMapperEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.ICoordinateMapperListener;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

public class BNAComposite extends Composite implements PaintListener, IBNAModelListener, ICoordinateMapperListener {

	public static final int SCROLL_INCREMENT = 30;

	protected PeriodicPaintThread ppt = null;
	protected BNASWTEventHandler eventHandler = null;

	protected IBNAView bnaView = null;

	public BNAComposite(Composite parent, int style, IBNAView view) {
		super(parent, style);
		this.bnaView = view;
		init();
	}

	public IBNAView getView() {
		return bnaView;
	}

	protected IBNAWorld getWorld() {
		return bnaView.getBNAWorld();
	}

	protected ICoordinateMapper getCoordinateMapper() {
		return getView().getCoordinateMapper();
	}

	protected void init() {
		ppt = new PeriodicPaintThread(this);
		ppt.start();

		eventHandler = new BNASWTEventHandler(this);
		this.addPaintListener(this);
		getView().getBNAWorld().getBNAModel().addBNAModelListener(this);
		getView().getCoordinateMapper().addCoordinateMapperListener(this);

		initScrolling();
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseDown(MouseEvent e) {
				BNAComposite.this.setFocus();
			}
		});
	}

	private boolean hasHscroll() {
		return (getStyle() & SWT.H_SCROLL) != 0;
	}

	private boolean hasVscroll() {
		return (getStyle() & SWT.V_SCROLL) != 0;
	}

	private void initScrolling() {
		if (hasHscroll()) {
			final ScrollBar hb = getHorizontalBar();
			hb.setMinimum(0);
			hb.setMaximum(getCoordinateMapper().getWorldMaxX() - getCoordinateMapper().getWorldMinX());
		}

		if (hasVscroll()) {
			final ScrollBar vb = getVerticalBar();
			vb.setMinimum(0);
			vb.setMaximum(getCoordinateMapper().getWorldMaxY() - getCoordinateMapper().getWorldMinY());
		}

		doSyncScrollBars();

		if (hasHscroll()) {
			final ScrollBar hb = getHorizontalBar();

			hb.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					if (hb.getData() != null) {
						return;
					}
					int sel = hb.getSelection();
					((DefaultCoordinateMapper) getCoordinateMapper()).repositionAbsolute(sel
							- getCoordinateMapper().getWorldMinX(), getCoordinateMapper().localYtoWorldY(0));
				}
			});
		}

		if (hasVscroll()) {
			final ScrollBar vb = getVerticalBar();

			vb.addListener(SWT.Selection, new Listener() {

				@Override
				public void handleEvent(Event event) {
					if (vb.getData() != null) {
						return;
					}
					int sel = vb.getSelection();
					((DefaultCoordinateMapper) getCoordinateMapper()).repositionAbsolute(getCoordinateMapper()
							.localXtoWorldX(0), sel - getCoordinateMapper().getWorldMinY());
				}
			});
		}
	}

	@Override
	public void dispose() {
		// TODO: Implement cleanup
		getView().setParentCanvas(null);
		ppt.terminate();
		eventHandler.dispose();
		super.dispose();
	}

	int holdPaintingLevel = 0;
	boolean needsPaintWhenNotHolding = false;

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.BULK_CHANGE_BEGIN) {
			holdPaintingLevel++;
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.BULK_CHANGE_END) {
			holdPaintingLevel--;
			if (holdPaintingLevel < 0) {
				holdPaintingLevel = 0;
			}
		}
		else {
			needsPaintWhenNotHolding = true;
		}
		if (holdPaintingLevel == 0) {
			if (needsPaintWhenNotHolding) {
				// doRedraw();
				ppt.redraw();
				needsPaintWhenNotHolding = false;
			}
		}
	}

	@Override
	public void paintControl(PaintEvent e) {
		e.gc.setAdvanced(true);
		Graphics g = new SWTGraphics(e.gc);
		g.setAntialias(BNARenderingSettings.getAntialiasGraphics(this) ? SWT.ON : SWT.OFF);
		g.setTextAntialias(BNARenderingSettings.getAntialiasText(this) ? SWT.ON : SWT.OFF);
		org.eclipse.draw2d.geometry.Rectangle clip = BNAUtils.toRectangle(e.gc.getClipping());
		ResourceUtils res = ResourceUtils.resourceUtilsFor(e.display);

		try {
			BNAUtils.renderWorld(getView(), g, clip, res);
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private final Runnable scrollbarSyncer = new Runnable() {

		@Override
		public void run() {
			try {
				if (!isDisposed()) {
					syncScrollBars();
				}
			}
			catch (Throwable t) {
				t.printStackTrace();
			}
		}
	};

	public void doSyncScrollBars() {
		SWTWidgetUtils.async(this, scrollbarSyncer);
	}

	private final Object SCROLLBAR_HOLDER = new Object();

	private void syncScrollBars() {
		int scrollIncrement = BNAUtils.round(SCROLL_INCREMENT * 1.0d / getCoordinateMapper().getScale());

		if (hasHscroll()) {
			ScrollBar hb = getHorizontalBar();
			hb.setIncrement(scrollIncrement);
			hb.setPageIncrement(scrollIncrement * 2);
			hb.setData(SCROLLBAR_HOLDER);
			hb.setSelection(getCoordinateMapper().localXtoWorldX(0) - getCoordinateMapper().getWorldMinX());
			hb.setData(null);
		}

		if (hasVscroll()) {
			ScrollBar vb = getVerticalBar();
			vb.setIncrement(scrollIncrement);
			vb.setPageIncrement(scrollIncrement * 2);
			vb.setData(SCROLLBAR_HOLDER);
			vb.setSelection(getCoordinateMapper().localYtoWorldY(0) - getCoordinateMapper().getWorldMinY());
			vb.setData(null);
		}
	}

	@Override
	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		doSyncScrollBars();
		// doRedraw();
		ppt.redraw();
	}

}
