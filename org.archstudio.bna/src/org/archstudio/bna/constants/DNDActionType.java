package org.archstudio.bna.constants;

import java.awt.dnd.DnDConstants;

import org.eclipse.swt.dnd.DND;

public enum DNDActionType {
	NONE(DnDConstants.ACTION_NONE, DND.DROP_NONE), //
	COPY(DnDConstants.ACTION_COPY, DND.DROP_COPY), //
	LINK(DnDConstants.ACTION_LINK, DND.DROP_LINK), //
	MOVE(DnDConstants.ACTION_MOVE, DND.DROP_MOVE);

	private final int awtAction;
	private final int swtDNDDropOperation;

	private DNDActionType(int awtAction, int swtDNDDropOperation) {
		this.awtAction = awtAction;
		this.swtDNDDropOperation = swtDNDDropOperation;
	}

	public int toAwtAction() {
		return awtAction;
	}

	public int toSWTDNDDropOperation() {
		return swtDNDDropOperation;
	}
}