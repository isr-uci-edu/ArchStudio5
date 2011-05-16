package org.archstudio.bna.logics.editing;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MaintainTagsLogic;
import org.archstudio.bna.utils.AssemblyUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchActionConstants;

public class ShowHideTagsLogic extends AbstractThingLogic implements IBNAMenuListener {

	public void fillMenu(IBNAView view, Point localPoint, Point worldPoint, Iterable<IThing> things, IMenuManager m) {
		IAssemblyThing assembly = AssemblyUtils.getAssemblyWithPart(things);
		if (assembly != null) {
			List<IThing> taggableThings = new ArrayList<IThing>();
			for (IThing pt : assembly.getParts()) {
				if (UserEditableUtils.hasAnyEditableQualities(pt, MaintainTagsLogic.USER_MAY_SHOW_TAG)) {
					taggableThings.add(pt);
				}
			}

			if (!taggableThings.isEmpty()) {
				for (final IThing tt : taggableThings) {
					final boolean isShown = Boolean.TRUE.equals(tt.getProperty(MaintainTagsLogic.SHOW_TAG_KEY));
					IAction tagAction = new Action("Show Tag") {

						@Override
						public void run() {
							tt.setProperty(MaintainTagsLogic.SHOW_TAG_KEY, !isShown);
						}
					};
					tagAction.setChecked(isShown);
					m.add(tagAction);
				}
				m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			}
		}
	}
}
