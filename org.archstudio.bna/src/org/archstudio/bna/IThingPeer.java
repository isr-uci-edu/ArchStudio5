package org.archstudio.bna;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IThingPeer<T extends IThing> {

	/**
	 * Determine if the given point (in {@link ICoordinateMapper world
	 * coordinates}) is "in" the peer's associated {@link IThing}. That is, if
	 * the user clicked the mouse on the given world-point, would they
	 * reasonably expect to have hit this {@link IThing}?
	 * 
	 * @param view
	 *            The {@link IBNAView} that sets the context for making the
	 *            determination.
	 * @param location
	 *            The {@link ICoordinate location} of the point.
	 * @return <code>true</code> if the point is "in" the {@link IThing},
	 *         <code>false</code> otherwise.
	 */
	public boolean isInThing(IBNAView view, ICoordinateMapper cm, ICoordinate location);

	public Rectangle getLocalBounds(IBNAView view, ICoordinateMapper cm, IResources r);

	public void draw(IBNAView view, ICoordinateMapper cm, IResources r, Graphics g);

	public void dispose();
}