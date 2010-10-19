package org.archstudio.bna.facets;

public interface IHasStickyEndpoints extends IHasMutableEndpoints {

	public static final String ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME = "&endpoint1StuckToThingID";
	public static final String ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME = "&endpoint2StuckToThingID";

	public String getEndpoint1StuckToThingID();

	public String getEndpoint2StuckToThingID();
}
