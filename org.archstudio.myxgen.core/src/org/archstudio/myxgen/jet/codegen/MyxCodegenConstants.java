package org.archstudio.myxgen.jet.codegen;

/**
 * Constans for Myx code generation.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 *
 */
public interface MyxCodegenConstants {
	
	/**
	 * the variable name used to pass brick info to jet2 templates
	 * see MyxCodeGenerator#generateSkeleonCodeJet2()
	 * see templates/MyxBase.java.jet
	 * see templates/MyxComp.java.jet
	 */
	public final static String JET2_TEMPLATE_VARIABLE_NAME_BRICK = "brick";
	
	/** property file for codegen*/
	public static final String MYX_CODEGEN_PROPERTY = "myxcodegen.properties";
	
	/** preference key of formatter*/
	public static final String PREF_CODEGEN_FORMATTER = "org.archstudio.myxgen.jet.formatter";
}
