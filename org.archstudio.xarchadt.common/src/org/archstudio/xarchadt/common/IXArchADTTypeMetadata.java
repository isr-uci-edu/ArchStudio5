package org.archstudio.xarchadt.common;

import java.util.Map;

public interface IXArchADTTypeMetadata extends java.io.Serializable {

	public String getNsURI();

	public String getNsPrefix();

	public String getTypeName();

	public Map<String, IXArchADTFeature> getFeatures();
	
	/**
	 * @deprecated Prevents use of non-generated models, i.e., when there is no underlying Java class.
	 */
	@Deprecated
	public Class<?> getElementClass();

	/**
	 * @deprecated Prevents use of non-generated models, i.e., when there is no underlying Java class.
	 */
	@Deprecated
	public String getInstanceTypeName();

	/**
	 * @deprecated Prevents use of non-generated models, i.e., when there is no underlying Java class.
	 */
	@Deprecated
	public Class<?> getInstanceClass();
}
