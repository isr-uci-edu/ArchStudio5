package org.archstudio.bna.logics.navigating;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchActionConstants;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAKeyListener;
import org.archstudio.bna.IBNAMenuListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;

import org.archstudio.swtutils.FindDialog;
import org.archstudio.swtutils.IFinder;

public class FindDialogLogic extends AbstractThingLogic implements IBNAKeyListener, IBNAMenuListener {

	protected IFinder<IBNAView> finder = null;
	protected FindDialog<IBNAView> fd = null;

	public FindDialogLogic(IFinder<IBNAView> finder) {
		this.finder = finder;
	}

	public void keyPressed(IBNAView view, KeyEvent e) {
		//Only respond if we are the top-level view.
		if (view.getParentView() == null) {
			//102 == f
			if ((e.keyCode == 102) && (e.stateMask == SWT.CONTROL)) {
				showFindDialog(view, Integer.MIN_VALUE, Integer.MIN_VALUE);
			}
		}
	}

	public void keyReleased(IBNAView view, KeyEvent e) {
	}

	public void fillMenu(final IBNAView view, IMenuManager m, final int localX, final int localY, IThing t, int worldX, int worldY) {
		if ((t == null) && (view.getParentView() == null)) {
			IAction findAction = new Action("Find...") {
				public void run() {
					showFindDialog(view, localX, localY);
				}
			};
			m.add(findAction);
			m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	public void showFindDialog(IBNAView view, int localX, int localY) {
		if ((fd != null) && (fd.getShell() != null) && (!fd.getShell().isDisposed())) {
			fd.getParent().setActive();
			fd.getParent().setFocus();
		}
		else {
			Composite c = BNAUtils.getParentComposite(view);
			if (c != null) {
				fd = new FindDialog<IBNAView>(finder, c.getShell());
				//if(localX != Integer.MIN_VALUE){
				//	fd.getShell().setLocation(localX, localY);
				//}
				fd.open(view, null);
			}
		}
	}
}
