package org.archstudio.myxgen.jet.codegen;

import com.google.common.base.Objects;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.util.List;
import org.archstudio.myxgen.MyxGenBrick;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.Signature;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.osgi.framework.Bundle;

@SuppressWarnings("all")
public class MyxCompUtils {
  public static String toPackageName(final String fullClassName) {
    int _lastIndexOf = fullClassName.lastIndexOf(".");
    String _substring = fullClassName.substring(0, _lastIndexOf);
    return _substring;
  }
  
  public static String toClassName(final String fullClassName) {
    int _lastIndexOf = fullClassName.lastIndexOf(".");
    int _plus = (_lastIndexOf + 1);
    String _substring = fullClassName.substring(_plus);
    return _substring;
  }
  
  public static String toConstantName(final String fullVariableName) {
    StringBuilder _stringBuilder = new StringBuilder();
    final StringBuilder sb = _stringBuilder;
    char[] _charArray = fullVariableName.toCharArray();
    for (final char c : _charArray) {
      {
        boolean _isUpperCase = Character.isUpperCase(c);
        if (_isUpperCase) {
          sb.append("_");
        }
        char _upperCase = Character.toUpperCase(c);
        sb.append(_upperCase);
      }
    }
    return sb.toString();
  }
  
  public static String constructorsFor(final MyxGenBrick brick, final String bundleProjectId, final String fullClassName) throws MalformedURLException {
    try {
      StringBuffer _stringBuffer = new StringBuffer();
      final StringBuffer sb = _stringBuffer;
      try {
        IWorkspace _workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot _root = _workspace.getRoot();
        IProject _project = _root.getProject(bundleProjectId);
        String _replace = fullClassName.replace(".", "/");
        String _plus = ("src/" + _replace);
        String _plus_1 = (_plus + ".java");
        IResource res = _project.findMember(_plus_1);
        final ICompilationUnit cu = JavaCore.createCompilationUnitFrom(((IFile) res));
        String _className = MyxCompUtils.toClassName(fullClassName);
        final IType cuType = cu.getType(_className);
        IMethod[] _methods = cuType.getMethods();
        for (final IMethod m : _methods) {
          boolean _and = false;
          boolean _isConstructor = m.isConstructor();
          if (!_isConstructor) {
            _and = false;
          } else {
            boolean _or = false;
            int _flags = m.getFlags();
            boolean _isPublic = Flags.isPublic(_flags);
            if (_isPublic) {
              _or = true;
            } else {
              int _flags_1 = m.getFlags();
              boolean _isProtected = Flags.isProtected(_flags_1);
              _or = (_isPublic || _isProtected);
            }
            _and = (_isConstructor && _or);
          }
          if (_and) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("/**");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("* A constructor from the super class...");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("* @generated");
            _builder.newLine();
            _builder.append(" ");
            _builder.append("*/");
            sb.append(_builder.toString());
            int _flags_2 = m.getFlags();
            String _string = Flags.toString(_flags_2);
            String _plus_2 = (_string + " ");
            String _stubClassName = brick.getStubClassName();
            String _className_1 = MyxCompUtils.toClassName(_stubClassName);
            String _plus_3 = (_plus_2 + _className_1);
            sb.append(_plus_3);
            StringBuffer _stringBuffer_1 = new StringBuffer();
            final StringBuffer args = _stringBuffer_1;
            StringBuffer _stringBuffer_2 = new StringBuffer();
            final StringBuffer superArgs = _stringBuffer_2;
            int i = 0;
            int _numberOfParameters = m.getNumberOfParameters();
            boolean _lessThan = (i < _numberOfParameters);
            boolean _while = _lessThan;
            while (_while) {
              {
                String[] _parameterNames = m.getParameterNames();
                final String name = ((List<String>)Conversions.doWrapArray(_parameterNames)).get(i);
                final String type = MyxCompUtils.toType(cuType, m, i);
                boolean _greaterThan = (i > 0);
                if (_greaterThan) {
                  args.append(", ");
                }
                String _plus_4 = (type + " ");
                String _plus_5 = (_plus_4 + name);
                args.append(_plus_5);
                boolean _greaterThan_1 = (i > 0);
                if (_greaterThan_1) {
                  superArgs.append(", ");
                }
                superArgs.append(name);
                int _plus_6 = (i + 1);
                i = _plus_6;
              }
              int _numberOfParameters_1 = m.getNumberOfParameters();
              boolean _lessThan_1 = (i < _numberOfParameters_1);
              _while = _lessThan_1;
            }
            String _plus_4 = ("(" + args);
            String _plus_5 = (_plus_4 + ") { super(");
            String _plus_6 = (_plus_5 + superArgs);
            String _plus_7 = (_plus_6 + "); }");
            sb.append(_plus_7);
          }
        }
        return sb.toString();
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          e.printStackTrace();
          Bundle _bundle = Platform.getBundle(bundleProjectId);
          final Class parent = _bundle.loadClass(fullClassName);
          Constructor<? extends Object>[] _declaredConstructors = parent.getDeclaredConstructors();
          for (final Constructor c : _declaredConstructors) {
            boolean _or_1 = false;
            int _modifiers = c.getModifiers();
            boolean _isPublic_1 = Modifier.isPublic(_modifiers);
            if (_isPublic_1) {
              _or_1 = true;
            } else {
              int _modifiers_1 = c.getModifiers();
              boolean _isProtected_1 = Modifier.isProtected(_modifiers_1);
              _or_1 = (_isPublic_1 || _isProtected_1);
            }
            if (_or_1) {
              StringConcatenation _builder_1 = new StringConcatenation();
              _builder_1.append("/**");
              _builder_1.newLine();
              _builder_1.append(" ");
              _builder_1.append("* A constructor from the super class...");
              _builder_1.newLine();
              _builder_1.append(" ");
              _builder_1.append("* @generated");
              _builder_1.newLine();
              _builder_1.append(" ");
              _builder_1.append("*/");
              sb.append(_builder_1.toString());
              int _modifiers_2 = c.getModifiers();
              String _string_1 = Modifier.toString(_modifiers_2);
              String _plus_8 = (_string_1 + " ");
              String _stubClassName_1 = brick.getStubClassName();
              String _className_2 = MyxCompUtils.toClassName(_stubClassName_1);
              String _plus_9 = (_plus_8 + _className_2);
              sb.append(_plus_9);
              StringBuffer _stringBuffer_3 = new StringBuffer();
              final StringBuffer args_1 = _stringBuffer_3;
              StringBuffer _stringBuffer_4 = new StringBuffer();
              final StringBuffer superArgs_1 = _stringBuffer_4;
              int i_1 = 0;
              Class<? extends Object>[] _parameterTypes = c.getParameterTypes();
              int _size = ((List<Class<? extends Object>>)Conversions.doWrapArray(_parameterTypes)).size();
              boolean _lessThan_1 = (i_1 < _size);
              boolean _while_1 = _lessThan_1;
              while (_while_1) {
                {
                  final String name = ("arg" + Integer.valueOf(i_1));
                  final String type = MyxCompUtils.toType(c, i_1);
                  boolean _greaterThan = (i_1 > 0);
                  if (_greaterThan) {
                    args_1.append(", ");
                  }
                  String _plus_10 = (type + " ");
                  String _plus_11 = (_plus_10 + name);
                  args_1.append(_plus_11);
                  boolean _greaterThan_1 = (i_1 > 0);
                  if (_greaterThan_1) {
                    superArgs_1.append(", ");
                  }
                  superArgs_1.append(name);
                  int _plus_12 = (i_1 + 1);
                  i_1 = _plus_12;
                }
                Class<? extends Object>[] _parameterTypes_1 = c.getParameterTypes();
                int _size_1 = ((List<Class<? extends Object>>)Conversions.doWrapArray(_parameterTypes_1)).size();
                boolean _lessThan_2 = (i_1 < _size_1);
                _while_1 = _lessThan_2;
              }
              String _plus_10 = ("(" + args_1);
              String _plus_11 = (_plus_10 + ") { super(");
              String _plus_12 = (_plus_11 + superArgs_1);
              String _plus_13 = (_plus_12 + "); }");
              sb.append(_plus_13);
            }
          }
          return sb.toString();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static String toType(final IType t, final IMethod m, final int i) {
    try {
      String[] _parameterTypes = m.getParameterTypes();
      String _get = ((List<String>)Conversions.doWrapArray(_parameterTypes)).get(i);
      final String type = Signature.toString(_get);
      final String[][] resolved = t.resolveType(type);
      boolean _equals = Objects.equal(resolved, null);
      if (_equals) {
        return type;
      }
      String[] _get_1 = ((List<String[]>)Conversions.doWrapArray(resolved)).get(0);
      String _get_2 = ((List<String>)Conversions.doWrapArray(_get_1)).get(0);
      boolean _equals_1 = "".equals(_get_2);
      if (_equals_1) {
        return type;
      }
      String[] _get_3 = ((List<String[]>)Conversions.doWrapArray(resolved)).get(0);
      String _get_4 = ((List<String>)Conversions.doWrapArray(_get_3)).get(0);
      String _plus = (_get_4 + ".");
      String[] _get_5 = ((List<String[]>)Conversions.doWrapArray(resolved)).get(0);
      String _get_6 = ((List<String>)Conversions.doWrapArray(_get_5)).get(1);
      final String c = (_plus + _get_6);
      return c;
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static String toType(final Constructor c, final int i) {
    Class<? extends Object>[] _parameterTypes = c.getParameterTypes();
    Class<? extends Object> _get = ((List<Class<? extends Object>>)Conversions.doWrapArray(_parameterTypes)).get(i);
    final String t = MyxCompUtils.toType(_get);
    return t;
  }
  
  private static String toType(final Class c) {
    boolean _isArray = c.isArray();
    if (_isArray) {
      Class<? extends Object> _componentType = c.getComponentType();
      String _type = MyxCompUtils.toType(_componentType);
      return (_type + "[]");
    }
    return c.getName();
  }
}
