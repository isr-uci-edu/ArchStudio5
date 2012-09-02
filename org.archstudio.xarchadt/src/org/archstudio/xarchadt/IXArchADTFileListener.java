package org.archstudio.xarchadt;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IXArchADTFileListener {
	public void handleXArchADTFileEvent(XArchADTFileEvent evt);
}
