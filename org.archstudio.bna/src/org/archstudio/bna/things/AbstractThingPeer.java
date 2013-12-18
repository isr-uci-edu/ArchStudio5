package org.archstudio.bna.things;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.jogl.IJOGLResources;
import org.archstudio.bna.ui.swt.ISWTResources;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractThingPeer<T extends IThing> implements IThingPeer<T> //
/* , IHasShadowPeer<T> <-- implement in your peer if you want shadows */{

	protected final T t;
	protected final IBNAView view;
	protected final IBNAModel model;
	protected final ICoordinateMapper cm;

	public AbstractThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		this.t = thing;
		this.view = view;
		this.model = view.getBNAWorld().getBNAModel();
		this.cm = cm;
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		return true;
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, IJOGLResources r) {
	}

	@Override
	public void draw(GC gc, Rectangle localBounds, ISWTResources r) {
	}

	public boolean drawShadow(Rectangle localBounds, IUIResources r) {
		return true;
	}

	public void drawShadow(GL2 gl, Rectangle localBounds, IUIResources r) {
	}

	public void drawShadow(GC gc, Rectangle localBounds, IUIResources r) {
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return false;
	}

}
