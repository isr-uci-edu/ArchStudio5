package org.archstudio.swtutils;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolItem;

public abstract class DropdownSelectionListener extends SelectionAdapter {
	private final Menu dropdownMenu;

	public DropdownSelectionListener(ToolItem dropdown) {
		MenuManager menuMgr = new MenuManager("#DropdownMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			@Override
			public void menuAboutToShow(IMenuManager m) {
				fillDropdownMenu(m);
			}
		});
		dropdownMenu = menuMgr.createContextMenu(dropdown.getParent());
		dropdown.getParent().setMenu(dropdownMenu);

		//menu = new Menu(dropdown.getParent().getShell());
	}

	public abstract void fillDropdownMenu(IMenuManager menuMgr);

	/**
	 * Called when either the button itself or the dropdown arrow is clicked
	 * 
	 * @param event
	 *            the event that trigged this call
	 */

	@Override
	public void widgetSelected(SelectionEvent event) {
		// If they clicked the arrow, we show the list
		if (event.detail == SWT.ARROW) {
			// Determine where to put the dropdown list
			ToolItem item = (ToolItem) event.widget;
			Rectangle rect = item.getBounds();
			Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
			dropdownMenu.setLocation(pt.x, pt.y + rect.height);
			dropdownMenu.setVisible(true);
		}
		else {
			// They pushed the button; take appropriate action
			ToolItem item = (ToolItem) event.widget;
			Rectangle rect = item.getBounds();
			Point pt = item.getParent().toDisplay(new Point(rect.x, rect.y));
			dropdownMenu.setLocation(pt.x, pt.y + rect.height);
			dropdownMenu.setVisible(true);
		}
	}
}
