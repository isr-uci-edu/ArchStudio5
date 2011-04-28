package org.archstudio.myxgen.jet.extension.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.archstudio.myxgen.jet.extension.ConnectionTiming;
import org.archstudio.myxgen.jet.extension.Direction;
import org.archstudio.myxgen.jet.extension.IInterface;
import org.archstudio.myxgen.jet.extension.TemplateType;
import org.eclipse.core.runtime.IConfigurationElement;


/**
 * an internal implementation of IInterface.
 * 
 * @author Nobu Takeo [nobu.takeo@gmail.com, nobu.takeo@uci.edu]
 */
public class Interface extends ExtensionPointLocation implements IInterface {

	/** the "id" attribute of the "interface" element */
	private static final String ATTR_ID = "id";

	/** the "name" attribute of the "interface" element */
	private static final String ATTR_NAME = "name";

	/** the "direction" attribute of the "interface" element */
	private static final String ATTR_DIRECTION = "direction";

	/** the "connectBeforeInit" attribute of the "interface" element */
	private static final String ATTR_CONNECTION_TIMING = "connectionTiming";

	private static final String ATTR_TEMPLATE_TYPE = "templateType";

	/** the "javaInterface" attribute of the "interface" element */
	private static final String ATTR_JAVA_INTERFACE = "class";

	/** the description of this interface */
	private static final String ELEMENT_DESCRIPTION_NAME = "description";

	/** the unique id of this interface */
	private final String id;

	/** the name of this interface */
	private final String name;

	/**
	 * the direction of the interface.
	 * <ul>
	 * <li>Direction.IN : the brick is used by others through this interface.</li>
	 * <li>Direction.OUT_SINGLE: the brick uses only one service object through
	 * this interface.</li>
	 * <li>Direction.OUT_MULTI: the brick uses more than one service object
	 * through this interface.</li>
	 * </ul>
	 */
	private final Direction direction;

	/**
	 * When this interface is connected to other interfaces.
	 * <ul>
	 * <li>ConnectionTiming.NONE : the initializing timing is not cared.</li>
	 * <li>ConnectionTiming.CONNECT_BEFORE_INIT : the brick will be connected to
	 * others before its initialization.</li>
	 * <li>ConnectionTiming.CONNECT_BEFORE_BEGIN : the brick must be initialized
	 * first, then it will be connected to others.</li>
	 * </ul>
	 */
	private final ConnectionTiming connectionTiming;

	/** the fully qualified Java Interface Name assigned to this interface */
	private final String javaInterfaceName;

	/**
	 * the template type how the interface should be implemented. MyxCodegen
	 * generates code according to the template type.
	 */
	private final TemplateType templateType;

	/**
	 * the description of the interface
	 */
	private String description = null;

	public Interface(URL pluginUrl, IConfigurationElement element) {
		super(pluginUrl);

		this.id = element.getAttribute(Interface.ATTR_ID);
		this.name = element.getAttribute(Interface.ATTR_NAME);
		this.direction = Direction.fromSchemaDescription(element.getAttribute(Interface.ATTR_DIRECTION));
		this.connectionTiming = ConnectionTiming.fromSchemaDescription(element.getAttribute(Interface.ATTR_CONNECTION_TIMING));
		this.javaInterfaceName = element.getAttribute(Interface.ATTR_JAVA_INTERFACE);
		this.templateType = TemplateType.fromSchemaDescription(element.getAttribute(Interface.ATTR_TEMPLATE_TYPE));

		IConfigurationElement[] children = element.getChildren();
		for (IConfigurationElement child : children) {
			if (Interface.ELEMENT_DESCRIPTION_NAME.equals(child.getName())) {
				this.description = child.getValue();
				break;
			}
		}
	}

	public URL getExtensionPointUrl() {
		try {
			return new URL(pluginUrl.getProtocol() + ":" + pluginUrl.getPath() + "#" + id);
		}
		catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getId()
	 */
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getName()
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getDirection()
	 */
	public Direction getDirection() {
		return direction;
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getConnectionTiming()
	 */
	public ConnectionTiming getConnectionTiming() {
		return connectionTiming;
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getTemplateType()
	 */
	public TemplateType getTemplateType() {
		return templateType;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.archstudio.myxgen.jet.extension.IInterface#getFQJavaInterfaceName()
	 */
	public String getFQJavaInterfaceName() {
		return javaInterfaceName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.archstudio.myxgen.jet.extension.IInterface#getDescription()
	 */
	public String getDescription() {
		return description;
	}
}
