package org.archstudio.dblgen;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.dblgen.IDataBindingGenerator;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "xADL Data Binding Library (DBL) Generator Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class DblGenComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
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
	 * Myx interface dblgen: <code>IN_DBLGEN</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_DBLGEN = new MyxBasicName(
			"org.archstudio.dblgen.core.interface1");

	/**
	 * Service object(s) for dblgen: <code>dblgen</code>
	 *
	 * @see #IN_DBLGEN
	 * @generated
	 */
	protected org.archstudio.dblgen.IDataBindingGenerator dblgen = null;

	/**
	 * Returns the service object(s) for <code>dblgen</code>
	 *
	 * @see #IN_DBLGEN
	 * @generated
	 */
	public org.archstudio.dblgen.IDataBindingGenerator getDblgen() {
		return dblgen;
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
	public void interfaceDisconnecting(IMyxName interfaceName,
			Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName,
			Object serviceObject) {
	}

	/**
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_DBLGEN)) {
			if (dblgen == null) {
				throw new NullPointerException("dblgen");
			}
			return dblgen;
		}
		return null;
	}
}