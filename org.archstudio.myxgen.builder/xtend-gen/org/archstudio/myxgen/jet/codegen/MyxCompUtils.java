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
    return fullClassName.substring(0, _lastIndexOf);
  }
  
  public static String toClassName(final String fullClassName) {
    int _lastIndexOf = fullClassName.lastIndexOf(".");
    int _plus = (_lastIndexOf + 1);
    return fullClassName.substring(_plus);
  }
  
  public static String toConstantName(final String fullVariableName) {
    final StringBuilder sb = new StringBuilder();
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
      final StringBuffer sb = new StringBuffer();
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
              _or = _isProtected;
            }
            _and = _or;
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
            sb.append(_builder);
            int _flags_2 = m.getFlags();
            String _string = Flags.toString(_flags_2);
            String _plus_2 = (_string + " ");
            String _stubClassName = brick.getStubClassName();
            String _className_1 = MyxCompUtils.toClassName(_stubClassName);
            String _plus_3 = (_plus_2 + _className_1);
            sb.append(_plus_3);
            final StringBuffer args = new StringBuffer();
            final StringBuffer superArgs = new StringBuffer();
            int i = 0;
            int _numberOfParameters = m.getNumberOfParameters();
            boolean _lessThan = (i < _numberOfParameters);
            boolean _while = _lessThan;
            while (_while) {
              {
                String[] _parameterNames = m.getParameterNames();
                final String name = _parameterNames[i];
                final String type = MyxCompUtils.toType(cuType, m, i);
                if ((i > 0)) {
                  args.append(", ");
                }
                args.append(((type + " ") + name));
                if ((i > 0)) {
                  superArgs.append(", ");
                }
                superArgs.append(name);
                i = (i + 1);
              }
              int _numberOfParameters_1 = m.getNumberOfParameters();
              boolean _lessThan_1 = (i < _numberOfParameters_1);
              _while = _lessThan_1;
            }
            sb.append((((("(" + args) + ") { super(") + superArgs) + "); }"));
          }
        }
        return sb.toString();
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          Bundle _bundle = Platform.getBundle(bundleProjectId);
          final Class<?> parent = _bundle.loadClass(fullClassName);
          Constructor<?>[] _declaredConstructors = parent.getDeclaredConstructors();
          for (final Constructor<?> c : _declaredConstructors) {
            boolean _or_1 = false;
            int _modifiers = c.getModifiers();
            boolean _isPublic_1 = Modifier.isPublic(_modifiers);
            if (_isPublic_1) {
              _or_1 = true;
            } else {
              int _modifiers_1 = c.getModifiers();
              boolean _isProtected_1 = Modifier.isProtected(_modifiers_1);
              _or_1 = _isProtected_1;
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
              sb.append(_builder_1);
              int _modifiers_2 = c.getModifiers();
              String _string_1 = Modifier.toString(_modifiers_2);
              String _plus_4 = (_string_1 + " ");
              String _stubClassName_1 = brick.getStubClassName();
              String _className_2 = MyxCompUtils.toClassName(_stubClassName_1);
              String _plus_5 = (_plus_4 + _className_2);
              sb.append(_plus_5);
              final StringBuffer args_1 = new StringBuffer();
              final StringBuffer superArgs_1 = new StringBuffer();
              int i_1 = 0;
              Class<?>[] _parameterTypes = c.getParameterTypes();
              int _size = ((List<Class<?>>)Conversions.doWrapArray(_parameterTypes)).size();
              boolean _lessThan_1 = (i_1 < _size);
              boolean _while_1 = _lessThan_1;
              while (_while_1) {
                {
                  final String name = ("arg" + Integer.valueOf(i_1));
                  final String type = MyxCompUtils.toType(c, i_1);
                  if ((i_1 > 0)) {
                    args_1.append(", ");
                  }
                  args_1.append(((type + " ") + name));
                  if ((i_1 > 0)) {
                    superArgs_1.append(", ");
                  }
                  superArgs_1.append(name);
                  i_1 = (i_1 + 1);
                }
                Class<?>[] _parameterTypes_1 = c.getParameterTypes();
                int _size_1 = ((List<Class<?>>)Conversions.doWrapArray(_parameterTypes_1)).size();
                boolean _lessThan_2 = (i_1 < _size_1);
                _while_1 = _lessThan_2;
              }
              sb.append((((("(" + args_1) + ") { super(") + superArgs_1) + "); }"));
            }
          }
          return sb.toString();
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static String toType(final IType t, final IMethod m, final int i) {
    try {
      String[] _parameterTypes = m.getParameterTypes();
      String _get = _parameterTypes[i];
      final String type = Signature.toString(_get);
      final String[][] resolved = t.resolveType(type);
      boolean _equals = Objects.equal(resolved, null);
      if (_equals) {
        return type;
      }
      String[] _get_1 = resolved[0];
      Object _get_2 = _get_1[0];
      boolean _equals_1 = "".equals(_get_2);
      if (_equals_1) {
        return type;
      }
      String[] _get_3 = resolved[0];
      String _get_4 = _get_3[0];
      String _plus = (_get_4 + ".");
      String[] _get_5 = resolved[0];
      String _get_6 = _get_5[1];
      final String c = (_plus + _get_6);
      return c;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static String toType(final Constructor<?> c, final int i) {
    Class<?>[] _parameterTypes = c.getParameterTypes();
    Class<?> _get = _parameterTypes[i];
    final String t = MyxCompUtils.toType(_get);
    return t;
  }
  
  private static String toType(final Class<?> c) {
    boolean _isArray = c.isArray();
    if (_isArray) {
      Class<?> _componentType = c.getComponentType();
      String _type = MyxCompUtils.toType(_componentType);
      return (_type + "[]");
    }
    return c.getName();
  }
}
