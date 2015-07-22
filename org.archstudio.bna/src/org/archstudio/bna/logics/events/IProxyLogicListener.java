package org.archstudio.bna.logics.events;

/**
 * An interface to inform the listener when a ProxyLogic or proxy object instance has been created. This is useful when
 * the listener wants to add the proxy objects as listeners to some some other object's events.
 */
public interface IProxyLogicListener {
	/**
	 * Called when a new ProxyLogic has been created.
	 *
	 * @param proxyLogic The proxy logic that was just created.
	 */
	public void proxyLogicCreated(ProxyLogic proxyLogic);

	/**
	 * Called when a new proxy object is created.
	 *
	 * @param proxyLogic The proxy logic that created the proxy object.
	 * @param proxyObject The proxy object created by the proxy logic.
	 */
	public void proxyObjectCreated(ProxyLogic proxyLogic, Object proxyObject);
}