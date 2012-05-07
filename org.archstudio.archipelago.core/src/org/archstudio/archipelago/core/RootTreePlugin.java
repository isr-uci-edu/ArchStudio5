package org.archstudio.archipelago.core;

import org.archstudio.myx.fw.Services;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TreeViewer;

public class RootTreePlugin extends AbstractArchipelagoTreePlugin {
	public RootTreePlugin(TreeViewer viewer, Services AS, ObjRef documentRootRef) {
		this.contentProvider = new RootContentProvider(AS, documentRootRef);
		this.labelProvider = new RootLabelProvider(AS);

		final TreeViewer fviewer = viewer;
		this.editorFocuser = new IArchipelagoEditorFocuser() {
			@Override
			public void focusEditor(String editorName, ObjRef[] refs) {
				fviewer.setSelection(null);
			}
		};
		initDefaultPreferences(AS.get(IPreferenceStore.class));
	}

	protected void initDefaultPreferences(IPreferenceStore prefs) {
		//prefs.setDefault(ArchipelagoConstants.PREF_ANTIALIAS_GRAPHICS, false);
		//prefs.setDefault(ArchipelagoConstants.PREF_ANTIALIAS_TEXT, false);
		//prefs.setDefault(ArchipelagoConstants.PREF_DECORATIVE_GRAPHICS, false);
		//prefs.setDefault(ArchipelagoConstants.PREF_GRID_SPACING, 24);
		//prefs.setDefault(ArchipelagoConstants.PREF_GRID_DISPLAY_TYPE, GridDisplayType.NONE.name());
	}

}
