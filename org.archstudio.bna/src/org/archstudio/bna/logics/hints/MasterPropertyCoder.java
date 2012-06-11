package org.archstudio.bna.logics.hints;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.bna.logics.hints.coders.Draw2DPropertyCoder;
import org.archstudio.bna.logics.hints.coders.EnumPropertyCoder;
import org.archstudio.bna.logics.hints.coders.NativePropertyCoder;

public class MasterPropertyCoder implements IPropertyCoder {

	static MasterPropertyCoder singleton = new MasterPropertyCoder();

	List<IPropertyCoder> propertyCoders = new ArrayList<IPropertyCoder>();

	public MasterPropertyCoder() {
		propertyCoders.add(new NativePropertyCoder());
		propertyCoders.add(new EnumPropertyCoder());
		propertyCoders.add(new Draw2DPropertyCoder());
	}

	@Override
	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value) {
		if (encodedValue != null) {
			if (value == null) {
				encodedValue.setType("null");
				encodedValue.setData("");
				return true;
			}
			if (masterCoder == null) {
				masterCoder = this;
			}
			for (IPropertyCoder pc : propertyCoders) {
				if (pc.encode(masterCoder, encodedValue, value)) {
					return true;
				}
			}
		}
		return false;
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
