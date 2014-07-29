package org.archstudio.bna.constants;

import org.archstudio.swtutils.constants.LineStyle;

public enum GridDisplayType {
	NONE("None", LineStyle.NONE), //
	DOTS_AT_CORNERS("Dots at Corners", LineStyle.NONE), //
	CROSSES_AT_CORNERS("Crosses at Corners", LineStyle.NONE), //
	DOTTED_LINES("Dotted Lines", LineStyle.DOT), //
	SOLID_LINES("Solid Lines", LineStyle.SOLID);

	private final String description;
	private final LineStyle lineStyle;

	private GridDisplayType(String description, LineStyle lineStyle) {
		this.description = description;
		this.lineStyle = lineStyle;
	}

	public String getDescription() {
		return description;
	}

	public LineStyle getLineStyle() {
		return lineStyle;
	}

	@Override
	public String toString() {
		return description;
	}
}
