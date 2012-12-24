package org.archstudio.bna;

import java.util.List;

import org.archstudio.bna.constants.DropEventType;
import org.archstudio.bna.constants.MouseEventType;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNADragAndDropListener;
import org.archstudio.bna.utils.IBNAFocusListener;
import org.archstudio.bna.utils.IBNAKeyListener;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.bna.utils.IBNAMouseTrackListener;
import org.archstudio.bna.utils.IBNAUntypedListener;
import org.archstudio.swtutils.IMenuFiller;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class BNASWTEventHandler implements MouseListener, MouseWheelListener, MouseMoveListener, MouseTrackListener,
		KeyListener, FocusListener, Listener, DropTargetListener {

	private static final boolean DEBUG = false;

	private static final List<Integer> SWT_UNTYPED_EVENT_INDEXES = Ints.asList(SWT.None, SWT.KeyDown, SWT.KeyUp,
			SWT.MouseDown, SWT.MouseUp, SWT.MouseMove, SWT.MouseEnter, SWT.MouseExit, SWT.MouseDoubleClick, SWT.Paint,
			SWT.Move, SWT.Resize, SWT.Dispose, SWT.Selection, SWT.DefaultSelection, SWT.FocusIn, SWT.FocusOut,
			SWT.Expand, SWT.Collapse, SWT.Iconify, SWT.Deiconify, SWT.Close, SWT.Show, SWT.Hide, SWT.Modify,
			SWT.Verify, SWT.Activate, SWT.Deactivate, SWT.Help, SWT.DragDetect, SWT.Arm, SWT.Traverse, SWT.MouseHover,
			SWT.HardKeyDown, SWT.HardKeyUp, SWT.MenuDetect, SWT.SetData, SWT.MouseWheel);

	protected final Control control;
	protected final IBNAView view;
	protected final ICoordinateMapper cm;
	protected final IThingLogicManager thingLogicManager;
	protected final PeriodicDropEventThread dropEventThread;
	protected final DropTarget dropTarget;
	protected final PeriodicMouseEventThread mouseEventThread;

	public BNASWTEventHandler(Control control, IBNAView view) {
		this.control = control;
		this.view = view;
		this.cm = view.getCoordinateMapper();
		this.thingLogicManager = view.getBNAWorld().getThingLogicManager();
		this.mouseEventThread = new PeriodicMouseEventThread();
		this.mouseEventThread.start();
		this.dropEventThread = new PeriodicDropEventThread();
		this.dropEventThread.start();

		control.addMouseWheelListener(this);
		control.addMouseListener(this);
		control.addMouseMoveListener(this);
		control.addMouseTrackListener(this);
		control.addKeyListener(this);
		control.addFocusListener(this);
		for (int eventType : SWT_UNTYPED_EVENT_INDEXES) {
			control.addListener(eventType, this);
		}
		dropTarget = new DropTarget(control, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT | DND.DROP_LINK);
		dropTarget.setTransfer(new Transfer[] { LocalSelectionTransfer.getInstance() });
		dropTarget.addDropListener(this);

		SWTWidgetUtils.setupContextMenu("#PopupMenu", control, new IMenuFiller() {

			@Override
			public void fillMenu(IMenuManager m) {
				fillContextMenu(m);
			}
		});
	}

	public void dispose() {
		//dropTarget.removeDropListener(this);
		for (int eventType : Lists.reverse(SWT_UNTYPED_EVENT_INDEXES)) {
			control.removeListener(eventType, this);
		}
		control.removeFocusListener(this);
		control.removeKeyListener(this);
		control.removeMouseTrackListener(this);
		control.removeMouseMoveListener(this);
		control.removeMouseListener(this);
		control.removeMouseWheelListener(this);
		this.mouseEventThread.terminate();
	}

	private final Point lastMouseActionPoint = new Point(0, 0);

	/* Handle Events */

	private void fillContextMenu(IMenuManager m) {
		try {
			ICoordinate location = DefaultCoordinate.forLocal(lastMouseActionPoint, cm);
			List<IThing> t = view.getThingsAt(location);
			for (IBNAMenuListener logic : thingLogicManager.getThingLogics(IBNAMenuListener.class)) {
				logic.fillMenu(view, t, location, m);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	private void mouseEvt(MouseEvent e, MouseEventType type) {
		try {
			lastMouseActionPoint.x = e.x;
			lastMouseActionPoint.y = e.y;

			if (DEBUG) {
				System.err.println("ME: mouseEvt" + e);
				System.err.println("ME:  - "
						+ cm.localToWorld(new Point(lastMouseActionPoint.x, lastMouseActionPoint.y)));
			}

			ICoordinate location = DefaultCoordinate.forLocal(lastMouseActionPoint, cm);
			List<IThing> t = view.getThingsAt(location);

			switch (type) {
			case MOUSE_UP:
				for (IBNAMouseListener logic : thingLogicManager.getThingLogics(IBNAMouseListener.class)) {
					logic.mouseUp(view, e, t, location);
				}
				break;
			case MOUSE_DOWN:
				for (IBNAMouseListener logic : thingLogicManager.getThingLogics(IBNAMouseListener.class)) {
					logic.mouseDown(view, e, t, location);
				}
				break;
			case MOUSE_CLICK:
				for (IBNAMouseClickListener logic : thingLogicManager.getThingLogics(IBNAMouseClickListener.class)) {
					logic.mouseClick(view, e, t, location);
				}
				break;
			case MOUSE_DOUBLECLICK:
				for (IBNAMouseClickListener logic : thingLogicManager.getThingLogics(IBNAMouseClickListener.class)) {
					logic.mouseDoubleClick(view, e, t, location);
				}
				break;
			case MOUSE_MOVE:
				for (IBNAMouseMoveListener logic : thingLogicManager.getThingLogics(IBNAMouseMoveListener.class)) {
					logic.mouseMove(view, e, t, location);
				}
				break;
			case MOUSE_ENTER:
				for (IBNAMouseTrackListener logic : thingLogicManager.getThingLogics(IBNAMouseTrackListener.class)) {
					logic.mouseEnter(view, e, t, location);
				}
				break;
			case MOUSE_EXIT:
				for (IBNAMouseTrackListener logic : thingLogicManager.getThingLogics(IBNAMouseTrackListener.class)) {
					logic.mouseExit(view, e, t, location);
				}
				break;
			case MOUSE_HOVER:
				for (IBNAMouseTrackListener logic : thingLogicManager.getThingLogics(IBNAMouseTrackListener.class)) {
					logic.mouseHover(view, e, t, location);
				}
				break;
			case MOUSE_WHEEL:
				for (IBNAMouseWheelListener logic : thingLogicManager.getThingLogics(IBNAMouseWheelListener.class)) {
					logic.mouseWheel(view, e, t, location);
				}
				break;
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	private MouseEvent lastMouseDownEvt = null;

	@Override
	public void mouseUp(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_UP);
		if (lastMouseDownEvt != null && BNAUtils.wasClick(lastMouseDownEvt, e)) {
			mouseEvt(e, MouseEventType.MOUSE_CLICK);
		}
	}

	@Override
	public void mouseDown(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_DOWN);
		lastMouseDownEvt = e;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_DOUBLECLICK);
	}

	/*
	 * Mouse move events are run through the Periodic thread because SWT is
	 * really good at emitting mouse events...like, it emits five gazillion a
	 * second, and if BNA tries to respond to them all it will choke because
	 * each little mouse event sets off a cascade of BNA model events. So, this
	 * use of the alternate thread rate-limits mouse events to one every 66ms
	 * (15 events per second) which substantially improves the responsiveness of
	 * BNA apps
	 */

	@Override
	public void mouseMove(MouseEvent e) {
		mouseEventThread.setEvent(e);
		mouseEventThread.event();
		// mouseEvt(e, MouseEventType.MOUSE_MOVE);
		lastMouseDownEvt = null;
	}

	@Override
	public void mouseEnter(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_ENTER);
	}

	@Override
	public void mouseExit(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_EXIT);
	}

	@Override
	public void mouseHover(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_HOVER);
	}

	@Override
	public void mouseScrolled(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_WHEEL);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			IBNAView bnaView = view;
			for (IBNAKeyListener logic : thingLogicManager.getThingLogics(IBNAKeyListener.class)) {
				logic.keyPressed(bnaView, e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			IBNAView bnaView = view;
			for (IBNAKeyListener logic : thingLogicManager.getThingLogics(IBNAKeyListener.class)) {
				logic.keyReleased(bnaView, e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		try {
			IBNAView bnaView = view;
			for (IBNAFocusListener logic : thingLogicManager.getThingLogics(IBNAFocusListener.class)) {
				logic.focusGained(bnaView, e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		try {
			IBNAView bnaView = view;
			for (IBNAFocusListener logic : thingLogicManager.getThingLogics(IBNAFocusListener.class)) {
				logic.focusLost(bnaView, e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	public void handleEvent(Event evt) {
		try {
			IBNAView bnaView = view;
			for (IBNAUntypedListener logic : thingLogicManager.getThingLogics(IBNAUntypedListener.class)) {
				logic.handleEvent(bnaView, evt);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	protected void dropEvt(DropTargetEvent e, DropEventType evtType) {
		// This is necessary because the point in 'e' is in a different coordinate system
		// - referenced to the window or something.  This puts it so 0,0 is the upper-left of the canvas
		org.eclipse.swt.graphics.Point pointFromControlPerspective = control.toControl(e.x, e.y);
		ICoordinate location = DefaultCoordinate.forLocal(new Point(pointFromControlPerspective.x,
				pointFromControlPerspective.y), cm);
		List<IThing> t = view.getThingsAt(location);

		try {
			switch (evtType) {
			case DRAG_ENTER:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.dragEnter(view, e, t, location);
				}
				break;
			case DRAG_OVER:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.dragOver(view, e, t, location);
				}
				break;
			case DRAG_LEAVE:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.dragLeave(view, e, t, location);
				}
				break;
			case DRAG_OPERATION_CHANGED:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.dragOperationChanged(view, e, t, location);
				}
				break;
			case DROP_ACCEPT:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.dropAccept(view, e, t, location);
				}
				break;
			case DROP:
				e.detail = DND.DROP_NONE;
				for (IBNADragAndDropListener logic : thingLogicManager.getThingLogics(IBNADragAndDropListener.class)) {
					logic.drop(view, e, t, location);
				}
				break;
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	@Override
	public void dragEnter(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_ENTER);
	}

	/*
	 * These can't be rate-limited like mouse events because the event has to be
	 * fully processed synchronously because clients change the event internals
	 * and the results of that won't be properly displayed if the changes are
	 * done in another thread.
	 */
	int counter = 0;

	@Override
	public void dragOver(DropTargetEvent e) {
		counter++;
		if (counter % 3 == 0) {
			dropEvt(e, DropEventType.DRAG_OVER);
		}
	}

	@Override
	public void dragOperationChanged(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_OPERATION_CHANGED);
	}

	@Override
	public void dragLeave(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_LEAVE);
	}

	@Override
	public void dropAccept(DropTargetEvent e) {
		dropEvt(e, DropEventType.DROP_ACCEPT);
	}

	@Override
	public void drop(DropTargetEvent e) {
		dropEvt(e, DropEventType.DROP);
	}

	class PeriodicMouseEventThread extends PeriodicEventThread {

		@Override
		public void doEvent() {
			mouseEvt((MouseEvent) evt, MouseEventType.MOUSE_MOVE);
		}
	}

	class PeriodicDropEventThread extends PeriodicEventThread {

		@Override
		public void doEvent() {
			dropEvt((DropTargetEvent) evt, DropEventType.DRAG_OVER);
		}
	}

	abstract class PeriodicEventThread extends Thread {

		// 30fps default
		protected int period = 66;
		protected boolean needsToEvent = false;
		protected boolean terminate = false;

		public PeriodicEventThread() {
			this.setPriority(Thread.MAX_PRIORITY);
		}

		public void setPeriodInMillis(int periodInMillis) {
			this.period = periodInMillis;
		}

		protected Object evt;

		public void setEvent(Object evt) {
			this.evt = evt;
		}

		public abstract void doEvent();

		private final Runnable eventer = new Runnable() {

			@Override
			public void run() {
				try {
					doEvent();
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		};

		public void event() {
			synchronized (this) {
				needsToEvent = true;
				this.notifyAll();
			}
		}

		public void terminate() {
			synchronized (this) {
				this.terminate = true;
				this.notifyAll();
			}
		}

		@Override
		public void run() {
			while (true) {
				synchronized (this) {
					if (terminate) {
						return;
					}
					if (!needsToEvent) {
						try {
							this.wait();
						}
						catch (InterruptedException e) {
						}
					}
				}
				if (terminate) {
					return;
				}
				//				try {
				//					sleep(period);
				//				}
				//				catch (InterruptedException e) {
				//				}
				eventNow();
				needsToEvent = false;
			}
		}

		public void eventNow() {
			SWTWidgetUtils.sync(control, eventer);
		}

	}

	protected void handleException(Exception ex) {
		ex.printStackTrace();
		MessageDialog.openError(control.getShell(), "Error", "An exception (" + ex.getClass().getName()
				+ ") has occurred. Please see the platform log file for details.");
	}

}
