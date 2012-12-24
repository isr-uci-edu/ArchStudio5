package org.archstudio.bna.things.labels;

import org.archstudio.bna.facets.IHasMinimumSize;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFlow;
import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.things.AbstractRectangleThing;
import org.archstudio.swtutils.constants.Flow;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.RGB;

public class DirectionalLabelThing extends AbstractRectangleThing implements IHasMinimumSize, IHasMutableColor,
		IHasMutableOrientation, IHasMutableFlow {

	public DirectionalLabelThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setOrientation(Orientation.NONE);
		setFlow(Flow.NONE);
		setColor(new RGB(0, 0, 0));
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	@Override
	public void setOrientation(Orientation o) {
		set(ORIENTATION_KEY, o);
	}

	@Override
	public Flow getFlow() {
		return get(FLOW_KEY);
	}

	@Override
	public void setFlow(Flow f) {
		set(FLOW_KEY, f);
	}
}
