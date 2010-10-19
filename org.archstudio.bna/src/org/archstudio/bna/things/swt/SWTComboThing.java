package org.archstudio.bna.things.swt;

import org.archstudio.bna.*;

public class SWTComboThing extends AbstractSWTOptionSelectionThing {

	public SWTComboThing() {
		this(BNAUtils.generateUID(SWTComboThing.class.getName()));
	}

	public SWTComboThing(String id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setAllowsArbitraryInput(true);
	}

	public void setAllowsArbitraryInput(boolean allowsArbitraryInput) {
		setProperty("allowsArbitraryInput", allowsArbitraryInput);
	}

	public boolean getAllowsArbitraryInput() {
		return getProperty("allowsArbitraryInput");
	}
}
