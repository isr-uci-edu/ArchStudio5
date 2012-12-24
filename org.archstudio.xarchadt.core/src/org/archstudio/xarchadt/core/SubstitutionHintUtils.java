package org.archstudio.xarchadt.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint.HintType;
import org.archstudio.xarchadt.core.internal.BasicXArchADTSubstitutionHint;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

class SubstitutionHintUtils {
	private static final String DOCUMENT_ROOT_NAME = "DocumentRoot";

	/**
	 * Find all hints in all the packages.
	 * 
	 * @param allEPackages
	 *            All the EPackages to check for hints
	 * @return List of all substitution hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTSubstitutionHint> parseSubstitutionHints(Collection<EPackage> allEPackages) {
		List<IXArchADTSubstitutionHint> allHints = new ArrayList<IXArchADTSubstitutionHint>();
		for (EPackage ePackage : allEPackages) {
			allHints.addAll(parseSubstitutionHints(ePackage));
		}
		return allHints;
	}

	/**
	 * Find all substitution hints in the given package.
	 * 
	 * @param ePackage
	 *            The package to search.
	 * @return List of all substitution hints found in all the factories in the
	 *         map.
	 */
	public static List<IXArchADTSubstitutionHint> parseSubstitutionHints(EPackage ePackage) {
		List<IXArchADTSubstitutionHint> hintList = new ArrayList<IXArchADTSubstitutionHint>();

		EClassifier eClassifier = ePackage.getEClassifier(DOCUMENT_ROOT_NAME);
		if (eClassifier != null && eClassifier instanceof EClass) {
			EClass eClass = (EClass) eClassifier;
			for (EStructuralFeature feature : eClass.getEAllStructuralFeatures()) {
				if (feature instanceof EReference) {
					EReference eReference = (EReference) feature;
					EAnnotation eAnnotation = eReference.getEAnnotation(ExtendedMetaData.ANNOTATION_URI);
					if (eAnnotation != null) {
						EMap<String, String> detailsMap = eAnnotation.getDetails();
						if (detailsMap != null) {
							String affiliation = detailsMap.get("affiliation");
							if (affiliation != null) {
								int hashIndex = affiliation.lastIndexOf("#");
								if (hashIndex != -1) {
									String sourceNsURI = affiliation.substring(0, hashIndex);
									String sourceTypeName = affiliation.substring(hashIndex + 1);

									EClassifier eType = eReference.getEType();
									if (eType != null) {
										String targetNsURI = eType.getEPackage().getNsURI();
										String targetTypeName = eType.getName();
										hintList.add(new BasicXArchADTSubstitutionHint(HintType.SUBSTITUTION_GROUP,
												sourceNsURI, sourceTypeName, targetNsURI, targetTypeName));
									}

								}
							}
						}
					}
				}
			}
		}
		return hintList;
	}
}
