package org.archstudio.archlight;

public enum DoubleClickAction {
	OPEN_DETAIL_WINDOW, FOCUS_IN_DEFAULT_EDITOR;

	@Override
	public String toString() {
		switch (this) {
		case OPEN_DETAIL_WINDOW:
			return "Open Detail Window";
		case FOCUS_IN_DEFAULT_EDITOR:
			return "Open Default Editor";
		}
		return super.toString();
	}
}
