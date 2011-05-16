package org.archstudio.bna.things.swt;

import org.archstudio.bna.facets.IHasMutableText;

public class SWTTextThing extends AbstractSWTThing implements IHasMutableText {

	public SWTTextThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setText("");
	}

	public String getText() {
		return (String) get(TEXT_KEY);
	}

	public void setText(String text) {
		put(TEXT_KEY, text);
	}
}
