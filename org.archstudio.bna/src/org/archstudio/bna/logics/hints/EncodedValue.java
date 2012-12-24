package org.archstudio.bna.logics.hints;

public class EncodedValue implements IEncodedValue {

	String type;
	String data;

	public EncodedValue(String type, String data) {
		this.type = type;
		this.data = data;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

}
