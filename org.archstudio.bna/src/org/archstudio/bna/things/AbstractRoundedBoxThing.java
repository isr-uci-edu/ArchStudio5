package org.archstudio.bna.things;

import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public abstract class AbstractRoundedBoxThing extends AbstractBoxThing implements IHasMutableRoundedCorners {

	public AbstractRoundedBoxThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setCornerSize(new Dimension(0, 0));
	}

	@Override
	public Dimension getCornerSize() {
		Dimension d = get(CORNER_SIZE_KEY);
		if (!d.isEmpty()) {
			Rectangle r = getRaw(IHasBoundingBox.BOUNDING_BOX_KEY);
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
