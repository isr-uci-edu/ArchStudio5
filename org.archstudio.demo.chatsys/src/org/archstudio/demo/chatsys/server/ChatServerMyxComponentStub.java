package org.archstudio.demo.chatsys.server;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL
 * BE OVERWRITTEN.
 * 
 * To modify, update the "Server Impl" MyxGen extension in the
 * org.archstudio.demo.chatsys plugin.
 */

/**
 * Abstract Myx brick: Server Impl
 * 
 * @generated
 */
public abstract class ChatServerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.demo.chatsys.IChat {

	/**
	 * The registry of objects for this brick.
	 * 
	 * @generated
	 */
	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();

	/**
	 * Register this brick instance with the registry.
	 * 
	 * @generated
	 */
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
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
	public static final IMyxName IN_CHAT = MyxUtils.createName("chat");

	/**
	 * Returns the service object(s) for the chat interface.
	 * 
	 * @see #IN_CHAT
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChat getChat() {
		return this;
	}

	/**
	 * Myx name for the <code>chatEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_CHAT_EVENTS = MyxUtils.createName("chatEvents");

	/**
	 * Service object for the chatEvents interface.
	 * 
	 * @see #OUT_CHAT_EVENTS
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChatListener chatEvents = null;

	/**
	 * Returns the service object(s) for the chatEvents interface.
	 * 
	 * @see #OUT_CHAT_EVENTS
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChatListener getChatEvents() {
		if (chatEvents == null) {
			throw new NullPointerException("Uninitialized service object: chatEvents");
		}
		return chatEvents;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_CHAT
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_CHAT)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_CHAT_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_CHAT_EVENTS)) {
			if (chatEvents != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: chatEvents");
			}
			chatEvents = (org.archstudio.demo.chatsys.IChatListener) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_CHAT_EVENTS
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_CHAT_EVENTS)) {
			if (chatEvents == null) {
				throw new IllegalStateException("A connection was never made on interface: chatEvents");
			}
			chatEvents = null;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Performs no operation upon the completion of an interface disconnecting.
	 * 
	 * @generated
	 */
	@Override
	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}
}
