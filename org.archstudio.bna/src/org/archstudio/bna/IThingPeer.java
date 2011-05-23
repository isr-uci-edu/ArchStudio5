package org.archstudio.bna;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IThingPeer<T extends IThing> {

	/**
	 * Draws the peer's associated {@link IThing} on the given {@link Graphics}, using the given {@link IBNAView} to map
	 * all world coordinates to local coordinates if necessary.
	 * 
	 * @param view
	 *            The {@link IBNAView} that sets the context for drawing the {@link IThing}.
	 * @param cm
	 *            TODO
	 * @param g
	 *            The {@link Graphics} on which to draw the Thing.
	 * @param localClip
	 *            TODO
	 * @param worldClip
	 *            TODO
	 * @param res
	 *            The {@link IResources} to use for creating resources.
	 */
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, IRegion localClip, IRegion worldClip);

	/**
	 * Determine if the given point (in {@link ICoordinateMapper world coordinates}) is "in" the peer's associated
	 * {@link IThing}. That is, if the user clicked the mouse on the given world-point, would they reasonably expect to
	 * have hit this {@link IThing}?
	 * 
	 * @param view
	 *            The {@link IBNAView} that sets the context for making the determination.
	 * @param location
	 *            TODO
	 * @return <code>true</code> if the point is "in" the {@link IThing}, <code>false</code> otherwise.
	 */
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location);

	public boolean getLocalBounds(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r, Rectangle boundsResult);

	public boolean isOpaqueAndFilled();

	public void dispose();
}