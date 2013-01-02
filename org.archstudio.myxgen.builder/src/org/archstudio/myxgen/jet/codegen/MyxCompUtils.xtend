package org.archstudio.myxgen.jet.codegen

import java.lang.reflect.Constructor
import java.lang.reflect.Modifier
import java.net.MalformedURLException
import org.archstudio.myxgen.MyxGenBrick
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Platform
import org.eclipse.jdt.core.Flags
import org.eclipse.jdt.core.ICompilationUnit
import org.eclipse.jdt.core.IMethod
import org.eclipse.jdt.core.IType
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.core.Signature

class MyxCompUtils {

	def public static toPackageName(String fullClassName) {
		fullClassName.substring(0, fullClassName.lastIndexOf('.'))
	}

	def public static String toClassName(String fullClassName) {
		fullClassName.substring(fullClassName.lastIndexOf('.') + 1)
	}

	def public static String toConstantName(String fullVariableName) {
		val StringBuilder sb = new StringBuilder();
		for (char c : fullVariableName.toCharArray) {
			if (Character::isUpperCase(c)) {
				sb.append('_');
			}
			sb.append(Character::toUpperCase(c));
		}
		return sb.toString();
	}

	def public static String constructorsFor(MyxGenBrick brick, String bundleProjectId, String fullClassName) throws MalformedURLException {

		val StringBuffer sb = new StringBuffer();

		try {

			// check for a source file in the workspace (from a project)
			var IResource res = ResourcesPlugin::workspace.root.getProject(bundleProjectId).findMember(
				'src/' + fullClassName.replace('.', '/') + '.java');
			val ICompilationUnit cu = JavaCore::createCompilationUnitFrom(res as IFile);
			val IType cuType = cu.getType(toClassName(fullClassName));
			for (IMethod m : cuType.methods) {
				if (m.constructor && (Flags::isPublic(m.flags) || Flags::isProtected(m.flags))) {
					sb.append('''
					/**
					 * A constructor from the super class...
					 * @generated
					 */''')
					sb.append(Flags::toString(m.flags) + ' ' + toClassName(brick.stubClassName));
					val StringBuffer args = new StringBuffer();
					val StringBuffer superArgs = new StringBuffer();
					var i = 0;
					while (i < m.numberOfParameters) {
						val String name = m.parameterNames.get(i);
						val String type = toType(cuType, m, i);
						if (i > 0)
							args.append(", ");
						args.append(type + ' ' + name);
						if (i > 0)
							superArgs.append(", ");
						superArgs.append(name);
						i = i + 1;
					}
					sb.append('(' + args + ') { super(' + superArgs + '); }');

				}
			}

			return sb.toString;
		} catch (Exception e) {
			e.printStackTrace;

			// check for constructors in the class file (from a bundle)
			val Class parent = Platform::getBundle(bundleProjectId).loadClass(fullClassName);
			for (Constructor c : parent.declaredConstructors) {
				if (Modifier::isPublic(c.modifiers) || Modifier::isProtected(c.modifiers)) {
					sb.append('''
					/**
					 * A constructor from the super class...
					 * @generated
					 */''')
					sb.append(Modifier::toString(c.modifiers) + ' ' + toClassName(brick.stubClassName));
					val StringBuffer args = new StringBuffer();
					val StringBuffer superArgs = new StringBuffer();
					var i = 0;
					while (i < c.parameterTypes.size) {
						val String name = "arg" + i;
						val String type = toType(c, i);
						if (i > 0)
							args.append(", ");
						args.append(type + ' ' + name);
						if (i > 0)
							superArgs.append(", ");
						superArgs.append(name);
						i = i + 1;
					}
					sb.append('(' + args + ') { super(' + superArgs + '); }');
				}
			}

			return sb.toString;
		}
	}

	def private static String toType(IType t, IMethod m, int i) {
		val String type = Signature::toString(m.parameterTypes.get(i));
		val String[][] resolved = t.resolveType(type);
		if (resolved == null)
			return type;
		if ("".equals(resolved.get(0).get(0)))
			return type;
		val String c = resolved.get(0).get(0) + '.' + resolved.get(0).get(1);
		return c;
	}

	def private static String toType(Constructor c, int i) {
		val String t = toType(c.parameterTypes.get(i));
		return t;
	}

	def private static String toType(Class c) {
		if (c.array) {
			return toType(c.componentType) + '[]';
		}
		return c.name;
	}
}
