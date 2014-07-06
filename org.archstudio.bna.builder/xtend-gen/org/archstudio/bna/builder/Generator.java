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
import org.archstudio.bna.keys.ThingKey;
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
      boolean _and = false;
      if (!(index < length)) {
        _and = false;
      } else {
        char _charAt = name.charAt(index);
        boolean _isUpperCase = Character.isUpperCase(_charAt);
        _and = _isUpperCase;
      }
      boolean _while = _and;
      while (_while) {
        index = (index + 1);
        boolean _and_1 = false;
        if (!(index < length)) {
          _and_1 = false;
        } else {
          char _charAt_1 = name.charAt(index);
          boolean _isUpperCase_1 = Character.isUpperCase(_charAt_1);
          _and_1 = _isUpperCase_1;
        }
        _while = _and_1;
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
      boolean _while = (start < length);
      while (_while) {
        {
          boolean _and = false;
          if (!(index < length)) {
            _and = false;
          } else {
            char _charAt = name.charAt(index);
            boolean _isLowerCase = Character.isLowerCase(_charAt);
            boolean _not = (!_isLowerCase);
            _and = _not;
          }
          boolean _while_1 = _and;
          while (_while_1) {
            index = (index + 1);
            boolean _and_1 = false;
            if (!(index < length)) {
              _and_1 = false;
            } else {
              char _charAt_1 = name.charAt(index);
              boolean _isLowerCase_1 = Character.isLowerCase(_charAt_1);
              boolean _not_1 = (!_isLowerCase_1);
              _and_1 = _not_1;
            }
            _while_1 = _and_1;
          }
          if (((index - start) > 1)) {
            String _substring = name.substring(start, (index - 1));
            String _upperCase = _substring.toUpperCase();
            StringBuffer _append = result.append(_upperCase);
            _append.append("_");
            start = (index - 1);
          }
          boolean _and_1 = false;
          if (!(index < length)) {
            _and_1 = false;
          } else {
            char _charAt_1 = name.charAt(index);
            boolean _isLowerCase_1 = Character.isLowerCase(_charAt_1);
            _and_1 = _isLowerCase_1;
          }
          boolean _while_2 = _and_1;
          while (_while_2) {
            index = (index + 1);
            boolean _and_2 = false;
            if (!(index < length)) {
              _and_2 = false;
            } else {
              char _charAt_2 = name.charAt(index);
              boolean _isLowerCase_2 = Character.isLowerCase(_charAt_2);
              _and_2 = _isLowerCase_2;
            }
            _while_2 = _and_2;
          }
          String _substring_1 = name.substring(start, index);
          String _upperCase_1 = _substring_1.toUpperCase();
          StringBuffer _append_1 = result.append(_upperCase_1);
          _append_1.append("_");
          start = index;
        }
        _while = (start < length);
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
  
  private static List<Map.Entry<IConfigurationElement,IConfigurationElement>> sortByKeyElementName(final Iterable<Map.Entry<IConfigurationElement,IConfigurationElement>> entries) {
    List<Map.Entry<IConfigurationElement,IConfigurationElement>> _xblockexpression = null;
    {
      final List<Map.Entry<IConfigurationElement,IConfigurationElement>> sorted = Lists.<Map.Entry<IConfigurationElement,IConfigurationElement>>newArrayList(entries);
      final Comparator<Map.Entry<IConfigurationElement,IConfigurationElement>> _function = new Comparator<Map.Entry<IConfigurationElement,IConfigurationElement>>() {
        public int compare(final Map.Entry<IConfigurationElement,IConfigurationElement> x, final Map.Entry<IConfigurationElement,IConfigurationElement> y) {
          IConfigurationElement _key = x.getKey();
          String _elementName = Generator.elementName(_key);
          IConfigurationElement _key_1 = y.getKey();
          String _elementName_1 = Generator.elementName(_key_1);
          return _elementName.compareTo(_elementName_1);
        }
      };
      Collections.<Map.Entry<IConfigurationElement,IConfigurationElement>>sort(sorted, _function);
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
        IConfigurationElement _get = javaDocs.get(0);
        String _value = _get.getValue();
        String _trim = _value.trim();
        _builder.append(_trim, "");
        _builder.newLineIfNotEmpty();
        return _builder.toString();
      }
      _xblockexpression = "";
    }
    return _xblockexpression;
  }
  
  private static String elementPackageName(final IConfigurationElement element) {
    IConfigurationElement c = element;
    boolean _and = false;
    boolean _notEquals = (!Objects.equal(c, null));
    if (!_notEquals) {
      _and = false;
    } else {
      String _name = c.getName();
      boolean _equals = "Package".equals(_name);
      boolean _not = (!_equals);
      _and = _not;
    }
    boolean _while = _and;
    while (_while) {
      {
        final Object parent = c.getParent();
        c = null;
        if ((parent instanceof IConfigurationElement)) {
          c = ((IConfigurationElement) parent);
        }
      }
      boolean _and_1 = false;
      boolean _notEquals_1 = (!Objects.equal(c, null));
      if (!_notEquals_1) {
        _and_1 = false;
      } else {
        String _name_1 = c.getName();
        boolean _equals_1 = "Package".equals(_name_1);
        boolean _not_1 = (!_equals_1);
        _and_1 = _not_1;
      }
      _while = _and_1;
    }
    boolean _notEquals_1 = (!Objects.equal(c, null));
    if (_notEquals_1) {
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
          if (Objects.equal(type,"java.lang.Boolean")) {
            _matched=true;
            type = "boolean";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Byte")) {
            _matched=true;
            type = "byte";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Short")) {
            _matched=true;
            type = "short";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Integer")) {
            _matched=true;
            type = "int";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Long")) {
            _matched=true;
            type = "long";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Float")) {
            _matched=true;
            type = "float";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Double")) {
            _matched=true;
            type = "double";
          }
        }
        if (!_matched) {
          if (Objects.equal(type,"java.lang.Character")) {
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
    final String type = Generator.keyType(key);
    int _lastIndexOf = type.lastIndexOf(".");
    int _plus = (_lastIndexOf + 1);
    String _substring = type.substring(_plus);
    final String cloneName = Generator.toFirstKeywordLower(_substring);
    try {
      ThingKey.class.getMethod(cloneName);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(", org.archstudio.bna.keys.ThingKey.");
      _builder.append(cloneName, "");
      _builder.append("()");
      return _builder.toString();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
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
      boolean _isEmpty = toProcess.isEmpty();
      boolean _not = (!_isEmpty);
      boolean _while = _not;
      while (_while) {
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
        boolean _isEmpty_1 = toProcess.isEmpty();
        boolean _not_1 = (!_isEmpty_1);
        _while = _not_1;
      }
      ArrayList<IConfigurationElement> _newArrayList = Lists.<IConfigurationElement>newArrayList(allFacets);
      _xblockexpression = Generator.elementSortByName(_newArrayList);
    }
    return _xblockexpression;
  }
  
  private static Map<IConfigurationElement,IConfigurationElement> thingAllKeysToFacet(final IConfigurationElement thing, final Mappings mappings) {
    Map<IConfigurationElement,IConfigurationElement> _xblockexpression = null;
    {
      final Map<IConfigurationElement,IConfigurationElement> keys = Maps.<IConfigurationElement, IConfigurationElement>newHashMap();
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
      _builder.append("src/");
      String _packageFileName = Generator.packageFileName(packageElement);
      _builder.append(_packageFileName, "");
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
          _builder.append("package ");
          String _packageName = Generator.packageName(packageElement);
          _builder.append(_packageName, "");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
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
          _builder.append("* ");
          Object _elementPath = Generator.elementPath(facet);
          _builder.append(_elementPath, " ");
          _builder.append(".");
          _builder.newLineIfNotEmpty();
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.newLine();
          String _elementJavaDoc = Generator.elementJavaDoc(facet);
          _builder.append(_elementJavaDoc, "");
          _builder.newLineIfNotEmpty();
          _builder.append("@SuppressWarnings(\"all\")");
          _builder.newLine();
          _builder.append("@NonNullByDefault");
          _builder.newLine();
          _builder.append("public interface ");
          String _facetClassName = Generator.facetClassName(facet);
          _builder.append(_facetClassName, "");
          _builder.append(" extends org.archstudio.bna.IThing");
          {
            IConfigurationElement[] _children_1 = facet.getChildren("ExtendsFacet");
            List<IConfigurationElement> _facetSort = Generator.facetSort(((List<IConfigurationElement>)Conversions.doWrapArray(_children_1)), mappings);
            boolean _hasElements = false;
            for(final IConfigurationElement i : _facetSort) {
              if (!_hasElements) {
                _hasElements = true;
                _builder.append(",\n\t\t", "");
              } else {
                _builder.appendImmediate(",\n\t\t", "");
              }
              IConfigurationElement _facetRefToFacet = Generator.facetRefToFacet(i, mappings);
              String _facetFQClassName = Generator.facetFQClassName(_facetRefToFacet);
              _builder.append(_facetFQClassName, "");
            }
          }
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          {
            IConfigurationElement[] _children_2 = facet.getChildren("Method");
            for(final IConfigurationElement method : _children_2) {
              _builder.newLine();
              String _elementJavaDoc_1 = Generator.elementJavaDoc(method);
              _builder.append(_elementJavaDoc_1, "");
              _builder.newLineIfNotEmpty();
              _builder.append("\t\t");
              String _attribute = method.getAttribute("signature");
              _builder.append(_attribute, "\t\t");
              _builder.append(";");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          {
            IConfigurationElement[] _children_3 = facet.getChildren("Key");
            List<IConfigurationElement> _elementSortByName = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_3)));
            for(final IConfigurationElement key : _elementSortByName) {
              _builder.append("\t");
              _builder.append("public static final IThingKey<");
              String _keyType = Generator.keyType(key);
              _builder.append(_keyType, "\t");
              _builder.append("> ");
              String _keyConstantName = Generator.keyConstantName(key);
              _builder.append(_keyConstantName, "\t");
              _builder.append(" = ThingKey.create(com.google.common.collect.Lists.newArrayList(\"");
              String _elementName = Generator.elementName(key);
              String _firstKeywordLower = Generator.toFirstKeywordLower(_elementName);
              _builder.append(_firstKeywordLower, "\t");
              _builder.append("\", ");
              String _facetClassName_1 = Generator.facetClassName(facet);
              _builder.append(_facetClassName_1, "\t");
              _builder.append(".class)");
              String _keyCloneParameter = Generator.keyCloneParameter(key);
              _builder.append(_keyCloneParameter, "\t");
              {
                boolean _keyIsNullable = Generator.keyIsNullable(key);
                if (_keyIsNullable) {
                  _builder.append(", true");
                }
              }
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          {
            IConfigurationElement[] _children_4 = facet.getChildren("RefKey");
            List<IConfigurationElement> _elementSortByName_1 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_4)));
            for(final IConfigurationElement key_1 : _elementSortByName_1) {
              _builder.append("\t");
              _builder.append("public static final IThingRefKey<");
              String _refKeyType = Generator.refKeyType(key_1);
              _builder.append(_refKeyType, "\t");
              _builder.append("> ");
              String _refKeyConstantName = Generator.refKeyConstantName(key_1);
              _builder.append(_refKeyConstantName, "\t");
              _builder.append(" = ThingRefKey.create(com.google.common.collect.Lists.newArrayList(\"");
              String _elementName_1 = Generator.elementName(key_1);
              String _firstKeywordLower_1 = Generator.toFirstKeywordLower(_elementName_1);
              _builder.append(_firstKeywordLower_1, "\t");
              _builder.append("\", ");
              String _facetClassName_2 = Generator.facetClassName(facet);
              _builder.append(_facetClassName_2, "\t");
              _builder.append(".class));");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          {
            IConfigurationElement[] _children_5 = facet.getChildren("Key");
            List<IConfigurationElement> _elementSortByName_2 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_5)));
            for(final IConfigurationElement key_2 : _elementSortByName_2) {
              _builder.append("\t");
              _builder.append("public ");
              {
                boolean _keyIsNullable_1 = Generator.keyIsNullable(key_2);
                if (_keyIsNullable_1) {
                  _builder.append("@Nullable ");
                }
              }
              String _keySimpleType = Generator.keySimpleType(key_2);
              _builder.append(_keySimpleType, "\t");
              _builder.append(" ");
              {
                String _keySimpleType_1 = Generator.keySimpleType(key_2);
                boolean _equals = "boolean".equals(_keySimpleType_1);
                if (_equals) {
                  _builder.append("is");
                } else {
                  _builder.append("get");
                }
              }
              String _keyFunctionName = Generator.keyFunctionName(key_2);
              _builder.append(_keyFunctionName, "\t");
              _builder.append("();");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          {
            IConfigurationElement[] _children_6 = facet.getChildren("RefKey");
            List<IConfigurationElement> _elementSortByName_3 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_6)));
            for(final IConfigurationElement key_3 : _elementSortByName_3) {
              _builder.append("\t");
              _builder.append("public @Nullable ");
              String _refKeyType_1 = Generator.refKeyType(key_3);
              _builder.append(_refKeyType_1, "\t");
              _builder.append(" get");
              String _refKeyFunctionName = Generator.refKeyFunctionName(key_3);
              _builder.append(_refKeyFunctionName, "\t");
              _builder.append("(org.archstudio.bna.IBNAModel model);");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
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
          _builder_1.append("package ");
          String _packageName_1 = Generator.packageName(packageElement);
          _builder_1.append(_packageName_1, "");
          _builder_1.append(";");
          _builder_1.newLineIfNotEmpty();
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
          _builder_1.append("* ");
          Object _elementPath_1 = Generator.elementPath(facet);
          _builder_1.append(_elementPath_1, " ");
          _builder_1.append(".");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append(" ");
          _builder_1.append("*/");
          _builder_1.newLine();
          _builder_1.newLine();
          String _elementJavaDoc_2 = Generator.elementJavaDoc(facet);
          _builder_1.append(_elementJavaDoc_2, "");
          _builder_1.newLineIfNotEmpty();
          _builder_1.append("@SuppressWarnings(\"all\")");
          _builder_1.newLine();
          _builder_1.append("@NonNullByDefault");
          _builder_1.newLine();
          _builder_1.append("public interface ");
          String _facetMutableClassName = Generator.facetMutableClassName(facet);
          _builder_1.append(_facetMutableClassName, "");
          _builder_1.append(" extends ");
          String _facetClassName_3 = Generator.facetClassName(facet);
          _builder_1.append(_facetClassName_3, "");
          {
            IConfigurationElement[] _children_7 = facet.getChildren("ExtendsFacet");
            List<IConfigurationElement> _facetSort_1 = Generator.facetSort(((List<IConfigurationElement>)Conversions.doWrapArray(_children_7)), mappings);
            boolean _hasElements_1 = false;
            for(final IConfigurationElement i_1 : _facetSort_1) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
                _builder_1.append(",\n\t\t", "");
              } else {
                _builder_1.appendImmediate(",\n\t\t", "");
              }
              IConfigurationElement _facetRefToFacet_1 = Generator.facetRefToFacet(i_1, mappings);
              String _facetFQMutableClassName = Generator.facetFQMutableClassName(_facetRefToFacet_1);
              _builder_1.append(_facetFQMutableClassName, "");
            }
          }
          _builder_1.append(" {");
          _builder_1.newLineIfNotEmpty();
          _builder_1.newLine();
          {
            IConfigurationElement[] _children_8 = facet.getChildren("EditableQuality");
            List<IConfigurationElement> _elementSortByName_4 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_8)));
            for(final IConfigurationElement quality : _elementSortByName_4) {
              _builder_1.append("\t");
              _builder_1.append("public static final String ");
              String _elementName_2 = Generator.elementName(quality);
              String _constantName = Generator.toConstantName(_elementName_2);
              _builder_1.append(_constantName, "\t");
              _builder_1.append(" = \"");
              String _elementName_3 = Generator.elementName(quality);
              _builder_1.append(_elementName_3, "\t");
              _builder_1.append("\";");
              _builder_1.newLineIfNotEmpty();
              _builder_1.newLine();
            }
          }
          {
            IConfigurationElement[] _children_9 = facet.getChildren("Key");
            List<IConfigurationElement> _elementSortByName_5 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_9)));
            for(final IConfigurationElement key_4 : _elementSortByName_5) {
              _builder_1.append("\t");
              _builder_1.append("public void set");
              String _keyFunctionName_1 = Generator.keyFunctionName(key_4);
              _builder_1.append(_keyFunctionName_1, "\t");
              _builder_1.append("(");
              {
                boolean _keyIsNullable_2 = Generator.keyIsNullable(key_4);
                if (_keyIsNullable_2) {
                  _builder_1.append("@Nullable ");
                }
              }
              String _keySimpleType_2 = Generator.keySimpleType(key_4);
              _builder_1.append(_keySimpleType_2, "\t");
              _builder_1.append(" ");
              String _keyVariableName = Generator.keyVariableName(key_4);
              _builder_1.append(_keyVariableName, "\t");
              _builder_1.append(");");
              _builder_1.newLineIfNotEmpty();
              _builder_1.newLine();
            }
          }
          {
            IConfigurationElement[] _children_10 = facet.getChildren("RefKey");
            List<IConfigurationElement> _elementSortByName_6 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_10)));
            for(final IConfigurationElement key_5 : _elementSortByName_6) {
              _builder_1.append("\t");
              _builder_1.append("public void set");
              String _refKeyFunctionName_1 = Generator.refKeyFunctionName(key_5);
              _builder_1.append(_refKeyFunctionName_1, "\t");
              _builder_1.append("(@Nullable ");
              String _refKeyType_2 = Generator.refKeyType(key_5);
              _builder_1.append(_refKeyType_2, "\t");
              _builder_1.append(" ");
              String _refKeyVariableName = Generator.refKeyVariableName(key_5);
              _builder_1.append(_refKeyVariableName, "\t");
              _builder_1.append(");");
              _builder_1.newLineIfNotEmpty();
              _builder_1.newLine();
            }
          }
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
          _builder.append("package ");
          String _packageName = Generator.packageName(packageElement);
          _builder.append(_packageName, "");
          _builder.append(";");
          _builder.newLineIfNotEmpty();
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
          _builder.append("* ");
          Object _elementPath = Generator.elementPath(thing);
          _builder.append(_elementPath, " ");
          _builder.append(".");
          _builder.newLineIfNotEmpty();
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append(" ");
          _builder.newLine();
          String _elementJavaDoc = Generator.elementJavaDoc(thing);
          _builder.append(_elementJavaDoc, "");
          _builder.newLineIfNotEmpty();
          _builder.append("@SuppressWarnings(\"all\")");
          _builder.newLine();
          _builder.append("@NonNullByDefault");
          _builder.newLine();
          _builder.append("public abstract class ");
          String _thingAbstractClassName = Generator.thingAbstractClassName(thing);
          _builder.append(_thingAbstractClassName, "");
          _builder.append(" extends ");
          String _thingExtendsClassName = Generator.thingExtendsClassName(thing, mappings);
          _builder.append(_thingExtendsClassName, "");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append("implements org.archstudio.bna.IThing");
          {
            IConfigurationElement[] _children_2 = thing.getChildren("ImplementsFacet");
            List<IConfigurationElement> _facetSort = Generator.facetSort(((List<IConfigurationElement>)Conversions.doWrapArray(_children_2)), mappings);
            boolean _hasElements = false;
            for(final IConfigurationElement i : _facetSort) {
              if (!_hasElements) {
                _hasElements = true;
                _builder.append(",\n\t\t", "\t");
              } else {
                _builder.appendImmediate(",\n\t\t", "\t");
              }
              {
                boolean _implementsFacetIsReadOnly = Generator.implementsFacetIsReadOnly(i);
                if (_implementsFacetIsReadOnly) {
                  IConfigurationElement _facetRefToFacet = Generator.facetRefToFacet(i, mappings);
                  String _facetFQClassName = Generator.facetFQClassName(_facetRefToFacet);
                  _builder.append(_facetFQClassName, "\t");
                } else {
                  IConfigurationElement _facetRefToFacet_1 = Generator.facetRefToFacet(i, mappings);
                  String _facetFQMutableClassName = Generator.facetFQMutableClassName(_facetRefToFacet_1);
                  _builder.append(_facetFQMutableClassName, "\t");
                }
              }
            }
          }
          {
            IConfigurationElement[] _children_3 = thing.getChildren("ImplementsInterface");
            boolean _hasElements_1 = false;
            for(final IConfigurationElement i_1 : _children_3) {
              if (!_hasElements_1) {
                _hasElements_1 = true;
                _builder.append(",\n\t\t", "\t");
              } else {
                _builder.appendImmediate(",\n\t\t", "\t");
              }
              String _interfaceFQClassName = Generator.interfaceFQClassName(i_1);
              _builder.append(_interfaceFQClassName, "\t");
            }
          }
          _builder.append(" {");
          _builder.newLineIfNotEmpty();
          _builder.newLine();
          {
            IConfigurationElement[] _children_4 = thing.getChildren("Key");
            List<IConfigurationElement> _elementSortByName = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_4)));
            for(final IConfigurationElement key : _elementSortByName) {
              _builder.append("\t");
              _builder.append("public static final IThingKey<");
              String _keyType = Generator.keyType(key);
              _builder.append(_keyType, "\t");
              _builder.append("> ");
              String _keyConstantName = Generator.keyConstantName(key);
              _builder.append(_keyConstantName, "\t");
              _builder.append(" = ThingKey.create(com.google.common.collect.Lists.newArrayList(\"");
              String _elementName = Generator.elementName(key);
              String _firstKeywordLower = Generator.toFirstKeywordLower(_elementName);
              _builder.append(_firstKeywordLower, "\t");
              _builder.append("\", ");
              String _thingAbstractClassName_1 = Generator.thingAbstractClassName(thing);
              _builder.append(_thingAbstractClassName_1, "\t");
              _builder.append(".class)");
              String _keyCloneParameter = Generator.keyCloneParameter(key);
              _builder.append(_keyCloneParameter, "\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          {
            IConfigurationElement[] _children_5 = thing.getChildren("RefKey");
            List<IConfigurationElement> _elementSortByName_1 = Generator.elementSortByName(((List<IConfigurationElement>)Conversions.doWrapArray(_children_5)));
            for(final IConfigurationElement key_1 : _elementSortByName_1) {
              _builder.append("\t");
              _builder.append("public static final IThingRefKey<");
              String _keyType_1 = Generator.keyType(key_1);
              _builder.append(_keyType_1, "\t");
              _builder.append("> ");
              String _keyConstantName_1 = Generator.keyConstantName(key_1);
              _builder.append(_keyConstantName_1, "\t");
              _builder.append(" = ThingRefKey.create(com.google.common.collect.Lists.newArrayList(\"");
              String _elementName_1 = Generator.elementName(key_1);
              String _firstKeywordLower_1 = Generator.toFirstKeywordLower(_elementName_1);
              _builder.append(_firstKeywordLower_1, "\t");
              _builder.append("\", ");
              String _thingAbstractClassName_2 = Generator.thingAbstractClassName(thing);
              _builder.append(_thingAbstractClassName_2, "\t");
              _builder.append(".class));");
              _builder.newLineIfNotEmpty();
              _builder.newLine();
            }
          }
          _builder.append("\t");
          _builder.append("public ");
          String _thingAbstractClassName_3 = Generator.thingAbstractClassName(thing);
          _builder.append(_thingAbstractClassName_3, "\t");
          _builder.append("(@Nullable Object id) {");
          _builder.newLineIfNotEmpty();
          _builder.append("\t\t");
          _builder.append("super(id);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          {
            boolean _thingIsAbstract = Generator.thingIsAbstract(thing);
            boolean _not_1 = (!_thingIsAbstract);
            if (_not_1) {
              _builder.newLine();
              _builder.append("@Override");
              _builder.newLine();
              _builder.append("public IThingPeer<? extends ");
              String _thingClassName = Generator.thingClassName(thing);
              _builder.append(_thingClassName, "");
              _builder.append("> createPeer(IBNAView view, ICoordinateMapper cm){");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("return new ");
              String _thingPeerClassName = Generator.thingPeerClassName(thing);
              _builder.append(_thingPeerClassName, "\t");
              _builder.append("<>((");
              String _thingClassName_1 = Generator.thingClassName(thing);
              _builder.append(_thingClassName_1, "\t");
              _builder.append(")this, view, cm);");
              _builder.newLineIfNotEmpty();
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.newLine();
          _builder.append("\t");
          _builder.append("@Override");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("protected void initProperties() {");
          _builder.newLine();
          {
            Map<IConfigurationElement,IConfigurationElement> _thingAllKeysToFacet = Generator.thingAllKeysToFacet(thing, mappings);
            Set<Map.Entry<IConfigurationElement,IConfigurationElement>> _entrySet = _thingAllKeysToFacet.entrySet();
            List<Map.Entry<IConfigurationElement,IConfigurationElement>> _sortByKeyElementName = Generator.sortByKeyElementName(_entrySet);
            for(final Map.Entry<IConfigurationElement, IConfigurationElement> keyToFacet : _sortByKeyElementName) {
              _builder.append("\t\t");
              _builder.append("initProperty(");
              IConfigurationElement _key = keyToFacet.getKey();
              String _keyFQConstantName = Generator.keyFQConstantName(_key);
              _builder.append(_keyFQConstantName, "\t\t");
              _builder.append(", ");
              IConfigurationElement _key_1 = keyToFacet.getKey();
              String _keyDefaultValue = Generator.keyDefaultValue(_key_1);
              _builder.append(_keyDefaultValue, "\t\t");
              _builder.append(");");
              {
                boolean _and = false;
                IConfigurationElement _value = keyToFacet.getValue();
                IConfigurationElement[] _children_6 = thing.getChildren("ImplementsFacet");
                boolean _facetIsReadOnly = Generator.facetIsReadOnly(_value, _children_6);
                boolean _not_2 = (!_facetIsReadOnly);
                if (!_not_2) {
                  _and = false;
                } else {
                  IConfigurationElement _key_2 = keyToFacet.getKey();
                  boolean _keyIsShapeModifying = Generator.keyIsShapeModifying(_key_2);
                  _and = _keyIsShapeModifying;
                }
                if (_and) {
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t\t");
                  _builder.append("addShapeModifyingKey(");
                  IConfigurationElement _key_3 = keyToFacet.getKey();
                  String _keyFQConstantName_1 = Generator.keyFQConstantName(_key_3);
                  _builder.append(_keyFQConstantName_1, "\t\t");
                  _builder.append(");");
                }
              }
              _builder.newLineIfNotEmpty();
            }
          }
          {
            List<IConfigurationElement> _thingAllRefKeys = Generator.thingAllRefKeys(thing, mappings);
            List<IConfigurationElement> _elementSortByName_2 = Generator.elementSortByName(_thingAllRefKeys);
            for(final IConfigurationElement key_2 : _elementSortByName_2) {
              _builder.append("\t\t");
              _builder.append("initProperty(");
              String _refKeyFQConstantName = Generator.refKeyFQConstantName(key_2);
              _builder.append(_refKeyFQConstantName, "\t\t");
              _builder.append(", null);");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t\t");
          _builder.append("super.initProperties();");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.newLine();
          {
            Map<IConfigurationElement,IConfigurationElement> _thingAllKeysToFacet_1 = Generator.thingAllKeysToFacet(thing, mappings);
            Set<Map.Entry<IConfigurationElement,IConfigurationElement>> _entrySet_1 = _thingAllKeysToFacet_1.entrySet();
            List<Map.Entry<IConfigurationElement,IConfigurationElement>> _sortByKeyElementName_1 = Generator.sortByKeyElementName(_entrySet_1);
            for(final Map.Entry<IConfigurationElement, IConfigurationElement> keyToFacet_1 : _sortByKeyElementName_1) {
              _builder.append("\t");
              _builder.append("public ");
              {
                IConfigurationElement _key_4 = keyToFacet_1.getKey();
                boolean _keyIsNullable = Generator.keyIsNullable(_key_4);
                if (_keyIsNullable) {
                  _builder.append("@Nullable ");
                }
              }
              IConfigurationElement _key_5 = keyToFacet_1.getKey();
              String _keySimpleType = Generator.keySimpleType(_key_5);
              _builder.append(_keySimpleType, "\t");
              _builder.append(" ");
              {
                IConfigurationElement _key_6 = keyToFacet_1.getKey();
                String _keySimpleType_1 = Generator.keySimpleType(_key_6);
                boolean _equals = "boolean".equals(_keySimpleType_1);
                if (_equals) {
                  _builder.append("is");
                } else {
                  _builder.append("get");
                }
              }
              IConfigurationElement _key_7 = keyToFacet_1.getKey();
              String _keyFunctionName = Generator.keyFunctionName(_key_7);
              _builder.append(_keyFunctionName, "\t");
              _builder.append("() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return get(");
              IConfigurationElement _key_8 = keyToFacet_1.getKey();
              String _keyFQConstantName_2 = Generator.keyFQConstantName(_key_8);
              _builder.append(_keyFQConstantName_2, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.newLine();
              _builder.append("\t");
              _builder.append("/*package*/ ");
              {
                IConfigurationElement _key_9 = keyToFacet_1.getKey();
                boolean _keyIsNullable_1 = Generator.keyIsNullable(_key_9);
                if (_keyIsNullable_1) {
                  _builder.append("@Nullable ");
                }
              }
              IConfigurationElement _key_10 = keyToFacet_1.getKey();
              String _keySimpleType_2 = Generator.keySimpleType(_key_10);
              _builder.append(_keySimpleType_2, "\t");
              _builder.append(" ");
              {
                IConfigurationElement _key_11 = keyToFacet_1.getKey();
                String _keySimpleType_3 = Generator.keySimpleType(_key_11);
                boolean _equals_1 = "boolean".equals(_keySimpleType_3);
                if (_equals_1) {
                  _builder.append("is");
                } else {
                  _builder.append("get");
                }
              }
              _builder.append("Raw");
              IConfigurationElement _key_12 = keyToFacet_1.getKey();
              String _keyFunctionName_1 = Generator.keyFunctionName(_key_12);
              _builder.append(_keyFunctionName_1, "\t");
              _builder.append("() {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return getRaw(");
              IConfigurationElement _key_13 = keyToFacet_1.getKey();
              String _keyFQConstantName_3 = Generator.keyFQConstantName(_key_13);
              _builder.append(_keyFQConstantName_3, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.newLine();
              _builder.append("\t");
              {
                IConfigurationElement _value_1 = keyToFacet_1.getValue();
                IConfigurationElement[] _children_7 = thing.getChildren("ImplementsFacet");
                boolean _facetIsReadOnly_1 = Generator.facetIsReadOnly(_value_1, _children_7);
                if (_facetIsReadOnly_1) {
                  _builder.append("/*package*/ ");
                } else {
                  _builder.append("public ");
                }
              }
              _builder.append("void set");
              IConfigurationElement _key_14 = keyToFacet_1.getKey();
              String _keyFunctionName_2 = Generator.keyFunctionName(_key_14);
              _builder.append(_keyFunctionName_2, "\t");
              _builder.append("(");
              {
                IConfigurationElement _key_15 = keyToFacet_1.getKey();
                boolean _keyIsNullable_2 = Generator.keyIsNullable(_key_15);
                if (_keyIsNullable_2) {
                  _builder.append("@Nullable ");
                }
              }
              IConfigurationElement _key_16 = keyToFacet_1.getKey();
              String _keySimpleType_4 = Generator.keySimpleType(_key_16);
              _builder.append(_keySimpleType_4, "\t");
              _builder.append(" ");
              IConfigurationElement _key_17 = keyToFacet_1.getKey();
              String _keyVariableName = Generator.keyVariableName(_key_17);
              _builder.append(_keyVariableName, "\t");
              _builder.append(") {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("set(");
              IConfigurationElement _key_18 = keyToFacet_1.getKey();
              String _keyFQConstantName_4 = Generator.keyFQConstantName(_key_18);
              _builder.append(_keyFQConstantName_4, "\t\t");
              _builder.append(", ");
              IConfigurationElement _key_19 = keyToFacet_1.getKey();
              String _keyVariableName_1 = Generator.keyVariableName(_key_19);
              _builder.append(_keyVariableName_1, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.newLine();
              _builder.append("\t");
              _builder.append("/*package*/ ");
              {
                IConfigurationElement _key_20 = keyToFacet_1.getKey();
                boolean _keyIsNullable_3 = Generator.keyIsNullable(_key_20);
                if (_keyIsNullable_3) {
                  _builder.append("@Nullable ");
                }
              }
              IConfigurationElement _key_21 = keyToFacet_1.getKey();
              String _keySimpleType_5 = Generator.keySimpleType(_key_21);
              _builder.append(_keySimpleType_5, "\t");
              _builder.append(" ");
              {
                IConfigurationElement _key_22 = keyToFacet_1.getKey();
                String _keySimpleType_6 = Generator.keySimpleType(_key_22);
                boolean _equals_2 = "boolean".equals(_keySimpleType_6);
                if (_equals_2) {
                  _builder.append("is");
                } else {
                  _builder.append("set");
                }
              }
              _builder.append("Raw");
              IConfigurationElement _key_23 = keyToFacet_1.getKey();
              String _keyFunctionName_3 = Generator.keyFunctionName(_key_23);
              _builder.append(_keyFunctionName_3, "\t");
              _builder.append("(");
              {
                IConfigurationElement _key_24 = keyToFacet_1.getKey();
                boolean _keyIsNullable_4 = Generator.keyIsNullable(_key_24);
                if (_keyIsNullable_4) {
                  _builder.append("@Nullable ");
                }
              }
              IConfigurationElement _key_25 = keyToFacet_1.getKey();
              String _keySimpleType_7 = Generator.keySimpleType(_key_25);
              _builder.append(_keySimpleType_7, "\t");
              _builder.append(" ");
              IConfigurationElement _key_26 = keyToFacet_1.getKey();
              String _keyVariableName_2 = Generator.keyVariableName(_key_26);
              _builder.append(_keyVariableName_2, "\t");
              _builder.append(") {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return setRaw(");
              IConfigurationElement _key_27 = keyToFacet_1.getKey();
              String _keyFQConstantName_5 = Generator.keyFQConstantName(_key_27);
              _builder.append(_keyFQConstantName_5, "\t\t");
              _builder.append(", ");
              IConfigurationElement _key_28 = keyToFacet_1.getKey();
              String _keyVariableName_3 = Generator.keyVariableName(_key_28);
              _builder.append(_keyVariableName_3, "\t\t");
              _builder.append(");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.newLine();
            }
          }
          {
            List<IConfigurationElement> _thingAllRefKeys_1 = Generator.thingAllRefKeys(thing, mappings);
            for(final IConfigurationElement key_3 : _thingAllRefKeys_1) {
              _builder.append("\t");
              _builder.append("public ");
              String _refKeyType = Generator.refKeyType(key_3);
              _builder.append(_refKeyType, "\t");
              _builder.append(" get");
              String _refKeyFunctionName = Generator.refKeyFunctionName(key_3);
              _builder.append(_refKeyFunctionName, "\t");
              _builder.append("(org.archstudio.bna.IBNAModel model) {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("org.archstudio.bna.IThing thing = model.getThing(getRaw(");
              String _keyFQConstantName_6 = Generator.keyFQConstantName(key_3);
              _builder.append(_keyFQConstantName_6, "\t\t");
              _builder.append("));");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("if (thing instanceof ");
              String _refKeyType_1 = Generator.refKeyType(key_3);
              _builder.append(_refKeyType_1, "\t\t");
              _builder.append(") {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("return (");
              String _refKeyType_2 = Generator.refKeyType(key_3);
              _builder.append(_refKeyType_2, "\t\t\t");
              _builder.append(") thing;");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return null;");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
              _builder.newLine();
              _builder.append("\t");
              _builder.append("public void set");
              String _refKeyFunctionName_1 = Generator.refKeyFunctionName(key_3);
              _builder.append(_refKeyFunctionName_1, "\t");
              _builder.append("(");
              String _refKeyType_3 = Generator.refKeyType(key_3);
              _builder.append(_refKeyType_3, "\t");
              _builder.append(" ");
              String _refKeyVariableName = Generator.refKeyVariableName(key_3);
              _builder.append(_refKeyVariableName, "\t");
              _builder.append(") {");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("setRaw(");
              String _keyFQConstantName_7 = Generator.keyFQConstantName(key_3);
              _builder.append(_keyFQConstantName_7, "\t\t");
              _builder.append(", ");
              String _refKeyVariableName_1 = Generator.refKeyVariableName(key_3);
              _builder.append(_refKeyVariableName_1, "\t\t");
              _builder.append(".getID());");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
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
          boolean _not_3 = (!_exists_1);
          if (_not_3) {
            file.create(null, true, null);
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append("package ");
            String _packageName_1 = Generator.packageName(packageElement);
            _builder_1.append(_packageName_1, "");
            _builder_1.append(";");
            _builder_1.newLineIfNotEmpty();
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
            _builder_1.append("public ");
            {
              boolean _thingIsAbstract_1 = Generator.thingIsAbstract(thing);
              if (_thingIsAbstract_1) {
                _builder_1.append("abstract ");
              }
            }
            _builder_1.append(" class ");
            String _thingClassName_2 = Generator.thingClassName(thing);
            _builder_1.append(_thingClassName_2, "");
            _builder_1.append(" extends ");
            String _thingAbstractClassName_4 = Generator.thingAbstractClassName(thing);
            _builder_1.append(_thingAbstractClassName_4, "");
            _builder_1.newLineIfNotEmpty();
            _builder_1.append("{");
            _builder_1.newLine();
            _builder_1.newLine();
            _builder_1.append("\t");
            _builder_1.append("public ");
            String _thingClassName_3 = Generator.thingClassName(thing);
            _builder_1.append(_thingClassName_3, "\t");
            _builder_1.append("(@Nullable Object id) {");
            _builder_1.newLineIfNotEmpty();
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
          boolean _not_4 = (!_exists_2);
          if (_not_4) {
            file.create(null, true, null);
            StringConcatenation _builder_2 = new StringConcatenation();
            _builder_2.append("package ");
            String _packageName_2 = Generator.packageName(packageElement);
            _builder_2.append(_packageName_2, "");
            _builder_2.append(";");
            _builder_2.newLineIfNotEmpty();
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
            _builder_2.append("public ");
            {
              boolean _thingIsAbstract_2 = Generator.thingIsAbstract(thing);
              if (_thingIsAbstract_2) {
                _builder_2.append("abstract ");
              }
            }
            _builder_2.append("class ");
            String _thingPeerClassName_1 = Generator.thingPeerClassName(thing);
            _builder_2.append(_thingPeerClassName_1, "");
            _builder_2.append("<T extends ");
            String _thingClassName_4 = Generator.thingClassName(thing);
            _builder_2.append(_thingClassName_4, "");
            _builder_2.append("> extends AbstractThingPeer<T>");
            _builder_2.newLineIfNotEmpty();
            _builder_2.append("{");
            _builder_2.newLine();
            _builder_2.newLine();
            _builder_2.append("\t");
            _builder_2.append("public ");
            String _thingPeerClassName_2 = Generator.thingPeerClassName(thing);
            _builder_2.append(_thingPeerClassName_2, "\t");
            _builder_2.append("(T thing, IBNAView view, ICoordinateMapper cm) {");
            _builder_2.newLineIfNotEmpty();
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
