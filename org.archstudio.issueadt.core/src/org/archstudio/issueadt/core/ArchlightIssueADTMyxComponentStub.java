package org.archstudio.issueadt.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.issueadt.ArchlightIssueADTListener;
import org.archstudio.issueadt.IArchlightIssueADT;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Archlight Issue Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
abstract class ArchlightIssueADTMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.xarchadt.IXArchADTFileListener, org.archstudio.myx.fw.IMyxProvidedServiceProvider {

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
	 * Myx interface issueEvents: <code>OUT_ISSUE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_ISSUE_EVENTS = MyxUtils.createName("issueEvents");
	/**
	 * Myx interface fileEvents: <code>IN_FILE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_FILE_EVENTS = MyxUtils.createName("fileEvents");
	/**
	 * Myx interface issues: <code>IN_ISSUES</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_ISSUES = MyxUtils.createName("issues");
	/**
	 * Service object(s) for issueEvents: <code>issueEvents</code>
	 *
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	protected org.archstudio.issueadt.ArchlightIssueADTListener issueEvents = null;
	/**
	 * Service object proxy for the issueEvents interface.
	 * Calls to the proxy object are automatically delegated to all service objects of this interface.
	 *
	 * @see #OUT_ISSUE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.issueadt.ArchlightIssueADTListener issueEventsProxy = (org.archstudio.issueadt.ArchlightIssueADTListener) Proxy
			.newProxyInstance(org.archstudio.issueadt.ArchlightIssueADTListener.class.getClassLoader(),
					new Class[] { org.archstudio.issueadt.ArchlightIssueADTListener.class }, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							org.archstudio.issueadt.ArchlightIssueADTListener o = issueEvents;
							if (o != null) {
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
	 * Service object(s) for issues: <code>issues</code>
	 *
	 * @see #IN_ISSUES
	 * @generated
	 */
	protected org.archstudio.issueadt.IArchlightIssueADT issues = null;

	/**
	 * Returns the service object(s) for <code>issueEvents</code>
	 *
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	public org.archstudio.issueadt.ArchlightIssueADTListener getIssueEvents() {
		return issueEvents;
	}

	/**
	 * Returns the service object(s) for <code>fileEvents</code>
	 *
	 * @see #IN_FILE_EVENTS
	 * @generated
	 */
	public org.archstudio.xarchadt.IXArchADTFileListener getFileEvents() {
		return this;
	}

	/**
	 * Returns the service object(s) for <code>issues</code>
	 *
	 * @see #IN_ISSUES
	 * @generated
	 */
	public org.archstudio.issueadt.IArchlightIssueADT getIssues() {
		return issues;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_ISSUE_EVENTS)) {
			if (issueEvents != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			issueEvents = (org.archstudio.issueadt.ArchlightIssueADTListener) serviceObject;
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
		if (interfaceName.equals(OUT_ISSUE_EVENTS)) {
			issueEvents = null;
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
		if (interfaceName.equals(IN_FILE_EVENTS)) {
			return this;
		}
		if (interfaceName.equals(IN_ISSUES)) {
			if (issues == null) {
				throw new NullPointerException("issues");
			}
			return issues;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}