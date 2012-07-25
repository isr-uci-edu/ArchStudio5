
import edu.uci.isr.xarch.*;
import edu.uci.isr.xarch.instance.*;

import java.util.*;

public class Test{

	//An IXArchImplementation object, providing top-level services for accessing
	//and manipulating xADL 2.0 documents.
	private XArchImplementation xArchImplementation;
	
	//An IXArch object, representing the top-level xArch element of a xADL 2.0 document.
	private IXArch xArch;

	//The instance context is the factory object by which all classes defined in the
	//instance schema are instantiated.
	private InstanceContext instances;
	
	//Simple main driver.
	public static void main(String[] args){
		new Test();
	}
	
	//This program creates the architecture for an instance of a simple 
	//stack-manipulator application built in the C2 style.  This application's
	//architecture looks like this:
	
	/*
                +-------------------------+
                |                         |
                |       Stack ADT         |  component
	            |                         |
                +-------------------------+
                            |
           +----------------------------------+
           |              bus1                | connector
           +----------------------------------+
                            |
                 +-------------------------+
                 |                         |
                 |       Stack Artist      | component
                 |                         |
                 +-------------------------+
                            |
           +----------------------------------+
           |              bus2                | connector
           +----------------------------------+
                            |
                 +-------------------------+
                 |                         |
                 |       Graphics Binding  | component
                 |                         |
                 +-------------------------+
	*/
	
	public Test(){

		//This line gets the default IXArchImplementation object.  This object is
		//the entrypoint to all data binding library services and allows you to
		//perform document-level operations (create, clone, parse, serialize).
		//The default implementation of this object uses the DOM-based wrappers,
		//so the store for xADL data will be an in-memory DOM model.
		//XArchUtils is a collection of utility functions that are useful
		//for dealing with xArch/xADL 2.0 documents.
		xArchImplementation = XArchUtils.getDefaultXArchImplementation();

		//This line uses the IXArchImplementation object to create
		//a new xADL document.  Although the xArch element is defined in the Instances context,
		//the APIs consider it independent of any individual schema.  As such, this is the
		//way to start a new xADL document.
		xArch = xArchImplementation.createXArch();
		
		//Now that we have a top-level xArch element, we need to create a context
		//(factory) that can provide us with objects that can be made children of that
		//object.  For this example, we will create an InstanceContext, corresponding
		//to the instance.xsd schema.
		instances = xArchImplementation.createContext(xArch, "instance");
		
		//This creates the 2nd element of the architecture document, an <archInstance> element.
		//Normally, we would use instances.createArchInstance() for this purpose.  However,
		//since the <archInstance> tag is attached to the top-level xArch tag, we have to use
		//the special function createArchInstanceElement().
		IArchInstance archInstance = instances.createArchInstanceElement();
		
		//This adds the archInstance element to the xArch element.  Since the xArch element
		//can have any children, it exposes a function called addObject, instead of a specific
		//function called addArchInstance.  Now our document looks roughly like this:
		//<xArch>
		//  <archInstance>
		// ...
		xArch.addObject(archInstance);
		
		//We are going to create three C2-style component instances.  createC2CopmonentInstance() is 
		//a function that, given an ID and a description, can create a C2-style component.
		IComponentInstance stackAdtCompInst = createC2ComponentInstance("stackAdt", "Stack ADT");
		IComponentInstance stackArtistCompInst = createC2ComponentInstance("stackArtist", "Stack Artist");
		IComponentInstance graphicsBindingCompInst = createC2ComponentInstance("graphicsBinding", "Graphics Binding");
		
		//Likewise, we will create two C2-style bus connector instances.
		IConnectorInstance bus1ConnInst = createC2ConnectorInstance("bus1", "Bus Connector #1");
		IConnectorInstance bus2ConnInst = createC2ConnectorInstance("bus2", "Bus Connector #2");
		
		//Now, we will create the links among the interfaces on these components and connectors.
		ILinkInstance linkInst1 = createLinkInstance("link1", "ADT-Bus1 Link", "stackAdt.bottom", "bus1.top");
		ILinkInstance linkInst2 = createLinkInstance("link2", "Bus1-Artist Link", "bus1.bottom", "artist.top");
		ILinkInstance linkInst3 = createLinkInstance("link3", "Artist-Bus2 Link", "artist.bottom", "bus2.top");
		ILinkInstance linkInst4 = createLinkInstance("link4", "Bus2-GB Link", "bus2.bottom", "graphicsBinding.top");
		
		//Now we add the component instances to the archInstanc tag
		archInstance.addComponentInstance(stackAdtCompInst);
		archInstance.addComponentInstance(stackArtistCompInst);
		archInstance.addComponentInstance(graphicsBindingCompInst);
		
		//And we add the connector instances to the archInstance tag
		archInstance.addConnectorInstance(bus1ConnInst);
		archInstance.addConnectorInstance(bus2ConnInst);
		
		//And we add the link instances to the archInstance tag
		archInstance.addLinkInstance(linkInst1);
		archInstance.addLinkInstance(linkInst2);
		archInstance.addLinkInstance(linkInst3);
		archInstance.addLinkInstance(linkInst4);
		
		//This line uses the XML prettyprinter included in XArchUtils
		//to print our in-memory data structure as an XML document.
		try{
			System.out.println(xArchImplementation.serialize(xArch, null));
		}
		catch(XArchSerializeException xse){
			xse.printStackTrace();
			return;
		}
		
		//"Get" functions work basically the same way.  For instance, this will get
		//all the component instances in the architecture in a Collection.
		Collection allComponentInstances = archInstance.getAllComponentInstances();
		//Now, let's iterate over them and get their descriptions.
		for(Iterator it = allComponentInstances.iterator(); it.hasNext(); ){
			IComponentInstance compInstance = (IComponentInstance)it.next();
			IDescription description = compInstance.getDescription();
			//Note--description could be null, if one hadn't been set.  However,
			//we have set descriptions for all our components, so we don't have
			//to check.
			String value = description.getValue();
			System.out.println("Description: " + value);
		}
	}
	
	//Descriptions are an element with a text value in between the start
	//and end tags.  We use this function to create the element and add
	//the value in one function call instead of two.
	public IDescription createDescription(String descString){
		//Create an empty description with no text between
		//the start and end tags
		IDescription desc = instances.createDescription();
		//Set the text value.
		desc.setValue(descString);
		return desc;
	}
	
	//See createDescription...
	public IDirection createDirection(String directionString){
		IDirection dir = instances.createDirection();
		dir.setValue(directionString);
		return dir;
	}
	
	//This function creates a C2-style component instance given
	//an ID and a description.  A C2-style component is a regular
	//component with two fixed interfaces: a top (inout) interface
	//and a bottom (inout) interface.
	public IComponentInstance createC2ComponentInstance(String id,
		String description){
		
		//This creates the component instance, using the InstanceContext
		//factory object.  At this point, the component instance
		//has no ID, description, interfaces, etc.
		IComponentInstance comp = instances.createComponentInstance();
		//Since ID is a string attribute, we don't have to create
		//an object for it (like an IIdentifier or something).  Rather,
		//we can just set it directly.
		comp.setId(id);
		//Since descriptions are elements (that is, they have their own
		//separate tag), we do have to create a description object.
		//That is accompblished with the createDescription convenience
		//function included in this program.
		IDescription desc = createDescription(description);
		
		//We have to attach our new description element to the
		//component instance.
		comp.setDescription(desc);
		
		//Here we create two interface instances--one for the top
		//of the component and one for the bottom.  We auto-generate
		//the names and IDs of these interface instances by appending
		//".top" and " Top" or ".bottom" and " Bottom" to the component
		//instances ID and description, respectively.  We take advantage
		//of the convenience functions createDescription and createDirection
		//to make our job easier here.
		IInterfaceInstance top = instances.createInterfaceInstance();
		top.setId(id + ".top");
		top.setDescription(createDescription(description + " Top Interface"));
		top.setDirection(createDirection("inout"));
		
		IInterfaceInstance bottom = instances.createInterfaceInstance();
		bottom.setId(id + ".bottom");
		bottom.setDescription(createDescription(description + " Bottom Interface"));
		bottom.setDirection(createDirection("inout"));
		
		//We have to attach our new interface instances to the component
		//as well.
		comp.addInterfaceInstance(top);
		comp.addInterfaceInstance(bottom);
		
		//Finally, we return our newly created component instance.
		return comp;
	}
	
	//This function is very similat to createC2ComponentInstance, above.
	//See commentary on that function for details.
	public IConnectorInstance createC2ConnectorInstance(String id,
		String description){
		IConnectorInstance conn = instances.createConnectorInstance();
		conn.setId(id);
		conn.setDescription(createDescription(description));
		
		IInterfaceInstance top = instances.createInterfaceInstance();
		top.setId(id + ".top");
		top.setDescription(createDescription(description + " Top Interface"));
		top.setDirection(createDirection("inout"));
		
		IInterfaceInstance bottom = instances.createInterfaceInstance();
		bottom.setId(id + ".bottom");
		bottom.setDescription(createDescription(description + " Bottom Interface"));
		bottom.setDirection(createDirection("inout"));
		
		conn.addInterfaceInstance(top);
		conn.addInterfaceInstance(bottom);
		
		return conn;
	}
	
	//This creates an architecture link (i.e. a link between two interfaces) given
	//a link ID and description, as well as the identifiers of the interface endpoints
	//of the link.
	public ILinkInstance createLinkInstance(String linkId, String linkDesc, String id1, String id2){
		//First, we create a new link instance from the InstanceContext factory object.
		ILinkInstance linkInst = instances.createLinkInstance();
		//Since ID is an attribute string, we can set it directly.
		linkInst.setId(linkId);
		//Again, we use the convenience function createDescription() to create a string-based
		//description of this link and attach it to the link object using setDescription.
		linkInst.setDescription(createDescription(linkDesc));
		
		//Links look roughly like this:
		//<link>
		//  <point>
		//    <anchorOnInterface type="simple" href="#...."/>
		//  </point>
		//  <point>
		//    <anchorOnInterface type="simple" href="#...."/>
		//  </point>
		//</link>
		
		//Each point has an anchor.  That anchor, by virtue of having the XLink attributes
		//'type' and 'href', is an XLink.  XLinks are like hyperlinks from one part of an
		//XML document to another part of an XML document.  Our architecture links
		//use XLinks, but they are not the same thing.
		
		//Anchors are of the type IXMLLink, so we will create the first anchor here.
		IXMLLink anchor1 = instances.createXMLLink();
		//xADL 2.0 currently supports only "simple" XLinks.  Therefore, the type
		//attribute is always "simple."
		anchor1.setType("simple");
		//Simple XLinks have only an href to specify the link destination.  We have
		//adopted a simple convention that defines this href as follows:
		//  http://www......com/foobar.xml#id1234
		//  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ^^^^^^
		//     URL of XML document          ID of element within that document
		//
		//Of course, local URLs are allowed as well.  If this XLink is linking to something
		//internal (i.e. in the same XML document), the part before the pound-sign (#) can
		//be omitted.  Since this test program only uses local links, we ignore the
		//http://.... part before the pound sign.
		anchor1.setHref("#" + id1);
		
		//The second anchor is created similarly.
		IXMLLink anchor2 = instances.createXMLLink();
		anchor2.setType("simple");
		anchor2.setHref("#" + id2);
		
		//Now we create the two points that will hold the anchors, and attach the anchors
		//to their points.
		IPoint point1 = instances.createPoint();
		point1.setAnchorOnInterface(anchor1);
		
		IPoint point2 = instances.createPoint();
		point2.setAnchorOnInterface(anchor2);
		
		//Now we add the two points to the link object.
		linkInst.addPoint(point1);
		linkInst.addPoint(point2);
		
		//And we return the completed link object.
		return linkInst;
	}
	
}

