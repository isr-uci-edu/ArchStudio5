package org.archstudio.myx.java;

import org.archstudio.aim.IMyxBrickDescriptionFromXadl;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class MyxJavaClassBrickDescriptionFromXadl implements IMyxBrickDescriptionFromXadl {

	@Override
	public IMyxBrickDescription parse(IXArchADT xarch, ObjRef brickRef) throws MyxUnsupportedBrickDescriptionException {
		ObjRef javaImplementationRef = XadlUtils.getJavaImplementation(xarch, brickRef);
		if (javaImplementationRef == null) {
			return null;
		}
		ObjRef mainClassRef = (ObjRef) xarch.get(javaImplementationRef, "mainClass");
		if (mainClassRef == null) {
			throw new MyxUnsupportedBrickDescriptionException("Java implementation lacks main class on brick "
					+ XadlUtils.getName(xarch, brickRef));
		}
		String mainClassName = (String) xarch.get(mainClassRef, "className");
		if (mainClassName == null) {
			throw new MyxUnsupportedBrickDescriptionException("Java implementation lacks main class name on brick "
					+ XadlUtils.getName(xarch, brickRef));
		}

		return new MyxJavaClassBrickDescription(mainClassName);
	}

}
