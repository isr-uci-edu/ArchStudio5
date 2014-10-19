/**
 */
package net.gexf_1_2.gexf;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Defaultedgetype Type</b></em>',
 * and utility methods for working with them. <!-- end-user-doc --> <!-- begin-model-doc --> Datatypes <!--
 * end-model-doc -->
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getDefaultedgetypeType()
 * @model extendedMetaData="name='defaultedgetype-type'"
 * @generated
 */
public enum DefaultedgetypeType implements Enumerator {
	/**
	 * The '<em><b>Directed</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #DIRECTED_VALUE
	 * @generated
	 * @ordered
	 */
	DIRECTED(0, "directed", "directed"),

	/**
	 * The '<em><b>Undirected</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #UNDIRECTED_VALUE
	 * @generated
	 * @ordered
	 */
	UNDIRECTED(1, "undirected", "undirected"),

	/**
	 * The '<em><b>Mutual</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #MUTUAL_VALUE
	 * @generated
	 * @ordered
	 */
	MUTUAL(2, "mutual", "mutual");

	/**
	 * The '<em><b>Directed</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Directed</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #DIRECTED
	 * @model name="directed"
	 * @generated
	 * @ordered
	 */
	public static final int DIRECTED_VALUE = 0;

	/**
	 * The '<em><b>Undirected</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Undirected</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #UNDIRECTED
	 * @model name="undirected"
	 * @generated
	 * @ordered
	 */
	public static final int UNDIRECTED_VALUE = 1;

	/**
	 * The '<em><b>Mutual</b></em>' literal value. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mutual</b></em>' literal object isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @see #MUTUAL
	 * @model name="mutual"
	 * @generated
	 * @ordered
	 */
	public static final int MUTUAL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Defaultedgetype Type</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	private static final DefaultedgetypeType[] VALUES_ARRAY = new DefaultedgetypeType[] { DIRECTED, UNDIRECTED, MUTUAL, };

	/**
	 * A public read-only list of all the '<em><b>Defaultedgetype Type</b></em>' enumerators. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final List<DefaultedgetypeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Defaultedgetype Type</b></em>' literal with the specified literal value. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static DefaultedgetypeType get(String literal) {
		for (DefaultedgetypeType result : VALUES_ARRAY) {
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Defaultedgetype Type</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static DefaultedgetypeType getByName(String name) {
		for (DefaultedgetypeType result : VALUES_ARRAY) {
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Defaultedgetype Type</b></em>' literal with the specified integer value. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static DefaultedgetypeType get(int value) {
		switch (value) {
		case DIRECTED_VALUE:
			return DIRECTED;
		case UNDIRECTED_VALUE:
			return UNDIRECTED;
		case MUTUAL_VALUE:
			return MUTUAL;
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
	private DefaultedgetypeType(int value, String name, String literal) {
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

} //DefaultedgetypeType
