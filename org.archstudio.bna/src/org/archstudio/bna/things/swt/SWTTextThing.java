package org.archstudio.bna.things.swt;

import org.archstudio.bna.facets.IHasMutableText;

public class SWTTextThing extends AbstractControlThing implements IHasMutableText {

	public SWTTextThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setText("[text]");
	}

	@Override
	public String getText() {
		return get(TEXT_KEY);
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}
}
