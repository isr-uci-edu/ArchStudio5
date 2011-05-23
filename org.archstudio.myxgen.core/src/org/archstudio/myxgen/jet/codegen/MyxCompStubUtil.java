package org.archstudio.myxgen.jet.codegen;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.HashSet;

import org.archstudio.myx.fw.AbstractMyxSimpleBrick;
import org.archstudio.myx.fw.IMyxBrickItems;
import org.archstudio.myx.fw.IMyxDynamicBrick;
import org.archstudio.myx.fw.IMyxLifecycleProcessor;
import org.archstudio.myx.fw.IMyxProvidedServiceProvider;
import org.archstudio.myxgen.extension.BrickExtension;
import org.archstudio.myxgen.extension.EServiceObjectDelegate;
import org.archstudio.myxgen.extension.InterfaceExtension;
import org.archstudio.sysutils.SystemUtils;

public class MyxCompStubUtil {

	public static String getImplementsClause(BrickExtension brick) {
		Collection<String> classes = new HashSet<String>();

		classes.add(IMyxDynamicBrick.class.getName());
		classes.add(IMyxProvidedServiceProvider.class.getName());
		classes.add(IMyxLifecycleProcessor.class.getName());
		for (InterfaceExtension iface : brick.getInterfaces()) {
			if (iface.getServiceObjectDelegate() == EServiceObjectDelegate.brick) {
				classes.add(iface.getClassName());
			}
		}

		return SystemUtils.join("implements ", ",", "", classes);
	}

	public static Iterable<String> getImports(BrickExtension brick) {
		assert brick != null;

		Collection<String> classes = new HashSet<String>();

		classes.add(IMyxDynamicBrick.class.getName());
		classes.add(IMyxProvidedServiceProvider.class.getName());
		classes.add(IMyxLifecycleProcessor.class.getName());
		classes.add(IMyxBrickItems.class.getName());
		for (InterfaceExtension iface : brick.getInterfaces()) {
			classes.add(iface.getClassName());
		}

		return classes;

	}

	public static String getExtendsClause(BrickExtension brick) {
		checkNotNull(brick);

		if (brick.getParentBrick() != null) {
			return " extends " + brick.getParentBrick().getClassName();
		}
		return " extends " + AbstractMyxSimpleBrick.class.getName();
	}

	public static String getServiceObjectName(InterfaceExtension iface) {
		return iface.getName();
	}

	public static String getConstantName(InterfaceExtension iface) {
		StringBuilder sb = new StringBuilder(iface.getDirection().name() + "_");
		for (char c : iface.getName().toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append('_');
			}
			sb.append(Character.toUpperCase(c));
		}
		return sb.toString();
	}

	public static String getListener(InterfaceExtension iface) {

		// TODO: implement Nobu's generated handlers

		return "(" + iface.getClassName() + ") Proxy.newProxyInstance("//
				+ iface.getClassName() + ".class.getClassLoader(),"//
				+ "new Class[]{ " + iface.getClassName() + ".class },"//
				+ "new InvocationHandler(){"//
				+ "@Override"//
				+ "public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {"//
				+ "	for (" + iface.getClassName() + " l : " + getServiceObjectName(iface) + ") {"//
				+ "		try {"//
				+ "			method.invoke(l, args);"//
				+ "		}"//
				+ "		catch (Exception e) {"//
				+ "			e.printStackTrace();"//
				+ "		}"//
				+ "	}"//
				+ "	return null;"//
				+ "});";
	}
}
