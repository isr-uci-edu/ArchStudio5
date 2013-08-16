package org.archstudio.myxgen.jet.codegen

import org.archstudio.myx.fw.EMyxInterfaceDirection
import org.archstudio.myxgen.EServiceObjectDelegate
import org.archstudio.myxgen.MyxGenBrick
import org.archstudio.myxgen.eclipse.^extension.MyxGenWorkspaceExtensions

class MyxCompStubBuilder extends MyxCompUtils {

	def static String generate(MyxGenBrick b) {
		val MyxGenBrick pb = try{
			MyxGenWorkspaceExtensions::getActiveMyxGenBrick(b.parentBrickId);
		}catch(Exception e){
			null;
		}
		return '''
		package «toPackageName(b.stubClassName)»;
		
		«IF pb == null»
			import org.archstudio.myx.fw.MyxRegistry;
		«ENDIF»
		«IF b.interfaces.size > 0»
			import org.archstudio.myx.fw.MyxUtils;
		«ENDIF»
		import org.archstudio.myx.fw.IMyxName;

		/*
		 * DO NOT EDIT THIS CLASS, it is automatically generated.
		 * ANY MODIFICATIONS WILL BE OVERWRITTEN.
		 *
		 * To modify, update the "«b.name»" MyxGen 
		 * extension in the «b.contributor.name» plugin.
		 */
		
		/**
		 * Abstract Myx brick: «b.name»
		 * «IF b.description != null»<p>«b.description»«ENDIF»
		 * @generated
		 */
		public abstract class «toClassName(b.stubClassName)»
		«IF pb == null»
		extends org.archstudio.myx.fw.AbstractMyxSimpleBrick
		«ELSE»
		extends «pb.className»
		«ENDIF»
		implements org.archstudio.myx.fw.IMyxDynamicBrick
		«FOR i : b.interfaces.filter[i|i.serviceObjectDelegate == EServiceObjectDelegate::brick] BEFORE ', ' SEPARATOR ', '»«i.className»«ENDFOR»
		{
			
			«IF pb != null»
				«constructorsFor(b, pb.contributor.name, pb.className)»
			«ELSE»
				/**
				 * The registry of objects for this brick.
				 * @generated
				 */
				protected final MyxRegistry myxRegistry = MyxRegistry.getSharedInstance();
				
				/**
				 * Register this brick instance with the registry.
				 * @generated
				 */
				@Override
				public void begin(){
					super.begin();
					myxRegistry.register(this);
				}
				
				/**
				 * Unregister this brick instance with the registry.
				 * @generated
				 */
				@Override
				public void end(){
					myxRegistry.unregister(this);
					super.end();
				}
			«ENDIF»
			
			«FOR i : b.interfaces»
				/**
				 * Myx name for the <code>«i.name»</code> interface.
				 * «IF i.description != null»<p>«i.description»«ENDIF»
				 * @generated
				 */
				public static final IMyxName «toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)» = MyxUtils.createName("«i.id»");
				
				«IF i.serviceObjectDelegate.needsVariable»
					/**
					 * Service object«IF !i.single»s«ENDIF» for the «i.name» interface.
					 * @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
					 * @generated
					 */
					«IF i.single»
						protected «i.className» «i.name» = null;
					«ELSE»
						protected final java.util.Collection<«i.className»> «i.name» = new java.util.concurrent.CopyOnWriteArrayList<«i.className»>();
					«ENDIF»
				«ENDIF»
				
				«IF i.serviceObjectDelegate.needsProxy»
					/**
					 * Service object proxy for the «i.name» interface.
					 * Calls to this proxy object are automatically delegated to «IF i.serviceObjectDelegate == EServiceObjectDelegate::myxRegistry»all service objects in the MyxRegistry of type «i.className».«ELSE»all connections on the interface«ENDIF»
					* @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
					* @generated
					*/
					protected final «i.className» «i.name»Proxy =
						(«i.className») java.lang.reflect.Proxy.newProxyInstance(
							«i.className».class.getClassLoader(), 
							new Class[] { «i.className».class },
							new java.lang.reflect.InvocationHandler() {
								@Override
								public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable {
									«IF i.serviceObjectDelegate == EServiceObjectDelegate::myxRegistry»
										for («i.className» o : myxRegistry.getObjects(«toClassName(b.stubClassName)».this, «i.className».class)) {
									«ELSEIF !i.single»
										for («i.className» o : «i.name») {
									«ELSE»
										«i.className» o = «i.name»;
										if (o == null) {
											throw new NullPointerException("«i.name»");
										}
										else {
									«ENDIF»
									try {
										method.invoke(o, args);
									}
									catch (Exception e) {
										e.printStackTrace();
									}
									}
								return null;
								}
						});
				«ENDIF»

				«IF i.generateGetter»
				/**
				* Returns the service object(s) for the «i.name» interface.
				* @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
				* @generated
				*/
				«IF i.serviceObjectDelegate.needsProxy»
				public «i.className» get«i.name.toFirstUpper»() {
				«ELSE»
				public «IF i.single»«i.className»«ELSE»java.util.Collection<«i.className»>«ENDIF» get«i.name.toFirstUpper»() {
				«ENDIF»
					«IF i.serviceObjectDelegate == EServiceObjectDelegate::brick»
						return this;
					«ELSE»
						«IF !i.serviceObjectDelegate.needsProxy && i.single»
							if («i.name» == null) {
								throw new NullPointerException("Uninitialized service object: «i.name»");
							}
						«ENDIF»
						return «i.name»«IF i.serviceObjectDelegate.needsProxy»Proxy«ENDIF»;
					«ENDIF»
				}
				«ENDIF»

			«ENDFOR»
			
			/**
			 * Returns service object(s) for IN interfaces.
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::IN]»
				* @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
			«ENDFOR»
			* @generated
			*/
			@Override
			public Object getServiceObject(IMyxName interfaceName) {
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::IN]»
				if(interfaceName.equals(«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»)) {
					«IF i.serviceObjectDelegate == EServiceObjectDelegate::brick»
						return this;
					«ELSE»
						«IF !i.serviceObjectDelegate.needsProxy && i.single»
							if («i.name» == null) {
								throw new NullPointerException("Uninitialized service object: «i.name»");
							}
						«ENDIF»
						return «i.name»«IF i.serviceObjectDelegate.needsProxy»Proxy«ENDIF»;
					«ENDIF»
				}
			«ENDFOR»
			«IF b.parentBrickId == null»
				throw new IllegalArgumentException("Unhandled interface: "+interfaceName.getName());
			«ELSE»
				return super.getServiceObject(interfaceName);
			«ENDIF»
			}

			/**
			* Update service objects based on connected OUT interfaces.
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::OUT]»
				* @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
			«ENDFOR»
			* @generated
			*/
			@Override
			public void interfaceConnected(IMyxName interfaceName, Object serviceObject) {
				if (serviceObject == null)
					throw new NullPointerException(interfaceName.getName());
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::OUT]»

				if(interfaceName.equals(«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»)) {
						«IF i.single»
							if («i.name» != null)
								throw new IllegalStateException("Only a single connection is supported on interface: «i.name»");
							«i.name» = («i.className») serviceObject;
						«ELSE»
							«i.name».add((«i.className») serviceObject);
						«ENDIF»
						return;
				}
			«ENDFOR»
			
			«IF b.parentBrickId == null»
				throw new IllegalArgumentException("Unhandled interface: "+interfaceName.getName());
			«ELSE»
				super.interfaceConnected(interfaceName, serviceObject);
			«ENDIF»
			}

			/**
			* Update service objects based on disconnecting OUT interfaces.
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::OUT]»
				* @see #«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»
			«ENDFOR»
			* @generated
			*/
			@Override
			public void interfaceDisconnecting(IMyxName interfaceName, Object serviceObject) {
				if (serviceObject == null)
					throw new NullPointerException(interfaceName.getName());
			«FOR i : b.interfaces.filter[i|i.direction == EMyxInterfaceDirection::OUT]»

				if(interfaceName.equals(«toConstantName(i.direction.name.toLowerCase + i.name.toFirstUpper)»)) {
						«IF i.single»
							if («i.name» == null)
								throw new IllegalStateException("A connection was never made on interface: «i.name»");
							«i.name» = null;
						«ELSE»
							«i.name».remove(serviceObject);
						«ENDIF»
						return;
				}
			«ENDFOR»
			
			«IF b.parentBrickId == null»
				throw new IllegalArgumentException("Unhandled interface: "+interfaceName.getName());
			«ELSE»
				super.interfaceDisconnecting(interfaceName, serviceObject);
			«ENDIF»
			}
		
			/**
			 * Performs no operation upon the completion of an interface disconnecting.
			 * @generated
			 */
			@Override
			public void interfaceDisconnected(IMyxName interfaceName, Object serviceObject) {
			«IF b.parentBrickId != null»
				super.interfaceDisconnected(interfaceName, serviceObject);
			«ENDIF»
			}
		}
	''';
	}
	}
