package org.archstudio.ljm;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

public class LJMURL implements java.io.Serializable {

	private static final long serialVersionUID = 1239612043748440884L;

	public static final String DEFAULT_PROTOCOL = "ljm";

	protected String protocol;
	protected String host;
	protected int port;
	protected String object;

	public LJMURL(String protocol, InetAddress inethost, int port, String object) {
		this.protocol = protocol;
		this.host = inethost.getHostName();
		this.port = port;
		this.object = object;
	}

	public LJMURL(String protocol, String host, int port, String object) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.object = object;
	}

	public LJMURL(String spec) throws MalformedURLException {
		if (!spec.startsWith("ljm://")) {
			throw new MalformedURLException("Invalid protocol.");
		}

		protocol = "ljm";

		spec = spec.substring(6);
		if (spec.indexOf("/") == -1) {
			throw new MalformedURLException("Object name separator missing.");
		}

		if (spec.indexOf("/") != spec.lastIndexOf("/")) {
			throw new MalformedURLException("Too many slashes.");
		}

		String hostPort = spec.substring(0, spec.indexOf("/"));
		object = spec.substring(spec.indexOf("/") + 1);

		int colonIndex = hostPort.indexOf(":");

		if (colonIndex == -1) {
			host = hostPort;
			throw new MalformedURLException("Nameless LJM has no default port.");
		}

		host = hostPort.substring(0, colonIndex);
		String portString = hostPort.substring(colonIndex + 1);
		try {
			port = Integer.parseInt(portString);
		}
		catch (NumberFormatException nfe) {
			throw new MalformedURLException("Invalid port number.");
		}
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getObject() {
		return object;
	}

	public LJMEndpoint getEndpoint() throws UnknownHostException {
		InetAddress inetHost = InetAddress.getByName(host);
		return new LJMEndpoint(inetHost, port, object);
	}

	public boolean equals(Object o) {
		if (!(o instanceof LJMURL)) {
			return false;
		}
		LJMURL otherURL = (LJMURL) o;
		return otherURL.getProtocol().equals(getProtocol()) && otherURL.getHost().equals(getHost())
				&& otherURL.getPort() == getPort() && otherURL.getObject().equals(getObject());
	}

	public int hashCode() {
		return getHost().hashCode() ^ port ^ getObject().hashCode();
	}

	public String toString() {
		return protocol + "://" + host + ":" + port + "/" + object;
	}

}
