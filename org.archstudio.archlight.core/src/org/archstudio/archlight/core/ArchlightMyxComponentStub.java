package org.archstudio.archlight.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.testadt.IArchlightTestADT;
import org.archstudio.archlight.IArchlightTool;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;

/**
 * Abstract Myx brick: "Archlight Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightMyxComponentStub extends
		org.archstudio.editors.AbstractArchstudioEditorMyxComponent implements
		org.archstudio.myx.fw.IMyxDynamicBrick,
		org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.myx.fw.IMyxProvidedServiceProvider {

	protected ArchlightMyxComponentStub(String editorName,
			String eclipseEditorID, boolean registerWithEditorManager) {
		super(editorName, eclipseEditorID, registerWithEditorManager);
	}

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
	 * Myx interface tools: <code>OUT_TOOLS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_TOOLS = MyxUtils.createName("tools");
	/**
	 * Myx interface testADT: <code>OUT_TEST_A_D_T</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_TEST_A_D_T = MyxUtils
			.createName("testADT");
	/**
	 * Service object(s) for tools: <code>tools</code>
	 *
	 * @see #OUT_TOOLS
	 * @generated
	 */
	protected org.archstudio.archlight.IArchlightTool tools = null;
	/**
	 * Service object(s) for testADT: <code>testADT</code>
	 *
	 * @see #OUT_TEST_A_D_T
	 * @generated
	 */
	protected org.archstudio.testadt.IArchlightTestADT testADT = null;

	/**
	 * Returns the service object(s) for <code>tools</code>
	 *
	 * @see #OUT_TOOLS
	 * @generated
	 */
	public org.archstudio.archlight.IArchlightTool getTools() {
		return tools;
	}

	/**
	 * Returns the service object(s) for <code>testADT</code>
	 *
	 * @see #OUT_TEST_A_D_T
	 * @generated
	 */
	public org.archstudio.testadt.IArchlightTestADT getTestADT() {
		return testADT;
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
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			tools = (org.archstudio.archlight.IArchlightTool) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_TEST_A_D_T)) {
			if (testADT != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			testADT = (org.archstudio.testadt.IArchlightTestADT) serviceObject;
			return;
		}
		super.interfaceConnected(interfaceName, serviceObject);
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
		if (interfaceName.equals(OUT_TOOLS)) {
			tools = null;
			return;
		}
		if (interfaceName.equals(OUT_TEST_A_D_T)) {
			testADT = null;
			return;
		}
		super.interfaceDisconnecting(interfaceName, serviceObject);
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName,
			Object serviceObject) {
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