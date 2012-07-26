package org.archstudio.bna.logics.hints.coders;

import org.archstudio.bna.logics.hints.EncodedValue;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class SWTPropertyCoder implements IPropertyCoder {

	@Override
	public IEncodedValue encode(IPropertyCoder masterCoder, Object value) {
		if (value instanceof Point) {
			Point v = (Point) value;
			String type = Point.class.getName();
			String data = v.x + "," + v.y;
			return new EncodedValue(type, data);
		}
		else if (value instanceof Rectangle) {
			Rectangle v = (Rectangle) value;
			String type = Rectangle.class.getName();
			String data = v.x + "," + v.y + "," + v.width + "," + v.height;
			return new EncodedValue(type, data);
		}
		else if (value instanceof RGB) {
			RGB v = (RGB) value;
			String type = RGB.class.getName();
			String data = v.red + "," + v.green + "," + v.blue;
			return new EncodedValue(type, data);
		}
		return null;
	}

	@Override
	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();
			if (Rectangle.class.getName().equals(type)) {
				String[] d = data.split(",");
				Rectangle v = new Rectangle(0, 0, 0, 0);
				v.x = Integer.parseInt(d[0]);
				v.y = Integer.parseInt(d[1]);
				v.width = Integer.parseInt(d[2]);
				v.height = Integer.parseInt(d[3]);
				return v;
			}
			else if (Point.class.getName().equals(type)) {
				String[] d = data.split(",");
				Point v = new Point(0, 0);
				v.x = Integer.parseInt(d[0]);
				v.y = Integer.parseInt(d[1]);
				return v;
			}
			else if (RGB.class.getName().equals(type)) {
				String[] d = data.split(",");
				RGB v = new RGB(0, 0, 0);
				v.red = Integer.parseInt(d[0]);
				v.green = Integer.parseInt(d[1]);
				v.blue = Integer.parseInt(d[2]);
				return v;
			}
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}
		return null;
	}
}
