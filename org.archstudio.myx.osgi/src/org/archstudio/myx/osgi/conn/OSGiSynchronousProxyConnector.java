package org.archstudio.myx.osgi.conn;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.osgi.MyxOSGiBrickDescription;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Myx brick: "OSGi Synch Proxy Connector"
 * 
 * @see org.archstudio.myx.osgi.conn.OSGiSynchronousProxyConnectorStub
 * @generated
 */
public class OSGiSynchronousProxyConnector extends org.archstudio.myx.osgi.conn.OSGiSynchronousProxyConnectorStub {

	protected ClassLoader getClassLoader(IMyxBrickItems brickItems) {
		if (brickItems.getBrickDescription() instanceof MyxOSGiBrickDescription) {
			String bundleName = ((MyxOSGiBrickDescription) brickItems.getBrickDescription()).getOsgiBundleName();
			final Bundle bundle = Platform.getBundle(bundleName);
			return new ClassLoader() {

				public Class<?> loadClass(String name) throws ClassNotFoundException {
					return bundle.loadClass(name);
				}

				protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
					return bundle.loadClass(name);
				}

				protected Class<?> findClass(String name) throws ClassNotFoundException {
					throw new UnsupportedOperationException();
				}

				public URL getResource(String name) {
					throw new UnsupportedOperationException();
				}

				public Enumeration<URL> getResources(String name) throws IOException {
					throw new UnsupportedOperationException();
				}

				protected URL findResource(String name) {
					throw new UnsupportedOperationException();
				}

				protected Enumeration<URL> findResources(String name) throws IOException {
					throw new UnsupportedOperationException();
				}

				public InputStream getResourceAsStream(String name) {
					throw new UnsupportedOperationException();
				}

				protected Package definePackage(String name, String specTitle, String specVersion, String specVendor,
						String implTitle, String implVersion, String implVendor, URL sealBase)
						throws IllegalArgumentException {
					throw new UnsupportedOperationException();
				}

				protected Package getPackage(String name) {
					throw new UnsupportedOperationException();
				}

				protected Package[] getPackages() {
					throw new UnsupportedOperationException();
				}

				protected String findLibrary(String libname) {
					throw new UnsupportedOperationException();
				}

				public synchronized void setDefaultAssertionStatus(boolean enabled) {
					throw new UnsupportedOperationException();
				}

				public synchronized void setPackageAssertionStatus(String packageName, boolean enabled) {
					throw new UnsupportedOperationException();
				}

				public synchronized void setClassAssertionStatus(String className, boolean enabled) {
					throw new UnsupportedOperationException();
				}

				public synchronized void clearAssertionStatus() {
					throw new UnsupportedOperationException();
				}

			};
		}
		return super.getClassLoader(brickItems);
	}

}