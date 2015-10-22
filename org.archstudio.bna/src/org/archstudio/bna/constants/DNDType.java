package org.archstudio.bna.constants;

import org.eclipse.swt.dnd.DND;

public enum DNDType {
	@Deprecated ENTER(DND.DragEnter), //
	@Deprecated EXIT(DND.DragLeave), //
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