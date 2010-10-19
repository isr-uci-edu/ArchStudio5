package org.archstudio.archipelago.core.util;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xarchadt.common.ObjRef;

public interface IPropertyCoder{
	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef, Object propertyValue);
	public Object decode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef valueRef) throws PropertyDecodeException;
	
}
