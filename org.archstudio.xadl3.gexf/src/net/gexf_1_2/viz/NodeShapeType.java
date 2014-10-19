/**
 */
package net.gexf_1_2.viz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Node Shape Type</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 * 
 * @see net.gexf_1_2.viz.VizPackage#getNodeShapeType()
 * @model extendedMetaData="name='node-shape-type'"
 * @generated
 */
public enum NodeShapeType implements Enumerator {
	/**
	 * The '<em><b>Disc</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #DISC_VALUE
	 * @generated
	 * @ordered
	 */
	DISC(0, "disc", "disc"),

	/**
	 * The '<em><b>Square</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #SQUARE_VALUE
	 * @generated
	 * @ordered
	 */
	SQUARE(1, "square", "square"),

	/**
	 * The '<em><b>Triangle</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #TRIANGLE_VALUE
	 * @generated
	 * @ordered
	 */
	TRIANGLE(2, "triangle", "triangle"),

	/**
	 * The '<em><b>Diamond</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #DIAMOND_VALUE
	 * @generated
	 * @ordered
	 */
	DIAMOND(3, "diamond", "diamond"),

	/**
	 * The '<em><b>Image</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #IMAGE_VALUE
	 * @generated
	 * @ordered
	 */
	IMAGE(4, "image", "image");

	/**
	 * The '<em><b>Disc</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Disc</b></em>' literal object isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #DISC
	 * @model name="disc"
	 * @generated
	 * @ordered
	 */
	public static final int DISC_VALUE = 0;

	/**
	 * The '<em><b>Square</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Square</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #SQUARE
	 * @model name="square"
	 * @generated
	 * @ordered
	 */
	public static final int SQUARE_VALUE = 1;

	/**
	 * The '<em><b>Triangle</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Triangle</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #TRIANGLE
	 * @model name="triangle"
	 * @generated
	 * @ordered
	 */
	public static final int TRIANGLE_VALUE = 2;

	/**
	 * The '<em><b>Diamond</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Diamond</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #DIAMOND
	 * @model name="diamond"
	 * @generated
	 * @ordered
	 */
	public static final int DIAMOND_VALUE = 3;

	/**
	 * The '<em><b>Image</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Image</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #IMAGE
	 * @model name="image"
	 * @generated
	 * @ordered
	 */
	public static final int IMAGE_VALUE = 4;

	/**
	 * An array of all the '<em><b>Node Shape Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final NodeShapeType[] VALUES_ARRAY = new NodeShapeType[] { DISC, SQUARE, TRIANGLE, DIAMOND, IMAGE, };

	/**
	 * A public read-only list of all the '<em><b>Node Shape Type</b></em>' enumerators. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<NodeShapeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Node Shape Type</b></em>' literal with the specified literal value. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static NodeShapeType get(String literal) {
		for (NodeShapeType result : VALUES_ARRAY) {
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Shape Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static NodeShapeType getByName(String name) {
		for (NodeShapeType result : VALUES_ARRAY) {
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Shape Type</b></em>' literal with the specified integer value. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static NodeShapeType get(int value) {
		switch (value) {
		case DISC_VALUE:
			return DISC;
		case SQUARE_VALUE:
			return SQUARE;
		case TRIANGLE_VALUE:
			return TRIANGLE;
		case DIAMOND_VALUE:
			return DIAMOND;
		case IMAGE_VALUE:
			return IMAGE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private NodeShapeType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //NodeShapeType
