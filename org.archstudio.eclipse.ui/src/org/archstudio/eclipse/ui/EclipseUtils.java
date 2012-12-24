package org.archstudio.eclipse.ui;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.eclipse.ArchStudioNature;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.ContainerGenerator;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import biz.volantec.utils.widgets.WSFileDialog;

public class EclipseUtils {

	private EclipseUtils() {
	}

	public static IProject[] getOpenArchStudioProjects() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		List<IProject> l = new ArrayList<IProject>();
		IProject[] projects = root.getProjects();
		for (IProject project : projects) {
			if (project.isOpen()) {
				try {
					if (project.getNature(ArchStudioNature.ID) != null) {
						l.add(project);
					}
				}
				catch (CoreException ce) {
					System.out.println(ce);
					ce.printStackTrace();
				}
			}
		}
		return l.toArray(new IProject[l.size()]);
	}

	public static IFileEditorInput getFileEditorInput(IPath path) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(path);
		if (file == null) {
			return null;
		}
		IFileEditorInput fileEditorInput = new FileEditorInput(file);
		return fileEditorInput;
	}

	public static void openEditor(String editorID, IFile file) {
		IWorkbench wb = PlatformUI.getWorkbench();
		if (wb != null) {
			IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
			if (win != null) {
				IWorkbenchPage page = win.getActivePage();
				if (page != null) {
					try {
						IFileEditorInput fileEditorInput = new FileEditorInput(file);
						page.openEditor(fileEditorInput, editorID, true, IWorkbenchPage.MATCH_INPUT
								| IWorkbenchPage.MATCH_ID);
					}
					catch (PartInitException pie) {
						//pie.printStackTrace();
					}
				}
			}
		}
	}

	public static void selectAndSaveFile(Shell shell, String targetExtension, InputStream contents) {
		IPath path = selectFile(shell, targetExtension);
		if (path != null) {
			saveFile(shell, contents, path);
		}
	}

	public static IPath selectFile(Shell shell, String targetExtension) {
		SaveAsDialog sad = new SaveAsDialog(shell);
		sad.open();
		IPath initialTargetPath = sad.getResult();
		if (initialTargetPath == null) {
			return null;
		}

		if (targetExtension != null) {
			String extension = initialTargetPath.getFileExtension();
			if (extension == null) {
				initialTargetPath = initialTargetPath.addFileExtension(targetExtension);
			}
		}
		return initialTargetPath;
	}

	public static void saveFile(Shell shell, InputStream contents, IPath initialTargetPath) {
		final Shell fshell = shell;
		final InputStream fcontents = contents;
		final IPath targetPath = initialTargetPath;

		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IFile targetFile = workspace.getRoot().getFile(targetPath);

		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {

			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException,
					InterruptedException {
				IPath targetContainerPath = targetPath.removeLastSegments(1);
				boolean createContainer = true;
				if (workspace.getRoot().getContainerForLocation(targetContainerPath) != null) {
					createContainer = false;
				}
				ContainerGenerator gen = new ContainerGenerator(targetContainerPath);
				try {
					if (createContainer) {
						gen.generateContainer(monitor); // creates project A and folder B if required
					}
					if (targetFile.exists()) {
						targetFile.delete(false, monitor);
					}
					targetFile.create(fcontents, false, monitor);
					try {
						fcontents.close();
					}
					catch (IOException ioe) {
					}
				}
				catch (CoreException e) {
					MessageDialog.openError(fshell, "Error", "Could not save file: " + e.getMessage());
					return;
				}
				catch (OperationCanceledException e) {
					return;
				}
			}
		};
		try {
			operation.run(null);
		}
		catch (InterruptedException e) {
		}
		catch (InvocationTargetException ite) {
		}
	}

	public static IFile[] selectFilesToOpen(Shell shell) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		ResourceSelectionDialog dialog = new ResourceSelectionDialog(shell, workspace.getRoot(),
				"Select a file to open");
		int result = dialog.open();
		if (result == Window.OK) {
			Object[] selection = dialog.getResult();
			IFile[] files = new IFile[selection.length];
			System.arraycopy(selection, 0, files, 0, selection.length);
			return files;
		}
		return null;
	}

	public static IResource[] selectResourcesToOpen(Shell shell, int selectionMode, String title, String[] extensions) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		WSFileDialog dialog = new WSFileDialog(shell, selectionMode, title, workspace.getRoot(), true, extensions, null);

		int result = dialog.open();
		if (result == Window.OK) {
			if (selectionMode == SWT.SINGLE) {
				IResource resource = dialog.getSingleResult();
				return new IResource[] { workspace.getRoot().getFile(resource.getFullPath()) };
			}
			else if (selectionMode == SWT.MULTI) {
				IResource[] resources = dialog.getMultiResult();
				return resources;
			}
		}
		return null;
	}

	public static void openExternalBrowser(String externalURL) {
		try {
			PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser().openURL(new java.net.URL(externalURL));
		}
		catch (java.net.MalformedURLException mue) {
		}
		catch (PartInitException pie) {
		}
	}

	public static String[][] getFieldEditorPreferenceData(Class<? extends Enum<?>> enumClass) {
		Enum<?>[] enumConstants = enumClass.getEnumConstants();
		String[][] values = new String[enumConstants.length][];
		for (int i = 0; i < enumConstants.length; i++) {
			values[i] = new String[] { enumConstants[i].toString(), enumConstants[i].name() };
		}
		return values;
	}

	//public static byte[] getBytes(String platformURI) throws IOException {
	//	URI uri = URI.createURI(platformURI);
	//	InputStream is = URIConverter.INSTANCE.createInputStream(uri);
	//	return SystemUtils.blt(is);
	//}
}
