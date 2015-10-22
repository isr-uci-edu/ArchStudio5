package org.archstudio.bna.ui;

import org.archstudio.bna.constants.DNDData;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * An interface for {@link Transfer} types used in DND operations. Implementations of this class must extend
 * {@link Transfer} and implement a public static method that returns the singleton instance of the type:
 *
 * <pre>
 * <code>
 * public static Transfer getInstance() {...};
 * </code>
 * </pre>
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public interface IUITransferProvider {
	/**
	 * Method signature for {@link Transfer#isSupportedType(TransferData)}.
	 */
	public boolean isSupportedType(TransferData transferData);

	/**
	 * Adds the data from <code>transferData</code> to <code>dndData</code>.
	 *
	 * @param transferData
	 *            The {@link TransferData} containing the DND source data.
	 * @param dndData
	 *            The {@link DNDData} to receive the data.
	 */
	public void addData(TransferData transferData, DNDData dndData);
}
