package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractControlThingPeer<T extends AbstractControlThing, C extends Control> extends
		AbstractThingPeer<T> {

	protected C control = null;

	public AbstractControlThingPeer(T thing) {
		super(thing);
	}

	protected abstract C createControl(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r);

	protected void remove(IBNAView view) {
		view.getBNAWorld().getBNAModel().removeThing(t);
	}

	protected Rectangle getBounds(C control, IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		return cm.worldToLocal(t.getBoundingBox());
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {

		if (control == null) {
			control = createControl(view, cm, g, r);
		}

		// update bounds
		final Rectangle newBounds = getBounds(control, view, cm, g, r);
		final Rectangle oldBounds = BNAUtils.toRectangle(control.getBounds());
		if (!oldBounds.equals(newBounds)) {
			SWTWidgetUtils.async(control, new Runnable() {
				@Override
				public void run() {
					if (!oldBounds.getSize().equals(newBounds.getSize())) {
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

	@Override
	public void dispose() {
		super.dispose();
		control = SWTWidgetUtils.quietlyDispose(control);
	}

	@Override
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location) {
		if (control != null) {
			Point local = location.getLocalPoint(new Point()).translate(cm.getLocalOrigin(new Point()));
			return control.getBounds().contains(local.x, local.y);
		}
		return false;
	}

	@Override
	public void getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r, Rectangle boundsResult) {
		cm.worldToLocal(boundsResult.setBounds(t.getBoundingBox()));
		if (control != null) {
			org.eclipse.swt.graphics.Rectangle bounds = control.getBounds();
			BNAUtils.union(boundsResult, new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height).translate(cm
					.getLocalOrigin(new Point())));
		}
	}
}
