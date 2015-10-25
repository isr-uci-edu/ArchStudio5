package org.archstudio.utils.bna.dot;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.Orientation;

public class Endpoint {
  private Node node;
  private IThing port;
  private Orientation orientation;

  public Endpoint(Node node, IThing port, Orientation orientation) {
    this.node = node;
    this.port = port;
    this.orientation = orientation;
  }

  public String toDotID() {
    StringBuilder sb = new StringBuilder();
    sb.append(Node.getUniqueId(node.getThing()));
    if (port != null) {
      sb.append(":" + Node.getUniqueId(port));
      if (orientation != null) {
        sb.append(":" + toDotOrientation(orientation));
      }
    }
    return sb.toString();
  }

  private String toDotOrientation(Orientation orientation) {
    switch (orientation) {
      case NORTH:
        return "n";
      case NORTHEAST:
        return "ne";
      case EAST:
        return "e";
      case SOUTHEAST:
        return "se";
      case SOUTH:
        return "s";
      case SOUTHWEST:
        return "sw";
      case WEST:
        return "w";
      case NORTHWEST:
        return "nw";
      default:
        return "c";
    }
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }

  public IThing getPort() {
    return port;
  }

  public void setPort(IThing port) {
    this.port = port;
  }

  public Orientation getOrientation() {
    return orientation;
  }

  public void setOrientation(Orientation orientation) {
    this.orientation = orientation;
  }
}
