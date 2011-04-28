package org.archstudio.demo.chatsys.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.archstudio.demo.chatsys.IChat;
import org.archstudio.demo.chatsys.IChatListener;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myx.fw.MyxLifecycleAdapter;
import org.archstudio.myx.fw.MyxUtils;

/**
 * Abstract base class of "Server Impl " brick.
 * 
 * <p>
 * Following methods are called automatically by the Myx framework. 
 * Clients can override them as necessary.
 * <ul>
 * 	<li> init(): this brick is created</li>
 *	<li> begin(): this brick is attached to others via links.</li>
 *	<li> end(): this brick is detached.</li>
 *	<li> destroy(): this brick is destroyed. </li>
 * </ul>
 * </p>
 * The brick interface service object(s):
 *	<table border="1">
 *	<tr>
 *		<th>JavaInterface</th><th>service object</th>
 *		<th>brick interface name</th><th>direction</th><th>connection timing</th><th>template type</th>
 *	</tr>
 *	<tr>
 *		<td>IChat</td><td><code>chat</code></td>
 * 		<td><code>chat</code></td><td>inSingleServiceObject</td>
 *		<td>beforeBegin</td><td>delegate</td>
 *	</tr>
 *	<tr>
 *		<td>Collection< IChatListener></td><td><code>chatevents</code></td>
 *		<td><code>chatevents</code></td><td>outMultipleServiceObjects</td>
 *		<td>beforeBegin</td><td>delegate</td>
 *	</tr>
 *	</table>
 * <p>
 * In order to prevent the myx code generator from overwriting the content of method, 
 * remove or change "@generated" annotation of java doc comment before re-run the generation.
 * </p>
 * @generated
 */
public abstract class ChatServerMyxComponentStub

implements IMyxDynamicBrick, IMyxLifecycleProcessor, IMyxProvidedServiceProvider {

	/**
	 * Myx-interface name for inSingleServiceObject chat.  
	 * <p>
	 * Through this IMyxName, other bricks can use services provided by chat brick interface.
	 * </p>
	 * @generated
	 */
	public static final IMyxName INTERFACE_NAME_IN_CHAT = MyxUtils.createName("org.archstudio.demo.chatsys.interface1");

	/**
	 * Myx-interface name for outMultipleServiceObjects chatevents.  
	 * <p>
	 * Through this IMyxName, this brick can use services reached through chatevents brick interface.
	 * </p>
	 * @generated
	 */
	public static final IMyxName INTERFACE_NAME_OUT_CHATEVENTS = MyxUtils
			.createName("org.archstudio.demo.chatsys.interface2");

	/**
	 * the service object for inSingleServiceObject <code>chat</code>. 
	 * @generated
	 */
	protected IChat chat = null;

	/**
	 *  the collection of service objects for outMultipleServiceObjects <code>chatevents</code>. 
	 * @generated
	 */
	protected Collection<IChatListener> chatevents = new ArrayList<IChatListener>();

	/**
	 * @generated
	 */
	class PreMyxLifecycleProcessor extends MyxLifecycleAdapter {

		/**
		 * @generated
		 */
		@Override
		public void init() {
			// connectBeforeInit
		}

		/**
		 * @generated
		 */
		@Override
		public void begin() {
			// connectBeforeBegin 

			if (chat == null) {
				throw new IllegalArgumentException("chat must be assigned a value before calling begin()");
			}

			if (chatevents == null) {
				throw new IllegalArgumentException("chatevents must be assigned a value before calling begin()");
			}
		}
	}

	/**
	 * @generated
	 */
	public ChatServerMyxComponentStub() {

		myxLifecycleProcessors.add(this);
		addPreMyxLifecycleProcessor(new PreMyxLifecycleProcessor());
	}

	/**
	 * @generated
	 */
	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {

		if (INTERFACE_NAME_OUT_CHATEVENTS.equals(interfaceName)) {

			if (chatevents.contains(serviceObject)) {
				throw new IllegalArgumentException("Interface chatevents already connected.");
			}
			// adds the serviceObject to chatevents  
			chatevents.add((IChatListener) serviceObject);
			return;
		}

	}

	/**
	 * @generated
	 */
	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {

		if (INTERFACE_NAME_OUT_CHATEVENTS.equals(interfaceName)) {

			if (!chatevents.contains(serviceObject)) {
				throw new IllegalArgumentException("Interface chatevents was not previously connected.");
			}
			// removes the serviceObject
			chatevents.remove(serviceObject);
			return;
		}

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

		if (INTERFACE_NAME_IN_CHAT.equals(interfaceName)) {

			if (chat == null) {
				throw new NullPointerException("chat service object is null.");
			}
			return chat;
		}
		return null;
	}

	/**
	 * @generated
	 */
	public void begin() {

	}

	/**
	 * @generated
	 */
	public void destroy() {

	}

	/**
	 * @generated
	 */
	public void end() {

	}

	/**
	 *   
	 * The following variable(s) must be assigned values before this method exits.
	 * <ul>
	 *  <li>{@link #chat}</li>
	 * </ul>	
	 * @generated
	 */
	public void init() {

	}

	/**
	 * A list of lifecycle processors. The order of method calling is
	 * <ol>
	 *  <li>PreMyxLifecycleProcessor#init()</li>
	 *  <li>this.init()</li>
	 *  <li>PreMyxLifecycleProcessor#begin()</li>
	 *  <li>this.begin()</li>
	 *  <li>PreMyxLifecycleProcessor#end()</li>
	 *  <li>this.end()</li>
	 *  <li>PreMyxLifecycleProcessor#destroy()</li>
	 *  <li>this.destroy()</li>
	 * @generated
	 */
	private final List<IMyxLifecycleProcessor> myxLifecycleProcessors = new ArrayList<IMyxLifecycleProcessor>();

	/**
	 * Adds a preMyxLifecycleProcessor
	 * @param preMyxLifecycleProcessor
	 */
	protected void addPreMyxLifecycleProcessor(IMyxLifecycleProcessor preMyxLifecycleProcessor) {

		// inserts a preMyxLifecycleProcessor into the head of the list
		this.myxLifecycleProcessors.add(0, preMyxLifecycleProcessor);
	}

	/**
	 * @generated
	 */
	private IMyxBrickItems brickItems = null;

	/**
	 * @generated
	 */
	public final void setMyxBrickItems(IMyxBrickItems brickItems) {
		this.brickItems = brickItems;
	}

	/**
	 * @generated
	 */
	public final IMyxBrickItems getMyxBrickItems() {
		return brickItems;
	}

	/**
	 * @generated
	 */
	public final Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors() {
		return myxLifecycleProcessors;
	}

	/**
	 * @generated
	 */
	public final IMyxProvidedServiceProvider getProvidedServiceProvider() {
		return this;
	}

}
