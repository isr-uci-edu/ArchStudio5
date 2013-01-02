package org.archstudio.myxgen.jet.codegen

import org.archstudio.myxgen.MyxGenBrick

class MyxCompBuilder extends MyxCompUtils {

	def static String generate(MyxGenBrick b) {
		return '''
			package «toPackageName(b.className)»;
			
			/**
			 * Myx brick: «b.name»
			 * «IF b.description != null»<p>«b.description»«ENDIF»
			 */
			public class «toClassName(b.className)»
			extends «b.stubClassName»
			{
				
			}
		''';
	}
}
