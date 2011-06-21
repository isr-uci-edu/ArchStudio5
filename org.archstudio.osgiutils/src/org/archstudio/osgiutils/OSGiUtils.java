package org.archstudio.osgiutils;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class OSGiUtils {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	// ServiceReference was not generic in Eclipse 3.5
	public static <T> T getServiceReference(BundleContext context, Class<T> service) {
		ServiceReference ref = context.getServiceReference(service.getName());
		return (T) context.getService(ref);
	}

}
