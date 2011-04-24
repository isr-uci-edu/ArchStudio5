package org.archstudio.archipelago.core;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import org.archstudio.editors.common.AbstractArchstudioEditor;
import org.archstudio.editors.common.AbstractArchstudioOutlinePage;
import org.archstudio.graphlayout.common.IGraphLayout;
import org.archstudio.resources.common.IResources;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTFileEvent;
import org.archstudio.xarchadt.common.XArchADTModelEvent;

public class ArchipelagoEditor extends AbstractArchstudioEditor {
	protected IPreferenceStore prefs = null;
	protected IGraphLayout graphLayout = null;

	public ArchipelagoEditor() {
		super(ArchipelagoMyxComponent.class, ArchipelagoMyxComponent.EDITOR_NAME);
		prefs = ((ArchipelagoMyxComponent) comp).getPreferences();
		graphLayout = ((ArchipelagoMyxComponent) comp).getGraphLayout();

		//ArchlightUtils.initResources(resources);

		setBannerInfo(((ArchipelagoMyxComponent) comp).getIcon(), "Graphical Architecture Editor");
		setHasBanner(true);
		setUpdateOnSelectionChange(false);
		setUpdateEditorOnXArchFlatEvent(false);
	}

	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		super.init(site, input);

		setSite(site);
		setInput(input);
		//setupToolbar(site);
	}

	protected AbstractArchstudioOutlinePage createOutlinePage() {
		return new ArchipelagoOutlinePage(this, xarch, getDocumentRootRef(), resources, fileman, editorManager, prefs, graphLayout);
	}

	/*
	 * protected void setupToolbar(IEditorSite site){ IActionBars bars =
	 * site.getActionBars(); IToolBarManager manager = bars.getToolBarManager();
	 * IAction[] actions = getToolbarActions(); for(int i = 0; i <
	 * actions.length; i++){ manager.add(actions[i]); } }
	 */

	public void createEditorContents(Composite c) {
		//System.out.println("create editor contents");
		Label l = new Label(c, SWT.NONE);
		l.setText("Double-click a node in the outline view to begin.");
		l.setFont(resources.getFont(IResources.PLATFORM_HEADER_FONT_ID));
		l.setBackground(c.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	}

	public void setFocus() {
		// parent.getChildren()[0].setFocus();
	}

	public void doHandleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (outlinePage != null) {
			((ArchipelagoOutlinePage) outlinePage).handleXArchFlatEvent(evt);
		}
	}

	public void handleXArchADTFileEvent(XArchADTFileEvent evt) {
		if (outlinePage != null) {
			((ArchipelagoOutlinePage) outlinePage).handleXArchFileEvent(evt);
		}
	}

	public void fileDirtyStateChanged(ObjRef xArchRef, boolean dirty) {
		super.fileDirtyStateChanged(xArchRef, dirty);
		if (outlinePage != null) {
			((ArchipelagoOutlinePage) outlinePage).fileDirtyStateChanged(xArchRef, dirty);
		}
	}

	public void fileSaving(ObjRef xArchRef, IProgressMonitor monitor) {
		super.fileSaving(xArchRef, monitor);
		if (outlinePage != null) {
			((ArchipelagoOutlinePage) outlinePage).fileSaving(xArchRef, monitor);
		}
	}
}
