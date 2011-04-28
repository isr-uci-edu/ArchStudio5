package org.archstudio.myxgen.jet.brick;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.myxgen.jet.extension.ConnectionTiming;
import org.archstudio.myxgen.jet.extension.Direction;
import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.TemplateType;
import org.archstudio.myxgen.jet.util.TextUtil;


/**
 * brick interface info container.
 * 
 * @author @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class CodegenBrickInterface implements IInterface {

	/**
	 * Interface of Brick Type defined in the eclipse's extension point
	 */
	private final IInterface extIntf;

	/**
	 * whether this is java interface
	 */
	private boolean isJavaInterface = true;

	/**
	 * NULL list
	 */
	private final static List<MethodContainer> NULL_METHOD_CONTAINERS = Collections
			.unmodifiableList(new ArrayList<MethodContainer>());

	/**
	 * List of this interface methods whose return types are not void.
	 */
	private List<MethodContainer> javaInterfaceNonVoidMethods = CodegenBrickInterface.NULL_METHOD_CONTAINERS;

	/**
	 * List of this interface methods whose return types are void.
	 */
	private List<MethodContainer> javaInterfaceVoidMethods = CodegenBrickInterface.NULL_METHOD_CONTAINERS;

	/**
	 * Constructor
	 * 
	 * @param extIntf
	 */
	public CodegenBrickInterface(IInterface extIntf) {
		this.extIntf = extIntf;
	}

	public URL getExtensionPointUrl() {
		return extIntf.getExtensionPointUrl();
	}

	public URL getPluginUrl() {
		return extIntf.getPluginUrl();
	}

	public String getSymbolicName() {
		return extIntf.getSymbolicName();
	}

	/**
	 * Returns the unique id of this interface
	 * 
	 * @return the unique id of this interface. null if no id is assigned.
	 */
	public String getId() {
		return extIntf.getId();
	}

	/**
	 * Returns the name of this interface. The name will be transformed appropriate for java implementation. For
	 * example, the string "foo.name" yields "fooName".
	 * 
	 * @return the name of this interface. null if no name is assigned.
	 */
	public String getName() {
		return TextUtil.toVariableName(extIntf.getName());
	}

	/**
	 * Returns the raw name of this interface
	 * 
	 * @return
	 */
	public String getRawName() {
		return extIntf.getName();
	}

	/**
	 * Returns the Direction of this interface.
	 * 
	 * @return Direction of this interface. null if no direction is assigned.
	 */
	public Direction getDirection() {
		return extIntf.getDirection();
	}

	/**
	 * Returns the ConnectionTiming of this interface.
	 * 
	 * @return the connectionTiming
	 */
	public ConnectionTiming getConnectionTiming() {
		return extIntf.getConnectionTiming();
	}

	/**
	 * Returns this interfaces template type
	 * 
	 * @return the templateType
	 */
	public TemplateType getTemplateType() {
		return extIntf.getTemplateType();
	}

	/**
	 * Returns the fully qualified java interface name of this interface.
	 * 
	 * @return the java interface name. null if no Interface is assigned.
	 */
	public String getFQJavaInterfaceName() {
		return extIntf.getFQJavaInterfaceName();
	}

	/**
	 * Returns the simple java interface name of this interface. For example, this method returns "Foo" instead of
	 * "package.name.Foo".
	 * 
	 * @return the java interface name. null if no Interface is assigned.
	 */
	public String getSimpleJavaInterfaceName() {
		return TextUtil.getClassPart(extIntf.getFQJavaInterfaceName());
	}

	/**
	 * Returns the description of the brick
	 * 
	 * @return the description of the brick
	 */
	public String getDescription() {
		String desc = extIntf.getDescription();
		return desc != null ? desc : "";
	}

	/**
	 * Returns the list of methods that this javaInterface declares
	 * 
	 * @return the list of method information of this java interface
	 */
	public List<MethodContainer> getJavaInterfaceMethods() {
		List<MethodContainer> methods = new ArrayList<MethodContainer>();
		if (javaInterfaceNonVoidMethods != null) {
			methods.addAll(javaInterfaceNonVoidMethods);
		}
		if (javaInterfaceVoidMethods != null) {
			methods.addAll(javaInterfaceVoidMethods);
		}
		return methods;
	}

	/**
	 * Returns the list of methods whose return type are not void
	 * 
	 * @return
	 */
	public List<MethodContainer> getJavaInterfaceNonVoidMethods() {
		return javaInterfaceNonVoidMethods;
	}

	/**
	 * @param javaInterfaceMethods
	 *            the javaInterfaceMethods to set
	 */
	public void setJavaInterfaceNonVoidMethods(List<MethodContainer> javaInterfaceMethods) {
		this.javaInterfaceNonVoidMethods = javaInterfaceMethods;
	}

	/**
	 * Returns a collection of methods whose return types are void.
	 * 
	 * @return the javaVoidInterfaceMethods
	 */
	public List<MethodContainer> getJavaInterfaceVoidMethods() {
		return javaInterfaceVoidMethods;
	}

	/**
	 * @param javaInterfaceVoidMethods
	 *            the javaInterfaceVoidMethods to set
	 */
	public void setJavaInterfaceVoidMethods(List<MethodContainer> javaInterfaceVoidMethods) {
		this.javaInterfaceVoidMethods = javaInterfaceVoidMethods;
	}

	/**
	 * Returns true if this brick interface is a java interface
	 * 
	 * @return
	 */
	public boolean isJavaInterface() {
		return isJavaInterface;
	}

	/**
	 * @param isJavaInterface
	 *            the isJavaInterface to set
	 */
	public void setJavaInterface(boolean isJavaInterface) {
		this.isJavaInterface = isJavaInterface;
	}

	/**
	 * Returns the collection of fully qualified class names the arguments of methods .
	 * 
	 * @return
	 */
	public static Collection<String> getArgsFQClassNames(List<MethodContainer> methods) {

		Set<String> fQClassNames = new HashSet<String>();

		for (MethodContainer method : methods) {

			//return type
			if (!method.isPrimitiveReturnType()) {
				fQClassNames.addAll(method.getFqReturnClassNames());
			}

			//paramter types
			for (MethodParameter param : method.getMethodParameters()) {
				if (!param.isPrimitiveType()) {
					fQClassNames.addAll(param.getFqClassNames());
				}
			}

			//exceptions
			fQClassNames.addAll(method.getFqExceptionTypes());

		}

		return fQClassNames;
	}

	/**
	 * Returns true if this interface declares non-void methods
	 * 
	 * @return
	 */
	public boolean hasNonVoidMethods() {
		return javaInterfaceNonVoidMethods != null && !javaInterfaceNonVoidMethods.isEmpty();
	}

	//	public static void main(String[] args) {
	//		System.out.println("Boolean.getBoolean(\"true\") =" + Boolean.getBoolean("true"));
	//		System.out.println("Boolean.parseBoolean(\"true\") =" + Boolean.parseBoolean("true"));
	//	}
}
