package org.archstudio.ljm;

import java.util.HashMap;

@SuppressWarnings("rawtypes")
public class ClassArrayEncoder {

	private static boolean initDone = false;
	private static HashMap<String, Class<?>> nameMap = new HashMap<String, Class<?>>();

	public synchronized static void init() {
		if (initDone) {
			return;
		}
		nameMap.put("boolean", boolean.class);
		nameMap.put("byte", byte.class);
		nameMap.put("short", short.class);
		nameMap.put("char", char.class);
		nameMap.put("int", int.class);
		nameMap.put("long", long.class);
		nameMap.put("float", float.class);
		nameMap.put("double", double.class);
		nameMap.put("void", void.class);
		initDone = true;
	}

	public static String classToString(Class c) {
		return classArrayToStringArray(new Class[] { c })[0];
	}

	public static Class stringToClass(String s) throws ClassNotFoundException {
		return stringArrayToClassArray(new String[] { s })[0];
	}

	public static String[] classArrayToStringArray(Class[] arr) {
		String[] strings = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].isPrimitive()) {
				strings[i] = "$$PT" + arr[i].getName();
			}
			else {
				strings[i] = arr[i].getName();
			}
		}
		return strings;
	}

	public static Class[] stringArrayToClassArray(String[] arr) throws ClassNotFoundException {
		if (!initDone) {
			init();
		}
		Class[] classes = new Class[arr.length];
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i].startsWith("$$PT")) {
				classes[i] = Class.forName(arr[i]);
			}
			else {
				String realName = arr[i].substring(4);
				classes[i] = nameMap.get(realName);
				/*
				 * if(realName.equals("boolean")){ classes[i] = boolean.class; }
				 * else if(realName.equals("byte")){ classes[i] = byte.class; }
				 * else if(realName.equals("short")){ classes[i] = int.class; }
				 * else if(realName.equals("int")){ classes[i] = int.class; }
				 * else if(realName.equals("long")){ classes[i] = long.class; }
				 * else if(realName.equals("float")){ classes[i] = float.class;
				 * } else if(realName.equals("double")){ classes[i] = int.class;
				 * } else if(realName.equals("void")){ classes[i] = void.class;
				 * }
				 */
			}
		}
		return classes;
	}

}
