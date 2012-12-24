package org.archstudio.archipelago.core;

import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.dnd.DragSourceListener;

public class AbstractArchipelagoTreePlugin implements IArchipelagoTreePlugin {

	protected IArchipelagoTreeContentProvider contentProvider = null;
	protected IArchipelagoTreeDoubleClickHandler doubleClickHandler = null;
	protected IArchipelagoLabelProvider labelProvider = null;
	protected IArchipelagoTreeContextMenuFiller[] contextMenuFillers = null;
	protected ICellModifier[] cellModifiers = null;
	protected IXArchADTModelListener flatListener = null;
	protected IXArchADTFileListener fileListener = null;
	protected IFileManagerListener fileManagerListener = null;
	protected DragSourceListener dragSourceListener = null;
	protected IArchipelagoEditorFocuser editorFocuser = null;
	protected IArchipelagoEditorPane editor = null;

	@Override
	public IArchipelagoTreeContentProvider getContentProvider() {
		return contentProvider;
	}

	@Override
	public IArchipelagoTreeDoubleClickHandler getDoubleClickHandler() {
		return doubleClickHandler;
	}

	@Override
	public IArchipelagoLabelProvider getLabelProvider() {
		return labelProvider;
	}

	@Override
	public IArchipelagoTreeContextMenuFiller[] getContextMenuFillers() {
		return contextMenuFillers;
	}

	@Override
	public ICellModifier[] getCellModifiers() {
		return cellModifiers;
	}

	@Override
	public IXArchADTFileListener getXArchADTFileListener() {
		return fileListener;
	}

	@Override
	public IXArchADTModelListener getXArchADTModelListener() {
		return flatListener;
	}

	@Override
	public DragSourceListener getDragSourceListener() {
		return dragSourceListener;
	}

	@Override
	public IFileManagerListener getFileManagerListener() {
		return fileManagerListener;
	}

	@Override
	public IArchipelagoEditorFocuser getEditorFocuser() {
		return editorFocuser;
	}

	@Override
	public void setEditor(IArchipelagoEditorPane editor) {
		this.editor = editor;
	}
}
