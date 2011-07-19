package org.archstudio.schematron.core;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SchematronTester {

	protected String xmlDocumentToTest;
	protected SchematronTestFile testFile;
	protected TransformerFactory transformerFactory;
	protected Document result = null;

	public SchematronTester(String xmlDocumentToTest, SchematronTestFile testFile) {
		this.xmlDocumentToTest = xmlDocumentToTest;
		this.testFile = testFile;
	}

	public void runTest() throws SchematronInitializationException, SchematronTestException {
		DOMSource metastylesheetSource = SchematronUtils.getSchematronMetastylesheet();
		Transformer transformer1 = SchematronUtils.getTransformer(metastylesheetSource);

		DOMSource rulesFileSource = null;
		Document doc = testFile.getDocument();
		rulesFileSource = new DOMSource(doc);

		DOMResult tempStylesheetResult = SchematronUtils.getEmptyDOMResult();
		try {
			transformer1.transform(rulesFileSource, tempStylesheetResult);
		}
		catch (TransformerException te) {
			throw new SchematronTestException(te);
		}

		DOMSource tempStylesheetSource = new DOMSource(tempStylesheetResult.getNode());
		Transformer transformer2 = SchematronUtils.getTransformer(tempStylesheetSource);

		DOMSource documentToTestSource = null;
		try {
			StringReader r = new StringReader(xmlDocumentToTest);
			Document docToTest = SchematronUtils.parseToDocument(r);
			r.close();
			documentToTestSource = new DOMSource(docToTest);
		}
		catch (ParserConfigurationException pce) {
			throw new SchematronTestException(pce);
		}
		catch (SAXException se) {
			throw new SchematronTestException(se);
		}
		catch (IOException ioe) {
			throw new SchematronTestException(ioe);
		}

		DOMResult finalResult = SchematronUtils.getEmptyDOMResult();
		try {
			transformer2.transform(documentToTestSource, finalResult);
			this.result = (Document) finalResult.getNode();
		}
		catch (TransformerException te) {
			throw new SchematronTestException(te);
		}
	}

	public Document getResult() {
		return result;
	}

}
