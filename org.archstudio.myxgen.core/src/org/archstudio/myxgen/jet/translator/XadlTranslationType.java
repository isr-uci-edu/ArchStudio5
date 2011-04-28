package org.archstudio.myxgen.jet.translator;

/**
 * Translation type
 * <ul>
 * <li>FULL_CONVERSION: ComponentType, Signatures, Interfaces and InterfaceTypes will be added into XADL. 
 * Conventional.
 * </li>
 * <li>INTERFACE_ONLY: Interface will be added into XADDL.
 * </li>
 * </ul>
 * @author Nobu Takeo
 *
 */
public enum XadlTranslationType {
	FULL_CONVERSION,
	INTERFACE_ONLY
}
