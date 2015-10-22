package org.archstudio.archipelago2.ext.structures.logics;

import java.awt.Dimension;

import org.archstudio.archipelago2.ext.structures.StructurePreferences;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.things.labels.BoundedLabelThing;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.eclipse.swt.graphics.FontData;

/**
 * Maps xADL components to BNA Rectangle Assemblies.
 */
public class MapConnectorLogic extends MapBrickLogic {
  public MapConnectorLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath,
      String mappingDescription) {
    super(world, xarch, rootObjRef, objRefPath, 1, new Dimension(240, 48), mappingDescription);
  }

  @Override
  public void applyDefaults(RectangleThing thing) {
    super.applyDefaults(thing);
    thing.setColor(StructurePreferences.getConnectorColor());
  }

  @Override
  public void applyPreferences(RectangleThing thing) {
    super.applyPreferences(thing);
    BoundedLabelThing label = Assemblies.BOUNDED_TEXT_KEY.get(thing, model);
    FontData labelFont = StructurePreferences.getConnectorFont();
    label.setFontName(labelFont.getName());
    label.setFontSize(labelFont.getHeight());
    label.setFontStyle(FontStyle.fromSWT(labelFont.getStyle()));
  }
}
