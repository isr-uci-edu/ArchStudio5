package org.archstudio.archipelago.core;

import org.archstudio.editormanager.IEditorManager;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.resources.IResources;
import org.archstudio.xarchadt.IXArchADT;
import org.eclipse.jface.preference.IPreferenceStore;

public class ArchipelagoServices {
	//public final IArchipelagoEventBus eventBus;
	public final IArchipelagoEditorPane editor;
	public final IArchipelagoTreeNodeDataCache treeNodeDataCache;
	public final IXArchADT xarch;
	public final IFileManager fileman;
	public final IEditorManager editorManager;
	public final IResources resources;
	public final IPreferenceStore prefs;
	public final IGraphLayout graphLayout;

	public ArchipelagoServices(IArchipelagoEventBus eventBus, IArchipelagoEditorPane editor,
			IArchipelagoTreeNodeDataCache treeNodeDataCache, IXArchADT xarch, IResources resources,
			IFileManager fileman, IEditorManager editorManager, IPreferenceStore prefs, IGraphLayout graphLayout) {

		//TODO: remove this.eventBus = eventBus;
		this.editor = editor;
		this.treeNodeDataCache = treeNodeDataCache;
		this.xarch = xarch;
		this.resources = resources;
		this.fileman = fileman;
		this.editorManager = editorManager;
		this.prefs = prefs;
		this.graphLayout = graphLayout;
	}
}
