package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasMutableLoopPoint {
	public static final IThingKey<Point> LOOP_POINT_KEY = CloneThingKey.create("loopPoint",
			AbstractCloneThingKey.point());

	public void setLoopPoint(@Nullable Point loop);

	public @Nullable
	Point getLoopPoint();
}
