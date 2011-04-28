package org.archstudio.myxgen.jet.xml;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * A simple XML writer, similar to
 * org.eclipse.jdt.internal.compiler.util.GenericXMLWriter
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class XMLWriter extends PrintWriter {

	private static final String lineseparator = System.getProperty("line.separator");
	private int tab;
	private static final String XML_VERSION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$

	private static void appendEscapedChar(StringBuffer buffer, char c) {
		String replacement = getReplacement(c);
		if (replacement != null) {
			buffer.append('&');
			buffer.append(replacement);
			buffer.append(';');
		}
		else {
			buffer.append(c);
		}
	}

	private static String getEscaped(String s) {
		StringBuffer result = new StringBuffer(s.length() + 10);
		for (int i = 0; i < s.length(); ++i) {
			appendEscapedChar(result, s.charAt(i));
		}
		return result.toString();
	}

	private static String getReplacement(char c) {
		// Encode special XML characters into the equivalent character references.
		// These five are defined by default for all XML documents.
		switch (c) {
		case '<':
			return "lt"; //$NON-NLS-1$
		case '>':
			return "gt"; //$NON-NLS-1$
		case '"':
			return "quot"; //$NON-NLS-1$
		case '\'':
			return "apos"; //$NON-NLS-1$
		case '&':
			return "amp"; //$NON-NLS-1$
		}
		return null;
	}

	public XMLWriter(OutputStream stream, boolean printXmlVersion) {
		this(new PrintWriter(stream), printXmlVersion);
	}

	public XMLWriter(Writer writer, boolean printXmlVersion) {
		super(writer);
		this.tab = 0;
		if (printXmlVersion) {
			println(XML_VERSION);
		}
	}

	/**
	 * Prints the given string.
	 * 
	 * @param string
	 */
	public void printString(String string) {
		printString(string, true/* insert tab */, true /* insert newline */);
	}

	private void printString(String string, boolean insertTab, boolean insertNewLine) {
		if (insertTab) {
			printTab();
		}
		if (insertNewLine) {
			println(string);
		}
		else {
			print(string);
		}
	}

	private void printTab() {
		for (int i = 0; i < this.tab; i++) {
			this.print('\t');
		}
	}

	private String getTab() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.tab; i++) {
			sb.append('\t');
		}
		return sb.toString();

	}

	/**
	 * Prints an empty tag with the specified name and the attributes.
	 * <p>
	 * For example,
	 * 
	 * <pre>
	 * printEmptyTag(&quot;name&quot;, new Attribute(&quot;attr1&quot;, &quot;value1&quot;), new Attribute(&quot;attr2&quot;, &quot;value2&quot;));
	 * </pre>
	 * 
	 * will produce
	 * 
	 * <pre>
	 * &lt;name attr1=&quot;value1&quot; attr2=&quot;value2&quot;/&gt;
	 * </pre>
	 * 
	 * .
	 * </p>
	 * 
	 * @param name
	 * @param attributes
	 *            {@link Attribute}
	 */
	public void printEmptyTag(String name, Attribute... attributes) {
		printTag(name, true/* insert tab */, true/* insert newline */, true/*
																		 * an
																		 * empty
																		 * tag
																		 */, attributes);

	}

	/**
	 * Prints an tag with the specified name, then the object, and then the
	 * closing tag. The object will be evaluated as object.toString().
	 * <p>
	 * For example,
	 * 
	 * <pre>
	 * printSimpleTag(&quot;name&quot;, Integer.valueOf(6));
	 * </pre>
	 * 
	 * will produce
	 * 
	 * <pre>
	 * &lt;name&gt;6&lt;/name&gt;
	 * </pre>
	 * 
	 * .
	 * </p>
	 * 
	 * @param name
	 * @param value
	 */
	public void printSimpleTag(String name, Object value) {
		if (value != null) {
			printTag(name, true/* tab */, false/* newline */, false/* closeTag */);
			this.tab--;
			print(getEscaped(String.valueOf(value)));
			printTag('/' + name, false /* insert tab */, true /*
																 * insert
																 * newline
																 */, false/*
																		 * don't
																		 * close
																		 * tag
																		 */);
			this.tab--;
		}
	}

	/**
	 * Prints a start tag with the given name and the attributes.
	 * <p>
	 * For example,
	 * 
	 * <pre>
	 * printTag(&quot;name&quot;, new Attribute(&quot;attr1&quot;, &quot;value1&quot;), new Attribute(&quot;attr2&quot;, &quot;value2&quot;));
	 * </pre>
	 * 
	 * will produce
	 * 
	 * <pre>
	 * &lt;name attr1=&quot;value1&quot; attr2=&quot;value2&quot;&gt;
	 * </pre>
	 * 
	 * .
	 * </p>
	 * 
	 * @param name
	 * @param attributes
	 *            {@link Attribute}
	 */
	public void printTag(String name, Attribute... attributes) {
		printTag(name, true/* insert tab */, true/* insert newline */, false/*
																			 * not
																			 * an
																			 * empty
																			 * tag
																			 */, attributes);
	}

	private void printTag(String name, boolean insertTab, boolean insertNewLine, boolean closeTag, Attribute... parameters) {
		if (insertTab) {
			this.printTab();
		}
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		sb.append(name);
		for (Attribute entry : parameters) {
			if (parameters.length > 2) {
				sb.append(lineseparator);
				sb.append(getTab());
				sb.append("  ");
			}
			else {
				sb.append(' ');
			}
			sb.append(entry.getKey());
			sb.append("=\""); //$NON-NLS-1$
			sb.append(getEscaped(String.valueOf(entry.getValue())));
			sb.append('\"');
		}
		if (closeTag) {
			sb.append("/>"); //$NON-NLS-1$
		}
		else {
			sb.append(">"); //$NON-NLS-1$
		}
		if (insertNewLine) {
			println(sb.toString());
		}
		else {
			print(sb.toString());
		}
		if (!closeTag) {
			this.tab++;
		}

	}

	/**
	 * Prints a end tag with the given name.
	 * <p>
	 * For example,
	 * 
	 * <pre>
	 * endTag(&quot;name&quot;);
	 * </pre>
	 * 
	 * will produce
	 * 
	 * <pre>
	 * &lt;/name&gt;
	 * </pre>
	 * 
	 * .
	 * </p>
	 * 
	 * @param name
	 */
	public void endTag(String name) {
		this.tab--;
		printTag('/' + name, true /* insert tab */, true /* insert newline */, false/*
																					 * don't
																					 * close
																					 * tag
																					 */);
		this.tab--;
	}

	public static void main(String[] args) {
		XMLWriter writer = new XMLWriter(System.out, true);
		writer.printTag("Users");
		writer.printTag("UserA", new Attribute("attr1", "value1"), new Attribute("attr2", "value2"), new Attribute("attr3", "value3"));
		writer.printTag("userB");
		writer.printString("test", true, true);
		writer.endTag("userB");
		writer.printTag("userB");
		writer.endTag("userB");
		writer.printSimpleTag("simple", "value");
		writer.printEmptyTag("UserC", new Attribute("attr1", "value1"), new Attribute("attr2", "value2"));

		writer.endTag("UserA");
		writer.endTag("Users");
		writer.flush();

	}

}
