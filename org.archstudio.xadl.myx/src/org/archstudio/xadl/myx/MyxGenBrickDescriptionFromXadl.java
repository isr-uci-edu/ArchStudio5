package org.archstudio.xadl.myx;

import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.MyxUnsupportedBrickDescriptionException;
import org.archstudio.myxgen.eclipse.MyxGenBrickDescription;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package;
import org.archstudio.xadl3.osgiimplementation_3_0.OSGiImplementation;
import org.archstudio.xadl3.osgiimplementation_3_0.Osgiimplementation_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;

public class MyxGenBrickDescriptionFromXadl implements IMyxBrickDescriptionFromXadl {

	@Override
	public IMyxBrickDescription parse(IXArchADT xarch, ObjRef brickRef) throws MyxUnsupportedBrickDescriptionException {

		ObjRef myxGenImplRef = XadlUtils.getImplementation(xarch, brickRef, Myxgen_3_0Package.Literals.MYX_GEN);
		if (myxGenImplRef == null) {
			return null;
		}

		String myxGenBrickID = (String) xarch.get(myxGenImplRef, "brickID");
		if (myxGenBrickID == null) {
			throw new MyxUnsupportedBrickDescriptionException("MyxGen implementation lacks a brick ID: "
					+ XadlUtils.getName(xarch, brickRef));
		}

		String osgiBundleName = null;
		OSGiImplementation osgiImpl = XArchADTProxy.proxy(xarch, XadlUtils.getImplementation(xarch, brickRef,
				Osgiimplementation_3_0Package.Literals.OS_GI_IMPLEMENTATION));
		if (osgiImpl != null) {
			osgiBundleName = osgiImpl.getBundle();
		}

		return new MyxGenBrickDescription(null, osgiBundleName, myxGenBrickID);
	}
}
