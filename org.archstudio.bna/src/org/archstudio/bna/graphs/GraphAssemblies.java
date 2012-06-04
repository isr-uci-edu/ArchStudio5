package org.archstudio.bna.graphs;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.graphs.things.AxisThing;
import org.archstudio.bna.graphs.things.GraphGridLinesThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.Assemblies.ThingAssemblyKey;
import org.eclipse.draw2d.geometry.Rectangle;

import com.google.common.base.Function;

public class GraphAssemblies {

	public static final IThingRefKey<AxisThing> HORIZONTAL_AXIS_KEY = ThingAssemblyKey
			.create("assembly-horizontal-axis");
	public static final IThingRefKey<AxisThing> VERTICAL_AXIS_KEY = ThingAssemblyKey.create("assembly-vertical-axis");
	public static final IThingRefKey<GraphGridLinesThing> HORIZONTAL_GRID_LINES_KEY = ThingAssemblyKey
			.create("assembly-horizontal-grid-lines");
	public static final IThingRefKey<GraphGridLinesThing> VERTICAL_GRID_LINES_KEY = ThingAssemblyKey
			.create("assembly-vertical-grid-lines");

	public static RectangleGlassThing createGraph(IBNAWorld world, @Nullable Object id) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		GraphGridLinesThing hGridLinesThing = model.addThing(new GraphGridLinesThing(null));
		GraphGridLinesThing vGridLinesThing = model.addThing(new GraphGridLinesThing(null), hGridLinesThing);
		AxisThing hAxisThing = model.addThing(new AxisThing(null), vGridLinesThing);
		AxisThing vAxisThing = model.addThing(new AxisThing(null), hAxisThing);
		RectangleGlassThing graphThing = model.addThing(new RectangleGlassThing(null), vAxisThing);

		Assemblies.markPart(graphThing, HORIZONTAL_GRID_LINES_KEY, hGridLinesThing);
		Assemblies.markPart(graphThing, VERTICAL_GRID_LINES_KEY, vGridLinesThing);
		Assemblies.markPart(graphThing, HORIZONTAL_AXIS_KEY, hAxisThing);
		Assemblies.markPart(graphThing, VERTICAL_AXIS_KEY, vAxisThing);

		hGridLinesThing.setOrientation(GraphGridLinesThing.Orientation.HORIZONTAL_LINES);
		vGridLinesThing.setOrientation(GraphGridLinesThing.Orientation.VERTICAL_LINES);
		hAxisThing.setOrientation(AxisThing.Orientation.BOTTOM);
		vAxisThing.setOrientation(AxisThing.Orientation.LEFT);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, hGridLinesThing);
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, vGridLinesThing);
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, hAxisThing, IHasBoundingBox.BOUNDING_BOX_KEY,
				new Function<Rectangle, Rectangle>() {
					@Override
					public Rectangle apply(Rectangle input) {
						// position the left axis to the left of the plot area
						return new Rectangle(input.x, input.y + input.height, input.width, 2);
					}
				});
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, vAxisThing, IHasBoundingBox.BOUNDING_BOX_KEY,
				new Function<Rectangle, Rectangle>() {
					@Override
					public Rectangle apply(Rectangle input) {
						// position the bottom axis just below the plot area
						return new Rectangle(input.x - 2, input.y, 2, input.height);
					}
				});

		return graphThing;
	}
}
