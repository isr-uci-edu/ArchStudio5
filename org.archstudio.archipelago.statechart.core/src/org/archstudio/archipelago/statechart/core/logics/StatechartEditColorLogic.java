package org.archstudio.archipelago.statechart.core.logics;

import java.util.List;

import org.archstudio.archipelago.statechart.core.Activator;
import org.archstudio.archipelago.statechart.core.StatechartConstants;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.logics.editing.EditColorLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.PreferenceConverter;

public class StatechartEditColorLogic extends EditColorLogic {

	protected final IXArchADT xarch;

	public StatechartEditColorLogic(IXArchADT xarch) {
		super();
		this.xarch = xarch;
	}

	@Override
	protected void appendMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu,
			final List<IHasMutableColor> editableColoredThings, List<IHasColor> coloredThings) {

		if (editableColoredThings.size() > 0) {
			menu.add(new Action("Reset to Default Color") {

				@Override
				public void run() {
					for (IHasMutableColor t : editableColoredThings) {
						IThing root = Assemblies.getThingWithProperty(getBNAModel(), t, IHasObjRef.OBJREF_KEY);
						if (root != null) {
							ObjRef objRef = root.get(IHasObjRef.OBJREF_KEY);
							if (objRef != null) {
								if (XadlUtils.isInstanceOf(xarch, objRef, Statechart_1_0Package.Literals.STATE)) {
									t.setColor(PreferenceConverter.getColor(
											Activator.getDefault().getPreferenceStore(),
											StatechartConstants.PREF_STATE_COLOR));
								}
							}
						}
					}
				}
			});
		}
		else {
			menu.add(SWTWidgetUtils.createNoAction("Reset to Default Color"));
		}
	}
}
