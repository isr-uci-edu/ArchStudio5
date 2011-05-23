package org.archstudio.myx.comp;

import org.archstudio.myx.fw.MyxUtils;

/**
 * Myx brick: "Myx Runtime Impl"
 * 
 * @see org.archstudio.myx.comp.MyxRuntimeComponentStub
 * @generated
 */
public class MyxRuntimeComponent extends
		org.archstudio.myx.comp.MyxRuntimeComponentStub {

	public MyxRuntimeComponent() {
		myxRuntime = MyxUtils.getDefaultImplementation().createRuntime();
	}
}