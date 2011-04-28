package org.eclipse.jet.compiled;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;
import java.util.*;
import org.archstudio.myxgen.jet.util.*;
import org.archstudio.myxgen.jet.brick.*;
import org.archstudio.myxgen.jet.codegen.BaseTemplateUtil;
import org.archstudio.myxgen.jet.codegen.MyxCodegenConstants;

public class _jet_MyxBasejava implements JET2Template {
    private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$

    public _jet_MyxBasejava() {
        super();
    }

    private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$
    
    private static final TagInfo _td_java_merge_7_1 = new TagInfo("java:merge", //$NON-NLS-1$
            7, 1,
            new String[] {
            },
            new String[] {
            } );

    public void generate(final JET2Context context, final JET2Writer __out) {
        JET2Writer out = __out;
        RuntimeTagElement _jettag_java_merge_7_1 = context.getTagFactory().createRuntimeTag(_jetns_java, "merge", "java:merge", _td_java_merge_7_1); //$NON-NLS-1$ //$NON-NLS-2$
        _jettag_java_merge_7_1.setRuntimeParent(null);
        _jettag_java_merge_7_1.setTagInfo(_td_java_merge_7_1);
        _jettag_java_merge_7_1.doStart(context, out);
        _jettag_java_merge_7_1.doEnd();
        out.write(NL);         

//	CodegenBrick brick = (CodegenBrick) argument;
	CodegenBrick brick = (CodegenBrick)context.getVariable(MyxCodegenConstants.JET2_TEMPLATE_VARIABLE_NAME_BRICK);
	String packageName = TextUtil.getPackagePart(brick.getFQBaseClassName());
	String className = TextUtil.getClassPart(brick.getFQBaseClassName());

 if (packageName != null && packageName.length() > 0) { 

        out.write("package ");  //$NON-NLS-1$        
        out.write(packageName);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
 }

        out.write(NL);         
//java imports
    for( String javaImport : BaseTemplateUtil.getImports(brick)) {

        out.write("import ");  //$NON-NLS-1$        
        out.write(javaImport);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
  }//end of additional java imports

        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * Abstract base class of \"");  //$NON-NLS-1$        
        out.write(brick.getName());
        out.write("\" brick.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(brick.getDescription(), 0));
        out.write(NL);         
        out.write(" * <p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * Following methods are called automatically by the Myx framework. ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * Clients can override them as necessary.");  //$NON-NLS-1$        
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
        out.write(" * </p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * The brick interface service object(s):");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<table border=\"1\">");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<th>JavaInterface</th><th>service object</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<th>brick interface name</th><th>direction</th><th>connection timing</th><th>template type</th>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
   //in brickInterfaces
	 List<CodegenBrickInterface> inIntfs = new ArrayList<CodegenBrickInterface>();
	 inIntfs.addAll(brick.getInInterfaces()); // of this brick
	 inIntfs.addAll(brick.getAncesotrInInterfaces());// of ancestors' brick
     for(CodegenBrickInterface intf : inIntfs) {

		//variables
		if(BaseTemplateUtil.isMyxVariable(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * \t\t<td><code>");  //$NON-NLS-1$        
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
		//getter
		else if(BaseTemplateUtil.isMyxVariableGetter(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(intf.getName()));
        out.write("()</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * \t\t<td><code>");  //$NON-NLS-1$        
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
		else if (BaseTemplateUtil.isImplementedInClass(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getVariableType(intf));
        out.write("</td><td>this</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * \t\t<td><code>");  //$NON-NLS-1$        
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

	//variable
	if(BaseTemplateUtil.isMyxVariable(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getVariableType(intf));
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
    	if(BaseTemplateUtil.isProxyVariable(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyVariableName(intf));
        out.write("<code> as a proxy for ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
  	}// end of proxy variables
	}//end of variable

	//getter
	else if(BaseTemplateUtil.isMyxVariableGetter(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(intf.getName()));
        out.write("()</code></td>");  //$NON-NLS-1$        
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
		//proxy getter
    	if(BaseTemplateUtil.isProxyVariableGetter(intf)) {

        out.write(" *\t<tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t\t<td>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyVariableType(intf));
        out.write("</td><td><code>");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(BaseTemplateUtil.getProxyVariableName(intf)));
        out.write("()</code> as a proxy accessor for <code>");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("</code></td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
  	}// end of proxy getter
	}//end of variable


   }// end of out brickInterfaces

        out.write(" *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * <p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * In order to prevent the myx code generator from overwriting the content of method, ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * remove or change \"@generated\" annotation of java doc comment before re-run the generation.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * </p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("public abstract class ");  //$NON-NLS-1$        
        out.write(className);
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getExtendsString(brick));
        out.write(NL);         
        out.write("    ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getImplementsString(brick));
        out.write(" {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
  // IMyxNames of in-brick interfaces
    for(CodegenBrickInterface intf : brick.getInInterfaces()) {

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Myx-interface name for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".  ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * <p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Through this IMyxName, other bricks can use services provided by ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(" brick interface.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * </p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic static final IMyxName ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getInMyxName(intf));
        out.write(" = MyxUtils.createName(\"");  //$NON-NLS-1$        
        out.write(intf.getId());
        out.write("\");");  //$NON-NLS-1$        
        out.write(NL);         
  }

        out.write(NL);         
  // IMyxNames of out-brick interfaces
    for(CodegenBrickInterface intf : brick.getOutInterfaces()) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Myx-interface name for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".  ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * <p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Through this IMyxName, this brick can use services reached through ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(" brick interface.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * </p>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic static final IMyxName ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(" = MyxUtils.createName(\"");  //$NON-NLS-1$        
        out.write(intf.getId());
        out.write("\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
  }

 // in-brick interface variables
   for(CodegenBrickInterface intf : BaseTemplateUtil.getInMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the service object for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of in-brick interface variables

 // in-brick interface variables getter
   for(CodegenBrickInterface intf : BaseTemplateUtil.getInMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Returns the service object for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(".  ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tabstract protected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
 
   }// end of in-brick interface variables getter

  // out-multi brick interfaces variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  the collection of service objects for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected Collection<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("> ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = new ArrayList<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(">();\t");  //$NON-NLS-1$        
        out.write(NL);         
   } // end of out-multi brick interfaces variables

  // out-multi brick interfaces getter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  Returns the collection of service objects for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(". ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tabstract protected Collection<");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write("> ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
   } // end of out-multi brick interfaces getter

  // out-single brick interfaces variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the service object for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
  }// end of out-single brick interfaces variables

  // out-single brick interfaces getter/setter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Returns the service object for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(". ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tabstract protected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Sets the service object for ");  //$NON-NLS-1$        
        out.write(intf.getDirection().getSchemaDescription());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write(". ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t abstract protected void ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getSetterMethodName( intf.getName()));
        out.write("(");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  }// end of out-single brick interfaces getter/setter

        out.write(NL);         
  // Proxy adapters
	for(Collection<CodegenBrickInterface> intfs : BaseTemplateUtil.getProxyAdapters(brick)) {
		
		//a representative brick interface 
		Iterator<CodegenBrickInterface> it = intfs.iterator();
		CodegenBrickInterface repSig = it.next();

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Proxy adapter class of ");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write(" interface.");  //$NON-NLS-1$        
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
        out.write(repSig.getDirection().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t\t<td>");  //$NON-NLS-1$        
        out.write(repSig.getConnectionTiming().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * \t\t<td>");  //$NON-NLS-1$        
        out.write(repSig.getTemplateType().getSchemaDescription());
        out.write("</td>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</tr>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *\t</table>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * This class plays a proxy role for a collection of service objects that");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * implement the interface. ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected abstract class ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyAdapterName(repSig));
        out.write(NL);         
        out.write("\t\t\t\t\t");  //$NON-NLS-1$        
        out.write((repSig.isJavaInterface())? "implements" : "extends" );
        out.write(" ");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write(" {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * A collection of service objects whose void methods will be invoked through this class");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tprotected Collection<");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write("> serviceObjects;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * Constructs a proxy class with the given collection of service objects.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyAdapterName(repSig));
        out.write("(Collection<");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write("> serviceObjects) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tthis.serviceObjects = serviceObjects;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
      //common void methods
		for( MethodContainer method : repSig.getJavaInterfaceVoidMethods()){

        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/* (non-Javadoc)");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * @see ");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tfor (");  //$NON-NLS-1$        
        out.write(repSig.getSimpleJavaInterfaceName());
        out.write(" o : serviceObjects ) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\to.");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
		} // end of common void methods

        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		//proxy variables
        for(CodegenBrickInterface intf : intfs) {

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Creates a ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" proxy for <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * Non-void methods of the interface should be inplemented in the extended class.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected abstract ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getCreateProxyMethodName(intf));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
			if(BaseTemplateUtil.isProxyVariable(intf)) {
			//variable

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * the ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" proxy for <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyVariableName(intf));
        out.write(" = ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getCreateProxyMethodName(intf));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t//new ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyAdapterName(intf));
        out.write("(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
			} else {
			//setter/getter

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Gets the ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" proxy for <code>");  //$NON-NLS-1$        
        out.write(intf.getRawName());
        out.write("</code>. ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getComment(intf.getDescription(), 1));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tabstract protected ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(BaseTemplateUtil.getProxyVariableName(intf)));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
			}
		} // end of proxy variables
	} // end of Proxy adapters

        out.write(NL);         
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tclass PreMyxLifecycleProcessor extends MyxLifecycleAdapter {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tpublic void init() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// connectBeforeInit");  //$NON-NLS-1$        
        out.write(NL);         
 	  // preMyxLifecycleInit variables
	for(CodegenBrickInterface intf : BaseTemplateUtil.getPreMyxLifecycleInitVariables(brick)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" must be assigned a value (i.e., the interface must be connected) before calling init()\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
		//proxy variable
        if(BaseTemplateUtil.isProxyVariable(intf)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(BaseTemplateUtil.getProxyVariableName(intf)));
        out.write("() == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getSetterMethodName(BaseTemplateUtil.getProxyVariableName(intf)));
        out.write("(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getCreateProxyMethodName(intf));
        out.write("());");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
	  	 } // end of proxy variable
	}// end of preMyxLifecycleInit variables
	
	// preMyxLifecycleInit variables getter
	for(CodegenBrickInterface intf : BaseTemplateUtil.getPreMyxLifecycleInitVariablesGetter(brick)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write(" == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write(" must return non-null value \" ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t+ \"(i.e., the interface must be connected before) calling init()\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         

	}// end of preMyxLifecycleInit variables getter

        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tpublic void begin() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// connectBeforeBegin ");  //$NON-NLS-1$        
        out.write(NL);         
	 //preMyxLifecycleBegin variables
     for(CodegenBrickInterface intf : BaseTemplateUtil.getPreMyxLifecycleBeginVariables(brick)) {

        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" must be assigned a value before calling begin()\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
		
	}// end of preMyxLifecycleBegin variables
	
	//preMyxLifecycleBegin getters 
	for(CodegenBrickInterface intf : BaseTemplateUtil.getPreMyxLifecycleBeginVariablesGetter(brick)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new NullPointerException(\"");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() service object is null.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
	 }// end of preMyxLifecycleBegin getter

        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  // constructors when parent is specified
	if( brick.hasParentBrick() ) {
		for(MethodContainer constructor : BaseTemplateUtil.getConstructors(brick)) {

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
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
        out.write("\t\taddPreMyxLifecycleProcessor(new PreMyxLifecycleProcessor());");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		}
	}// end of constructors when parent is specified
	else {

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic ");  //$NON-NLS-1$        
        out.write(className);
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tmyxLifecycleProcessors.add(this);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\taddPreMyxLifecycleProcessor(new PreMyxLifecycleProcessor());");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
	}

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
  // implemented methods of out brick interfaces
    for(CodegenBrickInterface repSig : BaseTemplateUtil.getImplMethodOut(brick)) {
    	//methods
	  	for(MethodContainer method : repSig.getJavaInterfaceVoidMethods()) {

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/* (non-Javadoc)");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see ");  //$NON-NLS-1$        
        out.write(repSig.getFQJavaInterfaceName());
        out.write("#");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamsSimpleString());
        out.write(")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(method.toSimpleString());
        out.write("{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
			//checks all the out brick interfaces
			for(CodegenBrickInterface intf : brick.getOutInterfaces()) {

				//multi out variable
				if(BaseTemplateUtil.isOutMultiMyxVariable(intf)) {

        out.write("\t\t//multiple out");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tfor(");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" o: ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\to.");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
				//multi out getter
				} else if(BaseTemplateUtil.isOutMultiMyxVariableGetter(intf)) {

        out.write("\t\t//multiple out getter");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tfor(");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" o: ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("()){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\to.");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
				//out single variable
				}else if(BaseTemplateUtil.isOutSingleMyxVariable(intf)) {

        out.write("\t\t//single out");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
				//out single getter
				} else if(BaseTemplateUtil.isOutSingleMyxVariableGetter(intf)) {

        out.write("\t\t//single out getter");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("().");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
				}//end of getter
			}// end of checks all the out brick interfaces

        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
		}//methods
    }// end of implemented methods of out brick interfaces

        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic synchronized void interfaceConnected(IMyxName interfaceName, Object serviceObject){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
  //out-multi variables getter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("().contains(serviceObject)) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"Interface ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("  already connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t} ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// adds the serviceObject to ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("().add((");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(")serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of out-multi variables

  //out-multi variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".contains(serviceObject)) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"Interface ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" already connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t} ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// adds the serviceObject to ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("  ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".add((");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(")serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of out-multi variables

  // out-single variables getter/setter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() != null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" is already connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// sets the serviceObject to ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getSetterMethodName( intf.getName()));
        out.write("((");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(")serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
   } // end of out-single variables getter/setter

  // out-single variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" != null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" is already connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// sets the serviceObject to ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = (");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(")serviceObject;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
   } // end of out-single variables

        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.interfaceConnected(interfaceName, serviceObject);" : "");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic synchronized void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject){");  //$NON-NLS-1$        
        out.write(NL);         
  //out-multi variables getter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(!");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("().contains(serviceObject)) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"Interface ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" was not previously connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t} ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// removes the serviceObject");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("().remove(serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of out-multi variables getter

  //out-multi variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutMultiMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t\t\tif(!");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".contains(serviceObject)) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"Interface ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" was not previously connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t} ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// removes the serviceObject");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(".remove(serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of out-multi variables

  // out-single variables getter/setter
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariablesGetter(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" was not previously connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// removes the serviceObject");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getSetterMethodName(intf.getName()));
        out.write("(null);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
 }// end of out-single variables getter/setter

  // out-single variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getOutSingleMyxVariables(brick)) {

        out.write(NL);         
        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getOutMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new IllegalArgumentException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" was not previously connected.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t// removes the serviceObject");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
 }// end of out-single variables

        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.interfaceDisconnecting(interfaceName, serviceObject);" : "");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void interfaceDisconnected(IMyxName interfaceName, Object serviceObject){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.interfaceDisconnected(interfaceName, serviceObject);" : "");
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
        out.write("\tpublic Object getServiceObject(IMyxName interfaceName){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
  //getServiceObject variables
    for(CodegenBrickInterface intf : BaseTemplateUtil.getGetServiceObjectVariables(brick)) {

        out.write("\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getInMyxName(intf));
        out.write(".equals(interfaceName)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
      //in variable
        if(BaseTemplateUtil.isInMyxVariable(intf)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new NullPointerException(\"");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(" service object is null.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn ");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
		//in variable getter
		} else if (BaseTemplateUtil.isInMyxVariableGetter(intf)) {

        out.write("\t\t\tif(");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() == null) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tthrow new NullPointerException(\"");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("() service object is null.\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\treturn ");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName( intf.getName()));
        out.write("();");  //$NON-NLS-1$        
        out.write(NL);         
      } else {
		// we will return this object

        out.write("\t\t\treturn this;");  //$NON-NLS-1$        
        out.write(NL);         
      } 

        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of getServiceObject variables

        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "return super.getServiceObject(interfaceName);" : "return null;");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
if(BaseTemplateUtil.shouldCreateMyxRegistry(brick)) {
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * The myx registry, which contains all objects interested in events from this brick.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tMyxRegistry myxr = MyxRegistry.getSharedInstance();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         

	for(CodegenBrickInterface intf : BaseTemplateUtil.getImplMethodDelegateToMyxRegistry(brick)) {
		// only handle void methods
		for( MethodContainer method : intf.getJavaInterfaceVoidMethods()) {

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
        out.write("\t\tfor(");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(" o: myxr.getObjects(this, ");  //$NON-NLS-1$        
        out.write(intf.getSimpleJavaInterfaceName());
        out.write(".class)){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\ttry{");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\to.");  //$NON-NLS-1$        
        out.write(method.getMethodName());
        out.write("(");  //$NON-NLS-1$        
        out.write(method.toParamNamesString());
        out.write(");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tcatch(Throwable t){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t// TODO: Handle exceptions better.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tt.printStackTrace();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         

		}
	}
}

        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void begin() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.begin();" : "");
        out.write(NL);         
if(BaseTemplateUtil.shouldCreateMyxRegistry(brick)){
        out.write("\t\t\tmyxr.register(this);");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void destroy() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.destroy();" : "");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void end() {");  //$NON-NLS-1$        
        out.write(NL);         
if(BaseTemplateUtil.shouldCreateMyxRegistry(brick)){
        out.write("\t\t\tmyxr.unregister(this);");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.end();" : "");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *   ");  //$NON-NLS-1$        
        out.write(NL);         
  //variables that will be avaialble in init()
    Collection<CodegenBrickInterface> intfsForInitAvailable = BaseTemplateUtil.getInitAvailableVariables(brick);
    if(!intfsForInitAvailable.isEmpty() ) {

        out.write("\t * The following variable(s) will be assigned values before this method is called.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * <ul>\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface intf : intfsForInitAvailable) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
			//proxy variables
		    if(BaseTemplateUtil.isProxyVariable(intf)) {

        out.write("\t *\t<li>{@link #");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getProxyVariableName(intf));
        out.write("} for a proxy of {@link #");  //$NON-NLS-1$        
        out.write(intf.getName());
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
  		}// end of proxy variables
		} // end of for(CodegenBrickInterface _

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of if(!intfsForInitAvailable.isEmpty() 

  //methods that will be avaialble in init()
    intfsForInitAvailable = BaseTemplateUtil.getInitAvailableGetters(brick);
    if(!intfsForInitAvailable.isEmpty() ) {

        out.write("\t * The following method(s) will be assigned values before this method is called.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * <ul>\t\t\t");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface intf : intfsForInitAvailable) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(intf.getName()));
        out.write("}()</li>");  //$NON-NLS-1$        
        out.write(NL);         
			//proxy variables
		    if(BaseTemplateUtil.isProxyVariable(intf)) {

        out.write("\t *\t<li>{@link #");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(BaseTemplateUtil.getProxyVariableName(intf)));
        out.write("}() ");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t for a proxy of {@link #");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(intf.getName()));
        out.write("}</li>");  //$NON-NLS-1$        
        out.write(NL);         
  		}// end of proxy variables
		} // end of for(CodegenBrickInterface _

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  } // end of if(!intfsForInitAvailable.isEmpty() 


    //variables that must be assigned values in init()
    Collection<CodegenBrickInterface> intfsForInitAssign = BaseTemplateUtil.getInitAssignVariables(brick);
    if(!intfsForInitAssign.isEmpty() ) {

        out.write("\t * The following variable(s) must be assigned values before this method exits.");  //$NON-NLS-1$        
        out.write(NL);         
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


    //mthods that must be assigned to return values in init()
    intfsForInitAssign = BaseTemplateUtil.getInitAssignMethods(brick);
    if(!intfsForInitAssign.isEmpty() ) {

        out.write("\t * The following method(s) must be assigned to return values before this method exits.");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * <ul>");  //$NON-NLS-1$        
        out.write(NL);         
		for(CodegenBrickInterface intf : intfsForInitAssign) {

        out.write("\t *  <li>{@link #");  //$NON-NLS-1$        
        out.write(BaseTemplateUtil.getGetterMethodName(intf.getName()));
        out.write("}()</li>");  //$NON-NLS-1$        
        out.write(NL);         
		}

        out.write("\t * </ul>\t");  //$NON-NLS-1$        
        out.write(NL);         
  }// end of if(!intfsForInitAssign.isEmpty() 

        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void init() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write((brick.hasParentBrick())? "super.init();" : "");
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         

	//brick is not extending another brick
	if(!brick.hasParentBrick()) {

        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * A list of lifecycle processors. The order of method calling is");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * <ol>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>PreMyxLifecycleProcessor#init()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>this.init()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>PreMyxLifecycleProcessor#begin()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>this.begin()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>PreMyxLifecycleProcessor#end()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>this.end()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>PreMyxLifecycleProcessor#destroy()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *  <li>this.destroy()</li>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprivate final List<IMyxLifecycleProcessor> myxLifecycleProcessors = new ArrayList<IMyxLifecycleProcessor>();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Adds a preMyxLifecycleProcessor");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @param preMyxLifecycleProcessor");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected void addPreMyxLifecycleProcessor(IMyxLifecycleProcessor preMyxLifecycleProcessor) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t// inserts a preMyxLifecycleProcessor into the head of the list");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tthis.myxLifecycleProcessors.add(0, preMyxLifecycleProcessor);");  //$NON-NLS-1$        
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
        out.write("\tprivate IMyxBrickItems brickItems = null;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic final void setMyxBrickItems(IMyxBrickItems brickItems){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tthis.brickItems = brickItems;");  //$NON-NLS-1$        
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
        out.write("\tpublic final IMyxBrickItems getMyxBrickItems(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn brickItems;");  //$NON-NLS-1$        
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
        out.write("\tpublic final Collection<? extends IMyxLifecycleProcessor> getLifecycleProcessors(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn myxLifecycleProcessors;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic final IMyxProvidedServiceProvider getProvidedServiceProvider(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn this;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         

	}
	//end of brick is not extending another brick

        out.write(NL);         
        out.write("}");  //$NON-NLS-1$        
    }
}
