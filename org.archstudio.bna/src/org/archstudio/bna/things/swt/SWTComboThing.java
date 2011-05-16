package org.archstudio.bna.things.swt;

public class SWTComboThing extends SWTListThing {

	public SWTComboThing() {
		this(null);
	}

	public SWTComboThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAllowsArbitraryInput(true);
	}
}
