package org.archstudio.core;

import org.archstudio.launcher.core.LauncherView;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ArchStudio5PerspectiveFactory implements IPerspectiveFactory {

	//private static boolean appInited = false;

	public ArchStudio5PerspectiveFactory() {
	}

	@Override
	@SuppressWarnings("deprecation")
	public synchronized void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.25f, editorArea);

		layout.addView(IPageLayout.ID_RES_NAV, IPageLayout.BOTTOM, 0.66f, IPageLayout.ID_OUTLINE);

		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.66f, editorArea);

		//bottom.addView("edu.uci.isr.archstudio4.comp.fileman.FileManagerView");
		bottom.addView(LauncherView.class.getName());
		//bottom.addView("org.archstudio.comp.filetracker.FileTrackerView");
		bottom.addView(IPageLayout.ID_TASK_LIST);
	}
}
