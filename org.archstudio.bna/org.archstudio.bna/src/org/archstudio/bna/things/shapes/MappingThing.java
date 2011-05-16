package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.things.MappingEssenceThing;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;

public class MappingThing extends MappingEssenceThing implements IHasMutableColor, IHasMutableLineWidth,
		IHasMutableLineStyle {

	public MappingThing() {
		this(null);
	}

	public MappingThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setLineStyle(SWT.LINE_SOLID);
		setColor(new RGB(0, 0, 0));
		setLineWidth(1);
	}

	public void setColor(RGB c) {
		put(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public int getLineStyle() {
		return get(LINE_STYLE_KEY);
	}

	public void setLineStyle(int lineStyle) {
		put(LINE_STYLE_KEY, lineStyle);
	}

	public int getLineWidth() {
		return get(LINE_WIDTH_KEY);
	}

	public void setLineWidth(int lineWidth) {
		put(LINE_WIDTH_KEY, lineWidth);
	}
}
