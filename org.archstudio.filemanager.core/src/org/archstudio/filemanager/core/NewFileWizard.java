package org.archstudio.filemanager.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.archstudio.eclipse.core.startup.InstantiateArchStudio;
import org.archstudio.myx.fw.MyxRegistry;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class NewFileWizard extends Wizard implements INewWizard {
	private final MyxRegistry er = MyxRegistry.getSharedInstance();
	private FileManagerMyxComponent comp = null;

	protected IWorkbench workbench;
	protected IStructuredSelection selection;

	protected NewFileCreationPage mainPage;

	protected IXArchADT xarch = null;

	public NewFileWizard() {
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		InstantiateArchStudio.instantiate();
		comp = er.waitForBrick(FileManagerMyxComponent.class);
		er.map(comp, this);
		xarch = comp.xarch;

		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle("New Architecture Description");
		//setDefaultPageImageDescriptor(ReadmeImages.README_WIZARD_BANNER);
	}

	public void addPages() {
		mainPage = new NewFileCreationPage(workbench, selection);
		addPage(mainPage);
	}

	public boolean performFinish() {
		IPath containerPath = mainPage.getContainerFullPath();
		String fileName = mainPage.getFileName();

		if (!fileName.toLowerCase().endsWith(".xml")) {
			fileName += ".xml";
		}

		IPath filePath = containerPath.append(fileName);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(filePath);
		if (file.exists()) {
			MessageDialog.openError(getShell(), "Error", "File already exists.");
			return false;
		}

		URI uri = URI.createURI(UIDGenerator.generateUID("urn:"));
		ObjRef documentRootRef = xarch.createDocument(uri);

		ObjRef xADLRef = XadlUtils.create(xarch, Xadlcore_3_0Package.Literals.XADL_TYPE);

		xarch.set(documentRootRef, "xADL", xADLRef);

		String fileContents = new String(xarch.serialize(uri));
		xarch.close(uri);

		InputStream is = new ByteArrayInputStream(fileContents.getBytes());
		try {
			file.create(is, false, null);
		}
		catch (CoreException ce) {
			MessageDialog.openError(getShell(), "Error", ce.getMessage());
			return false;
		}
		finally {
			try {
				is.close();
			}
			catch (IOException ioe) {
			}
		}

		return true;
	}

	static class NewFileCreationPage extends WizardNewFileCreationPage {
		public NewFileCreationPage(IWorkbench workbench, IStructuredSelection selection) {
			super("New Architecture Description", selection);
			setTitle("New Architecture Description");
		}

		public void createControl(Composite parent) {
			super.createControl(parent);
		}
	}

	public static void showWizard(Shell shell, IWorkbench workbench) {
		NewFileWizard wizard = new NewFileWizard();
		wizard.init(workbench, StructuredSelection.EMPTY);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}

}
