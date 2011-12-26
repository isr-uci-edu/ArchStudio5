package org.archstudio.graphlayout;

import java.util.ArrayList;
import java.util.List;

import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class GraphLayout implements java.io.Serializable {

	private static final long serialVersionUID = -6699237769430902424L;

	protected List<Node> nodes;
	protected List<Edge> edges;

	public GraphLayout() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}

	public void addNode(Node n) {
		nodes.add(n);
	}

	public int getNumNodes() {
		return nodes.size();
	}

	public Node getNodeAt(int index) {
		return nodes.get(index);
	}

	public void addEdge(Edge e) {
		edges.add(e);
	}

	public int getNumEdges() {
		return edges.size();
	}

	public Edge getEdgeAt(int index) {
		return edges.get(index);
	}

	public static class Node {
		protected String nodeId;
		protected Rectangle bounds;

		protected List<AbstractPort> ports;

		public Node() {
			this.nodeId = null;
			this.bounds = null;

			ports = new ArrayList<AbstractPort>();
		}

		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

		public String getNodeId() {
			return nodeId;
		}

		public void setBounds(Rectangle bounds) {
			this.bounds = bounds;
		}

		public Rectangle getBounds() {
			return bounds;
		}

		public void addPort(AbstractPort p) {
			ports.add(p);
		}

		public AbstractPort[] getAllPorts() {
			return ports.toArray(new AbstractPort[0]);
		}
	}

	public static abstract class AbstractPort {
		protected String id;

		public AbstractPort(String id) {
			this.id = id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}
	}

	public static class PlainPort extends AbstractPort {
		public PlainPort(String id) {
			super(id);
		}
	}

	public static class CompassPort extends AbstractPort {
		protected Orientation direction;

		public CompassPort(String id, Orientation direction) {
			super(id);
			this.direction = direction;
		}

		public void setDirection(Orientation direction) {
			this.direction = direction;
		}

		public Orientation getDirection() {
			return direction;
		}
	}

	public static class Edge {
		protected String edgeId;
		protected List<Point> points = new ArrayList<Point>();

		protected String node1Id, port1Id;
		protected String node2Id, port2Id;

		public Edge() {
		}

		public Edge(Point[] pointArray) {
			for (Point element : pointArray) {
				points.add(element);
			}
		}

		public void setEndpoint1(String nodeId, String portId) {
			this.node1Id = nodeId;
			this.port1Id = portId;
		}

		public void setEndpoint2(String nodeId, String portId) {
			this.node2Id = nodeId;
			this.port2Id = portId;
		}

		public String getNode1Id() {
			return node1Id;
		}

		public String getNode2Id() {
			return node2Id;
		}

		public String getPort1Id() {
			return port1Id;
		}

		public String getPort2Id() {
			return port2Id;
		}

		public void addPoint(Point p) {
			points.add(p);
		}

		public int getNumPoints() {
			return points.size();
		}

		public Point getPointAt(int i) {
			return points.get(i);
		}

		public String getEdgeId() {
			return edgeId;
		}

		public void setEdgeId(String string) {
			edgeId = string;
		}

	}
}
