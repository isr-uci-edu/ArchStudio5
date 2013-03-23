package org.archstudio.archipelago.core;

import org.archstudio.xarchadt.ObjRef;

public interface IArchipelagoEditorFocuser {
	public void focusEditor(ObjRef[] refs);

	public void focusEditor(String editorName, ObjRef[] refs);
}
