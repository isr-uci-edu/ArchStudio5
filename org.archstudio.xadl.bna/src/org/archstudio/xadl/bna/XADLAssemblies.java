package org.archstudio.xadl.bna;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.xadl.bna.things.MappingSplineGlassThing;

public class XADLAssemblies extends Assemblies {

	public static MappingSplineGlassThing createMapping(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		SplineThing bkg = model.addThing(new SplineThing(null),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		MappingSplineGlassThing glass = model.addThing(new MappingSplineGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_1_KEY, bkg);
		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_2_KEY, bkg);
		mvl.mirrorValue(glass, IHasMidpoints.MIDPOINTS_KEY, bkg);

		return glass;
	}

}
