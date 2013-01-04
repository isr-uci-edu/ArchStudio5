package org.archstudio.bna;

import javax.media.opengl.GL2;

import org.eclipse.swt.graphics.Rectangle;

public interface IThingPeer<T extends IThing> {

	/**
	 * Determine if the given point (in {@link ICoordinateMapper world coordinates}) is "in" the peer's associated
	 * {@link IThing}. That is, if the user clicked the mouse on the given world-point, would they reasonably expect to
	 * have hit this {@link IThing}?
	 * 
	 * @param view
	 *            The {@link IBNAView} that sets the context for making the determination.
	 * @param cm
	 *            The {@link ICoordinateMapper} used to convert between world and local coordinates.
	 * @param location
	 *            The {@link ICoordinate location} of the point.
	 * @return <code>true</code> if the point is "in" the {@link IThing}, <code>false</code> otherwise.
	 */
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location);

	public void draw(IBNAView view, ICoordinateMapper cm, GL2 gl, Rectangle clip, IResources r);

	public void dispose();
}