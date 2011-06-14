package org.archstudio.bna.logics.hints;

public interface IPropertyCoder {

	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value);

	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException;

}
