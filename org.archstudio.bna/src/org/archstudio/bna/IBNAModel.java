package org.archstudio.bna;

import java.util.Collection;
import java.util.List;

import org.archstudio.sysutils.Finally;
import org.eclipse.jdt.annotation.Nullable;

public interface IBNAModel {

	public interface RunnableStreamNotification extends Runnable {
	}

	public void addBNAModelListener(IBNAModelListener l);

	public void removeBNAModelListener(IBNAModelListener l);

	public void fireStreamNotificationEvent(Object streamNotification);

	/**
	 * Callers should invoke this when they are about to start a bulk change to the model, that is, many changes to the
	 * model in quick succession. This generally results in a <code>BULK_CHANGE_BEGIN</code> event being emitted from
	 * the model and can provide listeners with a hint to hold normally per-event processing (like repaints) until the
	 * end of the bulk change. This method is optional; it need not be implemented by models. Calls to this method
	 * should be wrapped in a try-with-resource block, which ensures that the bulk change is properly bounded. For
	 * example:
	 * 
	 * <pre>
	 * IBNAModel model = ...;
	 * try (Finally bulkChange = model.beginBulkChange) {
	 *    ...
	 * }
	 * </pre>
	 */
	public Finally beginBulkChange();

	public <T extends IThing> T addThing(T thing);

	public <T extends IThing> T addThing(T thing, IThing parentThing);

	public <T extends IThing> T insertThing(T thing, IThing beforeThing);

	public void removeThing(IThing thing);

	public void removeThingAndChildren(IThing thing);

	public @Nullable
	IThing getThing(@Nullable Object thingID);

	public List<IThing> getThingsByID(Collection<Object> thingIDs);

	public List<IThing> getAllThings();

	public int getNumThings();

	public IThing getParentThing(IThing thing);

	public List<IThing> getChildThings(IThing thing);

	public List<IThing> getAncestorThings(IThing thing);

	public List<IThing> getDescendantThings(IThing thing);

	public void bringToFront(IThing thing);

	public void sendToBack(IThing thing);

	public void reparent(IThing newParent, IThing thing);

	public void dispose();

}
