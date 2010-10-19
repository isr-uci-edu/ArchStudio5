package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.BNAUtils;

public class ThingRefData {
	private String thingID;
	private String propertyName;

	public ThingRefData(String thingID, String propertyName) {
		this.thingID = thingID;
		this.propertyName = propertyName;
	}

	public String getThingID() {
		return thingID;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public int hashCode() {
		int hc1 = 0;
		int hc2 = 0;
		if (thingID != null)
			hc1 = thingID.hashCode();
		if (propertyName != null)
			hc2 = propertyName.hashCode();
		return hc1 ^ hc2;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}
		return BNAUtils.nulleq(thingID, ((ThingRefData) obj).thingID) && BNAUtils.nulleq(propertyName, ((ThingRefData) obj).propertyName);
	}

}
