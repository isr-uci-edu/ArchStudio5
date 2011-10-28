package org.archstudio.xadl.myx;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.myx.java.MyxJavaClassBrickDescription;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class MyxJavaClassBrickDescriptionFromXadl implements IMyxBrickDescriptionFromXadl {

	@Override
	public IMyxBrickDescription parse(IXArchADT xarch, ObjRef brickRef) throws MyxUnsupportedBrickDescriptionException {
		ObjRef javaImplementationRef = XadlUtils.getImplementation(xarch, brickRef,
				Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION);
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
