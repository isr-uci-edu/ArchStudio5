package org.archstudio.utils.bna.dot;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.GridUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Node {
  public static final DecimalFormat SIMPLE_DECIMAL_FORMATTER;

  static {
    SIMPLE_DECIMAL_FORMATTER =
        new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    // 340 = DecimalFormat.DOUBLE_FRACTION_DIGITS
    SIMPLE_DECIMAL_FORMATTER.setMaximumFractionDigits(340);
  }

  private static long idCounter = 0;
  private static final IThingKey<String> UID_KEY =
      ThingKey.create(Lists.newArrayList("uid", Endpoint.class));

  public static final String getUniqueId(IThing thing) {
    String uid = thing.get(UID_KEY);
    if (uid == null) {
      thing.set(UID_KEY, uid = "uid_" + ++idCounter);
    }
    return uid;
  }

  public static final IThing getThingFromUniqueId(IBNAWorld world, String uid) {
    ThingValueTrackingLogic valueLogic =
        world.getThingLogicManager().getThingLogic(ThingValueTrackingLogic.class);
    for (IThing thing : valueLogic.getThings(UID_KEY, uid)) {
      return thing;
    }
    return null;
  }

  public static Map<String, Node> scanForNodes(IBNAWorld world, Iterable<? extends IThing> things,
      double scale) {
    final Map<String, Node> nodes = new HashMap<>();
    for (IThing t : things) {
      if (UserEditableUtils.isEditableForAnyQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
        IThing base = Assemblies.getRoot(world.getBNAModel(), t);
        String uid = getUniqueId(base);
        Node node = new Node(base, world);
        node.setScale(scale);
        nodes.put(uid, node);
      }
    }
    return nodes;
  }

  private final IThing thing;
  private final IBNAWorld world;
  private double scale = 1d;

  public Node(IThing thing, IBNAWorld world) {
    this.thing = Preconditions.checkNotNull(thing);
    this.world = Preconditions.checkNotNull(world);
  }

  public IThing getThing() {
    return thing;
  }

  public double getScale() {
    return scale;
  }

  public void setScale(double scale) {
    this.scale = scale;
  }

  public String toDotNode() {
    StringBuilder sb = new StringBuilder();
    Map<String, String> attributes = new HashMap<>();
    attributes.put("label", "\"\"");
    if (thing instanceof IHasBoundingBox) {
      Rectangle r = ((IHasBoundingBox) thing).getBoundingBox();
      if (r.height == 0 || r.width == 0) {
        return "";
      }
      attributes.put("shape", "box");
      attributes.put("fixedsize", "true");
      attributes.put("height", "" + SIMPLE_DECIMAL_FORMATTER
          .format((r.height + 2 * GridUtils.getGridSpacing(world)) * scale));
      attributes.put("width", "" + SIMPLE_DECIMAL_FORMATTER
          .format((r.width + 2 * GridUtils.getGridSpacing(world)) * scale));
      attributes.put("pos", "\"" + SIMPLE_DECIMAL_FORMATTER.format((r.x + r.width / 2d) * scale)
          + "," + SIMPLE_DECIMAL_FORMATTER.format((r.y + r.height / 2d) * scale) + "\"");
    } else if (thing instanceof IHasAnchorPoint) {
      Point2D p = ((IHasAnchorPoint) thing).getAnchorPoint();
      attributes.put("pos", "\"" + SIMPLE_DECIMAL_FORMATTER.format(p.getX() * scale) + ","
          + SIMPLE_DECIMAL_FORMATTER.format(p.getY() * scale) + "\"");
    } else if (thing instanceof IHasReferencePoint) {
      Point p = ((IHasReferencePoint) thing).getReferencePoint();
      attributes.put("pos", "\"" + SIMPLE_DECIMAL_FORMATTER.format(p.x * scale) + ","
          + SIMPLE_DECIMAL_FORMATTER.format(p.y * scale) + "\"");
    }
    if (thing instanceof IHasBoundingBox) {
      sb.append(getUniqueId(thing));
      if (!attributes.isEmpty()) {
        sb.append(" [");
        sb.append(Joiner.on(", ").join(Iterables.transform(attributes.entrySet(),
            new Function<Map.Entry<String, String>, String>() {
              @Override
              public String apply(Entry<String, String> input) {
                return "" + input.getKey() + "=" + input.getValue();
              }
            })));
        sb.append("]");
      }
      sb.append(";\n");
    }
    return sb.toString();
  }
}
