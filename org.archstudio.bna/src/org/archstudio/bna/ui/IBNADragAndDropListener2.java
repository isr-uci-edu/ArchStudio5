package org.archstudio.bna.ui;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.constants.DNDActionType;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;

/**
 * A BNA drag/drop listener that uses the logic described in
 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} to guide which world logics will be called for drag/drop
 * events. If the event is associated with a view, then the view's world's logics are used, otherwise the thing's
 * world's logics are used. If the logic implements {@link IBNAAllEventsListener2}, then it will always receive events.
 * <p>
 * Note: All {@link Transfer} providers must implement {@link IUITransferProvider} and be registered with the
 * org.archstudio.bna.dndtransferprovider plugin extension.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IBNADragAndDropListener2 {
	/**
	 * Analogous to {@link DropTargetListener#dragOver(org.eclipse.swt.dnd.DropTargetEvent) dragOver},
	 * {@link DropTargetListener#dragOperationChanged(org.eclipse.swt.dnd.DropTargetEvent) dragOperationChange}, and
	 * {@link DropTargetListener#dropAccept(org.eclipse.swt.dnd.DropTargetEvent) dropAccept}, however, with additional
	 * data about the BNA view and location. This method should indicate whether the logic's
	 * {@link #drop(IBNAView, DNDType, DNDData, ICoordinate, ThingsAtLocation) drop} method may be called by setting the
	 * {@link DNDData#setDropType(org.archstudio.bna.constants.DNDActionType) drop type} on data, which defaults to
	 * {@link DNDActionType#NONE NONE}. The drop type from all logics is cumulative in that the user will be informed of
	 * the most significant drop type returned by logics implementing this interface. See {@link DNDActionType} for the
	 * order of increasing drop type impact.
	 * <p>
	 * Note that some OS's don't provide the actual data until after the user has finished the drop. In this case, data
	 * will be empty. So, logics should not require that the data be present at this stage.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The DND event type, equal to {@link DNDType#DRAG}.
	 * @param data
	 *            The DND data extracted from the transfer objects and keyed off the transfered objects types.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void drag(IBNAView view, DNDType type, DNDData data, ICoordinate location,
			ThingsAtLocation thingsAtLocation);

	/**
	 * Analogous to {@link DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent) drop}, however, with additional
	 * data about the BNA view and location. This method will only be called if the
	 * {@link #drag(IBNAView, DNDType, DNDData, ICoordinate, ThingsAtLocation) drag} method sets the
	 * {@link DNDData#setDropType(org.archstudio.bna.constants.DNDActionType) drop type} on data to a value other than
	 * {@link DNDActionType#NONE NONE}.
	 *
	 * @param view
	 *            The view of the logic.
	 * @param type
	 *            The DND event type, equal to {@link DNDType#DROP}.
	 * @param data
	 *            The DND data extracted from the transfer objects and keyed off the transfered objects types.
	 * @param location
	 *            The location in the view.
	 * @param thingsAtLocation
	 *            The things at the location under the original view. This may differ from the view of this logic if the
	 *            logic implements {@link IBNAAllEventsListener2}.
	 */
	public void drop(IBNAView view, DNDType type, DNDData data, ICoordinate location,
			ThingsAtLocation thingsAtLocation);
}