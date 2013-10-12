package org.archstudio.archipelago.core.structure;

import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.Collection;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.archstudio.resources.IResources;
import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xadl.bna.utils.XArchADTOperations;
import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;

public class StructureNewInterfaceMappingLogic extends AbstractThingLogic implements IBNAMenuListener,
		IBNAMouseListener, IBNAMouseMoveListener {

	protected final IXArchADT xarch;
	protected final IResources resources;

	public StructureNewInterfaceMappingLogic(IBNAWorld world, IXArchADT xarch, IResources resources) {
		super(world);
		this.xarch = xarch;
		this.resources = resources;
	}

	protected boolean matches(IBNAView view, IThing t) {
		if (t != null) {
			ObjRef objRef = t.get(IHasObjRef.OBJREF_KEY);
			if (XadlUtils.isInstanceOf(xarch, objRef, Structure_3_0Package.Literals.INTERFACE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	synchronized public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager m) {
		Collection<IThing> selectedThings = BNAUtils.getSelectedThings(view.getBNAWorld().getBNAModel());
		if (selectedThings.size() > 1) {
			return;
		}

		IThing t = Assemblies.getThingWithProperty(model, firstOrNull(things), IHasObjRef.OBJREF_KEY);
		if (matches(view, t)) {
			Point world = location.getWorldPoint();
			IAction action = getAction(view, t, world.x, world.y);
			if (action != null) {
				m.add(action);
			}
		}
	}

	SplineThing indicatorSpline = null;
	ObjRef outerInterfaceRef = null;
	ObjRef subArchitectureRef = null;

	protected IAction getAction(final IBNAView view, final IThing t, final int worldX, final int worldY) {
		outerInterfaceRef = t.get(IHasObjRef.OBJREF_KEY);
		if (outerInterfaceRef != null) {
			ObjRef brickTypeRef = xarch.getParent(outerInterfaceRef);
			if (brickTypeRef != null) {
				subArchitectureRef = (ObjRef) xarch.get(brickTypeRef, "subStructure");
				if (subArchitectureRef != null) {
					ObjRef archStructureRef = (ObjRef) xarch.get(subArchitectureRef, "innerStructureLink");
					if (archStructureRef != null) {
						return new Action("New Interface-Interface Mapping...") {
							@Override
							public void run() {
								Point p1 = BNAUtils.getCentralPoint(t);
								if (p1 == null) {
									p1 = new Point(worldX, worldY);
								}

								indicatorSpline = view.getBNAWorld().getBNAModel().addThing(new SplineThing(null), t);
								indicatorSpline.setEndpoint1(p1);
								indicatorSpline.setEndpoint2(p1);
								indicatorSpline.setLineWidth(2);
								indicatorSpline.setEdgeColor(new RGB(0, 0, 255));
							}
						};
					}
				}
			}
		}
		return null;
	}

	@Override
	synchronized public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
	}

	@Override
	synchronized public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (indicatorSpline != null) {
			indicatorSpline.setEndpoint2(location.getWorldPoint());
		}
	}

	@Override
	synchronized public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (indicatorSpline != null) {
			if (evt.button == 1) {
				List<IThing> otherThings = Lists.newArrayList(things);
				otherThings.remove(indicatorSpline);
				IHasWorld worldThing = castOrNull(firstOrNull(otherThings), IHasWorld.class);
				if (worldThing != null) {
					IHasInnerViewPeer<?> worldThingPeer = castOrNull(view.getThingPeer(worldThing),
							IHasInnerViewPeer.class);
					if (worldThingPeer != null) {
						IBNAView iView = worldThingPeer.getInnerView();
						if (iView != null) {
							IThing iThing = firstOrNull(iView.getThingsAt(DefaultCoordinate.forLocal(
									location.getLocalPoint(), iView.getCoordinateMapper())));
							if (iThing != null) {
								ObjRef iRef = iThing.get(IHasObjRef.OBJREF_KEY);
								if (iRef != null
										&& XadlUtils.isInstanceOf(xarch, iRef, Structure_3_0Package.Literals.INTERFACE)) {
									Interface oiface = XArchADTProxy.proxy(xarch, outerInterfaceRef);
									Interface iiface = XArchADTProxy.proxy(xarch, iRef);
									InterfaceMapping mapping = XArchADTProxy.proxy(xarch,
											XadlUtils.create(xarch, Structure_3_0Package.Literals.INTERFACE_MAPPING));
									mapping.setId(UIDGenerator.generateUID());
									mapping.setOuterInterfaceLink(oiface);
									mapping.setInnerInterfaceLink(iiface);
									mapping.setName("" + iiface.getName() + " to " + oiface.getName());
									XArchADTOperations.add("Add Interface Mapping", xarch, subArchitectureRef,
											"interfaceMapping", XArchADTProxy.unproxy(mapping));
								}
							}
						}
					}
				}
				view.getBNAWorld().getBNAModel().removeThing(indicatorSpline);
				indicatorSpline = null;
			}
			else if (evt.button == 3) {
				view.getBNAWorld().getBNAModel().removeThing(indicatorSpline);
				indicatorSpline = null;
			}
		}
	}
}
