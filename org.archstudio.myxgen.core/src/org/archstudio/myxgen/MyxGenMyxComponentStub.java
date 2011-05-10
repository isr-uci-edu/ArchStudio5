package org.archstudio.myxgen;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myxgen.IMyxStubGenerator;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Myx Component Stub Generator Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class MyxGenMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * @generated
	 */
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * @generated
	 */
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx interface myxgen: <code>IN_MYXGEN</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_MYXGEN = new MyxBasicName("org.archstudio.myxgen.core.interface1");

	/**
	 * Service object(s) for myxgen: <code>myxgen</code>
	 *
	 * @see #IN_MYXGEN
	 * @generated
	 */
	protected org.archstudio.myxgen.IMyxStubGenerator myxgen = null;

	/**
	 * Returns the service object(s) for <code>myxgen</code>
	 *
	 * @see #IN_MYXGEN
	 * @generated
	 */
	public org.archstudio.myxgen.IMyxStubGenerator getMyxgen() {
		return myxgen;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_MYXGEN)) {
			if (myxgen == null) {
				throw new NullPointerException("myxgen");
			}
			return myxgen;
		}
		return null;
	}
}