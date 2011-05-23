package org.archstudio.noticeview.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.noticeadt.IArchlightNoticeADT;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.noticeadt.ArchlightNoticeADTListener;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.resources.IResources;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Archlight Notice View Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightNoticeViewMyxComponentStub extends
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
	 * Myx interface resources: <code>OUT_RESOURCES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_RESOURCES = MyxUtils
			.createName("resources");
	/**
	 * Myx interface noticeEvents: <code>IN_NOTICE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_NOTICE_EVENTS = MyxUtils
			.createName("noticeEvents");
	/**
	 * Myx interface notices: <code>OUT_NOTICES</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_NOTICES = MyxUtils.createName("notices");

	/**
	 * Service object(s) for resources: <code>resources</code>
	 *
	 * @see #OUT_RESOURCES
	 * @generated
	 */
	protected org.archstudio.resources.IResources resources = null;
	/**
	 * Service object(s) for noticeEvents: <code>noticeEvents</code>
	 *
	 * @see #IN_NOTICE_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.noticeadt.ArchlightNoticeADTListener> noticeEvents = new CopyOnWriteArrayList<org.archstudio.noticeadt.ArchlightNoticeADTListener>();
	/**
	 * Proxy to service objects for noticeEvents
	 *
	 * @see #IN_NOTICE_EVENTS
	 * @generated
	 */
	protected final org.archstudio.noticeadt.ArchlightNoticeADTListener noticeEventsProxy = (org.archstudio.noticeadt.ArchlightNoticeADTListener) Proxy
			.newProxyInstance(
					org.archstudio.noticeadt.ArchlightNoticeADTListener.class
							.getClassLoader(),
					new Class[] { org.archstudio.noticeadt.ArchlightNoticeADTListener.class },
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method,
								Object[] args) throws Throwable {
							for (org.archstudio.noticeadt.ArchlightNoticeADTListener o : noticeEvents) {
								try {
									method.invoke(o, args);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							return null;
						}
					});
	/**
	 * Service object(s) for notices: <code>notices</code>
	 *
	 * @see #OUT_NOTICES
	 * @generated
	 */
	protected org.archstudio.noticeadt.IArchlightNoticeADT notices = null;

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
	 * Returns the service object(s) for <code>noticeEvents</code>
	 *
	 * @see #IN_NOTICE_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.noticeadt.ArchlightNoticeADTListener> getNoticeEvents() {
		return noticeEvents;
	}

	/**
	 * Returns the service object(s) for <code>noticeEvents</code>
	 *
	 * @see #IN_NOTICE_EVENTS
	 * @generated
	 */
	// TODO: Not sure what to do here
	/**
	 * Returns the service object(s) for <code>notices</code>
	 *
	 * @see #OUT_NOTICES
	 * @generated
	 */
	public org.archstudio.noticeadt.IArchlightNoticeADT getNotices() {
		return notices;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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
		if (interfaceName.equals(OUT_NOTICES)) {
			if (notices != null) {
				throw new IllegalStateException(
						"Only a single connection is supported on "
								+ interfaceName);
			}
			notices = (org.archstudio.noticeadt.IArchlightNoticeADT) serviceObject;
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
		if (interfaceName.equals(OUT_RESOURCES)) {
			resources = null;
			return;
		}
		if (interfaceName.equals(OUT_NOTICES)) {
			notices = null;
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
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}