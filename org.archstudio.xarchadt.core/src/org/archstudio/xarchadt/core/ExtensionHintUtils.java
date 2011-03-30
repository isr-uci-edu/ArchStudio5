package org.archstudio.xarchadt.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;
import org.archstudio.xarchadt.common.BasicXArchADTExtensionHint;
import org.archstudio.xarchadt.common.IXArchADTExtensionHint;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

class ExtensionHintUtils {

	/**
	 * Find all hints in all the packages referenced by all the factories in the
	 * factory map.
	 * 
	 * @param factoryMap
	 *            Map mapping factory names to EFactories
	 * @return List of all extension hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTExtensionHint> parseExtensionHints(
			Map<String, EPackage> factoryMap) {
		List<IXArchADTExtensionHint> allHints = new ArrayList<IXArchADTExtensionHint>();
		for (Map.Entry<String, EPackage> entry : factoryMap.entrySet()) {
			allHints.addAll(parseExtensionHints(factoryMap, entry.getValue()));
		}
		return allHints;
	}

	/**
	 * Find all hints in the given package.
	 * 
	 * @param factoryMap
	 *            Map mapping factory names to EFactories
	 * @param ePackage
	 *            The package to search.
	 * @return List of all extension hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTExtensionHint> parseExtensionHints(
			Map<String, EPackage> factoryMap, EPackage ePackage) {
		EAnnotation eAnnotation = ePackage
				.getEAnnotation(Hints_3_0Package.eNS_URI);
		if (eAnnotation != null) {
			String extensionHintsText = eAnnotation.getDetails().get("appinfo");
			return parseExtensionHints(factoryMap, extensionHintsText);
		}
		return Collections.emptyList();
	}

	public static List<IXArchADTExtensionHint> parseExtensionHints(
			Map<String, EPackage> factoryMap, String xml) {
		List<IXArchADTExtensionHint> extensionHints = new ArrayList<IXArchADTExtensionHint>();
		try {
			Document doc = parseToDocument(new StringReader("<hints>" + xml
					+ "</hints>"));
			Element hintsElement = doc.getDocumentElement();
			NodeList childNodeList = hintsElement.getChildNodes();
			for (int i = 0; i < childNodeList.getLength(); i++) {
				Node childNode = childNodeList.item(i);
				if (childNode instanceof Element) {
					Element childElement = (Element) childNode;
					if (childElement.getLocalName().equals("hint")) {
						EPackage extensionSchema = EPackage.Registry.INSTANCE.getEPackage(childElement
								.getAttribute("extensionSchema"));
						String extensionTypeName = childElement
								.getAttribute("extensionType");
						EPackage targetSchema = EPackage.Registry.INSTANCE.getEPackage(childElement
								.getAttribute("targetSchema"));
						String targetTypeName = childElement
								.getAttribute("targetType");

						if ((extensionSchema != null)
								&& (extensionTypeName != null)
								&& (targetSchema != null)
								&& (targetTypeName != null)) {
							extensionHints
									.add(new BasicXArchADTExtensionHint(
											extensionSchema.getNsURI(),
											extensionTypeName,
											targetSchema.getNsURI(),
											targetTypeName));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extensionHints;
	}

	public static Document parseToDocument(java.io.Reader r)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setValidating(false);
		dbf.setIgnoringElementContentWhitespace(true);

		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new InputSource(r));
		return doc;
	}
}
