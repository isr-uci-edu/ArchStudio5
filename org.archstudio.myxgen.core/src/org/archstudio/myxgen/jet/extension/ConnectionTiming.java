package org.archstudio.myxgen.jet.extension;

/**
 * Connection Timing of a (brick)Interface.
 * <ul>
 * <li>NONE: there is no requirement about when the brick can be connected to
 * others.</li>
 * <li>CONNECT_BEFORE_INIT: the (brick)Interface will be connected to others before its
 * initialization.</li>
 * <li>CONNECT_BEFORE_BEGIN: the (brick)Interface must be initialized first, then it
 * will be connected to others.</li>
 * </ul>
 * All of the "in" (brick)Interfaces should be CONNECT_BEFORE_INIT since there is no way to 
 * provide the service before init().
 * For "out" (brick)Interfaces, typically, CONNECT_BEFORE_INIT is used for a client, and
 * CONNECT_BEFORE_BEGIN is used for a server. However, there is not
 * always the case, for example, logging (brick)Interface work as a service but should
 * be CONNECT_BEFORE_INIT in order to capture all the events without a loss.
 * 
 * See schema/org.archstudio.myxgen.jet.myxBrick.exsd file for the schema
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public enum ConnectionTiming {
	NONE("anyTime", ""), //
	CONNECT_BEFORE_INIT("beforeInit", "requires"), //
	CONNECT_BEFORE_BEGIN("beforeBegin", "provides");

	/** The Connection timing attribute name specified in the schema*/
	private String schemaDescription;
	
	/** The Service Type for the conventional xadl2.0
	 *  This is used when full translation to the conventional xadl2.0 is needed.
	 */
	private String serviceType;

	private ConnectionTiming(String schemaDescription, String serviceType) {
		this.schemaDescription = schemaDescription;
		this.serviceType = serviceType;
	}

	/**
	 * Returns the schema description string of the ConnectionTiming
	 * @return the schema description
	 */
	public String getSchemaDescription() {
		return schemaDescription;
	}

	/**
	 * Returns ConnectionTiming from the given schema description.
	 * @param schemaDescription
	 * @return the ConnectionTiming
	 * @exception IllegalArgumentException if the given schema description is invalid.
	 */
	public static ConnectionTiming fromSchemaDescription(String schemaDescription) {
		for (ConnectionTiming ct : ConnectionTiming.values()) {
			if (ct.getSchemaDescription().equals(schemaDescription)) {
				return ct;
			}
		}
		throw new IllegalArgumentException("no enum for " + schemaDescription);
	}

	/**
	 * Returns the ConnectionTiming from the given service type. The service type
	 * is either "provides" or "requires" and is used in the conventional 
	 * xadl2.0. This method is only for translation purpose.
	 * @param serviceType
	 * @return
	 */
	public static ConnectionTiming fromServiceType(String serviceType) {
		for (ConnectionTiming ct : ConnectionTiming.values()) {
			if (ct.toServiceType().equals(serviceType)) {
				return ct;
			}
		}
		throw new IllegalArgumentException("no enum for " + serviceType);
	}

	/**
	 * Returns the service type of the ConnectionTiming. The service type
	 * is either "provides" or "requires" and is used in the conventional 
	 * xadl2.0. This method is only for translation purpose.
	 * @return
	 */
	public String toServiceType() {
		return serviceType;
	}

	//	public static ConnectionTiming getConnectionTiming(String ct) {
	//		if(ct == null) {
	//			return NA;
	//		}
	//
	//		if (ct.equals("connectBeforeInit")) {
	//			return CONNECT_BEFORE_INIT;
	//		} else if (ct.equals("connectAfterInit")) {
	//			return CONNECT_BEFORE_BEGIN;
	//		} else {
	//			return NA;
	//		}
	//	}

}
