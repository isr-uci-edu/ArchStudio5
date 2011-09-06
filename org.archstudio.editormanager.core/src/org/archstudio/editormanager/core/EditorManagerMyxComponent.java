package org.archstudio.editormanager.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.archstudio.editormanager.EditorConstants;
import org.archstudio.editormanager.IEditorManager;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Image;

/**
 * Myx brick: "org.archstudio.editormanager.core.EditorManagerMyxComponent"
 * 
 * @see org.archstudio.editormanager.core.EditorManagerMyxComponentStub
 * @generated
 */
public class EditorManagerMyxComponent extends org.archstudio.editormanager.core.EditorManagerMyxComponentStub {
	public EditorManagerMyxComponent() {
	}

	@Override
	public void init() {
		editorManager = new EditorManager();
	}

	@Override
	public void destroy() {
		editorManager = null;
	}

	protected class EditorManager implements IEditorManager {
		protected Map<String, RegisteredEditor> registeredEditors = Collections
				.synchronizedMap(new HashMap<String, RegisteredEditor>());

		@Override
		public void focusEditor(String name, ObjRef[] refs) {
			synchronized (EditorManagerMyxComponent.this) {
				if (focusEditorEvents != null) {
					focusEditorEvents.focusEditor(name, refs);
				}
			}
		}

		@Override
		public String getDefaultEditor() {
			String defaultEditor = preferences.getString(EditorConstants.PREF_DEFAULT_EDITOR);
			if (defaultEditor == null) {
				return null;
			}
			if (defaultEditor.equals(EditorConstants.NO_EDITOR)) {
				return null;
			}
			if (registeredEditors.get(defaultEditor) == null) {
				return null;
			}
			return defaultEditor;
		}

		@Override
		public void registerEditor(String name, Image icon) {
			RegisteredEditor re = new RegisteredEditor(name, icon);
			registeredEditors.put(name, re);
		}

		@Override
		public void unregisterEditor(String name) {
			registeredEditors.remove(name);
		}

		@Override
		public boolean isEditorRegistered(String name) {
			return registeredEditors.get(name) != null;
		}

		@Override
		public String[] getEditors() {
			return registeredEditors.keySet().toArray(new String[registeredEditors.keySet().size()]);
		}

		@Override
		public Image getIcon(String name) {
			RegisteredEditor re = registeredEditors.get(name);
			if (re == null) {
				return null;
			}
			return re.getIcon();
		}
	}

	@SuppressWarnings("unused")
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