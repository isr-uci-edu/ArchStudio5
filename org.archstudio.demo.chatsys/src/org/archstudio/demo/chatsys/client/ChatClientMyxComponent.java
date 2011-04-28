package org.archstudio.demo.chatsys.client;

import org.archstudio.demo.chatsys.client.ChatClientMyxComponentStub;
import java.util.Collection;

/**
 * Myx brick component.
 * 
 * Following methods are called automatically. They should be overrided as necessary.
 * <ul>
 * 	<li> init(): this brick is created</li>
 *	<li> begin(): this brick is attached to others via links.</li>
 *	<li> end(): this brick is detached.</li>
 *	<li> destroy(): this brick is destroyed. </li>
 * </ul>
 * The brick interface service object variable(s):
 *	<table border="1">
 *	<tr>
 *		<th>JavaInterface</th><th>service object</th>
 *		<th>brick interface</th><th>direction</th><th>connection timing</th><th>template type</th>
 *	</tr>
 *	<tr>
 *		<td>IChatListener</td><td><code>chatevents</code></td>
 *		<td><code>chatevents</code></td><td>inSingleServiceObject</td>
 *		<td>beforeBegin</td><td>delegate</td>
 *	</tr>
 *	<tr>
 *		<td>IChat</td><td><code>chat</code></td>
 *		<td><code>chat</code></td><td>outSingleServiceObject</td>
 *		<td>beforeBegin</td><td>delegate</td>
 *	</tr>
 *	</table>
 * <p>
 * In order to prevent the myx code generator from overwriting the content of method, 
 * remove "@generated" annotation from java doc comment before re-run the generation
 * if any change is made to the method.
 * </p>
 * @generated
 */
public class ChatClientMyxComponent extends ChatClientMyxComponentStub {

	/**
	 *   
	 * @generated
	 */
	public ChatClientMyxComponent() {

	}

	/**
	 *
	 * The following variable(s) must be assigned values before this method exits.
	 * <ul>
	 *  <li>{@link #chatevents}</li>
	 * </ul>	
	 * @see edu.uci.isr.myx.fw.AbstractMyxSimpleBrick#init()
	 */
	@Override
	public void init() {
		super.init();
		//TODO: assign a value to this.chatevents
	}

	/**
	 * @see edu.uci.isr.myx.fw.AbstractMyxSimpleBrick#begin()
	 */
	@Override
	public void begin() {
		super.begin();

		//XXX: all the variables will be available at this point.
	}

}
