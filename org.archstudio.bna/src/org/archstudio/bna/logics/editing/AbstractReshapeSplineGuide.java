package org.archstudio.bna.logics.editing;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Set;

import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.keys.IThingKey;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Sets;

abstract public class AbstractReshapeSplineGuide implements IReshapeSplineGuide {

	private final Set<IThingKey<Point2D>> pointKeys;

	@SafeVarargs
	public AbstractReshapeSplineGuide(IThingKey<Point2D>... pointKeys) {
		this.pointKeys = Sets.newHashSet(Arrays.asList(pointKeys));
	}

	abstract protected boolean isRelevantPointsThing(IThing pointsThing);

	abstract protected boolean isRelevantStickyThing(IHasStickyShape stickyThing);

	@Override
	public boolean shouldBeStuck(IThing pointsThing, IThingKey<Point2D> pointKey) {
		if (pointKeys.contains(pointKey)) {
			return isRelevantPointsThing(pointsThing);
		}
		return false;
	}

	@Override
	public @Nullable
	StickyMode getStickyMode(IThing pointsThing, IHasStickyShape stickyThing, IThingKey<Point2D> pointKey) {
		return isRelevantPointsThing(pointsThing) && isRelevantStickyThing(stickyThing) ? StickyMode.EDGE_FROM_CENTER
				: null;
	}
}
