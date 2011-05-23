package org.archstudio.noticeadt.core;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.noticeadt.IArchlightNoticeADT;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.noticeadt.ArchlightNoticeADTListener;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Archlight Notice Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
public abstract class ArchlightNoticeADTMyxComponentStub extends
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
	 * Myx interface notices: <code>IN_NOTICES</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_NOTICES = MyxUtils.createName("notices");
	/**
	 * Myx interface noticeEvents: <code>OUT_NOTICE_EVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName OUT_NOTICE_EVENTS = MyxUtils
			.createName("noticeEvents");

	/**
	 * Service object(s) for notices: <code>notices</code>
	 *
	 * @see #IN_NOTICES
	 * @generated
	 */
	protected org.archstudio.noticeadt.IArchlightNoticeADT notices = null;
	/**
	 * Service object(s) for noticeEvents: <code>noticeEvents</code>
	 *
	 * @see #OUT_NOTICE_EVENTS
	 * @generated
	 */
	protected final Collection<org.archstudio.noticeadt.ArchlightNoticeADTListener> noticeEvents = new CopyOnWriteArrayList<org.archstudio.noticeadt.ArchlightNoticeADTListener>();

	/**
	 * Proxy to service objects for noticeEvents
	 *
	 * @see #OUT_NOTICE_EVENTS
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
	 * Returns the service object(s) for <code>notices</code>
	 *
	 * @see #IN_NOTICES
	 * @generated
	 */
	public org.archstudio.noticeadt.IArchlightNoticeADT getNotices() {
		return notices;
	}

	/**
	 * Returns the service object(s) for <code>noticeEvents</code>
	 *
	 * @see #OUT_NOTICE_EVENTS
	 * @generated
	 */
	public Collection<org.archstudio.noticeadt.ArchlightNoticeADTListener> getNoticeEvents() {
		return noticeEvents;
	}

	/**
	 * Returns the service object(s) for <code>noticeEvents</code>
	 *
	 * @see #OUT_NOTICE_EVENTS
	 * @generated
	 */
	// TODO: Not sure what to do here

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_NOTICE_EVENTS)) {
			noticeEvents
					.add((org.archstudio.noticeadt.ArchlightNoticeADTListener) serviceObject);
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
		if (interfaceName.equals(OUT_NOTICE_EVENTS)) {
			noticeEvents.remove(serviceObject);
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
		if (interfaceName.equals(IN_NOTICES)) {
			if (notices == null) {
				throw new NullPointerException("notices");
			}
			return notices;
		}
		throw new IllegalArgumentException(
				"Unhandled interface service object: " + interfaceName);
	}
}