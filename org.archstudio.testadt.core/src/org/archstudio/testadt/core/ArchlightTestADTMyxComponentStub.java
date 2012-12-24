package org.archstudio.testadt.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Archlight Test ADT Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class ArchlightTestADTMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>tests</code> interface.
	 * 
	 * MyxGenInterface[name=tests,direction=in,single=true,serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio.testadt.
	 * IArchlightTestADT,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_TESTS = MyxUtils.createName("tests");
	/**
	 * Myx name for the <code>testEvents</code> interface.
	 * 
	 * MyxGenInterface[name=testEvents,direction=out,single=false,
	 * serviceObjectDelegate
	 * =events,generateGetter=true,className=org.archstudio.
	 * testadt.ArchlightTestADTListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TEST_EVENTS = MyxUtils.createName("testEvents");

	/**
	 * Service object(s) for the tests interface.
	 * 
	 * @see #IN_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;
	/**
	 * Service object(s) for the testEvents interface.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.testadt.ArchlightTestADTListener> testEvents = new CopyOnWriteArrayList<org.archstudio.testadt.ArchlightTestADTListener>();
	/**
	 * Service object proxy for the testEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	protected final org.archstudio.testadt.ArchlightTestADTListener testEventsProxy = (org.archstudio.testadt.ArchlightTestADTListener) Proxy
			.newProxyInstance(org.archstudio.testadt.ArchlightTestADTListener.class.getClassLoader(),
					new Class[] { org.archstudio.testadt.ArchlightTestADTListener.class }, new InvocationHandler() {

						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
	 * Returns the service object(s) for the <code>tests</code> interface.
	 * 
	 * @see #IN_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		return tests;
	}

	/**
	 * Returns the service object(s) for the <code>testEvents</code> interface.
	 * 
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.testadt.ArchlightTestADTListener> getTestEvents() {
		return testEvents;
	}

	/**
	 * Returns the proxy service object for the <code>testEvents</code>
	 * interface.
	 * 
	 * @see #org testEventsProxy
	 * @see #OUT_TEST_EVENTS
	 * @generated
	 */
	public org.archstudio.testadt.ArchlightTestADTListener getTestEventsProxy() {
		return testEventsProxy;
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			testEvents.add((org.archstudio.testadt.ArchlightTestADTListener) serviceObject);
			return;
		}
		throw new IllegalArgumentException("Unhandled interface connection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TEST_EVENTS)) {
			testEvents.remove(serviceObject);
			return;
		}
		throw new IllegalArgumentException("Unhandled interface disconnection: " + interfaceName);
	}

	/**
	 * @generated
	 */

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	/**
	 * @generated
	 */

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_TESTS)) {
			if (tests == null) {
				throw new NullPointerException("tests");
			}
			return tests;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}