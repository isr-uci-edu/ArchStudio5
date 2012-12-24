package org.archstudio.sysutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Hashtable;

public class NativeProcess extends Thread {

	protected Process p;

	protected Object lock = new Object();

	protected InputStream processStdin;
	protected long stdinLen = -1;

	protected ByteArrayOutputStream processStdout;
	protected ByteArrayOutputStream processStderr;

	protected boolean hasExited;
	protected int exitCode;

	protected boolean hasJavaError;
	protected Throwable javaError;

	protected boolean terminateNow = false;

	protected Hashtable<String, Object> properties = new Hashtable<String, Object>();

	public NativeProcess(Process p) {
		this.p = p;
		processStdout = new ByteArrayOutputStream();
		processStderr = new ByteArrayOutputStream();
		hasExited = false;
		exitCode = -1;
		hasJavaError = false;
		javaError = null;
	}

	protected NativeProcess(Process p, InputStream stdin) {
		this(p);
		this.processStdin = stdin;
	}

	public NativeProcess(Process p, InputStream stdin, long stdinLen) {
		this(p, stdin);
		this.stdinLen = stdinLen;
	}

	public NativeProcess(Process p, String processInput) {
		//this(p, new ByteArrayInputStream(processInput.getBytes()), processInput.getBytes().length);
		this(p, new ByteArrayInputStream(processInput.getBytes()), processInput.getBytes().length);
	}

	public void setProperty(String name, Object value) {
		properties.put(name, value);
	}

	public Object getProperty(String name) {
		return properties.get(name);
	}

	public boolean hasExited() {
		return hasExited;
	}

	public int getExitCode() {
		return exitCode;
	}

	public boolean hasJavaError() {
		return hasJavaError;
	}

	public Throwable getJavaError() {
		return javaError;
	}

	public String getStdout() {
		return new String(processStdout.toByteArray());
	}

	public String getStderr() {
		return new String(processStderr.toByteArray());
	}

	public void destroy() {
		terminateNow = true;
	}

	public static synchronized int blt_noblock(InputStream is, OutputStream os) throws IOException {
		if (os == null) {
			throw new RuntimeException("OutputStream was null in blt_noblock.");
		}
		byte[] buf = new byte[512];
		int lenMoved = 0;
		while (is.available() > 0) {
			int len = is.read(buf);
			if (len == -1) {
				break;
			}
			//System.out.write(buf, 0, len);
			os.write(buf, 0, len);
			lenMoved += len;
		}
		return lenMoved;
	}

	public void run() {
		long bytesLeft = stdinLen;
		InputStream in = null;
		InputStream err = null;
		OutputStream out = null;
		//BufferedOutputStream bout = null;
		try {
			int exitValue = -1; // returned to caller when p is finished

			if (processStdin != null) {
				out = p.getOutputStream();
			}
			in = p.getInputStream();
			err = p.getErrorStream();

			boolean finished = false; // Set to true when p is finished

			while (!finished) {
				try {
					if (terminateNow) {
						throw new InterruptedException();
					}

					if (processStdin != null) {
						while (processStdin.available() > 0) {
							int bytesRead = blt_noblock(processStdin, out);
							bytesLeft -= bytesRead;
							//System.out.println("bytesLeft = " + bytesLeft);
							if (bytesLeft == 0) {
								processStdin.close();
								out.flush();
								out.close();
							}
						}
					}

					while (in.available() > 0) {
						blt_noblock(in, processStdout);
					}

					if (terminateNow) {
						throw new InterruptedException();
					}

					while (err.available() > 0) {
						blt_noblock(err, processStderr);
					}

					if (terminateNow) {
						throw new InterruptedException();
					}

					// Ask the process for its exitValue. If the process
					// is not finished, an IllegalThreadStateException
					// is thrown. If it is finished, we fall through and
					// the variable finished is set to true.

					exitValue = p.exitValue();
					finished = true;

				}
				catch (IllegalThreadStateException e) {
					// Process is not finished yet;
					// Sleep a little to save on CPU cycles
					try {
						Thread.sleep(250);
					}
					catch (InterruptedException ie) {
					}
				}
			}

			//Grab any leftovers.
			while (in.available() > 0) {
				blt_noblock(in, processStdout);
			}

			while (err.available() > 0) {
				blt_noblock(err, processStderr);
			}

			in.close();
			err.close();

			processStdout.close();
			processStderr.close();

			exitCode = exitValue;
			hasExited = true;
			notifyOfCompletion();
		}
		catch (IOException e) {
			hasExited = true;
			hasJavaError = true;
			javaError = e;
			notifyOfCompletion();
		}
		catch (InterruptedException e) {
			hasExited = true;
			p.destroy();
			hasJavaError = true;
			javaError = e;
			notifyOfCompletion();
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (IOException e1) {
				}
			}
			if (err != null) {
				try {
					err.close();
				}
				catch (IOException e2) {
				}
			}
			notifyOfCompletion();
		}
	}

	public void waitForCompletion() {
		synchronized (lock) {
			while (!hasExited) {
				try {
					lock.wait();
				}
				catch (InterruptedException e) {
				}
			}
		}
	}

	public void notifyOfCompletion() {
		synchronized (lock) {
			lock.notifyAll();
		}
	}

}
