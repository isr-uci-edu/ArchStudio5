package org.archstudio.myxgen.jet.extension;

/**
 * (Brick)Interface.
 * An interface is a port through which bricks exchanges their service objects.
 * An interface has the following attributes.
 * <ul>
 *  <li>id : the unique id of the interface</li>
 * 	<li>name : the name of the interface</li>
 * 	<li>direction : the direction of the interface 
 * 		<ul>
 * 		<li>Direction.IN : the brick is used by others through this interface.</li>
 * 		<li>Direction.OUT_SINGLE: the brick uses only one brick through this interface.</li>
 * 		<li>Direction.OUT_MULTI: the brick uses more than one brick through this interface.</li>
 * 		</ul>
 *  </li>
 * 	<li>connectionTiming : the connection timing of the interface
 * 		<ul>
 * 		<li> ConnectionTiming.NONE : the initializing timing is not cared.</li>
 * 		<li> ConnectionTiming.CONNECT_BEFORE_INIT : the brick will be connected to others 
 * 								before its initialization.</li>
 * 		<li> ConnectionTiming.CONNECT_BEFORE_BEGIN : the brick must be initialized first, 
 * 								then it will be connected to others.</li>
 * 		</ul>
 *  </li>
 * 	<li>javaInterface : the fully qualified Java Interface Name assigned to the interface. 
 * 			This java interface must exist before source code generation is called.</li>
 * 	<li>templateType : the template type how the interface should be implemented.
 * 		MyxCodegen generates code according to the template type.
 * 		<ul>
 * 		<li>NONE: no source code generation.</li>
 * 		<li> DELEGATE: The java interface of this signature will be (or has been)
 * 			imlemented outside of the brick class, and the brick will delegate to it.
 * 			A variable will be used to store the connection(s).</li>
 * 		<li> DELEGATE_WITH_GETTERS_AND_SETTERS: The java interface of this signature will be
 * 			(or has been) imlemented outside of the brick class, and the brick will
 * 			delegate it. Getters and Setters will be used to retrieve the connection(s).</li>
 * 		<li>IMPLEMENTED_IN_BRICK: (This option is only for inServiceObject) The java interface of this signature will be implemented
 * 			in the brick class.</li>
 * 		<li> IMPLEMENTED_IN_DELEGATE:(This option is only for outMultipleServiceObjects
 * 			direction.) The java interface of this signature will be implemented in a
 * 			proxy class that delegates the method calling to all the service objects. A
 * 			variable will be used to store the connection(s).</li>
 *		<li>IMPLEMENTED_IN_DELEGATE_WITH_GETTERS_AND_SETTERS:(This option is only for
 * 			outMultipleServiceObjects direction.) The java interface of this signature
 * 			will be implemented in a proxy class that delegates the method calling to all
 * 			the service objects. Getters and Setters will be used to retrieve the
 * 			connection(s).</li>
 * 		</ul>
 *  </li>
 * </ul> 
 * 
 * See schema/org.archstudio.myxgen.jet.myxBrick.exsd file for the schema
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 *
 */
public interface IInterface extends IExtensionPointLocation{

	/** the "interface" element name of the extension */
	public static final String ELEMENT_NAME = "interface";
	
	/**
	 * Returns the unique id of this interface
	 * @return the unique id of this interface. null if no id is assigned.
	 */
	public String getId();

	/**
	 * Returns the name of this interface
	 * 
	 * @return the name of this interface. null if no name is assigned.
	 */
	public String getName();

	/**
	 * Returns the Direction of this interface.
	 * 
	 * @return Direction of this interface. null if no direction is assigned.
	 */
	public Direction getDirection();

	/**
	 * Returns the ConnectionTiming of this interface.
	 * 
	 * @return the connectionTiming
	 */
	public ConnectionTiming getConnectionTiming();

	/**
	 * Returns this interfaces template type
	 * 
	 * @return the templateType
	 */
	public TemplateType getTemplateType();

	/**
	 * Returns the fully qualified java interface name of this interface.
	 * 
	 * @return the java interface name. null if no Interface is assigned.
	 */
	public String getFQJavaInterfaceName();

	/**
	 * Returns the description of this interface.
	 * @return the description or null if no description is assigned.
	 */
	public String getDescription();

}