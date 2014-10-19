package org.archstudio.myxgen.jet.codegen;

import org.archstudio.myxgen.MyxGenBrick;
import org.archstudio.myxgen.jet.codegen.MyxCompUtils;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MyxCompBuilder extends MyxCompUtils {
  public static String generate(final MyxGenBrick b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package �toPackageName(b.className)�;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("/**");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* Myx brick: �b.name�");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("* �IF b.description != null�<p>�b.description��ENDIF�");
    _builder.newLine();
    _builder.append(" ");
    _builder.append("*/");
    _builder.newLine();
    _builder.append("public class �toClassName(b.className)�");
    _builder.newLine();
    _builder.append("extends �b.stubClassName�");
    _builder.newLine();
    _builder.append("{");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    return _builder.toString();
  }
}
