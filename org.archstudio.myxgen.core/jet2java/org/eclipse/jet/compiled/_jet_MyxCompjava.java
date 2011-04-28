package org.eclipse.jet.compiled;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;
import java.util.*;
import org.archstudio.myxgen.jet.util.*;
import org.archstudio.myxgen.jet.brick.*;
import org.archstudio.myxgen.jet.codegen.CompTemplateUtil;
import org.archstudio.myxgen.jet.codegen.MyxCodegenConstants;

public class _jet_MyxCompjava implements JET2Template {
    private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$

    public _jet_MyxCompjava() {
        super();
    }

    private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$
    
    private static final TagInfo _td_java_merge_8_1 = new TagInfo("java:merge", //$NON-NLS-1$
            8, 1,
            new String[] {
            },
            new String[] {
            } );

    public void generate(final JET2Context context, final JET2Writer __out) {
        JET2Writer out = __out;
        RuntimeTagElement _jettag_java_merge_8_1 = context.getTagFactory().createRuntimeTag(_jetns_java, "merge", "java:merge", _td_java_merge_8_1); //$NON-NLS-1$ //$NON-NLS-2$
        _jettag_java_merge_8_1.setRuntimeParent(null);
        _jettag_java_merge_8_1.setTagInfo(_td_java_merge_8_1);
        _jettag_java_merge_8_1.doStart(context, out);
        _jettag_java_merge_8_1.doEnd();
        out.write(NL);         
 
	//CodegenBrick brick = (CodegenBrick) argument; 
	CodegenBrick brick = (CodegenBrick)context.getVariable(MyxCodegenConstants.JET2_TEMPLATE_VARIABLE_NAME_BRICK);
	String packageName = TextUtil.getPackagePart(brick.getFQDefaultImplClassName());
	String className = TextUtil.getClassPart(brick.getFQDefaultImplClassName());
	String baseClassName = TextUtil.getClassPart(brick.getFQBaseClassName());

 if (packageName != null && packageName.length() > 0) { 

        out.write("package ");  //$NON-NLS-1$        
        out.write(packageName);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
 }

        out.write(NL);         
 for(String javaImport : CompTemplateUtil.getImports(brick)){

        out.write("import ");  //$NON-NLS-1$        
        out.write(javaImport);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
 }

        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * Myx brick component.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getComment(brick.getDescription(), 0));
        out.write(NL);         
        out.write(" * Following methods are called automatically. They should be overrided as necessary.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * <ul>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * \t<li> init(): this brick is created</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<li> begin(): this brick is attached to others via links.</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<li> end(): this brick is detached.</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<li> destroy(): this brick is destroyed. </li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * </ul>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * The brick interface service object variable(s):");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<th>JavaInterface</th><th>service object</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<th>brick interface</th><th>direction</th><th>connection timing</th><th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
   //in brickInterfaces
	 List<CodegenBrickInterface> inIntfs = new ArrayList<CodegenBrickInterface>();
	 inIntfs.addAll(brick.getInInterfaces()); // of this brick
	 inIntfs.addAll(brick.getAncesotrInInterfaces());// of ancestors' bricks
     for(CodegenBrickInterface intf : inIntfs) {

		//variables
		if(CompTemplateUtil.isMyxVariable(intf) || CompTemplateUtil.isMyxVariablInBaseClass(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td><code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code></td><td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td><td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
		}
		//implemented
		else if (CompTemplateUtil.isImplementedInClass(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getVariableType(intf));
        out.write("</td><td>this</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td><code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code></td><td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td><td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
   	}
	 }// end of in brickInterfaces

 //out brickInterfaces
	List<CodegenBrickInterface> outIntfs = new ArrayList<CodegenBrickInterface>();
	outIntfs.addAll(brick.getOutInterfaces());//of this brick
	outIntfs.addAll(brick.getAncesotrOutInterfaces());// of ancestors' bricks
	for(CodegenBrickInterface intf : outIntfs) {
   		if(!CompTemplateUtil.isMyxVariable(intf) && !CompTemplateUtil.isMyxVariablInBaseClass(intf)) {
			continue;
   		}

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td><code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code></td><td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td><td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
	//proxy variables
    if(CompTemplateUtil.isProxyVariable(intf) || CompTemplateUtil.isProxyVariableInBaseClass(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyVariableName(intf));
        out.write("</code> as a proxy for <code>");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
  }// end of proxy variables
   }// end of out brickInterfaces

        out.write(" *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * <p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * In order to prevent the myx code generator from overwriting the content of method, ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * remove \"@generated\" annotation from java doc comment before re-run the generation");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * if any change is made to the method.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * </p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("public class ");  //$NON-NLS-1$        
        out.write(className);
        out.write(NL);         
        out.write("    extends ");  //$NON-NLS-1$        
        out.write(baseClassName);
        out.write(" {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("    ");  //$NON-NLS-1$        
        out.write(NL);         
 // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getInMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the variable for \"");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("\" brickInterface. ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprivate ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of for(CodegenBrickInterface intf : inSigs) 

 // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutSingleMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the variable for \"");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("\" brickInterface. ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprivate ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of for(CodegenBrickInterface intf : inSigs) 

 // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutMultiMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the variable for \"");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("\" brickInterface. ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprivate Collection<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("> ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = new ArrayList<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(">();");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of for(CodegenBrickInterface intf : inSigs) 

 // proxy variables
   for(CodegenBrickInterface intf : brick.getOutInterfaces()) {
    if(CompTemplateUtil.isProxyVariable(intf)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" proxy for \"");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("\". ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyVariableName(intf));
        out.write(" = ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getCreateProxyMethodName(intf));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  }
   }// end of proxy variables

        out.write("    ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("    /**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *   ");  //$NON-NLS-1$        
        out.write(NL);         
  //variables that must be assigned values in the constructor
    Collection<CodegenBrickInterface> intfsForConstructor = CompTemplateUtil.getConstructorVariables(brick);
    if(!intfsForConstructor.isEmpty() ) {

        out.write("\t * The following variable(s) must be assigned values in the constructor.");  //$NON-NLS-1$        
        out.write(NL);         
 // connectBeforeInit (for the client) 
        out.write("\t * <ul>\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface _intf : intfsForConstructor) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(_intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of for(CodegenBrickInterface _

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of if(!intfsForConstructor.isEmpty() 

        out.write("     * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("     */");  //$NON-NLS-1$        
        out.write(NL);         
  // constructors when parent is specified
	if( brick.hasParentBrick() ) {
		//constructor from ancestors
		for(MethodContainer constructor : CompTemplateUtil.getConstructors(brick)) {

        out.write("\t");  //$NON-NLS-1$        
        out.write(constructor.getModifiers());
        out.write(" ");  //$NON-NLS-1$        
        out.write(className);
        out.write("(");  //$NON-NLS-1$        
        out.write(constructor.toParamsSimpleString());
        out.write(") {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tsuper(");  //$NON-NLS-1$        
        out.write(constructor.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
		// variables need to be assigned in the constructor
		for(CodegenBrickInterface intf : CompTemplateUtil.getConstructorVariables(brick)) {

        out.write("\t\t//TODO: assign a value to this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(NL);         
		}//end of variables need to be assigned in the constructor

        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		}//end of constructors from ancestors
	}// end of constructors when parent is specified
	else {
	// the constructor when parent is not specified

        out.write("\tpublic ");  //$NON-NLS-1$        
        out.write(className);
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
		// variables need to be assigned in the constructor
		for(CodegenBrickInterface intf : CompTemplateUtil.getConstructorVariables(brick)) {

        out.write("\t\t//TODO: assign a value to this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(NL);         
		}//end of variables need to be assigned in the constructor

        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
	}// end of the constructor when parent is not specified

        out.write(NL);         
        out.write("   \t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
  //variables that will be avaialble in init()
    Collection<CodegenBrickInterface> intfsForInitAvailable = CompTemplateUtil.getInitAvailableVariables(brick);
    if(!intfsForInitAvailable.isEmpty() ) {

        out.write("\t * The following variable(s) will be assigned values before this method is called.");  //$NON-NLS-1$        
        out.write(NL);         
 // connectBeforeInit  

        out.write("\t * <ul>\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface intf : intfsForInitAvailable) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
			//proxy variables
		    if(CompTemplateUtil.isProxyVariable(intf) || CompTemplateUtil.isProxyVariableInBaseClass(intf)) {

        out.write("\t *\t<li>{@link #");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyVariableName(intf));
        out.write("} for a proxy of {@link #");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
  		}// end of proxy variables
		} // end of for(CodegenBrickInterface _

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of if(!intfsForInitAvailable.isEmpty() 

    //variables that must be assigned values in init()
    Collection<CodegenBrickInterface> intfsForInitAssign = CompTemplateUtil.getInitAssignVariables(brick);
    if(!intfsForInitAssign.isEmpty() ) {

        out.write("\t * The following variable(s) must be assigned values before this method exits.");  //$NON-NLS-1$        
        out.write(NL);         
 // connectBeforeBegin 
        out.write("\t * <ul>");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface intf : intfsForInitAssign) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
		}

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  }// end of if(!intfsForInitAssign.isEmpty() 

        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void init() {\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tsuper.init();");  //$NON-NLS-1$        
        out.write(NL);         
  	//variables that must be assigned values in init()
		for(CodegenBrickInterface intf : intfsForInitAssign) {

        out.write("\t \t//TODO: assign a value to this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(NL);         

		} // end of for(CodegenBrickInterface _

        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t @Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void begin() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tsuper.begin();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//XXX: all the variables will be available at this point.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  // proxy variables
	for(CodegenBrickInterface intf : CompTemplateUtil.getCreateProxyMethodVariables(brick)){

        out.write(NL);         
        out.write("\t/* (non-Javadoc)");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Creates a proxy for ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>direction</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>connection timing</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getCreateProxyMethodName(intf));
        out.write("() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn new ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyAdapterName(intf));
        out.write("(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(") {");  //$NON-NLS-1$        
        out.write(NL);         
      
        //non-void methods to implement
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t/* (non-Javadoc)");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t * @see ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t//TODO: MyxCodeGen:implement code here");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\treturn ");  //$NON-NLS-1$        
        out.write(method.getDefaultReturnValue());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of for( MethodContainer method

        out.write("\t\t};");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         

	} // end of proxy variables

        out.write(NL);         
  // implemented methods for in
	for(CodegenBrickInterface intf : CompTemplateUtil.getImplMethodIn(brick)) {
		//voidMethods
		for( MethodContainer method : intf.getJavaInterfaceVoidMethods()){

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Implementation of ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>direction</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>connection timing</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//TODO: MyxCodeGen:implement code here");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of voidMethods
		//nonVoidMethods
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Implementation of ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>direction</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>connection timing</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//TODO: MyxCodeGen:implement code here");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(method.getDefaultReturnValue());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of voidMethods
	} // end of implemented methods for in
	

  // implemented methods for out
	for(CodegenBrickInterface intf : CompTemplateUtil.getImplMethodOut(brick)) {
		//nonVoidMethods
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Implementation of ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>direction</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>connection timing</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getConnectionTiming().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(intf.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//TODO: MyxCodeGen:implement code here");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(method.getDefaultReturnValue());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of voidMethods
	} // end of implemented methods for out

 // in brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getInMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of in brickInterface variables

 // out-single brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutSingleMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected void ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getSetterMethodName( intf.getName()));
        out.write("(");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(") {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tthis.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of out-single brickInterface variables

 // out-multi brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutMultiMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected Collection<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("> ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn this.");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
 
   }// end of out-multi brickInterface variables 

 // proxy variables
   for(CodegenBrickInterface intf : brick.getOutInterfaces()) {
    if(CompTemplateUtil.isProxyVariable(intf)) {

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getGetterMethodName(CompTemplateUtil.getProxyVariableName(intf)));
        out.write("() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(CompTemplateUtil.getProxyVariableName(intf));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  }
   }// end of proxy variables

	//methods that are not implemented in the parent
	for(MethodContainer method : brick.getUnimplMethods()) {

        out.write("\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//TODO: MyxCodeGen: implement the method declared in the parent brick");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(method.getDefaultReturnValue());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         

	}

        out.write(NL);         
        out.write("}");  //$NON-NLS-1$        
    }
}
