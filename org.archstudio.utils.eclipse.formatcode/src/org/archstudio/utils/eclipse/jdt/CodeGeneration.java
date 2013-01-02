package org.archstudio.utils.eclipse.jdt;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
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
import com.google.common.primitives.Ints;

@SuppressWarnings({ "restriction", "unchecked" })
public class CodeGeneration {

	public static void formatCode(IProject project) throws CoreException {
		formatCode(project, null);
	}

	public static void formatCode(IFile file) throws CoreException {
		formatCode(file.getProject(), file);
	}

	public static void formatCode(IProject project, IFile file) throws CoreException {
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

			IJavaProject javaProject = JavaCore.create(project);
			SAXParserFactory factory = SAXParserFactory.newInstance();

			// scan for java files
			Collection<ICompilationUnit> cus = Lists.newArrayList();
			{
				List<IFolder> folders = Lists.newArrayList(project.getFolder("src"));
				while (folders.size() > 0) {
					IFolder f = folders.remove(folders.size() - 1);
					if (f.exists()) {
						for (IResource r : f.members()) {
							if (r instanceof IFile && "java".equalsIgnoreCase(((IFile) r).getFileExtension())) {
								ICompilationUnit cu = JavaCore.createCompilationUnitFrom((IFile) r);
								cu.becomeWorkingCopy(new NullProgressMonitor());
								cus.add(cu);
							}
							else if (r instanceof IFolder) {
								folders.add((IFolder) r);
							}
						}
					}
				}
			}

			// get options
			CleanUpOptions cuOptions = new CleanUpOptions();
			{
				final Map<String, String> options = new HashMap<String, String>();
				try {
					factory.newSAXParser()
							.parse(new URL("platform:/plugin/org.archstudio.core/res/eclipse/ArchStudio Clean Up.xml")
									.openStream(),
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

			// iterate through cleanups
			{
				for (ICleanUp cleanup : JavaPlugin.getDefault().getCleanUpRegistry().createCleanUps()) {
					if (cleanup instanceof CodeFormatCleanUp)
						continue; // we do this later

					cleanup.setOptions(cuOptions);
					RefactoringStatus status = cleanup.checkPreConditions(javaProject,
							cus.toArray(new ICompilationUnit[cus.size()]), new NullProgressMonitor());
					if (status.isOK()) {
						for (ICompilationUnit cu : cus) {
							if (file != null) {
								if (!cu.getResource().getFullPath().equals(file.getFullPath()))
									continue;
							}
							CompilationUnit cUnit = null;
							if (cleanup.getRequirements().requiresAST()) {
								ASTParser parser = ASTParser.newParser(AST.JLS4);
								parser.setProject(javaProject);
								parser.setKind(ASTParser.K_COMPILATION_UNIT);
								parser.setSource(cu);
								parser.setResolveBindings(true);
								cUnit = (CompilationUnit) parser.createAST(new NullProgressMonitor());
							}
							ICleanUpFix fix = cleanup.createFix(new CleanUpContext(cu, cUnit));
							if (fix != null) {
								CompilationUnitChange change = fix.createChange(new NullProgressMonitor());
								if (change.isEnabled()) {
									cu.applyTextEdit(change.getEdit(), new NullProgressMonitor());
								}
							}
						}
					}
					cleanup.checkPostConditions(new NullProgressMonitor());
				}
			}

			// get format options
			final Map<String, String> options = new HashMap<String, String>();
			{
				options.putAll(JavaCore.getOptions());
				options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_6);
				options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_6);
				options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_6);

				// read settings from format file
				try {
					factory.newSAXParser()
							.parse(new URL(
									"platform:/plugin/org.archstudio.core/res/eclipse/ArchStudio Code Formatting.xml")
									.openStream(),
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
			}

			// format file
			{
				CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(options, ToolFactory.M_FORMAT_NEW);
				for (ICompilationUnit cu : cus) {
					if (file != null) {
						if (!cu.getResource().getFullPath().equals(file.getFullPath()))
							continue;
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
	}
}
