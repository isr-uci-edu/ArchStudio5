package org.archstudio.bna.logics.hints;

import org.archstudio.bna.logics.hints.IPropertyCoder.IEncodedValue;

public class BasicEncodedValue implements IEncodedValue {

	String type;
	String data;

	public BasicEncodedValue() {
		this(null, null);
	}

	public BasicEncodedValue(String type, String data) {
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

	@Override
	public int hashCode() {
		return type.hashCode() ^ data.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof IEncodedValue) {
			IEncodedValue ev = (IEncodedValue) o;
			return type.equals(ev.getType()) && data.equals(ev.getData());
		}
		return false;
	}

	@Override
	public String toString() {
		return "" + data + " : " + type;
	}
}
