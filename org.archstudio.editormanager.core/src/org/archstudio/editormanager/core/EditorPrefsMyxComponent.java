package org.archstudio.editormanager.core;

import org.archstudio.editormanager.common.IEditorManager;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.MyxUtils;
import org.eclipse.jface.preference.IPreferenceStore;

public class EditorPrefsMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_OUT_EDITORMANAGER = MyxUtils.createName("editormanager");
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");

	protected IPreferenceStore prefs = null;
	protected IEditorManager editorManager = null;

	private MyxRegistry er = MyxRegistry.getSharedInstance();

	public EditorPrefsMyxComponent() {
	}

	public void begin() {
		er.register(this);
	}

	public void end() {
		er.unregister(this);
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			prefs = (IPreferenceStore) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_EDITORMANAGER)) {
			editorManager = (IEditorManager) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			prefs = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_EDITORMANAGER)) {
			editorManager = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		return null;
	}

}
