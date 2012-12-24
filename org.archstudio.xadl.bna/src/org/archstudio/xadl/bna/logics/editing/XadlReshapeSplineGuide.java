package org.archstudio.xadl.bna.logics.editing;

import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.logics.editing.AbstractReshapeSplineGuide;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.emf.ecore.EClass;

public class XadlReshapeSplineGuide extends AbstractReshapeSplineGuide {

	protected final IXArchADT xarch;
	protected final EClass splineEClass;
	protected final EClass stickyEClass;

	public XadlReshapeSplineGuide(IXArchADT xarch, EClass splineEClass, EClass stickyEClass, int... pointIndex) {
		super(pointIndex);
		this.xarch = xarch;
		this.splineEClass = splineEClass;
		this.stickyEClass = stickyEClass;
	}

	@Override
	protected boolean isRelevantPointsThing(IHasPoints pointsThing) {
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
