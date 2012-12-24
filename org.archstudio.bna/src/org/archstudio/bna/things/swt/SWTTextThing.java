package org.archstudio.bna.things.swt;

import org.archstudio.bna.facets.IHasMutableText;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public class SWTTextThing extends AbstractControlThing implements IHasMutableText {

	public SWTTextThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setText("");
	}

	@Override
	public String getText() {
		return get(TEXT_KEY, "");
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}
}
