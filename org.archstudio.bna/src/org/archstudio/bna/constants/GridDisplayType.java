package org.archstudio.bna.constants;

public enum GridDisplayType {
	NONE("None"), //
	DOTS_AT_CORNERS("Dots at Corners"), //
	CROSSES_AT_CORNERS("Crosses at Corners"), //
	DOTTED_LINES("Dotted Lines"), //
	SOLID_LINES("Solid Lines");

	private final String description;

	private GridDisplayType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return description;
	}
}
