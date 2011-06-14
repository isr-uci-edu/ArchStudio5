package org.archstudio.xadlbna.logics.hints;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.IHintRepositoryChangeListener;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.MasterPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

import com.google.common.collect.Lists;

public class XadlHintRepository implements IHintRepository, IXArchADTModelListener {

	class XadlEncodedValue implements IEncodedValue {

		ObjRef hintRef;

		public XadlEncodedValue(ObjRef hintRef) {
			this.hintRef = hintRef;
		}

		@Override
		public void setType(String type) {
			xarch.set(hintRef, "type", type);
		}

		@Override
		public void setData(String data) {
			xarch.set(hintRef, "data", data);
		}

		@Override
		public String getType() {
			return (String) xarch.get(hintRef, "type");
		}

		@Override
		public String getData() {
			return (String) xarch.get(hintRef, "data");
		}
	}

	private final IXArchADT xarch;
	private final IPropertyCoder coder;

	public XadlHintRepository(IXArchADT xarch) {
		this.xarch = xarch;
		this.coder = new MasterPropertyCoder();
	}

	@Override
	public Object getContextForThing(IBNAWorld world, IThing thing) {
		return thing.get(IHasObjRef.OBJREF_KEY);
	}

	@Override
	public Iterable<IThing> getThingsForContext(IBNAWorld world, Object context) {
		ThingValueTrackingLogic tvtl = world.getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		return world.getBNAModel().getThings(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, (ObjRef) context));
	}

	@Override
	public List<String> getStoredHintNames(Object context) {
		List<String> names = Lists.newArrayList();
		ObjRef hintsExtRef = XadlUtils.getExt(xarch, (ObjRef) context, Hints_3_0Package.Literals.HINTS_EXTENSION);
		if (hintsExtRef != null) {
			for (ObjRef hintRef : xarch.getAll(hintsExtRef, "hint")) {
				names.add((String) xarch.get(hintRef, "name"));
			}
		}
		return names;
	}

	@Override
	public void storeHint(Object context, String hintName, Serializable hintValue) {
		ObjRef hintsExtRef = XadlUtils.createExt(xarch, (ObjRef) context, Hints_3_0Package.Literals.HINTS_EXTENSION);
		ObjRef hintRef = (ObjRef) xarch.get(hintsExtRef, "hint", hintName);
		if (hintRef == null) {
			hintRef = XadlUtils.create(xarch, Hints_3_0Package.Literals.VALUE);
			encode(hintRef, hintValue);
			xarch.put(hintsExtRef, "hint", hintName, hintRef);
			return;
		}
		encode(hintRef, hintValue);
	}

	@Override
	public Object getHint(Object context, String hintName) throws PropertyDecodeException {
		ObjRef hintsExtRef = XadlUtils.getExt(xarch, (ObjRef) context, Hints_3_0Package.Literals.HINTS_EXTENSION);
		if (hintsExtRef != null) {
			ObjRef hintRef = (ObjRef) xarch.get(hintsExtRef, "hint", hintName);
			if (hintRef != null) {
				return decode(hintRef);
			}
		}
		return null;
	}

	private void encode(ObjRef hintRef, Serializable hintValue) {
		coder.encode(coder, new XadlEncodedValue(hintRef), hintValue);
	}

	private Serializable decode(ObjRef hintRef) throws PropertyDecodeException {
		return (Serializable) coder.decode(coder, new XadlEncodedValue(hintRef));
	}

	CopyOnWriteArrayList<IHintRepositoryChangeListener> changeListeners = new CopyOnWriteArrayList<IHintRepositoryChangeListener>();

	@Override
	public void addHintRepositoryChangeListener(IHintRepositoryChangeListener l) {
		changeListeners.add(l);
	}

	@Override
	public void removeHintRepositoryChangeListener(IHintRepositoryChangeListener l) {
		changeListeners.remove(l);
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		// TODO
	}
}
