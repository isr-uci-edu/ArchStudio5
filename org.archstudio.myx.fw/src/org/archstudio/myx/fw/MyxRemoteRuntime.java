package org.archstudio.myx.fw;

import java.io.IOException;

import org.archstudio.ljm.LJMProxyFactory;
import org.archstudio.ljm.LJMServer;


public class MyxRemoteRuntime extends MyxBasicRuntime {

	public static void main(String[] args) throws IOException {
		String registryHost = "localhost";
		int registryPort = 0;
		String registryName = "MyxRegistry";
		String runtimeHost = "localhost";
		String runtimeName = "MyxRuntime";

		for (int i = 0; i < args.length; i++) {
			if ("-registryHost".equals(args[i])) {
				registryHost = args[++i];
			}
			else if ("-registryPort".equals(args[i])) {
				registryPort = Integer.parseInt(args[++i]);
			}
			else if ("-registryName".equals(args[i])) {
				registryName = args[++i];
			}
			else if ("-runtimeHost".equals(args[i])) {
				runtimeHost = args[++i];
			}
			else if ("-runtimeName".equals(args[i])) {
				runtimeName = args[++i];
			}
		}

		IMyxRuntimeRegistry myxRegistry = (IMyxRuntimeRegistry) LJMProxyFactory.createProxy(registryHost, registryPort, registryName,
		        new Class[] { IMyxRuntimeRegistry.class });
		MyxRemoteRuntime myxRuntime = new MyxRemoteRuntime();

		LJMServer server = new LJMServer();
		server.deploy(runtimeName, myxRuntime);
		myxRegistry.addLJMRuntime(runtimeName, runtimeHost, server.getPort());
	}
}
