package org.archstudio.swtutils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class FindDialog<C> extends Dialog {

	/*
	 * public static void main(String[] args){ Display display = new Display();
	 * 
	 * Shell shell = new Shell(display); shell.setText("Dialog Example");
	 * shell.setSize(300, 200); shell.open();
	 * 
	 * ColorSelectorDialog csd = new ColorSelectorDialog(shell);
	 * System.err.println("opening"); RGB result = csd.open(new RGB(0,0,0));
	 * System.err.println("opened done: " + result);
	 * 
	 * while (!shell.isDisposed()) { if (!display.readAndDispatch())
	 * display.sleep(); } System.err.println("exit"); }
	 */

	//protected ImageRegistry imageRegistry = null;
	//protected ColorRegistry colorRegistry = null;
	private Shell shell = null;
	protected IFinder<C> finder = null;

	protected IFindResult[] currentResults = new IFindResult[0];

	public FindDialog(IFinder<C> finder, Shell parent, int style) {
		super(parent, style);
		this.finder = finder;
	}

	public FindDialog(IFinder<C> finder, Shell parent) {
		this(finder, parent, 0); // your default style bits go here (not the Shell's style bits)
	}

	protected void done() {
		this.shell.dispose();
	}

	public void open(final C context, String initialValue) {
		Shell parent = getParent();

		this.shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.MODELESS | SWT.ON_TOP | SWT.RESIZE);

		String text = getText();
		if (text == null || text.trim().equals("")) {
			text = "Find";
		}
		shell.setText(text);

		// Your code goes here (widget creation, set result, etc).
		//imageRegistry = new ImageRegistry(parent.getDisplay());
		//colorRegistry = new ColorRegistry(parent.getDisplay());

		shell.setLayout(new FillLayout());

		Composite mainComposite = new Composite(shell, SWT.NONE);
		mainComposite.setLayout(new GridLayout(1, false));

		Composite cFind = new Composite(mainComposite, SWT.NONE);
		cFind.setLayout(new GridLayout(3, false));

		Label lFind = new Label(cFind, SWT.NONE);
		lFind.setText("Find:");

		final Text tFind = new Text(cFind, SWT.BORDER);
		if (initialValue != null) {
			tFind.setText(initialValue);
		}

		final Button bFind = new Button(cFind, SWT.PUSH);
		bFind.setText("Find");

		//ScrolledComposite scrolledComposite = new ScrolledComposite(mainComposite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		final TreeViewer resultViewer = new TreeViewer(mainComposite, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.BORDER);
		resultViewer.setContentProvider(new ResultsTreeContentProvider());
		resultViewer.setLabelProvider(new ResultsTreeLabelProvider());
		resultViewer.setInput(FindDialog.this);
		GridData resultViewerData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL
				| GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		resultViewerData.widthHint = 300;
		resultViewer.getTree().setLayoutData(resultViewerData);
		resultViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection ss = (IStructuredSelection) selection;
					Object selected = ss.getFirstElement();
					if (selected instanceof IFindResult) {
						finder.selected((IFindResult) selected);
					}
				}
			}
		});

		SelectionListener findListener = new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String text = tFind.getText();
				if (text == null) {
					text = "";
				}
				if (text.trim().length() == 0) {
					currentResults = new IFindResult[0];
					resultViewer.refresh(true);
				}
				else {
					currentResults = finder.find(context, text);
					resultViewer.refresh();
					if (currentResults.length > 0) {
						resultViewer.getTree().setFocus();
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		};

		tFind.addSelectionListener(findListener);
		bFind.addSelectionListener(findListener);

		//Composite cButtons = new Composite(mainComposite, SWT.NONE);
		//cButtons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		Button bClose = new Button(mainComposite, SWT.PUSH);
		bClose.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.GRAB_HORIZONTAL));
		bClose.setText("Close");
		bClose.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				done();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		tFind.setFocus();

		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		//Clean up resources used
		//if(imageRegistry != null){
		//	imageRegistry.dispose();
		//}
	}

	public Shell getShell() {
		return shell;
	}

	class ResultsTreeContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof FindDialog) {
				return ((FindDialog<?>) parentElement).currentResults;
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			return element instanceof FindDialog;
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof IFindResult) {
				return FindDialog.this;
			}
			return null;
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			return;
		}
	}

	class ResultsTreeLabelProvider extends LabelProvider implements ILabelProvider {
		Map<IFindResult, Image> resultImages = new HashMap<IFindResult, Image>();

		@Override
		public String getText(Object element) {
			if (element instanceof IFindResult) {
				return ((IFindResult) element).getString();
			}
			return null;
		}

		@Override
		public Image getImage(Object element) {
			if (element instanceof IFindResult) {
				Image img = resultImages.get(element);
				if (img == null) {
					ImageDescriptor id = ((IFindResult) element).getImageDescriptor();
					if (id == null) {
						return null;
					}
					if (shell != null) {
						img = id.createImage(shell.getDisplay());
					}
					else {
						img = id.createImage();
					}
					if (img == null) {
						return null;
					}
					resultImages.put((IFindResult) element, img);
				}
				return img;
			}
			return null;
		}

		@Override
		public void dispose() {
			Image[] images = resultImages.values().toArray(new Image[resultImages.size()]);
			for (int i = 0; i < images.length; i++) {
				if (!images[i].isDisposed()) {
					images[i].dispose();
				}
			}
			super.dispose();
		}
	}
}
