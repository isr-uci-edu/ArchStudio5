package org.archstudio.myx.fw;

class MyxBasicName implements IMyxName {

	private static final long serialVersionUID = 7434809370415125742L;

	protected String name;

	public MyxBasicName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return MyxUtils.classeq(this, o) && MyxUtils.nulleq(this.name, ((MyxBasicName) o).name);
	}

}
