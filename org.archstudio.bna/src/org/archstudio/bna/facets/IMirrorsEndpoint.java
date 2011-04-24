package org.archstudio.bna.facets;

public interface IMirrorsEndpoint extends IHasMutableAnchorPoint {
	public static final String ENDPOINT_MASTER_THING_ID_PROPERTY_NAME = "&endpointMasterThingID";
	public static final String ENDPOINT_NUMBER_PROPERTY_NAME = "endpointNumber";

	public String getEndpointMasterThingID();

	public int getEndpointNumber();
}
