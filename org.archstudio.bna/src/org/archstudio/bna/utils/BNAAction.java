package org.archstudio.bna.utils;

import org.archstudio.sysutils.Finally;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Event;

public abstract class BNAAction extends Action {

	public BNAAction() {
		super();
	}

	public BNAAction(String text, ImageDescriptor image) {
		super(text, image);
	}

	public BNAAction(String text, int style) {
		super(text, style);
	}

	public BNAAction(String text) {
		super(text);
	}

	@Override
	public final void run() {
		try (Finally lock = BNAUtils.lock()) {
			runWithLock();
		}
	}

	@Override
	public final void runWithEvent(Event event) {
		try (Finally lock = BNAUtils.lock()) {
			runWithEventAndLock(event);
		}
	}

	public void runWithLock() {
	}

	public void runWithEventAndLock(Event event) {
		runWithLock();
	}

}
