package org.archstudio.bna.things.swt;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.CompletionStatus;

public abstract class AbstractSWTTreeThingPeer extends AbstractThingPeer {

	protected AbstractSWTTreeThing lt;
	protected TreeViewer control = null;

	public static final int DEFAULT_CONTROL_WIDTH = 340;
	public static final int DEFAULT_CONTROL_HEIGHT = 220;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public AbstractSWTTreeThingPeer(IThing t) {
		super(t);
		if (!(t instanceof AbstractSWTTreeThing)) {
			throw new IllegalArgumentException("AbstractSWTTreeThingPeer can only peer for AbstractSWTTreeThing");
		}
		this.lt = (AbstractSWTTreeThing) t;
		lt.addThingListener(new IThingListener() {
			public void thingChanged(ThingEvent thingEvent) {
				if (!lt.isEditing() && (control != null)) {
					if (!control.getControl().isDisposed()) {
						control.getControl().dispose();
						control = null;
					}
				}
			}
		});
	}

	protected abstract Object getInput();

	protected abstract ITreeContentProvider getContentProvider();

	protected abstract ILabelProvider getLabelProvider();

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		Point p = lt.getAnchorPoint();
		Point lp = BNAUtils.worldToLocal(cm, p);

		if (control != null) {
			int cw = control.getControl().getBounds().width;
			int ch = control.getControl().getBounds().height;
			localBoundingBox = new Rectangle(lp.x - (cw / 2), lp.y - (ch / 2), cw, ch);
		}
		else {
			localBoundingBox = new Rectangle(lp.x - (DEFAULT_CONTROL_WIDTH / 2), lp.y - (DEFAULT_CONTROL_HEIGHT / 2), DEFAULT_CONTROL_WIDTH,
			        DEFAULT_CONTROL_HEIGHT);
		}
		lt.setProperty("#boundingBox", BNAUtils.localToWorld(cm, localBoundingBox));
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());

		if (lt.isEditing() && (control == null)) {
			//It has been made visible but we have no corresponding control
			Composite composite = BNAUtils.getParentComposite(view);
			if (composite == null) {
				return;
			}

			int flags = SWT.BORDER | SWT.FLAT | SWT.SINGLE;

			control = new TreeViewer(composite, flags);

			//Set initial properties on the control

			ITreeContentProvider contentProvider = getContentProvider();
			if (contentProvider != null) {
				control.setContentProvider(contentProvider);
			}
			ILabelProvider labelProvider = getLabelProvider();
			if (labelProvider != null) {
				control.setLabelProvider(labelProvider);
			}

			control.setInput(getInput());
			control.expandAll();

			Object initialValue = lt.getValue();
			if (initialValue != null) {
				control.setSelection(new StructuredSelection(initialValue));
			}

			if (composite.isFocusControl())
				control.getControl().forceFocus();
			;

			control.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					ISelection selection = event.getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						Object value = structuredSelection.getFirstElement();
						lt.setValue(value);
						lt.setCompletionStatus(CompletionStatus.OK);
						lt.setEditing(false);
					}
				}
			});

			control.getControl().addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (e.character == SWT.ESC) {
						lt.setCompletionStatus(CompletionStatus.CANCEL);
						lt.setEditing(false);
					}
				}
			});

			//Note: this is a good idea but it doesn't work because of an Eclipse bug.
			//Point computedSize = control.getTree().computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
			control.getTree().setSize(280, control.getTree().getItemHeight() * 5);
			ScrollBar hb = control.getTree().getHorizontalBar();
			if (hb != null) {
				hb.setSelection(hb.getMinimum());
			}

			updateLocalBoundingBox(view.getCoordinateMapper());
		}
		else if (!lt.isEditing() && (control != null) && !control.getControl().isDisposed()) {
			//It has been made invisible but we are still showing the control
			control.getControl().dispose();
			control = null;
			//view.getWorld().getBNAModel().removeThing(lt);
		}

		//Update the control
		if ((control != null) && !control.getControl().isDisposed()) {
			control.getControl().setBounds(localBoundingBox);
		}

		//if(!g.getClipping().intersects(localBoundingBox)) return;
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
