package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.archstudio.bna.logics.AbstractKeyCoordinatingThingLogic;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAUtils;

import com.google.common.collect.Lists;

/**
 * This logic re-parents a thing so that it is a child of a given thing id. This is useful, for example, to force
 * interfaces to always be children of the bricks that have them. Optionally, when re-parenting, a thing can be placed
 * before or after a sibling specified by a {@link BNAPath} from the parent thing.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class ReparentToThingIDLogic extends AbstractKeyCoordinatingThingLogic implements IBNAModelListener {
	/** The key storing the parent thing's id. */
	public static final IThingRefKey<IThing> REPARENT_TO_THING_KEY =
			ThingRefKey.create(Lists.newArrayList("reparent-to-thing", ReparentToThingIDLogic.class));

	/**
	 * The key storing optional parameters that indicate which sibling a thing should be placed by. May be
	 * <code>null</code>.
	 */
	public static final IThingKey<ReparentParams> REPARENT_TO_THING_PARAMS_KEY =
			ThingKey.create(Lists.newArrayList("reparent-to-thing-parameters", ReparentToThingIDLogic.class));

	/**
	 * Parameters to specify which sibling a thing should be placed before or after.
	 *
	 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
	 */
	public static final class ReparentParams {
		/** The BNA path leading to the sibling under the parent to stack next to. */
		private final BNAPath siblingPath;

		/** Whether the thing should be stacked before (<code>true</code>) or after (<code>false</code>) the sibling. */
		private final boolean before;

		/**
		 * Indicates which sibling a thing should be placed before or after.
		 *
		 * @param siblingPath
		 *            The BNA path leading to the sibling under the parent to stack next to.
		 * @param before
		 *            Whether the thing should be stacked before (<code>true</code>) or after (<code>false</code>) the
		 *            sibling.
		 */
		public ReparentParams(BNAPath siblingPath, boolean before) {
			this.siblingPath = siblingPath;
			this.before = before;
		}

		public BNAPath getSiblingPath() {
			return siblingPath;
		}

		public boolean isBefore() {
			return before;
		}
	}

	public ReparentToThingIDLogic(IBNAWorld world) {
		super(world, false);
		track(REPARENT_TO_THING_KEY);
		track(REPARENT_TO_THING_PARAMS_KEY);
	}

	/**
	 * Returns the key storing the parent thing's id.
	 *
	 * @return the key storing the parent thing's id.
	 */
	public IThingRefKey<IThing> getReparentToThingKey() {
		BNAUtils.checkLock();
		return REPARENT_TO_THING_KEY;
	}

	/**
	 * Returns the key storing optional parameters that indicate which sibling a thing should be placed by.
	 *
	 * @return the key storing optional parameters that indicate which sibling a thing should be placed by.
	 */
	public IThingKey<ReparentParams> getReparentToThingParamsKey() {
		BNAUtils.checkLock();
		return REPARENT_TO_THING_PARAMS_KEY;
	}

	@Override
	protected void update(IThing thing, IThingKey<?> key) {
		IThing parentThing = REPARENT_TO_THING_KEY.get(thing, model);
		if (parentThing != null) {
			ReparentParams params = thing.get(REPARENT_TO_THING_PARAMS_KEY);
			if (params != null) {
				IThing sibling = BNAPath.resolve(model, parentThing, params.siblingPath);
				if (sibling != null) {
					IThing siblingParent = model.getParentThing(sibling);
					if (siblingParent != null) {
						model.reparent(siblingParent, thing);
						if (params.isBefore()) {
							model.moveBefore(thing, sibling);
						}
						else {
							model.moveAfter(thing, sibling);
						}
					}
				}
			}
			else {
				model.reparent(parentThing, thing);
			}
		}
	}
}
