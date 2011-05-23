package org.archstudio.testadt.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.testadt.ArchlightTestADTListener;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "org.archstudio.testadt.core.Archlight Test ADT Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightTestADTMyxComponentStub extends
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
	 * Myx interface tests: <code>IN_TESTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_TESTS = MyxUtils.createName("tests");
	/**
	 * Myx interface testEvents: <code>OUT_TEST_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_TEST_EVENTS = MyxUtils
			.createName("testEvents");

	/**
	 * Service object(s) for tests: <code>tests</code>
	 *
	 * @see #IN_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;
	/**
	 * Service object(s) for testEvents: <code>testEvents</code>
	 *
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	protected org.archstudio.testadt.ArchlightTestADTListener testEvents = null;

	/**
	 * Returns the service object(s) for <code>tests</code>
	 *
	 * @see #IN_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		return tests;
	}

	/**
	 * Returns the service object(s) for <code>testEvents</code>
	 *
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	public org.archstudio.testadt.ArchlightTestADTListener getTestEvents() {
		return testEvents;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			if (testEvents != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			testEvents = (org.archstudio.testadt.ArchlightTestADTListener) serviceObject;
			return;
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
		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			testEvents = null;
			return;
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
		if (interfaceName.equals(IN_TESTS)) {
			if (tests == null) {
				throw new NullPointerException("tests");
			}
			return tests;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}