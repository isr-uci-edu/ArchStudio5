package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing;

import java.util.ArrayList;
import java.util.List;

public class XadlSelectionProviderLogic extends EclipseSelectionProviderLogic {

	protected final ThingPropertyTrackingLogic tptl;
	protected final XArchFlatInterface xarch;

	public XadlSelectionProviderLogic(IWorkbenchSite workbenchSite, ThingPropertyTrackingLogic tptl,
			XArchFlatInterface xarch) {
		super(workbenchSite);
		this.tptl = tptl;
		this.xarch = xarch;
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		super.bnaModelChanged(evt);
		switch (evt.getEventType()) {
		case THING_ADDED:
		case THING_REMOVED:
			if (evt.getTargetThing().hasProperty(IHasSelected.SELECTED_PROPERTY_NAME)) {
				updateSelection();
			}
			break;

		case THING_CHANGED:
			if (IHasSelected.SELECTED_PROPERTY_NAME.equals(evt.getThingEvent().getPropertyName())) {
				updateSelection();
			}
			break;
		}
	}

	private void updateSelection() {
		List<ObjRef> selectedObjRefs = new ArrayList<ObjRef>();
		for (IThing t : tptl.getThings(IHasSelected.SELECTED_PROPERTY_NAME, Boolean.TRUE)) {
			IAssembly assembly = AssemblyUtils.getAssemblyWithPart(t);
			if (assembly != null) {
				String id = assembly.getRootThing().getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
				if (id != null) {
					ObjRef objRef = xarch.getByID(id);
					if (objRef != null) {
						selectedObjRefs.add(objRef);
					}
				}
			}
		}
		setSelection(selectedObjRefs.toArray(new ObjRef[selectedObjRefs.size()]));
	}

	@Override
	protected void unselectAll() {
		for (IThing t : tptl.getThings(IHasSelected.SELECTED_PROPERTY_NAME, Boolean.TRUE)) {
			t.setProperty(IHasSelected.SELECTED_PROPERTY_NAME, Boolean.FALSE);
		}
	}
}
