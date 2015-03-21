package com.cueify.output.graph;

import com.cueify.output.graph.point.Point;

public interface SingleXYPointGraph extends Graph {
	public Point getSinglePointAtX(double x);
}
