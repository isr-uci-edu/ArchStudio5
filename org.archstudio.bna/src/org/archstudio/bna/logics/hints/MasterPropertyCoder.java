package org.archstudio.bna.logics.hints;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.bna.logics.hints.coders.CollectionPropertyCoder;
import org.archstudio.bna.logics.hints.coders.EnumPropertyCoder;
import org.archstudio.bna.logics.hints.coders.NativePropertyCoder;
import org.archstudio.bna.logics.hints.coders.SWTPropertyCoder;

public class MasterPropertyCoder implements IPropertyCoder {

	static MasterPropertyCoder singleton = new MasterPropertyCoder();

	List<IPropertyCoder> propertyCoders = new ArrayList<IPropertyCoder>();

	public MasterPropertyCoder() {
		propertyCoders.add(new NativePropertyCoder());
		propertyCoders.add(new CollectionPropertyCoder());
		propertyCoders.add(new EnumPropertyCoder());
		propertyCoders.add(new SWTPropertyCoder());
	}

	@Override
	public IEncodedValue encode(IPropertyCoder masterCoder, Object value) {
		if (value == null) {
			return new EncodedValue("null", "");
		}
		if (masterCoder == null) {
			masterCoder = this;
		}
		for (IPropertyCoder pc : propertyCoders) {
			IEncodedValue encodedValue = pc.encode(masterCoder, value);
			if (encodedValue != null) {
				return encodedValue;
			}
		}
		return null;
	}

	@Override
	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		if (encodedValue == null || "null".equals(encodedValue.getType()) || null == encodedValue.getType()
				|| null == encodedValue.getData()) {
			return null;
		}
		if (masterCoder == null) {
			masterCoder = this;
		}
		for (IPropertyCoder pc : propertyCoders) {
			Object value = pc.decode(masterCoder, encodedValue);
			if (value != null) {
				return value;
			}
		}
		return null;
	}

	public static MasterPropertyCoder getSingleton() {
		return singleton;
	}
}
