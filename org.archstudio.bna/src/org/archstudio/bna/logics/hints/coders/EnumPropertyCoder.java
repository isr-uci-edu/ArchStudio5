package org.archstudio.bna.logics.hints.coders;

import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

public class EnumPropertyCoder implements IPropertyCoder {

	@Override
	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value) {
		Class<?> c = value.getClass();
		if (c.isEnum()) {
			encodedValue.setType("enum:" + c.getName());
			encodedValue.setData(((Enum<?>) value).name());
			return true;
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();
			if (type.startsWith("enum:")) {
				@SuppressWarnings("rawtypes")
				Class<Enum> c = (Class<Enum>) Class.forName(type.substring(5));
				return Enum.valueOf(c, data);
			}
			return null;
		}
		catch (Throwable e) {
			throw new PropertyDecodeException(e);
		}
	}
}
