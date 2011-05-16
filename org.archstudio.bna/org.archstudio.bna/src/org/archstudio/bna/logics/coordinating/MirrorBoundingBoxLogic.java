package org.archstudio.bna.logics.coordinating;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;

public class MirrorBoundingBoxLogic extends
		AbstractPropagateValueLogic<IHasBoundingBox, IHasMutableBoundingBox, Insets> {

	public MirrorBoundingBoxLogic() {
		super(IHasBoundingBox.class, IHasMutableBoundingBox.class);
	}

	public void mirrorBoundingBox(IHasBoundingBox fromThing, Insets insets, IHasMutableBoundingBox... toThings) {
		setPropagate(fromThing, IHasBoundingBox.BOUNDING_BOX_KEY, IHasBoundingBox.BOUNDING_BOX_KEY, insets, toThings);
	}

	@Override
	protected void doPropagation(IBNAModel model, final IHasBoundingBox fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<IHasBoundingBox, ?, ?> fromThingEvent, final Insets insets,
			final IHasMutableBoundingBox toThing, IThingKey<?> toKey,
			@Nullable ThingEvent<IHasMutableBoundingBox, ?, ?> toThingEvent) {

		toThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Rectangle bb = fromThing.getBoundingBox();
				bb.crop(insets);
				toThing.setBoundingBox(bb);
			}
		});
	}
}
