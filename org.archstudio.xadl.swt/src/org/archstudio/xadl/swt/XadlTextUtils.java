package org.archstudio.xadl.swt;

import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class XadlTextUtils {
	public static Text createXadlTextField(final IXArchADT xarch, final ObjRef ref, final String fieldName,
			final Composite parent, Object layoutData) {
		Object currentDataObject = xarch.get(ref, fieldName);
		String currentDataString = currentDataObject == null ? "" : currentDataObject.toString();

		final Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
		text.setEditable(true);
		text.setText(currentDataString);
		if (layoutData != null) {
			text.setLayoutData(layoutData);
		}
		text.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

			public void widgetSelected(SelectionEvent e) {
				String input = text.getText();
				try {
					xarch.set(ref, fieldName, input);
				}
				catch (IllegalArgumentException iae) {
					MessageBox messageBox = new MessageBox(parent.getShell(), SWT.OK | SWT.ICON_ERROR);
					messageBox.setMessage(iae.getMessage());
					messageBox.setText("Error");
					messageBox.open();
				}
			}
		});
		return text;
	}
}
