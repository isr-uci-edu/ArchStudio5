package org.archstudio.myx.fw;

import java.lang.reflect.InvocationTargetException;

public class MyxClassManagerException extends Exception implements java.io.Serializable {

	private static final long serialVersionUID = -3134413141857370958L;

	public MyxClassManagerException(ClassNotFoundException cause) {
		super("Myx class manager class not found", cause);
	}

	public MyxClassManagerException(NoSuchMethodException cause) {
		super("Can't find class manager constructor", cause);
	}

	public MyxClassManagerException(IllegalAccessException cause) {
		super("Illegal access when creating class manager", cause);
	}

	public MyxClassManagerException(InstantiationException cause) {
		super("Instantiation exception when creating class manager", cause);
	}

	public MyxClassManagerException(InvocationTargetException cause) {
		super("Constructor invocation failed when creating class manager", cause);
	}

}
