package org.archstudio.myxgen.jet.codegen;

import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.jet.codegen.MyxCompUtils;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class MyxCompBuilder extends MyxCompUtils {
  public static String generate(final MyxGenBrick b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package ");
    String _className = b.getClassName();
    String _packageName = MyxCompUtils.toPackageName(_className);
    _builder.append(_packageName, "");
    _builder.append(";");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Myx brick: ");
    String _name = b.getName();
    _builder.append(_name, " ");
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
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class ");
    String _className_1 = b.getClassName();
    String _className_2 = MyxCompUtils.toClassName(_className_1);
    _builder.append(_className_2, "");
    _builder.newLineIfNotEmpty();
    _builder.append("extends ");
    String _stubClassName = b.getStubClassName();
    _builder.append(_stubClassName, "");
    _builder.newLineIfNotEmpty();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
