package org.archstudio.myx.demo.basic;

import java.util.Collections;
import java.util.Properties;

import org.archstudio.myx.fw.*;


public class App {

	public static final IMyxName serverName = MyxUtils.createName("Server");
	public static final IMyxName clientName = MyxUtils.createName("Client");
	public static final IMyxName proxyName = MyxUtils.createName("Proxy");

	protected IMyxImplementation myximpl;

	public static void main(String[] args) {
		new App();
	}

	public App() {
		myximpl = MyxUtils.getDefaultImplementation();
		doApp1();
		System.err.println("---");
		doApp2();
	}

	public void doApp1() {
		IMyxRuntime myx = myximpl.createRuntime();

		try {
			MyxJavaClassBrickDescription serverDesc = new MyxJavaClassBrickDescription("org.archstudio.myx.demo.basic.ServerComponent");
			MyxJavaClassBrickDescription clientDesc = new MyxJavaClassBrickDescription("org.archstudio.myx.demo.basic.ClientComponent");

			myx.addBrick(null, serverName, serverDesc, null);
			myx.addBrick(null, clientName, clientDesc, null);

			MyxJavaClassInterfaceDescription imathIfaceDesc = new MyxJavaClassInterfaceDescription(Collections.singleton("org.archstudio.myx.demo.basic.IMath"));

			myx.addInterface(null, clientName, MyxUtils.createName("math"), imathIfaceDesc, EMyxInterfaceDirection.OUT);
			myx.addInterface(null, serverName, MyxUtils.createName("math"), imathIfaceDesc, EMyxInterfaceDirection.IN);

			IMyxWeld s2c = myx.createWeld(null, clientName, MyxUtils.createName("math"), null, serverName, MyxUtils.createName("math"));
			myx.addWeld(s2c);

			myx.init(null, null);
			myx.begin(null, null);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doApp2() {
		IMyxRuntime myx = myximpl.createRuntime();

		try {
			MyxJavaClassBrickDescription serverDesc = new MyxJavaClassBrickDescription("org.archstudio.myx.demo.basic.ServerComponent");
			MyxJavaClassBrickDescription proxyDesc = new MyxJavaClassBrickDescription("org.archstudio.myx.conn.SynchronousProxyConnector");
			MyxJavaClassBrickDescription clientDesc = new MyxJavaClassBrickDescription("org.archstudio.myx.demo.basic.ClientComponent");

			myx.addBrick(null, serverName, serverDesc, null);
			myx.addBrick(null, clientName, clientDesc, null);

			Properties proxyProperties = new Properties();
			proxyProperties.put("interfaceClassName0", "org.archstudio.myx.demo.basic.IMath");
			myx.addBrick(null, proxyName, proxyDesc, new MyxBasicBrickInitializationData(proxyProperties));

			MyxJavaClassInterfaceDescription imathIfaceDesc = new MyxJavaClassInterfaceDescription(Collections.singleton("org.archstudio.myx.demo.basic.IMath"));

			myx.addInterface(null, clientName, MyxUtils.createName("math"), imathIfaceDesc, EMyxInterfaceDirection.OUT);

			myx.addInterface(null, proxyName, MyxUtils.createName("out"), imathIfaceDesc, EMyxInterfaceDirection.OUT);
			myx.addInterface(null, proxyName, MyxUtils.createName("in"), imathIfaceDesc, EMyxInterfaceDirection.IN);

			myx.addInterface(null, serverName, MyxUtils.createName("math"), imathIfaceDesc, EMyxInterfaceDirection.IN);

			IMyxWeld p2s = myx.createWeld(null, proxyName, MyxUtils.createName("out"), null, serverName, MyxUtils.createName("math"));
			IMyxWeld c2p = myx.createWeld(null, clientName, MyxUtils.createName("math"), null, proxyName, MyxUtils.createName("in"));

			myx.addWeld(p2s);
			myx.addWeld(c2p);

			myx.init(null, null);
			myx.begin(null, null);

		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
