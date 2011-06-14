package org.archstudio.bna;

public interface IBNAModel {

	public void addBNAModelListener(IBNAModelListener l);

	public void removeBNAModelListener(IBNAModelListener l);

	public void addSynchronousBNAModelListener(IBNASynchronousModelListener l);

	public void removeSynchronousBNAModelListener(IBNASynchronousModelListener l);

	public void fireStreamNotificationEvent(String streamNotification);

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

	public void removeThing(IThing thing);

	public void removeThingAndChildren(IThing thing);

	public IThing getThing(Object thingId);

	public Iterable<IThing> getThings();

	public Iterable<IThing> getThings(Iterable<Object> thingIDs);

	public Iterable<IThing> getReverseThings();

	public int getNumThings();

	public IThing getParentThing(IThing thing);

	public Iterable<IThing> getChildThings(IThing thing);

	public Iterable<IThing> getAncestorThings(IThing thing);

	public Iterable<IThing> getDescendantThings(IThing thing);

	public void stackAbove(IThing lowerThing, Iterable<? extends IThing> upperThings);

	public void bringToFront(Iterable<? extends IThing> things);

	public void sendToBack(Iterable<? extends IThing> things);

	public void reparent(IThing newParent, IThing thing);

}
