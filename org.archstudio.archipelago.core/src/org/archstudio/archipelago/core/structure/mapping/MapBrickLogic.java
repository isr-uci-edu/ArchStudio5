package org.archstudio.archipelago.core.structure.mapping;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import java.awt.Dimension;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.archipelago.core.structure.StructureEditorSupport;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasCount;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.myx.fw.Services;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xadl.bna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadl.bna.things.IHasObjRef;
import org.archstudio.xadl.bna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

/**
 * Maps xADL Bricks (i.e., Components or Connectors) to BNA Rectangle
 * Assemblies.
 */
public class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleGlassThing> {
	protected final Services services;
	protected final Dimension defaultSize;
	protected final RGB defaultColor;
	protected final int defaultCount;
	protected final FontStyle defaultFontStyle;

	public MapBrickLogic(Services services, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
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
		syncValue("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncValue("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
		addBNAUpdater(new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt,
					RectangleGlassThing rootThing) {

				updateSubstructure(objRef, xadlPath, evt, rootThing);
			}
		});
	}

	@Override
	protected RectangleGlassThing addThing(List<ObjRef> relLineageRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		RectangleGlassThing thing = Assemblies.createRectangleWithWorld(getBNAWorld(), null, null);
		thing.setBoundingBox(new Rectangle(newPointSpot.x, newPointSpot.y, defaultSize.width, defaultSize.height));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, defaultColor);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasSecondaryColor.SECONDARY_COLOR_KEY,
				BNAUtils.adjustBrightness(defaultColor, 1.5f));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasCount.COUNT_KEY, defaultCount);
		Assemblies.TEXT_KEY.get(thing, getBNAModel()).set(IHasFontData.FONT_STYLE_KEY, defaultFontStyle);

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableSize.USER_MAY_RESIZE, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}

	protected void updateSubstructure(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt,
			RectangleGlassThing rootThing) {

		IHasMutableWorld worldThing = castOrNull(
				BNAPath.resolve(getBNAModel(), rootThing, BNAPath.create(Assemblies.WORLD_KEY)), IHasMutableWorld.class);
		if (worldThing != null) {
			ObjRef innerStructureRef = null;

			ObjRef subStructureRef = (ObjRef) xarch.get(objRef, "subStructure");
			if (subStructureRef != null) {
				innerStructureRef = (ObjRef) xarch.get(subStructureRef, "innerStructureLink");
			}
			// If innerStructureRef is null, then we need to remove the world from the worldThing.
			// Otherwise, we need to add it and hook it up.
			if (innerStructureRef == null) {
				worldThing.remove(IHasWorld.WORLD_KEY);
				worldThing.set(IHasObjRef.OBJREF_KEY, null);
			}
			else {
				ObjRef documentRootRef = xarch.getDocumentRootRef(objRef);
				IBNAWorld internalWorld = StructureEditorSupport.setupWorld(services, xarch, documentRootRef, innerStructureRef);
				if (internalWorld != null) {
					worldThing.setWorld(internalWorld);
					worldThing.set(IHasObjRef.OBJREF_KEY, subStructureRef);
				}
			}
		}
	}

}
