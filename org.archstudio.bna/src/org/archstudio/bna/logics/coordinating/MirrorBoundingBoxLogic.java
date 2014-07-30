package org.archstudio.bna.logics.coordinating;

import java.awt.Insets;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

public class MirrorBoundingBoxLogic extends AbstractThingLogic {

	protected final MirrorValueLogic mirrorLogic;

	public MirrorBoundingBoxLogic(IBNAWorld world) {
		super(world);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
	}

	public void mirrorBoundingBox(IHasBoundingBox fromThing, IHasBoundingBox toThing, final Insets insets) {
		BNAUtils.checkLock();

		mirrorLogic.mirrorValue(fromThing, IHasBoundingBox.BOUNDING_BOX_KEY, toThing, IHasBoundingBox.BOUNDING_BOX_KEY,
				new Function<Rectangle, Rectangle>() {

					@Override
					public Rectangle apply(Rectangle input) {
						input.x += insets.left;
						input.y += insets.top;
						input.width -= insets.left + insets.right;
						input.height -= insets.top + insets.bottom;
						return input;
					}
				});
	}

	public void unmirrorBoundingBox(IHasBoundingBox toThing) {
		BNAUtils.checkLock();

		mirrorLogic.unmirror(toThing, IHasBoundingBox.BOUNDING_BOX_KEY);
	}
}
