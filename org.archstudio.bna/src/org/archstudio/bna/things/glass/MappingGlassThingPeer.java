package org.archstudio.bna.things.glass;

import static org.archstudio.sysutils.SystemUtils.castOrNull;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IResources;
import org.archstudio.bna.things.utility.WorldThing;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;

public class MappingGlassThingPeer<T extends MappingGlassThing> extends SplineGlassThingPeer<T> {

	public MappingGlassThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, ICoordinateMapper cm, Graphics g, IResources r) {

		// update endpoint 2
		WorldThing worldThing = castOrNull(MappingGlassThing.WORLD_REF_KEY.get(t, view.getBNAWorld().getBNAModel()),
				WorldThing.class);
		if (worldThing != null) {
			WorldThingPeer<WorldThing> worldThingPeer = (WorldThingPeer<WorldThing>) view.getThingPeer(worldThing);
			if (worldThingPeer != null) {
				IBNAView internalView = worldThingPeer.getInnerView();
				if (internalView != null) {
					Point worldPoint = t.getWorldPoint();
					internalView.getCoordinateMapper().worldToLocal(worldPoint);
					cm.localToWorld(worldPoint);
					t.setEndpoint2(worldPoint);
				}
			}
		}

		super.draw(view, cm, g, r);
	}

}
