package org.archstudio.myx.fw;

class MyxBasicName implements IMyxName {

	protected String name;

	public MyxBasicName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name;
	}

	public int hashCode() {
		return name.hashCode();
	}

	public boolean equals(Object o) {
		return MyxUtils.classeq(this, o) && MyxUtils.nulleq(this.name, ((MyxBasicName) o).name);
	}

}
