package org.archstudio.myxgen.jet.ui;

import java.io.IOException;

import org.archstudio.myxgen.jet.codegen.MyxCodeGenFormatter;
import org.archstudio.myxgen.jet.codegen.MyxCodegenConstants;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * Codegen Preferences page.
 * 
 * //TODO: need to make this class ArchStudio-like by using PreferencesADT via a myx component
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 *
 */
public class MyxCodegenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage{
	
	/** CodeStyle file field*/
	private FileFieldEditor formatterFileEditor;
	
	private PreferenceStore ps;
	public MyxCodegenPreferencePage() {
		super(GRID);
		setDescription("MyxCodegen preferences");
		// Set the preference store
		ps = new PreferenceStore(MyxCodegenConstants.MYX_CODEGEN_PROPERTY);
		try {
			ps.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		setPreferenceStore(new PreferenceStore(JET2Constants.MYX_CODEGEN_PROPERTY));
	}
	
	@Override
	protected void createFieldEditors() {

		formatterFileEditor = new FileFieldEditor(MyxCodegenConstants.PREF_CODEGEN_FORMATTER, "Code Style Formatter", getFieldEditorParent());
		formatterFileEditor.setFileExtensions(new String[]{"xml"});
//		formatterFileEditor.setStringValue(getPreferenceStore().getString(JET2Constants.PREF_CODEGEN_FORMATTER));
		formatterFileEditor.setStringValue(ps.getString(MyxCodegenConstants.PREF_CODEGEN_FORMATTER));
		addField(formatterFileEditor);
		
	}
	

//	@Override
//	protected IPreferenceStore doGetPreferenceStore() {
//		return new PreferenceStore(JET2Constants.MYX_CODEGEN_PROPERTY);
//	}

	public void init(IWorkbench workbench) {
		
	}

	@Override
	public boolean performOk() {

		//TODO: temporary, will be changed to use ArchStudio preferences
		MyxCodeGenFormatter.setCodeFormatterFileName(formatterFileEditor.getStringValue());
		ps.setValue(MyxCodegenConstants.PREF_CODEGEN_FORMATTER, formatterFileEditor.getStringValue());
		try {
			ps.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.performOk();
	}

}
