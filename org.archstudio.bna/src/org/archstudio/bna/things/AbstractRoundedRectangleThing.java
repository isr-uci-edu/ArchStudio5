package org.archstudio.bna.things;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractRoundedRectangleThing extends AbstractRectangleThing implements IHasMutableRoundedCorners {

	public AbstractRoundedRectangleThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setCornerSize(new Dimension(0, 0));
		addShapeModifyingKey(CORNER_SIZE_KEY);
	}

	@Override
	public Dimension getCornerSize() {
		Dimension d = get(CORNER_SIZE_KEY);
		if (d.width != 0 || d.height != 0) {
			Rectangle r = get(IHasBoundingBox.BOUNDING_BOX_KEY);
			d.width = Math.min(d.width, r.width / 2);
			d.height = Math.min(d.height, r.height / 2);
		}
		return d;
	}

	@Override
	public void setCornerSize(Dimension dimension) {
		set(CORNER_SIZE_KEY, dimension);
	}

}
