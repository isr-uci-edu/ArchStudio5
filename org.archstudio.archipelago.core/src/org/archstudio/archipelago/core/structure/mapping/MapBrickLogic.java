package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasSecondaryColor;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;

public class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleGlassThing> {

	protected final Dimension defaultSize;
	protected final RGB defaultColor;

	public MapBrickLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath, Dimension defaultSize, RGB defaultColor) {
		super(xarch, rootObjRef, objRefPath);
		this.defaultSize = defaultSize;
		this.defaultColor = defaultColor;
	}

	@Override
	public void init() {
		super.init();
		syncAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		syncAttribute("name", null, "[no name]", BNAPath.create(), IHasToolTip.TOOL_TIP_KEY, true);
	}

	@Override
	protected RectangleGlassThing addThing(List<ObjRef> relativeAncestorRefs, ObjRef objRef) {

		Point newPointSpot = ArchipelagoUtils.findOpenSpotForNewThing(getBNAWorld().getBNAModel());

		RectangleGlassThing thing = Assemblies.createRectangle(getBNAWorld(), null, null);
		thing.setBoundingBox(new Rectangle(newPointSpot, defaultSize));
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasColor.COLOR_KEY, defaultColor);
		Assemblies.BACKGROUND_KEY.get(thing, getBNAModel()).set(IHasSecondaryColor.SECONDARY_COLOR_KEY,
				BNAUtils.adjustBrightness(defaultColor, 1.5f));

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableSize.USER_MAY_RESIZE, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
