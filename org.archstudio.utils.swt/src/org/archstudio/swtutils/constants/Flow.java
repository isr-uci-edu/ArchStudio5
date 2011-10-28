package org.archstudio.swtutils.constants;

public enum Flow {
	NONE("None"), IN("In"), OUT("Out"), INOUT("In-Out");

	private final String description;

	private Flow(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}