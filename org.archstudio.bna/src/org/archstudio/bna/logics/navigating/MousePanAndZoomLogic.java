package org.archstudio.bna.logics.navigating;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.util.List;

import org.archstudio.bna.BNACanvas;
import org.archstudio.bna.IBNAMouseWheelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;

public class MousePanAndZoomLogic extends AbstractThingLogic implements IBNAMouseWheelListener, IBNAMouseListener,
		IBNAMouseMoveListener, IBNAMouseClickListener {
	public static final int DEFAULT_PAN_BUTTON = 2;

	protected int panButton = DEFAULT_PAN_BUTTON;
	protected ICoordinate startMouseCoordinate = null;

	public MousePanAndZoomLogic(IThingLogicManager tlm) {
	}

	public MousePanAndZoomLogic() {
		this(DEFAULT_PAN_BUTTON);
	}

	public MousePanAndZoomLogic(int panButton) {
		this.panButton = panButton;
	}

	@Override
	public void mouseDown(IBNAView view, MouseEvent e, List<IThing> t, ICoordinate location) {
		//Only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (e.button == panButton) {
			startMouseCoordinate = location;
			BNACanvas canvas = castOrNull(e.getSource(), BNACanvas.class);
			if (canvas != null) {
				Cursor handCursor = e.display.getSystemCursor(SWT.CURSOR_HAND);
				canvas.setCursor(handCursor);
			}
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent e, List<IThing> t, ICoordinate location) {
		//Only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (startMouseCoordinate != null) {
			startMouseCoordinate = null;
			BNACanvas canvas = castOrNull(e.getSource(), BNACanvas.class);
			if (canvas != null) {
				canvas.setCursor(null);
			}
		}
	}

	@Override
	public void mouseMove(IBNAView view, MouseEvent e, List<IThing> t, ICoordinate location) {
		if (startMouseCoordinate != null) {
			IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
			if (mcm != null) {
				mcm.align(location.getLocalPoint(new Point()), startMouseCoordinate.getWorldPoint(new Point()));
			}
		}
	}

	@Override
	public void mouseWheel(IBNAView view, final MouseEvent e, Iterable<IThing> t, final ICoordinate location) {
		//Only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		final IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
		if (mcm != null) {
			mcm.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					double oldScale = mcm.getLocalScale();
					int oldPower = BNAUtils.round(Math.log(oldScale) / Math.log(Math.sqrt(2)));
					int newPower = SystemUtils.bound(-10, oldPower + (e.count < 0 ? -1 : 1), 10);
					if (newPower != oldPower) {
						final double newScale = Math.pow(Math.sqrt(2), newPower);
						mcm.synchronizedUpdate(new Runnable() {
							@Override
							public void run() {
								mcm.setLocalScale(newScale);
								mcm.align(location.getLocalPoint(new Point()), location.getWorldPoint(new Point()));
							}
						});
					}
				}
			});
		}
	}

	@Override
	public void mouseClick(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
	}

	@Override
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (evt.button == panButton) {
			final IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
			if (mcm != null) {
				Rectangle r = new Rectangle();
				for (IThing thing : getBNAModel().getAllThings()) {
					if (thing instanceof IHasBoundingBox) {
						union(r, ((IHasBoundingBox) thing).getBoundingBox());
					}
				}
				if (!r.isEmpty()) {
					mcm.setLocalScale(1);
					mcm.align(location.getLocalPoint(new Point()), r.getCenter());
				}
			}
		}
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
