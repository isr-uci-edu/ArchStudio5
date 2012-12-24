package org.archstudio.xarchadt;

import static org.archstudio.sysutils.SystemUtils.emptyIfNull;

import java.util.List;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.archstudio.xarchadt.internal.EFactoryProxy;
import org.archstudio.xarchadt.internal.EObjectProxy;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@NonNullByDefault
public final class XArchADTProxy {

	public static final <T extends EObject> T proxy(IXArchADT xarch, ObjRef objRef) {
		return EObjectProxy.proxy(xarch, objRef);
	}

	public static final <T extends EObject> List<T> proxy(final IXArchADT xarch, Iterable<ObjRef> objRefs) {
		return Lists.newArrayList(EObjectProxy.<T> proxy(xarch, objRefs));
	}

	public static final <T extends EObject> List<T> proxy(final IXArchADT xarch, ObjRef... objRefs) {
		return Lists.newArrayList(EObjectProxy.<T> proxy(xarch, emptyIfNull(objRefs)));
	}

	public static final ObjRef unproxy(EObject eObject) {
		return EObjectProxy.unproxy(eObject);
	}

	public static final List<ObjRef> unproxy(Iterable<? extends EObject> eObjects) {
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

	public static <T extends EObject> T create(IXArchADT xarch, EClass eClass) {
		return XArchADTProxy.proxy(xarch, xarch.create(eClass.getEPackage().getNsURI(), eClass.getName()));
	}

	@SuppressWarnings("unchecked")
	public static @Nullable
	<T extends Extension> T getExtension(IXArchADT xarch, EObject baseEObject, EClass extensionEClass, boolean create) {
		EStructuralFeature ext = baseEObject.eClass().getEStructuralFeature("ext");
		for (EObject eObject : (EList<EObject>) baseEObject.eGet(ext)) {
			if (extensionEClass.isInstance(eObject)) {
				return (T) eObject;
			}
		}
		if (create) {
			T eObject = create(xarch, extensionEClass);
			xarch.add(XArchADTProxy.unproxy(baseEObject), "ext", XArchADTProxy.unproxy(eObject));
			return eObject;
		}
		return null;
	}
}