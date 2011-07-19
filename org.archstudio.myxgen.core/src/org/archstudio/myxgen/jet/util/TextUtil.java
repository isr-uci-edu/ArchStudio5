package org.archstudio.myxgen.jet.util;

public class TextUtil {

	public static String getClassPart(String javaFQClass) {
		if (javaFQClass == null) {
			return null;
		}
		int index = javaFQClass.lastIndexOf('.');
		String className = javaFQClass.substring(index + 1);
		return className;
	}

	/**
	 * Returns the package name from the fully qualified class name. For
	 * example, "package.name" will be returned from "package.name.Foo".
	 * 
	 * @param javaFQClass
	 * @return
	 */
	public static String getPackagePart(String javaFQClass) {
		if (javaFQClass == null) {
			return null;
		}
		int index = javaFQClass.lastIndexOf('.');
		String packageName = javaFQClass.substring(0, index);
		return packageName;
	}

	/**
	 * Creates a upper case hyphenated word. For example, "XARCH_FLAT_EVENTS"
	 * will be created from "xarchFlatEvents".
	 * 
	 * @param str
	 * @return
	 */
	public static String upperHyphenate(String str) {
		StringBuilder sb = new StringBuilder();

		char[] cs = str.toCharArray();
		for (char c : cs) {
			if (Character.isUpperCase(c)) {
				sb.append('_');
			}
			sb.append(Character.toUpperCase(c));
		}

		return sb.toString();
	}
}
