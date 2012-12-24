package org.archstudio.graphlayout.core.graphviz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

import org.archstudio.graphlayout.GraphLayout;
import org.archstudio.graphlayout.GraphLayoutException;
import org.archstudio.graphlayout.GraphLayoutParameters;
import org.archstudio.graphlayout.core.AliasTable;
import org.archstudio.graphlayout.core.ILayoutEngine;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.xadl.XadlUtils;
import org.archstudio.xadl3.domain_3_0.DomainType;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Iterables;

public abstract class AbstractGraphvizLayoutEngine implements ILayoutEngine {

	public static final String EOL = System.getProperty("line.separator");

	@Override
	public GraphLayout layoutGraph(IXArchADT xarch, IPreferenceStore prefs, ObjRef rootRef, GraphLayoutParameters params)
			throws GraphLayoutException {
		StringBuffer sb = new StringBuffer();
		AliasTable at = new AliasTable();
		createGraph(sb, at, xarch, rootRef, params);
		String output = runLayoutTool(xarch, prefs, sb.toString());
		//System.err.println(sb.toString());
		GraphLayout gl = processOutput(at, xarch, rootRef, params, output);
		return gl;
	}

	protected abstract String runLayoutTool(IXArchADT xarch, IPreferenceStore prefs, String toolInput)
			throws GraphLayoutException;

	protected void createGraph(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef rootRef,
			GraphLayoutParameters params) throws GraphLayoutException {
		if (rootRef == null) {
			throw new IllegalArgumentException("Null root reference in graph layout.");
		}

		if (!XadlUtils.isInstanceOf(xarch, rootRef, Structure_3_0Package.Literals.STRUCTURE)) {
			throw new GraphLayoutException("Invalid root reference in graph layout; must be a Structure");
		}

		sb.append("digraph arch {" + EOL);
		createGraphParameters(sb, at, xarch, rootRef, params);
		sb.append(EOL);
		createBricks(sb, at, xarch, rootRef, params);
		sb.append(EOL);
		createLinks(sb, at, xarch, rootRef, params);
		sb.append(EOL);
		sb.append("}" + EOL);
	}

	protected void createGraphParameters(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef rootRef,
			GraphLayoutParameters params) throws GraphLayoutException {
		Object o = params.getProperty("nodeSep");
		if (o != null && o instanceof Double) {
			sb.append("  nodesep = ");
			sb.append(((Double) o).toString());
			sb.append(";");
			sb.append(EOL);
		}

		o = params.getProperty("rankSep");
		if (o != null && o instanceof Double) {
			sb.append("  ranksep = ");
			sb.append(((Double) o).toString());
			sb.append(";");
			sb.append(EOL);
		}
	}

	protected void createBricks(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef rootRef,
			GraphLayoutParameters params) throws GraphLayoutException {
		for (ObjRef componentRef : Iterables.filter(xarch.getAll(rootRef, "component"), ObjRef.class)) {
			createBrick(sb, at, xarch, componentRef, params, true);
		}
		sb.append(EOL);
		for (ObjRef connectorRef : Iterables.filter(xarch.getAll(rootRef, "connector"), ObjRef.class)) {
			createBrick(sb, at, xarch, connectorRef, params, false);
		}
	}

	protected void createBrick(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef brickRef,
			GraphLayoutParameters params, boolean isComponent) throws GraphLayoutException {
		double rw = isComponent ? params.getRelativeComponentWidth() : params.getRelativeConnectorWidth();
		double rh = isComponent ? params.getRelativeComponentHeight() : params.getRelativeConnectorHeight();

		String brickXArchID = XadlUtils.getID(xarch, brickRef);
		if (brickXArchID != null) {
			String brickAlias = at.getAlias(brickXArchID);
			sb.append("  ");
			sb.append(brickAlias);
			sb.append(" ");
			if (shouldOrientInterfaces(params) && hasOrientedInterface(xarch, brickRef)) {
				//the brick will be a quartered record
				sb.append("[shape=record,width=");
				sb.append(rw);
				sb.append(",height=");
				sb.append(rh);
				sb.append(",label=\"{<__n> | { <__w> | <__c> | <__e> } | <__s>}\"");
			}
			else {
				//The brick will be a box.
				sb.append("[shape=box,width=");
				sb.append(rw);
				sb.append(",height=");
				sb.append(rh);
			}
			sb.append("];");
			sb.append(EOL);
		}
	}

	protected void createLinks(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef rootRef,
			GraphLayoutParameters params) throws GraphLayoutException {
		for (ObjRef linkRef : Iterables.filter(xarch.getAll(rootRef, "link"), ObjRef.class)) {
			createLink(sb, at, xarch, linkRef, params);
		}
	}

	protected void createLink(StringBuffer sb, AliasTable at, IXArchADT xarch, ObjRef linkRef,
			GraphLayoutParameters params) throws GraphLayoutException {
		String linkXArchID = XadlUtils.getID(xarch, linkRef);
		if (linkXArchID == null) {
			return;
		}

		ObjRef linkEndpoint1Target = (ObjRef) xarch.get(linkRef, "point1");
		if (linkEndpoint1Target == null) {
			return;
		}
		ObjRef linkEndpoint1Parent = xarch.getParent(linkEndpoint1Target);
		if (linkEndpoint1Parent == null) {
			return;
		}
		if (!XadlUtils.isInstanceOf(xarch, linkEndpoint1Parent, Structure_3_0Package.Literals.BRICK)) {
			return;
		}
		String linkEndpoint1ParentXArchID = XadlUtils.getID(xarch, linkEndpoint1Parent);
		if (linkEndpoint1ParentXArchID == null) {
			return;
		}

		ObjRef linkEndpoint2Target = (ObjRef) xarch.get(linkRef, "point2");
		if (linkEndpoint2Target == null) {
			return;
		}
		ObjRef linkEndpoint2Parent = xarch.getParent(linkEndpoint2Target);
		if (linkEndpoint2Parent == null) {
			return;
		}
		if (!XadlUtils.isInstanceOf(xarch, linkEndpoint2Parent, Structure_3_0Package.Literals.BRICK)) {
			return;
		}
		String linkEndpoint2ParentXArchID = XadlUtils.getID(xarch, linkEndpoint2Parent);
		if (linkEndpoint2ParentXArchID == null) {
			return;
		}

		//OK, the link is valid.
		sb.append("  ");
		sb.append(at.getAlias(linkEndpoint1ParentXArchID));

		if (shouldOrientInterfaces(params) && hasOrientedInterface(xarch, linkEndpoint1Parent)) {
			//The brick is quartered.
			Orientation linkEndpoint1Orientation = guessInterfaceOrientation(xarch, linkEndpoint1Target);
			switch (linkEndpoint1Orientation) {
			case NORTH:
				sb.append(":__n");
				break;
			case EAST:
				sb.append(":__e");
				break;
			case SOUTH:
				sb.append(":__s");
				break;
			case WEST:
				sb.append(":__w");
				break;
			default:
				sb.append(":__c");
			}
		}

		sb.append(" -> ");

		sb.append(at.getAlias(linkEndpoint2ParentXArchID));

		if (shouldOrientInterfaces(params) && hasOrientedInterface(xarch, linkEndpoint2Parent)) {
			//The brick is quartered.
			Orientation linkEndpoint2Orientation = guessInterfaceOrientation(xarch, linkEndpoint2Target);
			switch (linkEndpoint2Orientation) {
			case NORTH:
				sb.append(":__n");
				break;
			case EAST:
				sb.append(":__e");
				break;
			case SOUTH:
				sb.append(":__s");
				break;
			case WEST:
				sb.append(":__w");
				break;
			default:
				sb.append(":__c");
			}
		}

		sb.append(" [label=\"");
		sb.append(at.getAlias(linkXArchID));
		sb.append("\"];");

		sb.append(EOL);
	}

	//------------

	public GraphLayout processOutput(AliasTable at, IXArchADT xarch, ObjRef rootRef, GraphLayoutParameters params,
			String output) throws GraphLayoutException {
		try {
			GraphLayout gl = new GraphLayout();

			BufferedReader br = new BufferedReader(new StringReader(output));

			double scale = params.getScale();
			while (true) {
				String line = br.readLine().trim();
				if (line.startsWith("stop")) {
					return gl;
				}
				else if (line.startsWith("node")) {
					StringTokenizer tok = new StringTokenizer(line);
					@SuppressWarnings("unused")
					String nodeToken = tok.nextToken();
					String eltToken = tok.nextToken();
					String xToken = tok.nextToken();
					String yToken = tok.nextToken();
					String widthToken = tok.nextToken();
					String heightToken = tok.nextToken();

					GraphLayout.Node node = new GraphLayout.Node();
					node.setNodeId(at.getTruename(eltToken));

					double xd = Double.parseDouble(xToken);
					double yd = Double.parseDouble(yToken);
					double widthd = Double.parseDouble(widthToken);
					double heightd = Double.parseDouble(heightToken);

					//We want a bounds rectangle, (xd,yd) is the CENTER
					//of the box, so we have to offset it by half the width
					//and half the height to get the UL coordinate.

					xd -= widthd / 2.0d;
					yd -= heightd / 2.0d;

					xd *= scale;
					int x = (int) Math.round(xd);

					yd *= scale;
					int y = (int) Math.round(yd);

					widthd *= scale;
					int width = (int) Math.round(widthd);

					heightd *= scale;
					int height = (int) Math.round(heightd);

					Rectangle bounds = new Rectangle(x, y, width, height);
					node.setBounds(bounds);

					gl.addNode(node);
				}
				else if (line.startsWith("edge")) {
					StringTokenizer tok = new StringTokenizer(line);
					@SuppressWarnings("unused")
					String edgeToken = tok.nextToken();
					String endpt1Token = tok.nextToken();
					String endpt2Token = tok.nextToken();
					String numPointsToken = tok.nextToken();
					int numPoints = Integer.parseInt(numPointsToken);

					String[] xTokens = new String[numPoints];
					String[] yTokens = new String[numPoints];

					for (int i = 0; i < numPoints; i++) {
						xTokens[i] = tok.nextToken();
						yTokens[i] = tok.nextToken();
					}

					String edgeIdToken = tok.nextToken();

					GraphLayout.Edge edge = new GraphLayout.Edge();

					edge.setEdgeId(at.getTruename(edgeIdToken));

					int colon1Index = endpt1Token.indexOf(":");
					String node1Token = null;
					String port1Token = null;

					if (colon1Index != -1) {
						node1Token = endpt1Token.substring(0, colon1Index);
						port1Token = endpt1Token.substring(colon1Index + 1);
					}
					/*
					 * else{ int secondEIndex = endpt1Token.indexOf("e", 1);
					 * node1Token = endpt1Token.substring(0, secondEIndex);
					 * port1Token = endpt1Token.substring(secondEIndex); }
					 */

					int colon2Index = endpt2Token.indexOf(":");
					String node2Token = null;
					String port2Token = null;

					if (colon2Index != -1) {
						node2Token = endpt2Token.substring(0, colon2Index);
						port2Token = endpt2Token.substring(colon2Index + 1);
					}
					/*
					 * else{ int secondEIndex = endpt2Token.indexOf("e", 1);
					 * node2Token = endpt2Token.substring(0, secondEIndex);
					 * port2Token = endpt2Token.substring(secondEIndex); }
					 */
					edge.setEndpoint1(at.getTruename(node1Token), at.getTruename(port1Token));
					edge.setEndpoint2(at.getTruename(node2Token), at.getTruename(port2Token));

					for (int i = 0; i < numPoints; i++) {
						double xd = Double.parseDouble(xTokens[i]);
						xd *= scale;
						int x = (int) Math.round(xd);

						double yd = Double.parseDouble(yTokens[i]);
						yd *= scale;
						int y = (int) Math.round(yd);

						Point p = new Point(x, y);
						edge.addPoint(p);
					}

					gl.addEdge(edge);
				}
			}
		}
		catch (IOException e) {
			throw new GraphLayoutException("This shouldn't happen.");
		}
		catch (Exception e2) {
			throw new GraphLayoutException("Exception while parsing Graphviz output", e2);
		}
	}

	//------------

	protected static boolean hasOrientedInterface(IXArchADT xarch, ObjRef brickRef) {
		for (ObjRef interfaceRef : Iterables.filter(xarch.getAll(brickRef, "interface"), ObjRef.class)) {
			Orientation o = guessInterfaceOrientation(xarch, interfaceRef);
			if (o != null && !o.equals(Orientation.NONE)) {
				return true;
			}
		}
		return false;
	}

	protected static Orientation guessInterfaceOrientation(IXArchADT xarch, ObjRef interfaceRef) {
		// Check the domain
		for (ObjRef extRef : Iterables.filter(xarch.getAll(interfaceRef, "ext"), ObjRef.class)) {
			if (XadlUtils.isInstanceOf(xarch, extRef, Domain_3_0Package.Literals.DOMAIN_EXTENSION)) {
				ObjRef domainRef = (ObjRef) xarch.get(extRef, "domain");
				if (domainRef != null) {
					DomainType domainType = (DomainType) xarch.get(domainRef, "type");
					if (domainType.equals(DomainType.TOP)) {
						return Orientation.NORTH;
					}
					else if (domainType.equals(DomainType.BOTTOM)) {
						return Orientation.SOUTH;
					}
				}
			}
		}

		//Try to guess based on the name.
		String name = XadlUtils.getName(xarch, interfaceRef);
		if (name == null) {
			return Orientation.NONE;
		}

		name = name.toLowerCase();
		if (name.indexOf("top") != -1) {
			return Orientation.NORTH;
		}
		if (name.indexOf("bottom") != -1) {
			return Orientation.SOUTH;
		}
		if (name.indexOf("left") != -1) {
			return Orientation.WEST;
		}
		if (name.indexOf("right") != -1) {
			return Orientation.EAST;
		}
		if (name.indexOf("peer") != -1) {
			return Orientation.EAST;
		}
		if (name.indexOf("north") != -1) {
			return Orientation.NORTH;
		}
		if (name.indexOf("east") != -1) {
			return Orientation.EAST;
		}
		if (name.indexOf("west") != -1) {
			return Orientation.WEST;
		}
		if (name.indexOf("south") != -1) {
			return Orientation.SOUTH;
		}
		return Orientation.NONE;
	}

	protected static boolean shouldOrientInterfaces(GraphLayoutParameters params) {
		Object o = params.getProperty("orientInterfaces");
		if (o == null) {
			return false;
		}
		if (o instanceof Boolean) {
			Boolean b = (Boolean) o;
			return b.booleanValue();
		}
		return false;
	}
}
