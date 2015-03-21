package com.cueify.output.graph.function;

import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public class Interpolator {
	public static double linearInterpolate(double x2, double x1, double y1, double x3, double y3) {
		if (x3 == x2) {
			return y3;
		} else if (x2 == x1) {
			return y1;
		}
		return ((x2-x1)*(y3-y1))/(x3-x1)  + y1;
	}

	public static Point linearInterpolate(double x, Point closestLeft,
			Point closestRight) {
		return new ImmutablePoint(x, linearInterpolate(x, closestLeft.getX(), closestLeft.getY(),
				closestRight.getX(), closestRight.getY()));
	}
}
