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
public abstract class ChatServerMyxComponentStub extends org.archstudio.myx.fw.AbstractMyxSimpleBrick implements
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
	 * Myx interface chat: <code>IN_CHAT</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_CHAT = MyxUtils.createName("org.archstudio.demo.chatsys.interface1");
	/**
	 * Myx interface chatevents: <code>IN_CHATEVENTS</code>
	 *
	 * @generated
	 */
	public static final IMyxName IN_CHATEVENTS = MyxUtils.createName("org.archstudio.demo.chatsys.interface2");

	/**
	 * Service object(s) for chat: <code>chat</code>
	 *
	 * @see #IN_CHAT
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChat chat = null;
	/**
	 * Service object(s) for chatevents: <code>chatevents</code>
	 *
	 * @see #IN_CHATEVENTS
	 * @generated
	 */
	protected org.archstudio.demo.chatsys.IChatListener chatevents = null;

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
	 * Returns the service object(s) for <code>chatevents</code>
	 *
	 * @see #IN_CHATEVENTS
	 * @generated
	 */
	public org.archstudio.demo.chatsys.IChatListener getChatevents() {
		return chatevents;
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
	}

	/**
	 * @generated
	 */
	@Override
	public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (serviceObject == null) {
			throw new NullPointerException(interfaceName.getName());
		}
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
		if (interfaceName.equals(IN_CHATEVENTS)) {
			if (chatevents == null) {
				throw new NullPointerException("chatevents");
			}
			return chatevents;
		}
		return null;
	}
}