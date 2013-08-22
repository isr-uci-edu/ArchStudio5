package org.archstudio.bna.logics.editing;

import java.util.List;
import java.util.Map;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class EditColorLogic extends AbstractThingLogic implements IBNAMenuListener {

	protected static RGB copiedRGB = null;

	public EditColorLogic() {
	}

	@Override
	public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		final List<IHasMutableColor> editableColoredThings = Lists.newArrayList();
		final List<IHasColor> coloredThings = Lists.newArrayList();

		MenuManager m = new MenuManager("Edit Color...");
		menu.add(m);

		for (IThing selectedThing : BNAUtils.getSelectedThings(getBNAModel())) {
			IHasMutableColor editableColorThing = Assemblies.getEditableThing(getBNAModel(), selectedThing,
					IHasMutableColor.class, IHasMutableColor.USER_MAY_EDIT_COLOR);
			if (editableColorThing != null) {
				editableColoredThings.add(editableColorThing);
			}
			IHasColor colorThing = Assemblies.getEditableThing(getBNAModel(), selectedThing, IHasColor.class,
					IHasColor.USER_MAY_COPY_COLOR);
			if (colorThing != null) {
				coloredThings.add(colorThing);
			}
		}

		if (editableColoredThings.size() > 0) {
			m.add(new Action("Assign Color...") {

				@Override
				public void run() {
					chooseAndAssignColor(view, editableColoredThings, editableColoredThings.get(0).getColor());
				}
			});
		}
		else {
			m.add(SWTWidgetUtils.createNoAction("Assign Color..."));
		}

		m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		if (coloredThings.size() == 1) {
			m.add(new Action("Copy Color") {

				@Override
				public void run() {
					copiedRGB = coloredThings.get(0).getColor();
				}
			});
		}
		else {
			m.add(SWTWidgetUtils.createNoAction("Copy Color"));
		}
		if (copiedRGB != null && editableColoredThings.size() > 0) {
			m.add(new Action("Paste Color") {

				@Override
				public void run() {
					assignColor(editableColoredThings, copiedRGB);
				}
			});
		}
		else {
			m.add(SWTWidgetUtils.createNoAction("Paste Color"));
		}

		m.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		appendMenu(view, things, location, m, editableColoredThings, coloredThings);
	}

	protected void appendMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu,
			List<IHasMutableColor> editableColoredThings, List<IHasColor> coloredThings) {
	}

	protected void chooseAndAssignColor(IBNAView view, Iterable<IHasMutableColor> thingsToEdit, RGB initialRGB) {
		Runnable undoRunnable = takeSnapshot(thingsToEdit);
		ColorDialog colorDialog = new ColorDialog(view.getComposite().getShell());
		colorDialog.setRGB(initialRGB);
		RGB rgb = colorDialog.open();
		if (rgb != null) {
			assignColor(thingsToEdit, rgb);
			Runnable redoRunnable = takeSnapshot(thingsToEdit);
			BNAOperations.runnable("Color", undoRunnable, redoRunnable, false);
		}
	}

	private Runnable takeSnapshot(Iterable<IHasMutableColor> thingsToEdit) {
		final Map<Object, RGB> colors = Maps.newHashMap();
		for (IHasMutableColor t : thingsToEdit) {
			colors.put(t.getID(), t.getColor());
		}
		return new Runnable() {
			@Override
			public void run() {
				for (Map.Entry<Object, RGB> e : colors.entrySet()) {
					IThing t = getBNAModel().getThing(e.getKey());
					if (t != null) {
						t.set(IHasColor.COLOR_KEY, e.getValue());
					}
				}
			}
		};
	}

	protected void assignColor(Iterable<IHasMutableColor> thingsToEdit, RGB rgb) {
		for (IHasMutableColor thingToEdit : thingsToEdit) {
			thingToEdit.setColor(rgb);
		}
	}
}
