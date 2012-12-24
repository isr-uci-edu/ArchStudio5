package org.archstudio.demo.chatsys.client;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract Myx brick: "Client Impl"
 * 
 * @generated
 */
@SuppressWarnings("unused")
abstract class ChatClientMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.myx.fw.IMyxLifecycleProcessor,
		org.archstudio.demo.chatsys.IChatListener, org.archstudio.myx.fw.IMyxProvidedServiceProvider {

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
	 * Myx name for the <code>chat</code> interface.
	 * 
	 * @generated
	 */
	// MyxGenInterface[name=chat,direction=out,single=true,serviceObjectDelegate=variable,generateGetter=true,className=org.archstudio.demo.chatsys.IChat,domain=top,description=null]
	public static final IMyxName OUT_CHAT = MyxUtils.createName("chat");
	/**
	 * Myx interface chatEvents: <code>IN_CHAT_EVENTS</code>
	 * 
	 * @generated
	 */
	public static final IMyxName IN_CHAT_EVENTS = MyxUtils.createName("chatEvents");
	/**
	 * Service object(s) for chat: <code>chat</code>
	 * 
	 * @see #IN_CHAT
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChat chat = null;

	/**
	 * Returns the service object(s) for <code>chat</code>
	 * 
	 * @see #IN_CHAT
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChat getChat() {
		return chat;
	}

	/**
	 * Returns the service object(s) for <code>chatEvents</code>
	 * 
	 * @see #IN_CHAT_EVENTS
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChatListener getChatEvents() {
		return this;
	}

	/**
	 * @generated
	 */

	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
		if (interfaceName.equals(OUT_CHAT)) {
			if (chat != null) {
				throw new IllegalStateException("Only a single connection is supported on " + interfaceName);
			}
			chat = (org.archstudio.demo.chatsys.IChat) serviceObject;
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
		if (interfaceName.equals(OUT_CHAT)) {
			chat = null;
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
		if (interfaceName.equals(IN_CHAT_EVENTS)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}