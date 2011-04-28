import java.util.*;
import org.archstudio.myxgen.jet.util.*;
import org.archstudio.myxgen.jet.brick.*;
import org.archstudio.myxgen.jet.codegen.CompTemplateUtil;
import org.archstudio.myxgen.jet.codegen.MyxCodegenConstants;

public class CLASS
{
  protected static String nl;
  public static synchronized CLASS create(String lineSeparator)
  {
    nl = lineSeparator;
    CLASS result = new CLASS();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<java:merge/>";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + "import ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + NL + "/**" + NL + " * Myx brick component." + NL + " * ";
  protected final String TEXT_8 = NL + " * Following methods are called automatically. They should be overrided as necessary." + NL + " * <ul>" + NL + " * \t<li> init(): this brick is created</li>" + NL + " *\t<li> begin(): this brick is attached to others via links.</li>" + NL + " *\t<li> end(): this brick is detached.</li>" + NL + " *\t<li> destroy(): this brick is destroyed. </li>" + NL + " * </ul>" + NL + " * The brick interface service object variable(s):" + NL + " *\t<table border=\"1\">" + NL + " *\t<tr>" + NL + " *\t\t<th>JavaInterface</th><th>service object</th>" + NL + " *\t\t<th>brick interface</th><th>direction</th><th>connection timing</th><th>template type</th>" + NL + " *\t</tr>";
  protected final String TEXT_9 = NL + " *\t<tr>" + NL + " *\t\t<td>";
  protected final String TEXT_10 = "</td><td><code>";
  protected final String TEXT_11 = "</code></td>" + NL + " *\t\t<td><code>";
  protected final String TEXT_12 = "</code></td><td>";
  protected final String TEXT_13 = "</td>" + NL + " *\t\t<td>";
  protected final String TEXT_14 = "</td><td>";
  protected final String TEXT_15 = "</td>" + NL + " *\t</tr>";
  protected final String TEXT_16 = NL + " *\t<tr>" + NL + " *\t\t<td>";
  protected final String TEXT_17 = "</td><td>this</td>" + NL + " *\t\t<td><code>";
  protected final String TEXT_18 = "</code></td><td>";
  protected final String TEXT_19 = "</td>" + NL + " *\t\t<td>";
  protected final String TEXT_20 = "</td><td>";
  protected final String TEXT_21 = "</td>" + NL + " *\t</tr>";
  protected final String TEXT_22 = NL + " *\t<tr>" + NL + " *\t\t<td>";
  protected final String TEXT_23 = "</td><td><code>";
  protected final String TEXT_24 = "</code></td>" + NL + " *\t\t<td><code>";
  protected final String TEXT_25 = "</code></td><td>";
  protected final String TEXT_26 = "</td>" + NL + " *\t\t<td>";
  protected final String TEXT_27 = "</td><td>";
  protected final String TEXT_28 = "</td>" + NL + " *\t</tr>";
  protected final String TEXT_29 = NL + " *\t<tr>" + NL + " *\t\t<td>";
  protected final String TEXT_30 = "</td><td><code>";
  protected final String TEXT_31 = "</code> as a proxy for <code>";
  protected final String TEXT_32 = "</code></td>" + NL + " *\t</tr>";
  protected final String TEXT_33 = NL + " *\t</table>" + NL + " * <p>" + NL + " * In order to prevent the myx code generator from overwriting the content of method, " + NL + " * remove \"@generated\" annotation from java doc comment before re-run the generation" + NL + " * if any change is made to the method." + NL + " * </p>" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_34 = NL + "    extends ";
  protected final String TEXT_35 = " {" + NL + "    ";
  protected final String TEXT_36 = NL + NL + "\t/**" + NL + "\t * the variable for \"";
  protected final String TEXT_37 = "\" brickInterface. ";
  protected final String TEXT_38 = NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_39 = " ";
  protected final String TEXT_40 = " = null;";
  protected final String TEXT_41 = NL + NL + "\t/**" + NL + "\t * the variable for \"";
  protected final String TEXT_42 = "\" brickInterface. ";
  protected final String TEXT_43 = NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_44 = " ";
  protected final String TEXT_45 = " = null;";
  protected final String TEXT_46 = " ";
  protected final String TEXT_47 = NL + NL + "\t/**" + NL + "\t * the variable for \"";
  protected final String TEXT_48 = "\" brickInterface. ";
  protected final String TEXT_49 = NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate Collection<";
  protected final String TEXT_50 = "> ";
  protected final String TEXT_51 = " = new ArrayList<";
  protected final String TEXT_52 = ">();";
  protected final String TEXT_53 = NL + NL + "\t/**" + NL + "\t * the ";
  protected final String TEXT_54 = " proxy for \"";
  protected final String TEXT_55 = "\". ";
  protected final String TEXT_56 = NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_57 = " ";
  protected final String TEXT_58 = " = ";
  protected final String TEXT_59 = "();" + NL + "\t";
  protected final String TEXT_60 = NL + "    " + NL + "    /**" + NL + "\t *   ";
  protected final String TEXT_61 = NL + "\t * The following variable(s) must be assigned values in the constructor.";
  protected final String TEXT_62 = NL + "\t * <ul>\t\t\t";
  protected final String TEXT_63 = NL + "\t *  <li>{@link #";
  protected final String TEXT_64 = "}</li>";
  protected final String TEXT_65 = NL + "\t * </ul>\t";
  protected final String TEXT_66 = NL + "     * @generated" + NL + "     */";
  protected final String TEXT_67 = NL + "\t";
  protected final String TEXT_68 = " ";
  protected final String TEXT_69 = "(";
  protected final String TEXT_70 = ") {" + NL + "\t\t" + NL + "\t\tsuper(";
  protected final String TEXT_71 = ");" + NL + "\t\t";
  protected final String TEXT_72 = NL + "\t\t//TODO: assign a value to this.";
  protected final String TEXT_73 = NL + "\t}";
  protected final String TEXT_74 = NL + "\tpublic ";
  protected final String TEXT_75 = "(){" + NL + "\t";
  protected final String TEXT_76 = NL + "\t\t//TODO: assign a value to this.";
  protected final String TEXT_77 = NL + "\t}" + NL;
  protected final String TEXT_78 = NL + NL + "   \t/**" + NL + "\t *";
  protected final String TEXT_79 = NL + "\t * The following variable(s) will be assigned values before this method is called.";
  protected final String TEXT_80 = NL + "\t * <ul>\t\t\t";
  protected final String TEXT_81 = NL + "\t *  <li>{@link #";
  protected final String TEXT_82 = "}</li>";
  protected final String TEXT_83 = NL + "\t *\t<li>{@link #";
  protected final String TEXT_84 = "} for a proxy of {@link #";
  protected final String TEXT_85 = "}</li>";
  protected final String TEXT_86 = NL + "\t * </ul>\t";
  protected final String TEXT_87 = NL + "\t * The following variable(s) must be assigned values before this method exits.";
  protected final String TEXT_88 = NL + "\t * <ul>";
  protected final String TEXT_89 = NL + "\t *  <li>{@link #";
  protected final String TEXT_90 = "}</li>";
  protected final String TEXT_91 = NL + "\t * </ul>\t";
  protected final String TEXT_92 = NL + "\t * @see edu.uci.isr.myx.fw.AbstractMyxSimpleBrick#init()" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void init() {\t" + NL + "\t\tsuper.init();";
  protected final String TEXT_93 = NL + "\t \t//TODO: assign a value to this.";
  protected final String TEXT_94 = NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @see edu.uci.isr.myx.fw.AbstractMyxSimpleBrick#begin()" + NL + "\t */" + NL + "\t @Override" + NL + "\tpublic void begin() {" + NL + "\t\tsuper.begin();" + NL + "\t\t" + NL + "\t\t//XXX: all the variables will be available at this point." + NL + "\t}" + NL + "\t";
  protected final String TEXT_95 = NL + NL + "\t/* (non-Javadoc)" + NL + "\t *" + NL + "\t */" + NL + "\t/**" + NL + "\t * Creates a proxy for ";
  protected final String TEXT_96 = "." + NL + "\t *\t<table border=\"1\">" + NL + "\t *\t<tr>" + NL + "\t *\t\t<th>direction</th>" + NL + "\t *\t\t<th>connection timing</th>" + NL + "\t *\t\t<th>template type</th>" + NL + "\t *\t</tr>" + NL + "\t *\t<tr>" + NL + "\t * \t\t<td>";
  protected final String TEXT_97 = "</td>" + NL + "\t *\t\t<td>";
  protected final String TEXT_98 = "</td>" + NL + "\t * \t\t<td>";
  protected final String TEXT_99 = "</td>" + NL + "\t *\t</tr>" + NL + "\t *\t</table>" + NL + "\t * " + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected ";
  protected final String TEXT_100 = " ";
  protected final String TEXT_101 = "() {" + NL + "\t\t\t\t\t" + NL + "\t\treturn new ";
  protected final String TEXT_102 = "(";
  protected final String TEXT_103 = ") {";
  protected final String TEXT_104 = "\t\t\t" + NL + "\t\t\t" + NL + "\t\t\t/* (non-Javadoc)" + NL + "\t\t\t * @generated" + NL + "\t\t\t * @see ";
  protected final String TEXT_105 = "#";
  protected final String TEXT_106 = "(";
  protected final String TEXT_107 = ")" + NL + "\t\t\t */" + NL + "\t\t\t";
  protected final String TEXT_108 = "{" + NL + "\t\t\t\t//TODO: MyxCodeGen:implement code here" + NL + "\t\t\t\treturn ";
  protected final String TEXT_109 = ";" + NL + "\t\t\t}";
  protected final String TEXT_110 = NL + "\t\t};" + NL + "\t}";
  protected final String TEXT_111 = NL;
  protected final String TEXT_112 = "\t\t\t" + NL + "\t" + NL + "\t/**" + NL + "\t * Implementation of ";
  protected final String TEXT_113 = "." + NL + "\t *\t<table border=\"1\">" + NL + "\t *\t<tr>" + NL + "\t *\t\t<th>direction</th>" + NL + "\t *\t\t<th>connection timing</th>" + NL + "\t *\t\t<th>template type</th>" + NL + "\t *\t</tr>" + NL + "\t *\t<tr>" + NL + "\t * \t\t<td>";
  protected final String TEXT_114 = "</td>" + NL + "\t *\t\t<td>";
  protected final String TEXT_115 = "</td>" + NL + "\t * \t\t<td>";
  protected final String TEXT_116 = "</td>" + NL + "\t *\t</tr>" + NL + "\t *\t</table>" + NL + "\t * @see ";
  protected final String TEXT_117 = "#";
  protected final String TEXT_118 = "(";
  protected final String TEXT_119 = ")" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_120 = "{" + NL + "\t" + NL + "\t\t//TODO: MyxCodeGen:implement code here" + NL + "" + NL + "\t}";
  protected final String TEXT_121 = "\t\t\t" + NL + "\t" + NL + "\t/**" + NL + "\t * Implementation of ";
  protected final String TEXT_122 = "." + NL + "\t *\t<table border=\"1\">" + NL + "\t *\t<tr>" + NL + "\t *\t\t<th>direction</th>" + NL + "\t *\t\t<th>connection timing</th>" + NL + "\t *\t\t<th>template type</th>" + NL + "\t *\t</tr>" + NL + "\t *\t<tr>" + NL + "\t * \t\t<td>";
  protected final String TEXT_123 = "</td>" + NL + "\t *\t\t<td>";
  protected final String TEXT_124 = "</td>" + NL + "\t * \t\t<td>";
  protected final String TEXT_125 = "</td>" + NL + "\t *\t</tr>" + NL + "\t *\t</table>" + NL + "\t * @see ";
  protected final String TEXT_126 = "#";
  protected final String TEXT_127 = "(";
  protected final String TEXT_128 = ")" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_129 = "{" + NL + "\t" + NL + "\t\t//TODO: MyxCodeGen:implement code here" + NL + "\t\treturn ";
  protected final String TEXT_130 = ";" + NL + "\t}";
  protected final String TEXT_131 = "\t\t\t" + NL + "\t" + NL + "\t/**" + NL + "\t * Implementation of ";
  protected final String TEXT_132 = "." + NL + "\t *\t<table border=\"1\">" + NL + "\t *\t<tr>" + NL + "\t *\t\t<th>direction</th>" + NL + "\t *\t\t<th>connection timing</th>" + NL + "\t *\t\t<th>template type</th>" + NL + "\t *\t</tr>" + NL + "\t *\t<tr>" + NL + "\t * \t\t<td>";
  protected final String TEXT_133 = "</td>" + NL + "\t *\t\t<td>";
  protected final String TEXT_134 = "</td>" + NL + "\t * \t\t<td>";
  protected final String TEXT_135 = "</td>" + NL + "\t *\t</tr>" + NL + "\t *\t</table>" + NL + "\t * @see ";
  protected final String TEXT_136 = "#";
  protected final String TEXT_137 = "(";
  protected final String TEXT_138 = ")" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_139 = "{" + NL + "\t" + NL + "\t\t//TODO: MyxCodeGen:implement code here" + NL + "\t\treturn ";
  protected final String TEXT_140 = ";" + NL + "\t}";
  protected final String TEXT_141 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected ";
  protected final String TEXT_142 = " ";
  protected final String TEXT_143 = "() {" + NL + "\t\treturn this.";
  protected final String TEXT_144 = ";" + NL + "\t}";
  protected final String TEXT_145 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected ";
  protected final String TEXT_146 = " ";
  protected final String TEXT_147 = "() {" + NL + "\t\treturn this.";
  protected final String TEXT_148 = ";" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected void ";
  protected final String TEXT_149 = "(";
  protected final String TEXT_150 = " ";
  protected final String TEXT_151 = ") {" + NL + "\t\tthis.";
  protected final String TEXT_152 = " = ";
  protected final String TEXT_153 = ";" + NL + "\t}";
  protected final String TEXT_154 = " ";
  protected final String TEXT_155 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected Collection<";
  protected final String TEXT_156 = "> ";
  protected final String TEXT_157 = "() {" + NL + "\t\treturn this.";
  protected final String TEXT_158 = ";" + NL + "\t}" + NL;
  protected final String TEXT_159 = NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected ";
  protected final String TEXT_160 = " ";
  protected final String TEXT_161 = "() {" + NL + "\t\treturn ";
  protected final String TEXT_162 = ";" + NL + "\t}" + NL + "\t\t" + NL + "\t";
  protected final String TEXT_163 = NL + "\t";
  protected final String TEXT_164 = "{" + NL + "\t" + NL + "\t\t//TODO: MyxCodeGen: implement the method declared in the parent brick" + NL + "\t\treturn ";
  protected final String TEXT_165 = ";" + NL + "\t}";
  protected final String TEXT_166 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
     
	//CodegenBrick brick = (CodegenBrick) argument; 
	CodegenBrick brick = (CodegenBrick)context.getVariable(MyxCodegenConstants.JET2_TEMPLATE_VARIABLE_NAME_BRICK);
	String packageName = TextUtil.getPackagePart(brick.getFQDefaultImplClassName());
	String className = TextUtil.getClassPart(brick.getFQDefaultImplClassName());
	String baseClassName = TextUtil.getClassPart(brick.getFQBaseClassName());

     if (packageName != null && packageName.length() > 0) { 

    stringBuffer.append(TEXT_2);
    stringBuffer.append(packageName);
    stringBuffer.append(TEXT_3);
     }

    stringBuffer.append(TEXT_4);
     for(String javaImport : CompTemplateUtil.getImports(brick)){

    stringBuffer.append(TEXT_5);
    stringBuffer.append(javaImport);
    stringBuffer.append(TEXT_6);
     }

    stringBuffer.append(TEXT_7);
    stringBuffer.append(CompTemplateUtil.getComment(brick.getDescription(), 0));
    stringBuffer.append(TEXT_8);
       //in brickInterfaces
	 List<CodegenBrickInterface> inIntfs = new ArrayList<CodegenBrickInterface>();
	 inIntfs.addAll(brick.getInInterfaces()); // of this brick
	 inIntfs.addAll(brick.getAncesotrInInterfaces());// of ancestors' bricks
     for(CodegenBrickInterface intf : inIntfs) {

    		//variables
		if(CompTemplateUtil.isMyxVariable(intf) || CompTemplateUtil.isMyxVariablInBaseClass(intf)) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(CompTemplateUtil.getVariableType(intf));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_15);
    		}
		//implemented
		else if (CompTemplateUtil.isImplementedInClass(intf)) {

    stringBuffer.append(TEXT_16);
    stringBuffer.append(CompTemplateUtil.getVariableType(intf));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_21);
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

    stringBuffer.append(TEXT_22);
    stringBuffer.append(CompTemplateUtil.getVariableType(intf));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_28);
    	//proxy variables
    if(CompTemplateUtil.isProxyVariable(intf) || CompTemplateUtil.isProxyVariableInBaseClass(intf)) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(CompTemplateUtil.getProxyVariableType(intf));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(CompTemplateUtil.getProxyVariableName(intf));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_32);
      }// end of proxy variables
   }// end of out brickInterfaces

    stringBuffer.append(TEXT_33);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(baseClassName);
    stringBuffer.append(TEXT_35);
     // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getInMyxVariables(brick)) {

    stringBuffer.append(TEXT_36);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(CompTemplateUtil.getComment(intf.getDescription(), 1));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_40);
     
   }// end of for(CodegenBrickInterface intf : inSigs) 

     // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutSingleMyxVariables(brick)) {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(CompTemplateUtil.getComment(intf.getDescription(), 1));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_45);
     
   }// end of for(CodegenBrickInterface intf : inSigs) 

    stringBuffer.append(TEXT_46);
     // brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutMultiMyxVariables(brick)) {

    stringBuffer.append(TEXT_47);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(CompTemplateUtil.getComment(intf.getDescription(), 1));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_52);
     
   }// end of for(CodegenBrickInterface intf : inSigs) 

     // proxy variables
   for(CodegenBrickInterface intf : brick.getOutInterfaces()) {
    if(CompTemplateUtil.isProxyVariable(intf)) {

    stringBuffer.append(TEXT_53);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(CompTemplateUtil.getComment(intf.getDescription(), 1));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(CompTemplateUtil.getProxyVariableName(intf));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(CompTemplateUtil.getCreateProxyMethodName(intf));
    stringBuffer.append(TEXT_59);
      }
   }// end of proxy variables

    stringBuffer.append(TEXT_60);
      //variables that must be assigned values in the constructor
    Collection<CodegenBrickInterface> intfsForConstructor = CompTemplateUtil.getConstructorVariables(brick);
    if(!intfsForConstructor.isEmpty() ) {

    stringBuffer.append(TEXT_61);
     // connectBeforeInit (for the client) 
    stringBuffer.append(TEXT_62);
    		for(CodegenBrickInterface _intf : intfsForConstructor) {

    stringBuffer.append(TEXT_63);
    stringBuffer.append(_intf.getName());
    stringBuffer.append(TEXT_64);
    		} // end of for(CodegenBrickInterface _

    stringBuffer.append(TEXT_65);
      } // end of if(!intfsForConstructor.isEmpty() 

    stringBuffer.append(TEXT_66);
      // constructors when parent is specified
	if( brick.hasParentBrick() ) {
		//constructor from ancestors
		for(MethodContainer constructor : CompTemplateUtil.getConstructors(brick)) {

    stringBuffer.append(TEXT_67);
    stringBuffer.append(constructor.getModifiers());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(constructor.toParamsSimpleString());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(constructor.toParamNamesString());
    stringBuffer.append(TEXT_71);
    		// variables need to be assigned in the constructor
		for(CodegenBrickInterface intf : CompTemplateUtil.getConstructorVariables(brick)) {

    stringBuffer.append(TEXT_72);
    stringBuffer.append(intf.getName());
    		}//end of variables need to be assigned in the constructor

    stringBuffer.append(TEXT_73);
    		}//end of constructors from ancestors
	}// end of constructors when parent is specified
	else {
	// the constructor when parent is not specified

    stringBuffer.append(TEXT_74);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_75);
    		// variables need to be assigned in the constructor
		for(CodegenBrickInterface intf : CompTemplateUtil.getConstructorVariables(brick)) {

    stringBuffer.append(TEXT_76);
    stringBuffer.append(intf.getName());
    		}//end of variables need to be assigned in the constructor

    stringBuffer.append(TEXT_77);
    	}// end of the constructor when parent is not specified

    stringBuffer.append(TEXT_78);
      //variables that will be avaialble in init()
    Collection<CodegenBrickInterface> intfsForInitAvailable = CompTemplateUtil.getInitAvailableVariables(brick);
    if(!intfsForInitAvailable.isEmpty() ) {

    stringBuffer.append(TEXT_79);
     // connectBeforeInit  

    stringBuffer.append(TEXT_80);
    		for(CodegenBrickInterface intf : intfsForInitAvailable) {

    stringBuffer.append(TEXT_81);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_82);
    			//proxy variables
		    if(CompTemplateUtil.isProxyVariable(intf) || CompTemplateUtil.isProxyVariableInBaseClass(intf)) {

    stringBuffer.append(TEXT_83);
    stringBuffer.append(CompTemplateUtil.getProxyVariableName(intf));
    stringBuffer.append(TEXT_84);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_85);
      		}// end of proxy variables
		} // end of for(CodegenBrickInterface _

    stringBuffer.append(TEXT_86);
      } // end of if(!intfsForInitAvailable.isEmpty() 

    //variables that must be assigned values in init()
    Collection<CodegenBrickInterface> intfsForInitAssign = CompTemplateUtil.getInitAssignVariables(brick);
    if(!intfsForInitAssign.isEmpty() ) {

    stringBuffer.append(TEXT_87);
     // connectBeforeBegin 
    stringBuffer.append(TEXT_88);
    		for(CodegenBrickInterface intf : intfsForInitAssign) {

    stringBuffer.append(TEXT_89);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_90);
    		}

    stringBuffer.append(TEXT_91);
      }// end of if(!intfsForInitAssign.isEmpty() 

    stringBuffer.append(TEXT_92);
      	//variables that must be assigned values in init()
		for(CodegenBrickInterface intf : intfsForInitAssign) {

    stringBuffer.append(TEXT_93);
    stringBuffer.append(intf.getName());
    
		} // end of for(CodegenBrickInterface _

    stringBuffer.append(TEXT_94);
      // proxy variables
	for(CodegenBrickInterface intf : CompTemplateUtil.getCreateProxyMethodVariables(brick)){

    stringBuffer.append(TEXT_95);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(CompTemplateUtil.getCreateProxyMethodName(intf));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(CompTemplateUtil.getProxyAdapterName(intf));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_103);
          
        //non-void methods to implement
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

    stringBuffer.append(TEXT_104);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(method.getMethodName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(method.toParamsSimpleString());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(method.toSimpleString());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(method.getDefaultReturnValue());
    stringBuffer.append(TEXT_109);
    		} // end of for( MethodContainer method

    stringBuffer.append(TEXT_110);
    
	} // end of proxy variables

    stringBuffer.append(TEXT_111);
      // implemented methods for in
	for(CodegenBrickInterface intf : CompTemplateUtil.getImplMethodIn(brick)) {
		//voidMethods
		for( MethodContainer method : intf.getJavaInterfaceVoidMethods()){

    stringBuffer.append(TEXT_112);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(method.getMethodName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(method.toParamsSimpleString());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(method.toSimpleString());
    stringBuffer.append(TEXT_120);
    		} // end of voidMethods
		//nonVoidMethods
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

    stringBuffer.append(TEXT_121);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(method.getMethodName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(method.toParamsSimpleString());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(method.toSimpleString());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(method.getDefaultReturnValue());
    stringBuffer.append(TEXT_130);
    		} // end of voidMethods
	} // end of implemented methods for in
	

      // implemented methods for out
	for(CodegenBrickInterface intf : CompTemplateUtil.getImplMethodOut(brick)) {
		//nonVoidMethods
		for( MethodContainer method : intf.getJavaInterfaceNonVoidMethods()){

    stringBuffer.append(TEXT_131);
    stringBuffer.append(intf.getRawName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(intf.getDirection().getSchemaDescription());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(intf.getConnectionTiming().getSchemaDescription());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(intf.getTemplateType().getSchemaDescription());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(method.getMethodName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(method.toParamsSimpleString());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(method.toSimpleString());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(method.getDefaultReturnValue());
    stringBuffer.append(TEXT_140);
    		} // end of voidMethods
	} // end of implemented methods for out

     // in brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getInMyxVariables(brick)) {

    stringBuffer.append(TEXT_141);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(CompTemplateUtil.getGetterMethodName( intf.getName()));
    stringBuffer.append(TEXT_143);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_144);
     
   }// end of in brickInterface variables

     // out-single brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutSingleMyxVariables(brick)) {

    stringBuffer.append(TEXT_145);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(CompTemplateUtil.getGetterMethodName( intf.getName()));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(CompTemplateUtil.getSetterMethodName( intf.getName()));
    stringBuffer.append(TEXT_149);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_153);
     
   }// end of out-single brickInterface variables

    stringBuffer.append(TEXT_154);
     // out-multi brickInterface variables
   for(CodegenBrickInterface intf : CompTemplateUtil.getOutMultiMyxVariables(brick)) {

    stringBuffer.append(TEXT_155);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(CompTemplateUtil.getGetterMethodName( intf.getName()));
    stringBuffer.append(TEXT_157);
    stringBuffer.append(intf.getName());
    stringBuffer.append(TEXT_158);
     
   }// end of out-multi brickInterface variables 

     // proxy variables
   for(CodegenBrickInterface intf : brick.getOutInterfaces()) {
    if(CompTemplateUtil.isProxyVariable(intf)) {

    stringBuffer.append(TEXT_159);
    stringBuffer.append(intf.getSimpleJavaInterfaceName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(CompTemplateUtil.getGetterMethodName(CompTemplateUtil.getProxyVariableName(intf)));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(CompTemplateUtil.getProxyVariableName(intf));
    stringBuffer.append(TEXT_162);
      }
   }// end of proxy variables

    	//methods that are not implemented in the parent
	for(MethodContainer method : brick.getUnimplMethods()) {

    stringBuffer.append(TEXT_163);
    stringBuffer.append(method.toSimpleString());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(method.getDefaultReturnValue());
    stringBuffer.append(TEXT_165);
    
	}

    stringBuffer.append(TEXT_166);
    return stringBuffer.toString();
  }
}
