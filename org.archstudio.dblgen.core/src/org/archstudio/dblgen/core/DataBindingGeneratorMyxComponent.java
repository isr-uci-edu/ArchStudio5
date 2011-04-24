package org.archstudio.dblgen.core;

import org.eclipse.emf.common.util.Monitor;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;

public class DataBindingGeneratorMyxComponent extends AbstractMyxSimpleBrick {
	public static final IMyxName INTERFACE_NAME_IN_DATABINDING = MyxUtils.createName("databinding");
	public static final IMyxName INTERFACE_NAME_OUT_MONITOR = MyxUtils.createName("monitor");

	protected DataBindingGeneratorImpl impl;
	protected Monitor emfMonitor;

	public DataBindingGeneratorMyxComponent() {
	}

	public void init() {
		super.init();
		impl = new DataBindingGeneratorImpl();
	}

	public void begin() {
		super.begin();
		emfMonitor = (Monitor) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_MONITOR);
		impl.setMonitor(emfMonitor);
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_DATABINDING)) {
			return impl;
		}
		return null;
	}

}
