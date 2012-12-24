package org.eclipse.jet.compiled;

import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.MyxGenInterface;
import org.archstudio.myxgen.jet.codegen.MyxCompStubUtil;
import org.archstudio.myxgen.jet.codegen.MyxCompUtil;
import org.archstudio.myxgen.jet.util.TextUtil;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;

public class _jet_MyxCompjava implements JET2Template {
	private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$

	public _jet_MyxCompjava() {
		super();
	}

	private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$

	private static final TagInfo _td_java_merge_5_1 = new TagInfo("java:merge", //$NON-NLS-1$
			5, 1, new String[] {}, new String[] {});

	@Override
	public void generate(final JET2Context context, final JET2Writer __out) {
		JET2Writer out = __out;
		RuntimeTagElement _jettag_java_merge_5_1 = context.getTagFactory().createRuntimeTag(_jetns_java,
				"merge", "java:merge", _td_java_merge_5_1); //$NON-NLS-1$ //$NON-NLS-2$
		_jettag_java_merge_5_1.setRuntimeParent(null);
		_jettag_java_merge_5_1.setTagInfo(_td_java_merge_5_1);
		_jettag_java_merge_5_1.doStart(context, out);
		_jettag_java_merge_5_1.doEnd();
		out.write(NL);

		MyxGenBrick brick = (MyxGenBrick) context.getVariable(MyxGenBrick.class.getName());
		String packageName = TextUtil.getPackagePart(brick.getClassName());
		String className = TextUtil.getClassPart(brick.getClassName());

		out.write("package "); //$NON-NLS-1$        
		out.write(packageName);
		out.write(";"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		for (String javaImport : MyxCompUtil.getImports(brick)) {
			out.write("import "); //$NON-NLS-1$        
			out.write(javaImport);
			out.write(";"); //$NON-NLS-1$        
			out.write(NL);
		}
		out.write(NL);
		out.write("/**"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" * Myx brick: \""); //$NON-NLS-1$        
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
		out.write(" * @see "); //$NON-NLS-1$        
		out.write(brick.getStubClassName());
		out.write(NL);
		out.write(" * @generated"); //$NON-NLS-1$        
		out.write(NL);
		out.write(" */"); //$NON-NLS-1$        
		out.write(NL);
		out.write("public "); //$NON-NLS-1$        
		out.write(brick.isAbstract() ? "abstract" : "");
		out.write(" class "); //$NON-NLS-1$        
		out.write(className);
		out.write(" extends "); //$NON-NLS-1$        
		out.write(brick.getStubClassName());
		out.write(" {"); //$NON-NLS-1$        
		out.write(NL);
		out.write(NL);
		for (MyxGenInterface iface : brick.getInterfaces()) {
			switch (iface.getServiceObjectDelegate()) {
			case brick:
				if (!iface.isSingle()) {
					out.write("\t/**"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t * @generated"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t */"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\tprotected void add"); //$NON-NLS-1$        
					out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
					out.write("("); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" serviceObject) {"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t\t// TODO Auto-generated method stub"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t}"); //$NON-NLS-1$        
					out.write(NL);
					out.write(NL);
					out.write("\t/**"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t * @generated"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t */"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\tprotected void remove"); //$NON-NLS-1$        
					out.write(SystemUtils.capFirst(MyxCompStubUtil.getServiceObjectName(iface)));
					out.write("("); //$NON-NLS-1$        
					out.write(iface.getClassName());
					out.write(" serviceObject) {"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t\t// TODO Auto-generated method stub"); //$NON-NLS-1$        
					out.write(NL);
					out.write("\t}"); //$NON-NLS-1$        
					out.write(NL);
				}
				break;
			default: // do nothing
			}
		}
		out.write("\t"); //$NON-NLS-1$        
		out.write(NL);
		out.write("}"); //$NON-NLS-1$        
	}
}
