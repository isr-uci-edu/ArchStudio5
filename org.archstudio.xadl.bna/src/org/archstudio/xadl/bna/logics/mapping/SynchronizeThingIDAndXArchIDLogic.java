package org.archstudio.xadl.bna.logics.mapping;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Set;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.xadl.bna.facets.IHasXArchID;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.Sets;

public class SynchronizeThingIDAndXArchIDLogic extends AbstractThingLogic implements IBNAModelListener {

	ThingValueTrackingLogic valuesLogic = null;
	Set<IThingMetakey<?, IThingKey<Object>, String>> xArchIDKeys = Sets.newHashSet();
	Set<IThingKey<Object>> thingIDKeys = Sets.newHashSet();

	public IThingKey<String> syncXArchIDKeyToThingIDKey(IThingRefKey<?> thingRefKey) {
		return getXArchIDKey(thingRefKey);
	}

	private IThingKey<String> getXArchIDKey(IThingKey<Object> thingIDKey) {
		thingIDKeys.add(thingIDKey);
		IThingMetakey<?, IThingKey<Object>, String> xArchIDKey = ThingMetakey.create("xArchID", thingIDKey);
		xArchIDKeys.add(xArchIDKey);
		return xArchIDKey;
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	int inUpdateCount = 0;

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		if (inUpdateCount > 0) {
			return;
		}
		inUpdateCount++;
		try {
			switch (evt.getEventType()) {
			case THING_ADDED: {
				IThing t = evt.getTargetThing();
				String xArchID = t.get(IHasXArchID.XARCH_ID_KEY);
				if (xArchID != null) {
					setThingIdForXArchID(xArchID, t.getID());
				}
				for (IThingMetakey<?, IThingKey<Object>, String> xArchIDKey : xArchIDKeys) {
					xArchID = t.get(xArchIDKey);
					if (xArchID != null) {
						t.set(xArchIDKey.getKey(),
								firstOrNull(valuesLogic.getThingIDs(IHasXArchID.XARCH_ID_KEY, xArchID)));
					}
				}
				break;
			}
			case THING_CHANGED: {
				ThingEvent te = evt.getThingEvent();
				IThingKey<?> p = te.getPropertyName();
				if (thingIDKeys.contains(p)) {
					@SuppressWarnings("unchecked")
					IThingKey<Object> idKey = (IThingKey<Object>) p;
					IThing thingWithReference = evt.getTargetThing();
					IThing referencedThing = evt.getSource().getThing(thingWithReference.get(idKey));
					String xArchID = referencedThing != null ? referencedThing.get(IHasXArchID.XARCH_ID_KEY) : null;
					thingWithReference.set(getXArchIDKey(idKey), xArchID);
				}
				else if (IHasXArchID.XARCH_ID_KEY.equals(p)) {
					IThing thingWithXArchID = evt.getTargetThing();
					String xArchID = thingWithXArchID.get(IHasXArchID.XARCH_ID_KEY);
					setThingIdForXArchID(xArchID, thingWithXArchID.getID());
				}
				else if (xArchIDKeys.contains(p)) {
					IThing thingWithXArchID = evt.getTargetThing();
					@SuppressWarnings("unchecked")
					IThingMetakey<?, IThingKey<Object>, String> xArchIDKey = (IThingMetakey<?, IThingKey<Object>, String>) p;
					String xArchID = thingWithXArchID.get(xArchIDKey);
					thingWithXArchID.set(xArchIDKey.getKey(),
							firstOrNull(valuesLogic.getThingIDs(IHasXArchID.XARCH_ID_KEY, xArchID)));
				}
				break;
			}
			case THING_REMOVING: {
				IThing t = evt.getTargetThing();
				String xArchID = t.get(IHasXArchID.XARCH_ID_KEY);
				if (xArchID != null) {
					setThingIdForXArchID(xArchID, null);
				}
				break;
			}
			default:
				// do nothing
			}
		}
		finally {
			inUpdateCount--;
		}
	}

	private void setThingIdForXArchID(String xArchID, @Nullable Object thingId) {
		for (IThingMetakey<?, IThingKey<Object>, String> xArchIDKey : xArchIDKeys) {
			for (IThing thingWithXArchID : getBNAModel().getThingsByID(valuesLogic.getThingIDs(xArchIDKey, xArchID))) {
				thingWithXArchID.set(xArchIDKey.getKey(), thingId);
			}
		}
	}
}
