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
 * Myx brick: "OSGi Event Pump Connector"
 * 
 * @see org.archstudio.myx.osgi.conn.OSGiEventPumpConnectorStub
 * @generated
 */
public class OSGiEventPumpConnector extends org.archstudio.myx.osgi.conn.OSGiEventPumpConnectorStub {

	@Override
	protected ClassLoader getClassLoader(IMyxBrickItems brickItems) {
		if (brickItems.getBrickDescription() instanceof MyxOSGiBrickDescription) {
			String bundleName = ((MyxOSGiBrickDescription) brickItems.getBrickDescription()).getOsgiBundleName();
			final Bundle bundle = Platform.getBundle(bundleName);
			return new ClassLoader() {

				@Override
				public Class<?> loadClass(String name) throws ClassNotFoundException {
					return bundle.loadClass(name);
				}

				@Override
				protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
					return bundle.loadClass(name);
				}

				@Override
				protected Class<?> findClass(String name) throws ClassNotFoundException {
					throw new UnsupportedOperationException();
				}

				@Override
				public URL getResource(String name) {
					throw new UnsupportedOperationException();
				}

				@Override
				public Enumeration<URL> getResources(String name) throws IOException {
					throw new UnsupportedOperationException();
				}

				@Override
				protected URL findResource(String name) {
					throw new UnsupportedOperationException();
				}

				@Override
				protected Enumeration<URL> findResources(String name) throws IOException {
					throw new UnsupportedOperationException();
				}

				@Override
				public InputStream getResourceAsStream(String name) {
					throw new UnsupportedOperationException();
				}

				@Override
				protected Package definePackage(String name, String specTitle, String specVersion, String specVendor,
						String implTitle, String implVersion, String implVendor, URL sealBase)
						throws IllegalArgumentException {
					throw new UnsupportedOperationException();
				}

				@Override
				protected Package getPackage(String name) {
					throw new UnsupportedOperationException();
				}

				@Override
				protected Package[] getPackages() {
					throw new UnsupportedOperationException();
				}

				@Override
				protected String findLibrary(String libname) {
					throw new UnsupportedOperationException();
				}

				@Override
				public synchronized void setDefaultAssertionStatus(boolean enabled) {
					throw new UnsupportedOperationException();
				}

				@Override
				public synchronized void setPackageAssertionStatus(String packageName, boolean enabled) {
					throw new UnsupportedOperationException();
				}

				@Override
				public synchronized void setClassAssertionStatus(String className, boolean enabled) {
					throw new UnsupportedOperationException();
				}

				@Override
				public synchronized void clearAssertionStatus() {
					throw new UnsupportedOperationException();
				}

			};
		}
		return super.getClassLoader(brickItems);
	}

}