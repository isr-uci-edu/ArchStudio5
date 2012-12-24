package org.archstudio.bna.things.swt;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractSWTControlThingPeer<T extends AbstractSWTThing, C extends Control> extends
		AbstractRectangleThingPeer<T> implements IThingPeer<T> {

	private C control;

	public AbstractSWTControlThingPeer(T thing) {
		super(thing);
		t.addThingListener(new IThingListener() {

			public void thingChanged(ThingEvent thingEvent) {
				if (!AbstractSWTControlThingPeer.this.t.isEditing() && control != null) {
					disposeControl(control);
					control = null;
				}
			}
		});
	}

	abstract protected C createControl(IBNAView view, ICoordinateMapper cm);

	abstract protected void updateControl(C control, IBNAView view, ICoordinateMapper cm);

	protected void disposeControl(final C control) {
		SWTWidgetUtils.async(control, new Runnable() {

			public void run() {
				control.dispose();
			}
		});
	}

	public void dispose() {
		super.dispose();
		disposeControl(control);
		control = null;
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {
		Composite composite = r.getComposite();
		if (composite == null) {
			return;
		}

		if (t.isEditing() && control == null) {
			//It has been made visible but we have no corresponding control
			control = createControl(view, cm);
			if (composite.isFocusControl()) {
				control.forceFocus();
			}
		}
		else if (!t.isEditing() && control != null) {
			//It has been made invisible but we are still showing the control
			disposeControl(control);
			control = null;
		}
		else if (control != null && !control.isDisposed()) {
			//Update the control
			updateControl(control, view, cm);
		}
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		return false;
	}
}
