package org.archstudio.xadl.bna.logics.editing;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.editing.AbstractReshapeSplineGuide;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.swt.graphics.Point;

public class XadlReshapeSplineGuide extends AbstractReshapeSplineGuide {

	protected final IXArchADT xarch;
	protected final EClass splineEClass;
	protected final EClass stickyEClass;

	@SafeVarargs
	public XadlReshapeSplineGuide(IXArchADT xarch, EClass splineEClass, EClass stickyEClass,
			IThingKey<Point>... pointKeys) {
		super(pointKeys);
		this.xarch = xarch;
		this.splineEClass = splineEClass;
		this.stickyEClass = stickyEClass;
	}

	@Override
	protected boolean isRelevantPointsThing(IThing pointsThing) {
		ObjRef objRef = pointsThing.get(IHasObjRef.OBJREF_KEY);
		if (objRef != null) {
			return xarch.isInstanceOf(objRef, splineEClass.getEPackage().getNsURI(), splineEClass.getName());
		}
		return false;
	}

	@Override
	protected boolean isRelevantStickyThing(IIsSticky stickyThing) {
		ObjRef objRef = stickyThing.get(IHasObjRef.OBJREF_KEY);
		if (objRef != null) {
			return xarch.isInstanceOf(objRef, stickyEClass.getEPackage().getNsURI(), stickyEClass.getName());
		}
		return false;
	}

}
