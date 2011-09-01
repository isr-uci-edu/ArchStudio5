package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ScrollBar;

public abstract class AbstractSWTTreeThingPeer<T extends AbstractSWTTreeThing> extends
		AbstractViewerThingPeer<T, TreeViewer> {

	public static final int DEFAULT_CONTROL_WIDTH = 340;
	public static final int DEFAULT_CONTROL_HEIGHT = 220;

	public AbstractSWTTreeThingPeer(T thing) {
		super(thing);
	}

	protected abstract Object getInput();

	protected abstract ITreeContentProvider getContentProvider();

	protected abstract ILabelProvider getLabelProvider();

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());

		if (t.isEditing() && (control == null)) {
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

			Object initialValue = t.getValue();
			if (initialValue != null) {
				control.setSelection(new StructuredSelection(initialValue));
			}

			if (composite.isFocusControl())
				control.getControl().forceFocus();

			control.addDoubleClickListener(new IDoubleClickListener() {
				public void doubleClick(DoubleClickEvent event) {
					ISelection selection = event.getSelection();
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection structuredSelection = (IStructuredSelection) selection;
						Object value = structuredSelection.getFirstElement();
						t.setValue(value);
						t.setCompletionStatus(CompletionStatus.OK);
						t.setEditing(false);
					}
				}
			});

			control.getControl().addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent e) {
					if (e.character == SWT.ESC) {
						t.setCompletionStatus(CompletionStatus.CANCEL);
						t.setEditing(false);
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
		else if (!t.isEditing() && (control != null) && !control.getControl().isDisposed()) {
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
