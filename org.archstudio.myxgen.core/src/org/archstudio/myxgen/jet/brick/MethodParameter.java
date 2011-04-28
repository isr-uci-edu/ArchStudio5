package org.archstudio.myxgen.jet.brick;

import java.util.Collection;

/**
 * One parameter of a method.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class MethodParameter {

	private String paramType;

	/** name */
	private String paramName;

	private boolean primitiveType;

	/** class names used in this method parameter
	 * Ex. if the parameter is defined as Collection&lt;String&gt; , 
	 * this fqClassNames contains java.util.Collection and java.lang.String.
	 */
	private Collection<String> fqClassNames;

	/**
	 * @param paramType
	 * @param paramName
	 * @param primitiveType
	 */
	public MethodParameter(String paramType, String paramName, boolean primitiveType, Collection<String> fqClassNames) {
		this.paramType = paramType;
		this.paramName = paramName;
		this.primitiveType = primitiveType;
		this.fqClassNames = fqClassNames;
	}


	/**
	 * Returns the string expression of parameter type. 
	 * 
	 * @return
	 */
	public String getParamType() {

		return paramType;
	}

	/**
	 * Returns the parameter name
	 * 
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @return the primitiveType
	 */
	public boolean isPrimitiveType() {
		return primitiveType;
	}

	/**
	 * Returns the collection of classes of this parameter type.
	 * Ex. if the parameter is defined as Collection&lt;String&gt; , 
	 * this method returns a collection of java.util.Collection and java.lang.String.
	 * @return the fqClassNames
	 */
	public Collection<String> getFqClassNames() {
		return fqClassNames;
	}

	/**
	 * Returns a string expression of this parameter.
	 * Ex. "List<String> strList"
	 * @return
	 */
	public String toSimpleString() {

		return paramType + " " + paramName;
	}

}
