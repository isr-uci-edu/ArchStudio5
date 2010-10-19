package org.archstudio.myx.fw;

import java.lang.reflect.InvocationTargetException;

public class MyxBrickLoaderException extends Exception implements java.io.Serializable {

	public MyxBrickLoaderException(ClassNotFoundException cause) {
		super("Myx brick loader class not found", cause);
	}

	public MyxBrickLoaderException(NoSuchMethodException cause) {
		super("Can't find brick loader constructor", cause);
	}

	public MyxBrickLoaderException(IllegalAccessException cause) {
		super("Illegal access when creating brick loader", cause);
	}

	public MyxBrickLoaderException(InstantiationException cause) {
		super("Instantiation exception when creating brick loader", cause);
	}

	public MyxBrickLoaderException(InvocationTargetException cause) {
		super("Constructor invocation failed when creating brick loader", cause);
	}

}
