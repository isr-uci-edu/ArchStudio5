package org.archstudio.bna.logics.information;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNAKeyListener2;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.swtutils.FindDialog;
import org.archstudio.swtutils.IFinder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchActionConstants;

public class FindDialogLogic extends AbstractThingLogic
		implements IBNAKeyListener2, IBNAAllEventsListener2, IBNAMenuListener2 {

	protected IFinder<IBNAView> finder = null;
	protected FindDialog<IBNAView> fd = null;

	public FindDialogLogic(IBNAWorld world, IFinder<IBNAView> finder) {
		super(world);
		this.finder = finder;
	}

	@Override
	public void keyPressed(IBNAView view, KeyType type, KeyEvent e) {
		BNAUtils.checkLock();

		// Only respond if we are the top-level view.
		if (view.getParentView() == null) {
			//102 == f
			if (e.keyCode == 102 && e.stateMask == SWT.CONTROL) {
				showFindDialog(view, Integer.MIN_VALUE, Integer.MIN_VALUE);
			}
		}
	}

	@Override
	public void keyReleased(IBNAView view, KeyType type, KeyEvent e) {
	}

	@Override
	public void fillMenu(final IBNAView view, final ICoordinate location, ThingsAtLocation thingsAtLocation,
			IMenuManager menu) {
		BNAUtils.checkLock();
		// Only respond if we are the top-level view.
		if (view.getParentView() == null) {
			IAction findAction = new BNAAction("Find...") {

				@Override
				public void runWithLock() {
					Point localPoint = location.getLocalPoint();
					showFindDialog(view, localPoint.x, localPoint.y);
				}
			};
			menu.add(findAction);
			menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		}
	}

	public void showFindDialog(IBNAView view, int localX, int localY) {
		if (fd != null && fd.getShell() != null && !fd.getShell().isDisposed()) {
			fd.getParent().setActive();
			fd.getParent().setFocus();
		}
		else {
			Control c = view.getBNAUI().getComposite();
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
