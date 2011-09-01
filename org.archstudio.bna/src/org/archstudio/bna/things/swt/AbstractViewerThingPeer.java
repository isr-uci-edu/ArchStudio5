package org.archstudio.bna.things.swt;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.eclipse.draw2d.Graphics;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;

public abstract class AbstractViewerThingPeer<T extends AbstractViewerThing, C extends Control, V extends Viewer>
		extends AbstractControlThingPeer<T, C> {

	protected V viewer = null;

	private AbstractViewerThingPeer(T thing) {
		super(thing);
	}

	@SuppressWarnings("unchecked")
	protected final C createControl(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r){
		return (C) viewer.getControl();
	}
	
	protected abstract V createViewer(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r);

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {
		
		if(viewer == null){
			viewer = createViewer(view, cm, g, r);
		}
		
		super.draw(view, cm, g, r);
	}
	
}
