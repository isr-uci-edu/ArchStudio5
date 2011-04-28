package org.archstudio.myxgen.jet.taglib;

import org.eclipse.jet.JET2Context;
import org.eclipse.jet.taglib.AbstractFunctionTag;
import org.eclipse.jet.taglib.JET2TagException;
import org.eclipse.jet.taglib.TagInfo;

/**
 * Under experiment.
 * <p>
 * Extracts the package name from a fully qualified class name, implementing the
 * JET Function Tag &lt;jcn:getPackage&gt;. If "var" attribute is set, the
 * package name is stored in the attribute.
 * </p>
 * Eclipse extension point used here is:
 * <ul>
 * <li>ExtensionPoint: org.eclipse.jet.taglibraries</li>
 * <li>tagLibrary (id): javaClassNameLibrary</li>
 * <li>functionTag (name): getPackage</li>
 * </ul>
 * Taglib declaration: &lt;%@taglib prefix="jcn"
 * id="org.archstudio.myxgen.jet.pluginid.javaClassNameLibrary"%&gt;
 * <p>
 * Example1:
 * <code>&lt;jcn:getPackage&gt;com.foo.Foo&lt;/jcn:getPackage&gt;</code> will
 * produce <code>"com.foo"</code>.
 * </p>
 * <p>
 * Example2:
 * <code>&lt;jcn:getPackage var="pckg"&gt;com.foo.Foo&lt;/jcn:getPackage&gt;</code>
 * will produce <code>"com.foo"</code> and also <code>$pckg</code> will remember
 * <code>"com.foo"</code>.
 * </p>
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class GetPackageNameTag extends AbstractFunctionTag {

	public GetPackageNameTag() {
		super();
	}

	/**
	 * Implements <jcn:getPackage>fullyQualifiedClassName</jcn:getPackage>
	 */
	public String doFunction(TagInfo td, JET2Context context, String bodyContent) throws JET2TagException {

		if (bodyContent == null) {
			return null;
		}
		int index = bodyContent.lastIndexOf('.');
		String packageName = bodyContent.substring(0, index);

		//if "var" is set, rember the packageName as var
		String var = getAttribute("var"); //$NON-NLS-1$
		if (var != null) {
			context.setVariable(var, packageName);
		}
		return packageName;
	}

}
