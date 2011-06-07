package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.coordinating;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.logics.coordinating.AbstractMaintainThingsLogic;
import org.archstudio.xarchadt.ObjRef;

public class MaintainXadlLinksLogic extends AbstractMaintainThingsLogic<IThing, IThing> {

	private static final String PROPERTY_NAME_PREFIX = "linkToXArchID";

	public static final Object getReferenceName(Object propertyName) {
		return new Tuple(PROPERTY_NAME_PREFIX, propertyName);
	}

	public static final boolean isReferenceName(Object propertyName) {
		return propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(PROPERTY_NAME_PREFIX);
	}

	public static final void updateThingIDByXArchID(String xArchID, Object propertyName, IThing targetThing) {
		targetThing.setProperty(getReferenceName(propertyName), xArchID);
	}

	public static final void updateThingIDByXArchID(XArchFlatQueryInterface xarch, ObjRef linkRef, Object propertyName,
			IThing targetThing) {
		/*
		 * We cannot assume that the target is currently attached, so we parse the xArchID manually
		 */
		String xArchID = null;
		if (linkRef != null) {
			String href = (String) xarch.get(linkRef, "href");
			if (href != null) {
				int hashIndex = href.indexOf('#');
				if (hashIndex >= 0) {
					xArchID = href.substring(hashIndex + 1);
				}
			}
		}
		updateThingIDByXArchID(xArchID, propertyName, targetThing);
	}

	protected final ThingPropertyTrackingLogic tptl;
	protected final ThingPropertyPrefixTrackingLogic tpptl;
	protected final String propertyNamePrefix;

	public MaintainXadlLinksLogic(ThingPropertyTrackingLogic tptl, ThingPropertyPrefixTrackingLogic tpptl) {
		super(null, new Object[] { ArchipelagoUtils.XARCH_ID_PROPERTY_NAME }, null, new Object[] {});
		this.tptl = tptl;
		this.tpptl = tpptl;
		this.propertyNamePrefix = PROPERTY_NAME_PREFIX;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void maintainAll() {
		for (Object propertyName : tpptl.getPropertyNamesWithPrefix(propertyNamePrefix)) {
			for (IThing targetThing : tptl.getThings(propertyName)) {
				if (isTargetThing(targetThing, null)) {
					updateToTarget(null, targetThing, propertyName, null);
				}
			}
		}
	}

	@Override
	protected void updateFromSource(IBNAModel sourceModel, IThing sourceThing, ThingEvent sourceThingEvent) {
		IBNAModel targetModel = getBNAModel();
		String xarchID = sourceThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		if (xarchID != null) {
			IAssembly sourceThingAssembly = AssemblyUtils.getAssemblyWithRoot(sourceThing);
			if (sourceThingAssembly != null) {
				IThing xarchThing = sourceThingAssembly.getPart("glass");
				if (xarchThing != null) {
					String xarchThingID = xarchThing.getID();
					for (Object propertyName : tpptl.getPropertyNamesWithPrefix(propertyNamePrefix)) {
						if (propertyName instanceof Tuple) {
							for (IThing thing : tptl.getThings(propertyName, xarchID)) {
								setThingProperty(thing, ((Tuple) propertyName).getElement(1), xarchThingID);
							}
						}
					}
				}
			}
		}
	}

	protected void updateToTarget(IBNAModel sourceModel, IThing targetThing, Object propertyName,
			ThingEvent targetThingEvent) {
		IBNAModel targetModel = getBNAModel();
		if (sourceModel == null) {
			sourceModel = targetModel;
		}

		if (propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(propertyNamePrefix)) {
			// the xarchID reference was changed, update the thing reference
			String xarchID = targetThing.getProperty(propertyName);
			String xarchThingID = null;
			if (xarchID != null) {
				IThing sourceThing = tptl.getThing(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xarchID);
				if (sourceThing != null) {
					IAssembly sourceThingAssembly = AssemblyUtils.getAssemblyWithRoot(sourceThing);
					if (sourceThingAssembly != null) {
						IThing xarchThing = sourceThingAssembly.getPart("glass");
						if (xarchThing != null) {
							xarchThingID = xarchThing.getID();
						}
					}
				}
			}
			setThingProperty(targetThing, ((Tuple) propertyName).getElement(1), xarchThingID);
		}
	}

	@Override
	protected void updateToTarget(IBNAModel sourceModel, IThing targetThing, ThingEvent targetThingEvent) {
		if (targetThingEvent != null) {
			updateToTarget(sourceModel, targetThing, targetThingEvent.getPropertyName(), targetThingEvent);
		}
		else {
			IBNAModel targetModel = getBNAModel();
			if (sourceModel == null) {
				sourceModel = targetModel;
			}

			// the target thing was probably just added, verify all of its values
			for (Object propertyName : targetThing.getAllPropertyNames()) {
				if (propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(propertyNamePrefix)) {
					String xarchID = targetThing.getProperty(propertyName);
					String xarchThingID = null;
					if (xarchID != null) {
						IThing sourceThing = tptl.getThing(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xarchID);
						if (sourceThing != null) {
							IAssembly sourceThingAssembly = AssemblyUtils.getAssemblyWithRoot(sourceThing);
							if (sourceThingAssembly != null) {
								IThing xarchThing = sourceThingAssembly.getPart("glass");
								if (xarchThing != null) {
									xarchThingID = xarchThing.getID();
								}
							}
						}
					}
					setThingProperty(targetThing, ((Tuple) propertyName).getElement(1), xarchThingID);
				}
			}
		}
	}

	@Override
	protected boolean isTargetThing(IThing targetThing, ThingEvent targetThingEvent) {
		if (targetThingEvent != null) {
			Object propertyName = targetThingEvent.getPropertyName();
			if (propertyName instanceof Tuple && ((Tuple) propertyName).startsWith(propertyNamePrefix)) {
				// the xADL reference has changed, update the thing reference
				return true;
			}
			return false;
		}
		return super.isTargetThing(targetThing, targetThingEvent);
	}
}
