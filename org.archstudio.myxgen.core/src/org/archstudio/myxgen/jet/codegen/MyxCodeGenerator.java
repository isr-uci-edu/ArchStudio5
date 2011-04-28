package org.archstudio.myxgen.jet.codegen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import org.archstudio.myxgen.jet.brick.CodegenBrick;
import org.archstudio.myxgen.jet.util.TextUtil;
import org.archstudio.myxgen.jet.xml.JET2InputXMLWriter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jet.JET2Platform;


/**
 * Generates java source code according to the information from eclipse extension point. Restrictions: if different
 * signature interfaces have the same method, this code generator generates two methods separately, and the generated
 * code will results in java compiler error.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class MyxCodeGenerator {

	/**
	 * the plugin id of this project.
	 */
	private final String pluginId = "org.archstudio.myxgen.core";

	/** JET template file for abstract myx adapter class */
	private final static String JET_MYX_ABS_TEMPLATE = "MyxBase.java.jet";

	/** JET template file for myx component class */
	private final static String JET_MYX_COMP_TEMPLATE = "MyxComp.java.jet";

	/** defualt src folder name */
	private final static String DEFAULT_SRC_FOLDER_NAME = "src";

	/** the target project name */
	private IJavaProject targetJavaProject = null;

	/** the target source folder */
	private IPackageFragmentRoot sourcePackageFragmentRoot = null;

	/** bricks read from workspace */
	private final Collection<CodegenBrick> bricks;

	/** the source code files generated */
	private Collection<IFile> generatedSourceFiles = null;

	/** the class names failed to be created */
	private Stack<String> failedClassNames = null;

	public MyxCodeGenerator(String projectName) {
		this.targetJavaProject = findJavaProject(projectName);
		this.sourcePackageFragmentRoot = findSourcePackageFragmentRoot(targetJavaProject);
		this.generatedSourceFiles = new ArrayList<IFile>();
		this.failedClassNames = new Stack<String>();

		bricks = CodegenBrickLoader.loadCodegenBricks(targetJavaProject);
		//bricks = BrickLoader.loadBricks(projectName);
	}

	/**
	 * Generates source code for all the bricks in the project
	 * 
	 * @param brickName
	 */

	public void generateCode() {

		for (CodegenBrick brick : bricks) {
			// methodCheck(brick);
			generateSkeletonCode(brick);
		}
	}

	/**
	 * Generates source code for the given name brick
	 * 
	 * @param brickName
	 * @return brick null if brick doesn't exist
	 */
	public CodegenBrick generateCodeByBrickName(String brickName) {

		for (CodegenBrick brick : bricks) {
			if (brick.getName().equals(brickName)) {
				// methodCheck(brick);
				generateSkeletonCode(brick);
				return brick;
			}
		}

		return null;
	}

	/**
	 * Generates source code for the given brick id
	 * 
	 * @param brickName
	 * @return brick null if brick doesn't exist
	 */
	public CodegenBrick generateCodeByBrickId(String brickId) {

		for (CodegenBrick brick : bricks) {
			if (brick.getId().equals(brickId)) {
				// methodCheck(brick);
				generateSkeletonCode(brick);
				return brick;
			}
		}

		return null;
	}

	/**
	 * Returns the generated source files
	 * 
	 * @return
	 */
	public Collection<IFile> getGeneratedFiles() {
		return this.generatedSourceFiles;
	}

	/**
	 * Returns the class names failed to be created
	 * 
	 * @return
	 */
	public Collection<String> getFailedClassNames() {
		return this.failedClassNames;
	}

	/**
	 * Returns true if the given class name is defined outside of this project
	 * 
	 * @param fqClassName
	 * @return
	 */
	private boolean isDefinedOutside(String fqClassName) {
		boolean result = false;
		try {
			IType type = targetJavaProject.findType(fqClassName);
			if (type == null) {
				//checks if (sourceRoots.isEmpty()) {
				// could not find the source folder
				return false;
			} // the class is a binary
			result = type.isBinary();
			if (!result) {
				//checks if the class is defined in a different project
				IProject fragProject = type.getPackageFragment().getJavaProject().getProject();
				result = !fragProject.getName().equals(targetJavaProject.getProject().getName());
			}
		}
		catch (JavaModelException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Generates skeleton source code files from the brick.
	 * 
	 * @param brick
	 */
	private void generateSkeletonCode(CodegenBrick brick) {

		String baseClassName = brick.getFQBaseClassName();
		if (baseClassName == null) {
			//baseClassName should not be null;
			throw new IllegalArgumentException("baseClassName is null");
		}
		//TODO: experiment
		// checks if the brick class is defined outside of the project.
		if (!isDefinedOutside(baseClassName)) {
			// refresh the project in order to prevent the "out of sync" problem.
			refresh();

			// myx base class
			IFile baseFile = generateSkeleonCodeJet2(brick, JET_MYX_ABS_TEMPLATE, brick.getFQBaseClassName());
			if (baseFile != null) {
				this.generatedSourceFiles.add(baseFile);
			}
		}

		String userClassName = brick.getFQDefaultImplClassName();
		if (userClassName != null) {

			// myx comp class
			IFile compFile = generateSkeleonCodeJet2(brick, JET_MYX_COMP_TEMPLATE, brick.getFQDefaultImplClassName());
			if (compFile != null) {
				this.generatedSourceFiles.add(compFile);
			}

		}

	}

	/**
	 * Refreshes the local files of the target project in order to avoid out-of-sync problem.
	 */
	private void refresh() {

		try {
			targetJavaProject.getProject().refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
		}
		catch (CoreException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generates java source files using JET2.
	 * 
	 * @param brick
	 * @param templateName
	 * @param outputJavaClassName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private IFile generateSkeleonCodeJet2(CodegenBrick brick, String templateName, String outputJavaClassName) {
		IFile generatedFile = null;
		IFile tmpInputFile = null;

		// pushes the class name into the failed class names stack
		// if the generation succeeds, the class name will be poped.
		this.failedClassNames.push(outputJavaClassName);

		try {
			tmpInputFile = createTmpJetInput(outputJavaClassName, templateName);
			Map variables = new HashMap();
			variables.put(MyxCodegenConstants.JET2_TEMPLATE_VARIABLE_NAME_BRICK, brick);

			IStatus status = JET2Platform.runTransformOnResource(pluginId, tmpInputFile, variables,
					new NullProgressMonitor());
			if (!status.isOK()) {
				throw new JETException(status.getMessage(), status.getException());
			}

			generatedFile = getSourceFile(outputJavaClassName);
			if (generatedFile != null) {
				//formats
				MyxCodeGenFormatter.formatCode(generatedFile);
				//this file is not failed
				this.failedClassNames.pop();
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {

			try {
				if (tmpInputFile != null) {
					tmpInputFile.delete(true, new NullProgressMonitor());
				}
			}
			catch (CoreException e) {
				e.printStackTrace();
			}

		}

		return generatedFile;

	}

	/**
	 * The temporary folder name of JET transformation
	 */
	private final static String JET2_TMP_INPUT_ROOT = "._codegen_tmp";

	/**
	 * The temporary xml file name of JET transformation
	 */
	private final static String JET2_TMP_INPUT_XML = "_jet2_tmp.xml";

	/**
	 * Creates a temporary JET XML file for passing information of the template file and the java source file to be
	 * created. The created file is used in main.jet. Since JET2Platform.runTransformOnString() doesn't work properly,
	 * we need to create a file to pass the information. This file will be deleted when the source code generation is
	 * finished.
	 * 
	 * @param brick
	 * @param templateName
	 * @return
	 * @throws CoreException
	 * @throws FileNotFoundException
	 */
	private IFile createTmpJetInput(String fqClassName, String templateName) throws CoreException,
			FileNotFoundException {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		JET2InputXMLWriter.write(fqClassName, templateName, getSrcRelativePathString(sourcePackageFragmentRoot), out);

		IContainer sourceContainer = targetJavaProject.getProject().getFolder(new Path(JET2_TMP_INPUT_ROOT));
		if (!sourceContainer.exists()) {
			((IFolder) sourceContainer).create(false, true, new NullProgressMonitor());
		}

		String tmpFileName = TextUtil.getClassPart(fqClassName) + "_"
				+ templateName.substring(0, templateName.indexOf('.')) + JET2_TMP_INPUT_XML;

		IFile targetFile = sourceContainer.getFile(new Path(tmpFileName));
		if (targetFile.exists()) {
			targetFile.delete(true, new NullProgressMonitor());
		}

		targetFile.create(new ByteArrayInputStream(out.toByteArray()), true, new NullProgressMonitor());
		return targetFile;

	}

	/**
	 * Returns the source file of the given java class
	 * 
	 * @param fullyQualifiedClassName
	 * @return
	 */
	private IFile getSourceFile(String fullyQualifiedClassName) {

		String packageName = TextUtil.getPackagePart(fullyQualifiedClassName);
		String className = TextUtil.getClassPart(fullyQualifiedClassName);

		StringTokenizer stringTokenizer = new StringTokenizer(packageName, ".");

		String srcFolderName = getSrcRelativePathString(sourcePackageFragmentRoot);

		IContainer sourceContainer = targetJavaProject.getProject().getFolder(srcFolderName);

		// the package
		while (stringTokenizer.hasMoreElements()) {
			String folderName = stringTokenizer.nextToken();
			sourceContainer = sourceContainer.getFolder(new Path(folderName));
			if (!sourceContainer.exists()) {
				return null;
			}
		}

		// the java file
		IFile targetFile = sourceContainer.getFile(new Path(className + ".java"));

		return targetFile;
	}

	/**
	 * Returns the java project of the given name.
	 * 
	 * @param projectName
	 * @return
	 */
	private static IJavaProject findJavaProject(String projectName) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();

		final IProject targetProject = workspace.getRoot().getProject(projectName);

		final IJavaProject javaProject = JavaCore.create(targetProject);

		return javaProject;

	}

	/**
	 * Finds the source folder package root. If there are more than one source folders found, checks if there is "src"
	 * folder and returns it if found otherwise returns the first one found.
	 * 
	 * @param javaProject
	 * @return returns null if no package fragment root is found, otherwise returns the source package fragment root.
	 */
	private static IPackageFragmentRoot findSourcePackageFragmentRoot(IJavaProject javaProject) {

		//source folders
		List<IPackageFragmentRoot> sourceRoots = new ArrayList<IPackageFragmentRoot>();

		try {
			for (IPackageFragmentRoot fragmentRoot : javaProject.getPackageFragmentRoots()) {
				if (fragmentRoot.getKind() == IPackageFragmentRoot.K_SOURCE) {
					sourceRoots.add(fragmentRoot);
				}
			}
		}
		catch (JavaModelException e) {
			e.printStackTrace();
		}
		if (sourceRoots.isEmpty()) {
			// could not find the source folder
			return null;
		}

		//if more than one root folders are found, tries to find the default one.
		if (sourceRoots.size() > 1) {
			for (IPackageFragmentRoot root : sourceRoots) {
				if (DEFAULT_SRC_FOLDER_NAME.equalsIgnoreCase(getSrcRelativePathString(root))) {
					return root;
				}
			}
		}

		//return the first found
		return sourceRoots.get(0);
	}

	/**
	 * Returns the relative path from the project root of the source folder
	 * 
	 * @param sourceRoot
	 * @return returns null if no path is found, otherwise returns the relative path string expression.
	 */
	public static String getSrcRelativePathString(IPackageFragmentRoot sourceRoot) {
		IPath srcPath = null;
		try {
			srcPath = sourceRoot.getCorrespondingResource().getProjectRelativePath();

		}
		catch (JavaModelException e) {
			e.printStackTrace();
		}

		return srcPath != null ? srcPath.toPortableString() : null;
	}

	/**
	 * Returns the string expression of the source folder relative path from the given project root. If there are more
	 * than one source folders found, checks if there is "src" folder and returns it otherwise returns the first one
	 * found.
	 * 
	 * @param javaProject
	 * @return returns null if there is no source folder found
	 */
	public static String getSrcRelativePathString(IJavaProject javaProject) {
		String srcPath = null;
		IPackageFragmentRoot srcRoot = findSourcePackageFragmentRoot(javaProject);
		if (srcRoot != null) {
			srcPath = getSrcRelativePathString(srcRoot);
		}
		return srcPath;
	}

}
