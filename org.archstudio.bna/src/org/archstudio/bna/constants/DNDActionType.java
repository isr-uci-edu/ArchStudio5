package org.archstudio.bna.constants;

import java.awt.dnd.DnDConstants;

import org.eclipse.swt.dnd.DND;

/**
 * Analogous to {@link DND} constants. However, ordered in increasing impact to the document such that if two logics
 * report different action types, the action type with the higher value will be displayed to the user.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public enum DNDActionType {
	// These values must be in increasing order of impact to the document.
	DEFAULT(DnDConstants.ACTION_NONE, DND.DROP_DEFAULT), //
	NONE(DnDConstants.ACTION_NONE, DND.DROP_NONE), //
	LINK(DnDConstants.ACTION_LINK, DND.DROP_LINK), //
	COPY(DnDConstants.ACTION_COPY, DND.DROP_COPY), //
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