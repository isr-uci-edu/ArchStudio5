package org.archstudio.myxgen.jet.brick;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.archstudio.myxgen.jet.util.TextUtil;


/**
 * A method info container.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class MethodContainer {

	private String modifiers;
	private String returnType;
	private String methodName;
	private List<MethodParameter> methodParameters = new ArrayList<MethodParameter>();
	private List<String> fqExceptionTypes = new ArrayList<String>();

	//the collection of class names including class names specified in the generics
	private Collection<String> fqReturnClassNames;

	/**
	 * true is the return type of this method is primitive types such as "void,
	 * int, short,.."
	 */
	private boolean primitiveReturnType;

	/** String expression of default return value */
	private String defaultReturnValue;

	public MethodContainer() {
	}

	/**
	 * Returns modifiers of this method in string
	 * 
	 * @return the modifiers
	 */
	public String getModifiers() {
		return modifiers;
	}

	/**
	 * @param modifiers
	 *            the modifiers to set
	 */
	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	/**
	 * @param returnType
	 *            the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * Returns the simple return type. (i.e. /"Foo/" not /"package.name.Foo/")
	 * 
	 * @return the simple return type
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @return the fqReturnClassNames
	 */
	public Collection<String> getFqReturnClassNames() {
		return fqReturnClassNames;
	}

	/**
	 * @param fqReturnClassNames
	 *            the fqReturnClassNames to set
	 */
	public void setFqReturnClassNames(Collection<String> fqReturnClassNames) {
		this.fqReturnClassNames = fqReturnClassNames;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Returns the list of parameters of this method
	 * 
	 * @return the params
	 */
	public List<MethodParameter> getMethodParameters() {
		return methodParameters;
	}

	/**
	 * @param methodParameters
	 *            the list of method parameters to set
	 */
	public void setMethodParameters(List<MethodParameter> methodParameters) {
		this.methodParameters = methodParameters;
	}

	/**
	 * @return the list of fully qualified exception types
	 */
	public List<String> getFqExceptionTypes() {
		return fqExceptionTypes;
	}

	/**
	 * @param fqExceptionTypes
	 *            the list of fully qualified exception types to set
	 */
	public void setFqExceptionTypes(List<String> fqExceptionTypes) {
		this.fqExceptionTypes = fqExceptionTypes;
	}

	/**
	 * @return the primitiveReturnType
	 */
	public boolean isPrimitiveReturnType() {
		return primitiveReturnType;
	}

	/**
	 * @param primitiveReturnType
	 *            the primitiveReturnType to set
	 */
	public void setPrimitiveReturnType(boolean primitiveReturnType) {
		this.primitiveReturnType = primitiveReturnType;
	}

	/**
	 * @return the defaultReturnValue
	 */
	public String getDefaultReturnValue() {
		return defaultReturnValue;
	}

	/**
	 * @param defaultReturnValue
	 *            the defaultReturnValue to set
	 */
	public void setDefaultReturnValue(String defaultReturnValue) {
		this.defaultReturnValue = defaultReturnValue;
	}

	/**
	 * Returns string expression of parameter names. For example, if the method
	 * is /"foo(String arg1, String arg2)/", this method returns /"arg1, arg2/".
	 * 
	 * @return
	 */
	public String toParamNamesString() {

		StringBuilder sb = new StringBuilder();
		for (MethodParameter param : methodParameters) {
			sb.append(param.getParamName()).append(", ");
		}
		if (methodParameters.size() > 0) {
			sb.delete(sb.lastIndexOf(", "), sb.length());
		}
		return sb.toString();
	}

	/**
	 * Returns string expression of parameters. Prameter types are expressed in
	 * simple class names. For example, if the method is /"foo(String arg1,
	 * String arg2)/", this method returns /"String arg1, String arg2/".
	 * 
	 * @return
	 */
	public String toParamsSimpleString() {
		StringBuilder sb = new StringBuilder();
		for (MethodParameter param : methodParameters) {
			sb.append(param.toSimpleString()).append(", ");
		}
		if (methodParameters.size() > 0) {
			sb.delete(sb.lastIndexOf(", "), sb.length());
		}
		return sb.toString();
	}

	/**
	 * Returns the information of this method in a simple class name style
	 * 
	 * @return
	 */
	public String toSimpleString() {
		StringBuilder sb = new StringBuilder();
		sb.append(modifiers).append(" ").append(TextUtil.getClassPart(returnType)).append(" ").append(methodName).append("(").append(toParamsSimpleString())
		        .append(")");
		if (fqExceptionTypes.size() > 0) {
			sb.append(" throws ");
		}
		for (String fqException : fqExceptionTypes) {
			sb.append(TextUtil.getClassPart(fqException)).append(", ");
		}
		if (fqExceptionTypes.size() > 0) {
			sb.delete(sb.lastIndexOf(", "), sb.length());
		}

		return sb.toString();

	}

}
