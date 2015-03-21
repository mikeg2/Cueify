package com.cueify.output.graph.function;

import com.cueify.output.graph.BasicSingleYXPointGraph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public abstract class LinearFunctionGraph extends BasicSingleYXPointGraph implements FunctionGraph {

	public LinearFunctionGraph(Range domain, Range range) {
		super(domain, range);
	}

	@Override
	public Point getSinglePointAtX(double x) {
		return interpolatePoint(x);
	}

	protected Point interpolatePoint(double x) {
		Point before = getClosestPointBeforeOr0(x);
		Point after = getClosestPointAfterOr0(x);
		double yValue = calculateInterpolationBetweenPoints(before, after, x);
		return new ImmutablePoint(x, yValue);
	}

	private Point getClosestPointBeforeOr0(double x) {
		Point closest = new ImmutablePoint(0, 0);
		for (Point p : getAllPoints()) {
			if(p.getX() <= x)
				continue;
			else 
				closest = p.getX() > closest.getX() ? p : closest;
		}
		return closest;
	}

	private Point getClosestPointAfterOr0(double x) {
		Point closest = new ImmutablePoint(0, 0);
		for (Point p : getAllPoints()) {
			if(p.getX() >= x)
				continue;
			else 
				closest = p.getX() < closest.getX() ? p : closest;
		}
		return closest;
	}
	
	private double calculateInterpolationBetweenPoints(Point before, Point after,
			double x) {
		double y1 = before.getX();
		double x1 = before.getX();
		double y3 = after.getY();
		double x3 = after.getX();
		return Interpolator.linearInterpolate(x, x1, y1, x3, y3);
	}
}
