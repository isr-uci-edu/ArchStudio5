package org.archstudio.bna.things.swt;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractControlThingPeer<T extends AbstractControlThing, C extends Control> extends
		AbstractThingPeer<T> {

	protected C control = null;

	public AbstractControlThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected abstract C createControl(IBNAView view, ICoordinateMapper cm);

	protected void remove(IBNAView view) {
		model.removeThing(t);
	}

	protected Rectangle getBounds(IBNAView view, ICoordinateMapper cm) {
		return cm.worldToLocal(t.getBoundingBox());
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {
		SWTWidgetUtils.async(view.getComposite(), new Runnable() {
			@Override
			public void run() {
				if (control == null) {
					control = createControl(view, cm);
					// necessary for the AWT UI
					control.moveAbove(null);
				}

				Rectangle newBounds = getBounds(view, cm);
				Rectangle oldBounds = control.getBounds();
				if (!oldBounds.equals(newBounds)) {
					if (oldBounds.width != newBounds.width || oldBounds.height != newBounds.height) {
						control.setSize(newBounds.width, newBounds.height);
						if (control instanceof Composite) {
							((Composite) control).layout(true, true);
							control.pack(true);
						}
					}
					control.setLocation(newBounds.x, newBounds.y);
				}
			}
		});
	}

	@Override
	public void dispose() {
		control = SWTWidgetUtils.quietlyDispose(control);
		super.dispose();
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
