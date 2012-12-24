package org.archstudio.xarchadt.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint.HintType;
import org.archstudio.xarchadt.core.internal.BasicXArchADTSubstitutionHint;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class ExtensionHintUtils {
	public static final String EXTENSION_HINT_URI = Hints_3_0Package.eINSTANCE.getNsURI();

	/**
	 * Find all hints in all the packages.
	 * 
	 * @param allEPackages
	 *            All the EPackages to check for hints
	 * @return List of all extension hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTSubstitutionHint> parseExtensionHints(Collection<EPackage> allEPackages) {
		List<IXArchADTSubstitutionHint> allHints = new ArrayList<IXArchADTSubstitutionHint>();
		for (EPackage ePackage : allEPackages) {
			allHints.addAll(parseExtensionHints(ePackage));
		}
		return allHints;
	}

	/**
	 * Find all hints in the given package.
	 * 
	 * @param ePackage
	 *            The package to search.
	 * @return List of all extension hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTSubstitutionHint> parseExtensionHints(EPackage ePackage) {
		EAnnotation eAnnotation = ePackage.getEAnnotation(EXTENSION_HINT_URI);
		if (eAnnotation != null) {
			String extensionHintsText = eAnnotation.getDetails().get("appinfo");
			return parseExtensionHints(extensionHintsText);
		}
		return Collections.emptyList();
	}

	public static List<IXArchADTSubstitutionHint> parseExtensionHints(String xml) {
		List<IXArchADTSubstitutionHint> extensionHints = new ArrayList<IXArchADTSubstitutionHint>();
		try {
			Document doc = parseToDocument(new StringReader("<hints>" + xml + "</hints>"));
			Element hintsElement = doc.getDocumentElement();
			NodeList childNodeList = hintsElement.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				Node childNode = childNodeList.item(i);
				if (childNode instanceof Element) {
					Element childElement = (Element) childNode;
					if (childElement.getLocalName().equals("hint")) {
						String extensionNsURI = childElement.getAttribute("extensionSchema");
						String extensionTypeName = childElement.getAttribute("extensionType");
						String targetNsURI = childElement.getAttribute("targetSchema");
						String targetTypeName = childElement.getAttribute("targetType");

						if (extensionNsURI != null && extensionTypeName != null && targetNsURI != null
								&& targetTypeName != null) {
							extensionHints.add(new BasicXArchADTSubstitutionHint(HintType.EXTENSION, extensionNsURI,
									extensionTypeName, targetNsURI, targetTypeName));
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return extensionHints;
	}

	public static Document parseToDocument(java.io.Reader r) throws SAXException, IOException,
			ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		dbf.setIgnoringElementContentWhitespace(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(r));
		return doc;
	}
}
