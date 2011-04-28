package org.archstudio.myxgen.jet.extension;

import org.archstudio.myx.fw.EMyxInterfaceDirection;

/**
 * Directions of a (brick)Interface.
 * <ul>
 * <li>IN : a service object will be grabbed by other bricks through the (brick)Interface.</li>
 * <li>OUT_SINGLE : the brick will grab a service object of another brick through through the (brick)Interface.</li>
 * <li>OUT_MULTI : the brick will grab a collection of service object of another brick through through the (brick)Interface.</li>
 * </ul>
 * 
 * A service object of IN (brick)Interface will be used through getServiceObject().
 * A service object of OUT (brick)Interface will be received through interfaceConnected().
 * 
 * See schema/org.archstudio.myxgen.jet.myxBrick.exsd file for the schema
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public enum Direction {
	IN("inSingleServiceObject", EMyxInterfaceDirection.IN), //
	OUT_SINGLE("outSingleServiceObject", EMyxInterfaceDirection.OUT), //
	OUT_MULTI("outMultipleServiceObjects", EMyxInterfaceDirection.OUT);

	/** The Direction attribute name specified in the schema*/
	private String schemaDescription;
	
	/** The EMyInterfaceDirection for the myx2.fw*/
	private EMyxInterfaceDirection eMyxInterfaceDirection;

	private Direction(String schemaDescription, EMyxInterfaceDirection myxInterfaceDirection) {
		this.schemaDescription = schemaDescription;
		this.eMyxInterfaceDirection = myxInterfaceDirection;
	}

	/**
	 * Returns the schema description string of the Direction
	 * @return the schema description
	 */
	public String getSchemaDescription() {
		return schemaDescription;
	}

	/**
	 * Returns Direction from the given schema description.
	 * @param schemaDescription
	 * @return the Direction
	 * @exception IllegalArgumentException if the given schema description is invalid.
	 */
	public static Direction fromSchemaDescription(String schemaDescription) {
		for (Direction d : Direction.values()) {
			if (d.getSchemaDescription().equals(schemaDescription)) {
				return d;
			}
		}
		throw new IllegalArgumentException("no enum for " + schemaDescription);
	}

	/**
	 * Returns EMyxInterfaceDirection of this Direction
	 * @return EMyxInterfaceDirection
	 */
	public EMyxInterfaceDirection toEMyxInterfaceDirection() {
		return eMyxInterfaceDirection;
	}
	
//	public static Direction getDirection(String dir) {
//		if(dir == null){
//			return NONE;
//		}
//		if (dir.equals("in")) {
//			return IN;
//		} else if (dir.equals("out (single)")) {
//			return OUT_SINGLE;
//		} else if (dir.equals("out (multiple)")) {
//			return OUT_MULTI;
//		} else {
//			return NONE;
//		}
//	}
//	public static void main(String[] args) {
//		System.out.println(Direction.fromSchemaDescription("in"));
//
//	}
}
