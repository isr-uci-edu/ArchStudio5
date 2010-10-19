package org.archstudio.bna.things.layouts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Layout;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasSWTLayout;
import org.archstudio.bna.facets.IHasSWTLayoutData;
import org.archstudio.bna.facets.IMutableMirrorsBoundingBox;

public class LayoutRootThing extends AbstractThing implements IHasMutableBoundingBox, IMutableMirrorsBoundingBox, IHasSWTLayout {

	public LayoutRootThing() {
		this(BNAUtils.generateUID(LayoutRootThing.class.getName()));
	}

	public LayoutRootThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Rectangle r = new Rectangle(0, 0, 50, 50);
		setBoundingBox(r);
	}

	public Layout getSWTLayout() {
		return getProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME);
	}

	public void setSWTLayout(Layout layout) {
		setProperty(IHasSWTLayout.SWT_LAYOUT_PROPERTY_NAME, layout);
		// TODO Refactor below layout_target_id assignment outside of this method ???
		// setProperty(IHasSWTLayoutData.LAYOUT_TARGET_ID_PROPERTY_NAME, classBox.getBoxGlassThing().getID());
	}

	public Rectangle getBoundingBox() {
		Rectangle bb = (Rectangle) getProperty(BOUNDING_BOX_PROPERTY_NAME);
		return new Rectangle(bb.x, bb.y, bb.width, bb.height);
	}

	public void setBoundingBox(Rectangle r) {
		Rectangle nr = BNAUtils.normalizeRectangle(r);
		setProperty(BOUNDING_BOX_PROPERTY_NAME, nr);
	}

	public void setBoundingBox(int x, int y, int width, int height) {
		Rectangle r = new Rectangle(x, y, width, height);
		setBoundingBox(r);
	}

	public Rectangle getBoundingBoxMirrorOffsets() {
		return (Rectangle) getProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME);
	}

	public void setBoundingBoxMasterThingID(String boundingBoxMasterThingID) {
		setProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME, boundingBoxMasterThingID);
	}

	public String getBoundingBoxMasterThingID() {
		return (String) getProperty(BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setBoundingBoxMirrorOffsets(Rectangle newOffsets) {
		setProperty(BOUNDING_BOX_MIRROR_OFFSETS_PROPETY_NAME, newOffsets);
	}

}
