package org.archstudio.archipelago.core.util;

import java.lang.reflect.Array;
import java.util.List;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.archipelago.core.ArchipelagoUtils;
import org.archstudio.xarchadt.common.ObjRef;

public class ArrayPropertyCoder implements IPropertyCoder{

	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef, Object propertyValue){
		Class<?> c = propertyValue.getClass();
		if(c.isArray()){
			AS.xarch.set(valueRef, "type", ArchipelagoUtils.getClassName(c));
			
			Object[] arr = (Object[])propertyValue;
			for(int i = 0; i < arr.length; i++){
				ObjRef childValueRef = (ObjRef)AS.xarch.create(HintSupport.HINTS_FACTORY, "value");
				if(masterCoder.encode(masterCoder, AS, childValueRef, arr[i])){
					AS.xarch.add(valueRef, "value", childValueRef);
				}
			}
			return true;
		}
		return false;
	}

	public Object decode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef) throws PropertyDecodeException{
		String propertyType = (String)AS.xarch.get(valueRef, "type");
		if(propertyType == null){
			return null;
		}
		if(propertyType.endsWith("[]")){
			String innerPropertyType = propertyType.substring(0, propertyType.length() - 2);
			
			List<ObjRef> valueRefs = AS.xarch.getAll(valueRef, "value");
			try{
				Class<?> cc = HintSupport.getInstance().classForName(getRealClassName(innerPropertyType));
				Object[] arr = (Object[])Array.newInstance(cc, valueRefs.size());
				for(int i = 0; i < valueRefs.size(); i++){
					arr[i] = masterCoder.decode(masterCoder, AS, valueRefs.get(i));
				}
				return arr;
			}
			catch(ClassNotFoundException cnfe){
				throw new PropertyDecodeException("Can't decode array type: " + propertyType, cnfe);
			}
		}
		return null;
	}
	
	private static String getRealClassName(String name){
		if(name.endsWith("[]")){
			String internalName = getRealClassName(name.substring(0, name.length() - 2));
			if(internalName.startsWith("[")){
				return "[" + internalName;
			}
			else{
				return "[L" + getRealClassName(name.substring(0, name.length() - 2)) + ";";
			}
		}
		else return name;
	}


}
