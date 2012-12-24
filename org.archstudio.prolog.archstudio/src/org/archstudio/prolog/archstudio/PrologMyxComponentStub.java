package org.archstudio.prolog.archstudio;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "PrologMyxComponent"
 * 
 * @generated
 */
/* package */abstract class PrologMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=xarch,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.xarchadt.IXArchADT,domain=top,description=null]
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx name for the <code>tests</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=tests,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.testadt.IArchlightTestADT,domain=top,description=null]
	public static final IMyxName OUT_TESTS = MyxUtils.createName("tests");
	/**
	 * Myx name for the <code>tools</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=tools,direction=in,single=true,serviceObjectDelegate=brick,generateGetter=true,className=org.archstudio.archlight.IArchlightTool,domain=top,description=null]
	public static final IMyxName IN_TOOLS = MyxUtils.createName("tools");

	/**
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for the tests interface.
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;

	/**
	 * Returns the service object(s) for the <code>xarch</code> interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADT getXarch() {
		return xarch;
	}

	/**
	 * Returns the service object(s) for the <code>tests</code> interface.
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTests() {
		return tests;
	}

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
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_TESTS)) {
			if (tests != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			tests = (org.archstudio.testadt.IArchlightTestADT) serviceObject;
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
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
		}
		if (interfaceName.equals(OUT_TESTS)) {
			tests = null;
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
		if (interfaceName.equals(IN_TOOLS)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}