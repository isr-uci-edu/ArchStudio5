package org.archstudio.bna.logics.hints.coders;

import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public class Draw2DPropertyCoder implements IPropertyCoder {

	public boolean encode(IPropertyCoder masterCoder, IEncodedValue encodedValue, Object value) {
		if (value instanceof Point) {
			Point v = (Point) value;
			encodedValue.setType(Point.class.getName());
			encodedValue.setData(v.x + "," + v.y);
			return true;
		}
		else if (value instanceof Rectangle) {
			Rectangle v = (Rectangle) value;
			encodedValue.setType(Rectangle.class.getName());
			encodedValue.setData(v.x + "," + v.y + "," + v.width + "," + v.height);
			return true;
		}
		return false;
	}

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
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}
		return null;
	}
}
