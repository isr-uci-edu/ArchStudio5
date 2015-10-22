package org.archstudio.bna.constants;

import java.util.Map;
import java.util.Set;

import org.archstudio.bna.ui.IBNADragAndDropListener2;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Information used to communicate DND events to, and receive input from, {@link IBNADragAndDropListener2} methods.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public class DNDData {
	private DNDActionType dropType = DNDActionType.DEFAULT;
	private final Map<Class<?>, Object> data = Maps.newHashMap();

	/**
	 * Returns the drop type.
	 *
	 * @return the drop type.
	 */
	public DNDActionType getDropType() {
		return dropType;
	}

	/**
	 * Resets the drop type returned by {@link #getDropType() getDropType} to {@link DNDActionType#NONE NONE}.
	 */
	public void resetDropType() {
		dropType = DNDActionType.DEFAULT;
	}

	/**
	 * Sets the drop type. Note, calls to this method by multiple calls to different logic's
	 * {@link IBNADragAndDropListener2#drag(org.archstudio.bna.IBNAView, DNDType, DNDData, org.archstudio.bna.ICoordinate, org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation)
	 * drag} method accumulate to the highest value of {@link DNDActionType}. The result is that the user is informed of
	 * the DND event that has the highest impact to the document.
	 *
	 * @param dropType
	 *            The new drop type.
	 */
	public void setDropType(DNDActionType dropType) {
		this.dropType = dropType.ordinal() > this.dropType.ordinal() ? dropType : this.dropType;
	}

	/**
	 * Returns the data key types associated with the DND source. The source data from the transfers will be stored and
	 * accessible through {@link #getData(Class) getData}, keyed by object type.
	 *
	 * @return The data type keys used for {@link #getData(Class) getData}.
	 */
	public Set<Class<?>> getDataTypes() {
		return Sets.newHashSet(data.keySet());
	}

	/**
	 * Returns the data associated with the given type, as extracted from the transfer source.
	 *
	 * @param ofType
	 *            The type of data to retrieve.
	 * @return the data associated with the given type, as extracted from the transfer source.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getData(Class<T> ofType) {
		return (T) data.get(ofType);
	}

	/**
	 * Adds data to this DND event, extracted from the transfer source.
	 *
	 * @param data
	 *            The data to add.
	 */
	public <T> void addData(Object data) {
		if (data != null) {
			this.data.put(data.getClass(), data);
		}
	}
}