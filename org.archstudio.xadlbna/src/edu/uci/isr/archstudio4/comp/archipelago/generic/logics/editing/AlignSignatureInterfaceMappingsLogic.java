package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.editing;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Action;

public class AlignSignatureInterfaceMappingsLogic extends AbstractThingLogic implements IBNAMenuListener {

	final protected ReferenceTrackingLogic rtl;

	public AlignSignatureInterfaceMappingsLogic(ReferenceTrackingLogic rtl) {
		this.rtl = rtl;
	}

	public void fillMenu(final IBNAView view, IMenuManager m, int localX, int localY, final IThing t, int worldX,
			int worldY) {
		if (t != null) {
			try {
				final IBNAModel model = getBNAModel();
				final ICoordinateMapper cm = view.getCoordinateMapper();

				IAssembly assembly = AssemblyUtils.getAssemblyWithPart(t);

				final IHasBoundingBox boxThing = AssemblyUtils.getPart(assembly, "glass", IHasBoundingBox.class);
				final IHasWorld worldThing = AssemblyUtils.getPart(assembly, "world", IHasWorld.class);

				if (model != null && boxThing != null && worldThing != null) {

					final IBNAView innnerView = ((WorldThingPeer) view.getPeer(worldThing)).getInnerView();
					final IBNAModel innerModel = worldThing.getWorld().getBNAModel();
					final ICoordinateMapper innerCM = innnerView.getCoordinateMapper();
					final Rectangle r = boxThing.getBoundingBox();

					m.add(new Action("Align Interface-Signature Mappings") {

						@Override
						public void run() {
							try {
								for (IHasInternalWorldMapping mappingThing : BNAUtils.toThings(
										rtl.getThingsReferencing(worldThing.getID(),
												IHasInternalWorldMapping.INTERNAL_ENDPOINT_WORLD_THING_ID_PROPERTY_NAME),
										model, IHasInternalWorldMapping.class)) {
									try {
										IAssembly innerAssembly = AssemblyUtils.getAssemblyWithPart(innerModel
												.getThing(mappingThing.getInternalEndpointStuckToThingID()));
										IAssembly outerAssembly = AssemblyUtils.getAssemblyWithPart(model
												.getThing(MaintainStickyPointLogic.getStuckToThingId(
														IHasAnchorPoint.ANCHOR_POINT_PROPERTY_NAME, mappingThing)));
										IHasMutableAnchorPoint outerThing = AssemblyUtils.getPart(outerAssembly,
												"glass", IHasMutableAnchorPoint.class);

										if (innerAssembly != null && outerAssembly != null && outerThing != null) {
											Point innerPoint = mappingThing.getInternalEndpoint();
											float localX = innerCM.worldXtoLocalX((float) innerPoint.x);
											float localY = innerCM.worldYtoLocalY((float) innerPoint.y);
											Point outerPoint = new Point(Math.round(cm.localXtoWorldX(localX)), Math
													.round(cm.localYtoWorldY(localY)));
											Point oldOuterPoint = outerThing.getAnchorPoint();
											Point newOuterPoint = null;

											Orientation innerOrientation = null;
											if (innerAssembly != null) {
												for (IThing partThing : innerAssembly.getParts()) {
													if (partThing instanceof IHasOrientation) {
														innerOrientation = ((IHasOrientation) partThing)
																.getOrientation();
														break;
													}
												}
											}

											Orientation outerOrientation = null;
											if (outerAssembly != null) {
												for (IThing partThing : outerAssembly.getParts()) {
													if (partThing instanceof IHasOrientation) {
														outerOrientation = ((IHasOrientation) partThing)
																.getOrientation();
														break;
													}
												}
											}

											Orientation orientation = null;
											if (innerOrientation != null) {
												orientation = innerOrientation;
											}
											else if (outerOrientation != null) {
												orientation = outerOrientation;
											}
											else {
												// TODO: handle this situation?
											}

											if (orientation != null) {
												switch (orientation) {
												case NORTH:
													newOuterPoint = new Point(outerPoint.x, r.y);
													break;
												case SOUTH:
													newOuterPoint = new Point(outerPoint.x, r.y + r.height);
													break;
												case EAST:
													newOuterPoint = new Point(r.x + r.width, outerPoint.y);
													break;
												case WEST:
													newOuterPoint = new Point(r.x, outerPoint.y);
													break;
												}
											}

											if (newOuterPoint != null) {
												outerThing.setAnchorPoint(newOuterPoint);
											}
										}
									}
									catch (Exception e) {
										//e.printStackTrace();
									}
								}
							}
							catch (Exception e) {
								//e.printStackTrace();
							}
						}
					});
				}
			}
			catch (Exception e) {
				//e.printStackTrace();
			}
		}
	}
}