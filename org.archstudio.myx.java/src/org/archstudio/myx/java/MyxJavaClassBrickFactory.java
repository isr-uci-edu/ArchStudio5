package org.archstudio.myx.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxBrickCreationException;

public class MyxJavaClassBrickFactory implements IMyxBrickFactory {

	public MyxJavaClassBrickFactory() {
	}

	@Override
	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
		try {
			MyxJavaClassBrickDescription desc = (MyxJavaClassBrickDescription) brickDescription;
			Class<?> mainBrickClass = Class.forName(desc.getMainBrickClassName());
			return create(name, brickDescription, initializationData, mainBrickClass);
		}
		catch (ClassNotFoundException e) {
			throw new MyxBrickCreationException("Can't find brick class", e);
		}
	}

	protected IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData, Class<?> mainBrickClass) throws MyxBrickCreationException {
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
