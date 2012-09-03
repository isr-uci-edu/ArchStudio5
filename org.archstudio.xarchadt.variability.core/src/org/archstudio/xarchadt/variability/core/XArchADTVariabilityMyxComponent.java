package org.archstudio.xarchadt.variability.core;

/**
 * Myx brick: "XArch ADT Variability Impl"
 * 
 * @see org.archstudio.xarchadt.variability.core.XArchADTVariabilityMyxComponentStub
 * @generated
 */

public class XArchADTVariabilityMyxComponent extends
		org.archstudio.xarchadt.variability.core.XArchADTVariabilityMyxComponentStub {
	public XArchADTVariabilityMyxComponent() {
		this.xarch = new XArchADTVariabilityImpl();
		((XArchADTVariabilityImpl) xarch).addXArchADTFileListener(fileEventsProxy);
		((XArchADTVariabilityImpl) xarch).addXArchADTModelListener(modelEventsProxy);
		((XArchADTVariabilityImpl) xarch).addXArchADTVariabilityListener(variabilityEventsProxy);
	}
}