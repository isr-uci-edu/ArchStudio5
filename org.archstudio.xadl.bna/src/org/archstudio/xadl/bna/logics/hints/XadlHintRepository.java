package org.archstudio.xadl.bna.logics.hints;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.hints.EncodedValue;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IHintRepository;
import org.archstudio.bna.logics.hints.IHintRepositoryChangeListener;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.MasterPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class XadlHintRepository implements IHintRepository, IXArchADTModelListener {

	private final IXArchADT xarch;
	private final IPropertyCoder coder;

	public XadlHintRepository(IXArchADT xarch) {
		this.xarch = xarch;
		this.coder = new MasterPropertyCoder();
	}

	@Override
	public Object getContextForThing(IBNAWorld world, IThing thing) {
		List<IThingRefKey<?>> assemblyPath = Lists.newArrayList();
		ObjRef objRef = null;
		do {
			objRef = thing.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null)
				break;
			IThingRefKey<?> partKey = Assemblies.getPartKey(thing);
			if (partKey == null)
				return null;
			assemblyPath.add(0, partKey);
			thing = Assemblies.getAssemblyWithPart(world.getBNAModel(), thing);
		} while (thing != null);
		if (objRef != null && thing != null)
			return Lists.newArrayList(assemblyPath, objRef);
		return null;
	}

	@SuppressWarnings("unchecked")
	private String toPathString(Object context) {
		List<IThingRefKey<?>> assemblyPath = (List<IThingRefKey<?>>) ((List<Object>) context).get(0);
		StringBuffer sb = new StringBuffer();
		for (IThingRefKey<?> trk : assemblyPath) {
			sb.append(trk.getKeyData()).append('/');
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private ObjRef toObjRef(Object context) {
		return (ObjRef) ((List<Object>) context).get(1);
	}

	@Override
	public Iterable<IThing> getThingsForContext(IBNAWorld world, Object context) {
		throw new UnsupportedOperationException();
		//ThingValueTrackingLogic tvtl = world.getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		//return world.getBNAModel().getThingsByID(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, (ObjRef) context));
	}

	@Override
	public List<String> getStoredHintNames(Object context) {
		List<String> names = Lists.newArrayList();
		ObjRef hintsExtRef = XadlUtils.getExt(xarch, toObjRef(context), Hints_3_0Package.Literals.HINTS_EXTENSION);
		if (hintsExtRef != null) {
			for (ObjRef hintRef : Iterables.filter(xarch.getAll(hintsExtRef, "hint"), ObjRef.class)) {
				names.add((String) xarch.get(hintRef, "name"));
			}
		}
		return names;
	}

	@Override
	public void storeHint(Object context, String hintName, Serializable hintValue) {
		ObjRef hintsExtRef = XadlUtils.createExt(xarch, toObjRef(context), Hints_3_0Package.Literals.HINTS_EXTENSION);
		ObjRef hintRef = (ObjRef) xarch.getByKey(hintsExtRef, "hint", toPathString(context) + hintName);
		if (hintRef == null) {
			hintRef = XadlUtils.create(xarch, Hints_3_0Package.Literals.VALUE);
			encode(hintRef, hintValue);
			xarch.put(hintsExtRef, "hint", toPathString(context) + hintName, hintRef);
			return;
		}
		encode(hintRef, hintValue);
	}

	@Override
	public Object getHint(Object context, String hintName) throws PropertyDecodeException {
		ObjRef hintsExtRef = XadlUtils.getExt(xarch, toObjRef(context), Hints_3_0Package.Literals.HINTS_EXTENSION);
		if (hintsExtRef != null) {
			ObjRef hintRef = (ObjRef) xarch.getByKey(hintsExtRef, "hint", toPathString(context) + hintName);
			if (hintRef != null) {
				return decode(hintRef);
			}
		}
		return null;
	}

	private void encode(ObjRef hintRef, Serializable hintValue) {
		IEncodedValue encodedValue = coder.encode(coder, hintValue);
		if (encodedValue != null) {
			xarch.set(hintRef, "type", encodedValue.getType());
			xarch.set(hintRef, "data", encodedValue.getData());
		}
	}

	private Serializable decode(ObjRef hintRef) throws PropertyDecodeException {
		String type = (String) xarch.get(hintRef, "type");
		String data = (String) xarch.get(hintRef, "data");
		return (Serializable) coder.decode(coder, new EncodedValue(type, data));
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
