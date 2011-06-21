package org.archstudio.bna.logics.editing;

import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.graphics.RGB;

public class StickySplineEndpointsColorLogic extends AbstractThingLogic implements ISelectionTrackingListener,
		IEndpointMovedListener {
	protected SelectionTrackingLogic stl = null;
	protected SplineReshapeHandleLogic srhl = null;

	protected static final RGB RGB_BLUE = new RGB(0, 0, 255);
	protected static final RGB RGB_GREEN = new RGB(0, 255, 0);

	public StickySplineEndpointsColorLogic(SelectionTrackingLogic stl, SplineReshapeHandleLogic srhl) {
		this.stl = stl;
		this.srhl = srhl;
	}

	protected void init() {
		stl.addSelectionTrackingListener(this);
		srhl.addEndpointMovedListener(this);
	}

	protected void destroy() {
		srhl.removeEndpointMovedListener(this);
		stl.removeSelectionTrackingListener(this);
	}

	public void selectionChanged(SelectionChangedEvent evt) {
		checkSpline();
	}

	public void endpointMoved(EndpointMovedEvent evt) {
		checkSpline();
	}

	public void checkSpline() {
		ISplineReshapable splineThing = srhl.getTargetThing();
		if (splineThing != null) {
			if (splineThing instanceof IHasStickyEndpoints) {
				SplineReshapeHandlesAssembly reshapeHandles = srhl.getReshapeHandles();
				if (reshapeHandles != null) {
					ReshapeHandleAssembly[] rhs = reshapeHandles.getAllReshapeHandleAssemblies();
					String st1ID = ((IHasStickyEndpoints) splineThing).getEndpoint1StuckToThingID();
					if (st1ID != null) {
						rhs[0].getReshapeHandleThing().setColor(RGB_GREEN);
					}
					else {
						rhs[0].getReshapeHandleThing().setColor(RGB_BLUE);
					}
					String st2ID = ((IHasStickyEndpoints) splineThing).getEndpoint2StuckToThingID();
					if (st2ID != null) {
						rhs[rhs.length - 1].getReshapeHandleThing().setColor(RGB_GREEN);
					}
					else {
						rhs[rhs.length - 1].getReshapeHandleThing().setColor(RGB_BLUE);
					}
				}
			}
		}
	}
}
