package org.archstudio.bna.logics.coordinating;

import static org.junit.Assert.assertEquals;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.things.shapes.RectangleThing;
import org.archstudio.bna.things.shapes.SplineThing;
import org.archstudio.bna.utils.DefaultBNAModel;
import org.archstudio.bna.utils.DefaultBNAWorld;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class MirrorValueLogicTest {

	protected IBNAModel model;
	protected IBNAWorld world;
	protected IThingLogicManager tlm;

	@Before
	public void setUp() throws Exception {
		model = new DefaultBNAModel();
		world = new DefaultBNAWorld(null, model);
		tlm = world.getThingLogicManager();
	}

	@Test
	public void mirrorRectangle() {
		RectangleThing b1 = model.addThing(new RectangleThing(null));
		RectangleThing b2 = model.addThing(new RectangleThing(null));
		RectangleThing b3 = model.addThing(new RectangleThing(null));

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		mvl.mirrorValue(b1, IHasBoundingBox.BOUNDING_BOX_KEY, b2);
		mvl.mirrorValue(b2, IHasBoundingBox.BOUNDING_BOX_KEY, b3);

		b1.setMinimumSize(new Dimension(10, 10));

		b1.setBoundingBox(new Rectangle(1, 2, 3, 4));
		assertEquals(new Rectangle(1, 2, 10, 10), b1.getBoundingBox());
		assertEquals(new Rectangle(1, 2, 10, 10), b2.getBoundingBox());
		assertEquals(new Rectangle(1, 2, 10, 10), b3.getBoundingBox());

		b1.setBoundingBox(new Rectangle(2, 4, 6, 8));
		assertEquals(new Rectangle(2, 4, 10, 10), b1.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b2.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b3.getBoundingBox());

		b3.setBoundingBox(new Rectangle(1, 2, 3, 4));
		assertEquals(new Rectangle(2, 4, 10, 10), b1.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b2.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b3.getBoundingBox());

		b2.setBoundingBox(new Rectangle(1, 2, 3, 4));
		assertEquals(new Rectangle(2, 4, 10, 10), b1.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b2.getBoundingBox());
		assertEquals(new Rectangle(2, 4, 10, 10), b3.getBoundingBox());
	}

	@Test
	public void mirrorPoints() {
		SplineThing s1 = model.addThing(new SplineThing(null));
		SplineThing s2 = model.addThing(new SplineThing(null));
		SplineThing s3 = model.addThing(new SplineThing(null));

		MirrorValueLogic mvl = tlm.addThingLogic(MirrorValueLogic.class);
		mvl.mirrorValue(s1, IHasPoints.POINTS_KEY, s2);
		mvl.mirrorValue(s2, IHasPoints.POINTS_KEY, s3);

		s1.setPoints(Lists.newArrayList(new Point(5, 6), new Point(7, 9)));
		assertEquals(new Rectangle(5, 6, 3, 4), s1.getBoundingBox());
		assertEquals(new Rectangle(5, 6, 3, 4), s2.getBoundingBox());
		assertEquals(new Rectangle(5, 6, 3, 4), s3.getBoundingBox());

		s1.setPoints(Lists.newArrayList(new Point(10, 11), new Point(12, 15)));
		assertEquals(new Rectangle(10, 11, 3, 5), s1.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s2.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s3.getBoundingBox());

		s3.setPoints(Lists.newArrayList(new Point(5, 6), new Point(7, 9)));
		assertEquals(new Rectangle(10, 11, 3, 5), s1.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s2.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s3.getBoundingBox());

		s2.setPoints(Lists.newArrayList(new Point(5, 6), new Point(7, 9)));
		assertEquals(new Rectangle(10, 11, 3, 5), s1.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s2.getBoundingBox());
		assertEquals(new Rectangle(10, 11, 3, 5), s3.getBoundingBox());
	}
}
