package org.archstudio.bna.logics.editing;

import java.util.Collection;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;

import com.google.common.primitives.Ints;

abstract public class AbstractReshapeSplineGuide implements IReshapeSplineGuide {

	protected static final Object normalizeIndex(IHasPoints thing, int pointIndex) {
		return pointIndex < 0 ? thing.getPointsSize() + pointIndex : pointIndex;
	}

	private final Collection<Integer> pointIndexes;

	public AbstractReshapeSplineGuide(int... pointIndex) {
		this.pointIndexes = Ints.asList(pointIndex);
	}

	abstract protected boolean isRelevantPointsThing(IHasPoints pointsThing);

	abstract protected boolean isRelevantStickyThing(IIsSticky stickyThing);

	@Override
	public boolean shouldBeStuck(IHasPoints thing, int pointIndex) {
		for (int i : pointIndexes) {
			if (normalizeIndex(thing, pointIndex) == normalizeIndex(thing, i)) {
				return isRelevantPointsThing(thing);
			}
		}
		return false;
	}

	@Override
	public StickyMode getStickyMode(IHasPoints pointsThing, IIsSticky stickyThing, int pointIndex) {
		return isRelevantPointsThing(pointsThing) && isRelevantStickyThing(stickyThing) ? StickyMode.EDGE_FROM_CENTER
				: null;
	}
}
