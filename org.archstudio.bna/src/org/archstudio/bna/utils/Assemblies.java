package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javax.annotation.Nullable;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.keys.AbstractThingRefKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.StickAnchorPointLogic;
import org.archstudio.bna.things.glass.EllipseGlassThing;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.glass.PolygonGlassThing;
import org.archstudio.bna.things.glass.RectangleGlassThing;
import org.archstudio.bna.things.glass.SplineGlassThing;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.things.shapes.EndpointThing;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.NoThing;
import org.eclipse.draw2d.geometry.Insets;

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

	private static final IThingKey<Boolean> IS_ROOT_KEY = ThingKey.create("assembly-is-root");
	private static final IThingRefKey<IThing> ROOT_KEY = ThingRefKey.create("assembly-root");
	private static final IThingKey<IThingRefKey<?>> PART_KEY = ThingKey.create("assembly-part");

	public static final IThingRefKey<IThing> SHADOW_KEY = ThingAssemblyKey.create("assembly-shadow");
	public static final IThingRefKey<IThing> BACKGROUND_KEY = ThingAssemblyKey.create("assembly-background");
	public static final IThingRefKey<IHasText> TEXT_KEY = ThingAssemblyKey.create("assembly-text");
	public static final IThingRefKey<DirectionalLabelThing> LABEL_KEY = ThingAssemblyKey.create("assembly-label");

	public static final Object BASE_LAYER_THING_ID = new Object();
	public static final Object SPLINE_LAYER_THING_ID = new Object();
	public static final Object MIDDLE_LAYER_THING_ID = new Object();

	protected static IThing initLayer(IBNAModel model, Object layerThingID, IThing parentThing) {
		IThing noThing = model.getThing(layerThingID);
		if (noThing == null) {
			model.addThing(new NoThing(layerThingID), parentThing);
		}
		return noThing;
	}

	@SuppressWarnings("unused")
	protected static void initLayers(IBNAModel model) {
		IThing baseLayerThing = initLayer(model, BASE_LAYER_THING_ID, null);
		IThing splineLayerThing = initLayer(model, SPLINE_LAYER_THING_ID, baseLayerThing);
		IThing middleLayerThing = initLayer(model, MIDDLE_LAYER_THING_ID, baseLayerThing);
	}

	public static IThing getLayer(IBNAModel model, Object layerThingID) {
		initLayers(model);
		return model.getThing(layerThingID);
	}

	protected static final <T extends IThing> void mark(IThing root, IThingRefKey<T> name, T part) {
		root.set(IS_ROOT_KEY, true);
		root.set(name, part.getID());
		part.set(ROOT_KEY, root.getID());
		part.set(PART_KEY, name);
	}

	public static final IThing getAssemblyWithPart(IBNAModel model, IThing part) {
		checkNotNull(part);

		return model.getThing(part.get(ROOT_KEY));
	}

	public static final IThing getAssemblyWithRootOrPart(IBNAModel model, IThing rootOrPart) {
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

	public static Iterable<IThing> getRelatedParts(IBNAModel model, IThing part) {
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

	public static Iterable<IThing> getParts(IBNAModel model, IThing root) {
		Collection<IThing> allParts = Sets.newHashSet(root);
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

	public static EllipseGlassThing createEllipse(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		EllipseThing bkg = model.addThing(new EllipseThing(id),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		EllipseGlassThing glass = model.addThing(new EllipseGlassThing(null), bkg);

		mark(glass, BACKGROUND_KEY, bkg);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, bkg);

		return glass;
	}

	public static RectangleGlassThing createRectangle(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		RectangleThing bkg = model.addThing(new RectangleThing(id),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		BoundedLabelThing label = model.addThing(new BoundedLabelThing(null), bkg);
		RectangleGlassThing glass = model.addThing(new RectangleGlassThing(null), bkg);

		mark(glass, BACKGROUND_KEY, bkg);
		mark(glass, TEXT_KEY, label);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, bkg);
		mbbl.mirrorBoundingBox(glass, new Insets(3, 3, 3, 3), label);

		return glass;
	}

	public static PolygonGlassThing createPolygon(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		PolygonThing bkg = model.addThing(new PolygonThing(id),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		PolygonGlassThing glass = model.addThing(new PolygonGlassThing(null), bkg);

		mark(glass, BACKGROUND_KEY, bkg);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasPoints.POINTS_KEY, bkg);
		mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, bkg);

		return glass;
	}

	public static EndpointGlassThing createEndpoint(IBNAWorld world, @Nullable Object id, @Nullable IIsSticky parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		EndpointThing bkg = model.addThing(new EndpointThing(id),
				parent != null ? parent : getLayer(model, MIDDLE_LAYER_THING_ID));
		DirectionalLabelThing label = model.addThing(new DirectionalLabelThing(null), bkg);
		label.setLocalInsets(new Insets(2, 2, 2, 2));
		EndpointGlassThing glass = model.addThing(new EndpointGlassThing(null), bkg);

		mark(glass, BACKGROUND_KEY, bkg);
		mark(glass, LABEL_KEY, label);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasAnchorPoint.ANCHOR_POINT_KEY, bkg);
		mvl.mirrorValue(glass, IHasBoundingBox.BOUNDING_BOX_KEY, label);

		if (parent != null) {
			StickAnchorPointLogic sapl = tlm.addThingLogic(StickAnchorPointLogic.class);
			sapl.stick(parent, StickyMode.EDGE, glass);
		}

		return glass;
	}

	public static SplineGlassThing createSpline(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();
		IThingLogicManager tlm = world.getThingLogicManager();

		SplineThing bkg = model.addThing(new SplineThing(id),
				parent != null ? parent : getLayer(model, SPLINE_LAYER_THING_ID));
		SplineGlassThing glass = model.addThing(new SplineGlassThing(null), bkg);

		mark(glass, BACKGROUND_KEY, bkg);

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(glass, IHasPoints.POINTS_KEY, bkg);

		return glass;
	}
}
