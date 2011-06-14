package org.archstudio.bna.logics.hints.coders;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

public class ArrayPropertyCoder implements IPropertyCoder {

	private static void escape(StringBuffer d, String s) {
		if (s == null) {
			d.append("null");
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case ',':
				d.append("\\c");
				break;
			case '{':
				d.append("\\(");
				break;
			case '}':
				d.append("\\)");
				break;
			case '\\':
				d.append("\\\\");
				break;
			default:
				d.append(ch);
				break;
			}
		}
	}

	private static String unescape(String s) {
		if (s == null) {
			return null;
		}
		StringBuffer d = new StringBuffer(s.length());
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch == '\\') {
				i++;
				ch = s.charAt(i);
				switch (ch) {
				case 'c':
					d.append(',');
					break;
				case '(':
					d.append('{');
					break;
				case ')':
					d.append('}');
					break;
				case '\\':
					d.append('\\');
					break;
				default:
					d.append('\\').append(ch);
					break;
				}
			}
			else {
				d.append(ch);
			}
		}
		return d.toString();
	}

	static class EncodingWrapper implements IEncodedValue {

		StringBuffer type;
		StringBuffer data;

		public EncodingWrapper(StringBuffer type, StringBuffer data) {
			this.type = type;
			this.data = data;
		}

		@Override
		public void setType(String t) {
			escape(this.type, t);
		}

		@Override
		public void setData(String d) {
			escape(this.data, d);
		}

		@Override
		public String getType() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getData() {
			throw new UnsupportedOperationException();
		}
	}

	void encode(StringBuffer type, StringBuffer data, IEncodedValue wrapper, IPropertyCoder masterCoder, Object value) {
		Class<?> c = value.getClass();
		if (c.isArray()) {
			int length = Array.getLength(value);
			type.append('{').append(c.getName()).append(',').append(length);
			data.append('{');
			for (int i = 0; i < length; i++) {
				type.append(',');
				if (i > 0) {
					data.append(',');
				}
				Object v = Array.get(value, i);
				if (v == null || !v.getClass().isArray()) {
					masterCoder.encode(masterCoder, wrapper, v);
				}
				else {
					encode(type, data, wrapper, masterCoder, v);
				}
			}
			data.append('}');
			type.append('}');
		}
	}

	String[] split(String s) {
		List<String> l = new ArrayList<String>();
		int b = 1;
		int d = 0;
		for (int i = 1; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (i == s.length() - 1) {
				ch = ',';
			}
			switch (ch) {
			case '{':
				d++;
				break;
			case '}':
				d--;
				break;
			case ',':
				if (d == 0) {
					l.add(s.substring(b, i));
					b = i + 1;
				}
				break;
			}
		}
		return l.toArray(new String[l.size()]);
	}

	Object decode(IPropertyCoder masterCoder, String type, String data) throws PropertyDecodeException {
		try {
			String[] ts = split(type);
			String[] ds = split(data);
			// System.err.println(Arrays.asList(ds) + " : " + Arrays.asList(ts));

			String cType = ts[0];
			int length = Integer.parseInt(ts[1]);
			Object value;
			value = Array.newInstance(Class.forName(cType).getComponentType(), length);

			BasicEncodedValue bev = new BasicEncodedValue();
			for (int i = 0; i < length; i++) {
				String sts = ts[i + 2];
				String sds = ds[i];
				Object o;
				if (sts.startsWith("{")) {
					o = decode(masterCoder, sts, sds);
				}
				else {
					bev.setType(unescape(sts));
					bev.setData(unescape(sds));
					o = masterCoder.decode(masterCoder, bev);
				}
				// System.err.println("" + i + " => " + o);
				if (o != null) {
					Array.set(value, i, o);
				}
			}

			return value;
		}
		catch (NegativeArraySizeException e) {
			throw new PropertyDecodeException(e);
		}
		catch (ClassNotFoundException e) {
			throw new PropertyDecodeException(e);
		}
	}

	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value) {
		Class<?> c = value.getClass();
		if (c.isArray()) {
			StringBuffer type = new StringBuffer();
			StringBuffer data = new StringBuffer();
			encode(type, data, new EncodingWrapper(type, data), masterCoder, value);
			encodedValue.setType(type.toString());
			encodedValue.setData(data.toString());
			return true;
		}
		return false;
	}

	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();
			if (type.startsWith("{")) {
				return decode(masterCoder, type, data);
			}
			return null;
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}
	}
}
