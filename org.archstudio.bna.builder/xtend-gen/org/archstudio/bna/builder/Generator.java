package org.archstudio.bna.builder;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.archstudio.bna.builder.ElementPath;
import org.archstudio.bna.builder.Mappings;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class Generator {
  private static String toFirstKeywordLower(final String name) {
    String _xblockexpression = null;
    {
      StringBuffer result = new StringBuffer();
      final int length = name.length();
      int index = 0;
      while (((index < length) && Character.isUpperCase(name.charAt(index)))) {
        index = (index + 1);
      }
      if ((index > 1)) {
        String _substring = name.substring(0, (index - 1));
        String _lowerCase = _substring.toLowerCase();
        result.append(_lowerCase);
        String _substring_1 = name.substring((index - 1));
        result.append(_substring_1);
      } else {
        String _firstLower = StringExtensions.toFirstLower(name);
        result.append(_firstLower);
      }
      _xblockexpression = result.toString();
    }
    return _xblockexpression;
  }
  
  private static String toConstantName(final String name) {
    String _xblockexpression = null;
    {
      StringBuffer result = new StringBuffer();
      final int length = name.length();
      int start = 0;
      int index = 0;
      while ((start < length)) {
        {
          while (((index < length) && (!Character.isLowerCase(name.charAt(index))))) {
            index = (index + 1);
          }
          if (((index - start) > 1)) {
            String _substring = name.substring(start, (index - 1));
            String _upperCase = _substring.toUpperCase();
            StringBuffer _append = result.append(_upperCase);
            _append.append("_");
            start = (index - 1);
          }
          while (((index < length) && Character.isLowerCase(name.charAt(index)))) {
            index = (index + 1);
          }
          String _substring_1 = name.substring(start, index);
          String _upperCase_1 = _substring_1.toUpperCase();
          StringBuffer _append_1 = result.append(_upperCase_1);
          _append_1.append("_");
          start = index;
        }
      }
      String _string = result.toString();
      String _replaceAll = _string.replaceAll("__", "_");
      _xblockexpression = _replaceAll.replaceAll("^_|_$", "");
    }
    return _xblockexpression;
  }
  
  private static Object elementPath(final IConfigurationElement element) {
    return new ElementPath(element);
  }
  
  private static String elementName(final IConfigurationElement element) {
    String _attribute = element.getAttribute("name");
    Object _elementPath = Generator.elementPath(element);
    return Preconditions.<String>checkNotNull(_attribute, "Element %s is missing required attribute name", _elementPath);
  }
  
  private static List<IConfigurationElement> elementSortByName(final List<IConfigurationElement> elements) {
    List<IConfigurationElement> _xblockexpression = null;
    {
      final Comparator<IConfigurationElement> _function = new Comparator<IConfigurationElement>() {
        public int compare(final IConfigurationElement x, final IConfigurationElement y) {
          String _elementName = Generator.elementName(x);
          String _elementName_1 = Generator.elementName(y);
          return _elementName.compareTo(_elementName_1);
        }
      };
      Collections.<IConfigurationElement>sort(elements, _function);
      _xblockexpression = elements;
    }
    return _xblockexpression;
  }
  
  private static List<Map.Entry<IConfigurationElement, IConfigurationElement>> sortByKeyElementName(final Iterable<Map.Entry<IConfigurationElement, IConfigurationElement>> entries) {
    List<Map.Entry<IConfigurationElement, IConfigurationElement>> _xblockexpression = null;
    {
      final List<Map.Entry<IConfigurationElement, IConfigurationElement>> sorted = Lists.<Map.Entry<IConfigurationElement, IConfigurationElement>>newArrayList(entries);
      final Comparator<Map.Entry<IConfigurationElement, IConfigurationElement>> _function = new Comparator<Map.Entry<IConfigurationElement, IConfigurationElement>>() {
        public int compare(final Map.Entry<IConfigurationElement, IConfigurationElement> x, final Map.Entry<IConfigurationElement, IConfigurationElement> y) {
          IConfigurationElement _key = x.getKey();
          String _elementName = Generator.elementName(_key);
          IConfigurationElement _key_1 = y.getKey();
          String _elementName_1 = Generator.elementName(_key_1);
          return _elementName.compareTo(_elementName_1);
        }
      };
      Collections.<Map.Entry<IConfigurationElement, IConfigurationElement>>sort(sorted, _function);
      _xblockexpression = sorted;
    }
    return _xblockexpression;
  }
  
  private static String elementJavaDoc(final IConfigurationElement element) {
    String _xblockexpression = null;
    {
      final List<IConfigurationElement> javaDocs = (List<IConfigurationElement>)Conversions.doWrapArray(element.getChildren("JavaDoc"));
      int _size = javaDocs.size();
      boolean _greaterThan = (_size > 0);
      if (_greaterThan) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("�javaDocs.get(0).value.trim�");
        _builder.newLine();
        return _builder.toString();
      }
      _xblockexpression = "";
    }
    return _xblockexpression;
  }
  
  private static String elementPackageName(final IConfigurationElement element) {
    IConfigurationElement c = element;
    while (((!Objects.equal(c, null)) && (!"Package".equals(c.getName())))) {
      {
        final Object parent = c.getParent();
        c = null;
        if ((parent instanceof IConfigurationElement)) {
          c = ((IConfigurationElement) parent);
        }
      }
    }
    boolean _notEquals = (!Objects.equal(c, null));
    if (_notEquals) {
      return Generator.elementName(c);
    }
    Object _elementPath = Generator.elementPath(element);
    String _string = _elementPath.toString();
    throw new IllegalArgumentException(_string);
  }
  
  private static String packageFileName(final IConfigurationElement packageElement) {
    String _elementName = Generator.elementName(packageElement);
    return _elementName.replaceAll("\\.", "/");
  }
  
  private static String packageName(final IConfigurationElement packageElement) {
    return Generator.elementName(packageElement);
  }
  
  private static String facetFileName(final IConfigurationElement facet) {
    String _facetClassName = Generator.facetClassName(facet);
    return (_facetClassName + ".java");
  }
  
  private static String facetMutableFileName(final IConfigurationElement facet) {
    String _facetMutableClassName = Generator.facetMutableClassName(facet);
    return (_facetMutableClassName + ".java");
  }
  
  private static String facetClassName(final IConfigurationElement facet) {
    String _elementName = Generator.elementName(facet);
    return ("IHas" + _elementName);
  }
  
  private static String facetMutableClassName(final IConfigurationElement facet) {
    String _elementName = Generator.elementName(facet);
    return ("IHasMutable" + _elementName);
  }
  
  private static String facetFQClassName(final IConfigurationElement facet) {
    String _elementPackageName = Generator.elementPackageName(facet);
    String _plus = (_elementPackageName + ".");
    String _facetClassName = Generator.facetClassName(facet);
    return (_plus + _facetClassName);
  }
  
  private static String facetFQMutableClassName(final IConfigurationElement facet) {
    String _elementPackageName = Generator.elementPackageName(facet);
    String _plus = (_elementPackageName + ".");
    String _facetMutableClassName = Generator.facetMutableClassName(facet);
    return (_plus + _facetMutableClassName);
  }
  
  private static IConfigurationElement facetRefToFacet(final IConfigurationElement facetRef, final Mappings mappings) {
    IConfigurationElement _xblockexpression = null;
    {
      final String name = facetRef.getAttribute("facet");
      IConfigurationElement _facet = mappings.getFacet(name);
      Object _elementPath = Generator.elementPath(facetRef);
      _xblockexpression = Preconditions.<IConfigurationElement>checkNotNull(_facet, "Facet reference %s refers to missing facet %s", _elementPath, name);
    }
    return _xblockexpression;
  }
  
  private static List<IConfigurationElement> facetSort(final List<IConfigurationElement> elements, final Mappings mappings) {
    List<IConfigurationElement> _xblockexpression = null;
    {
      final Comparator<IConfigurationElement> _function = new Comparator<IConfigurationElement>() {
        public int compare(final IConfigurationElement x, final IConfigurationElement y) {
          IConfigurationElement _facetRefToFacet = Generator.facetRefToFacet(x, mappings);
          String _elementName = Generator.elementName(_facetRefToFacet);
          IConfigurationElement _facetRefToFacet_1 = Generator.facetRefToFacet(y, mappings);
          String _elementName_1 = Generator.elementName(_facetRefToFacet_1);
          return _elementName.compareTo(_elementName_1);
        }
      };
      Collections.<IConfigurationElement>sort(elements, _function);
      _xblockexpression = elements;
    }
    return _xblockexpression;
  }
  
  private static boolean facetIsReadOnly(final IConfigurationElement facet, final IConfigurationElement[] implementsFacets) {
    boolean _notEquals = (!Objects.equal(facet, null));
    if (_notEquals) {
      for (final IConfigurationElement x : implementsFacets) {
        String _elementName = Generator.elementName(facet);
        String _attribute = x.getAttribute("facet");
        boolean _equals = _elementName.equals(_attribute);
        if (_equals) {
          return Generator.implementsFacetIsReadOnly(x);
        }
      }
    }
    return false;
  }
  
  private static boolean implementsFacetIsReadOnly(final IConfigurationElement facet) {
    String _attribute = facet.getAttribute("readOnly");
    return "true".equals(_attribute);
  }
  
  private static String interfaceFQClassName(final IConfigurationElement iface) {
    String _attribute = iface.getAttribute("interface");
    String _replace = null;
    if (_attribute!=null) {
      _replace=_attribute.replace("$", ".");
    }
    Object _elementPath = Generator.elementPath(iface);
    return Preconditions.<String>checkNotNull(_replace, 
      "Element %s is missing required attribute interface", _elementPath);
  }
  
  private static String keyType(final IConfigurationElement key) {
    String _elvis = null;
    String _elvis_1 = null;
    String _attribute = key.getAttribute("type");
    if (_attribute != null) {
      _elvis_1 = _attribute;
    } else {
      String _attribute_1 = key.getAttribute("enum");
      _elvis_1 = _attribute_1;
    }
    if (_elvis_1 != null) {
      _elvis = _elvis_1;
    } else {
      String _attribute_2 = key.getAttribute("generic");
      _elvis = _attribute_2;
    }
    Object _elementPath = Generator.elementPath(key);
    String _checkNotNull = Preconditions.<String>checkNotNull(_elvis, 
      "Key %s has no type", _elementPath);
    return _checkNotNull.replace("$", ".");
  }
  
  private static String keySimpleType(final IConfigurationElement key) {
    String _xblockexpression = null;
    {
      String type = Generator.keyType(key);
      boolean _keyIsNullable = Generator.keyIsNullable(key);
      boolean _not = (!_keyIsNullable);
      if (_not) {
        boolean _matched = false;
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Boolean")) {
            _matched=true;
            type = "boolean";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Byte")) {
            _matched=true;
            type = "byte";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Short")) {
            _matched=true;
            type = "short";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Integer")) {
            _matched=true;
            type = "int";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Long")) {
            _matched=true;
            type = "long";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Float")) {
            _matched=true;
            type = "float";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Double")) {
            _matched=true;
            type = "double";
          }
        }
        if (!_matched) {
          if (Objects.equal(type, "java.lang.Character")) {
            _matched=true;
            type = "char";
          }
        }
      }
      _xblockexpression = type;
    }
    return _xblockexpression;
  }
  
  private static boolean keyIsNullable(final IConfigurationElement key) {
    String _attribute = key.getAttribute("nullable");
    return "true".equals(_attribute);
  }
  
  private static String keyVariableName(final IConfigurationElement key) {
    String _elementName = Generator.elementName(key);
    return Generator.toFirstKeywordLower(_elementName);
  }
  
  private static String keyConstantName(final IConfigurationElement key) {
    String _elementName = Generator.elementName(key);
    String _constantName = Generator.toConstantName(_elementName);
    return (_constantName + "_KEY");
  }
  
  private static String keyFQConstantName(final IConfigurationElement key) {
    Object _parent = key.getParent();
    String _name = ((IConfigurationElement) _parent).getName();
    boolean _equals = _name.equals("Thing");
    if (_equals) {
      Object _parent_1 = key.getParent();
      String _thingFQClassName = Generator.thingFQClassName(((IConfigurationElement) _parent_1));
      String _plus = (_thingFQClassName + ".");
      String _keyConstantName = Generator.keyConstantName(key);
      return (_plus + _keyConstantName);
    }
    Object _parent_2 = key.getParent();
    String _name_1 = ((IConfigurationElement) _parent_2).getName();
    boolean _equals_1 = _name_1.equals("Facet");
    if (_equals_1) {
      Object _parent_3 = key.getParent();
      String _facetFQClassName = Generator.facetFQClassName(((IConfigurationElement) _parent_3));
      String _plus_1 = (_facetFQClassName + ".");
      String _keyConstantName_1 = Generator.keyConstantName(key);
      return (_plus_1 + _keyConstantName_1);
    }
    Object _elementPath = Generator.elementPath(key);
    String _string = _elementPath.toString();
    throw new IllegalArgumentException(_string);
  }
  
  private static String keyCloneParameter(final IConfigurationElement key) {
    final String clone = key.getAttribute("clone");
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(clone, null));
    if (!_notEquals) {
      _and = false;
    } else {
      int _length = clone.length();
      boolean _greaterThan = (_length > 0);
      _and = _greaterThan;
    }
    if (_and) {
      return (", " + clone);
    }
    return "";
  }
  
  private static String keyFunctionName(final IConfigurationElement key) {
    return Generator.elementName(key);
  }
  
  private static String keyDefaultValue(final IConfigurationElement key) {
    return key.getAttribute("default");
  }
  
  private static boolean keyIsShapeModifying(final IConfigurationElement key) {
    String _attribute = key.getAttribute("shapeModifying");
    return "true".equals(_attribute);
  }
  
  private static String thingFQClassName(final IConfigurationElement thing) {
    String _elementPackageName = Generator.elementPackageName(thing);
    String _plus = (_elementPackageName + ".");
    String _thingClassName = Generator.thingClassName(thing);
    return (_plus + _thingClassName);
  }
  
  private static String refKeyVariableName(final IConfigurationElement key) {
    String _elementName = Generator.elementName(key);
    return Generator.toFirstKeywordLower(_elementName);
  }
  
  private static String refKeyConstantName(final IConfigurationElement key) {
    String _elementName = Generator.elementName(key);
    String _constantName = Generator.toConstantName(_elementName);
    return (_constantName + "_KEY");
  }
  
  private static String refKeyFunctionName(final IConfigurationElement key) {
    return Generator.elementName(key);
  }
  
  private static String refKeyType(final IConfigurationElement key) {
    String _attribute = key.getAttribute("type");
    Object _elementPath = Generator.elementPath(key);
    return Preconditions.<String>checkNotNull(_attribute, "Key %s has no type", _elementPath);
  }
  
  private static String refKeyFQConstantName(final IConfigurationElement key) {
    Object _parent = key.getParent();
    String _name = ((IConfigurationElement) _parent).getName();
    boolean _equals = _name.equals("Thing");
    if (_equals) {
      Object _parent_1 = key.getParent();
      String _thingFQClassName = Generator.thingFQClassName(((IConfigurationElement) _parent_1));
      String _plus = (_thingFQClassName + ".");
      String _keyConstantName = Generator.keyConstantName(key);
      return (_plus + _keyConstantName);
    }
    Object _parent_2 = key.getParent();
    String _name_1 = ((IConfigurationElement) _parent_2).getName();
    boolean _equals_1 = _name_1.equals("Facet");
    if (_equals_1) {
      Object _parent_3 = key.getParent();
      String _facetFQClassName = Generator.facetFQClassName(((IConfigurationElement) _parent_3));
      String _plus_1 = (_facetFQClassName + ".");
      String _keyConstantName_1 = Generator.keyConstantName(key);
      return (_plus_1 + _keyConstantName_1);
    }
    Object _elementPath = Generator.elementPath(key);
    String _string = _elementPath.toString();
    throw new IllegalArgumentException(_string);
  }
  
  private static String thingAbstractClassName(final IConfigurationElement thing) {
    String _elementName = Generator.elementName(thing);
    return (_elementName + "ThingBase");
  }
  
  private static String thingClassName(final IConfigurationElement thing) {
    String _elementName = Generator.elementName(thing);
    return (_elementName + "Thing");
  }
  
  private static String thingPeerClassName(final IConfigurationElement thing) {
    String _elementName = Generator.elementName(thing);
    return (_elementName + "ThingPeer");
  }
  
  private static String thingAbstractFileName(final IConfigurationElement thing) {
    String _thingAbstractClassName = Generator.thingAbstractClassName(thing);
    return (_thingAbstractClassName + ".java");
  }
  
  private static String thingFileName(final IConfigurationElement thing) {
    String _thingClassName = Generator.thingClassName(thing);
    return (_thingClassName + ".java");
  }
  
  private static String thingPeerFileName(final IConfigurationElement thing) {
    String _thingPeerClassName = Generator.thingPeerClassName(thing);
    return (_thingPeerClassName + ".java");
  }
  
  private static boolean thingIsAbstract(final IConfigurationElement thing) {
    String _attribute = thing.getAttribute("abstract");
    return "true".equals(_attribute);
  }
  
  private static String thingExtendsClassName(final IConfigurationElement thing, final Mappings mappings) {
    String _xblockexpression = null;
    {
      final List<IConfigurationElement> superThings = (List<IConfigurationElement>)Conversions.doWrapArray(thing.getChildren("ExtendsThing"));
      boolean _and = false;
      boolean _notEquals = (!Objects.equal(superThings, null));
      if (!_notEquals) {
        _and = false;
      } else {
        int _size = superThings.size();
        boolean _greaterThan = (_size > 0);
        _and = _greaterThan;
      }
      if (_and) {
        final IConfigurationElement superThingElement = superThings.get(0);
        final String name = superThingElement.getAttribute("thing");
        IConfigurationElement _thing = mappings.getThing(name);
        Object _elementPath = Generator.elementPath(superThingElement);
        final IConfigurationElement superThing = Preconditions.<IConfigurationElement>checkNotNull(_thing, 
          "Thing %s super type refers to missing thing %s", _elementPath, name);
        return Generator.thingFQClassName(superThing);
      }
      _xblockexpression = "org.archstudio.bna.things.AbstractThing";
    }
    return _xblockexpression;
  }
  
  private static List<IConfigurationElement> thingAllFacets(final IConfigurationElement thing, final Mappings mappings) {
    List<IConfigurationElement> _xblockexpression = null;
    {
      final Set<IConfigurationElement> allFacets = Sets.<IConfigurationElement>newHashSet();
      final Set<String> allFacetNames = Sets.<String>newHashSet();
      IConfigurationElement[] _children = thing.getChildren("ImplementsFacet");
      final List<IConfigurationElement> toProcess = Lists.<IConfigurationElement>newArrayList(_children);
      while ((!toProcess.isEmpty())) {
        {
          int _size = toProcess.size();
          int _minus = (_size - 1);
          IConfigurationElement _remove = toProcess.remove(_minus);
          final IConfigurationElement facet = Generator.facetRefToFacet(_remove, mappings);
          String _elementName = Generator.elementName(facet);
          boolean _add = allFacetNames.add(_elementName);
          if (_add) {
            allFacets.add(facet);
            IConfigurationElement[] _children_1 = facet.getChildren("ExtendsFacet");
            CollectionExtensions.<IConfigurationElement>addAll(toProcess, _children_1);
          }
        }
      }
      ArrayList<IConfigurationElement> _newArrayList = Lists.<IConfigurationElement>newArrayList(allFacets);
      _xblockexpression = Generator.elementSortByName(_newArrayList);
    }
    return _xblockexpression;
  }
  
  private static Map<IConfigurationElement, IConfigurationElement> thingAllKeysToFacet(final IConfigurationElement thing, final Mappings mappings) {
    Map<IConfigurationElement, IConfigurationElement> _xblockexpression = null;
    {
      final Map<IConfigurationElement, IConfigurationElement> keys = Maps.<IConfigurationElement, IConfigurationElement>newHashMap();
      List<IConfigurationElement> _thingAllFacets = Generator.thingAllFacets(thing, mappings);
      for (final IConfigurationElement facet : _thingAllFacets) {
        IConfigurationElement[] _children = facet.getChildren("Key");
        for (final IConfigurationElement key : _children) {
          keys.put(key, facet);
        }
      }
      IConfigurationElement[] _children_1 = thing.getChildren("Key");
      for (final IConfigurationElement key_1 : _children_1) {
        keys.put(key_1, null);
      }
      _xblockexpression = keys;
    }
    return _xblockexpression;
  }
  
  private static List<IConfigurationElement> thingAllRefKeys(final IConfigurationElement thing, final Mappings mappings) {
    List<IConfigurationElement> _xblockexpression = null;
    {
      final List<IConfigurationElement> keys = Lists.<IConfigurationElement>newArrayList();
      List<IConfigurationElement> _thingAllFacets = Generator.thingAllFacets(thing, mappings);
      for (final IConfigurationElement facet : _thingAllFacets) {
        IConfigurationElement[] _children = facet.getChildren("RefKey");
        for (final IConfigurationElement key : _children) {
          keys.add(key);
        }
      }
      IConfigurationElement[] _children_1 = thing.getChildren("RefKey");
      for (final IConfigurationElement key_1 : _children_1) {
        keys.add(key_1);
      }
      _xblockexpression = keys;
    }
    return _xblockexpression;
  }
  
  public static void generatePackage(final IProject project, final Mappings mappings, final IConfigurationElement packageElement) {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("src/�packageElement.packageFileName�");
      final IFolder folder = project.getFolder(_builder.toString());
      boolean _exists = folder.exists();
      boolean _not = (!_exists);
      if (_not) {
        folder.create(true, true, null);
      }
      Generator.generateFacets(project, mappings, packageElement, folder);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static void generateFacets(final IProject project, final Mappings mappings, final IConfigurationElement packageElement, final IFolder folder) {
    try {
      IConfigurationElement[] _children = packageElement.getChildren("Facet");
      for (final IConfigurationElement facet : _children) {
        {
          String _facetFileName = Generator.facetFileName(facet);
          final IFile file = folder.getFile(_facetFileName);
          boolean _exists = file.exists();
          boolean _not = (!_exists);
          if (_not) {
            file.create(null, true, null);
          }
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("package �packageElement.packageName�;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.IThingKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.IThingRefKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.ThingKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.ThingRefKey;");
          _builder.newLine();
          _builder.append("import org.eclipse.jdt.annotation.NonNullByDefault;");
          _builder.newLine();
          _builder.append("import org.eclipse.jdt.annotation.Nullable;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("/*");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("*");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* To modify, update the thingdefinition extension at");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* �facet.elementPath�.");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.newLine();
          _builder.append("�facet.elementJavaDoc�");
          _builder.newLine();
          _builder.append("@SuppressWarnings(\"all\")");
          _builder.newLine();
          _builder.append("@NonNullByDefault");
          _builder.newLine();
          _builder.append("public interface �facet.facetClassName� extends org.archstudio.bna.IThing�FOR i : facet.getChildren(\"ExtendsFacet\").");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("facetSort(mappings) BEFORE \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\' SEPARATOR \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\'��i.facetRefToFacet(mappings).facetFQClassName��ENDFOR� {");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR method : facet.getChildren(\"Method\")�");
          _builder.newLine();
          _builder.newLine();
          _builder.append("�method.elementJavaDoc�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�method.getAttribute(\"signature\")�;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : facet.getChildren(\"Key\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public static final IThingKey<�key.keyType�> �key.keyConstantName� = ThingKey.create(com.google.common.collect.Lists.newArrayList(\"�key.elementName.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("toFirstKeywordLower�\", �facet.facetClassName�.class)�key.keyCloneParameter��IF key.keyIsNullable�, true�ENDIF�);");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : facet.getChildren(\"RefKey\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public static final IThingRefKey<�key.refKeyType�> �key.refKeyConstantName� = ThingRefKey.create(com.google.common.collect.Lists.newArrayList(\"�key.elementName.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("toFirstKeywordLower�\", �facet.facetClassName�.class));");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : facet.getChildren(\"Key\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public �IF key.keyIsNullable�@Nullable �ENDIF��key.keySimpleType� �IF \"boolean\".equals(key.keySimpleType)�is�ELSE�get�ENDIF��key.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("keyFunctionName�();");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : facet.getChildren(\"RefKey\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public @Nullable �key.refKeyType� get�key.refKeyFunctionName�(org.archstudio.bna.IBNAModel model);");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          String _string = new String(_builder.toString());
          byte[] _bytes = _string.getBytes();
          ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_bytes);
          file.setContents(_byteArrayInputStream, true, true, null);
          String _facetMutableFileName = Generator.facetMutableFileName(facet);
          final IFile mutableFile = folder.getFile(_facetMutableFileName);
          boolean _exists_1 = mutableFile.exists();
          boolean _not_1 = (!_exists_1);
          if (_not_1) {
            mutableFile.create(null, true, null);
          }
          StringConcatenation _builder_1 = new StringConcatenation();
          _builder_1.append("package �packageElement.packageName�;");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("import org.archstudio.bna.keys.IThingKey;");
          _builder_1.newLine();
          _builder_1.append("import org.archstudio.bna.keys.ThingKey;");
          _builder_1.newLine();
          _builder_1.append("import org.eclipse.jdt.annotation.NonNullByDefault;");
          _builder_1.newLine();
          _builder_1.append("import org.eclipse.jdt.annotation.Nullable;");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("/*");
          _builder_1.newLine();
          _builder_1.append(" ");
          _builder_1.append("* DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.");
          _builder_1.newLine();
          _builder_1.append(" ");
          _builder_1.append("*");
          _builder_1.newLine();
          _builder_1.append(" ");
          _builder_1.append("* To modify, update the thingdefinition extension at");
          _builder_1.newLine();
          _builder_1.append(" ");
          _builder_1.append("* �facet.elementPath�.");
          _builder_1.newLine();
          _builder_1.append(" ");
          _builder_1.append("*/");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("�facet.elementJavaDoc�");
          _builder_1.newLine();
          _builder_1.append("@SuppressWarnings(\"all\")");
          _builder_1.newLine();
          _builder_1.append("@NonNullByDefault");
          _builder_1.newLine();
          _builder_1.append("public interface �facet.facetMutableClassName� extends �facet.facetClassName��FOR i : facet.getChildren(\"ExtendsFacet\").");
          _builder_1.newLine();
          _builder_1.append("\t\t\t\t\t\t\t");
          _builder_1.append("facetSort(mappings) BEFORE \',");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("\' SEPARATOR \',");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("\'��i.facetRefToFacet(mappings).facetFQMutableClassName��ENDFOR� {");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�FOR quality : facet.getChildren(\"EditableQuality\").elementSortByName�");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("public static final String �quality.elementName.toConstantName� = \"�quality.elementName�\";");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�ENDFOR�");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�FOR key : facet.getChildren(\"Key\").elementSortByName�");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("public void set�key.keyFunctionName�(�IF key.keyIsNullable�@Nullable �ENDIF��key.keySimpleType� �key.keyVariableName�);");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�ENDFOR�");
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�FOR key : facet.getChildren(\"RefKey\").elementSortByName�");
          _builder_1.newLine();
          _builder_1.append("\t\t");
          _builder_1.append("public void set�key.refKeyFunctionName�(@Nullable �key.refKeyType� �key.refKeyVariableName�);");
          _builder_1.newLine();
          _builder_1.newLine();
          _builder_1.append("\t");
          _builder_1.append("�ENDFOR�");
          _builder_1.newLine();
          _builder_1.append("}");
          _builder_1.newLine();
          String _string_1 = new String(_builder_1.toString());
          byte[] _bytes_1 = _string_1.getBytes();
          ByteArrayInputStream _byteArrayInputStream_1 = new ByteArrayInputStream(_bytes_1);
          mutableFile.setContents(_byteArrayInputStream_1, true, true, null);
        }
      }
      IConfigurationElement[] _children_1 = packageElement.getChildren("Thing");
      for (final IConfigurationElement thing : _children_1) {
        {
          String _thingAbstractFileName = Generator.thingAbstractFileName(thing);
          IFile file = folder.getFile(_thingAbstractFileName);
          boolean _exists = file.exists();
          boolean _not = (!_exists);
          if (_not) {
            file.create(null, true, null);
          }
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("package �packageElement.packageName�;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("import org.archstudio.bna.IBNAView;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.ICoordinateMapper;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.IThingPeer;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.IThingKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.IThingRefKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.ThingKey;");
          _builder.newLine();
          _builder.append("import org.archstudio.bna.keys.ThingRefKey;");
          _builder.newLine();
          _builder.append("import org.eclipse.jdt.annotation.NonNullByDefault;");
          _builder.newLine();
          _builder.append("import org.eclipse.jdt.annotation.Nullable;");
          _builder.newLine();
          _builder.newLine();
          _builder.append("/*");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("*");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* To modify, update the thingdefinition extension at");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("* �thing.elementPath�.");
          _builder.newLine();
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append(" ");
          _builder.newLine();
          _builder.append("�thing.elementJavaDoc�");
          _builder.newLine();
          _builder.append("@SuppressWarnings(\"all\")");
          _builder.newLine();
          _builder.append("@NonNullByDefault");
          _builder.newLine();
          _builder.append("public abstract class �thing.thingAbstractClassName� extends �thing.thingExtendsClassName(mappings)�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("implements org.archstudio.bna.IThing�FOR i : thing.getChildren(\"ImplementsFacet\").facetSort(mappings) BEFORE \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\' SEPARATOR \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\'��IF i.implementsFacetIsReadOnly��i.facetRefToFacet(mappings).facetFQClassName��ELSE��i.facetRefToFacet(mappings).");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("facetFQMutableClassName��ENDIF��ENDFOR��FOR i : thing.getChildren(\"ImplementsInterface\") BEFORE \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\' SEPARATOR \',");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("\'��i.interfaceFQClassName��ENDFOR� {");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : thing.getChildren(\"Key\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public static final IThingKey<�key.keyType�> �key.keyConstantName� = ThingKey.create(com.google.common.collect.Lists.newArrayList(\"�key.elementName.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("toFirstKeywordLower�\", �thing.thingAbstractClassName�.class)�key.keyCloneParameter�);");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : thing.getChildren(\"RefKey\").elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public static final IThingRefKey<�key.keyType�> �key.keyConstantName� = ThingRefKey.create(com.google.common.collect.Lists.newArrayList(\"�key.elementName.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("toFirstKeywordLower�\", �thing.thingAbstractClassName�.class));");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public �thing.thingAbstractClassName�(@Nullable Object id) {");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("super(id);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�IF !thing.thingIsAbstract�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public IThingPeer<? extends �thing.thingClassName�> createPeer(IBNAView view, ICoordinateMapper cm) {");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("return new �thing.thingPeerClassName�<>((�thing.thingClassName�)this, view, cm);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDIF�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("protected void initProperties() {");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�FOR keyToFacet : thing.thingAllKeysToFacet(mappings).entrySet.sortByKeyElementName�");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("initProperty(�keyToFacet.key.keyFQConstantName�, �keyToFacet.key.keyDefaultValue�);�IF !keyToFacet.value.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t\t");
          _builder.append("facetIsReadOnly(thing.getChildren(\"ImplementsFacet\")) && keyToFacet.key.keyIsShapeModifying�");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("addShapeModifyingKey(�keyToFacet.key.keyFQConstantName�);�ENDIF�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�FOR key : thing.thingAllRefKeys(mappings).elementSortByName�");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("initProperty(�key.refKeyFQConstantName�, null);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("super.initProperties();");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR keyToFacet : thing.thingAllKeysToFacet(mappings).entrySet.sortByKeyElementName�");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("public �IF keyToFacet.key.keyIsNullable�@Nullable �ENDIF��keyToFacet.key.keySimpleType� �IF \"boolean\".equals(");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("keyToFacet.key.keySimpleType)�is�ELSE�get�ENDIF��keyToFacet.key.keyFunctionName�() {");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("return get(�keyToFacet.key.keyFQConstantName�);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("/*package*/ �IF keyToFacet.key.keyIsNullable�@Nullable �ENDIF��keyToFacet.key.keySimpleType� �IF \"boolean\".equals(");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("keyToFacet.key.keySimpleType)�is�ELSE�get�ENDIF�Raw�keyToFacet.key.keyFunctionName�() {");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("return getRaw(�keyToFacet.key.keyFQConstantName�);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("�IF keyToFacet.value.facetIsReadOnly(thing.getChildren(\"ImplementsFacet\"))�/*package*/ �ELSE�public �ENDIF�void set�keyToFacet.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("key.keyFunctionName�(�IF keyToFacet.key.keyIsNullable�@Nullable �ENDIF��keyToFacet.key.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("keySimpleType� �keyToFacet.key.keyVariableName�) {");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("set(�keyToFacet.key.keyFQConstantName�, �keyToFacet.key.keyVariableName�);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("/*package*/ �IF keyToFacet.key.keyIsNullable�@Nullable �ENDIF��keyToFacet.key.keySimpleType� �IF \"boolean\".equals(");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("keyToFacet.key.keySimpleType)�is�ELSE�set�ENDIF�Raw�keyToFacet.key.keyFunctionName�(�IF keyToFacet.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("key.keyIsNullable�@Nullable �ENDIF��keyToFacet.key.keySimpleType� �keyToFacet.key.");
          _builder.newLine();
          _builder.append("\t\t\t\t\t\t");
          _builder.append("keyVariableName�) {");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("return setRaw(�keyToFacet.key.keyFQConstantName�, �keyToFacet.key.keyVariableName�);");
          _builder.newLine();
          _builder.append("\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�FOR key : thing.thingAllRefKeys(mappings)�");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("public �key.refKeyType� get�key.refKeyFunctionName�(org.archstudio.bna.IBNAModel model) {");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("org.archstudio.bna.IThing thing = model.getThing(getRaw(�key.keyFQConstantName�));");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("if (thing instanceof �key.refKeyType�) {");
          _builder.newLine();
          _builder.append("\t\t\t\t\t");
          _builder.append("return (�key.refKeyType�) thing;");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("return null;");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("public void set�key.refKeyFunctionName�(�key.refKeyType� �key.refKeyVariableName�) {");
          _builder.newLine();
          _builder.append("\t\t\t\t");
          _builder.append("setRaw(�key.keyFQConstantName�, �key.refKeyVariableName�.getID());");
          _builder.newLine();
          _builder.append("\t\t\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("�ENDFOR�");
          _builder.newLine();
          _builder.append("}");
          _builder.newLine();
          String _string = new String(_builder.toString());
          byte[] _bytes = _string.getBytes();
          ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(_bytes);
          file.setContents(_byteArrayInputStream, true, true, null);
          String _thingFileName = Generator.thingFileName(thing);
          IFile _file = folder.getFile(_thingFileName);
          file = _file;
          boolean _exists_1 = file.exists();
          boolean _not_1 = (!_exists_1);
          if (_not_1) {
            file.create(null, true, null);
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("package �packageElement.packageName�;");
            _builder_1.newLine();
            _builder_1.newLine();
            _builder_1.append("import org.archstudio.bna.keys.IThingKey;");
            _builder_1.newLine();
            _builder_1.append("import org.archstudio.bna.keys.ThingKey;");
            _builder_1.newLine();
            _builder_1.append("import org.eclipse.jdt.annotation.NonNullByDefault;");
            _builder_1.newLine();
            _builder_1.append("import org.eclipse.jdt.annotation.Nullable;");
            _builder_1.newLine();
            _builder_1.newLine();
            _builder_1.append("@NonNullByDefault");
            _builder_1.newLine();
            _builder_1.append("public �IF thing.thingIsAbstract�abstract �ENDIF� class �thing.thingClassName� extends �thing.thingAbstractClassName�");
            _builder_1.newLine();
            _builder_1.append("{");
            _builder_1.newLine();
            _builder_1.newLine();
            _builder_1.append("\t");
            _builder_1.append("public �thing.thingClassName�(@Nullable Object id) {");
            _builder_1.newLine();
            _builder_1.append("\t\t");
            _builder_1.append("super(id);");
            _builder_1.newLine();
            _builder_1.append("\t");
            _builder_1.append("}");
            _builder_1.newLine();
            _builder_1.newLine();
            _builder_1.append("}");
            _builder_1.newLine();
            String _string_1 = new String(_builder_1.toString());
            byte[] _bytes_1 = _string_1.getBytes();
            ByteArrayInputStream _byteArrayInputStream_1 = new ByteArrayInputStream(_bytes_1);
            file.setContents(_byteArrayInputStream_1, true, true, null);
          }
          String _thingPeerFileName = Generator.thingPeerFileName(thing);
          IFile _file_1 = folder.getFile(_thingPeerFileName);
          file = _file_1;
          boolean _exists_2 = file.exists();
          boolean _not_2 = (!_exists_2);
          if (_not_2) {
            file.create(null, true, null);
            StringConcatenation _builder_2 = new StringConcatenation();
            _builder_2.append("package �packageElement.packageName�;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("import org.archstudio.bna.IBNAView;");
            _builder_2.newLine();
            _builder_2.append("import org.archstudio.bna.ICoordinateMapper;");
            _builder_2.newLine();
            _builder_2.append("import org.archstudio.bna.keys.IThingKey;");
            _builder_2.newLine();
            _builder_2.append("import org.archstudio.bna.keys.ThingKey;");
            _builder_2.newLine();
            _builder_2.append("import org.archstudio.bna.things.AbstractThingPeer;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.jdt.annotation.NonNullByDefault;");
            _builder_2.newLine();
            _builder_2.append("import org.eclipse.jdt.annotation.Nullable;");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("@NonNullByDefault");
            _builder_2.newLine();
            _builder_2.append("public �IF thing.thingIsAbstract�abstract �ENDIF�class �thing.thingPeerClassName�<T extends �thing.thingClassName�> extends AbstractThingPeer<T>");
            _builder_2.newLine();
            _builder_2.append("{");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("\t");
            _builder_2.append("public �thing.thingPeerClassName�(T thing, IBNAView view, ICoordinateMapper cm) {");
            _builder_2.newLine();
            _builder_2.append("\t\t");
            _builder_2.append("super(thing, view, cm);");
            _builder_2.newLine();
            _builder_2.append("\t");
            _builder_2.append("}");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("}");
            _builder_2.newLine();
            String _string_2 = new String(_builder_2.toString());
            byte[] _bytes_2 = _string_2.getBytes();
            ByteArrayInputStream _byteArrayInputStream_2 = new ByteArrayInputStream(_bytes_2);
            file.setContents(_byteArrayInputStream_2, true, true, null);
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
