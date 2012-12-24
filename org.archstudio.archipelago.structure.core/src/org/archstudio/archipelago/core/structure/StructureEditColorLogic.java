package org.archstudio.archipelago.core.structure;

import java.util.List;

import org.archstudio.archipelago.structure.core.Activator;
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
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.PreferenceConverter;

public class StructureEditColorLogic extends EditColorLogic {

	protected final IXArchADT xarch;

	public StructureEditColorLogic(IXArchADT xarch) {
		super();
		this.xarch = xarch;
	}

	protected void appendMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu,
			final List<IHasMutableColor> editableColoredThings, List<IHasColor> coloredThings) {

		if (editableColoredThings.size() > 0) {
			menu.add(new Action("Reset to Default Color") {

				public void run() {
					for (IHasMutableColor t : editableColoredThings) {
						IThing glass = Assemblies.getAssemblyWithPart(getBNAModel(), t);
						if (glass != null) {
							ObjRef objRef = glass.get(IHasObjRef.OBJREF_KEY);
							if (objRef != null) {
								if (XadlUtils.isComponent(xarch, objRef)) {
									t.setColor(PreferenceConverter.getColor(
											Activator.getDefault().getPreferenceStore(),
											ArchipelagoStructureConstants.PREF_DEFAULT_COMPONENT_COLOR));
								}
								if (XadlUtils.isConnector(xarch, objRef)) {
									t.setColor(PreferenceConverter.getColor(
											Activator.getDefault().getPreferenceStore(),
											ArchipelagoStructureConstants.PREF_DEFAULT_CONNECTOR_COLOR));
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
