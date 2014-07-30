package org.archstudio.bna.logics.editing;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableToolTip;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.swt.SWTTextThing;
import org.archstudio.bna.ui.IBNAKeyListener;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
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

	public EditTextLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager m) {
		BNAUtils.checkLock();

		if (Iterables.size(BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel())) <= 1) {
			IThing editThing = Assemblies.getEditableThing(model, firstOrNull(things), IHasMutableText.class,
					IHasMutableText.USER_MAY_EDIT_TEXT);
			if (editThing == null) {
				editThing = Assemblies.getEditableThing(model, firstOrNull(things), IThing.class,
						IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP);
			}
			if (editThing != null) {
				final IThing fEditThing = editThing;
				m.add(new BNAAction("Edit Description...") {

					@Override
					public void runWithLock() {
						initEdit(fEditThing);
					}
				});
			}
		}
	}

	@Override
	public void keyPressed(IBNAView view, KeyType type, KeyEvent e) {
	}

	@Override
	public void keyReleased(IBNAView view, KeyType type, KeyEvent e) {
		BNAUtils.checkLock();

		if (SWT.F2 == e.keyCode) {
			Collection<IThing> selectedThings = BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel());
			IThing editThing = null;
			if (selectedThings.size() == 1) {
				editThing = Assemblies.getEditableThing(model, firstOrNull(selectedThings), IHasMutableText.class,
						IHasMutableText.USER_MAY_EDIT_TEXT);
				if (editThing == null) {
					editThing = Assemblies.getEditableThing(model, firstOrNull(selectedThings), IThing.class,
							IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP);
				}
			}
			if (editThing != null) {
				initEdit(editThing);
			}
		}
	}

	private void initEdit(IThing forThing) {
		checkNotNull(forThing);

		Point2D p = checkNotNull(BNAUtils.getCentralPoint(forThing));

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
		tt.setBoundingBox(new Rectangle(SystemUtils.round(p.getX()), SystemUtils.round(p.getY()), 0, 0));
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		BNAUtils.checkLock();

		if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			EditTextLogicData data = evt.getTargetThing().get(DATA_KEY);
			if (data != null) {
				SWTTextThing tt = (SWTTextThing) evt.getTargetThing();
				IThing t = evt.getSource().getThing(data.thingID);
				if (t instanceof IHasMutableText
						&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableText.USER_MAY_EDIT_TEXT)) {
					BNAOperations.set("Text", model, t, IHasText.TEXT_KEY, tt.getText());
				}
				else if (t != null
						&& UserEditableUtils.isEditableForAnyQualities(t, IHasMutableToolTip.USER_MAY_EDIT_TOOL_TIP)) {
					BNAOperations.set("Text", model, t, IHasToolTip.TOOL_TIP_KEY, tt.getText());
				}
			}
		}
	}
}
