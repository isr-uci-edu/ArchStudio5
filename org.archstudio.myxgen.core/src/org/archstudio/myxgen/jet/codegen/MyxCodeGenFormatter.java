package org.archstudio.myxgen.jet.codegen;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A CodeFormatter. This class formats the generated source code.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 * 
 */
public class MyxCodeGenFormatter {

	/** line separator */
	private static final String lineseparator = System.getProperty("line.separator");

	private static final Map<String, String> formatOptions = new HashMap<String, String>();
	static {
		createFormatOptions();
	}

	/**
	 * file name of the code formatter style TODO: temporary
	 */
	private static String formatterFileName = null;

	private static void createFormatOptions() {
		//TODO: need to read ArchStudio code style
		formatOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
		formatOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
		formatOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);
		readPreferences();
	}

	/**
	 * Formats the file following jdk 1.5 code style
	 * 
	 * @param file
	 */
	static void formatCode(IFile file) {

		//reads the file content
		String contents;
		try {
			contents = toString(file);
		}
		catch (CoreException e) {
			e.printStackTrace();
			return;
		}

		//formats
		CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(formatOptions, ToolFactory.M_FORMAT_EXISTING);

		IDocument doc = new Document(contents);
		TextEdit edit = codeFormatter.format(CodeFormatter.K_COMPILATION_UNIT, // removed for 3.3: | CodeFormatter.F_INCLUDE_COMMENTS,
				doc.get(), 0, doc.get().length(), 0, null);
		try {
			edit.apply(doc);
		}
		catch (MalformedTreeException e) {
			e.printStackTrace();
			return;
		}
		catch (BadLocationException e) {
			e.printStackTrace();
			return;
		}

		//updates the file
		contents = doc.get();
		try {
			updateFile(file, contents);
		}
		catch (CoreException e) {
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Converts the content of the file into String
	 * 
	 * @param file
	 * @return
	 * @throws CoreException
	 */
	private static String toString(IFile file) throws CoreException {
		InputStream is = file.getContents();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + lineseparator);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * Updates the file with the given contents
	 * 
	 * @param file
	 * @param contents
	 * @throws CoreException
	 */
	private static void updateFile(IFile file, String contents) throws CoreException {
		ByteArrayInputStream bais = new ByteArrayInputStream(contents.getBytes());
		file.setContents(bais, IResource.KEEP_HISTORY | IResource.FORCE, new NullProgressMonitor());
	}

	@SuppressWarnings("unchecked")
	static void readPreferences() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		InputStream is = null;
		try {
			parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler() {
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if ("setting".equals(qName)) {
						formatOptions.put(attributes.getValue("id"), attributes.getValue("value"));
						//						for(int i = 0; i < attributes.getLength(); i++) {
						//							System.out.println(attributes.getQName(i) + " = " + attributes.getValue(i));
						//						}
					}
				}
			};

			//default setting
			formatOptions.putAll(JavaCore.getOptions());
			formatOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_5);
			formatOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_5);
			formatOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_5);

			//TODO: need to read Eclipse's 
			if (formatterFileName == null) {
				System.out.println("System Default Codeformat");
				return;
			}
			File f = new File(formatterFileName);
			if (!f.exists()) {
				return;
			}
			is = new FileInputStream(f);

			//reads the preference
			parser.parse(is, handler);
			System.out.println("Codeformat " + formatterFileName);

		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (SAXException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (is != null) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Sets the code formatter file name TODO: this is a temporary way until, Preferences ADT is used.
	 * 
	 * @param fileName
	 */
	public static void setCodeFormatterFileName(String fileName) {
		MyxCodeGenFormatter.formatterFileName = fileName;
		readPreferences();
	}

}
