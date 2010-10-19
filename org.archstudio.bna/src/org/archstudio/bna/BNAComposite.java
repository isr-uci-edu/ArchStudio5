package org.archstudio.bna;

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
	protected BNACompositeEventHandler eventHandler = null;

	protected IBNAView bnaView = null;

	public BNAComposite(Composite parent, int style, IBNAView view) {
		super(parent, style & ~SWT.NO_BACKGROUND);
		this.bnaView = view;
		view.setParentComposite(this);
		init();
	}

	public IBNAView getView() {
		return bnaView;
	}

	protected IBNAWorld getWorld() {
		return bnaView.getWorld();
	}

	protected ICoordinateMapper getCoordinateMapper() {
		return getView().getCoordinateMapper();
	}

	protected void init() {
		ppt = new PeriodicPaintThread(this);
		ppt.start();

		eventHandler = new BNACompositeEventHandler(this);
		this.addPaintListener(this);
		getView().getWorld().getBNAModel().addBNAModelListener(this);
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

				public void handleEvent(Event event) {
					if (hb.getData() != null) {
						return;
					}
					int sel = hb.getSelection();
					((DefaultCoordinateMapper) getCoordinateMapper()).repositionAbsolute(sel - getCoordinateMapper().getWorldMinX(), getCoordinateMapper()
					        .localYtoWorldY(0));
				}
			});
		}

		if (hasVscroll()) {
			final ScrollBar vb = getVerticalBar();

			vb.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event event) {
					if (vb.getData() != null) {
						return;
					}
					int sel = vb.getSelection();
					((DefaultCoordinateMapper) getCoordinateMapper()).repositionAbsolute(getCoordinateMapper().localXtoWorldX(0), sel
					        - getCoordinateMapper().getWorldMinY());
				}
			});
		}
	}

	@Override
	public void dispose() {
		//TODO: Implement cleanup
		getView().setParentComposite(null);
		ppt.terminate();
		eventHandler.dispose();
		super.dispose();
	}

	int holdPaintingLevel = 0;
	boolean needsPaintWhenNotHolding = false;

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
				//doRedraw();
				ppt.redraw();
				needsPaintWhenNotHolding = false;
			}
		}
	}

	public void paintControl(PaintEvent e) {
		e.gc.setAntialias(BNARenderingSettings.getAntialiasGraphics(this) ? SWT.ON : SWT.OFF);
		e.gc.setTextAntialias(BNARenderingSettings.getAntialiasText(this) ? SWT.ON : SWT.OFF);

		//The advanced system is generally screwed up and messes up
		//TextLayout rendering
		e.gc.setAdvanced(false);

		try {
			for (IThing element : getWorld().getBNAModel().getAllThings()) {
				IThingPeer peer = getView().getPeer(element);
				peer.draw(getView(), e.gc);
			}
		}
		catch (Throwable t) {
			t.printStackTrace();
		}
	}

	private Runnable scrollbarSyncer = new Runnable() {

		public void run() {
			if (!isDisposed()) {
				syncScrollBars();
			}
		}
	};

	public void doSyncScrollBars() {
		getDisplay().asyncExec(scrollbarSyncer);
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

	public void coordinateMappingsChanged(CoordinateMapperEvent evt) {
		doSyncScrollBars();
		//doRedraw();
		ppt.redraw();
	}

}
