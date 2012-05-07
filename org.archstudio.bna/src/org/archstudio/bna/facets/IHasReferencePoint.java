package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.eclipse.draw2d.geometry.Point;

public interface IHasReferencePoint extends IThing {

	public Point getReferencePoint();

}