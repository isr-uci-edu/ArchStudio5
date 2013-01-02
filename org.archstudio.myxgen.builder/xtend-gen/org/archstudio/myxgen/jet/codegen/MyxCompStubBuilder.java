package org.archstudio.myxgen.jet.codegen;

import java.util.Collection;
import org.archstudio.myx.fw.EMyxInterfaceDirection;
import org.archstudio.myxgen.EServiceObjectDelegate;
import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.MyxGenInterface;
import org.archstudio.myxgen.eclipse.extension.MyxGenWorkspaceExtensions;
import org.archstudio.myxgen.jet.codegen.MyxCompUtils;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class MyxCompStubBuilder extends MyxCompUtils {
  public static String generate(final MyxGenBrick b) {
    MyxGenBrick _xtrycatchfinallyexpression = null;
    try {
      String _parentBrickId = b.getParentBrickId();
      MyxGenBrick _activeMyxGenBrick = MyxGenWorkspaceExtensions.getActiveMyxGenBrick(_parentBrickId);
      _xtrycatchfinallyexpression = _activeMyxGenBrick;
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        _xtrycatchfinallyexpression = null;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    final MyxGenBrick pb = _xtrycatchfinallyexpression;
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package ");
      String _stubClassName = b.getStubClassName();
      String _packageName = MyxCompUtils.toPackageName(_stubClassName);
      _builder.append(_packageName, "");
      _builder.append(";");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      {
        boolean _equals = ObjectExtensions.operator_equals(pb, null);
        if (_equals) {
          _builder.append("import org.archstudio.myx.fw.MyxRegistry;");
          _builder.newLine();
        }
      }
      {
        Collection<MyxGenInterface> _interfaces = b.getInterfaces();
        int _size = _interfaces.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          _builder.append("import org.archstudio.myx.fw.MyxUtils;");
          _builder.newLine();
        }
      }
      _builder.append("import org.archstudio.myx.fw.IMyxName;");
      _builder.newLine();
      _builder.newLine();
      _builder.append("/*");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* DO NOT EDIT THIS CLASS, it is automatically generated.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* ANY MODIFICATIONS WILL BE OVERWRITTEN.");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* To modify, update the \"");
      String _name = b.getName();
      _builder.append(_name, " ");
      _builder.append("\" MyxGen ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("* extension in the ");
      IContributor _contributor = b.getContributor();
      String _name_1 = _contributor.getName();
      _builder.append(_name_1, " ");
      _builder.append(" plugin.");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.newLine();
      _builder.append("/**");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("* Abstract Myx brick: ");
      String _name_2 = b.getName();
      _builder.append(_name_2, " ");
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("* ");
      {
        String _description = b.getDescription();
        boolean _notEquals = ObjectExtensions.operator_notEquals(_description, null);
        if (_notEquals) {
          _builder.append("<p>");
          String _description_1 = b.getDescription();
          _builder.append(_description_1, " ");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append(" ");
      _builder.append("* @generated");
      _builder.newLine();
      _builder.append(" ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("public abstract class ");
      String _stubClassName_1 = b.getStubClassName();
      String _className = MyxCompUtils.toClassName(_stubClassName_1);
      _builder.append(_className, "");
      _builder.newLineIfNotEmpty();
      {
        boolean _equals_1 = ObjectExtensions.operator_equals(pb, null);
        if (_equals_1) {
          _builder.append("extends org.archstudio.myx.fw.AbstractMyxSimpleBrick");
          _builder.newLine();
        } else {
          _builder.append("extends ");
          String _className_1 = pb.getClassName();
          _builder.append(_className_1, "");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("implements org.archstudio.myx.fw.IMyxDynamicBrick");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_1 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EServiceObjectDelegate _serviceObjectDelegate = i.getServiceObjectDelegate();
              boolean _equals = ObjectExtensions.operator_equals(_serviceObjectDelegate, EServiceObjectDelegate.brick);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter = IterableExtensions.<MyxGenInterface>filter(_interfaces_1, _function);
        boolean _hasElements = false;
        for(final MyxGenInterface i : _filter) {
          if (!_hasElements) {
            _hasElements = true;
            _builder.append(", ", "");
          } else {
            _builder.appendImmediate(", ", "");
          }
          String _className_2 = i.getClassName();
          _builder.append(_className_2, "");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      {
        boolean _notEquals_1 = ObjectExtensions.operator_notEquals(pb, null);
        if (_notEquals_1) {
          _builder.append("\t");
          IContributor _contributor_1 = pb.getContributor();
          String _name_3 = _contributor_1.getName();
          String _className_3 = pb.getClassName();
          String _constructorsFor = MyxCompUtils.constructorsFor(b, _name_3, _className_3);
          _builder.append(_constructorsFor, "	");
          _builder.newLineIfNotEmpty();
        } else {
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* The registry of objects for this brick.");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* @generated");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();");
          _builder.newLine();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* Register this brick instance with the registry.");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* @generated");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public void begin(){");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("super.begin();");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("myxRegistry.register(this);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
          _builder.append("\t");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* Unregister this brick instance with the registry.");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* @generated");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public void end(){");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("myxRegistry.unregister(this);");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("\t");
          _builder.append("super.end();");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_2 = b.getInterfaces();
        for(final MyxGenInterface i_1 : _interfaces_2) {
          _builder.append("\t");
          _builder.append("/**");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* Myx name for the <code>");
          String _name_4 = i_1.getName();
          _builder.append(_name_4, "	 ");
          _builder.append("</code> interface.");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* ");
          {
            String _description_2 = i_1.getDescription();
            boolean _notEquals_2 = ObjectExtensions.operator_notEquals(_description_2, null);
            if (_notEquals_2) {
              _builder.append("<p>");
              String _description_3 = i_1.getDescription();
              _builder.append(_description_3, "	 ");
            }
          }
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("* @generated");
          _builder.newLine();
          _builder.append("\t");
          _builder.append(" ");
          _builder.append("*/");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("public static final IMyxName ");
          EMyxInterfaceDirection _direction = i_1.getDirection();
          String _name_5 = _direction.name();
          String _lowerCase = _name_5.toLowerCase();
          String _name_6 = i_1.getName();
          String _firstUpper = StringExtensions.toFirstUpper(_name_6);
          String _plus = (_lowerCase + _firstUpper);
          String _constantName = MyxCompUtils.toConstantName(_plus);
          _builder.append(_constantName, "	");
          _builder.append(" = MyxUtils.createName(\"");
          String _id = i_1.getId();
          _builder.append(_id, "	");
          _builder.append("\");");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          _builder.newLine();
          {
            EServiceObjectDelegate _serviceObjectDelegate = i_1.getServiceObjectDelegate();
            boolean _isNeedsVariable = _serviceObjectDelegate.isNeedsVariable();
            if (_isNeedsVariable) {
              _builder.append("\t");
              _builder.append("/**");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("* Service object");
              {
                boolean _isSingle = i_1.isSingle();
                boolean _not = (!_isSingle);
                if (_not) {
                  _builder.append("s");
                }
              }
              _builder.append(" for the ");
              String _name_7 = i_1.getName();
              _builder.append(_name_7, "	 ");
              _builder.append(" interface.");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("* @see #");
              EMyxInterfaceDirection _direction_1 = i_1.getDirection();
              String _name_8 = _direction_1.name();
              String _lowerCase_1 = _name_8.toLowerCase();
              String _name_9 = i_1.getName();
              String _firstUpper_1 = StringExtensions.toFirstUpper(_name_9);
              String _plus_1 = (_lowerCase_1 + _firstUpper_1);
              String _constantName_1 = MyxCompUtils.toConstantName(_plus_1);
              _builder.append(_constantName_1, "	 ");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("* @generated");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("*/");
              _builder.newLine();
              {
                boolean _isSingle_1 = i_1.isSingle();
                if (_isSingle_1) {
                  _builder.append("\t");
                  _builder.append("protected ");
                  String _className_4 = i_1.getClassName();
                  _builder.append(_className_4, "	");
                  _builder.append(" ");
                  String _name_10 = i_1.getName();
                  _builder.append(_name_10, "	");
                  _builder.append(" = null;");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  _builder.append("protected final java.util.Collection<");
                  String _className_5 = i_1.getClassName();
                  _builder.append(_className_5, "	");
                  _builder.append("> ");
                  String _name_11 = i_1.getName();
                  _builder.append(_name_11, "	");
                  _builder.append(" = new java.util.concurrent.CopyOnWriteArrayList<");
                  String _className_6 = i_1.getClassName();
                  _builder.append(_className_6, "	");
                  _builder.append(">();");
                  _builder.newLineIfNotEmpty();
                }
              }
            }
          }
          _builder.append("\t");
          _builder.newLine();
          {
            EServiceObjectDelegate _serviceObjectDelegate_1 = i_1.getServiceObjectDelegate();
            boolean _isNeedsProxy = _serviceObjectDelegate_1.isNeedsProxy();
            if (_isNeedsProxy) {
              _builder.append("\t");
              _builder.append("/**");
              _builder.newLine();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("* Service object proxy for the ");
              String _name_12 = i_1.getName();
              _builder.append(_name_12, "	 ");
              _builder.append(" interface.");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append(" ");
              _builder.append("* Calls to this proxy object are automatically delegated to ");
              {
                EServiceObjectDelegate _serviceObjectDelegate_2 = i_1.getServiceObjectDelegate();
                boolean _equals_2 = ObjectExtensions.operator_equals(_serviceObjectDelegate_2, EServiceObjectDelegate.myxRegistry);
                if (_equals_2) {
                  _builder.append("all service objects in the MyxRegistry of type ");
                  String _className_7 = i_1.getClassName();
                  _builder.append(_className_7, "	 ");
                  _builder.append(".");
                } else {
                  _builder.append("all connections on the interface");
                }
              }
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("* @see #");
              EMyxInterfaceDirection _direction_2 = i_1.getDirection();
              String _name_13 = _direction_2.name();
              String _lowerCase_2 = _name_13.toLowerCase();
              String _name_14 = i_1.getName();
              String _firstUpper_2 = StringExtensions.toFirstUpper(_name_14);
              String _plus_2 = (_lowerCase_2 + _firstUpper_2);
              String _constantName_2 = MyxCompUtils.toConstantName(_plus_2);
              _builder.append(_constantName_2, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("* @generated");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("*/");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("protected final ");
              String _className_8 = i_1.getClassName();
              _builder.append(_className_8, "	");
              _builder.append(" ");
              String _name_15 = i_1.getName();
              _builder.append(_name_15, "	");
              _builder.append("Proxy =");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("(");
              String _className_9 = i_1.getClassName();
              _builder.append(_className_9, "		");
              _builder.append(") java.lang.reflect.Proxy.newProxyInstance(");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              String _className_10 = i_1.getClassName();
              _builder.append(_className_10, "			");
              _builder.append(".class.getClassLoader(), ");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("new Class[] { ");
              String _className_11 = i_1.getClassName();
              _builder.append(_className_11, "			");
              _builder.append(".class },");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("new java.lang.reflect.InvocationHandler() {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t");
              _builder.append("@Override");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t");
              _builder.append("public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {");
              _builder.newLine();
              {
                EServiceObjectDelegate _serviceObjectDelegate_3 = i_1.getServiceObjectDelegate();
                boolean _equals_3 = ObjectExtensions.operator_equals(_serviceObjectDelegate_3, EServiceObjectDelegate.myxRegistry);
                if (_equals_3) {
                  _builder.append("\t");
                  _builder.append("\t\t\t\t");
                  _builder.append("for (");
                  String _className_12 = i_1.getClassName();
                  _builder.append(_className_12, "					");
                  _builder.append(" o : myxRegistry.getObjects(");
                  String _stubClassName_2 = b.getStubClassName();
                  String _className_13 = MyxCompUtils.toClassName(_stubClassName_2);
                  _builder.append(_className_13, "					");
                  _builder.append(".this, ");
                  String _className_14 = i_1.getClassName();
                  _builder.append(_className_14, "					");
                  _builder.append(".class)) {");
                  _builder.newLineIfNotEmpty();
                } else {
                  boolean _isSingle_2 = i_1.isSingle();
                  boolean _not_1 = (!_isSingle_2);
                  if (_not_1) {
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    _builder.append("for (");
                    String _className_15 = i_1.getClassName();
                    _builder.append(_className_15, "					");
                    _builder.append(" o : ");
                    String _name_16 = i_1.getName();
                    _builder.append(_name_16, "					");
                    _builder.append(") {");
                    _builder.newLineIfNotEmpty();
                  } else {
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    String _className_16 = i_1.getClassName();
                    _builder.append(_className_16, "					");
                    _builder.append(" o = ");
                    String _name_17 = i_1.getName();
                    _builder.append(_name_17, "					");
                    _builder.append(";");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    _builder.append("if (o == null) {");
                    _builder.newLine();
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    _builder.append("\t");
                    _builder.append("throw new NullPointerException(\"");
                    String _name_18 = i_1.getName();
                    _builder.append(_name_18, "						");
                    _builder.append("\");");
                    _builder.newLineIfNotEmpty();
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    _builder.append("}");
                    _builder.newLine();
                    _builder.append("\t");
                    _builder.append("\t\t\t\t");
                    _builder.append("else {");
                    _builder.newLine();
                  }
                }
              }
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("try {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t\t");
              _builder.append("method.invoke(o, args);");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("catch (Exception e) {");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t\t");
              _builder.append("e.printStackTrace();");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t");
              _builder.append("return null;");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t\t\t");
              _builder.append("}");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("});");
              _builder.newLine();
            }
          }
          _builder.newLine();
          {
            boolean _isGenerateGetter = i_1.isGenerateGetter();
            if (_isGenerateGetter) {
              _builder.append("\t");
              _builder.append("/**");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("* Returns the service object(s) for the ");
              String _name_19 = i_1.getName();
              _builder.append(_name_19, "	");
              _builder.append(" interface.");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("* @see #");
              EMyxInterfaceDirection _direction_3 = i_1.getDirection();
              String _name_20 = _direction_3.name();
              String _lowerCase_3 = _name_20.toLowerCase();
              String _name_21 = i_1.getName();
              String _firstUpper_3 = StringExtensions.toFirstUpper(_name_21);
              String _plus_3 = (_lowerCase_3 + _firstUpper_3);
              String _constantName_3 = MyxCompUtils.toConstantName(_plus_3);
              _builder.append(_constantName_3, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("* @generated");
              _builder.newLine();
              _builder.append("\t");
              _builder.append("*/");
              _builder.newLine();
              {
                EServiceObjectDelegate _serviceObjectDelegate_4 = i_1.getServiceObjectDelegate();
                boolean _isNeedsProxy_1 = _serviceObjectDelegate_4.isNeedsProxy();
                if (_isNeedsProxy_1) {
                  _builder.append("\t");
                  _builder.append("public ");
                  String _className_17 = i_1.getClassName();
                  _builder.append(_className_17, "	");
                  _builder.append(" get");
                  String _name_22 = i_1.getName();
                  String _firstUpper_4 = StringExtensions.toFirstUpper(_name_22);
                  _builder.append(_firstUpper_4, "	");
                  _builder.append("() {");
                  _builder.newLineIfNotEmpty();
                } else {
                  _builder.append("\t");
                  _builder.append("public ");
                  {
                    boolean _isSingle_3 = i_1.isSingle();
                    if (_isSingle_3) {
                      String _className_18 = i_1.getClassName();
                      _builder.append(_className_18, "	");
                    } else {
                      _builder.append("java.util.Collection<");
                      String _className_19 = i_1.getClassName();
                      _builder.append(_className_19, "	");
                      _builder.append(">");
                    }
                  }
                  _builder.append(" get");
                  String _name_23 = i_1.getName();
                  String _firstUpper_5 = StringExtensions.toFirstUpper(_name_23);
                  _builder.append(_firstUpper_5, "	");
                  _builder.append("() {");
                  _builder.newLineIfNotEmpty();
                }
              }
              {
                EServiceObjectDelegate _serviceObjectDelegate_5 = i_1.getServiceObjectDelegate();
                boolean _equals_4 = ObjectExtensions.operator_equals(_serviceObjectDelegate_5, EServiceObjectDelegate.brick);
                if (_equals_4) {
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("return this;");
                  _builder.newLine();
                } else {
                  {
                    boolean _and = false;
                    EServiceObjectDelegate _serviceObjectDelegate_6 = i_1.getServiceObjectDelegate();
                    boolean _isNeedsProxy_2 = _serviceObjectDelegate_6.isNeedsProxy();
                    boolean _not_2 = (!_isNeedsProxy_2);
                    if (!_not_2) {
                      _and = false;
                    } else {
                      boolean _isSingle_4 = i_1.isSingle();
                      _and = (_not_2 && _isSingle_4);
                    }
                    if (_and) {
                      _builder.append("\t");
                      _builder.append("\t");
                      _builder.append("if (");
                      String _name_24 = i_1.getName();
                      _builder.append(_name_24, "		");
                      _builder.append(" == null) {");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("\t");
                      _builder.append("\t");
                      _builder.append("throw new NullPointerException(\"Uninitialized service object: ");
                      String _name_25 = i_1.getName();
                      _builder.append(_name_25, "			");
                      _builder.append("\");");
                      _builder.newLineIfNotEmpty();
                      _builder.append("\t");
                      _builder.append("\t");
                      _builder.append("}");
                      _builder.newLine();
                    }
                  }
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("return ");
                  String _name_26 = i_1.getName();
                  _builder.append(_name_26, "		");
                  {
                    EServiceObjectDelegate _serviceObjectDelegate_7 = i_1.getServiceObjectDelegate();
                    boolean _isNeedsProxy_3 = _serviceObjectDelegate_7.isNeedsProxy();
                    if (_isNeedsProxy_3) {
                      _builder.append("Proxy");
                    }
                  }
                  _builder.append(";");
                  _builder.newLineIfNotEmpty();
                }
              }
              _builder.append("\t");
              _builder.append("}");
              _builder.newLine();
            }
          }
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("* Returns service object(s) for IN interfaces.");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_3 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_1 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.IN);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_1 = IterableExtensions.<MyxGenInterface>filter(_interfaces_3, _function_1);
        for(final MyxGenInterface i_2 : _filter_1) {
          _builder.append("\t");
          _builder.append("* @see #");
          EMyxInterfaceDirection _direction_4 = i_2.getDirection();
          String _name_27 = _direction_4.name();
          String _lowerCase_4 = _name_27.toLowerCase();
          String _name_28 = i_2.getName();
          String _firstUpper_6 = StringExtensions.toFirstUpper(_name_28);
          String _plus_4 = (_lowerCase_4 + _firstUpper_6);
          String _constantName_4 = MyxCompUtils.toConstantName(_plus_4);
          _builder.append(_constantName_4, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("* @generated");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public Object getServiceObject(IMyxName interfaceName) {");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_4 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_2 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.IN);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_2 = IterableExtensions.<MyxGenInterface>filter(_interfaces_4, _function_2);
        for(final MyxGenInterface i_3 : _filter_2) {
          _builder.append("\t");
          _builder.append("if(interfaceName.equals(");
          EMyxInterfaceDirection _direction_5 = i_3.getDirection();
          String _name_29 = _direction_5.name();
          String _lowerCase_5 = _name_29.toLowerCase();
          String _name_30 = i_3.getName();
          String _firstUpper_7 = StringExtensions.toFirstUpper(_name_30);
          String _plus_5 = (_lowerCase_5 + _firstUpper_7);
          String _constantName_5 = MyxCompUtils.toConstantName(_plus_5);
          _builder.append(_constantName_5, "	");
          _builder.append(")) {");
          _builder.newLineIfNotEmpty();
          {
            EServiceObjectDelegate _serviceObjectDelegate_8 = i_3.getServiceObjectDelegate();
            boolean _equals_5 = ObjectExtensions.operator_equals(_serviceObjectDelegate_8, EServiceObjectDelegate.brick);
            if (_equals_5) {
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return this;");
              _builder.newLine();
            } else {
              {
                boolean _and_1 = false;
                EServiceObjectDelegate _serviceObjectDelegate_9 = i_3.getServiceObjectDelegate();
                boolean _isNeedsProxy_4 = _serviceObjectDelegate_9.isNeedsProxy();
                boolean _not_3 = (!_isNeedsProxy_4);
                if (!_not_3) {
                  _and_1 = false;
                } else {
                  boolean _isSingle_5 = i_3.isSingle();
                  _and_1 = (_not_3 && _isSingle_5);
                }
                if (_and_1) {
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("if (");
                  String _name_31 = i_3.getName();
                  _builder.append(_name_31, "		");
                  _builder.append(" == null) {");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("throw new NullPointerException(\"Uninitialized service object: ");
                  String _name_32 = i_3.getName();
                  _builder.append(_name_32, "			");
                  _builder.append("\");");
                  _builder.newLineIfNotEmpty();
                  _builder.append("\t");
                  _builder.append("\t");
                  _builder.append("}");
                  _builder.newLine();
                }
              }
              _builder.append("\t");
              _builder.append("\t");
              _builder.append("return ");
              String _name_33 = i_3.getName();
              _builder.append(_name_33, "		");
              {
                EServiceObjectDelegate _serviceObjectDelegate_10 = i_3.getServiceObjectDelegate();
                boolean _isNeedsProxy_5 = _serviceObjectDelegate_10.isNeedsProxy();
                if (_isNeedsProxy_5) {
                  _builder.append("Proxy");
                }
              }
              _builder.append(";");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      {
        String _parentBrickId_1 = b.getParentBrickId();
        boolean _equals_6 = ObjectExtensions.operator_equals(_parentBrickId_1, null);
        if (_equals_6) {
          _builder.append("\t");
          _builder.append("throw new IllegalArgumentException(\"Unhandled interface: \"+interfaceName.getName());");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("return super.getServiceObject(interfaceName);");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("* Update service objects based on connected OUT interfaces.");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_5 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_3 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.OUT);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_3 = IterableExtensions.<MyxGenInterface>filter(_interfaces_5, _function_3);
        for(final MyxGenInterface i_4 : _filter_3) {
          _builder.append("\t");
          _builder.append("* @see #");
          EMyxInterfaceDirection _direction_6 = i_4.getDirection();
          String _name_34 = _direction_6.name();
          String _lowerCase_6 = _name_34.toLowerCase();
          String _name_35 = i_4.getName();
          String _firstUpper_8 = StringExtensions.toFirstUpper(_name_35);
          String _plus_6 = (_lowerCase_6 + _firstUpper_8);
          String _constantName_6 = MyxCompUtils.toConstantName(_plus_6);
          _builder.append(_constantName_6, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("* @generated");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (serviceObject == null)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("throw new NullPointerException(interfaceName.getName());");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_6 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_4 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.OUT);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_4 = IterableExtensions.<MyxGenInterface>filter(_interfaces_6, _function_4);
        for(final MyxGenInterface i_5 : _filter_4) {
          _builder.newLine();
          _builder.append("\t");
          _builder.append("if(interfaceName.equals(");
          EMyxInterfaceDirection _direction_7 = i_5.getDirection();
          String _name_36 = _direction_7.name();
          String _lowerCase_7 = _name_36.toLowerCase();
          String _name_37 = i_5.getName();
          String _firstUpper_9 = StringExtensions.toFirstUpper(_name_37);
          String _plus_7 = (_lowerCase_7 + _firstUpper_9);
          String _constantName_7 = MyxCompUtils.toConstantName(_plus_7);
          _builder.append(_constantName_7, "	");
          _builder.append(")) {");
          _builder.newLineIfNotEmpty();
          {
            boolean _isSingle_6 = i_5.isSingle();
            if (_isSingle_6) {
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("if (");
              String _name_38 = i_5.getName();
              _builder.append(_name_38, "			");
              _builder.append(" != null)");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("\t");
              _builder.append("throw new IllegalStateException(\"Only a single connection is supported on interface: ");
              String _name_39 = i_5.getName();
              _builder.append(_name_39, "				");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              String _name_40 = i_5.getName();
              _builder.append(_name_40, "			");
              _builder.append(" = (");
              String _className_20 = i_5.getClassName();
              _builder.append(_className_20, "			");
              _builder.append(") serviceObject;");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("\t\t");
              String _name_41 = i_5.getName();
              _builder.append(_name_41, "			");
              _builder.append(".add((");
              String _className_21 = i_5.getClassName();
              _builder.append(_className_21, "			");
              _builder.append(") serviceObject);");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("return;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      {
        String _parentBrickId_2 = b.getParentBrickId();
        boolean _equals_7 = ObjectExtensions.operator_equals(_parentBrickId_2, null);
        if (_equals_7) {
          _builder.append("\t");
          _builder.append("throw new IllegalArgumentException(\"Unhandled interface: \"+interfaceName.getName());");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("super.interfaceConnected(interfaceName, serviceObject);");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("* Update service objects based on disconnecting OUT interfaces.");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_7 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_5 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.OUT);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_5 = IterableExtensions.<MyxGenInterface>filter(_interfaces_7, _function_5);
        for(final MyxGenInterface i_6 : _filter_5) {
          _builder.append("\t");
          _builder.append("* @see #");
          EMyxInterfaceDirection _direction_8 = i_6.getDirection();
          String _name_42 = _direction_8.name();
          String _lowerCase_8 = _name_42.toLowerCase();
          String _name_43 = i_6.getName();
          String _firstUpper_10 = StringExtensions.toFirstUpper(_name_43);
          String _plus_8 = (_lowerCase_8 + _firstUpper_10);
          String _constantName_8 = MyxCompUtils.toConstantName(_plus_8);
          _builder.append(_constantName_8, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("\t");
      _builder.append("* @generated");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (serviceObject == null)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("throw new NullPointerException(interfaceName.getName());");
      _builder.newLine();
      {
        Collection<MyxGenInterface> _interfaces_8 = b.getInterfaces();
        final Function1<MyxGenInterface,Boolean> _function_6 = new Function1<MyxGenInterface,Boolean>() {
            public Boolean apply(final MyxGenInterface i) {
              EMyxInterfaceDirection _direction = i.getDirection();
              boolean _equals = ObjectExtensions.operator_equals(_direction, EMyxInterfaceDirection.OUT);
              return Boolean.valueOf(_equals);
            }
          };
        Iterable<MyxGenInterface> _filter_6 = IterableExtensions.<MyxGenInterface>filter(_interfaces_8, _function_6);
        for(final MyxGenInterface i_7 : _filter_6) {
          _builder.newLine();
          _builder.append("\t");
          _builder.append("if(interfaceName.equals(");
          EMyxInterfaceDirection _direction_9 = i_7.getDirection();
          String _name_44 = _direction_9.name();
          String _lowerCase_9 = _name_44.toLowerCase();
          String _name_45 = i_7.getName();
          String _firstUpper_11 = StringExtensions.toFirstUpper(_name_45);
          String _plus_9 = (_lowerCase_9 + _firstUpper_11);
          String _constantName_9 = MyxCompUtils.toConstantName(_plus_9);
          _builder.append(_constantName_9, "	");
          _builder.append(")) {");
          _builder.newLineIfNotEmpty();
          {
            boolean _isSingle_7 = i_7.isSingle();
            if (_isSingle_7) {
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("if (");
              String _name_46 = i_7.getName();
              _builder.append(_name_46, "			");
              _builder.append(" == null)");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              _builder.append("\t");
              _builder.append("throw new IllegalStateException(\"A connection was never made on interface: ");
              String _name_47 = i_7.getName();
              _builder.append(_name_47, "				");
              _builder.append("\");");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.append("\t\t");
              String _name_48 = i_7.getName();
              _builder.append(_name_48, "			");
              _builder.append(" = null;");
              _builder.newLineIfNotEmpty();
            } else {
              _builder.append("\t");
              _builder.append("\t\t");
              String _name_49 = i_7.getName();
              _builder.append(_name_49, "			");
              _builder.append(".remove(serviceObject);");
              _builder.newLineIfNotEmpty();
            }
          }
          _builder.append("\t");
          _builder.append("\t\t");
          _builder.append("return;");
          _builder.newLine();
          _builder.append("\t");
          _builder.append("}");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.newLine();
      {
        String _parentBrickId_3 = b.getParentBrickId();
        boolean _equals_8 = ObjectExtensions.operator_equals(_parentBrickId_3, null);
        if (_equals_8) {
          _builder.append("\t");
          _builder.append("throw new IllegalArgumentException(\"Unhandled interface: \"+interfaceName.getName());");
          _builder.newLine();
        } else {
          _builder.append("\t");
          _builder.append("super.interfaceDisconnecting(interfaceName, serviceObject);");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("\t");
      _builder.append("/**");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("* Performs no operation upon the completion of an interface disconnecting.");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("* @generated");
      _builder.newLine();
      _builder.append("\t ");
      _builder.append("*/");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {");
      _builder.newLine();
      {
        String _parentBrickId_4 = b.getParentBrickId();
        boolean _notEquals_3 = ObjectExtensions.operator_notEquals(_parentBrickId_4, null);
        if (_notEquals_3) {
          _builder.append("\t");
          _builder.append("super.interfaceDisconnected(interfaceName, serviceObject);");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      return _builder.toString();
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
