package org.archstudio.bna.logics.editing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MoveWithLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.swt.SWTTextThing;
import org.archstudio.bna.utils.AssemblyUtils;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;

public class EditTextLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener, IBNAMouseListener {

	public static class DescriptionContext {

		public String descriptionName;

		public DescriptionContext(String descriptionName) {
			this.descriptionName = descriptionName;
		}
	}

	protected List<SWTTextThing> openControls = Collections.synchronizedList(new ArrayList<SWTTextThing>());

	public EditTextLogic() {
	}

	public void fillMenu(final IBNAView view, Point localPoint, Point worldPoint, final Iterable<IThing> things,
			IMenuManager m) {
		IThing editThing = null;
		if (BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel()).length <= 1) {
			IAssemblyThing assembly = AssemblyUtils.getAssemblyWithPart(things);
			if (assembly != null) {
				for (IThing partThing : assembly.getParts()) {
					if (UserEditableUtils.isEditableForAnyQualities(partThing, IHasMutableText.USER_MAY_EDIT_TEXT,
							ToolTipLogic.USER_MAY_EDIT_TOOL_TIP)) {
						editThing = partThing;
						break;
					}
				}
			}
		}
		final IThing fThing = editThing;
		if (fThing != null) {
			m.add(new Action("Edit Description...") {

				@Override
				public void run() {
					Point p = BNAUtils.getCentralPoint(things);
					if (p == null) {
						p = new Point(worldPoint, worldY);
					}
					SWTTextThing tt = new SWTTextThing(null);
					tt.put("#targetThingID", fThing.getID());
					tt.setText(fThing instanceof IHasText ? ((IHasText) fThing).getText() : ToolTipLogic
							.getToolTip(fThing));
					tt.setAnchorPoint(p);
					MoveWithLogic.moveWith(things, MoveWithLogic.TRACK_ANCHOR_POINT_FIRST, tt);
					tt.setEditing(true);
					openControls.add(tt);
					view.getBNAWorld().getBNAModel().addThing(tt, things);
				}
			});
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			if (evt.getTargetThing() instanceof SWTTextThing) {
				SWTTextThing tt = (SWTTextThing) evt.getTargetThing();
				if (openControls.contains(tt)) {
					if (tt.getCompletionStatus() == CompletionStatus.OK) {
						IThing t = evt.getSource().getThing((String) tt.get("#targetThingID"));
						if (t instanceof IHasMutableText
								&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableText.USER_MAY_EDIT_TEXT)) {
							((IHasMutableText) t).setText(tt.getText());
						}
						else if (t != null
								&& UserEditableUtils.isEditableForAnyQualities(t, ToolTipLogic.USER_MAY_EDIT_TOOL_TIP)) {
							ToolTipLogic.setToolTip(t, tt.getText());
						}
					}
					if (tt.getCompletionStatus() != CompletionStatus.INCOMPLETE) {
						evt.getSource().removeThing(tt);
						openControls.remove(tt);
					}
				}
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		if (openControls.size() > 0) {
			SWTTextThing[] oc = openControls.toArray(new SWTTextThing[openControls.size()]);
			for (SWTTextThing tt : oc) {
				tt.setCompletionStatus(CompletionStatus.OK);
				tt.setEditing(false);
			}
		}
	}

	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}
}
