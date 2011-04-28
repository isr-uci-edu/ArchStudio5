package org.archstudio.myxgen.jet.util;

/**
 * Utility methods for text conversion
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class TextUtil {

	/**
	 * Returns the class name from the fully qualified class name. For example,
	 * "Foo" will be returned from "package.name.Foo".
	 * 
	 * @param javaFQClass
	 *            the fully qualified class name
	 * @return
	 */
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

	/**
	 * Creates the event class name from the signature name. The first letter
	 * will be capitalized and the last letter will be deleted. If the input is
	 * a single letter, a capitalized letter of the input will be returned. For
	 * example, "XarchFlatEvent" will be created from "xarchFlatEvents".
	 * 
	 * @param sig
	 * @return
	 */
	public static String toClassName(String sig) {

		if (sig == null || sig.length() == 0) {
			return sig;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(sig.charAt(0)));
		if (sig.length() > 1 && sig.charAt(sig.length() - 1) == 's') {
			sb.append(sig.substring(1, sig.length() - 1));
		}
		return sb.toString();
	}

	/**
	 * Makes the first character of the string upper case.
	 * 
	 * @param str
	 * @return
	 */
	public static String upperFirstChar(String str) {

		if (str == null || str.length() == 0) {
			return str;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.substring(1));

		return sb.toString();

	}
	
	/**
	 * Makes the first character of the string lower case.
	 * 
	 * @param str
	 * @return
	 */
	public static String lowerFirstChar(String str) {
		if (str == null || str.length() == 0) {
			return str;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(Character.toLowerCase(str.charAt(0)));
		sb.append(str.substring(1));

		return sb.toString();
	}
	
	/**
	 * Converts the given string to a name suitable for a java variable
	 * "package.name" -> "packageName"
	 * @param str
	 * @return
	 */
	public static String toVariableName(String str) {
		assert str != null;
		
		String[] parts = str.split("[.\\s]");
		
		StringBuilder sb = new StringBuilder();
		boolean firstString = true;
		for(String part : parts) {
			if(part.length() == 0) {
				continue;
			}
			
			if(firstString) {
				sb.append(lowerFirstChar(part));
				firstString = false;
			} else {
				sb.append(upperFirstChar(part));
			}
		}
		
		if(sb.length() == 0){
			throw new IllegalArgumentException("Input string \"" + str + "\" consists of only '.' characters.");
		}
		
		return sb.toString();
	}


	public static void main(String[] args) {
//		String test = "aaaBbbCcc";
//		System.out.println(test);
//		System.out.println(TextUtil.upperHyphenate(test));
//		System.out.println(TextUtil.sigToClassName(test));
//		System.out.println(TextUtil.sigToClassName("s"));
//		System.out.println(TextUtil.sigToClassName(""));
		System.out.println(TextUtil.toVariableName("....edu.  uc i.isr"));
		System.out.println(TextUtil.toVariableName(" T e s t"));
		System.out.println(TextUtil.toVariableName(".test"));
//		System.out.println(TextUtil.periodToUpperChar("."));
	}
}
