package org.archstudio.myxgen.jet.brick;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.archstudio.myxgen.jet.extension.ConnectionTiming;
import org.archstudio.myxgen.jet.extension.Direction;
import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.IMyxBrickExtension;
import org.archstudio.myxgen.jet.extension.TemplateType;


/**
 * Brick (either Component/Connector) info container for code generation.
 * 
 * @author Nobu Takeo nobu.takeo@gmail.com, nobu.takeo@uci.edu
 */
public class CodegenBrick implements IMyxBrickExtension {

	/**
	 * Brick defined in the eclipse's extension point
	 */
	private final IMyxBrickExtension extBrick;

	/** the list of "in" brick interfaces */
	private final List<CodegenBrickInterface> inIntfs = new ArrayList<CodegenBrickInterface>();

	/** the list of "out" brick interfaces */
	private final List<CodegenBrickInterface> outIntfs = new ArrayList<CodegenBrickInterface>();

	/** the constructors of the parent class */
	private List<MethodContainer> parentConstructors = new ArrayList<MethodContainer>();

	/**
	 * the collection of methods that should have been implemented in the parent brick
	 */
	private Collection<MethodContainer> unimplMethods = new ArrayList<MethodContainer>();

	public CodegenBrick(IMyxBrickExtension extBrick) {
		//brick
		this.extBrick = extBrick;

		//interfaces
		for (IInterface extIntf : extBrick.getInterfaces()) {
			CodegenBrickInterface intf = new CodegenBrickInterface(extIntf);
			if (intf.getDirection() == Direction.IN) {
				inIntfs.add(intf);
			}
			else {
				outIntfs.add(intf);
			}
		}

	}

	public URL getExtensionPointUrl() {
		return extBrick.getExtensionPointUrl();
	}

	public URL getPluginUrl() {
		return extBrick.getPluginUrl();
	}

	public String getSymbolicName() {
		return extBrick.getSymbolicName();
	}

	//TODO: experiment
	public IMyxBrickExtension getParentBrick() {
		return this.extBrick.getParentBrick();
	}

	/**
	 * Returns the list of "in" Brick interfaces.
	 * 
	 * @return
	 */
	public List<CodegenBrickInterface> getInInterfaces() {
		return inIntfs;
	}

	/**
	 * Returns the list of "out" brick interfaces.
	 * 
	 * @return
	 */
	public List<CodegenBrickInterface> getOutInterfaces() {
		return outIntfs;
	}

	/**
	 * Returns all the brick interfaces of this Brick
	 * 
	 * @return
	 */
	public List<CodegenBrickInterface> getAllInterfaces() {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		intfs.addAll(inIntfs);
		intfs.addAll(outIntfs);
		return intfs;
	}

	/**
	 * Returns the brick interfaces of the given name
	 * 
	 * @param name
	 * @return
	 */
	public CodegenBrickInterface getInterfaceByName(String name) {
		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getName().equals(name)) {
				return intf;
			}
		}
		return null;
	}

	/**
	 * Returns the list of Brick interfaces filtered by ConnectionTiming.
	 * 
	 * @param ct
	 *            ConnectionTiming
	 * @return
	 */
	public List<CodegenBrickInterface> getInterfaces(ConnectionTiming ct) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : this.outIntfs) {
			if (ct == intf.getConnectionTiming()) {
				intfs.add(intf);
			}
		}

		for (CodegenBrickInterface sig : this.inIntfs) {
			if (ct == sig.getConnectionTiming()) {
				intfs.add(sig);
			}
		}
		return intfs;

	}

	/**
	 * Returns the list of Brick interfaces filtered by Direction
	 * 
	 * @param directions
	 * @return
	 */
	public List<CodegenBrickInterface> getInterfaces(Direction... directions) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			for (Direction direction : directions) {
				if (intf.getDirection() == direction) {
					intfs.add(intf);
				}
			}
		}

		return intfs;
	}

	/**
	 * Returns the list of Brick interfaces filtered by Direction and TemplateType
	 * 
	 * @param direction
	 * @param templateType
	 * @return
	 */
	public List<CodegenBrickInterface> getInterfaces(Direction direction, TemplateType templateType) {

		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getDirection() == direction && intf.getTemplateType() == templateType) {
				intfs.add(intf);
			}
		}

		return intfs;

	}

	/**
	 * Returns the list of Brick interfaces filtered by ConnectionTiming and bImplementedInTemplateBrick.
	 * 
	 * @param ct
	 *            ConnectionTiming
	 * @param templateType
	 *            TemplateType
	 * @return
	 */
	public List<CodegenBrickInterface> getInterfaces(ConnectionTiming ct, TemplateType templateType) {
		List<CodegenBrickInterface> intfs = new ArrayList<CodegenBrickInterface>();
		for (CodegenBrickInterface intf : this.outIntfs) {
			if (ct == intf.getConnectionTiming() && templateType == intf.getTemplateType()) {
				intfs.add(intf);
			}
		}

		for (CodegenBrickInterface intf : this.inIntfs) {
			if (ct == intf.getConnectionTiming() && templateType == intf.getTemplateType()) {
				intfs.add(intf);
			}
		}
		return intfs;

	}

	/**
	 * Returns the collection of fully qualified class names this brick class needs to implement.
	 * 
	 * @return
	 */
	public Collection<String> getImplementsClasses() {

		Set<String> javaImplements = new HashSet<String>();

		// in brick interfaces
		for (CodegenBrickInterface intf : inIntfs) {

			if (intf.getTemplateType() == TemplateType.IMPLEMENTED_IN_BRICK
					|| intf.getTemplateType() == TemplateType.DELEGATE_TO_MYX_REGISTRY) {
				javaImplements.add(intf.getFQJavaInterfaceName());
			}
		}

		// out brick interfaces
		for (CodegenBrickInterface intf : outIntfs) {

			if (intf.getTemplateType() == TemplateType.IMPLEMENTED_IN_BRICK) {
				javaImplements.add(intf.getFQJavaInterfaceName());
			}
		}

		return javaImplements;

	}

	/**
	 * Returns the collection of fully qualified class names brick interfaces of the specified template type and the
	 * direction use.
	 * 
	 * @param direction
	 * @param types
	 * @return
	 */
	public Collection<String> getVoidMethodArgsFQClassNames(Direction direction, TemplateType... types) {

		Set<String> fqClassNames = new HashSet<String>();

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getDirection() != direction) {
				continue;
			}
			for (TemplateType type : types) {
				if (intf.getTemplateType() == type) {
					fqClassNames.addAll(CodegenBrickInterface.getArgsFQClassNames(intf.getJavaInterfaceVoidMethods()));
					break;
				}
			}
		}

		return fqClassNames;
	}

	/**
	 * Returns the collection of fully qualified class names brick interfaces of the specified template type and the
	 * direction use.
	 * 
	 * @param direction
	 * @param types
	 * @return
	 */
	public Collection<String> getNonVoidMethodArgsFQClassNames(Direction direction, TemplateType... types) {

		Set<String> fqClassNames = new HashSet<String>();

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getDirection() != direction) {
				continue;
			}
			for (TemplateType type : types) {
				if (intf.getTemplateType() == type) {
					fqClassNames
							.addAll(CodegenBrickInterface.getArgsFQClassNames(intf.getJavaInterfaceNonVoidMethods()));
					break;
				}
			}
		}

		return fqClassNames;
	}

	/**
	 * Returns the collection of fully qualified class names of brick interfaces specified by the direction and template
	 * types.
	 * 
	 * @param direction
	 * @param types
	 * @return
	 */
	public Collection<String> getInterfaceFQClassNames(Direction direction, TemplateType... types) {

		Set<String> fqClassNames = new HashSet<String>();

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getDirection() != direction) {
				continue;
			}
			for (TemplateType type : types) {
				if (type == intf.getTemplateType()) {
					fqClassNames.add(intf.getFQJavaInterfaceName());
					break;
				}
			}
		}

		return fqClassNames;
	}

	/**
	 * Returns a collection of java class names used in the constructor parameters.
	 * 
	 * @return a collection of fully qualified java class names
	 */
	public Collection<String> getFQParentConstructorParams() {
		Set<String> fqClassNames = new HashSet<String>();
		if (parentConstructors == null) {
			return fqClassNames;
		}
		for (MethodContainer constructor : parentConstructors) {
			for (MethodParameter param : constructor.getMethodParameters()) {
				fqClassNames.addAll(param.getFqClassNames());
			}
		}
		return fqClassNames;
	}

	/**
	 * Returns true if this brick has at least one brick interfaces that has the specified direction and the types
	 * 
	 * @param direction
	 * @param types
	 * @return
	 */
	public boolean hasInterface(Direction direction, TemplateType... types) {

		for (CodegenBrickInterface intf : getAllInterfaces()) {
			if (intf.getDirection() == direction) {
				for (TemplateType type : types) {
					if (intf.getTemplateType() == type) {
						return true;
					}
				}
			}
		}
		return false;

	}

	/**
	 * Returns the fully qualified java class name of the parent brick's myx base class
	 * 
	 * @return class name, or null if no parent is specified
	 */
	public String getFQParentBaseClassName() {
		IMyxBrickExtension parent = extBrick.getParentBrick();
		if (parent == null) {
			return null;
		}
		else {
			return parent.getFQBaseClassName();
		}
	}

	/**
	 * Returns the fully qualified java class name of the parent brick's myx default impl class
	 * 
	 * @return class name, or null if no parent is specified
	 */
	public String getFQParentDefaultImplClassName() {
		IMyxBrickExtension parent = extBrick.getParentBrick();
		if (parent == null) {
			return null;
		}
		else {
			return parent.getFQDefaultImplClassName();
		}
	}

	/**
	 * Returns the fully qualified java class name of the parent brick.
	 * 
	 * @return
	 */
	public String getFQParentClassName() {
		String base = getFQParentBaseClassName();
		String comp = getFQParentDefaultImplClassName();
		if (base != null && comp != null) {
			return comp;
		}
		else {
			return base;
		}
	}

	/**
	 * Returns true if this brick has a parent brick
	 * 
	 * @return
	 */
	public boolean hasParentBrick() {
		return getFQParentBaseClassName() != null;
	}

	/**
	 * Returns a collection of collections of brick interfaces filtered by TemplateType and grouped by the brick
	 * interfaces's java interfaces.
	 * 
	 * @param brickIntfs
	 * @param filterType
	 * @return
	 */
	public static Collection<Collection<CodegenBrickInterface>> getBrickInterfacesByInterface(
			Collection<CodegenBrickInterface> brickIntfs, TemplateType... filterTypes) {

		Map<String, Collection<CodegenBrickInterface>> map = new HashMap<String, Collection<CodegenBrickInterface>>();
		for (CodegenBrickInterface intf : brickIntfs) {

			//filters by filterType
			boolean isTarget = false;
			for (TemplateType filterType : filterTypes) {
				if (intf.getTemplateType() == filterType) {
					isTarget = true;
					break;
				}
			}
			if (!isTarget) {
				continue;
			}

			String key = intf.getFQJavaInterfaceName();
			if (key == null) {
				continue;
			}

			//group by the brick interface's java interface name
			if (map.containsKey(key)) {
				Collection<CodegenBrickInterface> intfs = map.get(key);
				intfs.add(intf);
			}
			else {
				Collection<CodegenBrickInterface> intfs = new HashSet<CodegenBrickInterface>();
				intfs.add(intf);
				map.put(key, intfs);
			}
		}

		//test
		for (Collection<CodegenBrickInterface> intfs : map.values()) {
			Iterator<CodegenBrickInterface> it = intfs.iterator();
			CodegenBrickInterface repIntfs = it.next();
			for (MethodContainer method : repIntfs.getJavaInterfaceVoidMethods()) {

				//java comment
				repIntfs.getFQJavaInterfaceName();
				method.getMethodName();
				method.toParamsSimpleString();

				//method
				method.toSimpleString();
				for (CodegenBrickInterface intf : intfs) {

					//proxy
					intf.getName();
					method.getMethodName();
					method.toParamNamesString();

				} // end of for(BrickSignature intf
			} // end of for( MethodContainer method
		} // end of for(Collection<BrickSignature> intfs

		return map.values();
	}

	/**
	 * Returns the name of this Brick
	 * 
	 * @return the name of this Brick. null if no name is assigned.
	 */
	public String getName() {
		return extBrick.getName();
	}

	/**
	 * Returns the brick Id of this Brick
	 * 
	 * @return
	 */
	public String getId() {
		return extBrick.getId();
	}

	/**
	 * Returns the fully qualified java class name of the default implementation class.
	 * 
	 * @return the class name assigned to this brick. null if no class name is assigned.
	 */
	public String getFQDefaultImplClassName() {
		return extBrick.getFQDefaultImplClassName();
	}

	/**
	 * Returns the fully qualified java class name of the myx base class
	 * 
	 * @return
	 */
	public String getFQBaseClassName() {
		return extBrick.getFQBaseClassName();
	}

	/**
	 * Returns the description of the brick
	 * 
	 * @return the description of the brick
	 */
	public String getDescription() {
		String desc = extBrick.getDescription();
		return desc != null ? desc : "";
	}

	public Collection<IInterface> getAncestorsExtensionInterfaces() {
		return extBrick.getAncestorsExtensionInterfaces();
	}

	public Collection<IInterface> getInterfaces() {
		return extBrick.getInterfaces();
	}

	public String getParentBrickId() {
		return extBrick.getParentBrickId();
	}

	/**
	 * @return the parentConstructors
	 */
	public List<MethodContainer> getParentConstructors() {
		return parentConstructors;
	}

	/**
	 * @param parentConstructors
	 *            the parentConstructors to set
	 */
	public void setParentConstructors(List<MethodContainer> parentConstructors) {
		this.parentConstructors = parentConstructors;
	}

	/**
	 * Returns a collection of ancesotr's "in" IInterfaces
	 * 
	 * @return
	 */
	public Collection<CodegenBrickInterface> getAncesotrInInterfaces() {
		Collection<CodegenBrickInterface> inAncestorItfs = new ArrayList<CodegenBrickInterface>();
		for (IInterface intf : extBrick.getAncestorsExtensionInterfaces()) {
			CodegenBrickInterface cbIntf = new CodegenBrickInterface(intf);
			if (intf.getDirection() == Direction.IN) {
				inAncestorItfs.add(cbIntf);
			}
		}
		return inAncestorItfs;
	}

	/**
	 * Returns a collection of ancesotr's "out" IInterfaces
	 * 
	 * @return
	 */
	public Collection<CodegenBrickInterface> getAncesotrOutInterfaces() {
		Collection<CodegenBrickInterface> outAncestorItfs = new ArrayList<CodegenBrickInterface>();
		for (IInterface intf : extBrick.getAncestorsExtensionInterfaces()) {
			CodegenBrickInterface cbIntf = new CodegenBrickInterface(intf);
			if (intf.getDirection() == Direction.OUT_MULTI || intf.getDirection() == Direction.OUT_SINGLE) {
				outAncestorItfs.add(cbIntf);
			}
		}
		return outAncestorItfs;
	}

	/**
	 * Returns a collection of methods that should have been implemented in the parent brick
	 * 
	 * @return the unimplMethods
	 */
	public Collection<MethodContainer> getUnimplMethods() {
		return unimplMethods;
	}

	/**
	 * @param unimplMethods
	 *            the unimplMethods to set
	 */
	public void setUnimplMethods(Collection<MethodContainer> unimplMethods) {
		this.unimplMethods = unimplMethods;
	}

	public void setParentBrick(IMyxBrickExtension parentBrick) {
		throw new IllegalArgumentException("Unimplemented");
	}
}
