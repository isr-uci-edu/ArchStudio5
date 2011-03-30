package org.archstudio.xarchadt.core;

import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.core.internal.EFactoryProxy;
import org.archstudio.xarchadt.core.internal.EObjectProxy;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;

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

	public static final Iterable<ObjRef> unproxy(Iterable<EObject> eObjects) {
		return EObjectProxy.unproxy(eObjects);
	}

	public static final <T extends EFactory> T proxy(IXArchADT xarch, String nsURI) {
		return EFactoryProxy.proxy(xarch, nsURI);
	}

	public static final String unproxy(EFactory factory) {
		return EFactoryProxy.unproxy(factory);
	}
}