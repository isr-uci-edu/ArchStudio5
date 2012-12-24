package org.eclipse.jet.compiled;

import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myxgen.EServiceObjectDelegate;
import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.MyxGenInterface;
import org.archstudio.myxgen.jet.codegen.MyxCompStubUtil;
import org.archstudio.myxgen.jet.util.TextUtil;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;

public class _jet_MyxCompStubjava implements JET2Template {
	private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$

	public _jet_MyxCompStubjava() {
		super();
	}

	private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$

	private static final TagInfo _td_java_merge_6_1 = new TagInfo("java:merge", //$NON-NLS-1$
			6, 1, new String[] {}, new String[] {});

	@Override
	public void generate(final JET2Context context, final JET2Writer __out) {
		JET2Writer out = __out;
		RuntimeTagElement _jettag_java_merge_6_1 = context.getTagFactory().createRuntimeTag(_jetns_java,
				"merge", "java:merge", _td_java_merge_6_1); //$NON-NLS-1$ //$NON-NLS-2$
		_jettag_java_merge_6_1.setRuntimeParent(null);
		_jettag_java_merge_6_1.setTagInfo(_td_java_merge_6_1);
		_jettag_java_merge_6_1.doStart(context, out);
		_jettag_java_merge_6_1.doEnd();
		out.write(NL);

		MyxGenBrick brick = (MyxGenBrick) context.getVariable(MyxGenBrick.class.getName());
		String packageName = TextUtil.getPackagePart(brick.getStubClassName());
		String className = TextUtil.getClassPart(brick.getStubClassName());

		out.write("package "); //$NON-NLS-1$        
		out.write(packageName);
		out.write(";"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		out.write("import java.lang.reflect.*;"); //$NON-NLS-1$        
		out.write(NL);
		out.write("import java.util.*;"); //$NON-NLS-1$        
		out.write(NL);
		out.write("import java.util.concurrent.*;"); //$NON-NLS-1$        
		out.write(NL);
		out.write("import org.archstudio.myx.fw.*;"); //$NON-NLS-1$        
		out.write(NL);
		for (String javaImport : MyxCompStubUtil.getImports(brick)) {
			out.write("import "); //$NON-NLS-1$        
			out.write(javaImport);
			out.write(";"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * Abstract Myx brick: \""); //$NON-NLS-1$        
		out.write(brick.getName());
		out.write("\""); //$NON-NLS-1$        
		out.write(NL);
		if (brick.getDescription() != null) {
			out.write(" * <p>"); //$NON-NLS-1$        
			out.write(brick.getDescription());
			out.write(NL);
		}
		out.write(" *"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("/* package */ abstract class "); //$NON-NLS-1$        
		out.write(className);
		out.write(NL);
		out.write("\t"); //$NON-NLS-1$        
		out.write(MyxCompStubUtil.getExtendsClause(brick));
		out.write(NL);
		out.write("    "); //$NON-NLS-1$        
		out.write(MyxCompStubUtil.getImplementsClause(brick));
		out.write(" {"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		if (brick.getParentBrickId() == null) {
			out.write("\t/**"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t * @generated"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t */"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\tprotected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t/**"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t * @generated"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t */"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\tpublic void begin(){"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t\tsuper.begin();"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t\tmyxRegistry.register(this);"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t}"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t/**"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t * @generated"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t */"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\tpublic void end(){"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t\tmyxRegistry.unregister(this);"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t\tsuper.end();"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t}"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write(NL);
		// ----- constant myx interface name declarations 
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			//EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			out.write("\t/**"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t * Myx name for the <code>"); //$NON-NLS-1$        
			out.write(iface.getName());
			out.write("</code> interface."); //$NON-NLS-1$        
			out.write(NL);
			if (iface.getDescription() != null) {
				out.write("\t * <p>"); //$NON-NLS-1$        
				out.write(iface.getDescription());
				out.write(NL);
			}
			out.write("\t *"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t * @generated"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t */"); //$NON-NLS-1$        
			out.write(NL);
			out.write("\t// "); //$NON-NLS-1$        
			out.write(iface);
			out.write(NL);
			out.write("\tpublic static final IMyxName "); //$NON-NLS-1$        
			out.write(MyxCompStubUtil.getConstantName(iface));
			out.write(" = MyxUtils.createName(\""); //$NON-NLS-1$        
			out.write(iface.getId());
			out.write("\");"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write(NL);
		// ----- myx service object declarations 
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			if (delegate.isNeedsVariable()) {
				out.write("\t/**"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * Service object(s) for the "); //$NON-NLS-1$        
				out.write(iface.getName());
				out.write(" interface."); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t *"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * @see #"); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(NL);
				out.write("\t * @generated"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t */"); //$NON-NLS-1$        
				out.write(NL);
				if (iface.isSingle()) {
					out.write("\tprotected "); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" "); //$NON-NLS-1$        
					out.write(MyxCompStubUtil.getServiceObjectName(iface));
					out.write(" = null;"); //$NON-NLS-1$        
					out.write(NL);
				}
				else {
					out.write("\tprotected final Collection<"); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write("> "); //$NON-NLS-1$        
					out.write(MyxCompStubUtil.getServiceObjectName(iface));
					out.write(NL);
					out.write("\t\t\t= new CopyOnWriteArrayList<"); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(">();"); //$NON-NLS-1$        
					out.write(NL);
				}
			}
			if (delegate.isNeedsProxy()) {
				out.write("\t/**"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * Service object proxy for the "); //$NON-NLS-1$        
				out.write(iface.getName());
				out.write(" interface."); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * Calls to the proxy object are automatically delegated to all service objects of this interface."); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t *"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * @see #"); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(NL);
				out.write("\t * @generated"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t */"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\tprotected final "); //$NON-NLS-1$        
				out.write(iface.getClassName());
				out.write(" "); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getServiceObjectName(iface));
				out.write("Proxy ="); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t("); //$NON-NLS-1$        
				out.write(iface.getClassName());
				out.write(") Proxy.newProxyInstance("); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t"); //$NON-NLS-1$        
				out.write(iface.getClassName());
				out.write(".class.getClassLoader(),"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\tnew Class[] { "); //$NON-NLS-1$        
				out.write(iface.getClassName());
				out.write(".class },"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\tnew InvocationHandler() {"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t@Override"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\tpublic Object invoke(Object proxy, Method method, Object[] args) throws Throwable {"); //$NON-NLS-1$        
				out.write(NL);
				if (delegate == EServiceObjectDelegate.myxRegistry) {
					out.write("\t\t\t\t\t\tfor ("); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" o : myxRegistry.getObjects("); //$NON-NLS-1$        
					out.write(className);
					out.write(".this, "); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(".class)) {"); //$NON-NLS-1$        
					out.write(NL);
				}
				else if (!iface.isSingle()) {
					out.write("\t\t\t\t\t\tfor ("); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" o : "); //$NON-NLS-1$        
					out.write(MyxCompStubUtil.getServiceObjectName(iface));
					out.write(") {"); //$NON-NLS-1$        
					out.write(NL);
				}
				else {
					out.write("\t\t\t\t\t\t"); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" o = "); //$NON-NLS-1$        
					out.write(MyxCompStubUtil.getServiceObjectName(iface));
					out.write(";"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t\t\t\t\t\tif(o != null) {"); //$NON-NLS-1$        
					out.write(NL);
				}
				out.write("\t\t\t\t\t\t\ttry {"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t\t\tmethod.invoke(o, args);"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t\t}"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t\tcatch (Exception e) {"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t\t\te.printStackTrace();"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t\t}"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\t}"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t\treturn null;"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t\t}"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t\t\t\t});"); //$NON-NLS-1$        
				out.write(NL);
			}
		}
		out.write(NL);
		// ----- myx service object getters 
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			if (iface.isGenerateGetter()) {
				out.write("\t/**"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * Returns the service object(s) for the <code>"); //$NON-NLS-1$        
				out.write(iface.getName());
				out.write("</code> interface."); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t *"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t * @see #"); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(NL);
				out.write("\t * @generated"); //$NON-NLS-1$        
				out.write(NL);
				out.write("\t */"); //$NON-NLS-1$        
				out.write(NL);
				switch (delegate) {
				case variable:
				case events:
					if (iface.isSingle()) {
						out.write("\tpublic "); //$NON-NLS-1$        
						out.write(iface.getClassName());
						out.write(" get"); //$NON-NLS-1$        
						out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
						out.write("(){"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\treturn "); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(";"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t}"); //$NON-NLS-1$        
						out.write(NL);
					}
					else {
						out.write("\tpublic Collection<"); //$NON-NLS-1$        
						out.write(iface.getClassName());
						out.write("> get"); //$NON-NLS-1$        
						out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
						out.write("(){"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\treturn "); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(";"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t}"); //$NON-NLS-1$        
						out.write(NL);
						if (delegate.isNeedsProxy()) {
							out.write("\t/**"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\t * Returns the proxy service object for the <code>"); //$NON-NLS-1$        
							out.write(iface.getName());
							out.write("</code> interface."); //$NON-NLS-1$        
							out.write(NL);
							out.write(" \t *"); //$NON-NLS-1$        
							out.write(NL);
							out.write(" \t * @see #"); //$NON-NLS-1$        
							out.write(iface.getClassName());
							out.write(" "); //$NON-NLS-1$        
							out.write(MyxCompStubUtil.getServiceObjectName(iface));
							out.write("Proxy"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\t * @see #"); //$NON-NLS-1$        
							out.write(MyxCompStubUtil.getConstantName(iface));
							out.write(NL);
							out.write("\t * @generated"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\t */"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\tpublic "); //$NON-NLS-1$        
							out.write(iface.getClassName());
							out.write(" get"); //$NON-NLS-1$        
							out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
							out.write("Proxy(){"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\t\treturn "); //$NON-NLS-1$        
							out.write(MyxCompStubUtil.getServiceObjectName(iface));
							out.write("Proxy;"); //$NON-NLS-1$        
							out.write(NL);
							out.write("\t}"); //$NON-NLS-1$        
							out.write(NL);
						}
					}
					break;
				case brick:
					out.write("\tpublic "); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" get"); //$NON-NLS-1$        
					out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
					out.write("(){"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t\treturn this;"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t}"); //$NON-NLS-1$        
					out.write(NL);
					break;
				default: // do nothing
				}
			}
		}
		out.write(NL);
		// ----- myx service object and connection handlers 
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t@Override"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\tpublic void interfaceConnected(IMyxName interfaceName, Object serviceObject) {"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\tif(serviceObject == null){"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\t\tthrow new NullPointerException(interfaceName.getName());"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\t}"); //$NON-NLS-1$        
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			if (iface.getDirection() == EMyxInterfaceDirection.OUT) {
				out.write("\t\tif(interfaceName.equals("); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(")){"); //$NON-NLS-1$        
				out.write(NL);
				switch (delegate) {
				case variable:
				case events:
					if (iface.isSingle()) {
						out.write("\t\t\t\tif("); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(" != null){"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\t\tthrow new IllegalStateException(\"Only a single connection is supported on \"+interfaceName);"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\t}"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\t"); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(" = ("); //$NON-NLS-1$        
						out.write(iface.getClassName());
						out.write(") serviceObject;"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\treturn;"); //$NON-NLS-1$        
						out.write(NL);
					}
					else {
						out.write("\t\t\t\t"); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(".add(("); //$NON-NLS-1$        
						out.write(iface.getClassName());
						out.write(") serviceObject);"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\treturn;"); //$NON-NLS-1$        
						out.write(NL);
					}
					break;
				default: // do nothing
				}
				out.write("\t\t}"); //$NON-NLS-1$        
				out.write(NL);
			}
		}
		if (brick.getParentBrickId() != null) {
			out.write("\t\tsuper.interfaceConnected(interfaceName, serviceObject);"); //$NON-NLS-1$        
			out.write(NL);
		}
		else {
			out.write("\t\tthrow new IllegalArgumentException(\"Unhandled interface connection: \"+interfaceName);"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write("\t}"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t@Override"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\tpublic void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\tif(serviceObject == null){"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\t\tthrow new NullPointerException(interfaceName.getName());"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t\t}"); //$NON-NLS-1$        
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			if (iface.getDirection() == EMyxInterfaceDirection.OUT) {
				out.write("\t\t\tif(interfaceName.equals("); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(")){"); //$NON-NLS-1$        
				out.write(NL);
				switch (delegate) {
				case variable:
				case events:
					if (iface.isSingle()) {
						out.write("\t\t\t\t"); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(" = null;"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\treturn;"); //$NON-NLS-1$        
						out.write(NL);
					}
					else {
						out.write("\t\t\t\t"); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(".remove(serviceObject);"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\treturn;"); //$NON-NLS-1$        
						out.write(NL);
					}
					break;
				default: // do nothing
				}
				out.write("\t\t}"); //$NON-NLS-1$        
				out.write(NL);
			}
		}
		if (brick.getParentBrickId() != null) {
			out.write("\t\tsuper.interfaceDisconnecting(interfaceName, serviceObject);"); //$NON-NLS-1$        
			out.write(NL);
		}
		else {
			out.write("\t\tthrow new IllegalArgumentException(\"Unhandled interface disconnection: \"+interfaceName);"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write("\t}"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t@Override"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\tpublic void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {"); //$NON-NLS-1$        
		out.write(NL);
		if (brick.getParentBrickId() != null) {
			out.write("\t\tsuper.interfaceDisconnected(interfaceName, serviceObject);"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write("\t}"); //$NON-NLS-1$        
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\t@Override"); //$NON-NLS-1$        
		out.write(NL);
		out.write("\tpublic Object getServiceObject(IMyxName interfaceName) {"); //$NON-NLS-1$        
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			EServiceObjectDelegate delegate = iface.getServiceObjectDelegate();
			if (iface.getDirection() == EMyxInterfaceDirection.IN) {
				out.write("\t\tif(interfaceName.equals("); //$NON-NLS-1$        
				out.write(MyxCompStubUtil.getConstantName(iface));
				out.write(")){"); //$NON-NLS-1$        
				out.write(NL);
				switch (delegate) {
				case variable:
				case events:
				case myxRegistry:
					if (delegate.isNeedsProxy()) {
						out.write("\t\t\treturn "); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write("Proxy;"); //$NON-NLS-1$        
						out.write(NL);
					}
					else if (iface.isSingle()) {
						out.write("\t\t\tif("); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(" == null){"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t\tthrow new NullPointerException(\""); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write("\");"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\t}"); //$NON-NLS-1$        
						out.write(NL);
						out.write("\t\t\treturn "); //$NON-NLS-1$        
						out.write(MyxCompStubUtil.getServiceObjectName(iface));
						out.write(";"); //$NON-NLS-1$        
						out.write(NL);
					}
					break;
				case brick:
					out.write("\t\t\treturn this;"); //$NON-NLS-1$        
					out.write(NL);
					break;
				default: // do nothing
				}
				out.write("\t\t}"); //$NON-NLS-1$        
				out.write(NL);
			}
		}
		if (brick.getParentBrickId() != null) {
			out.write("\t\treturn super.getServiceObject(interfaceName);"); //$NON-NLS-1$        
			out.write(NL);
		}
		else {
			out.write("\t\tthrow new IllegalArgumentException(\"Unhandled interface service object: \"+interfaceName);"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write("\t}"); //$NON-NLS-1$        
		out.write(NL);
		out.write("}"); //$NON-NLS-1$        
	}
}
