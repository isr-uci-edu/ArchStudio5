package org.archstudio.utils.eclipse.jdt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTRequestor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.refactoring.CompilationUnitChange;
import org.eclipse.jdt.internal.corext.fix.CleanUpConstants;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.ui.cleanup.CleanUpContext;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUp;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.text.edits.TextEdit;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

@SuppressWarnings({ "restriction", "unchecked" })
public class CodeGeneration {

	public static void formatCode(IProject project) throws CoreException {
		List<IFolder> folders = Lists.newArrayList(project.getFolder("src"));
		List<IFile> files = Lists.newArrayList();
		while (folders.size() > 0) {
			IFolder f = folders.remove(folders.size() - 1);
			if (f.exists()) {
				for (IResource r : f.members()) {
					if (r instanceof IFile && "java".equalsIgnoreCase(((IFile) r).getFileExtension())) {
						files.add((IFile) r);
					}
					else if (r instanceof IFolder) {
						folders.add((IFolder) r);
					}
				}
			}
		}
		formatCode(project, files);
	}

	public static void formatCode(IFile file) throws CoreException {
		formatCode(file.getProject(), Arrays.asList(file));
	}

	@SuppressWarnings("unused")
	private static void dump(Preferences preferences, int indent) throws BackingStoreException {
		char[] indentSpaceChars = new char[indent];
		Arrays.fill(indentSpaceChars, ' ');
		String indentSpace = new String(indentSpaceChars);
		List<String> children = Arrays.asList(preferences.childrenNames());
		Collections.sort(children);
		for (String child : children) {
			System.err.println(indentSpace + "* " + child);
			dump(preferences.node(child), indent + 2);
		}
		List<String> keys = Arrays.asList(preferences.keys());
		Collections.sort(keys);
		for (String key : keys) {
			System.err.println(indentSpace + "- " + key + " = " + preferences.get(key, "[]"));
		}
	}

	public static void formatCode(IProject project, List<IFile> files) throws CoreException {
		Collection<ICompilationUnit> cus = Lists.newArrayList();
		Collection<ICompilationUnit> cuWorkingCopies = Lists.newArrayList();
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
			IJavaProject javaProject = JavaCore.create(project);
			for (IFile file : files) {
				ICompilationUnit cu = JavaCore.createCompilationUnitFrom(file);
				cu = cu.getPrimary();
				cu = cu.getWorkingCopy(new NullProgressMonitor());
				if (cu.isWorkingCopy()) {
					cuWorkingCopies.add(cu);
				}
				cus.add(cu);
			}
			Map<String, String> compilerOptions = javaProject.getOptions(true);
			compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
			compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7);
			compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
			compilerOptions.put(JavaCore.COMPILER_PB_MISSING_OVERRIDE_ANNOTATION, JavaCore.WARNING);
			compilerOptions.put(JavaCore.COMPILER_PB_MISSING_OVERRIDE_ANNOTATION_FOR_INTERFACE_METHOD_IMPLEMENTATION,
					JavaCore.ENABLED);
			compilerOptions.put(JavaCore.COMPILER_PB_SUPPRESS_WARNINGS, JavaCore.DISABLED);
			compilerOptions.put(JavaCore.COMPILER_PB_SUPPRESS_OPTIONAL_ERRORS, JavaCore.DISABLED);
			compilerOptions.put(JavaCore.COMPILER_PB_MAX_PER_UNIT, "0");

			CleanUpOptions cuOptions = JavaPlugin.getDefault().getCleanUpRegistry()
					.getDefaultOptions(CleanUpConstants.DEFAULT_SAVE_ACTION_OPTIONS);
			// dump(Platform.getPreferencesService().getRootNode(), 0);
			for (String key : Lists.newArrayList(cuOptions.getKeys())) {
				String value = Platform.getPreferencesService().getRootNode().node("instance")
						.node(JavaPlugin.getPluginId()).get(key, cuOptions.getValue(key));
				cuOptions.setOption(key, value);
			}

			// iterate through cleanups
			{
				final List<ICleanUp> cleanups =
						Lists.newArrayList(JavaPlugin.getDefault().getCleanUpRegistry().createCleanUps());

				for (final ICleanUp cleanup : cleanups) {
					try {
						final List<CleanUpContext> cleanupContexts = Lists.newArrayList();
						cleanup.setOptions(cuOptions);
						RefactoringStatus status = cleanup.checkPreConditions(javaProject,
								cus.toArray(new ICompilationUnit[cus.size()]), new NullProgressMonitor());
						if (status.isOK()) {
							if (cleanup.getRequirements().requiresAST()) {
								// AST.JLS4 is deprecated in Eclipse 4.5
								@SuppressWarnings("all")
								ASTParser parser = ASTParser.newParser(AST.JLS4);
								parser.setProject(javaProject);
								parser.setKind(ASTParser.K_COMPILATION_UNIT);
								parser.setResolveBindings(true);
								parser.setBindingsRecovery(true);
								parser.setStatementsRecovery(true);
								parser.setCompilerOptions(compilerOptions);
								parser.createASTs(cus.toArray(new ICompilationUnit[cus.size()]), new String[] {},
										new ASTRequestor() {
											@Override
											public void acceptAST(ICompilationUnit source, CompilationUnit ast) {
												cleanupContexts.add(new CleanUpContext(source, ast));
											}
										}, new NullProgressMonitor());
							}
							else {
								for (ICompilationUnit source : cus) {
									cleanupContexts.add(new CleanUpContext(source, null));
								}
							}

							for (CleanUpContext cleanupContext : cleanupContexts) {
								try {
									ICleanUpFix fix = cleanup.createFix(cleanupContext);
									if (fix != null) {
										CompilationUnitChange change = fix.createChange(new NullProgressMonitor());
										if (change.isEnabled()) {
											TextEdit edit = change.getEdit();
											if (edit != null) {
												cleanupContext.getCompilationUnit().applyTextEdit(edit,
														new NullProgressMonitor());
											}
										}
									}
								}
								catch (Exception e) {
									e.printStackTrace();
								}
							}

							cleanup.checkPostConditions(new NullProgressMonitor());
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			// format files
			{
				// dump(Platform.getPreferencesService().getRootNode(), 0);
				Map<String, String> formatterOptions = JavaCore.getOptions();
				for (String key : Lists.newArrayList(formatterOptions.keySet())) {
					String value = Platform.getPreferencesService().getRootNode().node("project")
							.node(project.getName()).node("org.eclipse.jdt.core").get(key, formatterOptions.get(key));
					formatterOptions.put(key, value);
				}

				CodeFormatter codeFormatter =
						ToolFactory.createCodeFormatter(formatterOptions, ToolFactory.M_FORMAT_NEW);
				for (ICompilationUnit cu : cus) {
					String contents = cu.getBuffer().getContents();
					for (int kind : Ints.asList(CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS)) {
						TextEdit edit = codeFormatter.format(kind, contents, 0, contents.length(), 0, null);
						if (edit != null) {
							cu.applyTextEdit(edit, new NullProgressMonitor());
						}
					}
				}
			}

			// save files
			for (ICompilationUnit cu : cus) {
				cu.commitWorkingCopy(true, new NullProgressMonitor());
				cu.save(new NullProgressMonitor(), true);
			}
		}
		catch (Exception e) {
			throw new CoreException(
					new Status(IStatus.ERROR, "org.archstudio.utils.eclipse.formatcode", e.getMessage(), e));
		}
		finally {
			for (ICompilationUnit cu : cuWorkingCopies) {
				cu.discardWorkingCopy();
			}
		}
	}
}
