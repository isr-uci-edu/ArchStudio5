package org.archstudio.bna.things;

import java.awt.Dimension;
import java.util.Collections;
import java.util.Set;

import org.archstudio.bna.facets.IHasShapeKeys;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

import com.google.common.collect.Sets;

@NonNullByDefault
public abstract class AbstractRelativeMovableThing extends AbstractThing implements IRelativeMovable, IHasShapeKeys {

	public AbstractRelativeMovableThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public abstract void moveRelative(Point worldDelta);

	@Override
	public void moveRelative(Dimension worldDelta) {
		moveRelative(new Point(worldDelta.width, worldDelta.height));
	}

	@Override
	public void addShapeModifyingKey(IThingKey<?> key) {
		Set<IThingKey<?>> keys = get(SHAPE_MODIFYING_KEYS_KEY, Sets.<IThingKey<?>> newHashSet());
		keys.add(key);
		set(SHAPE_MODIFYING_KEYS_KEY, keys);
	}

	@Override
	public Set<IThingKey<?>> getShapeModifyingKeys() {
		return get(SHAPE_MODIFYING_KEYS_KEY, Collections.<IThingKey<?>> emptySet());
	}

	@Override
	public boolean isShapeModifyingKey(IThingKey<?> key) {
		return getShapeModifyingKeys().contains(key);
	}
}