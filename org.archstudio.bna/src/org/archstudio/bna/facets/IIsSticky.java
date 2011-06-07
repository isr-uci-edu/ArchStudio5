package org.archstudio.bna.facets;

import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.keys.SetThingKey;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

public interface IIsSticky extends IThing {

	public static final IThingKey<Set<IThingKey<?>>> STICKY_MODIFYING_KEYS_KEY = SetThingKey
			.create("stickyModifyingEvent");

	public Iterable<IThingKey<?>> getStickyModifyingKeys();

	public PrecisionPoint getStickyPointNear(StickyMode stickyMode, Point nearPoint, Point refPoint);
}
