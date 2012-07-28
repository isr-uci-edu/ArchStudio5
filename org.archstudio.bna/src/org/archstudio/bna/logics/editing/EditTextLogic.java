package org.archstudio.bna.logics.editing;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.swt.SWTTextThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAKeyListener;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Iterables;

public class EditTextLogic extends AbstractThingLogic implements IBNAMenuListener, IBNAModelListener, IBNAKeyListener {

	private static final class EditTextLogicData {
		public final Object thingID;

		public EditTextLogicData(Object thingID) {
			this.thingID = thingID;
		}
	}

	private static final IThingKey<EditTextLogicData> DATA_KEY = ThingKey.create(EditTextLogic.class);

	public EditTextLogic() {
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager m) {
		IThing editThing = null;
		if (Iterables.size(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) <= 1) {
			MAIN: for (IThing thing : things) {
				for (IThing assemblyPartThing : Assemblies.getRelatedParts(view.getBNAWorld().getBNAModel(), thing)) {
					if (assemblyPartThing instanceof IHasMutableText
							&& UserEditableUtils.isEditableForAnyQualities(assemblyPartThing,
									IHasMutableText.USER_MAY_EDIT_TEXT, IHasToolTip.USER_MAY_EDIT_TOOL_TIP)) {
						editThing = assemblyPartThing;
						break MAIN;
					}
				}
			}
		}
		final IThing finalThing = editThing;
		if (finalThing != null) {
			m.add(new Action("Edit Description...") {

				@Override
				public void run() {
					initEdit(finalThing);
				}
			});
		}
	}

	@Override
	public void keyPressed(IBNAView view, KeyEvent e) {
	}

	@Override
	public void keyReleased(IBNAView view, KeyEvent e) {
		if (SWT.F2 == e.keyCode) {
			IThing editThing = null;
			if (Iterables.size(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) == 1) {
				MAIN: for (IThing thing : BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) {
					for (IThing assemblyPartThing : Assemblies.getRelatedParts(view.getBNAWorld().getBNAModel(), thing)) {
						if (UserEditableUtils.isEditableForAnyQualities(assemblyPartThing,
								IHasMutableText.USER_MAY_EDIT_TEXT, IHasToolTip.USER_MAY_EDIT_TOOL_TIP)) {
							editThing = assemblyPartThing;
							break MAIN;
						}
					}
				}
			}
			if (editThing != null) {
				initEdit(editThing);
			}
		}
	}

	private void initEdit(IThing forThing) {
		checkNotNull(forThing);

		IBNAModel model = getBNAModel();
		if (model != null) {
			Point p = checkNotNull(BNAUtils.getCentralPoint(forThing));

			SWTTextThing tt = model.addThing(new SWTTextThing(null), forThing);
			tt.set(DATA_KEY, new EditTextLogicData(forThing.getID()));
			String text = "";
			if (forThing instanceof IHasText) {
				text = ((IHasText) forThing).getText();
			}
			else {
				text = ToolTipLogic.getToolTip(forThing);
			}
			tt.setText(text);
			tt.setBoundingBox(new Rectangle(p.x, p.y, 0, 0));
		}
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChanged(BNAModelEvent<ET, EK, EV> evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			EditTextLogicData data = evt.getTargetThing().get(DATA_KEY);
			if (data != null) {
				SWTTextThing tt = (SWTTextThing) evt.getTargetThing();
				IThing t = evt.getSource().getThing(data.thingID);
				if (t instanceof IHasMutableText
						&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableText.USER_MAY_EDIT_TEXT)) {
					BNAOperation.<String> execute("Text", getBNAModel(), t, IHasText.TEXT_KEY, tt.getText());
				}
				else if (t != null
						&& UserEditableUtils.isEditableForAnyQualities(t, IHasToolTip.USER_MAY_EDIT_TOOL_TIP)) {
					BNAOperation.<String> execute("Text", getBNAModel(), t, IHasToolTip.TOOL_TIP_KEY, tt.getText());
				}
			}
		}
	}
}
