package org.archstudio.bna.logics.navigating;

import static org.archstudio.sysutils.SystemUtils.bound;
import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.util.List;
import java.util.Set;

import org.archstudio.bna.IBNAMouseWheelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.GestureType;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.ui.IBNAMagnifyGestureListener;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.ui.IBNAPanGestureListener;
import org.archstudio.bna.ui.IBNATrackGestureListener;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.google.common.collect.Sets;

public class PanAndZoomLogic extends AbstractThingLogic implements IBNAMouseWheelListener, IBNAMouseListener,
		IBNAMouseMoveListener, IBNATrackGestureListener, IBNAMagnifyGestureListener, IBNAPanGestureListener {

	protected static final int PAN_BUTTON = 2; // middle button
	protected static final int MIN_POWER = -10;
	protected static final int MAX_POWER = 10;
	protected static boolean inGesture = false;
	protected static Set<Composite> registeredComposites = Sets.newHashSet();
	protected static Listener preventScrollListener = new Listener() {
		@Override
		public void handleEvent(Event event) {
			if (!inGesture) {
				event.doit = false;
			}
		}
	};

	protected final ModelBoundsTrackingLogic boundsLogic;
	protected ICoordinate startMouseCoordinate = null;

	public PanAndZoomLogic(IBNAWorld world) {
		super(world);
		this.boundsLogic = logics.addThingLogic(ModelBoundsTrackingLogic.class);
	}

	synchronized protected static void registerView(IBNAView view) {
		final Composite composite = view.getBNAUI().getComposite();
		if (!registeredComposites.contains(composite)) {
			composite.addListener(SWT.MouseVerticalWheel, preventScrollListener);
			composite.addListener(SWT.MouseHorizontalWheel, preventScrollListener);
			composite.addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					registeredComposites.remove(composite);
				}
			});
		}
	}

	@Override
	synchronized public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (evt.button == PAN_BUTTON) {
			startMouseCoordinate = location;
			Composite composite = view.getBNAUI().getComposite();
			composite.setCursor(composite.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
		}
	}

	@Override
	synchronized public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (startMouseCoordinate != null) {
			startMouseCoordinate = null;
			view.getBNAUI().getComposite().setCursor(null);
		}
	}

	@Override
	synchronized public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		registerView(view);

		if (startMouseCoordinate != null) {
			IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
			if (mcm != null) {
				mcm.align(location.getLocalPoint(), startMouseCoordinate.getWorldPoint());
			}
		}
	}

	@Override
	public void mouseHorizontalWheel(IBNAView view, MouseType type, MouseEvent e, List<IThing> t, ICoordinate location) {
	}

	@Override
	public void mouseVerticalWheel(IBNAView view, MouseType type, MouseEvent e, List<IThing> t, ICoordinate location) {
		registerView(view);

		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (!inGesture) {
			IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
			if (mcm != null) {
				double oldScale = mcm.getLocalScale();
				int oldPower = BNAUtils.round(Math.log(oldScale) / Math.log(Math.sqrt(2)));
				int newPower = bound(MIN_POWER, oldPower + (e.count < 0 ? -1 : 1), MAX_POWER);
				if (newPower != oldPower) {
					final double newScale = Math.pow(Math.sqrt(2), newPower);
					mcm.setLocalScaleAndAlign(newScale, location.getLocalPoint(), location.getWorldPoint());
				}
			}
		}
	}

	@Override
	public void magnifyGesture(IBNAView view, GestureType type, GestureEvent e, List<IThing> t, ICoordinate location) {
		IMutableCoordinateMapper mcm = castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
		if (mcm != null) {
			double oldScale = mcm.getLocalScale();
			double newScale = bound(Math.pow(Math.sqrt(2), MIN_POWER), mcm.getLocalScale() * e.magnification,
					Math.pow(Math.sqrt(2), MAX_POWER));
			if (newScale != oldScale) {
				mcm.setLocalScaleAndAlign(newScale, location.getLocalPoint(), location.getWorldPoint());
			}
		}
	}

	@Override
	public void beginGesture(IBNAView view, GestureType type, GestureEvent e, List<IThing> t, ICoordinate location) {
		inGesture = true;
	}

	@Override
	public void endGesture(IBNAView view, GestureType type, GestureEvent e, List<IThing> t, ICoordinate location) {
		inGesture = false;
	}

	@Override
	public void panGesture(IBNAView view, GestureType type, GestureEvent e, List<IThing> t, ICoordinate location) {
		// This seems to happen automatically using the scrollbars
	}

}
