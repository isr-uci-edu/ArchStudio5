package org.archstudio.eclipse.ui.editors;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.eclipse.ui.IFocusEditorListener;
import org.archstudio.eclipse.ui.XadlEditorMatchingStrategy;
import org.archstudio.eclipse.ui.views.AbstractArchStudioOutlinePage;
import org.archstudio.editormanager.IEditorManager;
import org.archstudio.filemanager.CantOpenFileException;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.myx.fw.Services;
import org.archstudio.resources.IResources;
import org.archstudio.swtutils.Banner;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.DelayedExecuteOnceThread;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.variability.IXArchADTVariabilityListener;
import org.archstudio.xarchadt.variability.XArchADTVariabilityEvent;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.operations.UndoRedoActionGroup;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public abstract class AbstractArchStudioEditor<B extends AbstractArchStudioEditorMyxComponent> extends EditorPart
		implements ISelectionChangedListener, IXArchADTModelListener, IFocusEditorListener, IFileManagerListener,
		IXArchADTVariabilityListener {

	protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
	protected final B brick;
	protected final Services services = new Services();

	protected boolean hasBanner = false;
	protected boolean updateOnSelectionChange = true;
	protected boolean updateOutlineOnXArchFlatEvent = true;
	protected boolean updateEditorOnXArchFlatEvent = true;
	protected boolean handleUnattachedXArchFlatEvents = false;

	protected String editorName = null;
	protected Image icon = null;
	protected String secondaryText = null;

	protected IXArchADT xarch;
	protected IFileManager fileman;
	protected IEditorManager editorManager;
	protected IResources resources;

	protected AbstractArchStudioOutlinePage outlinePage = null;
	protected ObjRef documentRootRef = null;

	protected Composite parent = null;

	protected String uniqueEditorID = null;

	private final DelayedExecuteOnceThread updateThread = new DelayedExecuteOnceThread(250, new Runnable() {

		@Override
		public void run() {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (updateEditorOnXArchFlatEvent) {
						updateEditor();
					}
					if (updateOutlineOnXArchFlatEvent) {
						updateOutlinePage();
					}
				}
			});
		};
	});

	public AbstractArchStudioEditor(Class<B> brickClass, String editorName) {
		super();
		InstantiateArchStudio.instantiate();
		this.brick = myxRegistry.waitForBrick(brickClass);
		this.uniqueEditorID = UIDGenerator.generateUID(editorName);
		this.editorName = editorName;
		myxRegistry.map(brick, this);
		services.add(this);
		xarch = services.add(brick.getXarch());
		fileman = services.add(brick.getFileManager());
		editorManager = services.add(brick.getEditorManager());
		resources = services.add(brick.getResources());

		updateThread.start();
	}

	protected void setHasBanner(boolean hasBanner) {
		this.hasBanner = hasBanner;
	}

	protected void setUpdateOnSelectionChange(boolean updateOnSelectionChange) {
		this.updateOnSelectionChange = updateOnSelectionChange;
	}

	protected void setUpdateOutlineOnXArchFlatEvent(boolean update) {
		this.updateOutlineOnXArchFlatEvent = update;
	}

	protected void setUpdateEditorOnXArchFlatEvent(boolean update) {
		this.updateEditorOnXArchFlatEvent = update;
	}

	protected void setHandleUnattachedXArchFlatEvents(boolean handle) {
		this.handleUnattachedXArchFlatEvents = handle;
	}

	protected void setBannerInfo(Image icon, String secondaryText) {
		this.icon = icon;
		this.secondaryText = secondaryText;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof IFileEditorInput) && !(input instanceof IPathEditorInput)) {
			throw new PartInitException("Input to " + editorName + " must be an XML file");
		}
		XadlEditorMatchingStrategy xadlChecker = new XadlEditorMatchingStrategy();
		if (!xadlChecker.matches(null, input)) {
			throw new PartInitException("Input to " + editorName + " must have root tag <xArch>");
		}

		if (input instanceof IFileEditorInput) {
			IFile f = ((IFileEditorInput) input).getFile();
			ObjRef ref = null;
			try {
				ref = fileman.open(uniqueEditorID, f);
			}
			catch (CantOpenFileException cofe) {
				throw new PartInitException("Can't open file.", cofe);
			}

			setPartName(f.getName() + " - " + super.getPartName());

			setSite(site);
			setInput(input);
			setDocumentRootRef(ref);
		}
		else if (input instanceof IPathEditorInput) {
			IPath p = ((IPathEditorInput) input).getPath();
			java.io.File f = p.toFile();
			ObjRef ref = null;
			try {
				ref = fileman.open(uniqueEditorID, f);
			}
			catch (CantOpenFileException cofe) {
				throw new PartInitException("Can't open file.", cofe);
			}

			setPartName(f.getName() + " - " + super.getPartName());

			setSite(site);
			setInput(input);
			setDocumentRootRef(ref);
		}

		outlinePage = createOutlinePage();
		if (outlinePage != null) {
			outlinePage.addSelectionChangedListener(this);
		}

		// enable undo/redo
		IActionBars actionBars = site.getActionBars();
		IUndoContext undoContext = PlatformUI.getWorkbench().getOperationSupport().getUndoContext();
		UndoRedoActionGroup undoRedo = new UndoRedoActionGroup(this.getSite(), undoContext, true);
		undoRedo.fillActionBars(actionBars);
		actionBars.updateActionBars();
	}

	protected abstract AbstractArchStudioOutlinePage createOutlinePage();

	public void setDocumentRootRef(ObjRef documentRootRef) {
		this.documentRootRef = documentRootRef;
	}

	public ObjRef getDocumentRootRef() {
		return documentRootRef;
	}

	@Override
	protected void setInput(IEditorInput input) {
		super.setInput(input);
	}

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		updateEditor();
	}

	@Override
	public void dispose() {
		clearEditor();
		if (outlinePage != null) {
			outlinePage.removeSelectionChangedListener(this);
		}
		updateThread.terminate();
		if (documentRootRef != null) {
			fileman.close(uniqueEditorID, documentRootRef);
		}
		myxRegistry.unmap(brick, this);
		super.dispose();
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (updateOnSelectionChange) {
			updateEditor();
		}
	}

	public void clearEditor() {
		//		IWorkbenchPartSite site = getSite();
		//		if (site != null) {
		//			site.getShell().getDisplay().syncExec(new Runnable() {
		//
		//				@Override
		//				public void run() {
		if (parent.isDisposed()) {
			return;
		}
		Control[] children = parent.getChildren();
		for (int i = children.length - 1; i >= 0; i--) {
			children[i].dispose();
		}
		//				}
		//			});
		//		}
	}

	public void updateOutlinePage() {
		if (outlinePage != null) {
			outlinePage.updateOutlinePage();
		}
	}

	public void updateEditor() {
		clearEditor();

		//		getSite().getShell().getDisplay().syncExec(new Runnable() {
		//
		//			@Override
		//			public void run() {
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		sc.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		sc.setBackgroundMode(SWT.INHERIT_DEFAULT);
		sc.setExpandHorizontal(true);
		sc.getVerticalBar().setIncrement(10);
		sc.getVerticalBar().setPageIncrement(50);

		Composite c = new Composite(sc, SWT.NONE);
		c.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		sc.setContent(c);
		c.setBackgroundMode(SWT.INHERIT_DEFAULT);
		SWTWidgetUtils.makeWheelFriendly(sc, c);

		GridLayout gridLayout = new GridLayout(1, true);
		gridLayout.marginTop = 5;
		gridLayout.marginBottom = 5;
		gridLayout.marginLeft = 5;
		gridLayout.marginRight = 5;
		gridLayout.verticalSpacing = 5;
		c.setLayout(gridLayout);

		if (hasBanner) {
			Composite header = new Banner(c, icon, editorName, secondaryText,
					resources.getColor(IResources.COLOR_BANNER_BRIGHT),
					resources.getColor(IResources.COLOR_BANNER_DARK));
			header.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		}

		createEditorContents(c);
		c.pack();
		parent.layout(true);
		//			}
		//		});
	}

	public abstract void createEditorContents(Composite parent);

	@Override
	public void doSave(IProgressMonitor monitor) {
		fileman.save(documentRootRef, monitor);
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
	}

	@Override
	public void fileDirtyStateChanged(ObjRef xArchRef, boolean dirty) {
		checkNotNull(xArchRef);

		if (xArchRef.equals(this.documentRootRef)) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {

				@Override
				public void run() {
					firePropertyChange(IEditorPart.PROP_DIRTY);
				}
			});
		}
	}

	@Override
	public void fileSaving(ObjRef xArchRef, IProgressMonitor monitor) {
	}

	@Override
	public boolean isDirty() {
		return fileman.isDirty(documentRootRef);
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		parent.getChildren()[0].setFocus();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return outlinePage;
		}
		return super.getAdapter(key);
	}

	public void doUpdate() {
		updateThread.execute();
	}

	public void doUpdateNow() {
		getSite().getShell().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				updateEditor();
				updateOutlinePage();
			}
		});
	}

	@Override
	public final void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		//if((!evt.getIsAttached()) && (!handleUnattachedXArchFlatEvents)){
		//	return;
		//}
		if (documentRootRef == null) {
			return;
		}
		if (documentRootRef.equals(xarch.getDocumentRootRef(evt.getSource()))) {
			updateThread.execute();
			doHandleXArchADTModelEvent(evt);
		}
	}

	public void doHandleXArchADTModelEvent(XArchADTModelEvent evt) {
	}

	@Override
	public void focusEditor(String editorName, ObjRef[] refs) {
		if (editorName.equals(this.editorName)) {
			if (outlinePage != null) {
				outlinePage.focusEditor(editorName, refs);
			}
		}
	}

	@Override
	public void focusEditor(ObjRef[] refs) {
		if (outlinePage != null) {
			outlinePage.focusEditor(editorName, refs);
		}
	}

	protected void showMessage(String message) {
		MessageDialog.openError(getEditorSite().getShell(), "Error", message);
	}

	protected void showError(IStatus status) {
		ErrorDialog.openError(getEditorSite().getShell(), "Error", status.getMessage(), status);
	}

	public Composite getParentComposite() {
		return parent;
	}

	@Override
	public void handleXArchADTVariabilityEvent(XArchADTVariabilityEvent evt) {
		if (documentRootRef == null) {
			return;
		}
		if (documentRootRef.equals(evt.getDocumentRootRef())) {
			updateThread.execute();
			doHandleXArchADTVariabilityEvent(evt);
		}

	}

	public void doHandleXArchADTVariabilityEvent(XArchADTVariabilityEvent evt) {
	}
}
