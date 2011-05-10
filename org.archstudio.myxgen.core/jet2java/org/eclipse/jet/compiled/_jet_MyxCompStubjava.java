package org.eclipse.jet.compiled;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;
import org.archstudio.myx.fw.*;
import org.archstudio.myxgen.extension.*;
import org.archstudio.myxgen.jet.codegen.*;
import org.archstudio.myxgen.jet.util.*;
import org.archstudio.sysutils.*;

public class _jet_MyxCompStubjava implements JET2Template {
    private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$

    public _jet_MyxCompStubjava() {
        super();
    }

    private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$
    
    private static final TagInfo _td_java_merge_6_1 = new TagInfo("java:merge", //$NON-NLS-1$
            6, 1,
            new String[] {
            },
            new String[] {
            } );

    public void generate(final JET2Context context, final JET2Writer __out) {
        JET2Writer out = __out;
        RuntimeTagElement _jettag_java_merge_6_1 = context.getTagFactory().createRuntimeTag(_jetns_java, "merge", "java:merge", _td_java_merge_6_1); //$NON-NLS-1$ //$NON-NLS-2$
        _jettag_java_merge_6_1.setRuntimeParent(null);
        _jettag_java_merge_6_1.setTagInfo(_td_java_merge_6_1);
        _jettag_java_merge_6_1.doStart(context, out);
        _jettag_java_merge_6_1.doEnd();
        out.write(NL);         

	BrickExtension brick = (BrickExtension)context.getVariable(BrickExtension.class.getName());
	String packageName = TextUtil.getPackagePart(brick.getStubClassName());
	String className = TextUtil.getClassPart(brick.getStubClassName());

        out.write("package ");  //$NON-NLS-1$        
        out.write(packageName);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("import java.lang.reflect.*;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("import java.util.*;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("import java.util.concurrent.*;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("import org.archstudio.myx.fw.*;");  //$NON-NLS-1$        
        out.write(NL);         
for(String javaImport : MyxCompStubUtil.getImports(brick)) {
        out.write("import ");  //$NON-NLS-1$        
        out.write(javaImport);
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * Abstract Myx brick: \"");  //$NON-NLS-1$        
        out.write(brick.getName());
        out.write("\"");  //$NON-NLS-1$        
        out.write(NL);         
if(brick.getDescription() != null){
        out.write(" * <p>");  //$NON-NLS-1$        
        out.write(brick.getDescription());
        out.write(NL);         
}
        out.write(" *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("@SuppressWarnings(\"unused\")");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("public abstract class ");  //$NON-NLS-1$        
        out.write(className);
        out.write(NL);         
        out.write("\t");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getExtendsClause(brick));
        out.write(NL);         
        out.write("    ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getImplementsClause(brick));
        out.write(" {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void begin(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tsuper.begin();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tmyxRegistry.register(this);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void end(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tmyxRegistry.unregister(this);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tsuper.end();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
 // ----- constant myx interface name declarations 
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Myx interface ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
if(iface.getDescription() != null){
        out.write("\t * <p>");  //$NON-NLS-1$        
        out.write(iface.getDescription());
        out.write(NL);         
}
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic static final IMyxName ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(" = new MyxBasicName(\"");  //$NON-NLS-1$        
        out.write(iface.getId());
        out.write("\");");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write(NL);         
 // ----- myx service object declarations 
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
switch(iface.getServiceObjectDelegate()){
case variable:
case listener:
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Service object(s) for ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
if(iface.isSingle()){
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\tprotected final Collection<");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write("> ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" = new CopyOnWriteArrayList<");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(">();");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case brick:
if(!iface.isSingle()){
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Add service object from ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected abstract void add");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Remove service object from ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected abstract void remove");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case myxRegistry:
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Service object(s) for ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected final ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" = (");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") Proxy.newProxyInstance(//");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(".class.getClassLoader(),//");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tnew Class[] { ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(".class },//");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tnew InvocationHandler() {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\tpublic Object invoke(Object proxy, Method method, Object[] args) throws Throwable {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\tfor (Object o : myxRegistry.getObjects(");  //$NON-NLS-1$        
        out.write(className);
        out.write(".this)) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\ttry {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t\tif (o instanceof ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t\t\tmethod.invoke(o, args);");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\tcatch (Exception e) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t\te.printStackTrace();");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\treturn null;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t});");  //$NON-NLS-1$        
        out.write(NL);         
break;
}
if(iface.getServiceObjectDelegate()==EServiceObjectDelegate.listener){
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Proxy object for ");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write(": <code>");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tprotected ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("Proxy = MyxCompStubUtil.getListener(iface);");  //$NON-NLS-1$        
        out.write(NL);         
}
}
        out.write(NL);         
 // ----- myx service object getters 
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
if(iface.isGenerateGetter()){
        out.write("\t/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * Returns the service object(s) for <code>");  //$NON-NLS-1$        
        out.write(iface.getName());
        out.write("</code>");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t *");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t * @see #");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(NL);         
        out.write("\t * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t */");  //$NON-NLS-1$        
        out.write(NL);         
switch(iface.getServiceObjectDelegate()){
case variable:
if(iface.isSingle()){
        out.write("\tpublic ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" get");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\tpublic Collection<");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write("> get");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case brick:
if(iface.isSingle()){
        out.write("\tpublic ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" get");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn this;");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case myxRegistry:
if(iface.isSingle()){
        out.write("\tpublic ");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(" get");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("(){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\treturn ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
}
}
}
        out.write(NL);         
 // ----- myx service object and connection handlers 
        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void interfaceConnected(IMyxName interfaceName, Object serviceObject) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tif(serviceObject == null){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tthrow new NullPointerException(interfaceName.getName());");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
if(iface.getDirection() == EMyxInterfaceDirection.OUT){
        out.write("\t\tif(interfaceName.equals(");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(")){");  //$NON-NLS-1$        
        out.write(NL);         
switch(iface.getServiceObjectDelegate()){
case variable:
case listener:
if(iface.isSingle()){
        out.write("\t\t\t\t");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" = (");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") serviceObject;");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\t");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(".add((");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case brick:
if(iface.isSingle()){
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\tadd");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("((");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case myxRegistry:
if(iface.isSingle()){
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\tmyxRegistry.map(this, serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
}
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
}
}
if(brick.getParentBrick() != null){
        out.write("\t\tsuper.interfaceConnected(interfaceName, serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\tif(serviceObject == null){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\tthrow new NullPointerException(interfaceName.getName());");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
if(iface.getDirection() == EMyxInterfaceDirection.OUT){
        out.write("\t\t\tif(interfaceName.equals(");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(")){");  //$NON-NLS-1$        
        out.write(NL);         
switch(iface.getServiceObjectDelegate()){
case variable:
case listener:
if(iface.isSingle()){
        out.write("\t\t\t\t");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" = null;");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\t");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(".remove(serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case brick:
if(iface.isSingle()){
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\tremove");  //$NON-NLS-1$        
        out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
        out.write("((");  //$NON-NLS-1$        
        out.write(iface.getClassName());
        out.write(") serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case myxRegistry:
if(iface.isSingle()){
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\tmyxRegistry.unmap(this, serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
}
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
}
}
if(brick.getParentBrick() != null){
        out.write("\t\tsuper.interfaceDisconnecting(interfaceName, serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {");  //$NON-NLS-1$        
        out.write(NL);         
if(brick.getParentBrick() != null){
        out.write("\t\tsuper.interfaceDisconnected(interfaceName, serviceObject);");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(NL);         
        out.write("/**");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" * @generated");  //$NON-NLS-1$        
        out.write(NL);         
        out.write(" */");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t@Override");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\tpublic Object getServiceObject(IMyxName interfaceName) {");  //$NON-NLS-1$        
        out.write(NL);         
for(InterfaceExtension iface : brick.getInterfaces()) {
if(iface.getDirection() == EMyxInterfaceDirection.IN){
        out.write("\t\t\tif(interfaceName.equals(");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getConstantName(iface));
        out.write(")){");  //$NON-NLS-1$        
        out.write(NL);         
switch(iface.getServiceObjectDelegate()){
case variable:
if(iface.isSingle()){
        out.write("\t\t\t\tif(");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(" == null){");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t\tthrow new NullPointerException(\"");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write("\");");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("\t\t\t\treturn ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case brick:
if(iface.isSingle()){
        out.write("\t\t\t\treturn this;");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
case myxRegistry:
if(iface.isSingle()){
        out.write("\t\t\t\treturn ");  //$NON-NLS-1$        
        out.write(MyxCompStubUtil.getServiceObjectName(iface));
        out.write(";");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\t\t\t// TODO: Not sure what to do here");  //$NON-NLS-1$        
        out.write(NL);         
}
break;
}
        out.write("\t\t}");  //$NON-NLS-1$        
        out.write(NL);         
}
}
if(brick.getParentBrick() != null){
        out.write("\t\treturn super.getServiceObject(interfaceName);");  //$NON-NLS-1$        
        out.write(NL);         
}else{
        out.write("\t\treturn null;");  //$NON-NLS-1$        
        out.write(NL);         
}
        out.write("\t}");  //$NON-NLS-1$        
        out.write(NL);         
        out.write("}");  //$NON-NLS-1$        
    }
}
