package org.archstudio.sysutils;

public class ArrayUtils {

	public static boolean equals(Object[] arr1, Object[] arr2) {
		return java.util.Arrays.equals(arr1, arr2);
		/*
		 * if(arr1.length != arr2.length){ return false; } for(int i = 0; i < arr1.length; i++){ if((arr1[i] == null) &&
		 * (arr2[i] == null)){ continue; } if((arr1[i] == null) || (arr2[i] == null)){ return false; }
		 * if(!arr1[i].equals(arr2[i])){ return false; } } return true;
		 */
	}

	public static int arrayHashCode(Object[] arr) {
		int hc = arr.length;
		for (Object element : arr) {
			if (element == null) {
				hc ^= 0;
			}
			else {
				hc ^= element.hashCode();
			}
		}
		return hc;
	}

	public static String arrayToString(byte[] array) {
		if (array == null) {
			return "null";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("Array[elementType=\"");
		sb.append("byte");
		sb.append("\", length=");
		sb.append(array.length);
		sb.append("]{");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i != array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public static String arrayToString(int[] array) {
		if (array == null) {
			return "null";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("Array[elementType=\"");
		sb.append("int");
		sb.append("\", length=");
		sb.append(array.length);
		sb.append("]{");
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i != array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public static String arrayToString(Object[] array) {
		if (array == null) {
			return "null";
		}

		Class<?> c = array.getClass();
		Class<?> t = c.getComponentType();
		boolean tIsArray = t.isArray();

		StringBuffer sb = new StringBuffer();
		sb.append("Array[elementType=\"");
		sb.append(t.getName());
		sb.append("\", length=");
		sb.append(array.length);
		sb.append("]{");
		for (int i = 0; i < array.length; i++) {
			if (tIsArray) {
				sb.append(arrayToString((Object[]) array[i]));
			}
			else {
				sb.append(array[i]);
			}
			if (i != array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @param array
	 *            the array to join
	 * @param delimiter
	 *            the string used to separate elements
	 * @return a string created by converting each element of the array to a string, separated by the given delimiter
	 */
	public static String join(Object[] array, String delimiter) {
		if (array == null) {
			return "null";
		}

		Class<?> c = array.getClass();
		Class<?> t = c.getComponentType();
		boolean tIsArray = t.isArray();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (tIsArray) {
				sb.append(join((Object[]) array[i], delimiter));
			}
			else {
				sb.append(array[i]);
			}
			if (i != array.length - 1) {
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] add(T[] list, T... values) {
		Object[] newList = new Object[list.length + values.length];
		System.arraycopy(list, 0, newList, 0, list.length);
		System.arraycopy(values, 0, newList, list.length, values.length);
		return (T[]) newList;
	}
}
