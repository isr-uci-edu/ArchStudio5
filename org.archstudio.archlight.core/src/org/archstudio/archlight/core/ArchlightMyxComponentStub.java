package org.archstudio.archlight.core;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Archlight Impl"
 * 
 * @generated
 */

/* package */abstract class ArchlightMyxComponentStub extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	public ArchlightMyxComponentStub(String editorName, String eclipseEditorID, boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

	/**
	 * Myx name for the <code>tools</code> interface.
	 * 
	 * MyxGenInterface[name=tools,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .archlight.IArchlightTool,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TOOLS = MyxUtils.createName("tools");
	/**
	 * Myx name for the <code>tests</code> interface.
	 * 
	 * MyxGenInterface[name=tests,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .testadt.IArchlightTestADT,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_TESTS = MyxUtils.createName("tests");

	/**
	 * Service object(s) for the tools interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool tools = null;
	/**
	 * Service object(s) for the tests interface.
	 * 
	 * @see #OUT_TESTS
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT tests = null;

	/**
	 * Returns the service object(s) for the <code>tools</code> interface.
	 * 
	 * @see #OUT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		return tools;
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
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TOOLS)) {
			if (tools != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			tools = (org.archstudio.archlight.IArchlightTool) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_TESTS)) {
			if (tests != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			tests = (org.archstudio.testadt.IArchlightTestADT) serviceObject;
			return;
		}
		super.interfaceConnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_TOOLS)) {
			tools = null;
			return;
		}
		if (interfaceName.equals(OUT_TESTS)) {
			tests = null;
			return;
		}
		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
		super.interfaceDisconnected(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */

	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		return super.getServiceObject(interfaceName);
	}
}