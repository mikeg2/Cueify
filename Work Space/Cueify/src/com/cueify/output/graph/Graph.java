package com.cueify.output.graph;

import com.cueify.output.graph.point.Point;


public interface Graph {
	public Point[] getPointsAtX(double x);
	
	// More thread safe. Gets all the points at once with a lock.
	// public Point[][] getPointsAtXIntervals(double interval, Range domain);
}
