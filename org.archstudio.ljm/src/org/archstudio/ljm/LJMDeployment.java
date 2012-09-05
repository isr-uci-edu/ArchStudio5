package org.archstudio.ljm;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Hashtable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LJMDeployment {

	private static Hashtable objectServers = new Hashtable();
	private static Hashtable refCounts = new Hashtable();

	private static LJMServer getObjectServer(int port) throws IOException, UnknownHostException {
		synchronized (objectServers) { // so no concurrent creation
			LJMServer existingServer = (LJMServer) objectServers.get(new Integer(port));
			if (existingServer != null) {
				return existingServer;
			}

			LJMServer objectServer = new LJMServer(port);
			if (port == 0) {
				port = objectServer.getPort();
			}

			objectServers.put(new Integer(port), objectServer);
			refCounts.put(new Integer(port), new Integer(0));
			return objectServer;
		}
	}

	private static void destroyObjectServer(int port) {
		LJMServer objectServer = (LJMServer) objectServers.get(new Integer(port));

		if (objectServer != null) {
			objectServer.destroy();
			objectServers.remove(new Integer(port));
			refCounts.remove(new Integer(port));
		}
	}

	public static int deploy(String objectName, Object o) throws LJMException {
		return deploy(objectName, o, 0);
	}

	//Returns port number that it's running on.  This is only different
	//from port passed in when port passed in is 0.
	public static int deploy(String objectName, Object o, int port) throws LJMException {
		LJMServer objectServer;
		try {
			objectServer = getObjectServer(port);
		}
		catch (UnknownHostException uhe) {
			throw new LJMException("Couldn't resolve local host: " + uhe.toString());
		}
		catch (IOException ioe) {
			throw new LJMException("Couldn't create object server: " + ioe.toString());
		}

		objectServer.deploy(objectName, o);

		Integer refCountInt = (Integer) refCounts.get(new Integer(port));
		if (refCountInt != null) {
			int refCount = refCountInt.intValue();
			refCount++;
			refCounts.put(new Integer(port), new Integer(refCount));
		}
		else {
			refCounts.put(new Integer(port), new Integer(1));
		}
		return objectServer.getPort();
	}

	public static void undeploy(String objectName, int port) throws LJMException {
		LJMServer objectServer = (LJMServer) objectServers.get(new Integer(port));
		objectServer.undeploy(objectName);
		Integer refCountInt = (Integer) refCounts.get(new Integer(port));
		if (refCountInt != null) {
			int refCount = refCountInt.intValue();
			refCount--;
			if (refCount == 0) {
				destroyObjectServer(port);
			}
			else {
				refCounts.put(new Integer(port), new Integer(refCount));
			}
		}
	}

}
