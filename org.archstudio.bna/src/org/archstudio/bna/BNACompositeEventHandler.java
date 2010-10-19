package org.archstudio.bna;

import java.util.List;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import org.archstudio.bna.constants.DropEventType;
import org.archstudio.bna.constants.MouseEventType;
import org.archstudio.swtutils.IMenuFiller;
import org.archstudio.swtutils.LocalSelectionTransfer;
import org.archstudio.swtutils.SWTWidgetUtils;

class BNACompositeEventHandler implements MouseListener, MouseMoveListener, MouseTrackListener, KeyListener, FocusListener, Listener, DropTargetListener {

	public static final int[] SWT_UNTYPED_EVENT_INDEXES = new int[] { SWT.None, SWT.KeyDown, SWT.KeyUp, SWT.MouseDown, SWT.MouseUp, SWT.MouseMove,
	        SWT.MouseEnter, SWT.MouseExit, SWT.MouseDoubleClick, SWT.Paint, SWT.Move, SWT.Resize, SWT.Dispose, SWT.Selection, SWT.DefaultSelection,
	        SWT.FocusIn, SWT.FocusOut, SWT.Expand, SWT.Collapse, SWT.Iconify, SWT.Deiconify, SWT.Close, SWT.Show, SWT.Hide, SWT.Modify, SWT.Verify,
	        SWT.Activate, SWT.Deactivate, SWT.Help, SWT.DragDetect, SWT.Arm, SWT.Traverse, SWT.MouseHover, SWT.HardKeyDown, SWT.HardKeyUp, SWT.MenuDetect,
	        SWT.SetData, SWT.MouseWheel };

	protected BNAComposite comp = null;
	protected IThingLogicManager logicManager = null;
	protected DropTarget dropTarget = null;

	protected PeriodicMouseEventThread mouseEventThread = null;

	//protected PeriodicDropEventThread dropEventThread = null;

	public BNACompositeEventHandler(BNAComposite comp) {
		this.comp = comp;
		this.logicManager = comp.getWorld().getThingLogicManager();
		this.mouseEventThread = new PeriodicMouseEventThread();
		this.mouseEventThread.start();
		//this.dropEventThread = new PeriodicDropEventThread(); 
		//this.dropEventThread.start();
		addListeners();
		setupContextMenu();
	}

	public void dispose() {
		//logicManager.destroy();
		removeListeners();
		//this.dropEventThread.terminate();
		this.mouseEventThread.terminate();
	}

	public void addListeners() {
		comp.addMouseListener(this);
		comp.addMouseMoveListener(this);
		comp.addMouseTrackListener(this);
		comp.addKeyListener(this);
		comp.addFocusListener(this);
		for (int i = 0; i < SWT_UNTYPED_EVENT_INDEXES.length; i++) {
			comp.addListener(SWT_UNTYPED_EVENT_INDEXES[i], this);
		}
		dropTarget = new DropTarget(comp, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT | DND.DROP_LINK);
		dropTarget.setTransfer(new Transfer[] { LocalSelectionTransfer.getInstance() });
		dropTarget.addDropListener(this);
	}

	public void setupContextMenu() {
		SWTWidgetUtils.setupContextMenu("#PopupMenu", comp, new IMenuFiller() {
			public void fillMenu(IMenuManager m) {
				fillContextMenu(m);
			}
		});
	}

	public void removeListeners() {
		dropTarget.removeDropListener(this);
		for (int i = 0; i < SWT_UNTYPED_EVENT_INDEXES.length; i++) {
			comp.removeListener(SWT_UNTYPED_EVENT_INDEXES[i], this);
		}
		comp.removeFocusListener(this);
		comp.removeKeyListener(this);
		comp.removeMouseTrackListener(this);
		comp.removeMouseMoveListener(this);
		comp.removeMouseListener(this);
	}

	private Point lastMouseActionPoint = new Point(0, 0);

	/* Handle Events */

	private void fillContextMenu(IMenuManager m) {
		try {
			List<IBNAMenuListener> menuListeners = logicManager.getThingLogics(IBNAMenuListener.class); 
			if (!menuListeners.isEmpty()) {
				IThing t = comp.getView().getThingAt(lastMouseActionPoint.x, lastMouseActionPoint.y);
				int worldX = comp.getCoordinateMapper().localXtoWorldX(lastMouseActionPoint.x);
				int worldY = comp.getCoordinateMapper().localYtoWorldY(lastMouseActionPoint.y);
				for (IBNAMenuListener l : menuListeners) {
					l.fillMenu(comp.getView(), m, lastMouseActionPoint.x, lastMouseActionPoint.y, t, worldX, worldY);
				}
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	private void mouseEvt(MouseEvent e, MouseEventType type) {
		try {
			if ((type == MouseEventType.MOUSE_UP) || (type == MouseEventType.MOUSE_DOWN) || (type == MouseEventType.MOUSE_CLICK) || (type == MouseEventType.MOUSE_DOUBLECLICK)) {
				lastMouseActionPoint.x = e.x;
				lastMouseActionPoint.y = e.y;
				List<IBNAMouseListener> logics = logicManager.getThingLogics(IBNAMouseListener.class);

				if (!logics.isEmpty()) {
					IThing t = comp.getView().getThingAt(e.x, e.y);
					int worldX = comp.getCoordinateMapper().localXtoWorldX(e.x);
					int worldY = comp.getCoordinateMapper().localYtoWorldY(e.y);

					switch (type) {
					case MOUSE_UP:
						for (IBNAMouseListener l : logics) {
							l.mouseUp(comp.getView(), e, t, worldX, worldY);
						}
						break;
					case MOUSE_DOWN:
						for (IBNAMouseListener l : logics) {
							l.mouseDown(comp.getView(), e, t, worldX, worldY);
						}
						break;
					case MOUSE_CLICK:
						for (IBNAMouseListener l : logics) {
							l.mouseClick(comp.getView(), e, t, worldX, worldY);
						}
						break;
					case MOUSE_DOUBLECLICK:
						for (IBNAMouseListener l : logics) {
							l.mouseDoubleClick(comp.getView(), e, t, worldX, worldY);
						}
						break;
					}
				}
			}
			else if (type == MouseEventType.MOUSE_MOVE) {
				List<IBNAMouseMoveListener> logics = logicManager.getThingLogics(IBNAMouseMoveListener.class);
				if (!logics.isEmpty()) {
					IThing t = comp.getView().getThingAt(e.x, e.y);
					int worldX = comp.getCoordinateMapper().localXtoWorldX(e.x);
					int worldY = comp.getCoordinateMapper().localYtoWorldY(e.y);

					for (IBNAMouseMoveListener l : logics) {
						l.mouseMove(comp.getView(), e, t, worldX, worldY);
					}
				}
			}
			else if ((type == MouseEventType.MOUSE_ENTER) || (type == MouseEventType.MOUSE_EXIT) || (type == MouseEventType.MOUSE_HOVER)) {
				List<IBNAMouseTrackListener> logics = logicManager.getThingLogics(IBNAMouseTrackListener.class);

				if (!logics.isEmpty()) {
					IThing t = comp.getView().getThingAt(e.x, e.y);
					int worldX = comp.getCoordinateMapper().localXtoWorldX(e.x);
					int worldY = comp.getCoordinateMapper().localYtoWorldY(e.y);

					switch (type) {
					case MOUSE_ENTER:
						for (IBNAMouseTrackListener l : logics) {
							l.mouseEnter(comp.getView(), e, t, worldX, worldY);
						}
						break;
					case MOUSE_EXIT:
						for (IBNAMouseTrackListener l : logics) {
							l.mouseExit(comp.getView(), e, t, worldX, worldY);
						}
						break;
					case MOUSE_HOVER:
						for (IBNAMouseTrackListener l : logics) {
							l.mouseHover(comp.getView(), e, t, worldX, worldY);
						}
						break;
					}
				}
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	private MouseEvent lastMouseDownEvt = null;

	public void mouseUp(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_UP);
		if ((lastMouseDownEvt != null) && (BNAUtils.wasClick(lastMouseDownEvt, e))) {
			mouseEvt(e, MouseEventType.MOUSE_CLICK);
		}
	}

	public void mouseDown(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_DOWN);
		lastMouseDownEvt = e;
	}

	public void mouseDoubleClick(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_DOUBLECLICK);
	}

	/*
	 * Mouse move events are run through the Periodic thread because SWT is
	 * really good at emitting mouse events...like, it emits five gazillion a
	 * second, and if BNA tries to respond to them all it will choke because
	 * each little mouse event sets off a cascade of BNA model events. So, this
	 * use of the alternate thread rate-limits mouse events to one every 66ms
	 * (15 events per second) which substantially improves the responseiveness
	 * of BNA apps
	 */
	public void mouseMove(MouseEvent e) {
		mouseEventThread.setEvent(e);
		mouseEventThread.event();
		//mouseEvt(e, MouseEventType.MOUSE_MOVE);
		lastMouseDownEvt = null;
	}

	public void mouseEnter(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_ENTER);
	}

	public void mouseExit(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_EXIT);
	}

	public void mouseHover(MouseEvent e) {
		mouseEvt(e, MouseEventType.MOUSE_HOVER);
	}

	public void keyPressed(KeyEvent e) {
		try {
			for (IBNAKeyListener l : logicManager.getThingLogics(IBNAKeyListener.class)) {
				l.keyPressed(comp.getView(), e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	public void keyReleased(KeyEvent e) {
		try {
			for (IBNAKeyListener l : logicManager.getThingLogics(IBNAKeyListener.class)) {
				l.keyReleased(comp.getView(), e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	public void focusGained(FocusEvent e) {
		try {
			for (IBNAFocusListener l : logicManager.getThingLogics(IBNAFocusListener.class)) {
				l.focusGained(comp.getView(), e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	public void focusLost(FocusEvent e) {
		try {
			for (IBNAFocusListener l : logicManager.getThingLogics(IBNAFocusListener.class)) {
				l.focusLost(comp.getView(), e);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	public void handleEvent(Event evt) {
		try {
			for (IBNAUntypedListener l : logicManager.getThingLogics(IBNAUntypedListener.class)) {
				l.handleEvent(comp.getView(), evt);
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	protected void dropEvt(DropTargetEvent e, DropEventType evtType) {
		List<IBNADragAndDropListener> logics = logicManager.getThingLogics(IBNADragAndDropListener.class);
		if (!logics.isEmpty()) {
			Point p = comp.toControl(e.x, e.y);
			IThing t = comp.getView().getThingAt(p.x, p.y);
			int worldX = comp.getCoordinateMapper().localXtoWorldX(p.x);
			int worldY = comp.getCoordinateMapper().localYtoWorldY(p.y);

			try {
				switch (evtType) {
				case DRAG_ENTER:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.dragEnter(comp.getView(), e, t, worldX, worldY);
					}
					break;
				case DRAG_OVER:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.dragOver(comp.getView(), e, t, worldX, worldY);
					}
					break;
				case DRAG_LEAVE:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.dragLeave(comp.getView(), e, t, worldX, worldY);
					}
					break;
				case DRAG_OPERATION_CHANGED:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.dragOperationChanged(comp.getView(), e, t, worldX, worldY);
					}
					break;
				case DROP_ACCEPT:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.dropAccept(comp.getView(), e, t, worldX, worldY);
					}
					break;
				case DROP:
					e.detail = DND.DROP_NONE;
					for (IBNADragAndDropListener l : logics) {
						l.drop(comp.getView(), e, t, worldX, worldY);
					}
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	public void dragEnter(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_ENTER);
	}

	//These can't be ratelimited like mouse events
	//because the event has to be fully processed synchronously
	//because clients change the event internals and the results
	//of that won't be properly displayed if the changes
	//are done in another thread.
	int counter = 0;

	public void dragOver(DropTargetEvent e) {
		counter++;
		if ((counter % 3) == 0) {
			dropEvt(e, DropEventType.DRAG_OVER);
		}
	}

	public void dragOperationChanged(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_OPERATION_CHANGED);
	}

	public void dragLeave(DropTargetEvent e) {
		dropEvt(e, DropEventType.DRAG_LEAVE);
	}

	public void dropAccept(DropTargetEvent e) {
		dropEvt(e, DropEventType.DROP_ACCEPT);
	}

	public void drop(DropTargetEvent e) {
		dropEvt(e, DropEventType.DROP);
	}

	class PeriodicMouseEventThread extends PeriodicEventThread {
		public void doEvent() {
			mouseEvt((MouseEvent) evt, MouseEventType.MOUSE_MOVE);
		}
	}

	/*
	 * class PeriodicDropEventThread extends PeriodicEventThread{ public void
	 * doEvent(){ dropEvt((DropTargetEvent)evt, DropEventType.DRAG_OVER); } }
	 */

	abstract class PeriodicEventThread extends Thread {
		//30fps default
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

		private Runnable eventer = new Runnable() {
			public void run() {
				doEvent();
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
				try {
					sleep(period);
				}
				catch (InterruptedException e) {
				}
				eventNow();
				needsToEvent = false;
			}
		}

		public void eventNow() {
			if (!comp.isDisposed())
				comp.getDisplay().syncExec(eventer);
		}

	}

	protected void handleException(Exception ex) {
		ex.printStackTrace();
		MessageDialog.openError(comp.getShell(), "Error", "An exception (" + ex.getClass().getName()
		        + ") has occurred. Please see the platform log file for details.");
	}

}
