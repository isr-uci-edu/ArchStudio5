package org.archstudio.eclipse.ui.views;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.myx.fw.IMyxBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.eclipse.ui.part.ViewPart;

public abstract class AbstractArchStudioView<B extends IMyxBrick> extends ViewPart {

	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
	protected final B brick;

	public AbstractArchStudioView(Class<B> brickClass) {
		InstantiateArchStudio.instantiate();
		brick = myxRegistry.waitForBrick(brickClass);
	}

	@SuppressWarnings("unchecked")
	public AbstractArchStudioView(IMyxName brickName) {
		brick = (B) myxRegistry.waitForBrick(brickName);
	}
}
