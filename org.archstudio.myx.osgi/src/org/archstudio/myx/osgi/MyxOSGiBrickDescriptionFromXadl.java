package org.archstudio.myx.osgi;

import org.archstudio.aim.IMyxBrickDescriptionFromXadl;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;

public class MyxOSGiBrickDescriptionFromXadl implements IMyxBrickDescriptionFromXadl {

	@Override
	public IMyxBrickDescription parse(IXArchADT xarch, ObjRef brickRef) throws MyxUnsupportedBrickDescriptionException {

		ObjRef osgiImplRef = XadlUtils.getImplementation(xarch, brickRef,
				Osgiimplementation_3_0Package.Literals.OS_GI_IMPLEMENTATION);
		if (osgiImplRef == null) {
			return null;
		}
		String osgiBundleName = (String) xarch.get(osgiImplRef, "bundle");
		if (osgiBundleName == null) {
			throw new MyxUnsupportedBrickDescriptionException("Osgi implementation lacks bundle name"
					+ XadlUtils.getName(xarch, brickRef));
		}

		ObjRef javaImplementationRef = XadlUtils.getJavaImplementation(xarch, brickRef);
		if (javaImplementationRef == null) {
			throw new MyxUnsupportedBrickDescriptionException("Osgi implementation lacks java implementation on brick "
					+ XadlUtils.getName(xarch, brickRef));
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

		return new MyxOSGiBrickDescription(mainClassName, osgiBundleName);
	}

}
