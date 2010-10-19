package org.archstudio.eclipsedev.core;

import org.archstudio.dblgen.common.IDataBindingGenerator;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.jface.preference.IPreferenceStore;

public class EclipseDevMyxComponent extends AbstractMyxSimpleBrick {
	public static final IMyxName INTERFACE_NAME_OUT_DATABINDING = MyxUtils.createName("databinding");
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_IN_MONITOR = MyxUtils.createName("monitor");

	protected IPreferenceStore prefs;
	protected IDataBindingGenerator dataBindingGenerator;

	public EclipseDevMyxComponent() {
	}

	public void begin() {
		super.begin();
		dataBindingGenerator = (IDataBindingGenerator) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_DATABINDING);
		prefs = (IPreferenceStore) MyxUtils.getFirstRequiredServiceObject(this, INTERFACE_NAME_OUT_PREFERENCES);
		MyxRegistry.getSharedInstance().register(this);
	}

	public IDataBindingGenerator getDataBindingGenerator() {
		return dataBindingGenerator;
	}
	
	public IPreferenceStore getPreferenceStore() {
		return prefs;
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_MONITOR)) {
			return null;
		}
		return null;
	}

}
