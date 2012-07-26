package org.archstudio.bna.logics.hints;

public interface IPropertyCoder {

	public IEncodedValue encode(IPropertyCoder masterCoder, Object value);

	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException;

}
