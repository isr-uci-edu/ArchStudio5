package org.archstudio.bna.logics.hints.coders;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.archstudio.bna.logics.hints.EncodedValue;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class CollectionPropertyCoder implements IPropertyCoder {

	private static final String escape(String s) {
		StringBuffer sb = new StringBuffer(s.length() * 2);
		for (char ch : s.toCharArray()) {
			switch (ch) {
			case '\\':
				sb.append("\\\\");
				break;
			case ';':
				sb.append("\\,");
				break;
			case '{':
				sb.append("\\(");
				break;
			case '}':
				sb.append("\\)");
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	private static final String unescape(String s) {
		StringBuffer sb = new StringBuffer(s.length());
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '\\':
				char ch2 = s.charAt(++i);
				switch (ch2) {
				case ',':
					sb.append(';');
					break;
				case '(':
					sb.append('{');
					break;
				case ')':
					sb.append('}');
					break;
				default:
					sb.append(ch2);
					break;
				}
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	public CollectionPropertyCoder() {
	}

	private IEncodedValue encode(IPropertyCoder masterCoder, String type, Iterable<?> values) {
		List<String> encodedTypes = Lists.newArrayList();
		List<String> encodedValues = Lists.newArrayList();

		for (Object value : values) {
			IEncodedValue ev = masterCoder.encode(masterCoder, value);
			encodedTypes.add(escape(ev.getType()));
			encodedValues.add(escape(ev.getData()));
		}

		String encodedType = Joiner.on(";").join(encodedTypes);
		String encodedData = Joiner.on(";").join(encodedValues);

		return new EncodedValue(type + "{" + encodedType + "}", encodedData);
	}

	@Override
	public IEncodedValue encode(IPropertyCoder masterCoder, Object value) {
		if (value.getClass().isArray()) {
			return encode(masterCoder, "Array", Arrays.asList((Object[]) value));
		}
		if (value instanceof List) {
			return encode(masterCoder, "List", (List<?>) value);
		}
		if (value instanceof Set) {
			return encode(masterCoder, "Set", (List<?>) value);
		}
		return null;
	}

	private Iterable<Object> decode(IPropertyCoder masterCoder, String type, String data)
			throws PropertyDecodeException {
		String[] splitTypes = type.length() > 0 ? type.split(";") : new String[0];
		String[] splitValues = data.length() > 0 ? data.split(";") : new String[0];

		List<Object> values = Lists.newArrayList();

		for (int i = 0; i < splitTypes.length && i < splitValues.length; i++) {
			String unescapedType = unescape(splitTypes[i]);
			String unescapedValue = unescape(splitValues[i]);
			IEncodedValue encodedValue = new EncodedValue(unescapedType, unescapedValue);
			values.add(masterCoder.decode(masterCoder, encodedValue));
		}

		return values;
	}

	@Override
	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();
			if (type.startsWith("Array{")) {
				return Lists.newArrayList(decode(masterCoder, type.substring(6, type.length() - 1), data)).toArray();
			}
			if (type.startsWith("List{")) {
				return Lists.newArrayList(decode(masterCoder, type.substring(5, type.length() - 1), data));
			}
			if (type.startsWith("Set{")) {
				return Sets.newHashSet(decode(masterCoder, type.substring(4, type.length() - 1), data));
			}
			return null;
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}
	}

}
