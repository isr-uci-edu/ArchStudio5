package org.archstudio.ljm;

import java.net.InetAddress;

public class LJMEndpoint implements java.io.Serializable {

	private static final long serialVersionUID = -112230200465187936L;

	protected InetAddress host;
	protected int port;
	protected String objectName;

	public LJMEndpoint(InetAddress host, int port, String objectName) {
		this.host = host;
		this.port = port;
		this.objectName = objectName;
	}

	public LJMEndpoint(LJMEndpoint endpt2) {
		this.host = endpt2.getHost();
		this.port = endpt2.getPort();
		this.objectName = endpt2.getObjectName();
	}

	public void setHost(InetAddress host) {
		this.host = host;
	}

	public InetAddress getHost() {
		return host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectName() {
		return objectName;
	}

	public String toString() {
		return "LJMEndpoint{host=\"" + host + "\", port=\"" + port + "\", objectName=\"" + objectName + "\"};";
	}

}
