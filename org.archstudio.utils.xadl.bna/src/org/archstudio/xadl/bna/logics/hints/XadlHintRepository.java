package org.archstudio.xadl.bna.logics.hints;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
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
import org.archstudio.xadl3.hints_3_0.Hint;
import org.archstudio.xadl3.hints_3_0.HintsExtension;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xadl3.hints_3_0.Value;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.Nullable;

public class XadlHintRepository implements IHintRepository, IXArchADTModelListener {

	private final IXArchADT xarch;
	private final IPropertyCoder coder;

	public XadlHintRepository(IXArchADT xarch) {
		this.xarch = xarch;
		this.coder = new MasterPropertyCoder();
	}

	@Override
	public @Nullable
	Object getContextForThing(IBNAWorld world, IThing thing) {
		IThing t = thing;
		ObjRef objRef = null;
		do {
			objRef = t.get(IHasObjRef.OBJREF_KEY);
			if (objRef != null) {
				break;
			}
			t = Assemblies.getRootWithPart(world.getBNAModel(), t);
		} while (t != null);
		if (objRef != null && t != null) {
			return objRef;
		}
		return null;
	}

	private @Nullable
	Hint getHint(HintsExtension hints, String name) {
		for (Hint hint : hints.getHint()) {
			if (name.equals(hint.getName())) {
				return hint;
			}
		}
		return null;
	}

	private Hint createHint(HintsExtension hints, String name) {
		Hint hint = getHint(hints, name);
		if (hint == null) {
			hint = XArchADTProxy.create(xarch, Hints_3_0Package.Literals.HINT);
			hint.setName(name);
			hints.getHint().add(hint);
		}
		return hint;
	}

	@Override
	public void storeHint(Object context, String hintName, @Nullable Serializable hintValue) {
		HintsExtension hints = XArchADTProxy.proxy(xarch, XadlUtils.createExt(xarch, (ObjRef) context,
				Hints_3_0Package.eNS_URI, Hints_3_0Package.Literals.HINTS_EXTENSION.getName()));
		encode(createHint(hints, hintName), hintValue);
	}

	@Override
	public @Nullable
	Object getHint(Object context, String hintName) throws PropertyDecodeException {
		HintsExtension hints = XArchADTProxy.proxy(xarch, XadlUtils.createExt(xarch, (ObjRef) context,
				Hints_3_0Package.eNS_URI, Hints_3_0Package.Literals.HINTS_EXTENSION.getName()));
		return decode(getHint(hints, hintName));
	}

	private void encode(Hint hint, @Nullable Serializable hintValue) {
		if (hintValue != null) {
			IEncodedValue encodedValue = coder.encode(coder, hintValue);
			hint.setHint(encodedValue.getType() + ":" + encodedValue.getData());
		}
		else {
			hint.setHint(null);
		}
	}

	private @Nullable
	Serializable decode(@Nullable Hint hint) throws PropertyDecodeException {
		if (hint != null) {
			String value = hint.getHint();
			if (value == null) {
				Value deprecatedValue = hint.getValue();
				if (deprecatedValue != null) {
					value = deprecatedValue.getType() + ":" + deprecatedValue.getData();
					hint.setHint(value);
					hint.setValue(null);
				}
			}
			if (value != null) {
				String[] hintParts = value.split(":", 2);
				return (Serializable) coder.decode(coder, new EncodedValue(hintParts[0], hintParts[1]));
			}
		}
		return null;
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

	protected void fireHintRepositoryChangeEvent(Object context, String name) {
		for (IHintRepositoryChangeListener l : changeListeners) {
			l.hintRepositoryChanged(this, context, name);
		}
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		EObject src = XArchADTProxy.proxy(xarch, evt.getSource());
		if (src instanceof Hint) {
			Hint hint = (Hint) src;
			if (hint.eContainer() != null && hint.eContainer().eContainer() != null) {
				fireHintRepositoryChangeEvent(XArchADTProxy.unproxy(hint.eContainer().eContainer()), hint.getName());
			}
		}
	}
}
