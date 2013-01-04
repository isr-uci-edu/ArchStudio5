package org.archstudio.myx.java.comp;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Myx Runtime Impl" MyxGen extension in the org.archstudio.myx.java plugin.
 */

/**
 * Abstract Myx brick: Myx Runtime Impl
 * 
 * @generated
 */
public abstract class MyxRuntimeComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>myxRuntime</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MYX_RUNTIME = MyxUtils.createName("myxRuntime");

	/**
	 * Service object for the myxRuntime interface.
	 * 
	 * @see #IN_MYX_RUNTIME
	 * @generated
	 */
	protected org.archstudio.myx.fw.IMyxRuntime myxRuntime = null;

	/**
	 * Returns the service object(s) for the myxRuntime interface.
	 * 
	 * @see #IN_MYX_RUNTIME
	 * @generated
	 */
	public org.archstudio.myx.fw.IMyxRuntime getMyxRuntime() {
		if (myxRuntime == null) {
			throw new NullPointerException("Uninitialized service object: myxRuntime");
		}
		return myxRuntime;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_MYX_RUNTIME
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_MYX_RUNTIME)) {
			if (myxRuntime == null) {
				throw new NullPointerException("Uninitialized service object: myxRuntime");
			}
			return myxRuntime;
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
