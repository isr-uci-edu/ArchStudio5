package org.archstudio.myx.fw;

public class MyxJavaClassBrickLoader implements IMyxBrickLoader {

	protected IMyxClassManager classManager;

	public MyxJavaClassBrickLoader() {
		super();
	}

	public void setRuntime(IMyxRuntime runtime) {
	}

	public void setClassManager(IMyxClassManager manager) {
		this.classManager = manager;
	}

	public IMyxBrickFactory load(IMyxBrickDescription brickDescription) throws MyxBrickNotFoundException, MyxBrickLoadFailedException,
	        MyxUnsupportedBrickDescriptionException {
		if (!(brickDescription instanceof MyxJavaClassBrickDescription)) {
			throw new MyxUnsupportedBrickDescriptionException();
		}
		MyxJavaClassBrickDescription jcbd = (MyxJavaClassBrickDescription) brickDescription;
		String mainBrickClassName = jcbd.getMainBrickClassName();

		try {
			Class<?> c = classManager.classForName(mainBrickClassName);
			//Class c = Class.forName(mainBrickClassName);
			return new MyxJavaClassBrickFactory(c);
		}
		catch (ClassNotFoundException cnfe) {
			throw new MyxBrickNotFoundException(mainBrickClassName, cnfe);
		}
	}

}
