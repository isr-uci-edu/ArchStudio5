package org.archstudio.issueview.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.eclipse.jface.preference.IPreferenceStore;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.issueadt.ArchlightIssueADTListener;
import org.archstudio.issueadt.IArchlightIssueADT;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.editormanager.IEditorManager;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.resources.IResources;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Archlight Issue Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class ArchlightIssueViewMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx name for the <code>xarch</code> interface.
	 * 
	 * MyxGenInterface[name=xarch,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .xarchadt.IXArchADT,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx name for the <code>resources</code> interface.
	 * 
	 * MyxGenInterface[name=resources,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .resources.IResources,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils.createName("resources");
	/**
	 * Myx name for the <code>preferences</code> interface.
	 * 
	 * MyxGenInterface[name=preferences,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.eclipse.jface
	 * .preference.IPreferenceStore,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils.createName("preferences");
	/**
	 * Myx name for the <code>editorManager</code> interface.
	 * 
	 * MyxGenInterface[name=editorManager,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .editormanager.IEditorManager,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils.createName("editorManager");
	/**
	 * Myx name for the <code>issueEvents</code> interface.
	 * 
	 * MyxGenInterface[name=issueEvents,direction=in,single=true,
	 * serviceObjectDelegate
	 * =brick,generateGetter=true,className=org.archstudio.issueadt
	 * .ArchlightIssueADTListener,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName IN_ISSUE_EVENTS = MyxUtils.createName("issueEvents");
	/**
	 * Myx name for the <code>issues</code> interface.
	 * 
	 * MyxGenInterface[name=issues,direction=out,single=true,
	 * serviceObjectDelegate
	 * =variable,generateGetter=true,className=org.archstudio
	 * .issueadt.IArchlightIssueADT,description=null]
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_ISSUES = MyxUtils.createName("issues");

	/**
	 * Service object(s) for the xarch interface.
	 * 
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for the resources interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for the preferences interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;
	/**
	 * Service object(s) for the editorManager interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for the issueEvents interface.
	 * 
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.issueadt.ArchlightIssueADTListener> issueEvents = new CopyOnWriteArrayList<org.archstudio.issueadt.ArchlightIssueADTListener>();
	/**
	 * Service object proxy for the issueEvents interface. Calls to the proxy
	 * object are automatically delegated to all service objects of this
	 * interface.
	 * 
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.issueadt.ArchlightIssueADTListener issueEventsProxy = (org.archstudio.issueadt.ArchlightIssueADTListener) Proxy
			.newProxyInstance(org.archstudio.issueadt.ArchlightIssueADTListener.class.getClassLoader(),
					new Class[] { org.archstudio.issueadt.ArchlightIssueADTListener.class }, new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							for (org.archstudio.issueadt.ArchlightIssueADTListener o : issueEvents) {
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
	 * Service object(s) for the issues interface.
	 * 
	 * @see #OUT_ISSUES
	 * @generated
	 */
	protected org.archstudio.issueadt.IArchlightIssueADT issues = null;

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
	 * Returns the service object(s) for the <code>resources</code> interface.
	 * 
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return resources;
	}

	/**
	 * Returns the service object(s) for the <code>preferences</code> interface.
	 * 
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * Returns the service object(s) for the <code>editorManager</code>
	 * interface.
	 * 
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

	/**
	 * Returns the service object(s) for the <code>issueEvents</code> interface.
	 * 
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.issueadt.ArchlightIssueADTListener> getIssueEvents() {
		return issueEvents;
	}

	/**
	 * Returns the proxy service object for the <code>issueEvents</code>
	 * interface.
	 * 
	 * @see #org issueEventsProxy
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	public org.archstudio.issueadt.ArchlightIssueADTListener getIssueEventsProxy() {
		return issueEventsProxy;
	}

	/**
	 * Returns the service object(s) for the <code>issues</code> interface.
	 * 
	 * @see #OUT_ISSUES
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
		if (interfaceName.equals(OUT_XARCH)) {
			if (xarch != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_ISSUES)) {
			if (issues != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			issues = (org.archstudio.issueadt.IArchlightIssueADT) serviceObject;
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
		if (interfaceName.equals(OUT_XARCH)) {
			xarch = null;
			return;
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			resources = null;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			preferences = null;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			editorManager = null;
			return;
		}
		if (interfaceName.equals(OUT_ISSUES)) {
			issues = null;
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
		if (interfaceName.equals(IN_ISSUE_EVENTS)) {
			return issueEventsProxy;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}