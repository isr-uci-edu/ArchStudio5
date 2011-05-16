package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;

public interface IHasMutableReferencePointFractionOffset extends IRelativeMovable, IHasBoundingBox {

	public static final IThingKey<float[]> REFERENCE_POINT_FRACTION_OFFSET_KEY = IThingKey
			.createKey("referencePointFractionOffset");

	public float[] getReferencePointFractionOffset();

	public void setReferencePointFractionOffset(float[] offset);
}
