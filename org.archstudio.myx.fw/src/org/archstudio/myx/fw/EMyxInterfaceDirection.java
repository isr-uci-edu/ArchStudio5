package org.archstudio.myx.fw;

public enum EMyxInterfaceDirection {
	IN("in"), OUT("out");

	private String stringRepresentation;

	private EMyxInterfaceDirection(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}

	public static EMyxInterfaceDirection fromString(String s) {
		for (EMyxInterfaceDirection dir : values()) {
			if (s.equals(dir.toString())) {
				return dir;
			}
		}
		throw new IllegalArgumentException("Invalid string representation: " + s);
	}
}
