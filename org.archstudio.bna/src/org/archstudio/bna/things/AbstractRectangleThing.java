package org.archstudio.bna.things;

import java.util.Set;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMinimumSize;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableLocalInsets;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.collect.Sets;

public abstract class AbstractRectangleThing extends AbstractRelativeMovableThing implements IHasMutableBoundingBox,
		IHasMinimumSize, IRelativeMovable, IHasMutableLocalInsets, IIsSticky {

	public AbstractRectangleThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setMinimumSize(new Dimension(5, 5));
		setBoundingBox(new Rectangle(0, 0, 10, 10));
		addEdgeModifyingKey(MINIMUM_SIZE_KEY);
		addEdgeModifyingKey(BOUNDING_BOX_KEY);
	}

	@Override
	public Dimension getMinimumSize() {
		return get(MINIMUM_SIZE_KEY);
	}

	@Override
	public void setMinimumSize(final Dimension minimumDimension) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				set(MINIMUM_SIZE_KEY, minimumDimension);
				Rectangle r = getBoundingBox();
				if (r != null) {
					setBoundingBox(r);
				}
			}
		});
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}

	@Override
	public void setBoundingBox(final Rectangle r) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				BNAUtils.normalizeRectangle(r);
				Dimension minDimension = getMinimumSize();
				if (minDimension != null) {
					if (r.width < minDimension.width) {
						r.width = minDimension.width;
					}
					if (r.height < minDimension.height) {
						r.height = minDimension.height;
					}
				}
				set(BOUNDING_BOX_KEY, r);
			}
		});
	}

	@Override
	public Point getReferencePoint() {
		return getBoundingBox().getCenter();
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Rectangle bounds = getBoundingBox();
				bounds.translate(worldDelta);
				setBoundingBox(bounds);
			}
		});
	}

	@Override
	public Insets getLocalInsets() {
		return get(LOCAL_INSETS_KEY);
	}

	@Override
	public void setLocalInsets(Insets insets) {
		set(LOCAL_INSETS_KEY, insets);
	}

	protected void addEdgeModifyingKey(final IThingKey<?> key) {
		synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Set<IThingKey<?>> keys = Sets.newHashSet(getStickyModifyingKeys());
				keys.add(key);
				set(STICKY_MODIFYING_KEYS_KEY, keys);
			}
		});
	}

	@Override
	public Iterable<IThingKey<?>> getStickyModifyingKeys() {
		return get(STICKY_MODIFYING_KEYS_KEY);
	}

	@Override
	public PrecisionPoint getStickyPointNear(StickyMode stickyMode, Point nearPoint, Point lastStuckPoint) {
		Rectangle bb = getBoundingBox();
		switch (stickyMode) {
		case CENTER:
			return new PrecisionPoint(bb.getCenter());
		case EDGE:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint);
		case EDGE_FROM_CENTER:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint, bb.getCenter());
		}
		throw new IllegalArgumentException(stickyMode.name());
	}
}
