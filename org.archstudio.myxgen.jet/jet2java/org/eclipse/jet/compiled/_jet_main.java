package org.eclipse.jet.compiled;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.JET2Template;
import org.eclipse.jet.JET2Writer;
import org.eclipse.jet.taglib.RuntimeTagElement;
import org.eclipse.jet.taglib.TagInfo;

public class _jet_main implements JET2Template {
	private static final String _jetns_c = "org.eclipse.jet.controlTags"; //$NON-NLS-1$
	private static final String _jetns_java = "org.eclipse.jet.javaTags"; //$NON-NLS-1$
	private static final String _jetns_ws = "org.eclipse.jet.workspaceTags"; //$NON-NLS-1$

	public _jet_main() {
		super();
	}

	private static final String NL = System.getProperty("line.separator"); //$NON-NLS-1$

	private static final TagInfo _td_c_setVariable_3_1 = new TagInfo("c:setVariable", //$NON-NLS-1$
			3, 1, new String[] { "var", //$NON-NLS-1$
					"select", //$NON-NLS-1$
			}, new String[] { "org.eclipse.jet.taglib.control.iterateSetsContext", //$NON-NLS-1$
					"true()", //$NON-NLS-1$
			});
	private static final TagInfo _td_ws_project_5_1 = new TagInfo("ws:project", //$NON-NLS-1$
			5, 1, new String[] { "name", //$NON-NLS-1$
			}, new String[] { "{$org.eclipse.jet.resource.project.name}", //$NON-NLS-1$
			});
	private static final TagInfo _td_ws_folder_6_3 = new TagInfo("ws:folder", //$NON-NLS-1$
			6, 3, new String[] { "path", //$NON-NLS-1$
			}, new String[] { "{$org.archstudio.myxgen.src.folder}", //$NON-NLS-1$
			});
	private static final TagInfo _td_java_class_7_3 = new TagInfo("java:class", //$NON-NLS-1$
			7, 3, new String[] { "package", //$NON-NLS-1$
					"name", //$NON-NLS-1$
					"template", //$NON-NLS-1$
			}, new String[] { "{$org.archstudio.myxgen.package}", //$NON-NLS-1$
					"{$org.archstudio.myxgen.class}", //$NON-NLS-1$
					"{$org.archstudio.myxgen.template}", //$NON-NLS-1$
			});

	public void generate(final JET2Context context, final JET2Writer __out) {
		JET2Writer out = __out;
		out.write(NL);
		RuntimeTagElement _jettag_c_setVariable_3_1 = context.getTagFactory().createRuntimeTag(_jetns_c,
				"setVariable", "c:setVariable", _td_c_setVariable_3_1); //$NON-NLS-1$ //$NON-NLS-2$
		_jettag_c_setVariable_3_1.setRuntimeParent(null);
		_jettag_c_setVariable_3_1.setTagInfo(_td_c_setVariable_3_1);
		_jettag_c_setVariable_3_1.doStart(context, out);
		_jettag_c_setVariable_3_1.doEnd();
		out.write(NL);
		RuntimeTagElement _jettag_ws_project_5_1 = context.getTagFactory().createRuntimeTag(_jetns_ws,
				"project", "ws:project", _td_ws_project_5_1); //$NON-NLS-1$ //$NON-NLS-2$
		_jettag_ws_project_5_1.setRuntimeParent(null);
		_jettag_ws_project_5_1.setTagInfo(_td_ws_project_5_1);
		_jettag_ws_project_5_1.doStart(context, out);
		while (_jettag_ws_project_5_1.okToProcessBody()) {
			RuntimeTagElement _jettag_ws_folder_6_3 = context.getTagFactory().createRuntimeTag(_jetns_ws,
					"folder", "ws:folder", _td_ws_folder_6_3); //$NON-NLS-1$ //$NON-NLS-2$
			_jettag_ws_folder_6_3.setRuntimeParent(_jettag_ws_project_5_1);
			_jettag_ws_folder_6_3.setTagInfo(_td_ws_folder_6_3);
			_jettag_ws_folder_6_3.doStart(context, out);
			while (_jettag_ws_folder_6_3.okToProcessBody()) {
				out.write("  "); //$NON-NLS-1$        
				RuntimeTagElement _jettag_java_class_7_3 = context.getTagFactory().createRuntimeTag(_jetns_java,
						"class", "java:class", _td_java_class_7_3); //$NON-NLS-1$ //$NON-NLS-2$
				_jettag_java_class_7_3.setRuntimeParent(_jettag_ws_folder_6_3);
				_jettag_java_class_7_3.setTagInfo(_td_java_class_7_3);
				_jettag_java_class_7_3.doStart(context, out);
				_jettag_java_class_7_3.doEnd();
				out.write(NL);
				_jettag_ws_folder_6_3.handleBodyContent(out);
			}
			_jettag_ws_folder_6_3.doEnd();
			_jettag_ws_project_5_1.handleBodyContent(out);
		}
		_jettag_ws_project_5_1.doEnd();
	}
}
