package org.archstudio.schematron.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.archlight.ArchlightTest;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SchematronTestFile {

	public static final String SCHEMATRON_NSURI = "http://www.ascc.net/xml/schematron";

	protected String toolID;
	protected String sourceURL;
	protected Document document;

	protected List<ArchlightTest> archlightTests;

	protected List<String> parseWarnings;

	private SchematronTestFile(SchematronTestFile copyMe) {
		this.toolID = copyMe.toolID;
		this.sourceURL = copyMe.sourceURL;
		if (copyMe.archlightTests != null) {
			archlightTests = new ArrayList<ArchlightTest>(copyMe.archlightTests);
		}
		if (copyMe.parseWarnings != null) {
			parseWarnings = new ArrayList<String>(copyMe.parseWarnings);
		}
		if (copyMe.document != null) {
			document = (Document) copyMe.document.cloneNode(true);
		}
	}

	protected SchematronTestFile(String toolID, String sourceURL, String xmlContents)
			throws SchematronTestFileParseException {
		this.toolID = toolID;
		this.sourceURL = sourceURL;
		parseContents(xmlContents);
	}

	protected void parseContents(String xmlContents) throws SchematronTestFileParseException {
		try {
			List<ArchlightTest> newArchlightTests = new ArrayList<ArchlightTest>();
			List<String> newParseWarnings = new ArrayList<String>();

			StringReader r = new StringReader(xmlContents);
			this.document = SchematronUtils.parseToDocument(r);

			Element rootElement = this.document.getDocumentElement();
			if (rootElement == null) {
				throw new SchematronTestFileParseException("Tests file missing root element"
						+ (sourceURL == null ? "" : ": " + sourceURL));
			}
			String nsuri = rootElement.getAttribute("xmlns");
			if (nsuri == null) {
				throw new SchematronTestFileParseException("Tests file missing xmlns declaration"
						+ (sourceURL == null ? "" : ": " + sourceURL));
			}
			nsuri = nsuri.trim();
			if (!nsuri.equals(SCHEMATRON_NSURI)) {
				throw new SchematronTestFileParseException("Tests file URI must be " + SCHEMATRON_NSURI + ": "
						+ (sourceURL == null ? "" : sourceURL));
			}

			//Find all the patterns and get their IDs
			NodeList topLevelChildren = rootElement.getChildNodes();
			for (int i = 0; i < topLevelChildren.getLength(); i++) {
				Node child = topLevelChildren.item(i);
				if (child instanceof Element) {
					Element childElt = (Element) child;
					String tagName = childElt.getTagName();
					if (tagName != null && tagName.equals("pattern")) {
						String testUID = childElt.getAttribute("id");
						if (testUID == null || testUID.trim().length() == 0) {
							newParseWarnings.add("Warning: Schematron tests file has pattern with no UID"
									+ (sourceURL == null ? "" : ": " + sourceURL));
						}
						else {
							String testCategory = childElt.getAttribute("name");
							if (testCategory == null || testCategory.trim().length() == 0) {
								newParseWarnings
										.add("Warning: Schematron tests file has pattern with no category (name)"
												+ (sourceURL == null ? "" : ": " + sourceURL));
								testCategory = "UnknownTest/" + testUID;
							}
							String testDescription = childElt.getAttribute("description");
							if (testDescription == null || testDescription.trim().length() == 0) {
								newParseWarnings.add("Warning: Schematron tests file has pattern with no description"
										+ (sourceURL == null ? "" : ": " + sourceURL)
										+ " has pattern with no description.");
								testDescription = "[No Description]";
							}
							ArchlightTest tt = new ArchlightTest(testUID.trim(), toolID, testCategory.trim(),
									testDescription.trim());
							newArchlightTests.add(tt);
						}
					}
				}
			}

			archlightTests = newArchlightTests;
			parseWarnings = newParseWarnings;
		}
		catch (ParserConfigurationException pce) {
			throw new SchematronTestFileParseException("XML Parser Configuration Error parsing document"
					+ (sourceURL == null ? "" : ": " + sourceURL), pce);
		}
		catch (SAXException se) {
			String loc = "";
			if (se instanceof SAXParseException) {
				SAXParseException spe = (SAXParseException) se;
				int lineNo = spe.getLineNumber();
				int colNo = spe.getColumnNumber();
				loc = " at line " + lineNo + ", column " + colNo;
			}

			throw new SchematronTestFileParseException("XML Parse Error parsing document" + loc
					+ (sourceURL == null ? "" : ": " + sourceURL), se);
		}
		catch (IOException ioe) {
			throw new SchematronTestFileParseException("I/O Error parsing document"
					+ (sourceURL == null ? "" : ": " + sourceURL), ioe);
		}
	}

	public List<? extends ArchlightTest> getArchlightTests() {
		return Collections.unmodifiableList(new ArrayList<ArchlightTest>(archlightTests));
	}

	public Document getDocument() {
		return this.document;
	}

	public String getSourceURL() {
		return sourceURL;
	}

	public List<String> getParseWarnings() {
		return Collections.unmodifiableList(new ArrayList<String>(parseWarnings));
	}

	public static SchematronTestFile create(String toolID, String sourceURL, InputStream is)
			throws SchematronTestFileParseException, IOException {
		//Note: the sourceURL here is only used to identify the document,
		//not to reload it or anything.
		StringBuffer sb = new StringBuffer();
		byte[] buf = new byte[2048];
		while (true) {
			int bytesRead = is.read(buf);
			if (bytesRead == -1) {
				break;
			}
			sb.append(new String(buf, 0, bytesRead));
		}
		is.close();

		SchematronTestFile stf = new SchematronTestFile(toolID, sourceURL, sb.toString());
		return stf;
	}

	private static InputStream openURI(String uriString) throws IOException {
		URI uri = URI.createURI(uriString);
		InputStream is = URIConverter.INSTANCE.createInputStream(uri);
		return is;
	}

	public static SchematronTestFile create(String toolID, String urlString) throws SchematronTestFileParseException,
			IOException, FileNotFoundException, MalformedURLException {
		InputStream is = openURI(urlString);
		return create(toolID, urlString, is);
	}

	public static SchematronTestFile create(SchematronTestFile originalTestFile, List<String> testUIDs)
			throws SchematronTestFileParseException {
		//For faster lookups
		HashSet<String> testUIDSet = new HashSet<String>(testUIDs);

		SchematronTestFile newFile = new SchematronTestFile(originalTestFile);
		Document newFileDoc = newFile.getDocument();
		if (newFileDoc == null) {
			return newFile;
		}

		Element rootElement = newFileDoc.getDocumentElement();
		if (rootElement == null) {
			//This shoudln't happen
			throw new SchematronTestFileParseException("Tests file missing root element"
					+ (newFile.sourceURL == null ? "" : ": " + newFile.sourceURL));
		}

		//Find all the patterns and get their IDs;
		//remove the IDs that don't correspond to tests we have.
		NodeList topLevelChildren = rootElement.getChildNodes();
		for (int i = 0; i < topLevelChildren.getLength(); i++) {
			Node child = topLevelChildren.item(i);
			if (child instanceof Element) {
				Element childElt = (Element) child;
				String tagName = childElt.getTagName();
				if (tagName != null && tagName.equals("pattern")) {
					String testUID = childElt.getAttribute("id");
					boolean found = false;
					if (testUID != null) {
						if (testUIDSet.contains(testUID)) {
							found = true;
						}
					}
					if (!found) {
						rootElement.removeChild(childElt);
					}
				}
			}
		}
		return newFile;
	}
}
