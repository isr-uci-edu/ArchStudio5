package org.archstudio.myxgen.jet.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.archstudio.myxgen.jet.brick.CodegenBrick;
import org.archstudio.myxgen.jet.brick.CodegenBrickInterface;
import org.archstudio.myxgen.jet.brick.MethodContainer;
import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;
import org.archstudio.myxgen.jet.extension.pde.ExtensionLoader;
import org.archstudio.myxgen.jet.extension.pde.ExtensionLoaderUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;


/**
 * The brick information loader for source code generation
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
class CodegenBrickLoader {

	/**
	 * Reads a brick of the given id from the extension point of the given java project.
	 * 
	 * @param project
	 * @param brickId
	 * @param includeDependProjects
	 *            if true, looks up all the dependent projects' extension
	 * @return
	 */
	public static CodegenBrick loadCodegenBrick(IProject project, String brickId, boolean includeDependProjects) {

		CodegenBrick brick = CodegenBrickLoader.loadCodegenBrick(JavaCore.create(project), brickId);

		if (brick != null) {
			return brick;
		}

		if (includeDependProjects) {
			for (IProject refProject : CodegenBrickLoader.getDependentProjects(project)) {
				brick = CodegenBrickLoader.loadCodegenBrick(JavaCore.create(refProject), brickId);
				if (brick != null) {
					return brick;
				}
			}
		}

		return null;
	}

	/**
	 * Reads a brick of the given Id from the extension point of the given java project.
	 * 
	 * @param javaProject
	 * @param brickId
	 * @return
	 */
	static CodegenBrick loadCodegenBrick(IJavaProject javaProject, String brickId) {
		assert javaProject != null;
		assert brickId != null;

		ExtensionLoader extLoader = ExtensionLoaderUtil.getExtensionLoader(javaProject.getProject());
		IMyxBrickExtension extBrick = extLoader.getExtensionBrickById(brickId);
		if (extBrick == null) {
			return null;
		}

		CodegenBrick brick = CodegenBrickLoader.createCodegenBrick(javaProject, extBrick);

		//TODO:experimenting
		brick.setUnimplMethods(CodegenBrickLoader.searchUnimplementedMethods(javaProject, brick));

		return brick;

	}

	/**
	 * Returns a list of Brick objects. This method reads eclipse's extension registry of the specified project and
	 * creates a list of Brick objects.
	 * 
	 * @param project
	 * @return
	 */
	static Collection<CodegenBrick> loadCodegenBricks(IProject project) {
		return CodegenBrickLoader.loadCodegenBricks(JavaCore.create(project));
	}

	private static Collection<IProject> getDependentProjects(IProject project) {
		//gets the referred projects
		Set<IProject> refProjects = new HashSet<IProject>();
		try {
			//looks at plugin.xml
			for (IProject refProject : project.getDescription().getReferencedProjects()) {
				refProjects.add(refProject);
			}
			//looks at MANIFEST.MF
			for (IProject refProject : project.getDescription().getDynamicReferences()) {
				refProjects.add(refProject);
			}
		}
		catch (CoreException e) {
			e.printStackTrace();
		}

		return refProjects;
	}

	/**
	 * Returns a list of Brick objects. This method reads eclipse's extension registry of the specified project and
	 * creates a list of Brick objects.
	 * 
	 * @param project
	 *            the target project
	 * @param includeDependentProjects
	 *            if true, looks up all the dependent projects' extension
	 * @return
	 */
	public static Collection<CodegenBrick> loadCodegenBricks(IProject project, boolean includeDependentProjects) {
		Set<CodegenBrick> brickSet = new HashSet<CodegenBrick>();

		if (includeDependentProjects) {
			//looks at all the referred projects
			for (IProject refProject : CodegenBrickLoader.getDependentProjects(project)) {
				brickSet.addAll(CodegenBrickLoader.loadCodegenBricks(refProject));
			}
		}

		brickSet.addAll(CodegenBrickLoader.loadCodegenBricks(JavaCore.create(project)));

		return brickSet;
	}

	/**
	 * Returns a list of Brick objects. This method reads eclipse's extension registry of the specified javaProject and
	 * creates a list of Brick objects.
	 * 
	 * @param javaProject
	 *            the target java project where the extension point is defined
	 * @return
	 */
	static List<CodegenBrick> loadCodegenBricks(IJavaProject javaProject) {
		assert javaProject != null;

		List<CodegenBrick> bricks = new ArrayList<CodegenBrick>();

		ExtensionLoader extLoader = ExtensionLoaderUtil.getExtensionLoader(javaProject.getProject());

		for (IMyxBrickExtension extBrick : extLoader.getExtensionBricks()) {
			//TODO: need to sort extBricks according to its parent child relation

			CodegenBrick brick = CodegenBrickLoader.createCodegenBrick(javaProject, extBrick);

			//TODO:experimenting
			brick.setUnimplMethods(CodegenBrickLoader.searchUnimplementedMethods(javaProject, brick));

			bricks.add(brick);

		}

		return bricks;
	}

	/**
	 * Finds methods that should have been implemented in the parent brick TODO: experimenting. This look at only the
	 * direct parent, so far.
	 * 
	 * @param javaProject
	 * @param brick
	 * @return
	 */
	private static Collection<MethodContainer> searchUnimplementedMethods(IJavaProject javaProject, CodegenBrick brick) {

		Collection<MethodContainer> unimplMethods = new ArrayList<MethodContainer>();

		IMyxBrickExtension extParent = brick.getParentBrick();
		if (extParent != null) {

			String parentJavaClassName = extParent.getFQDefaultImplClassName();
			if (parentJavaClassName == null) {
				parentJavaClassName = extParent.getFQBaseClassName();
			}
			if (parentJavaClassName != null) {
				try {
					IType type = javaProject.findType(parentJavaClassName);
					if (type == null) {
						System.err.println(parentJavaClassName + " is not found.");
						return unimplMethods;
					}
					MethodSearcher methodSearcher = new MethodSearcher();
					return methodSearcher.findUnimplementedMethods(type, javaProject);
				}
				catch (JavaModelException e) {
					e.printStackTrace();
				}
			}
		}

		return unimplMethods;

		//			//gets interfaces that should be implemented in the parent brick
		//			CodegenBrick parent = createCodegenBrick(javaProject, extParent);
		//			Collection<CodegenBrickInterface> parentIntfs = new ArrayList<CodegenBrickInterface>();
		//			parentIntfs.addAll(CompTemplateUtil.getImplMethodIn(parent));
		//			parentIntfs.addAll(CompTemplateUtil.getImplMethodOut(parent));
		//			//checks methods of each interface
		//			for (CodegenBrickInterface intf : parentIntfs) {
		//
		//				Set<MethodContainer> declaredMethods = new HashSet<MethodContainer>();
		//				declaredMethods.addAll(intf.getJavaInterfaceMethods());
		//				for (MethodContainer dm : declaredMethods) {
		//					boolean found = false;
		//
		//					// checks if the method is implemented
		//					for (MethodContainer im : implMethods) {
		//						if (MethodSearcher.compare(im, dm)) {
		//							found = true;
		//							break;
		//						}
		//					}
		//					if (!found) {
		//						unimplMethods.add(dm);
		//					}
		//				}
		////			}
		//		}
		//		for(MethodContainer method : unimplMethods) {
		//			System.out.println("unimplMethod :" + method.toSimpleString());
		//		}
		//		
		//		return unimplMethods;
	}

	/**
	 * Returns a list of Brick of the workspace.
	 * 
	 * @return
	 */
	public static Collection<CodegenBrick> loadAllBricks() {

		Collection<CodegenBrick> bricks = new ArrayList<CodegenBrick>();
		for (ExtensionLoader extLoader : ExtensionLoaderUtil.getAllExtensionLoaders()) {
			for (IMyxBrickExtension extBrick : extLoader.getExtensionBricks()) {
				CodegenBrick brick = new CodegenBrick(extBrick);
				bricks.add(brick);
			}
		}

		return bricks;

	}

	/**
	 * Creates a codegenBrick from the given extBrick
	 * 
	 * @param javaProject
	 * @param extBrick
	 * @return
	 */
	private static CodegenBrick createCodegenBrick(IJavaProject javaProject, IMyxBrickExtension extBrick) {
		CodegenBrick brick = new CodegenBrick(extBrick);

		//looks for interface methods
		for (CodegenBrickInterface brickIntf : brick.getAllInterfaces()) {
			try {
				CodegenBrickLoader.loadInterfaceImplMethods(brickIntf, javaProject);
			}
			catch (JavaModelException e) {
				e.printStackTrace();
			}
		}

		//looks for parent brick constructors
		try {
			CodegenBrickLoader.loadParentConstructors(brick, javaProject);
		}
		catch (JavaModelException e) {
			e.printStackTrace();
		}

		return brick;
	}

	/**
	 * Looks at java file specified by the brick interface and copies method information of the class.
	 * 
	 * @param intf
	 * @param javaProject
	 * @throws JavaModelException
	 */
	private static void loadInterfaceImplMethods(CodegenBrickInterface intf, IJavaProject javaProject)
			throws JavaModelException {

		// checks if the java interface or class of the intf exists
		IType type = javaProject.findType(intf.getFQJavaInterfaceName());
		if (type == null) {
			// the interface is not found.
			return;
		}

		//search methods declared in the interface and its super interfaces
		MethodSearcher methodSearcher = new MethodSearcher();
		methodSearcher.findAllMethods(type, javaProject);

		//set the method lists
		intf.setJavaInterfaceNonVoidMethods(methodSearcher.getNonVoidMethodList());
		intf.setJavaInterfaceVoidMethods(methodSearcher.getVoidMethodList());

		//set if the java interface is interface
		intf.setJavaInterface(type.isInterface());

	}

	private static void loadParentConstructors(CodegenBrick brick, IJavaProject javaProject) throws JavaModelException {

		String parentClassName = brick.getFQParentClassName();
		if (parentClassName == null) {
			return;
		}
		IType type = javaProject.findType(parentClassName);
		if (type == null) {
			// the parent class is not found.
			return;
		}

		//search constructors declared in the parent class and its super interfaces
		MethodSearcher methodSearcher = new MethodSearcher();
		methodSearcher.findConstructors(type);

		brick.setParentConstructors(methodSearcher.getConstructorList());

	}

	public static void main(String[] args) {

		System.out.println(Signature.createTypeSignature("void", true));
		System.out.println(Signature.getTypeSignatureKind(Signature.createTypeSignature("void", true)));

		System.out.println(Signature.createTypeSignature("void", false));
		System.out.println(Signature.getTypeSignatureKind(Signature.createTypeSignature("void", false)));

		System.out.println(Signature.createTypeSignature("int[]", true));
		System.out.println(Signature.getTypeSignatureKind(Signature.createTypeSignature("int[]", true)));

		System.out.println(Signature.createTypeSignature("int[]", false));
		System.out.println(Signature.getTypeSignatureKind(Signature.createTypeSignature("int[]", false)));

		System.out.println(Signature.getSignatureSimpleName(Signature.createTypeSignature("String[]", false)));
		System.out.println(Signature.getTypeSignatureKind(Signature.getSignatureSimpleName(Signature
				.createTypeSignature("String[]", false))));

		System.out.println(Signature.createTypeSignature("String[]", true));
		System.out.println(Signature.getTypeSignatureKind(Signature.createTypeSignature("String[]", true)));

	}
}
