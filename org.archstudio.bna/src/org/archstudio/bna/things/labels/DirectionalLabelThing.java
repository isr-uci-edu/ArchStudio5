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

	protected void initProperties() {
		super.initProperties();
		setOrientation(Orientation.NONE);
		setFlow(Flow.NONE);
		setColor(new RGB(0, 0, 0));
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation o) {
		set(ORIENTATION_KEY, o);
	}

	public Flow getFlow() {
		return get(FLOW_KEY);
	}

	public void setFlow(Flow f) {
		set(FLOW_KEY, f);
	}
}
