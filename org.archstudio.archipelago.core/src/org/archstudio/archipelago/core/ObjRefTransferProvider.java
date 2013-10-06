package org.archstudio.archipelago.core;

import java.util.Collection;
import java.util.List;

import org.archstudio.bna.ui.IUITransferProvider;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;

import com.google.common.collect.Lists;

public class ObjRefTransferProvider implements IUITransferProvider {

	@Override
	public Transfer getTransferInstance() {
		return ObjRefTransfer.getInstance();
	}

	@Override
	public Collection<Object> getData(TransferData transferData) {
		List<Object> data = Lists.newArrayList();
		List<ObjRef> objRefs = ObjRefTransfer.getInstance().nativeToJava(transferData);
		if (objRefs != null) {
			data.add(objRefs);
			if (objRefs.size() == 1) {
				data.add(objRefs.get(0));
			}
		}
		return data;
	}

}
