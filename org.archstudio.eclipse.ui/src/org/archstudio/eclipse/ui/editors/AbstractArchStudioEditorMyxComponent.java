package org.archstudio.eclipse.ui.editors;

/**
 * Myx brick: "Abstract ArchStudio Editor Component Impl"
 * 
 * @see org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponentStub
 * @generated
 */
public abstract class AbstractArchStudioEditorMyxComponent extends
		org.archstudio.eclipse.ui.editors.AbstractArchStudioEditorMyxComponentStub {

	protected String editorName = null;
	protected String eclipseEditorID = null;
	protected boolean registerWithEditorManager = false;

	protected AbstractArchStudioEditorMyxComponent(String editorName, String eclipseEditorID,
			boolean registerWithEditorManager) {
		this.editorName = editorName;
		this.eclipseEditorID = eclipseEditorID;
		this.registerWithEditorManager = registerWithEditorManager;
	}
}