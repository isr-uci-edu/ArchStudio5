package org.archstudio.sysutils;

public abstract class Finally implements AutoCloseable {

	@Override
	public abstract void close();

}
