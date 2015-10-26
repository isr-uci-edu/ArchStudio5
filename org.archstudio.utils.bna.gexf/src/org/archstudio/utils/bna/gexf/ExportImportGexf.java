package org.archstudio.utils.bna.gexf;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.utils.bna.dot.Edge;
import org.archstudio.utils.bna.dot.Node;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.FileDialog;
import org.xml.sax.SAXException;

import com.google.common.collect.Maps;
import com.google.common.io.Files;

import net.gexf_1_2.gexf.DocumentRoot;
import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;
import net.gexf_1_2.viz.VizPackage;

public class ExportImportGexf extends AbstractThingLogic implements IBNAMenuListener2 {

  public ExportImportGexf(IBNAWorld world) {
    super(world);
  }

  @Override
  public void fillMenu(final IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
      IMenuManager menuManager) {
    BNAUtils.checkLock();

    if (thingsAtLocation.getViewAtLocation() != null) {
      menuManager.add(new BNAAction("Export GEXF") {
        @Override
        public void runWithLock() {
          try {
            exportGexf(view);
          } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error",
                e.getMessage());
          }
        }
      });
      menuManager.add(new BNAAction("Import GEXF") {
        @Override
        public void runWithLock() {
          try {
            importGexf(view);
          } catch (Exception e) {
            e.printStackTrace();
            MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error",
                e.getMessage());
          }
        }
      });
    } else {
      menuManager.add(SWTWidgetUtils.createNoAction("Export GEXF"));
      menuManager.add(SWTWidgetUtils.createNoAction("Import GEXF"));
    }
  }

  protected void exportGexf(IBNAView view) throws IOException {
    FileDialog fd = new FileDialog(view.getBNAUI().getComposite().getShell(), SWT.SAVE);
    fd.setFilterExtensions(new String[] {"*.gexf"});
    String filepath = fd.open();
    if (filepath != null) {
      exportGexf(view, new File(filepath));
    }
  }

  protected void exportGexf(IBNAView view, File file) throws IOException {
    if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
      throw new IOException("Cannot create folder: " + file.getParentFile().toString());
    }

    IXArchADT xarch = new XArchADTImpl();
    ObjRef documentRef = xarch.createDocument(URI.createURI(file.toURI().toString()),
        GexfPackage.eINSTANCE.getNsURI());
    DocumentRoot document = XArchADTProxy.proxy(xarch, documentRef);
    GexfContent gexf = XArchADTProxy.create(xarch, GexfPackage.Literals.GEXF_CONTENT);
    document.setGexf(gexf);
    GraphContent graph = XArchADTProxy.create(xarch, GexfPackage.Literals.GRAPH_CONTENT);
    gexf.setGraph(graph);

    Map<String, Node> nodes = Node.scanForNodes(world, model.getAllThings(), 1);
    Map<String, Edge> edges = Edge.scanForEdges(world, model.getAllThings(), nodes);

    NodesContent nodesc = XArchADTProxy.create(xarch, GexfPackage.Literals.NODES_CONTENT);
    graph.getNodes().add(nodesc);
    EList<NodeContent> xadlNodes = nodesc.getNode();
    for (Node node : Edge.filterNodesByEdges(nodes.values(), edges.values())) {
      IThing t = node.getThing();

      NodeContent xadlNode = XArchADTProxy.create(xarch, GexfPackage.Literals.NODE_CONTENT);
      xadlNode.setId(Node.getUniqueId(node.getThing()));
      xadlNode.setLabel(getText(model, t));

      Point2D p = BNAUtils.getCentralPoint(t);
      PositionContent position = XArchADTProxy.create(xarch, VizPackage.Literals.POSITION_CONTENT);
      position.setX((float) p.getX());
      position.setY((float) -p.getY());
      xadlNode.getPosition().add(position);

      if (t instanceof IHasBoundingBox) {
        Rectangle r = ((IHasBoundingBox) t).getBoundingBox();
        SizeContent size = XArchADTProxy.create(xarch, VizPackage.Literals.SIZE_CONTENT);
        size.setValue((float) Math.sqrt(r.width * r.width / 4 + r.height * r.height / 4));
        xadlNode.getSize().add(size);
      }

      RGB rgb = t.get(IHasColor.COLOR_KEY);
      if (rgb != null) {
        ColorContent color = XArchADTProxy.create(xarch, VizPackage.Literals.COLOR_CONTENT);
        color.setR(BigInteger.valueOf(rgb.red));
        color.setG(BigInteger.valueOf(rgb.green));
        color.setB(BigInteger.valueOf(rgb.blue));
        xadlNode.getColor().add(color);
      }

      xadlNodes.add(xadlNode);
    }

    EdgesContent edgesc = XArchADTProxy.create(xarch, GexfPackage.Literals.EDGES_CONTENT);
    graph.getEdges().add(edgesc);
    EList<EdgeContent> xadlEdges = edgesc.getEdge();
    for (Edge edge : edges.values()) {
      IThing t = edge.getThing();
      Node fromNode = edge.getFromEndpoint().getNode();
      Node toNode = edge.getToEndpoint().getNode();
      int weight = 1;

      EdgeContent xadlEdge = XArchADTProxy.create(xarch, GexfPackage.Literals.EDGE_CONTENT);
      xadlEdge.setId(Node.getUniqueId(t));
      xadlEdge.setSource(Node.getUniqueId(fromNode.getThing()));
      xadlEdge.setTarget(Node.getUniqueId(toNode.getThing()));
      xadlEdge.setWeight(weight);
      xadlEdge.setLabel(getText(model, t));

      xadlEdges.add(xadlEdge);
    }

    Files.write(xarch.serialize(URI.createURI(file.toURI().toString())), file);
  }

  private String getText(IBNAModel model, IThing t) {
    IHasText textThing = Assemblies.getThingOfType(model, t, IHasText.class);
    if (textThing != null) {
      return textThing.getText();
    }
    IThing tooltipThing = Assemblies.getThingWithProperty(model, t, IHasToolTip.TOOL_TIP_KEY);
    if (tooltipThing != null) {
      return tooltipThing.get(IHasToolTip.TOOL_TIP_KEY);
    }
    return "[undefined]";
  }

  protected void importGexf(IBNAView view) throws SAXException, IOException {
    FileDialog fd = new FileDialog(view.getBNAUI().getComposite().getShell(), SWT.OPEN);
    fd.setFilterExtensions(new String[] {"*.gexf"});
    String filepath = fd.open();
    if (filepath != null) {
      importGexf(view, new File(filepath));
    }
  }

  protected void importGexf(IBNAView view, File file) throws SAXException, IOException {
    String contents = new String(Files.toByteArray(file));
    contents = contents.replaceAll("viz:", "");
    contents = contents.replaceAll("http://www.gexf.net/1.1draft", "http://www.gexf.net/1.2draft");
    contents = contents.replaceAll("version=\"1.1\"", "version=\"1.2\"");

    IXArchADT xarch = new XArchADTImpl();
    ObjRef documentRef = xarch.load(URI.createURI(file.toURI().toString()), contents.getBytes());
    DocumentRoot document = XArchADTProxy.proxy(xarch, documentRef);

    // Extract node locations.
    Map<String, Point> positions = Maps.newHashMap();
    float factor = 1f;
    for (NodesContent xadlNodes : document.getGexf().getGraph().getNodes()) {
      for (NodeContent xadlNode : xadlNodes.getNode()) {
        for (PositionContent position : xadlNode.getPosition()) {
          Point p = new Point(SystemUtils.round(factor * position.getX()),
              -SystemUtils.round(factor * position.getY()));
          positions.put(xadlNode.getId().toString(), p);
        }
      }
    }

    // Update BNA model.
    for (IThing t : view.getBNAWorld().getBNAModel().getAllThings()) {
      Point p = positions.get(Node.getUniqueId(t));
      if (p != null) {
        if (t instanceof IHasMutableBoundingBox) {
          Rectangle r = ((IHasMutableBoundingBox) t).getBoundingBox();
          r.x = p.x - r.width / 2;
          r.y = p.y - r.height / 2;
          ((IHasMutableBoundingBox) t).setBoundingBox(r);
        } else if (t instanceof IHasMutableAnchorPoint) {
          ((IHasMutableAnchorPoint) t).setAnchorPoint(BNAUtils.toPoint2D(p));
        } else if (t instanceof IHasMutableReferencePoint) {
          ((IHasMutableReferencePoint) t).setReferencePoint(p);
        }
      }
    }
  }
}
