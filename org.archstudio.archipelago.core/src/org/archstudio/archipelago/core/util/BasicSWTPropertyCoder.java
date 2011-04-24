package org.archstudio.archipelago.core.util;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xarchadt.ObjRef;

public class BasicSWTPropertyCoder implements IPropertyCoder{

	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef, Object propertyValue){
		String type = null;
		String value = null;
		
		if(propertyValue instanceof Point){
			type = Point.class.getName();
			value = ((Point)propertyValue).x + "," + ((Point)propertyValue).y;
		}
		else if(propertyValue instanceof Rectangle){
			type = Rectangle.class.getName();
			value = ((Rectangle)propertyValue).x + "," + ((Rectangle)propertyValue).y + "," + ((Rectangle)propertyValue).width + "," + ((Rectangle)propertyValue).height;
		}
		else if(propertyValue instanceof RGB){
			type = RGB.class.getName();
			value = ((RGB)propertyValue).red + "," + ((RGB)propertyValue).green + "," + ((RGB)propertyValue).blue;
		}

		if((type != null) && (value != null)){
			AS.xarch.set(valueRef, "type", type);
			AS.xarch.set(valueRef, "data", value);
			return true;
		}
		
		return false;
	}
	
	public Object decode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef) throws PropertyDecodeException{
		String propertyType = (String)AS.xarch.get(valueRef, "type");
		if(propertyType == null){
			return null;
		}
		
		String data = (String)AS.xarch.get(valueRef, "data");
		if(data == null){
			return null;
		}
		
		if(propertyType.equals(Rectangle.class.getName())){
			try{
				String[] ss = data.split(",");
				if(ss.length != 4){
					throw new PropertyDecodeException("Can't decode rectangle: " + data);
				}
				Rectangle r = new Rectangle(0,0,0,0);
				r.x = Integer.parseInt(ss[0]);
				r.y = Integer.parseInt(ss[1]);
				r.width = Integer.parseInt(ss[2]);
				r.height = Integer.parseInt(ss[3]);
				return r;
			}
			catch(NumberFormatException nfe){
				throw new PropertyDecodeException("Can't decode rectangle: " + data, nfe);
			}
		}
		else if(propertyType.equals(Point.class.getName())){
			try{
				String[] ss = data.split(",");
				if(ss.length != 2){
					throw new PropertyDecodeException("Can't decode point: " + data);
				}
				Point p = new Point(0,0);
				p.x = Integer.parseInt(ss[0]);
				p.y = Integer.parseInt(ss[1]);
				return p;
			}
			catch(NumberFormatException nfe){
				throw new PropertyDecodeException("Can't decode point: " + data, nfe);
			}
		}
		else if(propertyType.equals(RGB.class.getName())){
			try{
				String[] ss = data.split(",");
				if(ss.length != 3){
					throw new PropertyDecodeException("Can't decode RGB: " + data);
				}
				RGB rgb = new RGB(0,0,0);
				rgb.red = Integer.parseInt(ss[0]);
				rgb.green = Integer.parseInt(ss[1]);
				rgb.blue = Integer.parseInt(ss[2]);
				return rgb;
			}
			catch(NumberFormatException nfe){
				throw new PropertyDecodeException("Can't decode RGB: " + data, nfe);
			}
		}
		return null;
	}
}
