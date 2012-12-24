package org.archstudio.archipelago.core;

import org.archstudio.eclipse.ui.editors.AbstractArchStudioEditor;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.graphlayout.IGraphLayout;
import org.archstudio.resources.IResources;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTFileEvent;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IGotoMarker;

public class ArchipelagoEditor extends AbstractArchStudioEditor<ArchipelagoMyxComponent> implements IGotoMarker {
	protected IGraphLayout graphLayout = null;

	public ArchipelagoEditor() {
		super(ArchipelagoMyxComponent.class, ArchipelagoMyxComponent.EDITOR_NAME);
		graphLayout = brick.getGraphLayout();

		//ArchlightUtils.initResources(resources);

		setBannerInfo(brick.getIcon(), "Graphical Architecture Editor");
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

	protected AbstractArchStudioOutlinePage createOutlinePage() {
		return new ArchipelagoOutlinePage(this, xarch, getDocumentRootRef(), resources, fileman, editorManager,
				graphLayout);
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

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class key) {
		if (key.equals(IGotoMarker.class)) {
			return this;
		}
		return super.getAdapter(key);
	}

	public void gotoMarker(IMarker marker) {
		ObjRef objRef = xarch.getByID(documentRootRef, marker.getAttribute(IMarker.LOCATION, null));
		if (objRef != null) {
			focusEditor(editorName, new ObjRef[] { objRef });
		}

	}
}
