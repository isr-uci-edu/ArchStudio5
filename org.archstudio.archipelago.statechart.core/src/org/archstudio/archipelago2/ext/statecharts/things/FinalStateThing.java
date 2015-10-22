package org.archstudio.archipelago2.ext.statecharts.things;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public class FinalStateThing extends FinalStateThingBase {

  public FinalStateThing(@Nullable Object id) {
    super(id);
  }

  @Override
  public Point getReferencePoint() {
    Rectangle r = getBoundingBox();
    return new Point(r.x + r.width / 2, r.y + r.height / 2);
  }

  @Override
  public void setReferencePoint(Point value) {
    Point oldReferencePoint = getReferencePoint();
    Rectangle r = getBoundingBox();
    r.x += value.x - oldReferencePoint.x;
    r.y += value.y - oldReferencePoint.y;
    setBoundingBox(r);
  }

  @Override
  public boolean shouldIncrementRotatingOffset() {
    return isSelected();
  }

  @Override
  public Shape getStickyShape() {
    Rectangle r = getBoundingBox();
    return new Ellipse2D.Double(r.x, r.y, r.width, r.height);
  }

}
