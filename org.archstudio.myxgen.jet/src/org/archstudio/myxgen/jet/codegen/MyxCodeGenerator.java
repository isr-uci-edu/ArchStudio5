package org.archstudio.myxgen.jet.codegen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.eclipse.extension.MyxGenWorkspaceExtensions;
import org.archstudio.myxgen.jet.Activator;
import org.archstudio.myxgen.jet.util.TextUtil;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jet.JET2Platform;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

public class MyxCodeGenerator {

	private final static String JET_MYX_COMP_TEMPLATE = "templates/MyxComp.java.jet";
	private final static String JET_MYX_COMP_STUB_TEMPLATE = "templates/MyxCompStub.java.jet";

	private final IJavaProject javaProject;
	private final File formatterFile = null;

	public MyxCodeGenerator(IJavaProject javaProject) {
		this.javaProject = javaProject;
	}

	public void generateCode() {
		for (MyxGenBrick brick : MyxGenWorkspaceExtensions.getMyxGenBricks(javaProject.getProject())) {
			try {
				generateCode(brick);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void generateCode(MyxGenBrick brick) {
		generateCode(brick, JET_MYX_COMP_STUB_TEMPLATE, checkNotNull(brick.getStubClassName()));
		generateCode(brick, JET_MYX_COMP_TEMPLATE, checkNotNull(brick.getClassName()));
	}

	private void generateCode(MyxGenBrick brick, String jetTemplatePath, String outputJavaClassName) {
		try {
			Map<String, Object> variables = Maps.newHashMap();

			variables.put(MyxGenBrick.class.getName(), brick);
			variables.put("org.eclipse.jet.resource.project.name", javaProject.getProject().getName());
			variables.put("org.archstudio.myxgen.src.folder", "src");
			variables.put("org.archstudio.myxgen.package", TextUtil.getPackagePart(outputJavaClassName));
			variables.put("org.archstudio.myxgen.class", TextUtil.getClassPart(outputJavaClassName));
			variables.put("org.archstudio.myxgen.template", jetTemplatePath);

			URL templateURL = Activator.getSingleton().getContext().getBundle().getResource("templates/MyxGen.xml");
			String templateContents = new String(SystemUtils.blt(templateURL.openStream()));
			IStatus status = JET2Platform.runTransformOnString(Activator.getSingleton().getId(), templateContents,
					"xml", variables, new NullProgressMonitor());

			if (!status.isOK()) {
				for (IStatus s : status.getChildren()) {
					System.err.println(s);
					if (s.getException() != null) {
						s.getException().printStackTrace();
					}
				}
				throw new Exception(status.getMessage(), status.getException());
			}

			IFile generatedFile = javaProject.getProject().getFile(
					"src/" + outputJavaClassName.replace('.', '/') + ".java");
			formatCode(generatedFile);
			generatedFile.refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void formatCode(IFile generatedFile) {
		try {
			// get format options
			final Map<String, String> formatOptions = new HashMap<String, String>();
			formatOptions.putAll(JavaCore.getOptions());
			formatOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
			formatOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
			formatOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);

			if (formatterFile != null && formatterFile.exists()) {

				// read settings from format file
				SAXParserFactory factory = SAXParserFactory.newInstance();
				factory.newSAXParser().parse(formatterFile, new DefaultHandler() {

					public void startElement(String uri, String localName, String qName, Attributes attributes)
							throws SAXException {
						if ("setting".equals(qName)) {
							formatOptions.put(attributes.getValue("id"), attributes.getValue("value"));
						}
					}
				});
			}

			// format code
			String contents = new String(SystemUtils.blt(generatedFile.getContents()));
			CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(formatOptions, //
					ToolFactory.M_FORMAT_EXISTING);
			IDocument doc = new Document(contents);
			for (int kind : Ints.asList(CodeFormatter.K_COMPILATION_UNIT)) {
				TextEdit edit = codeFormatter.format(kind, doc.get(), 0, doc.get().length(), 0, null);
				if (edit != null) {
					edit.apply(doc);
				}
			}
			generatedFile.setContents(new ByteArrayInputStream(doc.get().getBytes()), true, true,
					new NullProgressMonitor());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
