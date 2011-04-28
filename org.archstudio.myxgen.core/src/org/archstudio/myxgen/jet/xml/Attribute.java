package org.archstudio.myxgen.jet.xml;


/**
 * An attribute of XML tag used in {@link JET2InputXMLWriter}.
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
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class Attribute {
	private String key;
	private String value;

	public Attribute(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
