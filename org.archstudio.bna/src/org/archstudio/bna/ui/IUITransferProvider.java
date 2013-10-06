package org.archstudio.bna.ui;

import java.util.Collection;

import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;

public interface IUITransferProvider {
	public Transfer getTransferInstance();

	public Collection<Object> getData(TransferData transferData);
}
