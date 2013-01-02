package org.archstudio.dblgen;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL
 * BE OVERWRITTEN.
 * 
 * To modify, update the "xADL Data Binding Library (DBL) Generator Impl" MyxGen
 * extension in the org.archstudio.dblgen.core plugin.
 */

/**
 * Abstract Myx brick: xADL Data Binding Library (DBL) Generator Impl
 * 
 * @generated
 */
public abstract class DblGenComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>dblgen</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_DBLGEN = MyxUtils.createName("dblgen");

	/**
	 * Service object for the dblgen interface.
	 * 
	 * @see #IN_DBLGEN
	 * @generated
	 */
	protected org.archstudio.dblgen.IDataBindingGenerator dblgen = null;

	/**
	 * Returns the service object(s) for the dblgen interface.
	 * 
	 * @see #IN_DBLGEN
	 * @generated
	 */
	public org.archstudio.dblgen.IDataBindingGenerator getDblgen() {
		if (dblgen == null) {
			throw new NullPointerException("Uninitialized service object: dblgen");
		}
		return dblgen;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_DBLGEN
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_DBLGEN)) {
			if (dblgen == null) {
				throw new NullPointerException("Uninitialized service object: dblgen");
			}
			return dblgen;
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
