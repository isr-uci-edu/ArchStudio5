package org.archstudio.bna.things.swt;

import org.archstudio.bna.keys.ThingKey;

public class SWTListThing extends SWTTextThing {

	public static final IThingKey<List<String>> OPTIONS_KEY = ListThingKey.create("options");
	public static final IThingKey<Boolean> ALLOWS_ARBITRARY_INPUT_KEY = ThingKey.create("allowsArbitraryInput");

	public SWTListThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setOptions(new String[] { "[No Options]" });
		setAllowsArbitraryInput(false);
	}

	public void setOptions(String[] options) {
		set(OPTIONS_KEY, options);
	}

	public String[] getOptions() {
		return get(OPTIONS_KEY);
	}

	public void setAllowsArbitraryInput(boolean allowsArbitraryInput) {
		set(ALLOWS_ARBITRARY_INPUT_KEY, allowsArbitraryInput);
	}

	public boolean getAllowsArbitraryInput() {
		return get(ALLOWS_ARBITRARY_INPUT_KEY);
	}
}
