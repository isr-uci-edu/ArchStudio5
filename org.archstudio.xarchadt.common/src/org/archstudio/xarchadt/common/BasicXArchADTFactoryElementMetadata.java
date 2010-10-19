package org.archstudio.xarchadt.common;

public class BasicXArchADTFactoryElementMetadata implements IXArchADTFactoryElementMetadata{

	private final String elementName;
	private final Class<?> elementClass;

	public BasicXArchADTFactoryElementMetadata(String elementName, Class<?> elementClass) {
	    super();
	    this.elementName = elementName;
	    this.elementClass = elementClass;
    }
	
	public String getElementName() {
    	return elementName;
    }

	public Class<?> getElementClass() {
    	return elementClass;
    }
}
