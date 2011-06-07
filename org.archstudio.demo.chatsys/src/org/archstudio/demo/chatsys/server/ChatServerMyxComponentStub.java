package org.archstudio.demo.chatsys.server;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import org.archstudio.myx.fw.*;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.demo.chatsys.IChatListener;
import org.archstudio.demo.chatsys.IChat;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;

/**
 * Abstract Myx brick: "Server Impl"
 *
 * @generated
 */
@SuppressWarnings("unused")
abstract class ChatServerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface chat: <code>IN_CHAT</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_CHAT = MyxUtils.createName("chat");
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
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
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
			if (chat == null) {
				throw new NullPointerException("chat");
			}
			return chat;
		}
		if (interfaceName.equals(IN_CHAT_EVENTS)) {
			return this;
		}
		throw new IllegalArgumentException("Unhandled interface service object: " + interfaceName);
	}
}