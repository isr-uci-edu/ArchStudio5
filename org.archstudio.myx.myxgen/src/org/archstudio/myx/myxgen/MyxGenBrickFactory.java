package org.archstudio.myx.myxgen;

import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxBrickDescription;
import org.archstudio.myx.fw.IMyxBrickFactory;
import org.archstudio.myx.fw.IMyxBrickInitializationData;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxBrickCreationException;
import org.archstudio.myx.myxgen.extension.MyxGenBrick;
import org.archstudio.myx.myxgen.extension.MyxGenExtensions;
import org.archstudio.myx.osgi.MyxOSGiBrickFactory;

public class MyxGenBrickFactory extends MyxOSGiBrickFactory implements IMyxBrickFactory {

	public MyxGenBrickFactory() {
	}

	@Override
	public IMyxBrick create(IMyxName name, IMyxBrickDescription brickDescription,
			IMyxBrickInitializationData initializationData) throws MyxBrickCreationException {
		MyxGenBrickDescription desc = (MyxGenBrickDescription) brickDescription;
		String brickID = desc.getMyxGenBrickID();
		MyxGenBrick brickExtension = MyxGenExtensions.getActiveMyxGenBrick(brickID);
		if (brickExtension == null) {
			throw new MyxBrickCreationException("Can't find myxgen brick");
		}

		return create(name, brickDescription, initializationData, brickExtension.getContributor().getName(),
				brickExtension.getClassName());
	}
}
