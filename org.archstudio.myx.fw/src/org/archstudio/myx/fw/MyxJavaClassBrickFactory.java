package org.archstudio.myx.fw;

import java.lang.reflect.*;

public class MyxJavaClassBrickFactory implements IMyxBrickFactory {

	protected Class<?> mainBrickClass;

	public MyxJavaClassBrickFactory(Class<?> mainBrickClass) {
		this.mainBrickClass = mainBrickClass;
	}

	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription, IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
		try {
			Constructor<?> constructor = mainBrickClass.getConstructor(new Class[0]);
			Object o = constructor.newInstance(new Object[0]);
			return (IMyxBrick) o;
		}
		catch (NoSuchMethodException nsme) {
			throw new MyxBrickCreationException("Can't find brick constructor", nsme);
		}
		catch (IllegalAccessException iae) {
			throw new MyxBrickCreationException("Illegal access when creating brick", iae);
		}
		catch (InstantiationException ie) {
			throw new MyxBrickCreationException("Instantiation exception when creating brick", ie);
		}
		catch (InvocationTargetException ite) {
			throw new MyxBrickCreationException("Constructor invocation failed when creating brick", ite);
		}
	}

}
