package org.archstudio.bna.logics.hints.coders;

import org.archstudio.bna.logics.hints.EncodedValue;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

public class NativePropertyCoder implements IPropertyCoder {

	public IEncodedValue encode(IPropertyCoder masterCoder, Object value) {
		if (value instanceof java.lang.String) {
			return new EncodedValue("java.lang.String", (java.lang.String) value);
		}
		else if (value instanceof java.lang.Boolean) {
			return new EncodedValue("java.lang.Boolean", ((java.lang.Boolean) value).toString());
		}
		else if (value instanceof java.lang.Byte) {
			return new EncodedValue("java.lang.Byte", ((java.lang.Byte) value).toString());
		}
		else if (value instanceof java.lang.Short) {
			return new EncodedValue("java.lang.Short", ((java.lang.Short) value).toString());
		}
		else if (value instanceof java.lang.Character) {
			return new EncodedValue("java.lang.Character", ((java.lang.Character) value).toString());
		}
		else if (value instanceof java.lang.Integer) {
			return new EncodedValue("java.lang.Integer", ((java.lang.Integer) value).toString());
		}
		else if (value instanceof java.lang.Long) {
			return new EncodedValue("java.lang.Long", ((java.lang.Long) value).toString());
		}
		else if (value instanceof java.lang.Float) {
			return new EncodedValue("java.lang.Float", ((java.lang.Float) value).toString());
		}
		else if (value instanceof java.lang.Double) {
			return new EncodedValue("java.lang.Double", ((java.lang.Double) value).toString());
		}
		return null;
	}

	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();

			if (type.equals("java.lang.String")) {
				return data;
			}
			else if (type.equals("java.lang.Boolean")) {
				return Boolean.parseBoolean(data) ? Boolean.TRUE : Boolean.FALSE;
			}
			else if (type.equals("java.lang.Byte")) {
				return Byte.valueOf(Byte.parseByte(data));
			}
			else if (type.equals("java.lang.Short")) {
				return Short.valueOf(Short.parseShort(data));
			}
			else if (type.equals("java.lang.Character")) {
				return Character.valueOf(data.charAt(0));
			}
			else if (type.equals("java.lang.Integer")) {
				return Integer.valueOf(Integer.parseInt(data));
			}
			else if (type.equals("java.lang.Long")) {
				return Long.valueOf(Long.parseLong(data));
			}
			else if (type.equals("java.lang.Float")) {
				return Float.valueOf(Float.parseFloat(data));
			}
			else if (type.equals("java.lang.Double")) {
				return Double.valueOf(Double.parseDouble(data));
			}

			return null;
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}

	}
}
