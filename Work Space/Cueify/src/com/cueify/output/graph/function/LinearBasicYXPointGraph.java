package com.cueify.output.graph.function;

import com.cueify.output.graph.BasicSingleYXPointGraph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public class LinearBasicYXPointGraph extends BasicSingleYXPointGraph {
	private Point defaultEndPoint = new ImmutablePoint(0,0);
	private Point defaultStartPoint;
	
	public LinearBasicYXPointGraph(Range domain, Range range) {
		super(domain, range);
		defaultStartPoint = new ImmutablePoint(domain.getEnd(), 0);
	}
	
	@Override
	public synchronized Point getSinglePointAtX(double x) {
		Point closestLeft = getClosestLeftPoint(x);
		Point closestRight = getClosestRightPoint(x);
		Point retur = Interpolator.linearInterpolate(x, closestLeft, closestRight);
		return retur;
	}

	private synchronized Point getClosestRightPoint(double x) {
		Point closest = null;
		for (Point p : getPoints()) {
			if (p.getX() >= x 
					&& (closest == null || 
						p.getX() < closest.getX())) {
				closest = p;
			}
		}
		if (closest == null)
			System.out.println("USED DEFAULT");
		return closest == null ? defaultEndPoint : closest;
	}

	private synchronized Point getClosestLeftPoint(double x) {
		Point closest = null;
		for (Point p : getPoints()) {
			if (p.getX() <= x 
					&& (closest == null || 
					p.getX() > closest.getX())) {
				closest = p;
			}
		}
		return closest == null ? defaultStartPoint : closest;
	}

}
