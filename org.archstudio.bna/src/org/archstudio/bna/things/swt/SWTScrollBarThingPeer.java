package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Slider;

public class SWTScrollBarThingPeer<T extends SWTScrollBarThing> extends AbstractControlThingPeer<T, Slider> {

	public SWTScrollBarThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		SWTWidgetUtils.async(view.getBNAUI().getComposite(), new Runnable() {
			@Override
			public void run() {
				if (control != null && (control.getStyle() & t.getType().getSwtStyle()) == 0) {
					control.dispose();
					control = null;
				}
			}
		});
		return super.draw(localBounds, r);
	}

	@Override
	protected Slider createControl(final IBNAView view, ICoordinateMapper cm) {
		final Slider control = new Slider(view.getBNAUI().getComposite(), t.getType().getSwtStyle());
		control.setData(t);
		control.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				t.setValue(control.getSelection());
			}
		});
		final IThingListener updateControlListener = new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				SWTWidgetUtils.async(view.getBNAUI().getComposite(), new Runnable() {
					@Override
					public void run() {
						updateControl(control);
					}
				});
			}
		};
		updateControl(control);
		t.addThingListener(updateControlListener);
		control.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				t.removeThingListener(updateControlListener);
			}
		});
		return control;
	}

	private void updateControl(Slider slider) {
		slider.setValues(t.getValue(), t.getMin(), t.getMax(), 1, 1, 1);
	}

	@Override
	protected Rectangle getBounds(IBNAView view, ICoordinateMapper cm) {
		Rectangle bounds = super.getBounds(view, cm);

		if (control != null) {
			if ((control.getStyle() & SWT.HORIZONTAL) != 0) {
				Point size = control.computeSize(bounds.width, SWT.DEFAULT);
				bounds.width = size.x;
				bounds.height = size.y;
			}
			if ((control.getStyle() & SWT.VERTICAL) != 0) {
				Point size = control.computeSize(SWT.DEFAULT, bounds.height);
				bounds.width = size.x;
				bounds.height = size.y;
			}
		}

		return bounds;
	}

}