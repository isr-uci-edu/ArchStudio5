package org.archstudio.bna.constants;

import org.eclipse.swt.dnd.DND;

public enum DNDType {
	ENTER(DND.DragEnter), //
	EXIT(DND.DragLeave), //
	DRAG(DND.DragOver), //
	DROP(DND.Drop);

	private final int swtDndType;

	private DNDType(int swtDndType) {
		this.swtDndType = swtDndType;
	}

	public int toSwtDndType() {
		return swtDndType;
	}
}