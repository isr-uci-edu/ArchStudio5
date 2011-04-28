package org.archstudio.myxgen.jet.codegen;

 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.archstudio.myxgen.jet.brick.MethodContainer;
import org.archstudio.myxgen.jet.brick.MethodParameter;
import org.archstudio.myxgen.jet.util.TextUtil;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IRegion;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;


/**
 * A methods searcher that gathers all the methods defined in the given interface and its super interfaces.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
class MethodSearcher {

	/**
	 * methods whose return types are not "void".
	 */
	private final List<MethodContainer> nonVoidMethodList = new ArrayList<MethodContainer>();

	/**
	 * methods whose return types are "void".
	 */
	private final List<MethodContainer> voidMethodList = new ArrayList<MethodContainer>();

	/**
	 * constructors
	 */
	private final List<MethodContainer> constructorList = new ArrayList<MethodContainer>();

	/**
	 * 
	 */
	public MethodSearcher() {
	}

	/**
	 * Returns methods whose return types are not "void"
	 * 
	 * @return the nonVoidMethodList
	 */
	public List<MethodContainer> getNonVoidMethodList() {
		return nonVoidMethodList;
	}

	/**
	 * Returns methods whose return types are "void"
	 * 
	 * @return the voidMethodList
	 */
	public List<MethodContainer> getVoidMethodList() {
		return voidMethodList;
	}

	/**
	 * Returns constructors
	 * 
	 * @return
	 */
	public List<MethodContainer> getConstructorList() {
		return constructorList;
	}

	/**
	 * Finds all the methods defined in the given interface and its ancestor interfaces.
	 * 
	 * @param sigInterfaceType
	 * @param javaProject
	 * @throws JavaModelException
	 */
	public void findAllMethods(IType sigInterfaceType, IJavaProject javaProject) throws JavaModelException {

		IMethodModifierChecker checker = new AMethodChecker();
		//methods defined at the given type
		findMethods(sigInterfaceType, checker, this.voidMethodList, this.nonVoidMethodList);

		//methods defined at the ancestor types
		IRegion region = JavaCore.newRegion();
		region.add(sigInterfaceType);
		ITypeHierarchy typeHierachy = javaProject.newTypeHierarchy(region, new NullProgressMonitor());

		//IType[] superTypes = typeHierachy.getAllSuperInterfaces(sigInterfaceType);
		IType[] superTypes = typeHierachy.getAllSupertypes(sigInterfaceType);
		for (IType type : superTypes) {
			findMethods(type, checker, this.voidMethodList, this.nonVoidMethodList);
		}
	}

	/**
	 * Finds methods defined in the specified interface and update the given lists according to the return type of each
	 * method. This method does not look at the super types.
	 * 
	 * @param interfaceType
	 * @throws JavaModelException
	 */
	private static void findMethods(IType interfaceType, IMethodModifierChecker checker,
			List<MethodContainer> voidMethodList, List<MethodContainer> nonVoidMethodList) throws JavaModelException {

		if (interfaceType == null) {
			// the interface is not found.
			return;
		}

		// Look through the methods of the interface
		IMethod[] methods = interfaceType.getMethods();
		for (IMethod method : methods) {

			if (method.isConstructor()) {
				continue;
			}
			////////////////
			//the modifiers
			String modifiers = checker.calcModifiers(interfaceType, method);
			if (modifiers == null) {

				continue;
			}

			//creates a method info container
			MethodContainer methodContainer = createMethodContainer(interfaceType, method, modifiers);

			// add the method to the list according to its return type
			if (method.getReturnType().equals(Signature.SIG_VOID)) {
				//void
				//voidMethodList.add(methodContainer);
				addMethodConatiner(methodContainer, voidMethodList);
			}
			else {
				//non-void
				//nonVoidMethodList.add(methodContainer);
				addMethodConatiner(methodContainer, nonVoidMethodList);
			}
		}

		//		System.out.println(sig.getFQJavaInterfaceName() + " has");
		//		for (MethodContainer method : nonVoidMethodList) {
		//			//			System.out.println("      " + method.toSimpleString());
		//			System.out.println("      " + method.toFQString());
		//		}
		//		// parameter types
		//		for (MethodContainer method : sig.getJavaInterfaceVoidMethods()) {
		//			System.out.println(method.toSimpleString());
		//			for (MethodParameter param : method.getMethodParameters()) {
		//				System.out.println("  imports    " + param.getFQParamType());
		//			}
		//		}

	}

	/**
	 * Finds constructors of the given class.
	 * 
	 * @param type
	 * @throws JavaModelException
	 */
	public void findConstructors(IType type) throws JavaModelException {

		if (type == null || !type.isClass()) {
			return;
		}

		// get the constructors
		List<IMethod> constructors = new ArrayList<IMethod>();
		for (IMethod method : type.getMethods()) {
			if (method.isConstructor()) {
				constructors.add(method);
			}

		}

		if (constructors.isEmpty()) {
			//creates the default constructor
			MethodContainer methodContainer = new MethodContainer();
			methodContainer.setModifiers(Flags.toString(Flags.AccPublic));
			methodContainer.setMethodName(TextUtil.getClassPart(type.getFullyQualifiedName()));
			methodContainer.setReturnType(Signature.getSignatureSimpleName(Signature.SIG_VOID));
			methodContainer.setFqReturnClassNames(new ArrayList<String>());
			methodContainer.setPrimitiveReturnType(true);
			methodContainer.setDefaultReturnValue(TypeUtil.getDefaultReturnValue(Signature.SIG_VOID));
			methodContainer.setMethodParameters(new ArrayList<MethodParameter>());
			methodContainer.setFqExceptionTypes(new ArrayList<String>());
			constructorList.add(methodContainer);
			return;
		}
		else {

			for (IMethod constructor : constructors) {
				int flags = constructor.getFlags();
				////////////////
				//the modifiers
				String modifiers;
				if (Flags.isProtected(flags)) {
					modifiers = Flags.toString(Flags.AccProtected);
				}
				else if (Flags.isPublic(flags)) {
					modifiers = Flags.toString(Flags.AccPublic);
				}
				else if (Flags.isPackageDefault(flags)) {
					modifiers = "";
				}
				else {
					continue;
				}

				//creates a constructor container
				MethodContainer methodContainer = createMethodContainer(type, constructor, modifiers);

				// add the container to the list
				constructorList.add(methodContainer);
			}
		}
	}

	/**
	 * Creates a MethodContainer
	 * 
	 * @param type
	 * @param method
	 * @param modifiers
	 * @return
	 * @throws JavaModelException
	 */
	private static MethodContainer createMethodContainer(IType type, IMethod method, String modifiers)
			throws JavaModelException {
		//a method info container
		MethodContainer methodContainer = new MethodContainer();

		//the modifiers
		methodContainer.setModifiers(modifiers);

		//the return type
		methodContainer.setReturnType(Signature.getSignatureSimpleName(method.getReturnType()));
		methodContainer.setFqReturnClassNames(TypeUtil.getFQClassNames(type, method.getReturnType()));
		methodContainer.setPrimitiveReturnType(TypeUtil.isPrimitive(method.getReturnType()));
		methodContainer.setDefaultReturnValue(TypeUtil.getDefaultReturnValue(method.getReturnType()));

		//the method name
		methodContainer.setMethodName(method.getElementName());

		// check the parameters of the method.
		List<MethodParameter> methodParams = new ArrayList<MethodParameter>();
		String[] paramTypes = method.getParameterTypes();
		String[] paramNames = method.getParameterNames();
		for (int i = 0; i < paramTypes.length; i++) {
			//String paramType = TypeUtil.resolveTypeFromSignature(type, paramTypes[i]);
			String paramType = Signature.getSignatureSimpleName(paramTypes[i]);
			String paramName = paramNames[i];
			Collection<String> fqClassNames = TypeUtil.getFQClassNames(type, paramTypes[i]);
			methodParams.add(new MethodParameter(paramType, paramName, TypeUtil.isPrimitive(paramTypes[i]),
					fqClassNames));
		}
		methodContainer.setMethodParameters(methodParams);

		// check the exceptions of the method
		List<String> fqExceptionTypes = new ArrayList<String>();
		String[] exceptions = method.getExceptionTypes();
		for (String exception : exceptions) {
			String fqExceptionType = TypeUtil.resolveTypeFromSignature(type, exception);
			fqExceptionTypes.add(fqExceptionType);
		}
		methodContainer.setFqExceptionTypes(fqExceptionTypes);

		return methodContainer;
	}

	/**
	 * Adds a methodContainer to the list only if there is no equivalent methodContainer in the list
	 * 
	 * @param methodContainer
	 * @param containerList
	 */
	private static void addMethodConatiner(MethodContainer methodContainer, List<MethodContainer> containerList) {
		boolean found = false;

		for (MethodContainer mc : containerList) {
			if (compare(mc, methodContainer)) {
				found = true;
				break;
			}
		}

		if (!found) {
			containerList.add(methodContainer);
		}
	}

	/**
	 * Compares two MethodContainers
	 * 
	 * @param mc0
	 * @param mc1
	 * @return
	 */
	private static boolean compare(MethodContainer mc0, MethodContainer mc1) {
		//method name
		if (!mc0.getMethodName().equals(mc1.getMethodName())) {
			return false;
		}

		List<MethodParameter> mc0Params = mc0.getMethodParameters();
		List<MethodParameter> mc1Params = mc1.getMethodParameters();

		//parameter size
		if (mc0Params.size() != mc1Params.size()) {
			return false;
		}

		//each parameter
		for (int i = 0; i < mc0Params.size(); i++) {
			MethodParameter mc0Param = mc0Params.get(i);
			MethodParameter mc1Param = mc1Params.get(i);
			if (!mc0Param.getParamType().equals(mc1Param.getParamType())) {
				return false;
			}
		}

		return true;
	}

	private interface IMethodModifierChecker {
		String calcModifiers(IType type, IMethod method) throws JavaModelException;
	}

	private class AMethodChecker implements IMethodModifierChecker {
		/**
		 * Returns a string expression of modifiers
		 * 
		 * @param type
		 * @param method
		 * @return a string expression of modifiers the interfaceType must implement, otherwise null
		 * @throws JavaModelException
		 */
		public String calcModifiers(IType type, IMethod method) throws JavaModelException {
			int flags = method.getFlags();

			int implFlags = 0;

			// the method is to declare implementation,
			// so only 'public' and 'protected' are used.
			if (Flags.isPrivate(flags) || Flags.isStatic(flags)) {
				// ignores "private", "static",
				return null;
			}

			// /////////////
			// for interface
			if (type.isInterface()) {

				// 'package default' or 'public'
				if (Flags.isPackageDefault(flags) || Flags.isPublic(flags)) {
					implFlags |= Flags.AccPublic;
				}
				else if (Flags.isProtected(flags)) {
					implFlags |= Flags.AccPrivate;
				}

				return Flags.toString(implFlags);

			}
			// //////////////
			// for class
			else if (type.isClass()) {
				if (method.isConstructor()) {
					String modifier = null;
					if (Flags.isProtected(flags)) {
						modifier = Flags.toString(Flags.AccProtected);
					}
					else if (Flags.isPublic(flags)) {
						modifier = Flags.toString(Flags.AccPublic);
					}
					else if (Flags.isPackageDefault(flags)) {
						modifier = "";
					}
					return modifier;
				}

				// 'abstract'
				if (Flags.isAbstract(flags)) {
					if (Flags.isProtected(flags)) {
						// 'protected'
						implFlags |= Flags.AccProtected;
					}
					else if (Flags.isPublic(flags)) {
						// 'public'
						implFlags |= Flags.AccPublic;
					}

					return Flags.toString(implFlags);
				}
			}

			return null;

		}
	}

	/**
	 * Implemented methods checker
	 * 
	 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
	 * 
	 */
	private class CMethodChecker implements IMethodModifierChecker {

		public String calcModifiers(IType type, IMethod method) throws JavaModelException {
			if (!type.isClass()) {
				//only class can have concrete methods
				return null;
			}

			int flags = method.getFlags();

			int implFlags = 0;

			if (Flags.isPrivate(flags) || Flags.isStatic(flags)) {
				// ignores "private", "static",
				return null;
			}

			if (method.isConstructor()) {
				String modifier = null;
				if (Flags.isProtected(flags)) {
					modifier = Flags.toString(Flags.AccProtected);
				}
				else if (Flags.isPublic(flags)) {
					modifier = Flags.toString(Flags.AccPublic);
				}
				else if (Flags.isPackageDefault(flags)) {
					modifier = "";
				}
				return modifier;
			}

			// not 'abstract'
			if (!Flags.isAbstract(flags)) {
				if (Flags.isProtected(flags)) {
					// 'protected'
					implFlags |= Flags.AccProtected;
				}
				else if (Flags.isPublic(flags)) {
					// 'public'
					implFlags |= Flags.AccPublic;
				}

				return Flags.toString(implFlags);
			}

			return null;
		}

	}

	/**
	 * Gets a collection of methods that are not implemented in the given type. TODO: experimenting
	 * 
	 * @param type
	 * @param javaProject
	 * @return
	 * @throws JavaModelException
	 */
	public List<MethodContainer> findUnimplementedMethods(IType type, IJavaProject javaProject)
			throws JavaModelException {

		List<MethodContainer> unimplMethods = new ArrayList<MethodContainer>();
		List<MethodContainer> concreteMethods = new ArrayList<MethodContainer>();
		List<MethodContainer> abstractMethods = new ArrayList<MethodContainer>();

		//ancestor types
		IRegion region = JavaCore.newRegion();
		region.add(type);
		ITypeHierarchy typeHierachy = javaProject.newTypeHierarchy(region, new NullProgressMonitor());
		IType[] superTypes = typeHierachy.getAllSupertypes(type);

		//concrete methods
		IMethodModifierChecker cChecker = new CMethodChecker();
		findMethods(type, cChecker, concreteMethods, concreteMethods);
		for (IType superType : superTypes) {
			findMethods(superType, cChecker, concreteMethods, concreteMethods);
		}

		//abstract methods
		IMethodModifierChecker aChecker = new AMethodChecker();
		findMethods(type, aChecker, abstractMethods, abstractMethods);
		for (IType superType : superTypes) {
			findMethods(superType, aChecker, abstractMethods, abstractMethods);
		}

		//compare abstract and concrete methods
		for (MethodContainer aMethod : abstractMethods) {
			boolean found = false;
			for (MethodContainer cMethod : concreteMethods) {
				if (compare(aMethod, cMethod)) {
					found = true;
					break;
				}
			}
			if (!found) {
				unimplMethods.add(aMethod);
			}
		}

		return unimplMethods;
	}
}
