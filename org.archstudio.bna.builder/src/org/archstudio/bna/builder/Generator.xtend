package org.archstudio.bna.builder

import com.google.common.base.Preconditions
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.common.collect.Sets
import java.io.ByteArrayInputStream
import java.util.Collections
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.IConfigurationElement

class Generator {

	def private static String toFirstKeywordLower(String name) {
		var StringBuffer result = new StringBuffer();
		val int length = name.length
		var int index = 0
		while (index < length && Character.isUpperCase(name.charAt(index))) {
			index = index + 1
		}
		if (index > 1) {
			result.append(name.substring(0, index - 1).toLowerCase);
			result.append(name.substring(index - 1));
		} else {
			result.append(name.toFirstLower);
		}
		result.toString
	}

	def private static String toConstantName(String name) {
		var StringBuffer result = new StringBuffer();
		val int length = name.length
		var int start = 0
		var int index = 0
		while (start < length) {
			while (index < length && !Character.isLowerCase(name.charAt(index))) {
				index = index + 1
			}
			if (index - start > 1) {
				result.append(name.substring(start, index - 1).toUpperCase).append("_")
				start = index - 1;
			}
			while (index < length && Character.isLowerCase(name.charAt(index))) {
				index = index + 1
			}
			result.append(name.substring(start, index).toUpperCase).append("_");
			start = index;
		}
		result.toString.replaceAll("__", "_").replaceAll("^_|_$", "")
	}

	def private static Object elementPath(IConfigurationElement element) {
		new ElementPath(element)
	}

	def private static String elementName(IConfigurationElement element) {
		Preconditions.checkNotNull(element.getAttribute("name"), "Element %s is missing required attribute name",
			element.elementPath);
	}

	def private static List<IConfigurationElement> elementSortByName(List<IConfigurationElement> elements) {
		Collections.sort(elements, [x, y|x.elementName.compareTo(y.elementName)])
		elements
	}

	def private static List<Map.Entry<IConfigurationElement, IConfigurationElement>> sortByKeyElementName(
		Iterable<Map.Entry<IConfigurationElement, IConfigurationElement>> entries) {
		val List<Map.Entry<IConfigurationElement, IConfigurationElement>> sorted = Lists.newArrayList(entries)
		Collections.sort(sorted, [x, y|x.key.elementName.compareTo(y.key.elementName)])
		sorted
	}

	def private static String elementJavaDoc(IConfigurationElement element) {
		val List<IConfigurationElement> javaDocs = element.getChildren("JavaDoc")
		if (javaDocs.size > 0) {
			return '''«javaDocs.get(0).value.trim»
'''
		}
		""
	}

	def private static String elementPackageName(IConfigurationElement element) {
		var c = element
		while (c != null && !("Package".equals(c.name))) {
			val Object parent = c.parent
			c = null;
			if (parent instanceof IConfigurationElement)
				c = parent as IConfigurationElement
		}
		if (c != null)
			return c.elementName
		throw new IllegalArgumentException(element.elementPath.toString())
	}

	def private static String packageFileName(IConfigurationElement packageElement) {
		packageElement.elementName.replaceAll('\\.', '/')
	}

	def private static String packageName(IConfigurationElement packageElement) {
		packageElement.elementName
	}

	def private static String facetFileName(IConfigurationElement facet) {
		facet.facetClassName + ".java"
	}

	def private static String facetMutableFileName(IConfigurationElement facet) {
		facet.facetMutableClassName + ".java"
	}

	def private static String facetClassName(IConfigurationElement facet) {
		"IHas" + facet.elementName;
	}

	def private static String facetMutableClassName(IConfigurationElement facet) {
		"IHasMutable" + facet.elementName;
	}

	def private static String facetFQClassName(IConfigurationElement facet) {
		facet.elementPackageName + "." + facet.facetClassName;
	}

	def private static String facetFQMutableClassName(IConfigurationElement facet) {
		facet.elementPackageName + "." + facet.facetMutableClassName;
	}

	def private static IConfigurationElement facetRefToFacet(IConfigurationElement facetRef, Mappings mappings) {
		val String name = facetRef.getAttribute("facet")
		Preconditions.checkNotNull(mappings.getFacet(name), "Facet reference %s refers to missing facet %s",
			facetRef.elementPath, name)
	}

	def private static List<IConfigurationElement> facetSort(List<IConfigurationElement> elements, Mappings mappings) {
		Collections.sort(elements,
			[x, y|x.facetRefToFacet(mappings).elementName.compareTo(y.facetRefToFacet(mappings).elementName)])
		elements
	}

	def private static boolean facetIsReadOnly(IConfigurationElement facet, IConfigurationElement[] implementsFacets) {
		if (facet != null) {
			for (x : implementsFacets) {
				if (facet.elementName.equals(x.getAttribute("facet")))
					return x.implementsFacetIsReadOnly
			}
		}
		return false
	}

	def private static boolean implementsFacetIsReadOnly(IConfigurationElement facet) {
		"true".equals(facet.getAttribute("readOnly"))
	}

	def private static String interfaceFQClassName(IConfigurationElement iface) {
		Preconditions.checkNotNull(iface.getAttribute("interface")?.replace('$', '.'),
			"Element %s is missing required attribute interface", iface.elementPath);
	}

	def private static String keyType(IConfigurationElement key) {
		Preconditions.checkNotNull(
			key.getAttribute("type") ?: key.getAttribute("enum") ?: key.getAttribute("generic"),
			"Key %s has no type", key.elementPath).replace('$', '.');
	}

	def private static String keySimpleType(IConfigurationElement key) {
		var String type = key.keyType
		if (!key.keyIsNullable) {
			switch (type) {
				case "java.lang.Boolean": type = "boolean"
				case "java.lang.Byte": type = "byte"
				case "java.lang.Short": type = "short"
				case "java.lang.Integer": type = "int"
				case "java.lang.Long": type = "long"
				case "java.lang.Float": type = "float"
				case "java.lang.Double": type = "double"
				case "java.lang.Character": type = "char"
			}
		}
		type
	}

	def private static boolean keyIsNullable(IConfigurationElement key) {
		"true".equals(key.getAttribute("nullable"))
	}

	def private static String keyVariableName(IConfigurationElement key) {
		key.elementName.toFirstKeywordLower
	}

	def private static String keyConstantName(IConfigurationElement key) {
		key.elementName.toConstantName + "_KEY"
	}

	def private static String keyFQConstantName(IConfigurationElement key) {
		if ((key.parent as IConfigurationElement).name.equals("Thing"))
			return (key.parent as IConfigurationElement).thingFQClassName + "." + key.keyConstantName
		if ((key.parent as IConfigurationElement).name.equals("Facet"))
			return (key.parent as IConfigurationElement).facetFQClassName + "." + key.keyConstantName
		throw new IllegalArgumentException(key.elementPath.toString())
	}

	def private static String keyCloneParameter(IConfigurationElement key) {
		val String clone = key.getAttribute("clone")
		if (clone != null && clone.length > 0) {
			return ", " + clone;
		}
		return ""
	}

	def private static String keyFunctionName(IConfigurationElement key) {
		key.elementName
	}

	def private static String keyDefaultValue(IConfigurationElement key) {
		key.getAttribute("default")
	}

	def private static boolean keyIsShapeModifying(IConfigurationElement key) {
		"true".equals(key.getAttribute("shapeModifying"))
	}

	def private static String thingFQClassName(IConfigurationElement thing) {
		thing.elementPackageName + "." + thing.thingClassName;
	}

	def private static String refKeyVariableName(IConfigurationElement key) {
		key.elementName.toFirstKeywordLower
	}

	def private static String refKeyConstantName(IConfigurationElement key) {
		key.elementName.toConstantName + "_KEY"
	}

	def private static String refKeyFunctionName(IConfigurationElement key) {
		key.elementName
	}

	def private static String refKeyType(IConfigurationElement key) {
		Preconditions.checkNotNull(key.getAttribute("type"), "Key %s has no type", key.elementPath);
	}

	def private static String refKeyFQConstantName(IConfigurationElement key) {
		if ((key.parent as IConfigurationElement).name.equals("Thing"))
			return (key.parent as IConfigurationElement).thingFQClassName + "." + key.keyConstantName
		if ((key.parent as IConfigurationElement).name.equals("Facet"))
			return (key.parent as IConfigurationElement).facetFQClassName + "." + key.keyConstantName
		throw new IllegalArgumentException(key.elementPath.toString())
	}

	def private static String thingAbstractClassName(IConfigurationElement thing) {
		thing.elementName + "ThingBase"
	}

	def private static String thingClassName(IConfigurationElement thing) {
		thing.elementName + "Thing"
	}

	def private static String thingPeerClassName(IConfigurationElement thing) {
		thing.elementName + "ThingPeer"
	}

	def private static String thingAbstractFileName(IConfigurationElement thing) {
		thing.thingAbstractClassName + ".java"
	}

	def private static String thingFileName(IConfigurationElement thing) {
		thing.thingClassName + ".java"
	}

	def private static String thingPeerFileName(IConfigurationElement thing) {
		thing.thingPeerClassName + ".java"
	}

	def private static boolean thingIsAbstract(IConfigurationElement thing) {
		"true".equals(thing.getAttribute("abstract"));
	}

	def private static String thingExtendsClassName(IConfigurationElement thing, Mappings mappings) {
		val List<IConfigurationElement> superThings = thing.getChildren("ExtendsThing")
		if (superThings != null && superThings.size > 0) {
			val IConfigurationElement superThingElement = superThings.get(0)
			val String name = superThingElement.getAttribute("thing")
			val IConfigurationElement superThing = Preconditions.checkNotNull(mappings.getThing(name),
				"Thing %s super type refers to missing thing %s", superThingElement.elementPath, name)
			return superThing.thingFQClassName
		}
		"org.archstudio.bna.things.AbstractThing"
	}

	def private static List<IConfigurationElement> thingAllFacets(IConfigurationElement thing, Mappings mappings) {
		val Set<IConfigurationElement> allFacets = Sets.newHashSet();
		val Set<String> allFacetNames = Sets.newHashSet();
		val List<IConfigurationElement> toProcess = Lists.newArrayList(thing.getChildren("ImplementsFacet"))
		while (!toProcess.empty) {
			val IConfigurationElement facet = toProcess.remove(toProcess.size - 1).facetRefToFacet(mappings);
			if (allFacetNames.add(facet.elementName)) {
				allFacets.add(facet);
				toProcess.addAll(facet.getChildren("ExtendsFacet"));
			}
		}
		Lists.newArrayList(allFacets).elementSortByName
	}

	def private static Map<IConfigurationElement, IConfigurationElement> thingAllKeysToFacet(
		IConfigurationElement thing, Mappings mappings) {
		val Map<IConfigurationElement, IConfigurationElement> keys = Maps.newHashMap();
		for (facet : thing.thingAllFacets(mappings)) {
			for (key : facet.getChildren("Key")) {
				keys.put(key, facet);
			}
		}
		for (key : thing.getChildren("Key")) {
			keys.put(key, null);
		}
		keys
	}

	def private static List<IConfigurationElement> thingAllRefKeys(IConfigurationElement thing, Mappings mappings) {
		val List<IConfigurationElement> keys = Lists.newArrayList();
		for (facet : thing.thingAllFacets(mappings)) {
			for (key : facet.getChildren("RefKey")) {
				keys.add(key);
			}
		}
		for (key : thing.getChildren("RefKey")) {
			keys.add(key);
		}
		keys
	}

	def public static void generatePackage(IProject project, Mappings mappings, IConfigurationElement packageElement) {
		val IFolder folder = project.getFolder('''src/«packageElement.packageFileName»''')
		if (!folder.exists) {
			folder.create(true, true, null)
		}
		generateFacets(project, mappings, packageElement, folder)
	}

	def public static void generateFacets(IProject project, Mappings mappings, IConfigurationElement packageElement,
		IFolder folder) {
		for (facet : packageElement.getChildren("Facet")) {
			val IFile file = folder.getFile(facet.facetFileName)
			if (!file.exists) {
				file.create(null, true, null)
			}
			file.setContents(
				new ByteArrayInputStream(
					new String(
						'''
package «packageElement.packageName»;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * «facet.elementPath».
 */

«facet.elementJavaDoc»
@SuppressWarnings("all")
@NonNullByDefault
public interface «facet.facetClassName» extends org.archstudio.bna.IThing«FOR i : facet.getChildren("ExtendsFacet").
							facetSort(mappings) BEFORE ',
		' SEPARATOR ',
		'»«i.facetRefToFacet(mappings).facetFQClassName»«ENDFOR» {

	«FOR method : facet.getChildren("Method")»

«method.elementJavaDoc»
		«method.getAttribute("signature")»;

	«ENDFOR»
	«FOR key : facet.getChildren("Key").elementSortByName»
		public static final IThingKey<«key.keyType»> «key.keyConstantName» = ThingKey.create(com.google.common.collect.Lists.newArrayList("«key.elementName.
							toFirstKeywordLower»", «facet.facetClassName».class)«key.keyCloneParameter»«IF key.keyIsNullable», true«ENDIF»);

	«ENDFOR»
	«FOR key : facet.getChildren("RefKey").elementSortByName»
		public static final IThingRefKey<«key.refKeyType»> «key.refKeyConstantName» = ThingRefKey.create(com.google.common.collect.Lists.newArrayList("«key.elementName.
							toFirstKeywordLower»", «facet.facetClassName».class));

	«ENDFOR»
	«FOR key : facet.getChildren("Key").elementSortByName»
		public «IF key.keyIsNullable»@Nullable «ENDIF»«key.keySimpleType» «IF "boolean".equals(key.keySimpleType)»is«ELSE»get«ENDIF»«key.
							keyFunctionName»();

	«ENDFOR»
	«FOR key : facet.getChildren("RefKey").elementSortByName»
		public @Nullable «key.refKeyType» get«key.refKeyFunctionName»(org.archstudio.bna.IBNAModel model);

	«ENDFOR»
}
'''
					).bytes), true, true, null);
			val IFile mutableFile = folder.getFile(facet.facetMutableFileName)
			if (!mutableFile.exists) {
				mutableFile.create(null, true, null)
			}
			mutableFile.setContents(
				new ByteArrayInputStream(
					new String(
						'''
package «packageElement.packageName»;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * «facet.elementPath».
 */

«facet.elementJavaDoc»
@SuppressWarnings("all")
@NonNullByDefault
public interface «facet.facetMutableClassName» extends «facet.facetClassName»«FOR i : facet.getChildren("ExtendsFacet").
							facetSort(mappings) BEFORE ',
		' SEPARATOR ',
		'»«i.facetRefToFacet(mappings).facetFQMutableClassName»«ENDFOR» {

	«FOR quality : facet.getChildren("EditableQuality").elementSortByName»
		public static final String «quality.elementName.toConstantName» = "«quality.elementName»";

	«ENDFOR»
	«FOR key : facet.getChildren("Key").elementSortByName»
		public void set«key.keyFunctionName»(«IF key.keyIsNullable»@Nullable «ENDIF»«key.keySimpleType» «key.keyVariableName»);

	«ENDFOR»
	«FOR key : facet.getChildren("RefKey").elementSortByName»
		public void set«key.refKeyFunctionName»(@Nullable «key.refKeyType» «key.refKeyVariableName»);

	«ENDFOR»
}
'''
					).bytes), true, true, null);
		}
		for (thing : packageElement.getChildren("Thing")) {
			var IFile file = folder.getFile(thing.thingAbstractFileName)
			if (!file.exists) {
				file.create(null, true, null)
			}
			file.setContents(
				new ByteArrayInputStream(
					new String(
						'''
package «packageElement.packageName»;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * «thing.elementPath».
 */
 
«thing.elementJavaDoc»
@SuppressWarnings("all")
@NonNullByDefault
public abstract class «thing.thingAbstractClassName» extends «thing.thingExtendsClassName(mappings)»
	implements org.archstudio.bna.IThing«FOR i : thing.getChildren("ImplementsFacet").facetSort(mappings) BEFORE ',
		' SEPARATOR ',
		'»«IF i.implementsFacetIsReadOnly»«i.facetRefToFacet(mappings).facetFQClassName»«ELSE»«i.facetRefToFacet(mappings).
							facetFQMutableClassName»«ENDIF»«ENDFOR»«FOR i : thing.getChildren("ImplementsInterface") BEFORE ',
		' SEPARATOR ',
		'»«i.interfaceFQClassName»«ENDFOR» {

	«FOR key : thing.getChildren("Key").elementSortByName»
		public static final IThingKey<«key.keyType»> «key.keyConstantName» = ThingKey.create(com.google.common.collect.Lists.newArrayList("«key.elementName.
							toFirstKeywordLower»", «thing.thingAbstractClassName».class)«key.keyCloneParameter»);

	«ENDFOR»
	«FOR key : thing.getChildren("RefKey").elementSortByName»
		public static final IThingRefKey<«key.keyType»> «key.keyConstantName» = ThingRefKey.create(com.google.common.collect.Lists.newArrayList("«key.elementName.
							toFirstKeywordLower»", «thing.thingAbstractClassName».class));

	«ENDFOR»
	public «thing.thingAbstractClassName»(@Nullable Object id) {
		super(id);
	}

	«IF !thing.thingIsAbstract»
	@Override
	public IThingPeer<? extends «thing.thingClassName»> createPeer(IBNAView view, ICoordinateMapper cm) {
		return new «thing.thingPeerClassName»<>((«thing.thingClassName»)this, view, cm);
	}

	«ENDIF»
	@Override
	protected void initProperties() {
		«FOR keyToFacet : thing.thingAllKeysToFacet(mappings).entrySet.sortByKeyElementName»
				initProperty(«keyToFacet.key.keyFQConstantName», «keyToFacet.key.keyDefaultValue»);«IF !keyToFacet.value.
							facetIsReadOnly(thing.getChildren("ImplementsFacet")) && keyToFacet.key.keyIsShapeModifying»
				addShapeModifyingKey(«keyToFacet.key.keyFQConstantName»);«ENDIF»
		«ENDFOR»
		«FOR key : thing.thingAllRefKeys(mappings).elementSortByName»
				initProperty(«key.refKeyFQConstantName», null);
		«ENDFOR»
		super.initProperties();
	}

	«FOR keyToFacet : thing.thingAllKeysToFacet(mappings).entrySet.sortByKeyElementName»
		public «IF keyToFacet.key.keyIsNullable»@Nullable «ENDIF»«keyToFacet.key.keySimpleType» «IF "boolean".equals(
						keyToFacet.key.keySimpleType)»is«ELSE»get«ENDIF»«keyToFacet.key.keyFunctionName»() {
			return get(«keyToFacet.key.keyFQConstantName»);
		}

		/*package*/ «IF keyToFacet.key.keyIsNullable»@Nullable «ENDIF»«keyToFacet.key.keySimpleType» «IF "boolean".equals(
						keyToFacet.key.keySimpleType)»is«ELSE»get«ENDIF»Raw«keyToFacet.key.keyFunctionName»() {
			return getRaw(«keyToFacet.key.keyFQConstantName»);
		}

		«IF keyToFacet.value.facetIsReadOnly(thing.getChildren("ImplementsFacet"))»/*package*/ «ELSE»public «ENDIF»void set«keyToFacet.
						key.keyFunctionName»(«IF keyToFacet.key.keyIsNullable»@Nullable «ENDIF»«keyToFacet.key.
						keySimpleType» «keyToFacet.key.keyVariableName») {
			set(«keyToFacet.key.keyFQConstantName», «keyToFacet.key.keyVariableName»);
		}

		/*package*/ «IF keyToFacet.key.keyIsNullable»@Nullable «ENDIF»«keyToFacet.key.keySimpleType» «IF "boolean".equals(
						keyToFacet.key.keySimpleType)»is«ELSE»set«ENDIF»Raw«keyToFacet.key.keyFunctionName»(«IF keyToFacet.
						key.keyIsNullable»@Nullable «ENDIF»«keyToFacet.key.keySimpleType» «keyToFacet.key.
						keyVariableName») {
			return setRaw(«keyToFacet.key.keyFQConstantName», «keyToFacet.key.keyVariableName»);
		}

	«ENDFOR»
	«FOR key : thing.thingAllRefKeys(mappings)»
			public «key.refKeyType» get«key.refKeyFunctionName»(org.archstudio.bna.IBNAModel model) {
				org.archstudio.bna.IThing thing = model.getThing(getRaw(«key.keyFQConstantName»));
				if (thing instanceof «key.refKeyType») {
					return («key.refKeyType») thing;
				}
				return null;
			}

			public void set«key.refKeyFunctionName»(«key.refKeyType» «key.refKeyVariableName») {
				setRaw(«key.keyFQConstantName», «key.refKeyVariableName».getID());
			}
	«ENDFOR»
}
'''
					).bytes), true, true, null);

			file = folder.getFile(thing.thingFileName)
			if (!file.exists) {
				file.create(null, true, null)
				file.setContents(
					new ByteArrayInputStream(
						new String(
							'''
package «packageElement.packageName»;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public «IF thing.thingIsAbstract»abstract «ENDIF» class «thing.thingClassName» extends «thing.thingAbstractClassName»
{

	public «thing.thingClassName»(@Nullable Object id) {
		super(id);
	}

}
''').bytes), true, true, null);
			}

			file = folder.getFile(thing.thingPeerFileName)
			if (!file.exists) {
				file.create(null, true, null)
				file.setContents(
					new ByteArrayInputStream(
						new String(
							'''
package «packageElement.packageName»;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractThingPeer;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public «IF thing.thingIsAbstract»abstract «ENDIF»class «thing.thingPeerClassName»<T extends «thing.thingClassName»> extends AbstractThingPeer<T>
{

	public «thing.thingPeerClassName»(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

}
''').bytes), true, true, null);
			}
		}
	}

}
