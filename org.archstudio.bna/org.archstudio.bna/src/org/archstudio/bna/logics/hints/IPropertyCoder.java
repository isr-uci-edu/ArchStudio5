package org.archstudio.bna.logics.hints;

public interface IPropertyCoder {

	public interface IEncodedValue {

		public String getType();

		public void setType(String type);

		public String getData();

		public void setData(String data);
	}

	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value);

	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException;
}
