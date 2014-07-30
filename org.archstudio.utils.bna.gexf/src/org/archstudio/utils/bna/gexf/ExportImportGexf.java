package org.archstudio.utils.bna.gexf;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.archstudio.xarchadt.core.XArchADTImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.FileDialog;
import org.xml.sax.SAXException;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

public class ExportImportGexf extends AbstractThingLogic implements IBNAMenuListener {

	public ExportImportGexf(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void fillMenu(final IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		BNAUtils.checkLock();

		if (things.size() == 0) {
			menu.add(new BNAAction("Export GEXF") {
				@Override
				public void runWithLock() {
					try {
						exportGexf(view);
					}
					catch (Exception e) {
						e.printStackTrace();
						MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error", e.getMessage());
					}
				}
			});
			menu.add(new BNAAction("Import GEXF") {
				@Override
				public void runWithLock() {
					try {
						importGexf(view);
					}
					catch (Exception e) {
						e.printStackTrace();
						MessageDialog.openError(view.getBNAUI().getComposite().getShell(), "Error", e.getMessage());
					}
				}
			});
		}
		else {
			menu.add(SWTWidgetUtils.createNoAction("Export GEXF"));
			menu.add(SWTWidgetUtils.createNoAction("Import GEXF"));
		}
	}

	protected void exportGexf(IBNAView view) throws IOException {
		FileDialog fd = new FileDialog(view.getBNAUI().getComposite().getShell(), SWT.SAVE);
		fd.setFilterExtensions(new String[] { "*.gexf" });
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

		IBNAModel model = view.getBNAWorld().getBNAModel();
		Map<Object, NodeContent> nodes = Maps.newHashMap();
		Map<Object, EdgeContent> edges = Maps.newHashMap();

		// extract nodes
		for (IThing t : model.getAllThings()) {
			if (isNodeOfInterest(model, t)) {
				NodeContent node = XArchADTProxy.create(xarch, GexfPackage.Literals.NODE_CONTENT);
				node.setId("thing:" + t.getID());
				node.setLabel(getText(model, t));

				Point2D p = BNAUtils.getCentralPoint(t);
				PositionContent position = XArchADTProxy.create(xarch, VizPackage.Literals.POSITION_CONTENT);
				position.setX((float) p.getX());
				position.setY((float) -p.getY());
				node.getPosition().add(position);

				if (t instanceof IHasBoundingBox) {
					Rectangle r = ((IHasBoundingBox) t).getBoundingBox();
					SizeContent size = XArchADTProxy.create(xarch, VizPackage.Literals.SIZE_CONTENT);
					size.setValue((float) Math.sqrt(r.width * r.width / 4 + r.height * r.height / 4));
					node.getSize().add(size);
				}

				RGB rgb = t.get(IHasColor.COLOR_KEY);
				if (rgb != null) {
					ColorContent color = XArchADTProxy.create(xarch, VizPackage.Literals.COLOR_CONTENT);
					color.setR(BigInteger.valueOf(rgb.red));
					color.setG(BigInteger.valueOf(rgb.green));
					color.setB(BigInteger.valueOf(rgb.blue));
					node.getColor().add(color);
				}

				nodes.put(t.getID(), node);
			}
		}

		// extract edges
		StickPointLogic spl = view.getBNAWorld().getThingLogicManager().addThingLogic(StickPointLogic.class);
		for (IHasEndpoints t : Iterables.filter(model.getAllThings(), IHasEndpoints.class)) {
			IThing node1 = getNodeFromEdgeEndpoint(model, t, IHasEndpoints.ENDPOINT_1_KEY, spl);
			IThing node2 = getNodeFromEdgeEndpoint(model, t, IHasEndpoints.ENDPOINT_2_KEY, spl);
			if (node1 != null && node2 != null && nodes.containsKey(node1.getID()) && nodes.containsKey(node2.getID())) {
				int weight = getEdgeWeight(model, t, node1, node2);
				if (weight != 0) {
					EdgeContent edge = XArchADTProxy.create(xarch, GexfPackage.Literals.EDGE_CONTENT);
					edge.setId("thing:" + t.getID());
					edge.setSource(nodes.get(node1.getID()).getId());
					edge.setTarget(nodes.get(node2.getID()).getId());
					edge.setWeight(weight);
					edge.setLabel(getText(model, t));
					edges.put(t.getID(), edge);
				}
			}
		}

		// pull it together
		GexfContent gexf = XArchADTProxy.create(xarch, GexfPackage.Literals.GEXF_CONTENT);
		document.setGexf(gexf);
		GraphContent graph = XArchADTProxy.create(xarch, GexfPackage.Literals.GRAPH_CONTENT);
		gexf.setGraph(graph);
		NodesContent nodesc = XArchADTProxy.create(xarch, GexfPackage.Literals.NODES_CONTENT);
		nodesc.getNode().addAll(nodes.values());
		graph.getNodes().add(nodesc);
		EdgesContent edgesc = XArchADTProxy.create(xarch, GexfPackage.Literals.EDGES_CONTENT);
		edgesc.getEdge().addAll(edges.values());
		graph.getEdges().add(edgesc);

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

	protected boolean isNodeOfInterest(IBNAModel model, IThing node) {
		return node instanceof IHasBoundingBox && Assemblies.isRoot(node);
	}

	protected IThing getNodeFromEdgeEndpoint(IBNAModel model, IThing edge, IThingKey<Point2D> endpointKey,
			StickPointLogic spl) {
		return Assemblies.getRoot(model, model.getThing(spl.getStickyThingID(edge, endpointKey)));
	}

	protected int getEdgeWeight(IBNAModel model, IHasEndpoints edge, IThing node1, IThing node2) {
		return 1;
	}

	protected void importGexf(IBNAView view) throws SAXException, IOException {
		FileDialog fd = new FileDialog(view.getBNAUI().getComposite().getShell(), SWT.OPEN);
		fd.setFilterExtensions(new String[] { "*.gexf" });
		String filepath = fd.open();
		if (filepath != null) {
			importGexf(view, new File(filepath));
		}
	}

	protected void importGexf(IBNAView view, File file) throws SAXException, IOException {
		IXArchADT xarch = new XArchADTImpl();

		String contents = new String(Files.toByteArray(file));
		contents = contents.replaceAll("viz:", "");

		ObjRef documentRef = xarch.load(URI.createURI(file.toURI().toString()), contents.getBytes());
		DocumentRoot document = XArchADTProxy.proxy(xarch, documentRef);

		IBNAModel model = view.getBNAWorld().getBNAModel();

		// determine node locations
		Map<Integer, Point> positions = Maps.newHashMap();
		for (NodesContent nodes : document.getGexf().getGraph().getNodes()) {
			for (NodeContent node : nodes.getNode()) {
				for (PositionContent position : node.getPosition()) {
					int id = Integer.parseInt(node.getId().toString().substring("thing:".length()));
					float factor = 10f;
					Point p = new Point(SystemUtils.round(factor * position.getX()), -SystemUtils.round(factor
							* position.getY()));
					positions.put(id, p);
				}
			}
		}

		// restore locations
		for (IThing t : model.getAllThings()) {
			Point p = positions.get(t.getID());
			if (p != null) {
				if (t instanceof IHasMutableReferencePoint) {
					((IHasMutableReferencePoint) t).setReferencePoint(p);
				}
			}
		}
	}
}
