package org.archstudio.resources.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.resources.IResources;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Resources Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ResourcesMyxComponentStub extends
		org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.resources.IResources,
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
	 * Myx interface resources: <code>IN_RESOURCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_RESOURCES = MyxUtils
			.createName("resources");

	/**
	 * Returns the service object(s) for <code>resources</code>
	 *
	 * @see #IN_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return this;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		throw new IllegalArgumentException("Unhandled interface connection: "
				+ interfaceName);
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
		throw new IllegalArgumentException(
				"Unhandled interface disconnection: " + interfaceName);
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
		if (interfaceName.equals(IN_RESOURCES)) {
			return this;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}