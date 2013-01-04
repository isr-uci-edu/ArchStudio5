package org.archstudio.schematron.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.archstudio.archlight.ArchlightElementIdentifier;
import org.archstudio.archlight.ArchlightIssue;
import org.archstudio.archlight.ArchlightTestResult;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class SchematronTestResultParser {

	public static final String CONTENT_ELEMENT_DELIMITER_REGEX = "\\|\\*\\|";

	//Returns an array of objects.  Each Object is either a TronTestResult
	//or a SchematronTestException in case the test result parsing failed.
	public static List<? extends Object> parseTestResults(IXArchADT xarch, ObjRef documentRef, String toolID,
			Document schematronProcessingResults) {
		List<Object> testResultList = new ArrayList<Object>();
		List<TestResultSnippet> resultSnippets = null;
		try {
			resultSnippets = parseTestResultSnippets(schematronProcessingResults);
			for (TestResultSnippet resultSnippet : resultSnippets) {
				try {
					ArchlightTestResult testResult = parseResultSnippet(xarch, documentRef, toolID, resultSnippet);
					testResultList.add(testResult);
				}
				catch (SchematronTestException ste) {
					testResultList.add(ste);
				}
			}
		}
		catch (SchematronTestException ste) {
			testResultList.add(ste);
		}
		return Collections.unmodifiableList(testResultList);
	}

	private static ArchlightTestResult parseResultSnippet(IXArchADT xarch, ObjRef documentRef, String toolID,
			TestResultSnippet snippet) throws SchematronTestException {
		String testUID = snippet.activePatternElement.getAttribute("id");
		if (testUID == null) {
			String desc = "Test result had no UID";
			String name = snippet.activePatternElement.getAttribute("name");
			if (name != null) {
				desc += ": " + name;
			}
			throw new SchematronTestException(desc);
		}
		List<ArchlightIssue> archlightIssues = new ArrayList<ArchlightIssue>(snippet.failedAssertElements.length);
		for (Element failedAssertElt : snippet.failedAssertElements) {
			Element contentElt = (Element) failedAssertElt.getFirstChild();
			String content = getElementContents(contentElt);
			if (content == null || content.equals("")) {
				SchematronTestException ste = new SchematronTestException("Failed assert had no content.");
				ste.setTestUID(testUID);
				throw ste;
			}

			ContentProperties contentProperties = ContentProperties.create(content);
			//We have the testUID
			//We have the documentRef
			//We have the toolID,
			int severity = ArchlightIssue.SEVERITY_ERROR;
			String severityString = contentProperties.getSeverity();
			if (severityString != null) {
				if (severityString.toLowerCase().equals("warning")) {
					severity = ArchlightIssue.SEVERITY_WARNING;
				}
				else if (severityString.toLowerCase().equals("info")) {
					severity = ArchlightIssue.SEVERITY_INFO;
				}
			}
			//We have the severity.
			String headline = contentProperties.getText();
			if (headline == null) {
				SchematronTestException ste = new SchematronTestException("Failed assert had no text.");
				ste.setTestUID(testUID);
				throw ste;
			}
			//We have the headline.
			String detailedDescription = contentProperties.getDetail();
			//if(detailedDescription == null){
			//	detailedDescription = "[No additional detail.]";
			//}
			//We have the detail
			String iconHref = contentProperties.getIconHref();
			//We have the icon href
			List<ArchlightElementIdentifier> elementIdentifiers = contentProperties.getElementIdentifiers();
			//We have the element identifiers.
			if (elementIdentifiers.isEmpty()) {
				//Can't find it.  Try resolving the XPath?
			}
			/*
			 * else{ String id = elementIdentifiers[0].getElementID(); ObjRef ref = xarch.getByID(documentRef, id);
			 * System.out.println(ref); System.out.println(xarch.getXArchPath(ref)); }
			 */
			archlightIssues.add(new ArchlightIssue(testUID, documentRef, toolID, severity, headline,
					detailedDescription, iconHref, elementIdentifiers));
		}
		return new ArchlightTestResult(documentRef, testUID, archlightIssues);
	}

	private static String getElementContents(Element elt) {
		elt.normalize();
		NodeList childNodes = elt.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			if (childNodes.item(i) instanceof Text) {
				return ((Text) childNodes.item(i)).getNodeValue();
			}
		}
		return "";
	}

	//The test results file is organized weirdly.  It looks like:
	//<ns...>
	//<ns...> ...
	//<active-pattern ... />
	//<failed-assert ... />
	//<failed-assert ... />
	//<active-pattern ... />
	//<failed-assert ... />
	//...
	//So all the failed assertions are at the top level but split
	//up by active-patterns.  There will always be an active-pattern
	//heading even if there are no failed-asserts
	private static List<TestResultSnippet> parseTestResultSnippets(Document schematronProcessingResults)
			throws SchematronTestException {
		List<TestResultSnippet> testResultSnippetList = new ArrayList<TestResultSnippet>();

		Element rootElement = schematronProcessingResults.getDocumentElement();
		NodeList topLevelElements = rootElement.getChildNodes();

		Element activePatternElement = null;
		List<Element> failedAssertElementList = new ArrayList<Element>();
		for (int i = 0; i < topLevelElements.getLength(); i++) {
			Node childNode = topLevelElements.item(i);
			if (childNode instanceof Element) {
				Element childElt = (Element) childNode;
				String tagName = childElt.getLocalName();
				if (tagName.equals("active-pattern")) {
					if (activePatternElement != null) {
						//Save the old snippet
						TestResultSnippet snippet = new TestResultSnippet();
						snippet.activePatternElement = activePatternElement;
						snippet.failedAssertElements = failedAssertElementList.toArray(new Element[0]);
						testResultSnippetList.add(snippet);
					}
					activePatternElement = childElt;
					failedAssertElementList.clear();
				}
				if (tagName.equals("failed-assert")) {
					failedAssertElementList.add(childElt);
				}
			}
		}
		if (activePatternElement != null) {
			//Save the old snippet
			TestResultSnippet snippet = new TestResultSnippet();
			snippet.activePatternElement = activePatternElement;
			snippet.failedAssertElements = failedAssertElementList.toArray(new Element[0]);
			testResultSnippetList.add(snippet);
		}

		return Collections.unmodifiableList(testResultSnippetList);
	}

	private static class TestResultSnippet {
		public Element activePatternElement;
		public Element[] failedAssertElements;
	}

	private static class ContentProperties {
		private final Properties properties;

		public ContentProperties(Properties properties) {
			this.properties = properties;
		}

		public String getSeverity() {
			return properties.getProperty("severity");
		}

		public String getIconHref() {
			return properties.getProperty("iconHref");
		}

		public String getDetail() {
			return properties.getProperty("detail");
		}

		public String getText() {
			return properties.getProperty("text");
		}

		public List<ArchlightElementIdentifier> getElementIdentifiers() {
			List<ArchlightElementIdentifier> elementIdentifierList = new ArrayList<ArchlightElementIdentifier>();

			//Get the default ID
			String id = properties.getProperty("id");
			if (id != null) {
				String iddesc = properties.getProperty("iddesc");
				ArchlightElementIdentifier eltIdentifier = new ArchlightElementIdentifier(id, iddesc);
				elementIdentifierList.add(eltIdentifier);
			}

			int index = 0;
			while (true) {
				String indexid = properties.getProperty("id" + index);
				if (indexid != null) {
					String indexiddesc = properties.getProperty("iddesc" + index);
					ArchlightElementIdentifier eltIdentifier = new ArchlightElementIdentifier(indexid, indexiddesc);
					elementIdentifierList.add(eltIdentifier);
					index++;
				}
				else {
					break;
				}
			}
			return Collections.unmodifiableList(elementIdentifierList);
		}

		public static ContentProperties create(String content) throws SchematronTestException {
			Properties newProperties = new Properties();
			String[] propertyElements = content.split(CONTENT_ELEMENT_DELIMITER_REGEX);
			for (String propertyElement : propertyElements) {
				int equalsIndex = propertyElement.indexOf("=");
				if (equalsIndex == -1) {
					if (newProperties.containsKey("text")) {
						throw new SchematronTestException("Test result has multiple segments with name 'text'");
					}
					newProperties.put("text", propertyElement.trim());
				}
				else {
					String propName = propertyElement.substring(0, equalsIndex).trim();
					String propValue = propertyElement.substring(equalsIndex + 1).trim();
					if (newProperties.containsKey(propName)) {
						throw new SchematronTestException("Test result has multiple segments with name '" + propName
								+ "'");
					}
					newProperties.put(propName, propValue);
				}
			}
			return new ContentProperties(newProperties);
		}
	}
}
