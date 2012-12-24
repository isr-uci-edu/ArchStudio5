package org.archstudio.bna.things.swt;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractControlThingPeer<T extends AbstractControlThing, C extends Control> extends
		AbstractThingPeer<T> implements IThingPeer<T> {

	protected C control = null;

	public AbstractControlThingPeer(T thing) {
		super(thing);
	}

	protected abstract C createControl(IBNAView view, ICoordinateMapper cm);

	protected void remove(IBNAView view) {
		view.getBNAWorld().getBNAModel().removeThing(t);
	}

	protected Rectangle getBounds(C control, IBNAView view, ICoordinateMapper cm) {
		return cm.worldToLocal(t.getBoundingBox());
	}

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r) {

		if (control == null) {
			control = createControl(view, cm);
		}

		// update bounds
		final Rectangle newBounds = getBounds(control, view, cm);
		final Rectangle oldBounds = control.getBounds();
		if (!oldBounds.equals(newBounds)) {
			SWTWidgetUtils.async(control, new Runnable() {

				public void run() {
					if (oldBounds.width != newBounds.width || oldBounds.height != newBounds.height) {
						control.setSize(newBounds.width, newBounds.height);
						if (control instanceof Composite) {
							((Composite) control).layout(true, true);
							control.pack(true);
						}
					}
					control.setLocation(newBounds.x, newBounds.y);
				}
			});
		}
	}

	public void dispose() {
		super.dispose();
		control = SWTWidgetUtils.quietlyDispose(control);
	}

	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (control != null) {
			Point local = location.getLocalPoint();
			local.x += cm.getLocalOrigin().x;
			local.y += cm.getLocalOrigin().y;
			return control.getBounds().contains(local.x, local.y);
		}
		return false;
	}

}
