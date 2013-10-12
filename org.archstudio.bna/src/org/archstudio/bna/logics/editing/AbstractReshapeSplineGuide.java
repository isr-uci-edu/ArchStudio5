package org.archstudio.bna.logics.editing;

import java.util.Arrays;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Sets;

abstract public class AbstractReshapeSplineGuide implements IReshapeSplineGuide {

	private final Set<IThingKey<Point>> pointKeys;

	@SafeVarargs
	public AbstractReshapeSplineGuide(IThingKey<Point>... pointKeys) {
		this.pointKeys = Sets.newHashSet(Arrays.asList(pointKeys));
	}

	abstract protected boolean isRelevantPointsThing(IThing pointsThing);

	abstract protected boolean isRelevantStickyThing(IIsSticky stickyThing);

	@Override
	public boolean shouldBeStuck(IThing pointsThing, IThingKey<Point> pointKey) {
		if (pointKeys.contains(pointKey)) {
			return isRelevantPointsThing(pointsThing);
		}
		return false;
	}

	@Override
	public @Nullable
	StickyMode getStickyMode(IThing pointsThing, IIsSticky stickyThing, IThingKey<Point> pointKey) {
		return isRelevantPointsThing(pointsThing) && isRelevantStickyThing(stickyThing) ? StickyMode.EDGE_FROM_CENTER
				: null;
	}
}
