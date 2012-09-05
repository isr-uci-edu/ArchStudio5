package org.archstudio.ljm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class LJMServer extends Thread {

	public static final int LJM_STAT_ERROR = 1000;
	public static final int LJM_STAT_DONE = 2000;
	public static final int LJM_STAT_RETVAL = 3000;
	public static final int LJM_STAT_EXCEPTION = 4000;

	protected ServerSocket socket;
	protected Hashtable serverObjects = new Hashtable();

	public LJMServer() throws IOException {
		//Create a socket on any available port
		this(0);
	}

	public LJMServer(int port) throws IOException {
		socket = new ServerSocket(port);
		start();
	}

	public int getPort() {
		return socket.getLocalPort();
	}

	public void deploy(String objectName, Object o) {
		serverObjects.put(objectName, o);
	}

	public Object getDeployedObject(String objectName) {
		return serverObjects.get(objectName);
	}

	public void undeploy(String objectName) {
		serverObjects.remove(objectName);
	}

	@Override
	public void destroy() {
		try {
			socket.close();
		}
		catch (IOException e) {
		}
	}

	@Override
	public void run() {
		LJMConnectionHandler connHandler = null;
		try {
			while (true) {
				Socket slaveSocket = socket.accept();
				connHandler = new LJMConnectionHandler(slaveSocket);
			}
		}
		catch (IOException e) {
			//e.printStackTrace();
			return;
		}
		finally {
			if (connHandler != null) {
				connHandler.close();
				connHandler = null;
			}
		}
	}

	//Protocol is:
	//1.  Read a boolean whether we want to continue or not (true = continue, false = don't)
	//If the boolean was false, we close the connection and return.

	//2.  Read the object name (java.lang.String)
	//3.  Read the method name (java.lang.String)
	//4.  Read the parameter class list (java.lang.Class[])
	//5.  Read the parameter value list (java.lang.Object[])

	//Then we do our thing.  No matter what happens, an integer
	//gets written to the wire:
	//LJM_STAT_ERROR (a network, object lookup, or whatever error happened)
	//LJM_STAT_DONE (the method ran and returned void)
	//LJM_STAT_RETVAL (the method ran and returned a value
	//LJM_STAT_EXCEPTION (the method ran and threw an exception)

	//If LJM_STAT_ERROR happened, then an LJMException is written to the wire 
	//If LJM_STAT_DONE happened, then that's the end of the interaction.
	//If LJM_STAT_RETVAL happened then we write the return value to the wire and
	//that's the end of the interaction
	//If LJM_STAT_EXCEPTION happened, then we write the exception to the wire and
	//that's the end of the interaction.

	//At the end of the interaction, we go back to reading the next interaction.

	class LJMConnectionHandler extends Thread {

		protected Socket slaveSocket;
		protected InputStream is;
		protected OutputStream os;
		protected ObjectInputStream ois;
		protected ObjectOutputStream oos;

		public LJMConnectionHandler(Socket slaveSocket) {
			//System.out.println("Creating new LJMConnectionHandler!");
			//new Throwable().printStackTrace();
			int priority = Thread.NORM_PRIORITY;
			this.setPriority(priority);
			try {
				this.slaveSocket = slaveSocket;
				is = new BufferedInputStream(slaveSocket.getInputStream());
				ois = new ObjectInputStream(is);
				os = new BufferedOutputStream(slaveSocket.getOutputStream());
				oos = new ObjectOutputStream(os);

				// Note: ObjectOutputStream sends an initial short across the stream
				// we have to flush the stream so that this short is sent and the client
				// does not hang waiting for it.
				oos.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
				close();
				return;
			}

			start();
		}

		public void close() {
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
				if (slaveSocket != null) {
					slaveSocket.close();
				}
			}
			catch (IOException ignored5) {
			}
		}

		@Override
		public void run() {
			while (true) {
				//System.err.println("Processing call: ");
				boolean cont;
				try {
					cont = ois.readBoolean();
					//System.err.println("Continuing: "+cont);
				}
				catch (ClassCastException cce1) {
					cce1.printStackTrace();
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Protocol error.", cce1));
						oos.flush();
						close();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					return;
				}

				catch (IOException ioe1) {
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Protocol error."));
						oos.flush();
						close();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					return;
				}

				if (!cont) {
					close();
					return;
				}

				String objectName;
				String methodName;
				Class[] paramClasses;
				Object[] paramValues;

				try {
					objectName = (String) ois.readObject();
					//System.err.println("objectName: "+objectName);
					methodName = (String) ois.readObject();
					//System.err.println("methodName: "+methodName);
					String[] paramClassNames = (String[]) ois.readObject();
					//System.err.println("paramClassNames: "+Arrays.asList(paramClassNames));
					paramClasses = ClassArrayEncoder.stringArrayToClassArray(paramClassNames);
					paramValues = (Object[]) ois.readObject();
					//System.err.println("paramClassValues: "+Arrays.asList(paramValues));
				}
				catch (ClassCastException cce) {
					cce.printStackTrace();
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Protocol error."));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					close();
					return;
				}
				catch (IOException ioe) {
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Protocol error."));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					close();
					return;
				}
				catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Can't find class:" + cnfe.toString()));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					close();
					return;
				}

				//System.out.println("ObjectName= " + objectName);
				Object targetObject = serverObjects.get(objectName);
				//System.err.println("targetObject: "+targetObject);
				if (targetObject == null) {
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Can't locate object.", true));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}

					continue;
				}

				Class targetObjectClass = targetObject.getClass();
				//System.err.println("targetObjectClass: "+targetObjectClass);
				Method targetMethod = null;
				try {
					targetMethod = targetObjectClass.getMethod(methodName, paramClasses);
					//System.err.println("targetMethod: "+targetMethod);
				}
				catch (NoSuchMethodException nsme) {
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Method does not exist."));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}

					continue;
				}

				//System.out.println("Performing method invocation on true object: " + targetMethod);
				Object retVal;
				try {
					//System.err.println("retVal: ?");
					retVal = targetMethod.invoke(targetObject, paramValues);
					//System.err.println("retVal: "+retVal);
					if (targetMethod.getReturnType().equals(void.class)
							|| targetMethod.getReturnType().equals(Void.class)) {
						try {
							oos.writeInt(LJM_STAT_DONE);
							oos.flush();
						}
						catch (IOException writeException) {
							close();
							return;
						}

						continue;
					}
					else {
						if (retVal != null && !(retVal instanceof java.io.Serializable)) {
							try {
								oos.writeInt(LJM_STAT_ERROR);
								oos.writeObject(new LJMException("Return type (" + retVal.getClass().getName()
										+ ") is not serializable."));
								oos.flush();
							}
							catch (IOException writeException) {
								close();
								return;
							}

							continue;
						}
						else {
							try {
								oos.writeInt(LJM_STAT_RETVAL);
								oos.writeObject(retVal);
								oos.flush();
							}
							catch (IOException writeException) {
								close();
								return;
							}
							continue;
						}
					}
				}
				catch (IllegalAccessException iae) {
					iae.printStackTrace();
					try {
						oos.writeInt(LJM_STAT_ERROR);
						oos.writeObject(new LJMException("Illegal access exception invoking method.", iae));
						oos.flush();
					}
					catch (IOException writeException) {
						close();
						return;
					}
					continue;
				}
				catch (InvocationTargetException ite) {
					Throwable t = ite.getTargetException();
					//System.out.println("An exception occurred performing the invocation!");
					t.printStackTrace();
					if (!(t instanceof java.io.Serializable)) {
						try {
							oos.writeInt(LJM_STAT_ERROR);
							oos.writeObject(new LJMException("Exception type is not serializable.", ite));
							oos.flush();
						}
						catch (IOException writeException) {
							close();
							return;
						}

						continue;
					}
					else {
						try {
							oos.writeInt(LJM_STAT_EXCEPTION);
							oos.writeObject(t);
							oos.flush();
						}
						catch (IOException writeException) {
							close();
							return;
						}
						continue;
					}
				}
			}
		}
	}

}
