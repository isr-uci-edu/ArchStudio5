package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.base.Function;

public class MirrorBoundingBoxLogic extends AbstractMirrorValueLogic<IHasBoundingBox, IHasMutableBoundingBox> {

	public MirrorBoundingBoxLogic() {
		super(IHasBoundingBox.class, IHasMutableBoundingBox.class);
	}

	public void mirrorBoundingBox(IHasBoundingBox fromThing, IHasMutableBoundingBox toThing, final Insets insets) {
		mirrorValue(fromThing, IHasBoundingBox.BOUNDING_BOX_KEY, toThing, IHasBoundingBox.BOUNDING_BOX_KEY,
				new Function<Rectangle, Rectangle>() {
					@Override
					public Rectangle apply(Rectangle input) {
						return input.getCropped(insets);
					}
				});
	}

	public void unmirrorBoundingBox(IHasMutableBoundingBox toThing) {
		unpropagate(toThing, IHasBoundingBox.BOUNDING_BOX_KEY);
	}

}
