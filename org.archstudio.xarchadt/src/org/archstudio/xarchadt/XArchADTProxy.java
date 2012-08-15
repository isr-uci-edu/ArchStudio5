package org.archstudio.xarchadt;

import org.archstudio.xarchadt.internal.EFactoryProxy;
import org.archstudio.xarchadt.internal.EObjectProxy;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class XArchADTProxy {

	public static final <T extends EObject> T proxy(IXArchADT xarch, ObjRef objRef) {
		return EObjectProxy.proxy(xarch, objRef);
	}

	public static final <T extends EObject> Iterable<T> proxy(final IXArchADT xarch, Iterable<ObjRef> objRefs) {
		return EObjectProxy.proxy(xarch, objRefs);
	}

	public static final ObjRef unproxy(EObject eObject) {
		return EObjectProxy.unproxy(eObject);
	}

	public static final Iterable<ObjRef> unproxy(Iterable<? extends EObject> eObjects) {
		return Lists.newArrayList(Iterables.transform(eObjects, new Function<EObject, ObjRef>() {
			@Override
			public ObjRef apply(EObject input) {
				return EObjectProxy.unproxy(input);
			}
		}));
	}

	public static final <T extends EFactory> T proxy(IXArchADT xarch, String nsURI) {
		return EFactoryProxy.proxy(xarch, nsURI);
	}

	public static final String unproxy(EFactory factory) {
		return EFactoryProxy.unproxy(factory);
	}
}