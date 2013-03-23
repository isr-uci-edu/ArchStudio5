package org.archstudio.archlight.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Archlight Impl" MyxGen extension in the org.archstudio.archlight.core plugin.
 */

/**
 * Abstract Myx brick: Archlight Impl
 * 
 * @generated
 */
public abstract class ArchlightMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * A constructor from the super class...
	 * 
	 * @generated
	 */
	protected ArchlightMyxComponentStub(java.lang.String arg0, java.lang.String arg1, boolean arg2) {
		super(arg0, arg1, arg2);
	}

	/**
	 * Myx name for the <code>tools</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TOOLS = MyxUtils.createName("tools");

	/**
	 * Service object for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool tools = null;

	/**
	 * Returns the service object(s) for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		if (tools == null) {
			throw new NullPointerException("Uninitialized service object: tools");
		}
		return tools;
	}

	/**
	 * Myx name for the <code>tests</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TESTS = MyxUtils.createName("tests");

	/**
	 * Service object for the tests interface.
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;

	/**
	 * Returns the service object(s) for the tests interface.
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		if (tests == null) {
			throw new NullPointerException("Uninitialized service object: tests");
		}
		return tests;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_TOOLS
	 * @see #OUT_TESTS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: tools");
			}
			tools = (org.archstudio.archlight.IArchlightTool) serviceObject;
			return;
		}

		if (interfaceName.equals(OUT_TESTS)) {
			if (tests != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: tests");
			}
			tests = (org.archstudio.testadt.IArchlightTestADT) serviceObject;
			return;
		}

		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_TOOLS
	 * @see #OUT_TESTS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools == null) {
				throw new IllegalStateException("A connection was never made on interface: tools");
			}
			tools = null;
			return;
		}

		if (interfaceName.equals(OUT_TESTS)) {
			if (tests == null) {
				throw new IllegalStateException("A connection was never made on interface: tests");
			}
			tests = null;
			return;
		}

		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}
}
