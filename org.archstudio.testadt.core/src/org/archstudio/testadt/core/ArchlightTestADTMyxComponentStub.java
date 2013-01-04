package org.archstudio.testadt.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Archlight Test ADT Impl" MyxGen extension in the org.archstudio.testadt.core plugin.
 */

/**
 * Abstract Myx brick: Archlight Test ADT Impl
 * 
 * @generated
 */
public abstract class ArchlightTestADTMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>tests</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_TESTS = MyxUtils.createName("tests");

	/**
	 * Service object for the tests interface.
	 * 
	 * @see #IN_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;

	/**
	 * Returns the service object(s) for the tests interface.
	 * 
	 * @see #IN_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		if (tests == null) {
			throw new NullPointerException("Uninitialized service object: tests");
		}
		return tests;
	}

	/**
	 * Myx name for the <code>testEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TEST_EVENTS = MyxUtils.createName("testEvents");

	/**
	 * Service objects for the testEvents interface.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	protected final java.util.Collection<org.archstudio.testadt.ArchlightTestADTListener> testEvents = new java.util.concurrent.CopyOnWriteArrayList<org.archstudio.testadt.ArchlightTestADTListener>();

	/**
	 * Service object proxy for the testEvents interface. Calls to this proxy object are automatically delegated to all
	 * connections on the interface
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	protected final org.archstudio.testadt.ArchlightTestADTListener testEventsProxy = (org.archstudio.testadt.ArchlightTestADTListener) java.lang.reflect.Proxy
			.newProxyInstance(org.archstudio.testadt.ArchlightTestADTListener.class.getClassLoader(),
					new Class[] { org.archstudio.testadt.ArchlightTestADTListener.class },
					new java.lang.reflect.InvocationHandler() {
						@Override
						public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
								throws Throwable {
							for (org.archstudio.testadt.ArchlightTestADTListener o : testEvents) {
								try {
									method.invoke(o, args);
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}
							return null;
						}
					});

	/**
	 * Returns the service object(s) for the testEvents interface.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	public org.archstudio.testadt.ArchlightTestADTListener getTestEvents() {
		return testEventsProxy;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_TESTS
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_TESTS)) {
			if (tests == null) {
				throw new NullPointerException("Uninitialized service object: tests");
			}
			return tests;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			testEvents.add((org.archstudio.testadt.ArchlightTestADTListener) serviceObject);
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			testEvents.remove(serviceObject);
			return;
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
