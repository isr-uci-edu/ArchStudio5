package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xarchadt.common.ObjRef;

public class BasicPropertyCoder implements IPropertyCoder{

	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef, Object propertyValue){
		String type = null;
		String value = null;
		if(propertyValue instanceof java.lang.String){
			type = "java.lang.String";
			value = (java.lang.String)propertyValue;
		}
		else if(propertyValue instanceof java.lang.Boolean){
			type = "java.lang.Boolean";
			value = ((java.lang.Boolean)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Byte){
			type = "java.lang.Byte";
			value = ((java.lang.Byte)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Short){
			type = "java.lang.Short";
			value = ((java.lang.Short)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Character){
			type = "java.lang.Character";
			value = ((java.lang.Character)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Integer){
			type = "java.lang.Integer";
			value = ((java.lang.Integer)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Long){
			type = "java.lang.Long";
			value = ((java.lang.Long)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Float){
			type = "java.lang.Float";
			value = ((java.lang.Float)propertyValue).toString();
		}
		else if(propertyValue instanceof java.lang.Double){
			type = "java.lang.Double";
			value = ((java.lang.Double)propertyValue).toString();
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
		
		try{
			if(propertyType.equals("java.lang.String")){
				return data;
			}
			else if(propertyType.equals("java.lang.Boolean")){
				return Boolean.parseBoolean(data);
			}
			else if(propertyType.equals("java.lang.Byte")){
				return Byte.parseByte(data);
			}
			else if(propertyType.equals("java.lang.Short")){
				return Short.parseShort(data);
			}
			else if(propertyType.equals("java.lang.Character")){
				return data.charAt(0);
			}
			else if(propertyType.equals("java.lang.Integer")){
				return Integer.parseInt(data);
			}
			else if(propertyType.equals("java.lang.Long")){
				return Long.parseLong(data);
			}
			else if(propertyType.equals("java.lang.Float")){
				return Float.parseFloat(data);
			}
			else if(propertyType.equals("java.lang.Double")){
				return Double.parseDouble(data);
			}
		}
		catch(NumberFormatException nfe){
			throw new PropertyDecodeException("Number format error decoding basic property value.", nfe);
		}
		return null;
	}
}
