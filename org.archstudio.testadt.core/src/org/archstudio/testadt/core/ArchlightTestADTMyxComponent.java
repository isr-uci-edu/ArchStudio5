package org.archstudio.testadt.core;

/**
 * Myx brick: "Archlight Test ADT Impl"
 * 
 * @see org.archstudio.testadt.core.ArchlightTestADTMyxComponentStub
 * @generated
 */
public class ArchlightTestADTMyxComponent extends org.archstudio.testadt.core.ArchlightTestADTMyxComponentStub {

	@Override
	public void init() {
		tests = new ArchlightTestADT();
		((ArchlightTestADT) tests).addArchlightTestADTListener(testEventsProxy);
	}
}