package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Insets;
import java.util.Collection;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasInternalWorldEndpoint;
import org.archstudio.bna.facets.IHasLineStyle;
import org.archstudio.bna.facets.IHasLineWidth;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableWorld;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasRoundedCorners;
import org.archstudio.bna.facets.IHasSize;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.AbstractThingRefKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.OrientDirectionalLabelLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.coordinating.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.coordinating.WorldThingInternalEventsLogic;
import org.archstudio.bna.things.glass.EllipseGlassThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.glass.MappingGlassThing;
import org.archstudio.bna.things.glass.PolygonGlassThing;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.things.glass.ReshapeHandleGlassThing;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.things.labels.ArrowheadThing;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.things.shapes.MappingThing;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.NoThing;
import org.archstudio.bna.things.utility.WorldThing;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Sets;

public class Assemblies {

	public static final class ThingAssemblyKey<D, T extends IThing> extends AbstractThingRefKey<D, T> {

		public static <D, T extends IThing> IThingRefKey<T> create(D keyData) {
			return new ThingAssemblyKey<D, T>(keyData, true);
		}

		protected ThingAssemblyKey(D keyData, boolean isFireEventOnChange) {
			super(keyData, isFireEventOnChange);
		}
	}

	private static final IThingKey<Boolean> IS_ROOT_KEY = ThingKey.create("is-assembly-root");
	private static final IThingRefKey<IThing> ROOT_KEY = ThingRefKey.create("my-assembly-root");
	private static final IThingKey<IThingRefKey<?>> PART_KEY = ThingKey.create("my-assembly-part");

	public static final IThingRefKey<IThing> BACKGROUND_KEY = ThingAssemblyKey.create("assembly-background");
	public static final IThingRefKey<IHasMutableText> TEXT_KEY = ThingAssemblyKey.create("assembly-text");
	public static final IThingRefKey<DirectionalLabelThing> LABEL_KEY = ThingAssemblyKey.create("assembly-label");
	public static final IThingRefKey<IHasMutableWorld> WORLD_KEY = ThingAssemblyKey.create("assembly-world");
	public static final IThingRefKey<IThing> ARROWHEAD1_KEY = ThingAssemblyKey.create("assembly-arrowhead-1");
	public static final IThingRefKey<IThing> ARROWHEAD2_KEY = ThingAssemblyKey.create("assembly-arrowhead-2");

	public static final Object BASE_LAYER_THING_ID = new Object();
	public static final Object SPLINE_LAYER_THING_ID = new Object();
	public static final Object ARROWHEAD_LAYER_THING_ID = new Object();
	public static final Object MIDDLE_LAYER_THING_ID = new Object();
	public static final Object TOP_LAYER_THING_ID = new Object();

	protected static IThing initLayer(IBNAModel model, Object layerThingID, IThing parentThing) {
		IThing noThing = model.getThing(layerThingID);
		if (noThing == null) {
			model.addThing(new NoThing(layerThingID), parentThing);
		}
		return noThing;
	}

	@SuppressWarnings("unused")
	protected static void initLayers(IBNAModel model) {
		if (model.getThing(BASE_LAYER_THING_ID) != null) {
			return;
		}

		IThing baseLayerThing = initLayer(model, BASE_LAYER_THING_ID, null);
		IThing splineLayerThing = initLayer(model, SPLINE_LAYER_THING_ID, baseLayerThing);
		IThing arrowheadLayerThing = initLayer(model, ARROWHEAD_LAYER_THING_ID, baseLayerThing);
		IThing middleLayerThing = initLayer(model, MIDDLE_LAYER_THING_ID, baseLayerThing);
		IThing topLayerThing = initLayer(model, TOP_LAYER_THING_ID, baseLayerThing);
	}

	public static IThing getLayer(IBNAModel model, Object layerThingID) {
		initLayers(model);
		return model.getThing(layerThingID);
	}

	public static final <T extends IThing> void markPart(IThing root, IThingRefKey<T> name, T part) {
		root.set(IS_ROOT_KEY, true);
		root.set(name, part.getID());
		part.set(ROOT_KEY, root.getID());
		part.set(PART_KEY, name);
	}

	public static boolean isAssembly(IThing potentialRootThing) {
		return Boolean.TRUE.equals(potentialRootThing.get(IS_ROOT_KEY));
	}

	public static final <T extends IThing> T getPart(IBNAModel model, IThing root, IThingRefKey<T> name) {
		checkNotNull(model);
		checkNotNull(name);

		return name.get(root, model);
	}

	public static final IThing getAssemblyWithPart(IBNAModel model, IThing part) {
		checkNotNull(model);
		checkNotNull(part);

		return model.getThing(part.get(ROOT_KEY));
	}

	public static final IThing getAssemblyWithRootOrPart(IBNAModel model, IThing rootOrPart) {
		checkNotNull(model);
		checkNotNull(rootOrPart);

		if (rootOrPart.has(IS_ROOT_KEY, true)) {
			return rootOrPart;
		}
		return model.getThing(rootOrPart.get(ROOT_KEY));
	}

	public static final IThingRefKey<?> getPartKey(IThing part) {
		checkNotNull(part);

		return part.get(PART_KEY);
	}

	public static Collection<IThing> getRelatedParts(IBNAModel model, IThing part) {
		Collection<IThing> allParts = Sets.newHashSet(part);
		for (IThingKey<?> k : part.keySet()) {
			if (k instanceof ThingAssemblyKey) {
				IThing t = ((IThingRefKey<?>) k).get(part, model);
				if (t != null) {
					allParts.add(t);
				}
			}
		}
		IThing root = ROOT_KEY.get(part, model);
		if (root != null) {
			allParts.add(root);
			for (IThingKey<?> k : root.keySet()) {
				if (k instanceof ThingAssemblyKey) {
					IThing t = ((IThingRefKey<?>) k).get(root, model);
					if (t != null) {
						allParts.add(t);
					}
				}
			}
		}
		return allParts;
	}

	public static Collection<IThing> getParts(IBNAModel model, IThing root) {
		Collection<IThing> allParts = Sets.newHashSet();
		for (IThingKey<?> k : root.keySet()) {
			if (k instanceof ThingAssemblyKey) {
				IThing t = ((IThingRefKey<?>) k).get(root, model);
				if (t != null) {
					allParts.add(t);
				}
			}
		}
		return allParts;
	}

	public static void removeRootAndParts(IBNAModel model, IThing root) {
		for (IThing part : getParts(model, root)) {
			removeRootAndParts(model, part);
		}
		model.removeThing(root);
	}

	public static EllipseGlassThing createEllipse(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		EllipseThing bkg = model.addThing(new EllipseThing(null),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		EllipseGlassThing glass = model.addThing(new EllipseGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, bkg);

		return glass;
	}

	public static RectangleGlassThing createRectangle(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);
		IBNAModel model = world.getBNAModel();

		RectangleThing bkg = model.addThing(new RectangleThing(null),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		BoundedLabelThing label = model.addThing(new BoundedLabelThing(null), bkg);
		RectangleGlassThing glass = model.addThing(new RectangleGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);
		markPart(glass, TEXT_KEY, label);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, bkg);
		mvl.mirrorValue(glass, IHasRoundedCorners.CORNER_SIZE_KEY, bkg);
		mbbl.mirrorBoundingBox(glass, label, new Insets(3, 3, 3, 3));

		return glass;
	}

	//TODO: change this to addWorldThing(...)
	public static RectangleGlassThing createRectangleWithWorld(IBNAWorld world, @Nullable Object id,
			@Nullable IThing parent) {
		RectangleGlassThing glass = createRectangle(world, id, parent);

		IBNAModel model = world.getBNAModel();

		WorldThing worldThing = model.addThing(new WorldThing(null), glass);

		markPart(glass, WORLD_KEY, worldThing);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);
		tlm.addThingLogic(WorldThingInternalEventsLogic.class);
		tlm.addThingLogic(WorldThingExternalEventsLogic.class);

		mbbl.mirrorBoundingBox(glass, worldThing, new Insets(6, 6, 6, 6));

		return glass;
	}

	public static PolygonGlassThing createPolygon(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		PolygonThing bkg = model.addThing(new PolygonThing(null),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		PolygonGlassThing glass = model.addThing(new PolygonGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasPoints.POINTS_KEY, bkg);
		mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, bkg);

		return glass;
	}

	public static EndpointGlassThing createEndpoint(IBNAWorld world, @Nullable Object id, @Nullable IIsSticky parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		RectangleThing bkg = model.addThing(new RectangleThing(null),
				parent != null ? parent : getLayer(model, TOP_LAYER_THING_ID));
		bkg.setColor(new RGB(255, 255, 255));
		bkg.setSecondaryColor(null);
		DirectionalLabelThing label = model.addThing(new DirectionalLabelThing(null), bkg);
		//FIXME: label.setLocalInsets(new Insets(2, 2, 2, 2));
		//AnchoredLabelThing text = model.addThing(new AnchoredLabelThing(null), bkg);
		EndpointGlassThing glass = model.addThing(new EndpointGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);
		markPart(glass, LABEL_KEY, label);
		//mark(glass, TEXT_KEY, text);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, bkg);
		//mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, text);
		//mvl.mirrorValue(label, IHasOrientation.ORIENTATION_KEY, text, IHasAngle.ANGLE_KEY, orientationToAngleFn);
		//mvl.mirrorValue(label, IHasOrientation.ORIENTATION_KEY, text, IHasHorizontalAlignment.HORIZONTAL_ALIGNMENT_KEY, orientationToHorizontalAlignmentFn);
		//mvl.mirrorValue(glass, IHasSize.SIZE_KEY, text, IHasOffset.OFFSET_KEY, sizeToOffsetFn);
		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, label);
		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, label);

		if (parent != null) {
			StickPointLogic stickLogic = tlm.addThingLogic(StickPointLogic.class);
			stickLogic.stick(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, StickyMode.EDGE, parent);
			if (parent instanceof IHasBoundingBox) {
				OrientDirectionalLabelLogic orientLogic = tlm.addThingLogic(OrientDirectionalLabelLogic.class);
				orientLogic.orient((IHasBoundingBox) parent, label);
			}
		}

		return glass;
	}

	public static SplineGlassThing createSpline(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		SplineThing bkg = model.addThing(new SplineThing(null),
				parent != null ? parent : getLayer(model, SPLINE_LAYER_THING_ID));
		SplineGlassThing glass = model.addThing(new SplineGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_1_KEY, bkg);
		mvl.mirrorValue(glass, IHasMidpoints.MIDPOINTS_KEY, bkg);
		mvl.mirrorValue(glass, IHasEndpoints.ENDPOINT_2_KEY, bkg);

		return glass;
	}

	public static SplineGlassThing addArrowhead(IBNAWorld world, SplineGlassThing splineGlassThing,
			IThingKey<Point> endpointKey, @Nullable IThing parent) {
		checkNotNull(world);
		checkArgument(endpointKey == IHasEndpoints.ENDPOINT_1_KEY || endpointKey == IHasEndpoints.ENDPOINT_2_KEY,
				"endpointKey must be IHasEndpoints.ENDPOINT_1_KEY or IHasEndpoints.ENDPOINT_2_KEY");

		IBNAModel model = world.getBNAModel();

		ArrowheadThing arrowheadThing = model.addThing(new ArrowheadThing(null),
				parent != null ? parent : getLayer(model, ARROWHEAD_LAYER_THING_ID));

		markPart(splineGlassThing, endpointKey == IHasEndpoints.ENDPOINT_1_KEY ? ARROWHEAD1_KEY : ARROWHEAD2_KEY,
				arrowheadThing);

		IThingLogicManager tlm = world.getThingLogicManager();
		ArrowheadLogic al = tlm.addThingLogic(ArrowheadLogic.class);
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		al.point(arrowheadThing, splineGlassThing, endpointKey);
		mvl.mirrorValue(Assemblies.BACKGROUND_KEY.get(splineGlassThing, model), IHasEdgeColor.EDGE_COLOR_KEY,
				arrowheadThing, IHasColor.COLOR_KEY);
		mvl.mirrorValue(Assemblies.BACKGROUND_KEY.get(splineGlassThing, model), IHasLineStyle.LINE_STYLE_KEY,
				arrowheadThing);
		mvl.mirrorValue(Assemblies.BACKGROUND_KEY.get(splineGlassThing, model), IHasLineWidth.LINE_WIDTH_KEY,
				arrowheadThing);

		return splineGlassThing;
	}

	public static MappingGlassThing createMapping(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		MappingThing bkg = model.addThing(new MappingThing(null),
				parent != null ? parent : getLayer(model, SPLINE_LAYER_THING_ID));
		MappingGlassThing glass = model.addThing(new MappingGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, bkg);
		mvl.mirrorValue(glass, IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_KEY, bkg);
		mvl.mirrorValue(glass, IHasInternalWorldEndpoint.INTERNAL_ENDPOINT_WORLD_THING_KEY, bkg);

		return glass;
	}

	public static ReshapeHandleGlassThing createHandle(IBNAWorld world, @Nullable Object id, IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		ReshapeHandleThing bkg = model.addThing(new ReshapeHandleThing(null), parent);
		ReshapeHandleGlassThing glass = model.addThing(new ReshapeHandleGlassThing(id), bkg);

		markPart(glass, BACKGROUND_KEY, bkg);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, bkg);
		mvl.mirrorValue(glass, IHasSize.SIZE_KEY, bkg);

		return glass;
	}
}
