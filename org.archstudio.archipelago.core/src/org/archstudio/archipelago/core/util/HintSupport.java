package org.archstudio.archipelago.core.util;

import java.util.HashSet;
import java.util.Set;

import org.archstudio.archipelago.core.ArchipelagoServices;
import org.archstudio.xadl.common.XadlUtils;
import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.common.ObjRef;

public class HintSupport implements IPropertyCoder{
	protected static HintSupport __instance = null;
	
	protected HintSupport(){}
	
	public ObjRef getOrCreateHintsExtension(ArchipelagoServices AS, ObjRef ref){
		ObjRef hintsExtRef = getHintsExtension(AS, ref);
		if(hintsExtRef == null){
			hintsExtRef = XadlUtils.create(AS.xarch, Hints_3_0Package.Literals.HINTS_EXTENSION);
			AS.xarch.add(ref, "ext", hintsExtRef);
		}
		return hintsExtRef;
	}
	
	public ObjRef getHintsExtension(ArchipelagoServices AS, ObjRef ref){
		ObjRef hintsExtRef = XadlUtils.getExt(AS.xarch, ref, Hints_3_0Package.eNS_URI, "HintsExtension");
		return hintsExtRef;
	}
	
	public ObjRef findHintByName(ArchipelagoServices AS, ObjRef eltRef, String name) {
		ObjRef hintsExtensionRef = getHintsExtension(AS, eltRef);
		if(hintsExtensionRef == null){
			return null;
		}
		
		ObjRef hintRef = null;
		for(ObjRef hintRefToCheck : AS.xarch.getAll(hintsExtensionRef, "hint")){
			String hintRefToCheckName = (String)AS.xarch.get(hintRefToCheck, "name");
			if((hintRefToCheckName != null) && (hintRefToCheckName.equals(name))){
				hintRef = hintRefToCheck;
				break;
			}
		}
		return hintRef;
	}
	
	public void writeProperty(ArchipelagoServices AS, ObjRef eltRef, String name, Object value){
		if(value == null) return;
		
		ObjRef hintRef = findHintByName(AS, eltRef, name);
		if(hintRef == null){
			ObjRef hintsExtensionRef = getOrCreateHintsExtension(AS, eltRef);
			hintRef = XadlUtils.create(AS.xarch, Hints_3_0Package.Literals.HINT);
			AS.xarch.set(hintRef, "name", name);
			AS.xarch.add(hintsExtensionRef, "hint", hintRef);
		}
		ObjRef valueRef = XadlUtils.create(AS.xarch, Hints_3_0Package.Literals.VALUE);

		if(encode(this, AS, valueRef, value)){
			AS.xarch.set(hintRef, "value", valueRef);
		}
	}
	
	public Object readProperty(ArchipelagoServices AS, ObjRef eltRef, String name){
		ObjRef hintsExtensionRef = getHintsExtension(AS, eltRef);
		if(hintsExtensionRef == null){
			return null;
		}
		
		ObjRef hintRef = findHintByName(AS, eltRef, name);
		if(hintRef == null){
			return null;
		}
		ObjRef valueRef = (ObjRef)AS.xarch.get(hintRef, "value");
		if(valueRef == null){
			return null;
		}

		try{
			return decode(this, AS, valueRef);
		}
		catch(PropertyDecodeException pde){
			return null;
		}
	}

	public Class<?> classForName(String name) throws ClassNotFoundException{
		try{
			Class<?> c = Class.forName(name);
			return c;
		}
		catch(ClassNotFoundException cnfe){
			for(ClassLoader cl : classLoaders){
				try{
					Class<?> c = Class.forName(name, true, cl);
					return c;
				}
				catch(ClassNotFoundException cnfe2){
				}
			}
			for(IPropertyCoder pc : propertyCoders){
				try{
					Class<?> c = Class.forName(name, true, pc.getClass().getClassLoader());
					return c;
				}
				catch(ClassNotFoundException cnfe2){
				}
			}
			throw cnfe;
		}
	}
	
	protected Set<ClassLoader> classLoaderSet = new HashSet<ClassLoader>();
	protected ClassLoader[] classLoaders = new ClassLoader[0];
	
	public void registerClassLoader(ClassLoader cl){
		classLoaderSet.add(cl);
		classLoaders = classLoaderSet.toArray(new ClassLoader[classLoaderSet.size()]);
	}
	
	public void unregisterClassloader(ClassLoader cl){
		classLoaderSet.remove(cl);
		classLoaders = classLoaderSet.toArray(new ClassLoader[classLoaderSet.size()]);
	}
	
	protected Set<IPropertyCoder> propertyCoderSet = new HashSet<IPropertyCoder>();
	protected IPropertyCoder[] propertyCoders = new IPropertyCoder[0];
	
	public void registerPropertyCoder(IPropertyCoder coder){
		propertyCoderSet.add(coder);
		propertyCoders = propertyCoderSet.toArray(new IPropertyCoder[propertyCoderSet.size()]);
	}
	
	public void unregisterPropertyCoder(IPropertyCoder coder){
		propertyCoderSet.remove(coder);
		propertyCoders = propertyCoderSet.toArray(new IPropertyCoder[propertyCoderSet.size()]);
	}
	
	public boolean encode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef propertyValueRef, Object propertyValue){
		for(IPropertyCoder pc : propertyCoders){
			if(pc.encode(this, AS, propertyValueRef, propertyValue)){
				return true;
			}
		}
		return false;
	}
	
	public Object decode(IPropertyCoder masterCoder, ArchipelagoServices AS, ObjRef propertyValueRef) throws PropertyDecodeException{
		for(IPropertyCoder pc : propertyCoders){
			Object o = pc.decode(this, AS, propertyValueRef);
			if(o != null){
				return o;
			}
		}
		return null;
	}
	
	public static HintSupport getInstance(){
		if(__instance == null){
			__instance = new HintSupport();
			__instance.registerPropertyCoder(new BasicPropertyCoder());
			__instance.registerPropertyCoder(new BasicSWTPropertyCoder());
			__instance.registerPropertyCoder(new ArrayPropertyCoder());
			__instance.registerPropertyCoder(new EnumPropertyCoder());
		}
		return __instance;
	}
}
