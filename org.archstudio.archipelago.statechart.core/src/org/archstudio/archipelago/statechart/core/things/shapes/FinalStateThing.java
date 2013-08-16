package org.archstudio.archipelago.statechart.core.things.shapes;

import org.archstudio.bna.things.shapes.EllipseThing;
import org.eclipse.swt.graphics.RGB;

public class FinalStateThing extends EllipseThing {

	public FinalStateThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(32, 32, 32));
		setLineWidth(2);
	}

}
