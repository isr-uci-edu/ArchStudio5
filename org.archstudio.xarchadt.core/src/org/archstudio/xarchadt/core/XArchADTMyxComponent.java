package org.archstudio.xarchadt.core;

/**
 * Myx brick: "XArch ADT Impl"
 * 
 * @see org.archstudio.xarchadt.core.XArchADTMyxComponentStub
 * @generated
 */

public class XArchADTMyxComponent extends org.archstudio.xarchadt.core.XArchADTMyxComponentStub {
	public XArchADTMyxComponent() {
		this.xarch = new XArchADTImpl();
		((XArchADTImpl) xarch).addXArchADTFileListener(fileEventsProxy);
		((XArchADTImpl) xarch).addXArchADTModelListener(modelEventsProxy);
	}

}