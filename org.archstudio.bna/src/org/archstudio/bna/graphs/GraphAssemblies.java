package org.archstudio.bna.graphs;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.graphs.things.AxisThing;
import org.archstudio.bna.graphs.things.GraphGridLinesThing;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.things.shapes.PreciselyAnchoredShapeThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;

public class GraphAssemblies {

	public static final IThingRefKey<AxisThing> TOP_AXIS_KEY = ThingRefKey.create("assembly-top-axis");
	public static final IThingRefKey<AxisThing> BOTTOM_AXIS_KEY = ThingRefKey.create("assembly-bottom-axis");
	public static final IThingRefKey<AxisThing> LEFT_AXIS_KEY = ThingRefKey.create("assembly-left-axis");
	public static final IThingRefKey<AxisThing> RIGHT_AXIS_KEY = ThingRefKey.create("assembly-right-axis");
	public static final IThingRefKey<GraphGridLinesThing> HORIZONTAL_GRID_LINES_KEY = ThingRefKey
			.create("assembly-horizontal-grid-lines");
	public static final IThingRefKey<GraphGridLinesThing> VERTICAL_GRID_LINES_KEY = ThingRefKey
			.create("assembly-vertical-grid-lines");
	public static final IThingRefKey<PreciselyAnchoredShapeThing> DATA_POINT_KEY = ThingRefKey
			.create("assembly-data-point");

	public static RectangleThing createGraph(IBNAWorld world, @Nullable Object id, String topLabel, String bottomLabel,
			String leftLabel, String rightLabel) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager logics = world.getThingLogicManager();

		GraphGridLinesThing hGridLinesThing = model.addThing(new GraphGridLinesThing(null));
		GraphGridLinesThing vGridLinesThing = model.addThing(new GraphGridLinesThing(null), hGridLinesThing);
		RectangleThing graphThing = model.addThing(new RectangleThing(id), vGridLinesThing);
		graphThing.setColor(null);

		Assemblies.markPart(graphThing, HORIZONTAL_GRID_LINES_KEY, hGridLinesThing);
		Assemblies.markPart(graphThing, VERTICAL_GRID_LINES_KEY, vGridLinesThing);

		hGridLinesThing.setOrientation(GraphGridLinesThing.Orientation.HORIZONTAL_LINES);
		vGridLinesThing.setOrientation(GraphGridLinesThing.Orientation.VERTICAL_LINES);

		MirrorValueLogic mvl = logics.addThingLogic(MirrorValueLogic.class);
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, hGridLinesThing);
		mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, vGridLinesThing);

		if (topLabel != null) {
			AxisThing topAxisThing = model.addThing(new AxisThing(null), vGridLinesThing);
			Assemblies.markPart(graphThing, TOP_AXIS_KEY, topAxisThing);
			topAxisThing.setOrientation(AxisThing.Orientation.TOP);
			topAxisThing.setText(topLabel);
			mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, topAxisThing,
					IHasBoundingBox.BOUNDING_BOX_KEY, new Function<Rectangle, Rectangle>() {

						@Override
						public Rectangle apply(Rectangle input) {
							// position the left axis to the left of the plot area
							return new Rectangle(input.x, input.y - 2, input.width, 2);
						}
					});
		}
		if (bottomLabel != null) {
			AxisThing bottomAxisThing = model.addThing(new AxisThing(null), vGridLinesThing);
			Assemblies.markPart(graphThing, BOTTOM_AXIS_KEY, bottomAxisThing);
			bottomAxisThing.setOrientation(AxisThing.Orientation.BOTTOM);
			bottomAxisThing.setText(bottomLabel);
			mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, bottomAxisThing,
					IHasBoundingBox.BOUNDING_BOX_KEY, new Function<Rectangle, Rectangle>() {

						@Override
						public Rectangle apply(Rectangle input) {
							// position the left axis to the left of the plot area
							return new Rectangle(input.x, input.y + input.height, input.width, 2);
						}
					});
		}
		if (leftLabel != null) {
			AxisThing leftAxisThing = model.addThing(new AxisThing(null), vGridLinesThing);
			Assemblies.markPart(graphThing, LEFT_AXIS_KEY, leftAxisThing);
			leftAxisThing.setOrientation(AxisThing.Orientation.LEFT);
			leftAxisThing.setText(leftLabel);
			mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, leftAxisThing,
					IHasBoundingBox.BOUNDING_BOX_KEY, new Function<Rectangle, Rectangle>() {

						@Override
						public Rectangle apply(Rectangle input) {
							// position the bottom axis just below the plot area
							return new Rectangle(input.x - 2, input.y, 2, input.height);
						}
					});
		}
		if (rightLabel != null) {
			AxisThing rightAxisThing = model.addThing(new AxisThing(null), vGridLinesThing);
			Assemblies.markPart(graphThing, RIGHT_AXIS_KEY, rightAxisThing);
			rightAxisThing.setOrientation(AxisThing.Orientation.RIGHT);
			rightAxisThing.setText(rightLabel);
			mvl.mirrorValue(graphThing, IHasBoundingBox.BOUNDING_BOX_KEY, rightAxisThing,
					IHasBoundingBox.BOUNDING_BOX_KEY, new Function<Rectangle, Rectangle>() {

						@Override
						public Rectangle apply(Rectangle input) {
							// position the bottom axis just below the plot area
							return new Rectangle(input.x + input.width, input.y, 2, input.height);
						}
					});
		}

		return graphThing;
	}

	public static PreciselyAnchoredShapeThing createDataPoint(IBNAWorld world, @Nullable Object id) {

		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		PreciselyAnchoredShapeThing dataPointThing = model.addThing(new PreciselyAnchoredShapeThing(id));

		Assemblies.markRoot(dataPointThing);

		return dataPointThing;
	}
}
