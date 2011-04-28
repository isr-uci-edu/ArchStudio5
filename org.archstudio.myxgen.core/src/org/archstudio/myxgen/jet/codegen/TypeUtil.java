package org.archstudio.myxgen.jet.codegen;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;

/**
 * Utitliy for mapping org.eclipse.jdt and the code generator.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
class TypeUtil {

	/**
	 * Recursively generates fully qualified class names from the given type signature and add them into the given set.
	 * 
	 * @param sigInterfaceType
	 * @param typeSig
	 * @param names
	 */
	private static void getFQTypeNames(IType sigInterfaceType, String typeSig, Set<String> names) {

		//removes array signature
		typeSig = removeArraySignature(typeSig);

		//checks if it is a primitive type
		if (isPrimitive(typeSig)) {
			return;
		}

		String typeName = Signature.getSignatureSimpleName(Signature.getTypeErasure(typeSig));
		String qualifier = Signature.getSignatureQualifier(typeSig);

		String fqName;
		if (qualifier != null && qualifier.length() > 0) {
			fqName = qualifier + "." + typeName;
		}
		else {
			String resolvedQualifier = resolveQualifierFromTypeName(sigInterfaceType, typeName);
			if (resolvedQualifier != null) {
				fqName = resolvedQualifier + "." + typeName;
			}
			else {
				fqName = typeName;
			}
		}

		names.add(fqName);

		for (String argType : Signature.getTypeArguments(typeSig)) {

			getFQTypeNames(sigInterfaceType, argType, names);
		}

	}

	/**
	 * Returns a type signature string after removing array signatures.
	 * 
	 * @param typeSignature
	 * @return
	 */
	private static String removeArraySignature(String typeSignature) {

		int arrayCount = Signature.getArrayCount(typeSignature);
		return typeSignature.substring(arrayCount);
	}

	/**
	 * Returns the collection of fully qualified class names from the given type signature. Ex. typeSignature =
	 * "QCollection<QString;>;" results in [java.lang.String, java.util.Collection].
	 * 
	 * @param typeSignature
	 *            defined at org.eclipse.jdt.core.Signature
	 * @return
	 */
	public static Collection<String> getFQClassNames(IType sigInterfaceType, String typeSignature) {

		Set<String> names = new HashSet<String>();

		getFQTypeNames(sigInterfaceType, typeSignature, names);

		return names;
	}

	/**
	 * Returns the resolved fully qualified type. If there are more than one candidates, returns the first one. Ignores
	 * parameters of the given signature. For example, if the given type signature is "QFoo&lt;QBoo&gt;" , &lt;QBoo&gt;
	 * will be ignored.
	 * 
	 * @param sigInterfaceType
	 * @param typeSignature
	 *            Signature string (i.e. /"QString/")
	 * @return
	 */
	public static String resolveTypeFromSignature(IType sigInterfaceType, String typeSignature) {

		String typeName = Signature.getSignatureSimpleName(typeSignature);

		String qualifier = Signature.getSignatureQualifier(typeSignature);

		String fqName;
		if (qualifier != null && qualifier.length() > 0) {
			fqName = qualifier + "." + typeName;
		}
		else {

			//type name without generics
			String erasureTypeName = Signature.getSignatureSimpleName(Signature.getTypeErasure(typeSignature));

			String resolvedQualifier = resolveQualifierFromTypeName(sigInterfaceType, erasureTypeName);
			if (resolvedQualifier != null) {
				fqName = resolvedQualifier + "." + typeName;
			}
			else {
				fqName = typeName;
			}
		}

		return fqName;
	}

	/**
	 * Returns the resolved qualifier. If there are more than one candidates, returns the first one. Returns null if
	 * there is no candidate.
	 * 
	 * @param sigInterfaceType
	 * @param typeName
	 * @return
	 */
	private static String resolveQualifierFromTypeName(IType sigInterfaceType, String typeName) {

		try {

			String[][] types = sigInterfaceType.resolveType(typeName);
			if (types != null) {
				// Returns the first candidate
				String qualifier = types[0][0];
				//				String name = types[0][1];
				return qualifier;
			}
		}
		catch (JavaModelException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns true if the given signature type is primitive
	 * 
	 * @param signatureType
	 *            signature type defined at org.eclipse.jdt.core.Signature
	 * @return
	 */
	public static boolean isPrimitive(String signatureType) {
		return Signature.SIG_BOOLEAN.equals(signatureType) // boolean
				|| Signature.SIG_CHAR.equals(signatureType) // char
				|| Signature.SIG_DOUBLE.equals(signatureType) // double
				|| Signature.SIG_FLOAT.equals(signatureType) // float
				|| Signature.SIG_INT.equals(signatureType) // int
				|| Signature.SIG_LONG.equals(signatureType) // long
				|| Signature.SIG_SHORT.equals(signatureType) // short
				|| Signature.SIG_VOID.equals(signatureType); // void
	}

	/**
	 * Returns string expression of default return value of the given signature type
	 * 
	 * @param signatureType
	 *            signature type defined at org.eclipse.jdt.core.Signature
	 * @return
	 */
	public static String getDefaultReturnValue(String signatureType) {
		if (Signature.SIG_BOOLEAN.equals(signatureType)) {
			return Boolean.FALSE.toString();// boolean
		}
		if (Signature.SIG_CHAR.equals(signatureType) // char
				|| Signature.SIG_INT.equals(signatureType) // int
				|| Signature.SIG_LONG.equals(signatureType) // long
				|| Signature.SIG_SHORT.equals(signatureType)) {// short
			return "0";
		}
		if (Signature.SIG_DOUBLE.equals(signatureType)) { // double
			return "0.0d";
		}
		if (Signature.SIG_FLOAT.equals(signatureType)) { // float
			return "0.0f";
		}
		if (Signature.SIG_VOID.equals(signatureType)) {// void
			return "";
		}

		return "null";

	}

	public static void main(String[] args) {
		String parametarizedFQClassname = "java.util.Map<EventKey, EventValue>";
		String typeSig = Signature.createTypeSignature(parametarizedFQClassname, false);
		// String typeSig = "Qjava.util.Map<QEventKey;QEventValue;>;";
		System.out.println(typeSig);

		// "java.util"
		System.out.println("    getSignatureQualifier:" + Signature.getSignatureQualifier(typeSig));

		// "Map<EventKey,EventValue>"
		System.out.println("    getSignatureSimpleName:" + Signature.getSignatureSimpleName(typeSig));

		// "Qjava.util.Map;"
		System.out.println("    getTypeErasure:" + Signature.getTypeErasure(typeSig));

		// "Map"
		System.out.println("    getSignatureSimpleName(getTypeErasure):"
				+ Signature.getSignatureSimpleName(Signature.getTypeErasure(typeSig)));

		// ["QEventKey;","QEventValue;"]
		for (String str : Signature.getTypeArguments(typeSig)) {
			System.out.println("    getTypeArguments((" + typeSig + ") = " + str);
		}
	}
}
