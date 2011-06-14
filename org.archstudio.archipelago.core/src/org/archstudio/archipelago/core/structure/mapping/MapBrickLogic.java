package org.archstudio.archipelago.core.structure.mapping;

import java.util.List;

import org.archstudio.bna.facets.IHasMutableSelected;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.information.ToolTipLogic;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.xadlbna.logics.mapping.AbstractXADLToBNAPathLogic;
import org.archstudio.xadlbna.logics.mapping.BNAPath;
import org.archstudio.xadlbna.things.IHasXArchID;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public class MapBrickLogic extends AbstractXADLToBNAPathLogic<RectangleGlassThing> {

	protected final Dimension defaultSize;

	public MapBrickLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath, Dimension defaultSize) {
		super(xarch, rootObjRef, objRefPath);
		this.defaultSize = defaultSize;
		mapAttribute("id", null, null, BNAPath.create(), IHasXArchID.XARCH_ID_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(Assemblies.TEXT_KEY), IHasText.TEXT_KEY, true);
		mapAttribute("name", null, "[no name]", BNAPath.create(), ToolTipLogic.TOOL_TIP_KEY, true);
	}

	@Override
	protected RectangleGlassThing addThing(List<ObjRef> relativeAncestorRefs, ObjRef objRef) {

		RectangleGlassThing thing = Assemblies.createRectangle(getBNAWorld(), null, null);
		thing.setBoundingBox(new Rectangle(new Point(10000, 10000), defaultSize));

		UserEditableUtils.addEditableQualities(thing, IHasMutableSelected.USER_MAY_SELECT,
				IHasMutableSize.USER_MAY_RESIZE, IRelativeMovable.USER_MAY_MOVE);
		UserEditableUtils.addEditableQualities(Assemblies.TEXT_KEY.get(thing, getBNAModel()),
				IHasMutableText.USER_MAY_EDIT_TEXT);

		return thing;
	}
}
