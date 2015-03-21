package com.cueify.output.graph;

import com.cueify.output.graph.point.Point;

//TODO possibly make mutable wrapper for Point to use internally and speed
// up performance
public interface MutablePointGraph extends PointGraph {
	public void addPoint(Point p);
	public void deletePoint(Point p);
	public void replacePoint(Point replace, Point with);
}