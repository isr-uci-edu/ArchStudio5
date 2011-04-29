package org.archstudio.myx.fw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MyxJavaClassInterfaceDescription implements IMyxInterfaceDescription {

	private static final long serialVersionUID = 3302979474104289249L;

	protected Collection<? extends String> serviceObjectInterfaceNames;

	public MyxJavaClassInterfaceDescription(Collection<? extends String> serviceObjectInterfaceNames) {
		this.serviceObjectInterfaceNames = new ArrayList<String>(serviceObjectInterfaceNames);
	}

	public Collection<? extends String> getServiceObjectInterfaceNames() {
		return Collections.unmodifiableCollection(serviceObjectInterfaceNames);
	}
}
