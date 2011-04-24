package org.archstudio.editormanager.core;

import org.archstudio.editormanager.common.EditorConstants;
import org.archstudio.editormanager.common.IEditorManager;
import org.archstudio.main.ArchStudio5Activator;
import org.archstudio.myx.fw.MyxRegistry;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.framework.BundleException;

public class EditorPreferencePanel extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	private EditorPrefsMyxComponent comp = null;
	private MyxRegistry er = MyxRegistry.getSharedInstance();

	protected IEditorManager editorManager;

	protected RadioGroupFieldEditor defaultEditorEditor;

	public EditorPreferencePanel() {
		super("Editor Preferences", GRID);
		comp = (EditorPrefsMyxComponent) er.waitForBrick(EditorPrefsMyxComponent.class);
		er.map(comp, this);

		setPreferenceStore(comp.prefs);
		editorManager = comp.editorManager;
		setDescription("This panel lets you select ArchStudio 5 Editor preferences, particularly the default editor.");
	}

	public void init(IWorkbench workbench) {
		try {
			Platform.getBundle(ArchStudio5Activator.PLUGIN_ID).start();
		}
		catch (BundleException be) {
			throw new RuntimeException(be);
		}
	}

	protected void createFieldEditors() {
		String[] availableEditors = editorManager.getEditors();

		String[][] editorData = new String[availableEditors.length + 1][];
		editorData[0] = new String[] { "[None]", EditorConstants.NO_EDITOR };
		for (int i = 0; i < availableEditors.length; i++) {
			editorData[i + 1] = new String[] { availableEditors[i], availableEditors[i] };
		}

		defaultEditorEditor = new RadioGroupFieldEditor(EditorConstants.PREF_DEFAULT_EDITOR, "Default Editor", 1, editorData, getFieldEditorParent(), true);
		addField(defaultEditorEditor);
	}

}
