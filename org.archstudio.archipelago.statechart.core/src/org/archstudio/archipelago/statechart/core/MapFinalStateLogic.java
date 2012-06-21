package org.archstudio.archipelago.statechart.core;

import java.awt.Dimension;
import java.awt.Insets;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasCount;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasLocalInsets;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.glass.EllipseGlassThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.Assemblies.ThingAssemblyKey;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class MapFinalStateLogic extends AbstractXADLToBNAPathLogic<EllipseGlassThing> {
	protected final Services services;
	protected final Dimension defaultSize;
	protected final RGB defaultColor;
	protected final int defaultCount;
	protected final FontStyle defaultFontStyle;

	public static final IThingRefKey<IHasEdgeColor> EDGE_KEY = ThingAssemblyKey.create("assembly-edge");

	public MapFinalStateLogic(Services services, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
			Dimension defaultSize, RGB defaultColor, int defaultCount, FontStyle defaultFontStyle) {
		super(xarch, rootObjRef, objRefPath);
		this.services = services;
		this.defaultSize = defaultSize;
		this.defaultColor = defaultColor;
		this.defaultCount = defaultCount;
		this.defaultFontStyle = defaultFontStyle;
	}

	@Override
	public void init() {
		super.init();
		syncValue("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
		//		addBNAUpdater(new IBNAUpdater() {
		//
		//			@Override
		//			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt,
		//					RectangleGlassThing rootThing) {
		//				updateSubstructure(objRef, xadlPath, evt, rootThing);
		//			}
		//		});
	}

	@Override
	protected EllipseGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		EllipseThing edge = getBNAModel().addThing(new EllipseThing(null));
		edge.setLineWidth(2);
		edge.setColor(null);

		EllipseGlassThing thing = Assemblies.createEllipse(getBNAWorld(), null, edge);
		thing.setBoundingBox(new Rectangle(newPointSpot.x, newPointSpot.y, defaultSize.width, defaultSize.height));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, defaultColor);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasSecondaryColor.SECONDARY_COLOR_KEY,
				BNAUtils.adjustBrightness(defaultColor, 1.5f));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasCount.COUNT_KEY, defaultCount);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasLocalInsets.LOCAL_INSETS_KEY,
				new Insets(4, 4, 4, 4));
		
		Assemblies.markPart(thing, EDGE_KEY, edge);

		IThingLogicManager tlm = getBNAWorld().getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(thing, IHasBoundingBox.BOUNDING_BOX_KEY, edge);
		
		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IRelativeMovable.USER_MAY_MOVE);

		return thing;
	}
	//	protected void updateSubstructure(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt,
	//			RectangleGlassThing rootThing) {
	//
	//		IHasMutableWorld worldThing = castOrNull(
	//				BNAPath.resolve(getBNAModel(), rootThing, BNAPath.create(Assemblies.WORLD_KEY)), IHasMutableWorld.class);
	//		if (worldThing != null) {
	//			ObjRef innerStructureRef = null;
	//
	//			ObjRef subStructureRef = (ObjRef) xarch.get(objRef, "subStructure");
	//			if (subStructureRef != null) {
	//				innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
	//			}
	//			// If innerStructureRef is null, then we need to remove the world from the worldThing.
	//			// Otherwise, we need to add it and hook it up.
	//			if (innerStructureRef == null) {
	//				worldThing.remove(IHasWorld.WORLD_KEY);
	//				worldThing.set(IHasObjRef.OBJREF_KEY, null);
	//			}
	//			else {
	//				ObjRef documentRootRef = xarch.getDocumentRootRef(objRef);
	//				IBNAWorld internalWorld = StatechartTreePlugin.setupWorld(services, xarch, documentRootRef, innerStructureRef);
	//				if (internalWorld != null) {
	//					worldThing.setWorld(internalWorld);
	//					worldThing.set(IHasObjRef.OBJREF_KEY, subStructureRef);
	//				}
	//			}
	//		}
	//	}

}
