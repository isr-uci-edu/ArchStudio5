package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.xarchadt.ObjRef;

public class EnumPropertyCoder implements IPropertyCoder{

	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef hintRef, Object propertyValue){
		Class<?> c = propertyValue.getClass();
		if(c.isEnum()){
			AS.xarch.set(hintRef, "type", ArchipelagoUtils.getClassName(c) + "[E]");
			AS.xarch.set(hintRef, "data", propertyValue.toString());
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Object decode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef hintRef) throws PropertyDecodeException{
		String propertyType = (String)AS.xarch.get(hintRef, "type");
		if(propertyType == null){
			return null;
		}
		if(propertyType.endsWith("[E]")){
			String innerPropertyType = propertyType.substring(0, propertyType.length() - 3);
			
			String data = (String)AS.xarch.get(hintRef, "data");
			if(data != null){
				try{
					Class cc = HintSupport.getInstance().classForName(innerPropertyType);
					return Enum.valueOf(cc, data);
				}
				catch(ClassNotFoundException cnfe){
					throw new PropertyDecodeException("Can't decode array type: " + propertyType, cnfe);
				}
			}
		}
		return null;
	}
}
