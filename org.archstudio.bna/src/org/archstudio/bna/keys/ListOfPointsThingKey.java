package org.archstudio.bna.keys;

import java.util.Collections;
import java.util.List;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.draw2d.geometry.Point;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ListOfPointsThingKey<D> extends AbstractCollectionThingKey<D, List<Point>, Point> {

	private static final Function<Point, Point> CLONE_POINT = new Function<Point, Point>() {
		@Override
		public Point apply(Point input) {
			return input.getCopy();
		}
	};

	public static final <D> IThingKey<List<Point>> create(D keyData) {
		return new ListOfPointsThingKey<D>(keyData, true);
	}

	protected ListOfPointsThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected List<Point> cloneReadOnly(List<Point> value) {
		return Collections.unmodifiableList(Lists.transform(value, CLONE_POINT));
	}
}
