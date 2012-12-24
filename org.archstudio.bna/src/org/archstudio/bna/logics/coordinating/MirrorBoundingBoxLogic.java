package org.archstudio.bna.logics.coordinating;

import java.awt.Insets;

import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

public class MirrorBoundingBoxLogic extends AbstractThingLogic {

	MirrorValueLogic mvl = null;

	public MirrorBoundingBoxLogic() {
	}

	protected void init() {
		super.init();
		mvl = addThingLogic(MirrorValueLogic.class);
	}

	public void mirrorBoundingBox(IHasBoundingBox fromThing, IHasMutableBoundingBox toThing, final Insets insets) {
		mvl.mirrorValue(fromThing, IHasBoundingBox.BOUNDING_BOX_KEY, toThing, IHasBoundingBox.BOUNDING_BOX_KEY,
				new Function<Rectangle, Rectangle>() {

					public Rectangle apply(Rectangle input) {
						input.x += insets.left;
						input.y += insets.top;
						input.width -= insets.left + insets.right;
						input.height -= insets.top + insets.bottom;
						return input;
					}
				});
	}

	public void unmirrorBoundingBox(IHasMutableBoundingBox toThing) {
		mvl.unmirror(toThing, IHasBoundingBox.BOUNDING_BOX_KEY);
	}
}
