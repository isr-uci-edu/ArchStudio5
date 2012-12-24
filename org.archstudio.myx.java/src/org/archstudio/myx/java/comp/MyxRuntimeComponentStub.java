package org.archstudio.myx.java.comp;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Myx Runtime Impl"
 * 
 * @generated
 */
abstract class MyxRuntimeComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	/**
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * @generated
	 */

	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * @generated
	 */

	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx interface myxRuntime: <code>IN_MYX_RUNTIME</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_MYX_RUNTIME = MyxUtils.createName("myxRuntime");

	/**
	 * Service object(s) for myxRuntime: <code>myxRuntime</code>
	 * 
	 * @see #IN_MYX_RUNTIME
	 * @generated
	 */
	protected org.archstudio.myx.fw.IMyxRuntime myxRuntime = null;

	/**
	 * Returns the service object(s) for <code>myxRuntime</code>
	 * 
	 * @see #IN_MYX_RUNTIME
	 * @generated
	 */
	public org.archstudio.myx.fw.IMyxRuntime getMyxRuntime() {
		return myxRuntime;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		throw new IllegalArgumentException("Unhandled interface connection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		throw new IllegalArgumentException("Unhandled interface disconnection: " + interfaceName);
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
		if (interfaceName.equals(IN_MYX_RUNTIME)) {
			if (myxRuntime == null) {
				throw new NullPointerException("myxRuntime");
			}
			return myxRuntime;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}