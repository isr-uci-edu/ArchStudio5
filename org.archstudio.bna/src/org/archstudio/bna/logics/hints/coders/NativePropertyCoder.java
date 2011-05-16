package org.archstudio.bna.logics.hints.coders;

import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

public class NativePropertyCoder implements IPropertyCoder {

	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value) {
		if (value instanceof java.lang.String) {
			encodedValue.setType("java.lang.String");
			encodedValue.setData((java.lang.String) value);
			return true;
		}
		else if (value instanceof java.lang.Boolean) {
			encodedValue.setType("java.lang.Boolean");
			encodedValue.setData(((java.lang.Boolean) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Byte) {
			encodedValue.setType("java.lang.Byte");
			encodedValue.setData(((java.lang.Byte) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Short) {
			encodedValue.setType("java.lang.Short");
			encodedValue.setData(((java.lang.Short) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Character) {
			encodedValue.setType("java.lang.Character");
			encodedValue.setData(((java.lang.Character) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Integer) {
			encodedValue.setType("java.lang.Integer");
			encodedValue.setData(((java.lang.Integer) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Long) {
			encodedValue.setType("java.lang.Long");
			encodedValue.setData(((java.lang.Long) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Float) {
			encodedValue.setType("java.lang.Float");
			encodedValue.setData(((java.lang.Float) value).toString());
			return true;
		}
		else if (value instanceof java.lang.Double) {
			encodedValue.setType("java.lang.Double");
			encodedValue.setData(((java.lang.Double) value).toString());
			return true;
		}
		return false;
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
