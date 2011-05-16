package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractSWTViewerThingPeer<T extends AbstractSWTThing, V extends Viewer> extends
		AbstractThingPeer<T> {

	protected V viewer = null;

	public AbstractSWTViewerThingPeer(T thing) {
		super(thing);
		t.addThingListener(new IThingListener() {
			public void thingChanged(ThingEvent<?, ?> thingEvent) {
				disposeViewer();
			}
		});
	}

	abstract protected void createViewer(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res,
			Composite composite);

	abstract protected void updateViewer(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res,
			Composite composite);

	protected void disposeViewer() {
		if (!AbstractSWTViewerThingPeer.this.t.isEditing() && viewer != null) {
			Control control = viewer.getControl();
			if (!control.isDisposed()) {
				control.dispose();
			}
			control = null;
		}
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Composite composite = BNAUtils.getParentComposite(view);
		if (composite == null) {
			return;
		}

		if (t.isEditing() && viewer == null) {
			//It has been made visible but we have no corresponding viewer
			createViewer(view, g, clip, res, composite);

			if (composite.isFocusControl()) {
				viewer.getControl().forceFocus();
			}
		}
		else if (!t.isEditing() && viewer != null) {
			//It has been made invisible but we are still showing the viewer
			disposeViewer();
		}
		else if (viewer != null && !viewer.getControl().isDisposed()) {
			//Update the viewer
			updateViewer(view, g, clip, res, composite);
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}