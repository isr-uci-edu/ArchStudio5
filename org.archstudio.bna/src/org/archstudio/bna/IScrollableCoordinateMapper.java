package org.archstudio.bna;

import org.eclipse.swt.widgets.Canvas;

/**
 * A tagging interface to indicate that an {@link ICoordinateMapper} is blit scrollable.
 * 
 * @see {@link Canvas#scroll(int, int, int, int, int, int, boolean)}
 */
public interface IScrollableCoordinateMapper extends IMutableCoordinateMapper {
}
