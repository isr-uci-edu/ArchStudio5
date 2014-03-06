package org.archstudio.demo.chatsys.client;

import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;

/*
 * DO NOT EDIT THIS CLASS, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 * 
 * To modify, update the "Client Impl" MyxGen extension in the org.archstudio.demo.chatsys plugin.
 */

/**
 * Abstract Myx brick: Client Impl
 * 
 * @generated
 */
public abstract class ChatClientMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
		org.archstudio.myx.fw.IMyxDynamicBrick, org.archstudio.demo.chatsys.IChatListener {

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
	@Override
	public void begin() {
		super.begin();
		myxRegistry.register(this);
	}

	/**
	 * Unregister this brick instance with the registry.
	 * 
	 * @generated
	 */
	@Override
	public void end() {
		myxRegistry.unregister(this);
		super.end();
	}

	/**
	 * Myx name for the <code>chat</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName OUT_CHAT = MyxUtils.createName("chat");

	/**
	 * Service object for the chat interface.
	 * 
	 * @see #OUT_CHAT
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChat chat = null;

	/**
	 * Returns the service object(s) for the chat interface.
	 * 
	 * @see #OUT_CHAT
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChat getChat() {
		if (chat == null) {
			throw new NullPointerException("Uninitialized service object: chat");
		}
		return chat;
	}

	/**
	 * Myx name for the <code>chatEvents</code> interface.
	 * 
	 * @generated
	 */
	public static final IMyxName IN_CHAT_EVENTS = MyxUtils.createName("chatEvents");

	/**
	 * Returns the service object(s) for the chatEvents interface.
	 * 
	 * @see #IN_CHAT_EVENTS
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChatListener getChatEvents() {
		return this;
	}

	/**
	 * Returns service object(s) for IN interfaces.
	 * 
	 * @see #IN_CHAT_EVENTS
	 * @generated
	 */
	@Override
	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(IN_CHAT_EVENTS)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on connected OUT interfaces.
	 * 
	 * @see #OUT_CHAT
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_CHAT)) {
			if (chat != null) {
				throw new IllegalStateException("Only a single connection is supported on interface: chat");
			}
			chat = (org.archstudio.demo.chatsys.IChat) serviceObject;
			return;
		}

		throw new IllegalArgumentException("Unhandled interface: " + interfaceName.getName());
	}

	/**
	 * Update service objects based on disconnecting OUT interfaces.
	 * 
	 * @see #OUT_CHAT
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}

		if (interfaceName.equals(OUT_CHAT)) {
			if (chat == null) {
				throw new IllegalStateException("A connection was never made on interface: chat");
			}
			chat = null;
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
