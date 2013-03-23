package org.archstudio.eclipse.ui;

import org.archstudio.xarchadt.ObjRef;

public interface IFocusEditorListener {
	public void focusEditor(ObjRef[] refs);

	public void focusEditor(String editorName, ObjRef[] refs);
}
