package org.archstudio.xadl.swt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.archstudio.resources.IResources;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class XadlSelectorDialog {

	public static ObjRef showSelectorDialog(Shell parentShell, String text, IXArchADT xarch, IResources resources,
			ObjRef rootRef, Set<XadlTreeUtils.Type> showFlags, Set<XadlTreeUtils.Type> selectionFlags) {
		ObjRef[] results = showSelectorDialog(parentShell, text, xarch, resources, rootRef, showFlags, selectionFlags,
				false);
		if (results == null) {
			return null;
		}
		return results[0];
	}

	public static ObjRef[] showSelectorDialog(Shell parentShell, String text, IXArchADT xarch, IResources resources,
			ObjRef rootRef, Set<XadlTreeUtils.Type> showFlags, Set<XadlTreeUtils.Type> selectionFlags,
			final boolean allowMultipleSelections) {
		final Shell dialog = new Shell(parentShell, SWT.RESIZE | SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		dialog.setText(text);

		dialog.setLayout(new GridLayout(1, false));

		int treeViewerFlags = allowMultipleSelections ? SWT.MULTI | SWT.BORDER : SWT.SINGLE | SWT.BORDER;

		final TreeViewer treeViewer = new TreeViewer(dialog, treeViewerFlags);
		treeViewer.setContentProvider(new XadlTreeContentProvider(xarch, rootRef, showFlags));
		treeViewer.setLabelProvider(new XadlTreeLabelProvider(xarch, resources));
		treeViewer.setInput(new XadlTreeContentProvider.XadlTreeInput());
		treeViewer.expandAll();

		GridData treeData = new GridData();
		treeData.horizontalAlignment = GridData.FILL;
		treeData.grabExcessHorizontalSpace = true;
		treeData.verticalAlignment = GridData.FILL;
		treeData.grabExcessVerticalSpace = true;
		treeData.widthHint = 400;
		treeData.heightHint = 300;
		treeViewer.getControl().setLayoutData(treeData);

		Composite cButtons = new Composite(dialog, SWT.NONE);
		GridLayout cButtonsLayout = new GridLayout(2, false);
		cButtonsLayout.horizontalSpacing = 5;
		cButtonsLayout.marginTop = 5;
		cButtonsLayout.marginBottom = 5;
		cButtonsLayout.marginLeft = 5;
		cButtonsLayout.marginRight = 5;
		cButtons.setLayout(cButtonsLayout);

		cButtons.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

		Button bOK = new Button(cButtons, SWT.PUSH);
		bOK.setText("OK");
		GridData bOKData = new GridData();
		bOKData.horizontalAlignment = GridData.FILL;
		//bOKData.grabExcessHorizontalSpace = true;
		bOKData.widthHint = 100;
		bOK.setLayoutData(bOKData);

		final java.util.List<ObjRef> results = new ArrayList<ObjRef>();

		final IXArchADT fxarch = xarch;
		final Set<XadlTreeUtils.Type> fselectionFlags = selectionFlags;
		final Listener okListener = new Listener() {

			public void handleEvent(Event event) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				if (!validateSelection(fxarch, selection, fselectionFlags)) {
					MessageBox messageBox = new MessageBox(dialog, SWT.ICON_ERROR | SWT.OK);
					messageBox.setText("Error - Invalid Selection");
					messageBox.setMessage("Please select one " + (allowMultipleSelections ? "or more " : "")
							+ "of the following: " + getSelectionString(fselectionFlags) + ".");
					messageBox.open();
				}
				else {
					for (Object result : selection.toList()) {
						if (result instanceof ObjRef) {
							results.add((ObjRef) result);
						}
					}
					dialog.close();
				}
			}
		};
		bOK.addListener(SWT.Selection, okListener);

		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				okListener.handleEvent(null);
			}
		});

		Button bCancel = new Button(cButtons, SWT.PUSH);
		bCancel.setText("Cancel");
		bCancel.setSize(bCancel.computeSize(100, SWT.DEFAULT));
		GridData bCancelData = new GridData();
		bCancelData.horizontalAlignment = GridData.FILL;
		//bCancelData.grabExcessHorizontalSpace = true;
		bCancelData.widthHint = 100;
		bCancel.setLayoutData(bCancelData);

		Listener cancelListener = new Listener() {

			public void handleEvent(Event event) {
				dialog.close();
			}
		};
		bCancel.addListener(SWT.Selection, cancelListener);
		dialog.pack();
		dialog.open();

		while (!dialog.isDisposed()) {
			if (!dialog.getDisplay().readAndDispatch()) {
				dialog.getDisplay().sleep();
			}
		}

		//IStructuredSelection selection = (IStructuredSelection)treeViewer.getSelection();
		if (results.size() == 0) {
			return null;
		}
		else {
			ObjRef[] resultArray = results.toArray(new ObjRef[results.size()]);
			return resultArray;
		}
	}

	protected static String getSelectionString(Set<XadlTreeUtils.Type> flags) {
		List<String> strings = XadlTreeUtils.typesToStrings(flags);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strings.size(); i++) {
			if (i != 0) {
				if (i == strings.size() - 1) {
					sb.append(" or ");
				}
				else {
					sb.append(", ");
				}
			}
			sb.append(strings.get(i));
		}
		return sb.toString();
	}

	protected static boolean validateSelection(IXArchADT xarch, IStructuredSelection selection,
			Set<XadlTreeUtils.Type> selectionFlags) {
		Object[] selectedObjects = selection.toArray();
		if (selectedObjects.length == 0) {
			return false;
		}
		for (Object selectedObject : selectedObjects) {
			if (selectedObject instanceof ObjRef) {
				ObjRef ref = (ObjRef) selectedObject;
				XadlTreeUtils.Type typeOfRef = XadlTreeUtils.getType(xarch, ref);
				if (!selectionFlags.contains(typeOfRef)) {
					return false;
				}
			}
		}
		return true;
	}
}
