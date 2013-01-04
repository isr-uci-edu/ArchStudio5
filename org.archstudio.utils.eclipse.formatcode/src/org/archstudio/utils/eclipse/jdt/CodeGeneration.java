package org.archstudio.utils.eclipse.jdt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
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
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.fix.CodeFormatCleanUp;
import org.eclipse.jdt.ui.cleanup.CleanUpContext;
import org.eclipse.jdt.ui.cleanup.CleanUpOptions;
import org.eclipse.jdt.ui.cleanup.ICleanUp;
import org.eclipse.jdt.ui.cleanup.ICleanUpFix;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.text.edits.TextEdit;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

@SuppressWarnings({ "restriction", "unchecked" })
public class CodeGeneration {

	public static void formatCode(final IProject project) throws CoreException {
		formatCode(project, null);
	}

	public static void formatCode(IFile file) throws CoreException {
		formatCode(file.getProject(), file);
	}

	public static void formatCode(IProject project, final IFile file) throws CoreException {
		Collection<ICompilationUnit> cus = Lists.newArrayList();
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

			final IJavaProject javaProject = JavaCore.create(project);
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// scan for java files
			{
				List<IFolder> folders = Lists.newArrayList(project.getFolder("src"));
				while (folders.size() > 0) {
					IFolder f = folders.remove(folders.size() - 1);
					if (f.exists()) {
						for (IResource r : f.members()) {
							if (r instanceof IFile && "java".equalsIgnoreCase(((IFile) r).getFileExtension())) {
								ICompilationUnit cu = JavaCore.createCompilationUnitFrom((IFile) r);
								cu = cu.getPrimary();
								cu = cu.getWorkingCopy(new NullProgressMonitor());
								if (!cu.isWorkingCopy())
									System.out.println(cu);
								cus.add(cu);
							}
							else if (r instanceof IFolder) {
								folders.add((IFolder) r);
							}
						}
					}
				}
			}

			// get cleanup options
			CleanUpOptions cuOptions = new CleanUpOptions();
			{
				final Map<String, String> options = new HashMap<String, String>();
				try {
					factory.newSAXParser().parse(CodeGeneration.class.getResourceAsStream("ArchStudio Clean Up.xml"),
							new DefaultHandler() {

								@Override
								public void startElement(String uri, String localName, String qName,
										Attributes attributes) throws SAXException {
									if ("setting".equals(qName)) {
										options.put(attributes.getValue("id"), attributes.getValue("value"));
									}
								}
							});
				}
				catch (Exception e) {
				}
				for (Entry<String, String> e : options.entrySet()) {
					cuOptions.setOption(e.getKey(), e.getValue());
				}
			}

			// get compiler options
			final Map<String, String> compilerOptions = Maps.newHashMap();
			{
				final Map<String, String> m = Maps.newHashMap(JavaCore.getOptions());
				// read settings from format file
				try {
					factory.newSAXParser().parse(
							CodeGeneration.class.getResourceAsStream("ArchStudio Code Formatting.xml"),
							new DefaultHandler() {

								@Override
								public void startElement(String uri, String localName, String qName,
										Attributes attributes) throws SAXException {
									if ("setting".equals(qName)) {
										m.put(attributes.getValue("id"), attributes.getValue("value"));
									}
								}
							});
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				m.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
				m.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
				m.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_6);
				m.put(JavaCore.COMPILER_PB_MISSING_OVERRIDE_ANNOTATION, JavaCore.WARNING);
				m.put(JavaCore.COMPILER_PB_MISSING_OVERRIDE_ANNOTATION_FOR_INTERFACE_METHOD_IMPLEMENTATION,
						JavaCore.ENABLED);
				m.put(JavaCore.COMPILER_PB_SUPPRESS_WARNINGS, JavaCore.DISABLED);
				m.put(JavaCore.COMPILER_PB_SUPPRESS_OPTIONAL_ERRORS, JavaCore.DISABLED);
				m.put(JavaCore.COMPILER_PB_MAX_PER_UNIT, "0");
				compilerOptions.putAll(m);
			}

			// iterate through cleanups
			{
				final List<ICleanUp> cleanups = Lists.newArrayList(JavaPlugin.getDefault().getCleanUpRegistry()
						.createCleanUps());
				for (Iterator<ICleanUp> i = cleanups.iterator(); i.hasNext();) {
					if (i.next() instanceof CodeFormatCleanUp) {
						i.remove(); // we do this later
					}
				}

				final List<CleanUpContext> cleanupContexts = Lists.newArrayList();
				for (final ICleanUp cleanup : cleanups) {
					try {
						cleanup.setOptions(cuOptions);
						RefactoringStatus status = cleanup.checkPreConditions(javaProject,
								cus.toArray(new ICompilationUnit[cus.size()]), new NullProgressMonitor());
						if (status.isOK()) {

							cleanupContexts.clear();
							if (cleanup.getRequirements().requiresAST()) {
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

			// format file
			{
				CodeFormatter codeFormatter = ToolFactory
						.createCodeFormatter(compilerOptions, ToolFactory.M_FORMAT_NEW);
				for (ICompilationUnit cu : cus) {
					if (file != null) {
						if (!cu.getResource().getFullPath().equals(file.getFullPath())) {
							continue;
						}
					}

					String contents = cu.getBuffer().getContents();
					for (int kind : Ints.asList(CodeFormatter.K_COMPILATION_UNIT | CodeFormatter.F_INCLUDE_COMMENTS)) {
						TextEdit edit = codeFormatter.format(kind, contents, 0, contents.length(), 0, null);
						if (edit != null) {
							cu.applyTextEdit(edit, new NullProgressMonitor());
						}
					}
				}
			}

			// save file
			{
				for (ICompilationUnit cu : cus) {
					cu.commitWorkingCopy(true, new NullProgressMonitor());
					cu.save(new NullProgressMonitor(), true);
				}
			}
		}
		catch (Exception e) {
			throw new CoreException(new Status(IStatus.ERROR, "org.archstudio.utils.eclipse.formatcode",
					e.getMessage(), e));
		}
		finally {
			for (ICompilationUnit cu : cus) {
				cu.discardWorkingCopy();
			}
		}
	}
}
