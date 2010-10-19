package org.archstudio.editormanager.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.archstudio.editormanager.common.EditorConstants;
import org.archstudio.editormanager.common.IEditorManager;
import org.archstudio.editors.common.IFocusEditorListener;
import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxName;
import org.archstudio.myx.fw.MyxUtils;
import org.archstudio.xarchadt.common.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Image;

public class EditorManagerMyxComponent extends AbstractMyxSimpleBrick implements IMyxDynamicBrick {

	public static final IMyxName INTERFACE_NAME_IN_EDITORMANAGER = MyxUtils.createName("editormanager");
	public static final IMyxName INTERFACE_NAME_OUT_PREFERENCES = MyxUtils.createName("preferences");
	public static final IMyxName INTERFACE_NAME_OUT_FOCUSEDITOREVENTS = MyxUtils.createName("focuseditorevents");

	protected IEditorManager editorManager;

	protected IPreferenceStore preferences;
	protected IFocusEditorListener focusEditorListener;

	public EditorManagerMyxComponent() {
	}

	public void init() {
		editorManager = new EditorManager();
	}

	public void destroy() {
		editorManager = null;
	}

	public synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_FOCUSEDITOREVENTS)) {
			focusEditorListener = (IFocusEditorListener) serviceObject;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			preferences = (IPreferenceStore) serviceObject;
		}
	}

	public synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
		if (interfaceName.equals(INTERFACE_NAME_OUT_FOCUSEDITOREVENTS)) {
			focusEditorListener = null;
		}
		else if (interfaceName.equals(INTERFACE_NAME_OUT_PREFERENCES)) {
			preferences = null;
		}
	}

	public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
	}

	public Object getServiceObject(IMyxName interfaceName) {
		if (interfaceName.equals(INTERFACE_NAME_IN_EDITORMANAGER)) {
			return editorManager;
		}
		return null;
	}

	protected class EditorManager implements IEditorManager {
		protected Map<String, RegisteredEditor> registeredEditors = Collections.synchronizedMap(new HashMap<String, RegisteredEditor>());

		public void focusEditor(String name, ObjRef[] refs) {
			synchronized (EditorManagerMyxComponent.this) {
				if (focusEditorListener != null) {
					focusEditorListener.focusEditor(name, refs);
				}
			}
		}

		public String getDefaultEditor() {
			String defaultEditor = preferences.getString(EditorConstants.PREF_DEFAULT_EDITOR);
			if (defaultEditor == null)
				return null;
			if (defaultEditor.equals(EditorConstants.NO_EDITOR))
				return null;
			if (registeredEditors.get(defaultEditor) == null)
				return null;
			return defaultEditor;
		}

		public void registerEditor(String name, Image icon) {
			RegisteredEditor re = new RegisteredEditor(name, icon);
			registeredEditors.put(name, re);
		}

		public void unregisterEditor(String name) {
			registeredEditors.remove(name);
		}

		public boolean isEditorRegistered(String name) {
			return registeredEditors.get(name) != null;
		}

		public String[] getEditors() {
			return (String[]) registeredEditors.keySet().toArray(new String[registeredEditors.keySet().size()]);
		}

		public Image getIcon(String name) {
			RegisteredEditor re = (RegisteredEditor) registeredEditors.get(name);
			if (re == null) {
				return null;
			}
			return re.getIcon();
		}
	}

	static private class RegisteredEditor {
		protected String name;
		protected Image icon;

		public RegisteredEditor(String name, Image icon) {
			this.name = name;
			this.icon = icon;
		}

		public Image getIcon() {
			return icon;
		}

		public String getName() {
			return name;
		}
	}

}
