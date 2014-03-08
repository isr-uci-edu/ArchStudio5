package org.archstudio.bna;

import java.util.List;

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
	 * end of the bulk change. This method is optional; it need not be implemented by models. Note that each call to
	 * beginBulkChange MUST be accompanied by a paired <code>endBulkChange()</code> call.
	 */
	public void beginBulkChange();

	/**
	 * Callers should invoke this when they have completed a bulk change to the model, that is, many changes to the
	 * model in quick succession. This generally results in a <code>BULK_CHANGE_END</code> event being emitted from the
	 * model and can provide listeners with a hint to hold normally per-event processing (like repaints) until the end
	 * of the bulk change. This method is optional; it need not be implemented by models.
	 */
	public void endBulkChange();

	public <T extends IThing> T addThing(T thing);

	public <T extends IThing> T addThing(T thing, IThing parentThing);

	public <T extends IThing> T insertThing(T thing, IThing beforeThing);

	public void removeThing(IThing thing);

	public void removeThingAndChildren(IThing thing);

	public @Nullable
	IThing getThing(@Nullable Object thingID);

	public List<IThing> getThingsByID(Iterable<Object> thingIDs);

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
