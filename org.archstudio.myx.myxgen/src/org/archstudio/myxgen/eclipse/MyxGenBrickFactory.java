package org.archstudio.myxgen.eclipse;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.osgi.MyxOSGiBrickFactory;
import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.eclipse.extension.MyxGenExtensions;

public class MyxGenBrickFactory extends MyxOSGiBrickFactory implements IMyxBrickFactory {

	public MyxGenBrickFactory() {
	}

	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
		MyxGenBrickDescription desc = (MyxGenBrickDescription) brickDescription;
		String brickID = desc.getMyxGenBrickID();
		MyxGenBrick brickExtension = MyxGenExtensions.getExtenalMyxGenBrick(brickID);
		if (brickExtension == null) {
			throw new MyxBrickCreationException("Can't find myxgen brick of " + brickID);
		}

		return create(name, brickDescription, initializationData, brickExtension.getContributor().getName(),
				brickExtension.getClassName());
	}
}
