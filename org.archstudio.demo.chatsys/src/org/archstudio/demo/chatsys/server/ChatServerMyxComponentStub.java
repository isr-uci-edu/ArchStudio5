package org.archstudio.demo.chatsys.server;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Server Impl"
 * 
 * @generated
 */
abstract class ChatServerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.demo.chatsys.IChat, org.archstudio.myx.fw.IMyxProvidedServiceProvider {

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
	 * Myx interface chat: <code>IN_CHAT</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_CHAT = MyxUtils.createName("chat");
	/**
	 * Myx name for the <code>chatEvents</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=chatEvents,direction=out,single=true,serviceObjectDelegate=brick,generateGetter=true,className=org.archstudio.demo.chatsys.IChatListener,description=null]
	public static final IMyxName OUT_CHAT_EVENTS = MyxUtils.createName("chatEvents");

	/**
	 * Service object(s) for the chatEvents interface.
	 * 
	 * @see #OUT_CHAT_EVENTS
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChatListener chatEvents = null;

	/**
	 * Returns the service object(s) for <code>chat</code>
	 * 
	 * @see #IN_CHAT
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChat getChat() {
		return this;
	}

	/**
	 * Returns the service object(s) for <code>chatEvents</code>
	 * 
	 * @see #IN_CHAT_EVENTS
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChatListener getChatEvents() {
		return chatEvents;
	}

	/**
	 * @generated
	 */

	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_CHAT_EVENTS)) {
			if (chatEvents != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			chatEvents = (org.archstudio.demo.chatsys.IChatListener) serviceObject;
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
		if (interfaceName.equals(OUT_CHAT_EVENTS)) {
			chatEvents = null;
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
		if (interfaceName.equals(IN_CHAT)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}