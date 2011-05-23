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

/**
 * Abstract Myx brick: "Archlight Issue Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightIssueViewMyxComponentStub extends
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
	 * Myx interface xarch: <code>OUT_XARCH</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_XARCH = MyxUtils.createName("xarch");
	/**
	 * Myx interface resources: <code>OUT_RESOURCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils
			.createName("resources");
	/**
	 * Myx interface preferences: <code>OUT_PREFERENCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_PREFERENCES = MyxUtils
			.createName("preferences");
	/**
	 * Myx interface editorManager: <code>OUT_EDITOR_MANAGER</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_EDITOR_MANAGER = MyxUtils
			.createName("editorManager");
	/**
	 * Myx interface issueEvents: <code>IN_ISSUE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_ISSUE_EVENTS = MyxUtils
			.createName("issueEvents");
	/**
	 * Myx interface issues: <code>OUT_ISSUES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_ISSUES = MyxUtils.createName("issues");
	/**
	 * Service object(s) for xarch: <code>xarch</code>
	 *
	 * @see #OUT_XARCH
	 * @generated
	 */
	protected org.archstudio.xarchadt.IXArchADT xarch = null;
	/**
	 * Service object(s) for resources: <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for preferences: <code>preferences</code>
	 *
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	protected org.eclipse.jface.preference.IPreferenceStore preferences = null;
	/**
	 * Service object(s) for editorManager: <code>editorManager</code>
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	protected org.archstudio.editormanager.IEditorManager editorManager = null;
	/**
	 * Service object(s) for issueEvents: <code>issueEvents</code>
	 *
	 * @see #IN_ISSUE_EVENTS
	 * @generated
	 */
	protected org.archstudio.issueadt.ArchlightIssueADTListener issueEvents = null;
	/**
	 * Service object(s) for issues: <code>issues</code>
	 *
	 * @see #OUT_ISSUES
	 * @generated
	 */
	protected org.archstudio.issueadt.IArchlightIssueADT issues = null;

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
	 * Returns the service object(s) for <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	public org.archstudio.resources.IResources getResources() {
		return resources;
	}

	/**
	 * Returns the service object(s) for <code>preferences</code>
	 *
	 * @see #OUT_PREFERENCES
	 * @generated
	 */
	public org.eclipse.jface.preference.IPreferenceStore getPreferences() {
		return preferences;
	}

	/**
	 * Returns the service object(s) for <code>editorManager</code>
	 *
	 * @see #OUT_EDITOR_MANAGER
	 * @generated
	 */
	public org.archstudio.editormanager.IEditorManager getEditorManager() {
		return editorManager;
	}

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
	 * Returns the service object(s) for <code>issues</code>
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
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			xarch = (org.archstudio.xarchadt.IXArchADT) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_RESOURCES)) {
			if (resources != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			resources = (org.archstudio.resources.IResources) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_PREFERENCES)) {
			if (preferences != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			preferences = (org.eclipse.jface.preference.IPreferenceStore) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_EDITOR_MANAGER)) {
			if (editorManager != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			editorManager = (org.archstudio.editormanager.IEditorManager) serviceObject;
			return;
		}
		if (interfaceName.equals(OUT_ISSUES)) {
			if (issues != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			issues = (org.archstudio.issueadt.IArchlightIssueADT) serviceObject;
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
		if (interfaceName.equals(IN_ISSUE_EVENTS)) {
			if (issueEvents == null) {
				throw new NullPointerException("issueEvents");
			}
			return issueEvents;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}