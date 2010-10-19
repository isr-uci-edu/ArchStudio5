package org.archstudio.archipelago.core;

import org.eclipse.jface.preference.IPreferenceStore;

import org.archstudio.filemanager.common.IFileManager;
import org.archstudio.graphlayout.common.IGraphLayout;
import org.archstudio.resources.common.IResources;
import org.archstudio.xarchadt.common.IXArchADT;

public class ArchipelagoServices{
	public IArchipelagoEventBus eventBus = null;
	public IArchipelagoEditorPane editor = null;
	public IArchipelagoTreeNodeDataCache treeNodeDataCache = null;
	public IXArchADT xarch = null;
	public IFileManager fileman = null;
	public IResources resources = null;
	public IPreferenceStore prefs = null;
	public IGraphLayout graphLayout = null;
	
	public ArchipelagoServices(IArchipelagoEventBus eventBus,
		                         IArchipelagoEditorPane editor, 
	                           IArchipelagoTreeNodeDataCache treeNodeDataCache, 
	                           IXArchADT xarch,
	                           IResources resources,
	                           IFileManager fileman,
	                           IPreferenceStore prefs,
	                           IGraphLayout graphLayout){
		
		this.eventBus = eventBus;
		this.editor = editor;
		this.treeNodeDataCache = treeNodeDataCache;
		this.xarch = xarch;
		this.resources = resources;
		this.fileman = fileman;
		this.prefs = prefs;
		this.graphLayout = graphLayout;
	}
}
