package org.archstudio.bna.logics.hints;

public class EncodedValue implements IEncodedValue {

	String type;
	String data;

	public EncodedValue(String type, String data) {
		this.type = type;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
