package org.archstudio.bna;

import org.eclipse.swt.graphics.GC;

public interface IThingPeer {

	/**
	 * Draws the peer's associated Thing on the given GC, using the given
	 * CoordinateMapper to map all world coordinates to local coordinates if
	 * necessary.
	 * 
	 * @param view
	 *            The view that sets the context for drawing the Thing
	 * @param g
	 *            The GC on which to draw the Thing.
	 */
	public void draw(IBNAView view, GC g);

	/**
	 * Determine if the given point (in world coordinates) is "in" the peer's
	 * associated Thing. That is, if the user clicked the mouse on the given
	 * world-point, would they reasonably expect to have hit this Thing?
	 * 
	 * @param view
	 *            The view that sets the context for making the determination.
	 * @param worldX
	 *            The X-coordinate of the world point to test.
	 * @param worldY
	 *            The Y-coordinate of the world point to test.
	 * @return <code>true</code> if the point is "in" the Thing,
	 *         <code>false</code> otherwise.
	 */
	public boolean isInThing(IBNAView view, int worldX, int worldY);

	/**
	 * Determine the local bounding box of the peer's associated Thing. This is
	 * used mostly for drawing optimization and as such is an optional
	 * operation; Things that return <code>null</code> from this routine will
	 * not be optimized. As such, well-behaved Things should not return
	 * <code>null</code>.
	 * 
	 * @param g
	 *            The GC context in which to make the determination.
	 * @param cm
	 *            The Coordinate mapper used to map world coordinates to local
	 *            coordinates.
	 * @return a <code>Rectangle</code> containing the Thing's bounding box in
	 *         local coordinates.
	 */
	//public Rectangle getLocalBoundingBox(IBNAContext context, GC g, ICoordinateMapper cm);

}