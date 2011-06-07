package org.archstudio.bna.facets;

import org.archstudio.bna.keys.ThingKey;
import org.eclipse.draw2d.geometry.PrecisionDimension;

public interface IHasMutableDimensionMultiplier extends IRelativeMovable, IHasBoundingBox {

	public static final IThingKey<PrecisionDimension> REFERENCE_POINT_FRACTION_OFFSET_KEY = ThingKey
			.create("referencePointFractionOffset");

	public PrecisionDimension getDimensionMultiplier();

	public void setDimensionMultiplier(PrecisionDimension dimensionMultiplier);
}
