package org.archstudio.archipelago2.core;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.archstudio.archipelago2.IArchipelago2Editor;
import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.filemanager.CantOpenFileException;
import org.archstudio.filemanager.IFileManager;
import org.archstudio.filemanager.IFileManagerListener;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class Archipelago2Editor extends EditorPart
    implements IFileManagerListener, IXArchADTModelListener {
  protected final MyxRegistry myxRegistry;
  protected final Archipelago2MyxComponent brick;
  protected final String uniqueEditorId;
  protected final IXArchADT xarch;
  protected final IFileManager fileman;

  protected ObjRef docRef = null;
  protected Archipelago2OutlinePage outlinePage = null;
  protected Composite parent = null;
  protected Composite editorParent = null;
  protected IArchipelago2Editor activeEditor = null;
  protected Set<ObjRef> activeEditorRefs = Sets.newHashSet();
  protected List<Object> activeEditorElementPath = null;
  protected Multimap<String, IArchipelago2Editor> editorProviders = ArrayListMultimap.create();

  public Archipelago2Editor() {
    InstantiateArchStudio.instantiate();
    myxRegistry = MyxRegistry.getSharedInstance();
    brick = myxRegistry.waitForBrick(Archipelago2MyxComponent.class);
    myxRegistry.registerObject(brick, this);
    uniqueEditorId = UIDGenerator.generateUID(Archipelago2Editor.class.getName());
    xarch = brick.getXarch();
    fileman = brick.getFileManager();
    IExtensionRegistry reg = Platform.getExtensionRegistry();
    if (reg != null) {
      for (IConfigurationElement configurationElement : reg
          .getConfigurationElementsFor("org.archstudio.archipelago2.editors")) {
        String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
        String editorName = configurationElement.getAttribute("name");
        String editorClassName = configurationElement.getAttribute("class");
        try {
          @SuppressWarnings("unchecked")
          Class<IArchipelago2Editor> editorClass = (Class<IArchipelago2Editor>) Platform
              .getBundle(bundleName).loadClass(editorClassName);
          IArchipelago2Editor editor = editorClass.newInstance();
          editor.init(xarch, docRef);
          editorProviders.put(editorName, editor);
        } catch (Exception e) {
          Activator.getDefault().getLog().log(
              new Status(IStatus.ERROR, bundleName, "Cannot instantiate " + editorClassName, e));
        }
      }
    }
  }

  @Override
  public void init(IEditorSite site, IEditorInput input) throws PartInitException {
    if (input instanceof IFileEditorInput) {
      IFile f = ((IFileEditorInput) input).getFile();
      try {
        f.refreshLocal(IResource.DEPTH_ONE, null);
      } catch (CoreException e) {
        throw new PartInitException("Unable to refresh file: " + f);
      }
      try {
        docRef = fileman.open(uniqueEditorId, f);
      } catch (CantOpenFileException e) {
        throw new PartInitException("Cannot open: " + f, e);
      }
      setPartName(f.getName());
    } else if (input instanceof IPathEditorInput) {
      IPath p = ((IPathEditorInput) input).getPath();
      java.io.File f = p.toFile();
      try {
        docRef = fileman.open(uniqueEditorId, f);
      } catch (CantOpenFileException e) {
        throw new PartInitException("Cannot open: " + f, e);
      }
      setPartName(f.getName());
    } else {
      throw new PartInitException("Unrecognized input: " + input.getClass().getName());
    }
    setSite(site);
    setInput(input);
    outlinePage = new Archipelago2OutlinePage(xarch, docRef);
    // If there is an editor selection event in the outline, update the editor.
    outlinePage.addHardSelectionListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection =
            SystemUtils.castOrNull(event.getSelection(), IStructuredSelection.class);
        if (selection != null) {
          @SuppressWarnings("unchecked")
          List<Object> elementPath =
              SystemUtils.castOrNull(selection.getFirstElement(), List.class);
          if (elementPath != null) {
            handleHardSelection(elementPath);
          }
        }
      }
    });
    myxRegistry.registerObject(brick, outlinePage);
  }

  @Override
  public void dispose() {
    disposeActiveEditor();
    if (docRef != null) {
      fileman.close(uniqueEditorId, docRef);
    }
    outlinePage.dispose();
    super.dispose();
  }

  protected void disposeActiveEditor() {
    // Dispose of current editor.
    if (activeEditor != null) {
      activeEditor.dispose();
      activeEditor = null;
    }
    activeEditorElementPath = null;
    // Remove editor controls.
    if (editorParent != null) {
      editorParent.dispose();
      editorParent = null;
    }
    activeEditorRefs.clear();
  }

  protected void createActiveEditor(Class<? extends IArchipelago2Editor> editorProviderClass,
      List<Object> elementPath) {
    disposeActiveEditor();
    try {
      // Record the ObjRefs relavent to the editor.
      activeEditorRefs.clear();
      for (Object element : elementPath) {
        if (element instanceof ObjRef) {
          activeEditorRefs.add((ObjRef) element);
        }
      }
      // Instantiate the editor.
      activeEditorElementPath = elementPath;
      activeEditor = editorProviderClass.newInstance();
      activeEditor.init(xarch, docRef);
      // Create a composite in which to hold the editor.
      editorParent = new Composite(parent, SWT.NONE);
      activeEditor.createPartControl(this, editorParent, elementPath);
      parent.layout(true);
    } catch (Exception e) {
      disposeActiveEditor();
      Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          "Cannot create editor for " + editorProviderClass, e));
      return;
    }
  }

  @Override
  public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
    if (activeEditorRefs.contains(evt.getOldValue())) {
      getSite().getShell().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          handleHardSelection(Collections.singletonList((Object) docRef));
        }
      });
    }
  }

  @Override
  public void doSave(IProgressMonitor monitor) {
    fileman.save(docRef, monitor);
    firePropertyChange(IEditorPart.PROP_DIRTY);
  }

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  @Override
  public void doSaveAs() {}

  @Override
  public void fileSaving(ObjRef documentRootRef, IProgressMonitor monitor) {}

  @Override
  public void fileDirtyStateChanged(ObjRef xArchRef, boolean dirty) {
    if (xArchRef.equals(docRef)) {
      getSite().getShell().getDisplay().asyncExec(new Runnable() {
        @Override
        public void run() {
          firePropertyChange(IEditorPart.PROP_DIRTY);
        }
      });
    }
  }

  @Override
  public boolean isDirty() {
    if (docRef != null) {
      return fileman.isDirty(docRef);
    }
    return false;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public Object getAdapter(Class adapter) {
    if (IContentOutlinePage.class.equals(adapter)) {
      return outlinePage;
    }
    return super.getAdapter(adapter);
  }

  @Override
  public void createPartControl(Composite parent) {
    parent.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    this.parent = parent;
    handleHardSelection(Collections.singletonList((Object) docRef));
  }

  @Override
  public void setFocus() {}

  /**
   * Opens an editor for the selected element and tries to focus on the particular element.
   * <p/>
   * First attempts to open an editor for the given element. If none exists, then searches for an
   * editor for its parent, traversing up the tree to the root node. Then tries to focus on that
   * node in the given editor.
   *
   * @param elementPath The element selected from the outline view.
   */
  protected void handleHardSelection(List<Object> elementPath) {
    // Search for a potential editor provider.
    Entry<String, IArchipelago2Editor> editorProvider = null;
    List<Object> editorElementPath = elementPath;
    SEARCH: for (int lastElement = elementPath.size(); lastElement >= 0; --lastElement) {
      editorElementPath = elementPath.subList(0, lastElement);
      for (Entry<String, IArchipelago2Editor> potentialProvider : editorProviders.entries()) {
        if (potentialProvider.getValue().canEdit(editorElementPath)) {
          editorProvider = potentialProvider;
          break SEARCH;
        }
      }
    }

    // Check that a provider was found, although this shouldn't happen since
    // Archipelago2DefaultEditor accepts the empty list and should always match.
    if (editorProvider == null) {
      Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
          "No editor is available for " + elementPath));
      return;
    }

    // Create a new editor if (1) the active editor is currently null, (2) the active editor type
    // does not match the provider type selected, or (3) the active editor has a different input
    // element path.
    if (activeEditor == null
        || !activeEditor.getClass().equals(editorProvider.getValue().getClass())
        || !activeEditorElementPath.equals(editorElementPath)) {
      createActiveEditor(editorProvider.getValue().getClass(), editorElementPath);
    }

    // Select the element.
    if (activeEditor != null) {
      activeEditor.setFocus(elementPath);
    }
  }
}
