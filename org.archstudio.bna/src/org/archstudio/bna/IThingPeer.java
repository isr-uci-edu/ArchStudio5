package org.archstudio.bna;

import javax.media.opengl.GL2;

import org.archstudio.bna.ui.IUIResources;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

public interface IThingPeer<T extends IThing> {

	/**
	 * Determine if the given point is "in" the peer's associated {@link IThing}. That is, if the user clicked the mouse
	 * on the given point, would they reasonably expect it to have hit this {@link IThing}?
	 * 
	 * @param location
	 *            The {@link ICoordinate location} of the point.
	 * 
	 * @return <code>true</code> if the point is "in" the {@link IThing}, <code>false</code> otherwise.
	 */
	public boolean isInThing(ICoordinate location);

	public boolean draw(Rectangle localBounds, IUIResources r);

	public void draw(GL2 gl, Rectangle localBounds, IUIResources r);

	public void draw(GC gc, Rectangle localBounds, IUIResources r);

	public void dispose();
}