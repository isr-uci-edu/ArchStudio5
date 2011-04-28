package org.archstudio.myxgen.jet.extension;

/**
 * Code generation template types. The template type specifies how the
 * (brick)Interface will be implemented in the brick through source code
 * generation.
 * <ul>
 * <li>none: no source code generation.</li>
 * <li>delegate: The java interface of this signature will be (or has been)
 * imlemented outside of the brick class, and the brick will delegate to it. A
 * variable will be used to store the connection(s).</li>
 * <li>delegateWithGetters: The java interface of this signature will be (or has
 * been) imlemented outside of the brick class, and the brick will delegate it.
 * Getters and Setters will be used to retrieve the connection(s).</li>
 * <li>implementedInBrick: The java interface of this signature will be
 * implemented in the brick class.</li>
 * <li>implementedInDelegate:(This option is only for outMultipleServiceObjects
 * direction.) The java interface of this signature will be implemented in a
 * proxy class that delegates the method calling to all the service objects. A
 * variable will be used to store the connection(s).</li>
 * <li>implementedInDelegateWithGetters:(This option is only for
 * outMultipleServiceObjects direction.) The java interface of this signature
 * will be implemented in a proxy class that delegates the method calling to all
 * the service objects. Getters and Setters will be used to retrieve the
 * connection(s).</li>
 * </ul>
 * See schema/org.archstudio.myxgen.jet.myxBrick.exsd file for the schema
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public enum TemplateType {

	NONE("none"), //
	DELEGATE("delegate"), //
	IMPLEMENTED_IN_BRICK("implementedInBrick"), //
	IMPLEMENTED_IN_DELEGATE("implementedInDelegate"), //
	DELEGATE_WITH_GETTERS("delegateWithGetters"), //
	IMPLEMENTED_IN_DELEGATE_WITH_GETTERS("implementedInDelegateWithGetters"), //
	DELEGATE_TO_MYX_REGISTRY("delegateToMyxRegistry");

	private String schemaDescription;

	private TemplateType(String schemaDescription) {
		this.schemaDescription = schemaDescription;
	}

	/**
	 * Returns the schema description string of the TemplateType
	 */
	public String getSchemaDescription() {
		return schemaDescription;
	}

	/**
	 * Returns TemplateType from the given schema description.
	 * 
	 * @param schemaDescription
	 * @return the TemplateType
	 * @exception IllegalArgumentException
	 *                if the given schema description is invalid.
	 */
	public static TemplateType fromSchemaDescription(String schemaDescription) {
		for (TemplateType t : TemplateType.values()) {
			if (t.getSchemaDescription().equals(schemaDescription)) {
				return t;
			}
		}
		throw new IllegalArgumentException("no enum for " + schemaDescription);
	}
}
