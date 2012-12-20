package org.archstudio.schematron.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.archlight.IArchlightTool;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Schematron Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class SchematronMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.archlight.IArchlightTool, org.archstudio.myx.fw.IMyxProvidedServiceProvider {

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
	 * Myx name for the <code>tools</code> interface.
	 *
	 * @generated
	 */
	// MyxGenInterface[name=tools,direction=in,single=true,serviceObjectDelegate=brick,generateGetter=true,className=org.archstudio.archlight.IArchlightTool,domain=top,description=null]
	public static final IMyxName IN_TOOLS = MyxUtils.createName("tools");
	/**
	 * Myx interface tests: <code>OUT_TESTS</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TESTS = MyxUtils.createName("tests");
	/**
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Service object(s) for tests: <code>tests</code>
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;
	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;

	/**
	 * Returns the service object(s) for the <code>tools</code> interface.
	 *
	 * @see #IN_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		return this;
	}

	/**
	 * Returns the service object(s) for <code>tests</code>
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		return tests;
	}

	/**
	 * Returns the service object(s) for <code>xarch</code>
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TESTS)) {
			if (tests != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			tests = (org.archstudio.testadt.IArchlightTestADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
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
		if (interfaceName.equals(OUT_TESTS)) {
			tests = null;
			return;
		}
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
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
		if (interfaceName.equals(IN_TOOLS)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}