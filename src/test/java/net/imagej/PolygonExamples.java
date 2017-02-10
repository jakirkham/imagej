package net.imagej;

import java.util.ArrayList;
import java.util.List;

import net.imglib2.RealLocalizable;
import net.imglib2.RealPoint;
import net.imglib2.roi.Regions;
import net.imglib2.roi.geometric.GeomRegions;
import net.imglib2.roi.geometric.Polygon2D;

public class PolygonExamples
{

	private PolygonExamples() {
		// prevent instantiation of utility class
	}

	public static ImageJ launch(final String... args) {
		final ImageJ ij = new ImageJ();
		ij.launch(args);

		return ij;
	}

	public static void main(final String... args) {
		ImageJ ij = launch(args);

		final Polygon2D hexagon = createHexagon();
		final Polygon2D selfIntersect = createSelfIntersectingPolygon();
		final Polygon2D combined = createCombinedPolygons();

		// doesn't work because can't display real space object
		ij.ui().show(hexagon);

		// rasterize
		ij.ui().show(Regions.rasterize(hexagon));
		ij.ui().show(Regions.rasterize(selfIntersect));
		ij.ui().show(Regions.rasterize(combined));
	}

	public static Polygon2D createHexagon() {
		final List<RealLocalizable> points = new ArrayList< >();
		points.add(new RealPoint(new double[] {0, 80}));
		points.add(new RealPoint(new double[] {40, 110}));
		points.add(new RealPoint(new double[] {80, 80}));
		points.add(new RealPoint(new double[] {80, 30}));
		points.add(new RealPoint(new double[] {40, 0}));
		points.add(new RealPoint(new double[] {0, 30}));

		return GeomRegions.polygon2D(points);
	}

	public static Polygon2D createSelfIntersectingPolygon() {
		final List<RealLocalizable> points = new ArrayList< >();
		points.add(new RealPoint(new double[] {70, 100}));
		points.add(new RealPoint(new double[] {30, 0}));
		points.add(new RealPoint(new double[] {15, 80}));
		points.add(new RealPoint(new double[] {120, 80}));
		points.add(new RealPoint(new double[] {120, 10}));
		points.add(new RealPoint(new double[] {0, 10}));
		points.add(new RealPoint(new double[] {100, 50}));

		return GeomRegions.polygon2D( points );
	}

	public static Polygon2D createCombinedPolygons() {
		final RealPoint origin = new RealPoint(new double[]{0,0});

		final List<RealLocalizable> points = new ArrayList<>();

		// Create square

		points.add(origin);
		points.add(new RealPoint(new double[] {1, 1}));
		points.add(new RealPoint(new double[] {1, 100}));
		points.add(new RealPoint(new double[] {100, 100}));
		points.add(new RealPoint(new double[] {100, 1}));
		points.add(new RealPoint(new double[] {1, 1}));
		
		// Create triangle

		points.add(origin);
		points.add(new RealPoint(new double[] {20, 60}));
		points.add(new RealPoint(new double[] {80, 60}));
		points.add(new RealPoint(new double[] {50, 20}));
		points.add(new RealPoint(new double[] {20, 60}));

		// Create pentagon

		points.add(origin);
		points.add(new RealPoint(new double[] {150, 20}));
		points.add(new RealPoint(new double[] {150, 80}));
		points.add(new RealPoint(new double[] {200, 120}));
		points.add(new RealPoint(new double[] {250, 80}));
		points.add(new RealPoint(new double[] {250, 20}));
		points.add(new RealPoint(new double[] {150, 20}));

		// Create rectangle

		points.add(origin);
		points.add(new RealPoint( new double[] {90, 40}));
		points.add(new RealPoint( new double[] {90, 60}));
		points.add(new RealPoint( new double[] {220, 60}));
		points.add(new RealPoint( new double[] {220, 40}));
		points.add(new RealPoint( new double[] {90, 40}));
		points.add(origin);

		return GeomRegions.polygon2D( points );
	}
}
