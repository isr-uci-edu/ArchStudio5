package org.archstudio.bna.logics.hints.coders;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import org.archstudio.bna.logics.hints.EncodedValue;
import org.archstudio.bna.logics.hints.IEncodedValue;
import org.archstudio.bna.logics.hints.IPropertyCoder;
import org.archstudio.bna.logics.hints.PropertyDecodeException;

public class GeomPropertyCoder implements IPropertyCoder {

	@Override
	public IEncodedValue encode(IPropertyCoder masterCoder, Object value) {
		if (value instanceof Point2D) {
			Point2D v = (Point2D) value;
			String type = Point2D.class.getName();
			String data = "1," + Double.doubleToLongBits(v.getX()) + "," + Double.doubleToLongBits(v.getY());
			return new EncodedValue(type, data);
		}
		else if (value instanceof Rectangle2D) {
			Rectangle2D v = (Rectangle2D) value;
			String type = Rectangle2D.class.getName();
			String data = "1," + Double.doubleToLongBits(v.getX()) + "," + Double.doubleToLongBits(v.getY()) + ","
					+ Double.doubleToLongBits(v.getWidth()) + "," + Double.doubleToLongBits(v.getHeight());
			return new EncodedValue(type, data);
		}
		return null;
	}

	@Override
	public Object decode(IPropertyCoder masterCoder, IEncodedValue encodedValue) throws PropertyDecodeException {
		try {
			String type = encodedValue.getType();
			String data = encodedValue.getData();
			if (Point2D.class.getName().equals(type)) {
				String[] d = data.split(",");
				double x = Double.longBitsToDouble(Long.parseLong(d[1]));
				double y = Double.longBitsToDouble(Long.parseLong(d[2]));
				Point2D v = new Point2D.Double(x, y);
				return v;
			}
			else if (Rectangle2D.class.getName().equals(type)) {
				String[] d = data.split(",");
				double x = Double.longBitsToDouble(Long.parseLong(d[1]));
				double y = Double.longBitsToDouble(Long.parseLong(d[2]));
				double width = Double.longBitsToDouble(Long.parseLong(d[3]));
				double height = Double.longBitsToDouble(Long.parseLong(d[4]));
				Rectangle2D v = new Rectangle2D.Double(x, y, width, height);
				return v;
			}
		}
		catch (Throwable t) {
			throw new PropertyDecodeException(t);
		}
		return null;
	}
}
