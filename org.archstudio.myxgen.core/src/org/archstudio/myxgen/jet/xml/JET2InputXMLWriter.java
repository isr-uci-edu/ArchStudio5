package org.archstudio.myxgen.jet.xml;

import java.io.OutputStream;
import java.io.Writer;

import org.archstudio.myxgen.jet.util.TextUtil;


/**
 * The XML writer that outputs the information for JET2 engine to generate a
 * java source file.
 * <p>
 * The format will be like this:
 * 
 * <pre>
 *  &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
 *  &lt;Input&gt;
 *  	&lt;Env srcFolder=&quot;srcFolderName&quot;/&gt;
 *  	&lt;Brick
 *  	  package=&quot;package.name.of.the.class&quot;
 *  	  class=&quot;ClassName&quot;
 *  	  template=&quot;TemplateName.java.jet&quot;&gt;
 *  	&lt;/Brick&gt;
 *  &lt;/Input&gt;
 * </pre>
 * 
 * </p>
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class JET2InputXMLWriter {

	/**
	 * @param fqClassName
	 * @param templateName
	 * @param srcFolderName
	 * @param out
	 */
	public static void write(String fqClassName, String templateName, String srcFolderName, OutputStream out) {
		XMLWriter writer = new XMLWriter(out, true);
		write(fqClassName, templateName, srcFolderName, writer);
	}

	/**
	 * Writes XML for JET2 engine specifying the class name, the template file
	 * name and the source folder name.
	 * 
	 * @param fqClassName
	 * @param templateName
	 * @param srcFolderName
	 * @param out
	 */
	public static void write(String fqClassName, String templateName, String srcFolderName, Writer out) {
		XMLWriter writer = new XMLWriter(out, true);
		write(fqClassName, templateName, srcFolderName, writer);
	}

	/**
	 * Writes XML for JET2 engine specifying the class name, the template file
	 * name and the source folder name.
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 *  &lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot;?&gt;
	 *  &lt;Input&gt;
	 *  	&lt;Env srcFolder=&quot;srcFolderName&quot;/&gt;
	 *  	&lt;Brick
	 *  	  package=&quot;package.name.of.the.class&quot;
	 *  	  class=&quot;ClassName&quot;
	 *  	  template=&quot;TemplateName.java.jet&quot;&gt;
	 *  	&lt;/Brick&gt;
	 *  &lt;/Input&gt;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param fqClassName
	 *            the fully qualified class name to be generated
	 * @param templateName
	 *            the JET2 template file name (stored at templates)
	 * @param srcFolderName
	 *            the root folder name of the source code (src)
	 * @param writer
	 */
	private static void write(String fqClassName, String templateName, String srcFolderName, XMLWriter writer) {
		writer.printTag("Input");
		writer.printEmptyTag("Env", new Attribute("srcFolder", srcFolderName));

		writer.printTag("Brick", new Attribute("package", TextUtil.getPackagePart(fqClassName)), new Attribute("class", TextUtil.getClassPart(fqClassName)),
		        new Attribute("template", templateName));
		writer.endTag("Brick");
		writer.endTag("Input");
		writer.flush();
	}

}
