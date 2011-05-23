package org.archstudio.editors;

/**
 * Myx brick: "Abstract ArchStudio Editor Component Impl"
 * 
 * @see org.archstudio.editors.AbstractArchstudioEditorMyxComponentStub
 * @generated
 */
public abstract class AbstractArchstudioEditorMyxComponent extends
		org.archstudio.editors.AbstractArchstudioEditorMyxComponentStub {

	protected String editorName = null;
	protected String eclipseEditorID = null;
	protected boolean registerWithEditorManager = false;

	protected AbstractArchstudioEditorMyxComponent(String editorName,
			String eclipseEditorID, boolean registerWithEditorManager) {
		this.editorName = editorName;
		this.eclipseEditorID = eclipseEditorID;
		this.registerWithEditorManager = registerWithEditorManager;
	}
}