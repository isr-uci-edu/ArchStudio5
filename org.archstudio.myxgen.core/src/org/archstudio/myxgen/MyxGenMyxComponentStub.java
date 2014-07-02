package org.archstudio.myxgen;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Myx Component Stub Generator Impl" MyxGen extension in the org.archstudio.myxgen.core plugin.
 */

/**
 * Abstract Myx brick: Myx Component Stub Generator Impl
 * 
 * @generated
 */
public abstract class MyxGenMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick {

	/**
	 * The registry of objects for this brick.
	 * 
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * Register this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>myxgen</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MYXGEN = MyxUtils.createName("myxgen");

	/**
	 * Service object for the myxgen interface.
	 * 
	 * @see #IN_MYXGEN
	 * @generated
	 */
	protected org.archstudio.myxgen.IMyxStubGenerator myxgen = null;

	/**
	 * Returns the service object(s) for the myxgen interface.
	 * 
	 * @see #IN_MYXGEN
	 * @generated
	 */
	public org.archstudio.myxgen.IMyxStubGenerator getMyxgen() {
		if (myxgen == null) {
			throw new NullPointerException("Uninitialized service object: myxgen");
		}
		return myxgen;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_MYXGEN
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_MYXGEN)) {
			if (myxgen == null) {
				throw new NullPointerException("Uninitialized service object: myxgen");
			}
			return myxgen;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}
}
