package net.imagej;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import net.imglib2.RealLocalizable;
import net.imglib2.RealRandomAccess;
import net.imglib2.roi.geometric.Polygon2D;
import net.imglib2.type.logic.BoolType;

import org.junit.Before;
import org.junit.Test;

public class PolygonTests
{

	private Polygon2D hexagon;
	private Polygon2D selfIntersect;
	private Polygon2D combined;

	@Before
	public void init() {
		hexagon = PolygonExamples.createHexagon();
		selfIntersect = PolygonExamples.createSelfIntersectingPolygon();
		combined = PolygonExamples.createCombinedPolygons();
	}

	@Test
	public void testHexagon() {
		RealRandomAccess< BoolType > ra = hexagon.realRandomAccess();

		// Vertices
		ra.setPosition( new double[]{0, 80} );
		assertTrue(ra.get().get());

		ra.setPosition( new double[]{40, 110} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{80, 80} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{80, 30} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{40, 0} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{0, 30} );
		assertTrue(ra.get().get());
	}

	@Test
	public void testSelfIntersect() {
		RealRandomAccess< BoolType > ra = selfIntersect.realRandomAccess();

		// Vertices
		ra.setPosition( new double[]{70, 100} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{30, 0} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{15, 80} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{120, 80} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{120, 10} );
		assertFalse(ra.get().get());

		ra.setPosition( new double[]{0, 10} );
		assertTrue(ra.get().get());

		ra.setPosition( new double[]{100, 50} );
		assertTrue(ra.get().get());
	}

	@Test
	public void testCombined() {
		RealRandomAccess< BoolType > ra = combined.realRandomAccess();

		// Vertices
		// Square
		ra.setPosition(new double[] {1, 1});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {1, 100});
		assertFalse(ra.get().get());

		ra.setPosition(new double[] {100, 100});
		assertFalse(ra.get().get());

		ra.setPosition(new double[] {100, 1});
		assertFalse(ra.get().get());

		// Triangle
		ra.setPosition(new double[] {20, 60});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {80, 60});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {50, 20});
		assertTrue(ra.get().get());

		// Pentagon
		ra.setPosition(new double[] {150, 20});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {150, 80});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {200, 120});
		assertFalse(ra.get().get());

		ra.setPosition(new double[] {250, 80});
		assertFalse(ra.get().get());

		ra.setPosition(new double[] {250, 20});
		assertFalse(ra.get().get());

		// Rectangle
		ra.setPosition(new double[] {90, 40});
		assertFalse(ra.get().get());

		ra.setPosition(new double[] {90, 60});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {220, 60});
		assertTrue(ra.get().get());

		ra.setPosition(new double[] {220, 40});
		assertTrue(ra.get().get());
	}
}
