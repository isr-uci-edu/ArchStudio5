package biz.volantec.utils.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ResourceWorkingSetFilter;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * 
 * FileDialog that is workspace relative and can start at a given root element
 * 
 * Basically embeds the relevant parts of the resource navigator in a dialog.
 * 
 * The workhorse here is the WorkbenchContentProvider I'm sure...
 * 
 * 
 * 
 * @author Frank Sauer
 * 
 */

public class WSFileDialog extends Dialog {

	private TreeViewer viewer;

	private final IResource rootElement;

	private final boolean expand;

	private String[] extensions;

	private final FilePatternFilter patternFilter = new FilePatternFilter();

	private final ResourceWorkingSetFilter workingSetFilter = new ResourceWorkingSetFilter();

	private final IWorkingSet workingSet;

	private final int selectionStyle;

	private IResource[] result;

	private final String title;

	/**
	 * 
	 * @param parentShell
	 *            this shell will be blocked by the modal WSFileDialog
	 * 
	 * @param selectionStyle
	 *            must be SWT.SINGLE or SWT.MULTI
	 * 
	 * @param title
	 *            the dialog's title
	 * 
	 * @param rootElement
	 *            resource to be the rootElement for the tree
	 * 
	 * @param expand
	 *            if true, the root element will be expanded
	 * 
	 * @param extensions
	 *            if specified only files with these extensions are shown
	 * 
	 * @param workingSet
	 *            if specified only files in this workingSet are shown
	 * 
	 */

	public WSFileDialog(Shell parentShell, int selectionStyle, String title, IResource rootElement, boolean expand,
			String[] extensions, IWorkingSet workingSet) {

		super(parentShell);

		setShellStyle(getShellStyle() | SWT.RESIZE);

		this.title = title;

		this.rootElement = rootElement;

		this.expand = expand;

		this.extensions = extensions;

		this.workingSet = workingSet;

		this.selectionStyle = selectionStyle;

	}

	/**
	 * 
	 * Open on the workspace root without filters or workingset
	 * 
	 * 
	 * 
	 * @param parentShell
	 *            this shell will be blocked by the modal WSFileDialog
	 * 
	 * @param selectionStyle
	 *            must be SWT.SINGLE or SWT.MULTI
	 * 
	 * @param title
	 *            the dialog title
	 * 
	 */

	public WSFileDialog(Shell parentShell, int selectionStyle, String title) {

		this(

		parentShell,

		selectionStyle,

		title,

		ResourcesPlugin.getWorkspace().getRoot(),

		true,

		null,

		null);

	}

	/**
	 * 
	 * Only files with the given file extensions will be shown
	 * 
	 * @param extensions
	 * 
	 */

	public void setExtensions(String[] extensions) {

		this.extensions = extensions;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets .Composite)
	 */

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite comp = (Composite) super.createDialogArea(parent);

		getShell().setText(title);

		TreeViewer viewer = createViewer(comp);

		GridData data = new GridData(GridData.FILL_BOTH);

		data.grabExcessHorizontalSpace = true;

		data.grabExcessVerticalSpace = true;

		data.heightHint = 400;

		data.widthHint = 300;

		viewer.getControl().setLayoutData(data);

		this.viewer = viewer;

		return comp;

	}

	protected TreeViewer createViewer(Composite parent) {

		final TreeViewer viewer =

		new TreeViewer(parent, selectionStyle | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		viewer.setUseHashlookup(true);

		initContentProvider(viewer);

		initLabelProvider(viewer);

		initFilters(viewer);

		viewer.setInput(rootElement);

		if (expand) {

			viewer.expandToLevel(2);

		}

		viewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = viewer.getSelection();
				if (!selection.isEmpty()) {
					okPressed();
				}
			}
		});

		return viewer;

	}

	/**
	 * 
	 * Attach the filters to the tree viewer
	 * 
	 * @param viewer
	 * 
	 */

	protected void initFilters(TreeViewer viewer) {

		viewer.addFilter(patternFilter);

		if (workingSet != null) {

			workingSetFilter.setWorkingSet(workingSet);

			viewer.addFilter(workingSetFilter);

		}

	}

	/**
	 * 
	 * This is the key, the WorkBenchContentProvider provides us
	 * 
	 * with all the resource information
	 * 
	 * @param viewer
	 * 
	 */

	protected void initContentProvider(TreeViewer viewer) {

		viewer.setContentProvider(new WorkbenchContentProvider());

	}

	protected void initLabelProvider(TreeViewer viewer) {

		viewer.setLabelProvider(

		new DecoratingLabelProvider(

		new WorkbenchLabelProvider(),

		PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator()));

	}

	/**
	 * 
	 * process the tree selection and keep as the resultIResource[] until needed by our client
	 * 
	 * 
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 * 
	 */

	@Override
	protected void okPressed() {

		ISelection selection = viewer.getSelection();

		List<IResource> data = new ArrayList<IResource>();

		if (!selection.isEmpty()) {

			if (selection instanceof IStructuredSelection) {

				IStructuredSelection sel = (IStructuredSelection) selection;

				for (Object next : sel.toList()) {

					IResource resource = null;

					if (next instanceof IResource) {
						resource = (IResource) next;
					}
					else if (next instanceof IAdaptable) {

						if (resource == null) {
							resource = (IResource) ((IAdaptable) next).getAdapter(IResource.class);
						}

					}

					if (resource != null) {

						data.add(resource);

					}

				}

			}

		}

		result = data.toArray(new IResource[] {});

		super.okPressed();

	}

	/**
	 * 
	 * Get the single selection result if any or the first selected
	 * 
	 * element if SWT.MULTI was used as the selectionType
	 * 
	 * @return one selected resource or null if none or canceled
	 * 
	 */

	public IResource getSingleResult() {

		if (getReturnCode() == OK) {

			return getMultiResult()[0];

		}
		else {
			return null;
		}

	}

	/**
	 * 
	 * Get an array of selected resources or null if canceled
	 * 
	 * @return selected resources or null if none or canceled
	 * 
	 */

	public IResource[] getMultiResult() {

		if (getReturnCode() == OK) {

			return result;

		}
		else {
			return null;
		}

	}

	/**
	 * 
	 * ViewerFilter to only show non-derived folders and those files matching the file extensions
	 * 
	 * @author Frank Sauer
	 * 
	 */

	private class FilePatternFilter extends ViewerFilter {

		/**
		 * Select all folders and files matching the desired file extensions
		 * 
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object,
		 *      java.lang.Object)
		 * 
		 */

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {

			if (extensions == null || extensions.length == 0) {
				return true;
			}

			IResource resource = null;

			if (element instanceof IResource) {

				resource = (IResource) element;

			}
			else if (element instanceof IAdaptable) {

				IAdaptable adaptable = (IAdaptable) element;

				resource = (IResource) adaptable.getAdapter(IResource.class);

			}

			if (resource != null && !resource.isDerived()) {

				if (resource.getType() != IResource.FILE) {
					return true;
				}

				String extension = resource.getFileExtension();

				if (extension == null) {
					return true;
				}

				for (String extension2 : extensions) {

					if (extension.equalsIgnoreCase(extension2)) {
						return true;
					}

				}

			}

			return false;

		}

	}

}
