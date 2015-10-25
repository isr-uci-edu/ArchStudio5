package org.archstudio.utils.bna.dot;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasArrowheads;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class Edge {
  public static final Map<String, Edge> scanForEdges(IBNAWorld world,
      Iterable<? extends IThing> things, Map<String, ? extends Node> nodes) {
    Map<String, Edge> edges = new HashMap<>();
    for (IHasEndpoints t : Iterables.filter(things, IHasEndpoints.class)) {
      Endpoint endpoint1 = getEndpoint(world, t, IHasEndpoints.ENDPOINT_1_KEY, nodes);
      Endpoint endpoint2 = getEndpoint(world, t, IHasEndpoints.ENDPOINT_2_KEY, nodes);
      if (endpoint1 != null && endpoint2 != null) {
        if (t instanceof IHasArrowheads) {
          IHasArrowheads arrowheads = (IHasArrowheads) t;
          if (isPointed(arrowheads, 2)) {
            if (!isPointed(arrowheads, 1)) {
              edges.put(Node.getUniqueId(t), new Edge(t, endpoint1, endpoint2));
              continue;
            }
          } else if (isPointed(arrowheads, 1)) {
            edges.put(Node.getUniqueId(t), new Edge(t, endpoint2, endpoint1));
            continue;
          }
        }
        if (t.getEndpoint1().getY() < t.getEndpoint2().getY()) {
          edges.put(Node.getUniqueId(t), new Edge(t, endpoint1, endpoint2));
          continue;
        }
        edges.put(Node.getUniqueId(t), new Edge(t, endpoint2, endpoint1));
      }
    }
    return edges;
  }

  public static Iterable<Node> filterNodesByEdges(Iterable<Node> nodes, Iterable<Edge> edges) {
    final Set<String> referencedNodes = new HashSet<>();
    for (Edge edge : edges) {
      referencedNodes.add(Node.getUniqueId(edge.getFromEndpoint().getNode().getThing()));
      referencedNodes.add(Node.getUniqueId(edge.getToEndpoint().getNode().getThing()));
    }
    return Iterables.filter(nodes, new Predicate<Node>() {
      @Override
      public boolean apply(Node node) {
        return referencedNodes.contains(Node.getUniqueId(node.getThing()));
      }
    });
  }

  private static final boolean isPointed(IHasArrowheads arrowheads, int endpoint) {
    if (endpoint == 1) {
      return arrowheads.getArrowhead1Color() != null
          && arrowheads.getArrowhead1Shape() != ArrowheadShape.NONE;
    } else if (endpoint == 2) {
      return arrowheads.getArrowhead2Color() != null
          && arrowheads.getArrowhead2Shape() != ArrowheadShape.NONE;
    } else {
      throw new IllegalArgumentException("Invalid endpoint: " + endpoint);
    }
  }

  private static final Endpoint getEndpoint(IBNAWorld world, IThing thing,
      IThingKey<Point2D> pointKey, Map<String, ? extends Node> nodes) {
    StickPointLogic stickLogic = world.getThingLogicManager().getThingLogic(StickPointLogic.class);
    IThing stickyThing = stickLogic.getStickyThing(thing, pointKey);
    if (stickyThing != null) {
      IThing stickyAnchoredThing =
          stickLogic.getStickyThing(stickyThing, IHasAnchorPoint.ANCHOR_POINT_KEY);
      if (stickyAnchoredThing == null) {
        Node node = nodes.get(Node.getUniqueId(stickyThing));
        if (node == null) {
          return null;
        }
        return new Endpoint(node, null, null);
      }
      Node anchoredNode = nodes.get(Node.getUniqueId(stickyAnchoredThing));
      if (anchoredNode == null) {
        Node node = nodes.get(Node.getUniqueId(stickyThing));
        if (node == null) {
          return null;
        }
        return new Endpoint(node, null, null);
      }
      Orientation o = null;
      if (stickyAnchoredThing instanceof IHasBoundingBox) {
        Point a = BNAUtils.toPoint(((IHasAnchorPoint) stickyThing).getAnchorPoint());
        Rectangle r = ((IHasBoundingBox) stickyAnchoredThing).getBoundingBox();
        int A = 3;
        int B = 7;
        Point tl = new Point(r.x + A * r.width / B, r.y + A * r.height / B);
        Point br = new Point(r.x + (B - A) * r.width / B, r.y + (B - A) * r.height / B);
        if (a.x < tl.x) {
          if (a.y < tl.y) {
            o = Orientation.NORTHWEST;
          } else if (a.y > br.y) {
            o = Orientation.SOUTHWEST;
          } else {
            o = Orientation.WEST;
          }
        } else if (a.x > br.x) {
          if (a.y < tl.y) {
            o = Orientation.NORTHEAST;
          } else if (a.y > br.y) {
            o = Orientation.SOUTHEAST;
          } else {
            o = Orientation.EAST;
          }
        } else {
          if (a.y < tl.y) {
            o = Orientation.NORTH;
          } else if (a.y > br.y) {
            o = Orientation.SOUTH;
          } else {
            o = Orientation.NONE;
          }
        }
      }
      return new Endpoint(anchoredNode, stickyThing, o);
    }
    return null;
  }

  private final IHasEndpoints thing;
  private final Endpoint fromEndpoint;
  private final Endpoint toEndpoint;

  public Edge(IHasEndpoints thing, Endpoint fromEndpoint, Endpoint toEndpoint) {
    this.thing = Preconditions.checkNotNull(thing);
    this.fromEndpoint = Preconditions.checkNotNull(fromEndpoint);
    this.toEndpoint = Preconditions.checkNotNull(toEndpoint);
  }

  public IHasEndpoints getThing() {
    return thing;
  }

  public Endpoint getFromEndpoint() {
    return fromEndpoint;
  }

  public Endpoint getToEndpoint() {
    return toEndpoint;
  }

  public String toDotEdge() {
    return fromEndpoint.toDotID() + " -> " + toEndpoint.toDotID() + ";\n";
  }
}
