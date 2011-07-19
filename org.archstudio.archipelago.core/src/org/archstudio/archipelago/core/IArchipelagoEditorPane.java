package org.archstudio.archipelago.core;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;

public interface IArchipelagoEditorPane {
	public void clearEditor();

	public void displayDefaultEditor();

	public Composite getParentComposite();

	public IActionBars getActionBars();

	public void setProperty(String name, Object value);

	public Object getProperty(String name);
}
