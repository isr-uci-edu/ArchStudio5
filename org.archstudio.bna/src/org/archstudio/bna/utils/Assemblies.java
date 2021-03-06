package org.archstudio.bna.utils;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.awt.Insets;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasStickyShape;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.coordinating.MirrorBoundingBoxLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.coordinating.OrientDirectionalLabelLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.events.WorldThingExternalEventsLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.things.shapes.EllipseThing;
import org.archstudio.bna.things.shapes.EndpointThing;
import org.archstudio.bna.things.shapes.MappingThing;
import org.archstudio.bna.things.shapes.PolygonThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.things.shapes.ReshapeHandleThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.things.utility.NoThing;
import org.archstudio.bna.things.utility.WorldThing;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Assemblies link individual things together to make <i>simple</i> composite things. In an assembly of things, a single
 * thing is treated as the "root" of the assembly and the other things are treated as the "parts" of the assembly. It
 * doesn't matter what thing is the root and what things are the parts, so long as they are consistently used. A thing
 * may be a part of one (and only one) assembly and a root of one (and only one) assembly at a time.
 * <p>
 * Below is an example of an assembly of things to make a "component" assembly:
 * 
 * <pre>
 *    Assembly Root Key       Things         Assembly Part Key
 * 
 *                             TOP
 *                      ____________________                  
 *                     /             (PART)/                  
 * .&lt;---------------- /    WorldThing     / &lt;--- WORLD_KEY ---.
 * |                 /___________________/                    |
 * |                    ____________________                  |
 * |                   /             (PART)/                  | 
 * |&lt;---------------- / BoundedLabelThing / &lt;--- TEXT_KEY ----|
 * |                 /___________________/                    |
 * |                    ____________________                  |
 * |                   /             (ROOT)/                  |
 * '--- ROOT_KEY ---&gt; /  RectangleThing   / -----------------&gt;'
 *                   /___________________/
 * 
 *                           BOTTOM
 * 
 * </pre>
 * 
 * The above things are created and organized by the following code. Note that the {@link MirrorValueLogic} is
 * automatically configured to mirror the bounds of the RectangleThing (the root) to the BoundedLabelThing and
 * WorldThing (the parts).
 * 
 * <pre>
 * // Creates the Assembly
 * RectangleThing root = Assemblies.createRectangle(model, null, null);
 * Assemblies.addWorld(model, null, root);
 * 
 * // Examples of Asembly Methods
 * WorldThing world = Assemblies.getPart(model, root, WORLD_KEY) // returns WorldThing
 * Assemblies.isRoot(root)                                       // returns true
 * Assemblies.isPart(root)                                       // returns false
 * Assemblies.getPartName(world)                                 // returns WORLD_KEY
 * Assemblies.getRootWithPart(model, world)                      // returns root
 * </pre>
 * 
 * Advantages of assemblies include:
 * <ul>
 * <li>Logics are automatically added and configured during creation of assemblies</li>
 * <li>Generally, properties only need to be set on one of the things (logics mirror those values to other things)</li>
 * <li>An entire assembly may be {@link #createRectangle(IBNAWorld, Object, IThing) constructed} and
 * {@link #removeRootAndParts(IBNAModel, IThing) removed} in unison</li>
 * <li>{@link #getEditableThing(IBNAModel, IThing, Class, String...) User editable} operations can be targeted to
 * different parts of an assembly</li>
 * </ul>
 * <p>
 * New Assemblies can be constructed in your own <i>MyAssemblies</i> class and by calling the static methods of this
 * class.
 */
@NonNullByDefault
public final class Assemblies {

	private Assemblies() {
	}

	private static final IThingKey<Boolean> IS_ROOT_KEY = ThingKey.create("is-assembly-root");
	private static final IThingRefKey<IThing> ROOT_KEY = ThingRefKey.create("my-assembly-root");
	private static final IThingKey<IThingRefKey<?>> PART_NAME_KEY = ThingKey.create("my-assembly-part-name");

	public static final boolean isRoot(IThing potentialRootThing) {
		checkNotNull(potentialRootThing);

		return potentialRootThing.has(IS_ROOT_KEY, true);
	}

	public static final boolean isPart(IThing potentialPartThing) {
		checkNotNull(potentialPartThing);

		return potentialPartThing.has(ROOT_KEY);
	}

	public static final void markRoot(IThing root) {
		checkNotNull(root);

		root.set(IS_ROOT_KEY, true);
	}

	public static final <T extends IThing> void markPart(IThing root, IThingRefKey<T> name, T part) {
		checkNotNull(root);
		checkNotNull(name);
		checkNotNull(part);

		markRoot(root);

		root.set(name, part.getID());
		part.set(ROOT_KEY, root.getID());
		part.set(PART_NAME_KEY, name);
	}

	public static final void unmarkPart(IBNAModel model, IThing part) {
		checkNotNull(model);
		checkNotNull(part);
		checkArgument(part.has(ROOT_KEY));
		checkArgument(part.has(PART_NAME_KEY));

		IThing root = checkNotNull(model.getThing(part.get(ROOT_KEY)));

		root.remove(part.get(PART_NAME_KEY));
		part.remove(ROOT_KEY);
		part.remove(PART_NAME_KEY);
	}

	public static final void unmarkPart(IThing root, IThing part) {
		checkNotNull(root);
		checkNotNull(part);
		checkArgument(root.getID().equals(part.get(ROOT_KEY)));
		checkArgument(part.has(PART_NAME_KEY));

		root.remove(part.get(PART_NAME_KEY));
		part.remove(ROOT_KEY);
		part.remove(PART_NAME_KEY);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public static final <T extends IThing> T getPart(IBNAModel model, @Nullable IThing root, IThingRefKey<T> name) {
		checkNotNull(model);
		checkNotNull(name);
		if (root == null) {
			return null;
		}

		IThing t = name.get(root, model);
		if (t != null && t.has(ROOT_KEY, root.getID())) {
			return (T) t;
		}

		return null;
	}

	public static final Map<IThingRefKey<?>, IThing> getParts(IBNAModel model, @Nullable IThing root) {
		checkNotNull(model);
		if (root == null) {
			return Collections.emptyMap();
		}

		Map<IThingRefKey<?>, IThing> allParts = Maps.newHashMap();

		for (Map.Entry<IThingKey<?>, ?> entry : root.entrySet()) {
			if (entry.getKey() instanceof IThingRefKey) {
				IThing t = model.getThing(entry.getValue());
				if (t != null && t.has(ROOT_KEY, root.getID())) {
					allParts.put((IThingRefKey<?>) entry.getKey(), t);
				}
			}
		}

		return allParts;
	}

	public static final IThingRefKey<?> getPartName(IThing part) {
		checkNotNull(part);

		return part.get(PART_NAME_KEY);
	}

	@Nullable
	public static final IThing getRootWithPart(IBNAModel model, @Nullable IThing part) {
		checkNotNull(model);
		if (part == null) {
			return null;
		}

		return model.getThing(part.get(ROOT_KEY));
	}

	@Nullable
	public static final IThing getRoot(IBNAModel model, @Nullable IThing rootOrPart) {
		checkNotNull(model);
		if (rootOrPart == null) {
			return null;
		}

		if (isRoot(rootOrPart)) {
			return rootOrPart;
		}

		IThing rootThing = getRootWithPart(model, rootOrPart);
		if (rootThing != null) {
			return rootThing;
		}

		return rootOrPart;
	}

	public static final void removeRootAndParts(IBNAModel model, @Nullable IThing root) {
		checkNotNull(model);
		if (root == null) {
			return;
		}

		if (isPart(root)) {
			unmarkPart(model, root);
		}
		_removeRootAndParts(model, root);
	}

	private static final void _removeRootAndParts(IBNAModel model, @Nullable IThing root) {
		for (IThing part : getParts(model, root).values()) {
			_removeRootAndParts(model, part);
		}
		model.removeThing(root);
	}

	/**
	 * Returns all of the things involved in an assembly assuming that the rootOrPart is a root, then assuming that the
	 * rootOrPart is a part of an assembly.
	 */
	public static final Collection<IThing> getAssemblyThings(IBNAModel model, @Nullable IThing rootOrPart) {
		checkNotNull(model);
		if (rootOrPart == null) {
			return Collections.emptyList();
		}

		Collection<IThing> allRelatedParts = Lists.newArrayList();
		allRelatedParts.add(rootOrPart);

		// if this is a root add the assembly
		if (isRoot(rootOrPart)) {
			allRelatedParts.addAll(getParts(model, rootOrPart).values());
		}

		// if this is a part of some assembly, add that assembly
		IThing otherRoot = getRootWithPart(model, rootOrPart);
		if (otherRoot != null) {
			allRelatedParts.add(otherRoot);
			allRelatedParts.addAll(getParts(model, otherRoot).values());
		}

		return allRelatedParts;
	}

	/**
	 * Returns the editable thing in an assembly that is an instance of the editableClass and has one of the specified
	 * editableQualities, or <code>null</code> if there is none.
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static final <T extends IThing> T getEditableThing(IBNAModel model, @Nullable IThing rootOrPart,
			Class<T> editableClass, String... editableQualities) {
		checkNotNull(model);
		checkNotNull(editableClass);
		checkNotNull(editableQualities);
		if (rootOrPart == null) {
			return null;
		}

		//HACK: a workaround for interacting with hierarchies, see #25
		if (rootOrPart instanceof IHasWorld && ((IHasWorld) rootOrPart).getWorld() != null) {
			return null;
		}

		for (IThing editableThing : Assemblies.getAssemblyThings(model, rootOrPart)) {
			if (UserEditableUtils.isEditableForAllQualities(editableThing, editableQualities)) {
				if (editableClass.isInstance(editableThing)) {
					return (T) editableThing;
				}
			}
		}

		return null;
	}

	/**
	 * Returns the thing that implements the specific class within an assembly, or <code>null</code> if there is none.
	 */
	@SuppressWarnings("unchecked")
	@Nullable
	public static final <T extends IThing> T getThingOfType(IBNAModel model, @Nullable IThing rootOrPart,
			Class<T> ofType) {
		checkNotNull(model);
		checkNotNull(ofType);
		if (rootOrPart == null) {
			return null;
		}

		//HACK: a workaround for interacting with hierarchies, see #25
		if (rootOrPart instanceof IHasWorld && ((IHasWorld) rootOrPart).getWorld() != null) {
			return null;
		}

		for (IThing relevantThing : Assemblies.getAssemblyThings(model, rootOrPart)) {
			if (ofType.isInstance(relevantThing)) {
				return (T) relevantThing;
			}
		}

		return null;
	}

	/**
	 * Returns the thing that has the given property within an assembly, or <code>null</code> if there is none.
	 */
	@Nullable
	public static final IThing getThingWithProperty(IBNAModel model, @Nullable IThing rootOrPart,
			IThingKey<?> withProperty) {
		checkNotNull(model);
		checkNotNull(withProperty);
		if (rootOrPart == null) {
			return null;
		}

		//HACK: a workaround for interacting with hierarchies, see #25
		if (rootOrPart instanceof IHasWorld && ((IHasWorld) rootOrPart).getWorld() != null) {
			return null;
		}

		for (IThing relevantThing : Assemblies.getAssemblyThings(model, rootOrPart)) {
			if (relevantThing.has(withProperty) && relevantThing.get(withProperty) != null) {
				return relevantThing;
			}
		}

		return null;
	}

	public static enum Layer {
		BASE, SPLINE, MIDDLE, ARROWHEAD, TOP
	}

	public static final IThing getLayer(IBNAModel model, Object layerThingID) {
		IThing layerThing = model.getThing(layerThingID);
		if (layerThing == null) {
			layerThing = model.getThing(layerThingID);
			if (layerThing == null) {
				for (Object layerID : Layer.values()) {
					model.addThing(new NoThing(layerID));
				}
				layerThing = model.getThing(layerThingID);
			}
		}
		if (layerThing == null) {
			throw new IllegalArgumentException("Unknown layer: " + layerThingID);
		}
		return layerThing;
	}

	public static final IThingRefKey<AnchoredLabelThing> ANCHORED_TEXT_KEY = ThingRefKey
			.create("assembly-anchored_text");
	public static final IThingRefKey<BoundedLabelThing> BOUNDED_TEXT_KEY = ThingRefKey.create("assembly-bounded-text");
	public static final IThingRefKey<DirectionalLabelThing> DIRECTION_KEY = ThingRefKey.create("assembly-direction");
	public static final IThingRefKey<WorldThing> WORLD_KEY = ThingRefKey.create("assembly-world");

	public static final EllipseThing createEllipse(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		EllipseThing bkg = model
				.addThing(new EllipseThing(id), parent != null ? parent : getLayer(model, Layer.MIDDLE));
		BoundedLabelThing label = model.addThing(new BoundedLabelThing(null), bkg);

		markRoot(bkg);
		markPart(bkg, BOUNDED_TEXT_KEY, label);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mbbl.mirrorBoundingBox(bkg, label, new Insets(3, 3, 3, 3));

		return bkg;
	}

	public static final RectangleThing createRectangle(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		RectangleThing bkg = model.addThing(new RectangleThing(id),
				parent != null ? parent : getLayer(model, Layer.MIDDLE));
		BoundedLabelThing label = model.addThing(new BoundedLabelThing(null), bkg);

		markRoot(bkg);
		markPart(bkg, BOUNDED_TEXT_KEY, label);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mbbl.mirrorBoundingBox(bkg, label, new Insets(3, 3, 3, 3));

		return bkg;
	}

	public static final <T extends IHasBoundingBox> T addWorld(IBNAWorld world, @Nullable Object id, T backgroundThing) {
		checkNotNull(world);
		checkNotNull(backgroundThing);

		IBNAModel model = world.getBNAModel();

		WorldThing worldThing = model.addThing(new WorldThing(id), backgroundThing);

		markRoot(backgroundThing);
		markPart(backgroundThing, WORLD_KEY, worldThing);

		IThingLogicManager tlm = world.getThingLogicManager();
		tlm.addThingLogic(WorldThingExternalEventsLogic.class);
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mbbl.mirrorBoundingBox(backgroundThing, worldThing, new Insets(6, 6, 6, 6));

		return backgroundThing;
	}

	public static final PolygonThing createPolygon(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		PolygonThing bkg = model
				.addThing(new PolygonThing(id), parent != null ? parent : getLayer(model, Layer.MIDDLE));

		markRoot(bkg);

		return bkg;
	}

	public static final EndpointThing createEndpoint(IBNAWorld world, @Nullable Object id,
			@Nullable IHasStickyShape parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		EndpointThing bkg = model.addThing(new EndpointThing(id), parent != null ? parent : getLayer(model, Layer.TOP));
		bkg.setColor(new RGB(255, 255, 255));
		bkg.setSecondaryColor(null);
		DirectionalLabelThing direction = model.addThing(new DirectionalLabelThing(null), bkg);
		direction.setLocalInsets(new Insets(2, 2, 2, 2));

		markRoot(bkg);
		markPart(bkg, DIRECTION_KEY, direction);

		IThingLogicManager tlm = world.getThingLogicManager();
		MirrorBoundingBoxLogic mbbl = tlm.addThingLogic(MirrorBoundingBoxLogic.class);

		mbbl.mirrorBoundingBox(bkg, direction, new Insets(0, 0, 0, 0));

		if (parent != null) {
			StickPointLogic stickLogic = tlm.addThingLogic(StickPointLogic.class);
			stickLogic.stick(bkg, IHasAnchorPoint.ANCHOR_POINT_KEY, StickyMode.EDGE, parent);
			if (parent instanceof IHasBoundingBox) {
				OrientDirectionalLabelLogic orientLogic = tlm.addThingLogic(OrientDirectionalLabelLogic.class);
				orientLogic.orient((IHasBoundingBox) parent, direction);
			}
		}

		return bkg;
	}

	public static final SplineThing createSpline(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		SplineThing bkg = model.addThing(new SplineThing(id), parent != null ? parent : getLayer(model, Layer.SPLINE));

		markRoot(bkg);

		return bkg;
	}

	public static final MappingThing createMapping(IBNAWorld world, @Nullable Object id, @Nullable IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		MappingThing bkg = model
				.addThing(new MappingThing(id), parent != null ? parent : getLayer(model, Layer.SPLINE));

		markRoot(bkg);

		return bkg;
	}

	public static final ReshapeHandleThing createHandle(IBNAWorld world, @Nullable Object id, IThing parent) {
		checkNotNull(world);

		IBNAModel model = world.getBNAModel();

		ReshapeHandleThing bkg = model.addThing(new ReshapeHandleThing(id), parent);

		markRoot(bkg);

		return bkg;
	}
}
