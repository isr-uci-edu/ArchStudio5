package org.archstudio.myxgen.jet.taglib;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.taglib.AbstractFunctionTag;
import org.eclipse.jet.taglib.JET2TagException;
import org.eclipse.jet.taglib.TagInfo;

/**
 * Under experiment.
 * <p>
 * Extracts the class name from a fully qualified class name, implementing the
 * JET Function Tag &lt;jcn:getClass&gt;. If "var" attribute is set, the package
 * name is stored in the attribute.
 * </p>
 * Eclipse extension point used here is:
 * <ul>
 * <li>ExtensionPoint: org.eclipse.jet.taglibraries</li>
 * <li>tagLibrary (id): javaClassNameLibrary</li>
 * <li>functionTag (name): getClass</li>
 * </ul>
 * Taglib declaration: &lt;%@taglib prefix="jcn"
 * id="org.archstudio.myxgen.jet.pluginid.javaClassNameLibrary"%&gt;
 * <p>
 * Example1: <code>&lt;jcn:getClass&gt;com.foo.Foo&lt;/jcn:getClass&gt;</code>
 * will produce <code>"Foo"</code>.
 * </p>
 * <p>
 * Example2:
 * <code>&lt;jcn:getClass var="cls"&gt;com.foo.Foo&lt;/jcn:getClass&gt;</code>
 * will produce <code>"Foo"</code> and also <code>$cls</code> will remember
 * <code>"Foo"</code>.
 * </p>
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class GetClassNameTag extends AbstractFunctionTag {

	public GetClassNameTag() {
		super();
	}

	/**
	 * Implements <jcn:getClass>fullyQualifiedClassName</jcn:getClass>
	 */
	public String doFunction(TagInfo td, JET2Context context, String bodyContent) throws JET2TagException {
		if (bodyContent == null) {
			return null;
		}
		int index = bodyContent.lastIndexOf('.');
		String className = bodyContent.substring(index + 1);

		//if "var" is set, remember the className as the var.
		String var = getAttribute("var"); //$NON-NLS-1$
		if (var != null) {
			context.setVariable(var, className);
		}
		return className;
	}

}
