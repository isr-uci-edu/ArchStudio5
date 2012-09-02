package org.archstudio.xarchadt;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTModelListener {
	public void handleXArchADTModelEvent(XArchADTModelEvent evt);
}
