package org.archstudio.ljm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

@SuppressWarnings("rawtypes")
public class LJMProxyInvoker implements InvocationHandler {

	protected String objectName;
	protected Class[] interfaceClasses;

	protected LJMEndpoint endpoint;

	protected Socket socket;
	protected InputStream is;
	protected OutputStream os;
	protected ObjectInputStream ois;
	protected ObjectOutputStream oos;

	public LJMProxyInvoker(String objectName, Class[] interfaceClasses, LJMEndpoint endpoint) {
		this.objectName = objectName;
		this.interfaceClasses = interfaceClasses;

		this.endpoint = endpoint;
	}

	protected void connect() throws LJMException {
		if (socket == null) {
			try {
				socket = new Socket(endpoint.getHost(), endpoint.getPort());
				is = new BufferedInputStream(socket.getInputStream());
				os = new BufferedOutputStream(socket.getOutputStream());

				//Do NOT reverse these two lines
				oos = new ObjectOutputStream(os);

				// Note: ObjectOutputStream sends an initial short across the stream
				// we have to flush the stream so that this short is sent and the client
				// does not hang waiting for it.
				oos.flush();

				ois = new ObjectInputStream(is);
			}
			catch (IOException ioe) {
				close();
				throw new LJMException("Can't connect to remote host (" + endpoint.getHost() + " : "
						+ endpoint.getPort() + ": " + ioe.toString(), ioe);
			}
		}
	}

	public void close() {
		if (oos != null) {
			try {
				oos.writeBoolean(false);
			}
			catch (IOException ignored) {
			}
		}

		try {
			if (is != null) {
				is.close();
			}
		}
		catch (IOException ignored) {
		}

		try {
			if (ois != null) {
				ois.close();
			}
		}
		catch (IOException ignored2) {
		}

		try {
			if (os != null) {
				os.close();
			}
		}
		catch (IOException ignored3) {
		}

		try {
			if (oos != null) {
				oos.close();
			}
		}
		catch (IOException ignored4) {
		}

		try {
			if (socket != null) {
				socket.close();
			}
		}
		catch (IOException ignored5) {
		}
		is = null;
		os = null;
		ois = null;
		oos = null;
		socket = null;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return invoke(proxy, method, args, 5);
	}

	protected Object invoke(Object proxy, Method method, Object[] args, int retryCount) throws Throwable {
		boolean connected = false;

		int connectRetryCount = 2;
		while (!connected) {
			try {
				connect();
				connected = true;
			}
			catch (LJMException ljme) {
				//Okay, let's back off and retry
				connectRetryCount--;
				if (connectRetryCount == 0) {
					throw ljme;
				}
				try {
					Thread.sleep(500);
				}
				catch (InterruptedException ie) {
				}
			}
		}

		//System.out.println("Invoking: " + endpoint.getHost() + " " + endpoint.getObjectName() + " " + method.getName());
		try {
			oos.writeBoolean(true);
			oos.writeObject(endpoint.getObjectName());
			oos.writeObject(method.getName());
			oos.writeObject(ClassArrayEncoder.classArrayToStringArray(method.getParameterTypes()));
			oos.writeObject(args);
			oos.flush();
		}
		catch (IOException ioe1) {
			//The method call failed, but we can retry.
			close();
			if (retryCount == 0) {
				throw new LJMException("Call failed: " + ioe1.toString(), ioe1, true);
			}
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
			}
			return invoke(proxy, method, args, retryCount - 1);
		}

		try {
			int status = ois.readInt();
			if (status == LJMServer.LJM_STAT_DONE) {
				//System.out.println("Done0");
				return null;
			}
			else if (status == LJMServer.LJM_STAT_RETVAL) {
				Object o = ois.readObject();
				//System.out.println("Done1");
				return o;
			}
			else if (status == LJMServer.LJM_STAT_EXCEPTION) {
				Object ot = ois.readObject();
				//System.out.println("ot = " + ot);
				//System.out.println("ot.class = " + ot.getClass());
				Throwable t = (Throwable) ot;
				//System.out.println("Done2");
				t.printStackTrace();
				throw t;
			}
			else if (status == LJMServer.LJM_STAT_ERROR) {
				LJMException ljme = (LJMException) ois.readObject();
				close();
				//System.out.println("Done3");
				throw ljme;
			}
			else {
				close();
				//System.out.println("Done4");
				throw new LJMException("Protocol error.");
			}
		}
		catch (IOException ioe2) {
			close();
			//The call completed.  We should not retry.
			//System.out.println("Done5");
			throw new LJMException("Call completed but protocol failed: " + ioe2.toString(), true);
		}
	}

}
